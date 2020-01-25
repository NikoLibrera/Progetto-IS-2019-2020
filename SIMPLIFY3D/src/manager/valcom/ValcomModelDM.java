package manager.valcom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Commento;
import model.DriverManagerConnectionPool;
import model.RispostaCommento;

public class ValcomModelDM {
	
	public Integer getNumeroValutazioniByIdProgetto(int idProgetto) throws SQLException 
	{
	    Connection connection = null;
	    Integer n=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT count(*)as n FROM valutazione WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, idProgetto);

	      System.out.println("getNumeroValutazioniByIdProgetto:" + preparedStatement.toString());

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
	
	public Integer getNumeroCommentiByIdProgetto(int idProgetto) throws SQLException 
	{
	    Connection connection = null;
	    Integer n=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT count(*)as n FROM commento WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, idProgetto);

	      System.out.println("getNumeroCommentiByIdProgetto:" + preparedStatement.toString());

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
	
	public Integer getNumeroRisposteByIdCommento(int idCommento) throws SQLException 
	{
	    Connection connection = null;
	    Integer n=null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT count(*)as n FROM risposta_commento WHERE id_commento= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, idCommento);

	      System.out.println("getNumeroRisposteByIdCommento:" + preparedStatement.toString());

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

	public static ArrayList<Commento> getCommentiByIdProgetto(int idProgetto) throws SQLException
	{
		Commento c=null;
		ArrayList<Commento> commenti=new ArrayList<Commento>();
		Connection conn = DriverManagerConnectionPool.getConnection();
		try 
		{
			Statement st=conn.createStatement();
			 System.out.println("getCommentiByIdProgetto:" + "select * from commento where id_progetto='"+idProgetto+"'");
			ResultSet result =st.executeQuery("select * from commento where id_progetto='"+idProgetto+"'");
			while(result.next())
			{
				int id_commento=result.getInt("id_commento");
				String contenuto=result.getString("contenuto");
				String username=result.getString("username");
				
				c=new Commento(id_commento, contenuto, username, idProgetto);
				commenti.add(c);
			}
			DriverManagerConnectionPool.releaseConnection(conn);
			return commenti;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<RispostaCommento> getRisposteByIdCommento(int idCommento) throws SQLException
	{
		RispostaCommento r=null;
		ArrayList<RispostaCommento> risposte=new ArrayList<RispostaCommento>();
		Connection conn = DriverManagerConnectionPool.getConnection();
		try 
		{
			Statement st=conn.createStatement();
			 System.out.println("getRisposteByIdCommento:" + "select * from risposta_commento where id_commento='"+idCommento+"'");
			ResultSet result =st.executeQuery("select * from risposta_commento where id_commento='"+idCommento+"'");
			while(result.next())
			{
				int id_risposta=result.getInt("id_risposta_commento");
				String contenuto=result.getString("contenuto");
				String username=result.getString("username");
				
				r=new RispostaCommento(id_risposta, contenuto, username, idCommento);
				risposte.add(r);
			}
			DriverManagerConnectionPool.releaseConnection(conn);
			return risposte;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void inserisciCommento(Commento commento,int idProgetto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ValcomModelDM model=new ValcomModelDM();

		String insertSQL = "INSERT INTO commento (id_commento, contenuto, username, id_progetto) VALUES (?, ?, ?, ?)"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, model.getLastIdCommento(idProgetto)+1);
			preparedStatement.setString(2, commento.getContenuto());
			preparedStatement.setString(3, commento.getUsername());
			preparedStatement.setInt(4, idProgetto);

			System.out.println("inserisciCommento: "+ preparedStatement.toString());
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
	
	public int getLastIdCommento(int idProgetto) 
	{
		int idCommento=0;
		try 
		{
	        Connection connection=DriverManagerConnectionPool.getConnection();
	        
	        Statement statement=connection.createStatement();
	        ResultSet r=statement.executeQuery("SELECT MAX(id_commento) AS id FROM commento WHERE id_progetto='"+idProgetto+"'");
	        if(r.next())
			{
	        	idCommento=r.getInt("id");
			}
			
			connection.close();				
	
		}
		catch (SQLException e) 
		{
			System.out.println("Errore durante la connessione." + e.getMessage());
		}
		return idCommento;
	}
	
	public void inserisciRisposta(RispostaCommento risposta,int idCommento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ValcomModelDM model=new ValcomModelDM();

		String insertSQL = "INSERT INTO risposta_commento (id_risposta_commento, contenuto, username, id_commento) VALUES (?, ?, ?, ?)"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, model.getLastIdRisposta(idCommento)+1);
			preparedStatement.setString(2, risposta.getContenuto());
			preparedStatement.setString(3, risposta.getUsername());
			preparedStatement.setInt(4, idCommento);

			System.out.println("inserisciRisposta: "+ preparedStatement.toString());
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
	
	public int getLastIdRisposta(int idCommento) 
	{
		int idRisposta=0;
		try 
		{
	        Connection connection=DriverManagerConnectionPool.getConnection();
	        
	        Statement statement=connection.createStatement();
	        ResultSet r=statement.executeQuery("SELECT MAX(id_risposta_commento) AS id FROM risposta_commento WHERE id_commento='"+idCommento+"'");
	        if(r.next())
			{
	        	idRisposta=r.getInt("id");
			}
			
			connection.close();				
	
		}
		catch (SQLException e) 
		{
			System.out.println("Errore durante la connessione." + e.getMessage());
		}
		return idRisposta;
	}

	public void cancellaRisposta(int idRisposta) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM risposta_commento WHERE id_risposta_commento = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		
			preparedStatement.setInt(1, idRisposta);
		
			preparedStatement.executeUpdate();
			System.out.println("cancellaRisposta: "+ preparedStatement.toString());
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
	
	public void cancellaCommento(int idCommento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ValcomModelDM model= new ValcomModelDM();
		
		String deleteSQL = "DELETE FROM commento WHERE id_commento = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		
			preparedStatement.setInt(1, idCommento);
		
			preparedStatement.executeUpdate();
			System.out.println("cancellaCommento: "+ preparedStatement.toString());
			model.cancellaRisposteByIdCommento(idCommento);
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
	
	public void cancellaRisposteByIdCommento(int idCommento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM risposta_commento WHERE id_commento = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		
			preparedStatement.setInt(1, idCommento);
		
			preparedStatement.executeUpdate();
			System.out.println("cancellaRisposteByIdCommento: "+ preparedStatement.toString());
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
