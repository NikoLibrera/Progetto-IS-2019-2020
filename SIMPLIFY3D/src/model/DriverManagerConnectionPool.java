package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool 
{
	static 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("DB driver non trovato:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException 
	{
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "simplify3d";
		String username = "root";
		String password = "*Resistenza98";

		newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useTimezone=true&serverTimezone=UTC", username, password);
		
		newConnection.setAutoCommit(false);
		return newConnection;
	}	
	
	public static synchronized Connection getConnection() throws SQLException 
	{
		Connection connection=null;

		connection = createDBConnection();

		return connection;
	}
	
	public static synchronized void releaseConnection(Connection connection) throws SQLException 
	{
		connection.close();
	}	
}