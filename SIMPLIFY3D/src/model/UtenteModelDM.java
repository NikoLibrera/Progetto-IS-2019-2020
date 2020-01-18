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
					preparedStatement.setBoolean(5, utente.getIsAdmin());
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
}
