package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;
import manager.progetto.ProgettoModelDM;
import manager.utente.UtenteModelDM;
import manager.valcom.ValcomModelDM;
import model.Commento;
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
}
