package manager.valcom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverManagerConnectionPool;

public class ValcomModelDM {
	
	public Integer getNumeroValutazioniById(int id) throws SQLException 
	{
	    Connection connection = null;
	    Integer n=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT count(*)as n FROM valutazione WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, id);

	      System.out.println("getNumeroValutazioniById:" + preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      if (rs.next())
	      {
	    	  n=rs.getInt("n");
	      }
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
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
	    return n;
	  }
	
	public Integer getNumeroCommentiById(int id) throws SQLException 
	{
	    Connection connection = null;
	    Integer n=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT count(*)as n FROM commento WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, id);

	      System.out.println("getNumeroCommentiById:" + preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      if (rs.next())
	      {
	    	  n=rs.getInt("n");
	      }
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
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
	    return n;
	  }

	public Integer getMediaValutazioniById(int id) throws SQLException 
	{
	    Connection connection = null;
	    Float n=null;
	    Integer media=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT AVG(voto)as n FROM valutazione WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, id);

	      System.out.println("getMediaValutazioniById:" + preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      if (rs.next())
	      {
	    	  n=rs.getFloat("n");
	    	  media=Math.round(n);
	      }
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
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
	    return media;
	  }
}
