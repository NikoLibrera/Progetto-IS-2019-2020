package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;
import manager.progetto.ProgettoModelDM;
import manager.utente.UtenteModelDM;
import manager.valcom.ValcomModelDM;
import model.Commento;
import model.Notifica;
import model.Progetto;
import model.RispostaCommento;
import model.Utente;
import model.Valutazione;

public class ValcomModelDM_Test extends TestCase  {
	
	Commento commento=new Commento();
	Progetto progetto=new Progetto();
	Utente utente=new Utente();
	ValcomModelDM daoCom=new ValcomModelDM();
	UtenteModelDM daoUtente=new UtenteModelDM();
	ProgettoModelDM daoProg=new ProgettoModelDM();
	Valutazione valutazione =new Valutazione();
	RispostaCommento risposta=new RispostaCommento();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		utente.setUsername("TestUser");
		utente.setPassword("testPass");
		utente.setEmail("TestEmail@Test.it");
		
		progetto.setUsername("TestUser");
		progetto.setId_progetto(99999);
		
		commento.setContenuto("Test commento");
		commento.setUsername("TestUser");
		commento.setId_progetto(99999);
		commento.setId_commento(daoCom.getLastIdCommento()+1);
		
		valutazione.setUsername("TestUser");
		valutazione.setId_progetto(99999);
		valutazione.setId_valutazione(9999);
		valutazione.setVoto(1);
		
		risposta.setContenuto("Test risposta");
		risposta.setId_commento(daoCom.getLastIdCommento());
		risposta.setUsername("TestUser");
		risposta.setId_risposta(daoCom.getLastIdRisposta()+1);
		
		UtenteModelDM.doRegistrazione(utente, 123456);
		daoProg.doUpload(progetto, null, null);
		daoCom.inserisciCommento(commento, 99999);
		daoCom.inserisciValutazione(valutazione, 99999);
		daoCom.inserisciRisposta(risposta, daoCom.getLastIdCommento());
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		UtenteModelDM.deleteUtenteByUsername("TestUser");
		//daoProg.doCancellaProgetto(99999, "TestUser");
	}
	
	
	public void testGetNumeroValutazioniByIdProgetto() throws SQLException
	{
		int numero=daoCom.getNumeroValutazioniByIdProgetto(99999);
		assertEquals(1, numero);
	}
	
	public void testGetNumeroCommentiByIdProgetto() throws SQLException
	{
		int numero=daoCom.getNumeroCommentiByIdProgetto(99999);
		assertEquals(1, numero);
	}
	
	public void testGetNumeroRisposteByIdCommento() throws SQLException
	{
		int numero=daoCom.getNumeroRisposteByIdCommento(daoCom.getLastIdCommento());
		assertEquals(1,numero );
	}
	
	public void testGetMediaValutazioniById() throws SQLException
	{
		Utente u=new Utente();
		u.setUsername("TestUserVal");
		UtenteModelDM.doRegistrazione(u, 123456);
		
		
		valutazione.setUsername("TestUserVal");
		valutazione.setId_progetto(99999);
		valutazione.setId_valutazione(99999);
		valutazione.setVoto(3);
		daoCom.inserisciValutazione(valutazione, 99999);
		
		int media=daoCom.getMediaValutazioniById(99999);
		assertEquals(2, media);
		
		UtenteModelDM.deleteUtenteByUsername("TestUserVal");
	}
	
	public void testGetCommentiByIdProgetto() throws SQLException
	{
		ArrayList<Commento> commenti=ValcomModelDM.getCommentiByIdProgetto(99999);
		assertFalse(commenti.isEmpty());
	}
	
	public void testGetRisposteByIdCommento() throws SQLException
	{
		ArrayList<RispostaCommento> risposte=ValcomModelDM.getRisposteByIdCommento(daoCom.getLastIdCommento());
		assertFalse(risposte.isEmpty());
	}
	
	public void testInserisciCommento() throws SQLException
	{
		Utente u=new Utente();
		u.setUsername("TestUserVal");
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Commento c=new Commento();
		c.setContenuto("test");
		c.setId_progetto(99999);
		c.setUsername("TestUserVal");
		c.setId_commento(daoCom.getLastIdCommento()+1);
		
		daoCom.inserisciCommento(c, 99999);
		
		assertEquals(c.getId_commento(), daoCom.getLastIdCommento());
		
		UtenteModelDM.deleteUtenteByUsername("TestUserVal");
	}
	
	public void testGetLastIdCommento()
	{
		assertEquals(commento.getId_commento(), daoCom.getLastIdCommento());
	}
	
	public void testInserisciRisposta() throws SQLException
	{
		RispostaCommento r=new RispostaCommento();
		
		r.setContenuto("Test risposta");
		r.setId_commento(daoCom.getLastIdCommento());
		r.setUsername("TestUser");
		r.setId_risposta(daoCom.getLastIdRisposta()+1);
		
		daoCom.inserisciRisposta(r, daoCom.getLastIdCommento());
		
		assertEquals(r.getId_risposta(), daoCom.getLastIdRisposta());
	}
	
	public void testGetLastIdRisposta()
	{
		assertEquals(risposta.getId_risposta(), daoCom.getLastIdRisposta());
	}
	
	public void testCancellaRisposta() throws SQLException
	{
		daoCom.cancellaRisposta(daoCom.getLastIdRisposta());
		assertNotEquals(risposta.getId_risposta(), daoCom.getLastIdRisposta());
	}
	
	public void testCancellaCommento() throws SQLException
	{
		daoCom.cancellaCommento(daoCom.getLastIdCommento());
		assertNotEquals(commento.getId_commento(), daoCom.getLastIdCommento());
	}

	public final void testInserisciValutazione() throws SQLException 
	{
	    System.out.println("Test inserisciValutazione");
	    
	    Utente u = new Utente();
	    u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
	    UtenteModelDM.doRegistrazione(u, 123456);
	    
	    String username = u.getUsername();
	    
	    Valutazione val = new Valutazione();
	    val.setId_progetto(progetto.getId_progetto());
	    val.setId_valutazione(999);
	    val.setUsername(username);
	    val.setVoto(5);
	    
	    daoCom.inserisciValutazione(val, progetto.getId_progetto());
	    
	    int result= daoCom.getLastIdValutazione();
	    
	    valutazione.setId_valutazione(result);
	    
	    assertEquals(result, val.getId_valutazione());
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
	   
	    System.out.println("\n");
	  }
	
	public final void testGetLastIdValutazione() throws SQLException 
	{
		System.out.println("Test getLastIdValutazione");
		
		int val = daoCom.getLastIdValutazione();
		
		assertEquals(valutazione.getId_valutazione(), val);
		
		System.out.println("\n");
	}
	
	public final void testIsValutato() throws SQLException 
	{
		System.out.println("Test isValutato");
		
		utente = UtenteModelDM.getUtenteByUsername(utente.getUsername());
		progetto = daoProg.getProgettoById(progetto.getId_progetto());
		
		boolean isValutato = daoCom.isValutato(progetto, utente);
		
		assertEquals(true, isValutato);
		
		System.out.println("\n");	
	}
	
	public final void testAggiornaValutazione() throws SQLException 
	{
		System.out.println("Test aggiornaValutazione");
		
		int id = progetto.getId_progetto();
		
		int media = daoCom.getMediaValutazioniById(id);
		
		valutazione.setVoto(5);
		
		daoCom.aggiornaValutazione(valutazione, id);
		
		assertNotEquals(media, daoCom.getMediaValutazioniById(id));
		
		System.out.println("\n");		
	}
	
	public final void testEliminaValutazione() throws SQLException 
	{
		System.out.println("Test eliminaValutazione");
		
		int id = progetto.getId_progetto();
		
		String username = "TestUser";
		
		daoCom.eliminaValutazione(id, username);
		
		boolean isValutato = daoCom.isValutato(progetto, utente);
		
		assertEquals(false, isValutato);
		
		System.out.println("\n");		
	}
	
	public final void testCreaNotificaCommento() throws SQLException 
	{
		System.out.println("Test creaNotificaCommento");
		
		int idCommento = daoCom.getLastIdCommento();
		
		daoCom.cancellaCommento(idCommento);
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Commento c = new Commento();
		c.setContenuto("Test esempio");
		c.setUsername("TestEsempio");
		c.setId_progetto(99999);
		
		int id = progetto.getId_progetto();
	    
	    daoCom.inserisciCommento(c, id);
	    
	    ArrayList<Notifica> notifiche = null;
	   
	    notifiche = ValcomModelDM.getNotificheByUsername(utente.getUsername());
	    
	    assertEquals(false, notifiche.isEmpty());
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
	    
	    System.out.println("\n");
	}	
	
	public final void testGetLastIdNotifica() throws SQLException 
	{
		System.out.println("Test creaNotificaCommento");
		
		int idNotifica = daoCom.getLastIdNotifica();
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Commento c = new Commento();
		c.setContenuto("Test esempio");
		c.setUsername("TestEsempio");
		c.setId_progetto(99999);
		
		int id = progetto.getId_progetto();
	    
	    daoCom.inserisciCommento(c, id);
				
	    assertEquals(idNotifica+1, daoCom.getLastIdNotifica());
		
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
	    
		System.out.println("\n");
	}
	
	public final void testCreaNotificaRisposta() throws SQLException 
	{
		System.out.println("Test creaNotificaRisposta");
		
		int idCommento = daoCom.getLastIdCommento();
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		RispostaCommento rc = new RispostaCommento();
		rc.setContenuto("Test esempio");
		rc.setUsername("TestEsempio");
		rc.setId_commento(idCommento);
	    
	    daoCom.inserisciRisposta(rc, idCommento);
	    
	    ArrayList<Notifica> notifiche = null;
	   
	    notifiche = ValcomModelDM.getNotificheByUsername(utente.getUsername());
	    
	    assertEquals(false, notifiche.isEmpty());
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
	    
	    System.out.println("\n");
	}	
	
	public final void testGetCommentoById() throws SQLException 
	{
		System.out.println("Test getCommentoById");
		
		int idCommento = daoCom.getLastIdCommento();
		
		daoCom.getCommentoById(idCommento);
		
		assertEquals(commento.getId_commento(), idCommento);
		
		System.out.println("\n");
	}
	
	public final void testCreaNotificaValutazione() throws SQLException
	{
		System.out.println("Test creaNotificaValutazione");
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Valutazione val = new Valutazione();
	    val.setId_progetto(progetto.getId_progetto());
	    val.setId_valutazione(999);
	    val.setUsername("TestEsempio");
	    val.setVoto(4);
	    
	    int id = progetto.getId_progetto();
	    
	    daoCom.inserisciValutazione(val, id);
	    
	    ArrayList<Notifica> notifiche = null;
		   
	    notifiche = ValcomModelDM.getNotificheByUsername(utente.getUsername());
	    
	    assertEquals(false, notifiche.isEmpty());
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
		
		System.out.println("\n");
	}
	
	public final void testGetNotificheByUsername() throws SQLException
	{
		System.out.println("Test getNotificheByUsername");

		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Valutazione val = new Valutazione();
	    val.setId_progetto(progetto.getId_progetto());
	    val.setId_valutazione(999);
	    val.setUsername("TestEsempio");
	    val.setVoto(4);
	    
	    int id = progetto.getId_progetto();
	    
	    daoCom.inserisciValutazione(val, id);
	    
	    ArrayList<Notifica> notifiche = null;
		   
	    notifiche = ValcomModelDM.getNotificheByUsername(utente.getUsername());
	    
	    assertEquals(false, notifiche.isEmpty());
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());
		
		System.out.println("\n");
	}
	
	public final void testGetNumeroNotificheNonLette() throws SQLException
	{
		System.out.println("Test getNumeroNotificheNonLette");
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Valutazione val = new Valutazione();
	    val.setId_progetto(progetto.getId_progetto());
	    val.setId_valutazione(999);
	    val.setUsername("TestEsempio");
	    val.setVoto(4);
	    
	    int id = progetto.getId_progetto();
	    
	    daoCom.inserisciValutazione(val, id);
	    
	    Integer notifiche = daoCom.getNumeroNotificheNonLette(utente.getUsername());
	    
	    assertNotEquals(0, notifiche);
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());

		System.out.println("\n");
	}
	
	public final void testSetClickedNotifica() throws SQLException
	{
		System.out.println("Test setClickedNotifica");
		
		Utente u = new Utente();
		u.setUsername("TestEsempio");
	    u.setPassword("testPass");
		u.setEmail("TestEsempio@Test.it");
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		Valutazione val = new Valutazione();
	    val.setId_progetto(progetto.getId_progetto());
	    val.setId_valutazione(999);
	    val.setUsername("TestEsempio");
	    val.setVoto(4);
	    
	    int id = progetto.getId_progetto();
	    
	    daoCom.inserisciValutazione(val, id);
	    
	    ArrayList<Notifica> notifiche = null;
		   
	    notifiche = ValcomModelDM.getNotificheByUsername(utente.getUsername());
	    
	    daoCom.setClickedNotifica(notifiche.get(0));
	    
	    int numero = daoCom.getNumeroNotificheNonLette(utente.getUsername());
	    
	    assertEquals(0, numero);
	    
	    UtenteModelDM.deleteUtenteByUsername(u.getUsername());

		System.out.println("\n");
	}
}
