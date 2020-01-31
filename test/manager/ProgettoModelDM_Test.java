package manager;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;
import manager.progetto.ProgettoModelDM;
import manager.utente.UtenteModelDM;
import model.*;

public class ProgettoModelDM_Test extends TestCase 
{
	Utente utente=null;
	Progetto p=null;
	
	@Override
	protected void setUp() throws Exception , SQLException 
	{
		utente=new Utente();
		utente.setUsername("username");
		utente.setPassword("123456789");
		utente.setEmail("test@email.it");
		UtenteModelDM.doRegistrazione(utente, 0);
		
		ProgettoModelDM model=new ProgettoModelDM();
		p=new Progetto();
		p.setUsername(utente.getUsername());
		p.setTitolo("titolo");
		p.setCategoria("Art");
		p.setConsigli("consigli");
		p.setDescrizione("descrizione");
		p.setVersione(1);
		p.setId_progetto(99999);
		
		model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		UtenteModelDM.deleteUtenteByUsername(utente.getUsername());
		ProgettoModelDM model=new ProgettoModelDM();
		try
		{
			model.doCancellaProgetto(99999, "username");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void testDoUpload() throws SQLException
	{
		System.out.println("Test doUpload");
		
		ProgettoModelDM model=new ProgettoModelDM();
		
		Progetto p=new Progetto();
		p.setUsername(utente.getUsername());
		p.setTitolo("titolo");
		p.setCategoria("Art");
		p.setConsigli("consigli");
		p.setDescrizione("descrizione");
		p.setVersione(1);
		p.setId_progetto(model.getLastId()+1);
		
		model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
	
		Progetto p2=null;
		
		p2=model.getProgettoById(p.getId_progetto());
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());

		model.doCancellaProgetto(p.getId_progetto(), p.getUsername());

		System.out.println("\n");
	}

	public void testGetLastId() 
	{
		System.out.println("Test getLastId");
		
		ProgettoModelDM model=new ProgettoModelDM();
		
		int id2=model.getLastId();
		
		assertEquals(p.getId_progetto(), id2);
		
		System.out.println("\n");
	}

	public void testGetByCategoria() throws SQLException
	{
		System.out.println("Test getByCategoria");
		
		ArrayList<Progetto> progetti = null;

		progetti=ProgettoModelDM.getByCategoria("Art");

		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}

	public void testGetByUsername() throws SQLException
	{
		System.out.println("Test getByUsername");
		
		ArrayList<Progetto> progetti = null;

		progetti=ProgettoModelDM.getByUsername(utente.getUsername());
		
		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}

	public void testGetProgettoById() throws SQLException
	{
		System.out.println("Test getProgettoById");
		
		ProgettoModelDM model=new ProgettoModelDM();
	
		Progetto p2=null;

		p2=model.getProgettoById(p.getId_progetto());
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());
	
		System.out.println("\n");
	}

	public void testModificaProgetto() throws SQLException
	{
		System.out.println("Test ModificaProgetto");
		
		ProgettoModelDM model=new ProgettoModelDM();
		
		p.setTitolo("titolo modificato");

		model.modificaProgetto(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		
		Progetto p2=null;

		p2=model.getProgettoById(p.getId_progetto());

		assertEquals(p.getTitolo(), p2.getTitolo());
		
		System.out.println("\n");
	}

	public void testAddToPreferiti() throws SQLException
	{
		System.out.println("Test addToPreferiti");
		
		ProgettoModelDM model = new ProgettoModelDM();
	
		model.addToPreferiti(p, utente);

		boolean b=false;
		
		b=model.isPreferito(p, utente);

		assertEquals(true, b);

		model.removeFromPreferiti(p, utente);
		
		System.out.println("\n");
	}

	public void testAggiornaDownload() throws SQLException
	{
		System.out.println("Test aggiornaDownload");
		
		ProgettoModelDM model = new ProgettoModelDM();

		model.aggiornaDownload(p, utente);
		
		int download = 0;

		download=model.getDownloadById(p.getId_progetto());

		assertNotEquals(0, download);
		
		System.out.println("\n");
	}

	public void testGetDownloadById() throws SQLException
	{
		System.out.println("Test getDownloadById");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		model.aggiornaDownload(p, utente);
		
		int download = 0;
		
		download=model.getDownloadById(p.getId_progetto());

		assertNotEquals(0, download);
		
		System.out.println("\n");
	}

	public void testRemoveFromPreferiti() throws SQLException
	{
		System.out.println("Test removeFromPreferiti");
		
		ProgettoModelDM model = new ProgettoModelDM();

		model.addToPreferiti(p, utente);
		
		boolean b=false;

		b=model.isPreferito(p, utente);
		
		assertEquals(true, b);

		model.removeFromPreferiti(p, utente);
		
		b=true;

		b=model.isPreferito(p, utente);
		
		assertEquals(false, b);
		
		System.out.println("\n");
	}

	public void testIsPreferito() throws SQLException
	{
		System.out.println("Test isPreferito");
		
		ProgettoModelDM model = new ProgettoModelDM();

		model.addToPreferiti(p, utente);
		
		boolean b=false;
		
		b=model.isPreferito(p, utente);
		
		assertEquals(true, b);
		
		model.removeFromPreferiti(p, utente);
		
		b=true;
		
		b=model.isPreferito(p, utente);
		
		assertEquals(false, b);
		
		System.out.println("\n");
	}

	public void testGetPreferitiByUsername() throws SQLException
	{
		System.out.println("Test getPreferitiByUsername");
		
		ProgettoModelDM model = new ProgettoModelDM();

		model.addToPreferiti(p, utente);
		
		ArrayList<Progetto> progetti = null;
		
		progetti=ProgettoModelDM.getPreferitiByUsername(utente.getUsername());
		
		assertEquals(false, progetti.isEmpty());
		
		model.removeFromPreferiti(p, utente);
		
		System.out.println("\n");
	}
	
	public void testDoCancellaProgetto() throws SQLException
	{
		System.out.println("Test doCancellaProgetto");
		
		ProgettoModelDM model=new ProgettoModelDM();
		
		Progetto p=new Progetto();
		
		int idProgetto=model.getLastId()+1;
		
		p.setUsername(utente.getUsername());
		p.setTitolo("titolo");
		p.setCategoria("Art");
		p.setConsigli("consigli");
		p.setDescrizione("descrizione");
		p.setVersione(1);
		p.setId_progetto(idProgetto);
		
		model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		
		Progetto p2=null;

		p2=model.getProgettoById(p.getId_progetto());
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());
	
		model.doCancellaProgetto(p.getId_progetto(), p.getUsername());
		
		assertNotEquals(idProgetto, model.getLastId());
		
		System.out.println("\n");
	}

	public void testRicercaBarra() throws SQLException
	{
		System.out.println("Test ricercaBarra");
		
		ProgettoModelDM model = new ProgettoModelDM();
		ArrayList<Progetto> progetti = null;
		
		progetti=model.ricercaBarra("titolo");
		
		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}
}