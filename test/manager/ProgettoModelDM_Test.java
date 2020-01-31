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
	protected void setUp() throws Exception 
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
		
		try 
		{
			model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
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
	
	public void testDoUpload() 
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
		
		try 
		{
			model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Progetto p2=null;
		
		try
		{
			p2=model.getProgettoById(p.getId_progetto());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());
		
		try 
		{
			model.doCancellaProgetto(p.getId_progetto(), p.getUsername());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
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

	public void testGetByCategoria() 
	{
		System.out.println("Test getByCategoria");
		
		ArrayList<Progetto> progetti = null;
		
		try
		{
			progetti=ProgettoModelDM.getByCategoria("Art");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}

	public void testGetByUsername()
	{
		System.out.println("Test getByUsername");
		
		ArrayList<Progetto> progetti = null;
		
		try
		{
			progetti=ProgettoModelDM.getByUsername(utente.getUsername());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}

	public void testGetProgettoById()
	{
		System.out.println("Test getProgettoById");
		
		ProgettoModelDM model=new ProgettoModelDM();
	
		Progetto p2=null;
		
		try 
		{
			p2=model.getProgettoById(p.getId_progetto());
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());
	
		System.out.println("\n");
	}

	public void testModificaProgetto() 
	{
		System.out.println("Test ModificaProgetto");
		
		ProgettoModelDM model=new ProgettoModelDM();
		
		p.setTitolo("titolo modificato");
			
		try
		{
			model.modificaProgetto(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Progetto p2=null;
		
		try
		{
			p2=model.getProgettoById(p.getId_progetto());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertEquals(p.getTitolo(), p2.getTitolo());
		
		System.out.println("\n");
	}

	public void testAddToPreferiti() 
	{
		System.out.println("Test addToPreferiti");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try
		{
			model.addToPreferiti(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		boolean b=false;
		
		try
		{
			b=model.isPreferito(p, utente);
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		assertEquals(true, b);
		
		try
		{
			model.removeFromPreferiti(p, utente);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("\n");
	}

	public void testAggiornaDownload() 
	{
		System.out.println("Test aggiornaDownload");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try 
		{
			model.aggiornaDownload(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		int download = 0;
		
		try 
		{
			download=model.getDownloadById(p.getId_progetto());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		assertNotEquals(0, download);
		
		System.out.println("\n");
	}

	public void testGetDownloadById()
	{
		System.out.println("Test getDownloadById");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try
		{
			model.aggiornaDownload(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		int download = 0;
		
		try 
		{
			download=model.getDownloadById(p.getId_progetto());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertNotEquals(0, download);
		
		System.out.println("\n");
	}

	public void testRemoveFromPreferiti()
	{
		System.out.println("Test removeFromPreferiti");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try
		{
			model.addToPreferiti(p, utente);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		boolean b=false;
		
		try
		{
			b=model.isPreferito(p, utente);
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		assertEquals(true, b);
		
		try
		{
			model.removeFromPreferiti(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		b=true;
		
		try
		{
			b=model.isPreferito(p, utente);
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		assertEquals(false, b);
		
		System.out.println("\n");
	}

	public void testIsPreferito() 
	{
		System.out.println("Test isPreferito");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try
		{
			model.addToPreferiti(p, utente);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		boolean b=false;
		
		try
		{
			b=model.isPreferito(p, utente);
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		assertEquals(true, b);
		
		try 
		{
			model.removeFromPreferiti(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		b=true;
		
		try 
		{
			b=model.isPreferito(p, utente);
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		assertEquals(false, b);
		
		System.out.println("\n");
	}

	public void testGetPreferitiByUsername()
	{
		System.out.println("Test getPreferitiByUsername");
		
		ProgettoModelDM model = new ProgettoModelDM();
		
		try
		{
			model.addToPreferiti(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Progetto> progetti = null;
		
		try 
		{
			progetti=ProgettoModelDM.getPreferitiByUsername(utente.getUsername());
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		assertEquals(false, progetti.isEmpty());
		
		try 
		{
			model.removeFromPreferiti(p, utente);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("\n");
	}
	
	public void testDoCancellaProgetto()
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
		
		try
		{
			model.doUpload(p, InputStream.nullInputStream(), InputStream.nullInputStream());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Progetto p2=null;
		
		try 
		{
			p2=model.getProgettoById(p.getId_progetto());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(p.getId_progetto(), p2.getId_progetto());
		
		try 
		{
			model.doCancellaProgetto(p.getId_progetto(), p.getUsername());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertNotEquals(idProgetto, model.getLastId());
		
		System.out.println("\n");
	}

	public void testRicercaBarra()
	{
		System.out.println("Test ricercaBarra");
		
		ProgettoModelDM model = new ProgettoModelDM();
		ArrayList<Progetto> progetti = null;
		
		try
		{
			progetti=model.ricercaBarra("titolo");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(false, progetti.isEmpty());
		
		System.out.println("\n");
	}
}