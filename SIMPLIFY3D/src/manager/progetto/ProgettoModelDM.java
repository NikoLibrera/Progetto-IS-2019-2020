package manager.progetto;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgettoModelDM 
{
	public static void doUpload(Progetto progetto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO progetto (id_progetto, titolo, descrizione, file_modello, immagine, consigli, categoria, versione, username) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, progetto.getId_progetto());
			preparedStatement.setString(2, progetto.getTitolo());
			preparedStatement.setString(3, progetto.getDescrizione());
			preparedStatement.setBlob(4, progetto.getFile_modello());
			preparedStatement.setBlob(5, progetto.getImmagine());
			preparedStatement.setString(6, progetto.getConsigli());
			preparedStatement.setString(7, progetto.getCategoria());
			preparedStatement.setInt(8, progetto.getVersione());
			preparedStatement.setString(9, progetto.getUsername());

			System.out.println("doUpload: "+ preparedStatement.toString());
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
