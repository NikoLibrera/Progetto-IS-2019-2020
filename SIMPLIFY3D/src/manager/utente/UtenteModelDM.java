package manager.utente;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteModelDM
{
	public static boolean doRegistrazione(Utente utente, int codice) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		final String insertSQL = "INSERT INTO utente (username, cognome, nome, data_nascita, isAdmin, password, email, nazionalita, confermato, codice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String controlloSQL = "SELECT * FROM utente WHERE email = ?";
		
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(controlloSQL);
			
			preparedStatement.setString(1, utente.getEmail());
			
			System.out.println("ControllaEmail: "+preparedStatement.toString());
			
			//In RS finisce il risultato della query che è stata appena eseguita
			ResultSet rs = preparedStatement.executeQuery();
			
			//Se rs.next == false allora non esiste un altro utente con la 
			//stessa email e quindi si passa all inserimento dell'utente nel DB
			if(rs.next() == false)   
			{
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, utente.getUsername());
				preparedStatement.setString(2, utente.getCognome());
				preparedStatement.setString(3, utente.getNome());
				preparedStatement.setString(4, utente.getData_nascita());
				preparedStatement.setInt(5, utente.getIsAdmin());
				preparedStatement.setString(6, utente.getPassword());
				preparedStatement.setString(7, utente.getEmail());
				preparedStatement.setString(8, utente.getNazionalita());
				preparedStatement.setInt(9, utente.getConfermato());
				preparedStatement.setInt(10, codice);
				
				System.out.println("doRegistrazione: "+preparedStatement.toString());
				preparedStatement.executeUpdate();

				connection.commit();
				return true;
			}
			else 
			{
		        return false;
		    }
		} 
		finally 
		{
			try 
			{
		        if (preparedStatement != null) 
		        {
		          preparedStatement.close();
		        }
		    } 
			finally 
			{
		        DriverManagerConnectionPool.releaseConnection(connection);
		    }
		}
	}
	
	public Utente doLogin(String username, String password) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente u = new Utente();
	
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND password = ? AND confermato=1";
		boolean find = false;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			System.out.println("doControlSelectLogin: "+preparedStatement.toString());
			
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
				System.out.println("doLogin: "+preparedStatement.toString());
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
		if(find == true) 
			return u;
		else 
			return null;
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
			
			System.out.println("doControlSelectResetPassword: "+preparedStatement.toString());
			
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
				System.out.println("Utente trovato: "+preparedStatement.toString());
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
		if(find == true) 
			return u;
		else 
			return null;
	}
	
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
			
			System.out.println("doControlSelectModificaPassword: "+preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				find = true;
				String updateSQL = "UPDATE utente SET password = ? WHERE username = ?";
				connection.commit();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, nuovapassword);
				preparedStatement.setString(2, utente.getUsername());
				
				System.out.println("doModificaPassword: "+preparedStatement.toString());
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
		if(find == true) 
			return utente;
		else 
			return null;
	}
	
	public static Utente getUtenteByUsername(String username) throws SQLException 
	{
	    Connection connection = null;
	    Utente utente =null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT * FROM utente WHERE username= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setString(1, username);

	      System.out.println("getUtenteByUsername: "+preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      if (rs.next())
	      {
	    	  utente=new Utente();
	    	  utente.setUsername(rs.getString(1));
	          utente.setCognome(rs.getString(2));
	          utente.setNome(rs.getString(3));
	          utente.setData_nascita(rs.getString(4));
	          utente.setIsAdmin(rs.getInt(5));
	          utente.setPassword(rs.getString(6));
	          utente.setEmail(rs.getString(7));
	          utente.setNazionalita(rs.getString(8));
	          utente.setConfermato(rs.getInt(9));
	          utente.setCodice(rs.getInt(10));
	      }
	    } 
	    finally 
	    {
	      try 
	      {
	        if (preparedStatement != null) 
	        {
	          preparedStatement.close();
	        }
	      } 
	      finally 
	      {
	        DriverManagerConnectionPool.releaseConnection(connection);
	      }
	    }
	    return utente;
	  }
	
	public static int verificaCodice(String username) throws SQLException
	{
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT codice FROM utente WHERE username= ?";
	    int codice_conferma = 0;

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setString(1, username);

	      System.out.println("verificaCodice di: "+preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      while (rs.next()) 
	      {
	        codice_conferma = rs.getInt(1);
	      }
	    } 
	    finally 
	    {
	      try 
	      {
	        if (preparedStatement != null) 
	        {
	          preparedStatement.close();
	        }
	      } 
	      finally 
	      {
	        DriverManagerConnectionPool.releaseConnection(connection);
	      }
	    }
	    return codice_conferma;
	  }
	
	public static void setConfermato(Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE utente SET confermato = 1 WHERE username = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, utente.getUsername());
			
			System.out.println("doSetConfermato: "+preparedStatement.toString());
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
