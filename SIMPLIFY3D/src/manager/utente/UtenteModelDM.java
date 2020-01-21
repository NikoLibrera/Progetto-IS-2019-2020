package manager.utente;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteModelDM implements UtenteModel<Utente>
{
	@Override
	public boolean doRegistrazione(Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean emailGiaPresente = true;
		
		//Query per controllare se esiste un utente con la stessa email
		String controlloSQL = "SELECT * FROM utente WHERE email = ?";
		
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(controlloSQL);
			
			preparedStatement.setString(1, utente.getEmail());
			
			System.out.println("ControllaEmail:" + preparedStatement.toString());
			
			//In RS finisce il risultato della query che è stata appena eseguita
			ResultSet rs = preparedStatement.executeQuery();
			
			//Se rs.next == false allora non esiste un altro utente con la 
			//stessa email e quindi si passa all inserimento dell'utente nel DB
			if(rs.next() == false)   
			{
				emailGiaPresente = false;
				String insertSQL = "INSERT INTO utente (username, cognome, nome, data_nascita, isAdmin, password, email, nazionalita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				try 
				{
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, utente.getUsername());
					preparedStatement.setString(2, utente.getCognome());
					preparedStatement.setString(3, utente.getNome());
					preparedStatement.setString(4, utente.getData_nascita());
					preparedStatement.setInt(5, utente.getIsAdmin());
					preparedStatement.setString(6, utente.getPassword());
					preparedStatement.setString(7, utente.getEmail());
					preparedStatement.setString(8, utente.getNazionalita());
					
					System.out.println("doRegistrazione: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					connection.commit();
					
				}
				finally 
				{
					try 
					{
						if (preparedStatement != null)
						preparedStatement.close();
					} 
					finally 
					{
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
				preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return emailGiaPresente;
	}
	
	@Override
	public Utente doLogin(String username, String password) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente u = new Utente();
	
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND password = ?";
		boolean find = false;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			System.out.println("doControlSELECTLogin:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				find = true;
				u.setUsername(rs.getString("username"));
				u.setCognome(rs.getString("cognome"));
				u.setNome(rs.getString("nome"));
				u.setData_nascita(rs.getString("data_nascita"));
				u.setIsAdmin(rs.getInt("isAdmin"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setNazionalita(rs.getString("nazionalita"));
				System.out.println("doLogin: "+ preparedStatement.toString());
				connection.commit();
			}
		} 
		finally 
		{
			try 
			{
				if(preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		if(find == true) return u;
		else return null;
	}
	
	public Utente doPasswordDimenticata(Utente utente,String nuovapassword) throws SQLException
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Utente u = new Utente();
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND email = ?";
		boolean find = false;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getEmail());
			
			System.out.println("doControlSELECTresetPassword:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				find = true;
				u.setUsername(rs.getString("username"));
				u.setCognome(rs.getString("cognome"));
				u.setNome(rs.getString("nome"));
				u.setData_nascita(rs.getString("data_nascita"));
				u.setIsAdmin(rs.getInt("isAdmin"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setNazionalita(rs.getString("nazionalita"));
				System.out.println("utente trovato: "+ preparedStatement.toString());
				connection.commit();
				doModificaPassword(u,nuovapassword);
				
				
			}
		} 
		finally 
		{
			try 
			{
				if(preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		
			if(find == true) return u;
		else return null;
		
	}
	
	@Override
	public Utente doModificaPassword(Utente utente,String nuovapassword) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND password = ?";
		boolean find = false;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			
			System.out.println("doControlSELECTmodificaPassword:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				find = true;
				String updateSQL = "UPDATE utente SET password = ? WHERE username = ?";
				connection.commit();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, nuovapassword);
				preparedStatement.setString(2, utente.getUsername());
				
				System.out.println("doModificaPassword: "+ preparedStatement.toString());
				preparedStatement.executeUpdate();
				connection.commit();
				utente.setPassword(nuovapassword);
			}
		} 
		finally 
		{
			try 
			{
				if(preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		if(find == true) return utente;
		else return null;
	}
}
