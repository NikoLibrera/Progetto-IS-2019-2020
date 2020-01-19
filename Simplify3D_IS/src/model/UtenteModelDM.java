package model;

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
		
		//Parte il controllo che guarda se esiste già un altro utente con stesso username o con la stessa email
		String controlloSQL = "SELECT * FROM utente WHERE email = ?";// Viene eseguita una query con la quale si va a controllora se esiste già un altro utente con un email uguale
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(controlloSQL);
			
			preparedStatement.setString(1, utente.getEmail());
			
			System.out.println("ControllaEmail:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery(); //In RS finisce il risultato della query che è stata appena eseguita

			if(rs.next() == false) //Se rs.next è false vuol dire che non esiste un altro utente con la stessa email e quindi si passa all inserimento dell'utente nel DB  
			{
				emailGiaPresente = false;
				String insertSQL = "INSERT INTO utente (username, cognome, nome, data_nascita, isAdmin, password, email, nazionalita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Viene eseguita una query con la quale si va ad inserire nel DB il nuovo utente
				try 
				{
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, utente.getUsername());
					preparedStatement.setString(2, utente.getCognome());
					preparedStatement.setString(3, utente.getNome());
					preparedStatement.setDate(4, utente.getData_nascita());
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
	public Utente doLogin(String username, String password) throws SQLException //Questo metodo serve per controllare se i dati che l'utente inserisce al login corrispondono ai dati presenti nel DB
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente u = new Utente();
	
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND password = ?"; // Viene eseguita una query con la quale si va a controllare se esiste un utente con le credenziali inserite
		boolean find = false; //Si imposta una varibile booleana a false,questa serve a controllare se l'utente è stato trovato nel DB
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			System.out.println("doControlSELECTLogin:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery(); //In RS finisce il risultato della query che � stata appena eseguita
			while(rs.next()) 
			{
				find = true; //Impostiamo la variabile booleana a true poichè l'utente con queste credenziali è stato trovato
				u.setUsername(rs.getString("username"));
				u.setCognome(rs.getString("cognome"));
				u.setNome(rs.getString("nome"));
				//u.setData_nascita(rs.getDate("data_nascita"));
				//u.setIsAdmin(rs.getInt("isAdmin"));
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
	
	@Override
	public Utente doPasswordDimenticata(String username, String email) throws SQLException
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
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			
			System.out.println("doControlSELECTPasswordDimenticata:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				find = true;
				u.setUsername(rs.getString("username"));
				u.setCognome(rs.getString("cognome"));
				u.setNome(rs.getString("nome"));
				//u.setData_nascita(rs.getDate("data_nascita"));
				//u.setIsAdmin(rs.getInt("isAdmin"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setNazionalita(rs.getString("nazionalita"));
				System.out.println("doPasswordDimenticata: "+ preparedStatement.toString());
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
	
	@Override
	public void doModificaPassword(Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE utente SET password = ? WHERE username = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, utente.getPassword());
			preparedStatement.setString(2, utente.getUsername());
			
			System.out.println("doModificaPassword: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();
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
	}
}
