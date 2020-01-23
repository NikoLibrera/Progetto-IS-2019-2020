package manager.progetto;

import model.*;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

public class ProgettoModelDM 
{
	public void doUpload(Progetto progetto, InputStream file_modello, InputStream immagine) throws SQLException
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
			preparedStatement.setBlob(4, file_modello);
			preparedStatement.setBlob(5, immagine);
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
	
	public int getLastId() {
		int idProgetto=0;
		try {
	        Connection connection=DriverManagerConnectionPool.getConnection();
	        
	        Statement statement=connection.createStatement();
	        ResultSet r=statement.executeQuery("SELECT MAX(id_progetto) AS id FROM progetto");
	        if(r.next())
			{
	        	idProgetto=r.getInt("id");
			}
			
			connection.close();				
	
		} catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
		}
		return idProgetto;
	}
	
	public static ArrayList<Progetto> getMostRated() throws SQLException
	{
		Progetto p=null;
		ArrayList<Progetto>slide=new ArrayList<Progetto>();
		Connection conn = DriverManagerConnectionPool.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet result =st.executeQuery("select id_progetto, titolo,descrizione,file_modello,immagine,consigli,categoria,versione,username,valutazione.id_progetto, valutazione.voto\r\n" + 
					"from progetto,valutazione where id_progetto=valutazione.id_progetto \r\n" + 
					"order by valutazione.voto;");
			System.out.println("getMostRated");
			boolean v=true;
			int i=0;
			while(result.next()&&v)
			{
				int id=result.getInt("id_progetto");
				String titolo=result.getString("titolo");
				String descrizione=result.getString("descrizione");
				Blob file_modello=result.getBlob("file_modello");
				Blob immagine=result.getBlob("immagine");
				String consigli=result.getString("consigli");
				String categoria=result.getString("categoria");
				int versione=result.getInt("versione");
				String username=result.getString("username");
				p=new Progetto(titolo, descrizione, file_modello, immagine, consigli, categoria, username, versione);
				slide.add(p);
				i++;
				if(i==6)
					v=false;
			}
			DriverManagerConnectionPool.releaseConnection(conn);
			return slide;
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}
	
	public static ArrayList<Progetto> getByCategoria(String categoria) throws SQLException
	{
		Progetto p=null;
		ArrayList<Progetto> progetti=new ArrayList<Progetto>();
		Connection conn = DriverManagerConnectionPool.getConnection();
		try {
			Statement st=conn.createStatement();
			 System.out.println("getByCategoria:" + "select * from progetto where categoria=\"+categoria");
			ResultSet result =st.executeQuery("select * from progetto where categoria="+categoria);
			while(result.next())
			{
				int id=result.getInt("id_progetto");
				String titolo=result.getString("titolo");
				String descrizione=result.getString("descrizione");
				Blob file_modello=result.getBlob("file_modello");
				Blob immagine=result.getBlob("immagine");
				String consigli=result.getString("consigli");
				int versione=result.getInt("versione");
				String username=result.getString("username");
				String c=result.getString("categoria");
				
				p=new Progetto(id, titolo, descrizione, file_modello, immagine, consigli, c, versione, username);
				progetti.add(p);
			}
			DriverManagerConnectionPool.releaseConnection(conn);
			return progetti;
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}
	
	public Progetto getProgettoById(int id) throws SQLException 
	{
	    Connection connection = null;
	    Progetto progetto =null;
	    PreparedStatement preparedStatement = null;

	    final String select_sql = "SELECT * FROM progetto WHERE id_progetto= ?";

	    try 
	    {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(select_sql);

	      preparedStatement.setInt(1, id);

	      System.out.println("getProgettoById:" + preparedStatement.toString());

	      ResultSet rs = preparedStatement.executeQuery();

	      if (rs.next())
	      {
	    	  progetto=new Progetto();
	    	progetto.setId_progetto(rs.getInt(1));
	        progetto.setTitolo(rs.getString(2));
	        progetto.setDescrizione(rs.getString(3));
	        progetto.setFile_modello(rs.getBlob(4));
	        progetto.setImmagine(rs.getBlob(5));
	        progetto.setConsigli(rs.getString(6));
	        progetto.setCategoria(rs.getString(7));
	        progetto.setVersione(rs.getInt(8));
	        progetto.setUsername(rs.getString(9));
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
	    return progetto;
	  }
	
	public void modificaProgetto(Progetto p) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE progetto SET titolo ='"+p.getTitolo()+"',descrizione ='"+p.getDescrizione()+"',"+
		"file_modello ='"+p.getFile_modello()+"',immagine ='"+p.getImmagine()+"',consigli ='"+p.getConsigli()+"',categoria ='"+p.getCategoria()+"',"+
		" WHERE id_progetto = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, p.getId_progetto());
			
			System.out.println("doModificaProgetto: "+ preparedStatement.toString());
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
