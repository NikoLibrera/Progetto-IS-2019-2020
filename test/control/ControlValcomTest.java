package control;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.utente.Login;
import control.valcom.EliminaCommento;
import control.valcom.EliminaRisposta;
import control.valcom.EliminaValutazione;
import control.valcom.InserisciCommento;
import control.valcom.InserisciRisposta;
import control.valcom.Valuta;
import junit.framework.TestCase;
import manager.progetto.ProgettoModelDM;
import manager.utente.UtenteModelDM;
import manager.valcom.ValcomModelDM;
import model.Commento;
import model.Progetto;
import model.RispostaCommento;
import model.Utente;
import model.Valutazione;

import org.junit.*;

public class ControlValcomTest extends TestCase
{
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	
	Utente u = new Utente();
	Progetto p = new Progetto();
	
	ProgettoModelDM  daoProg = new ProgettoModelDM();
	ValcomModelDM daoValCom = new ValcomModelDM();
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		
		u.setUsername("TestUser");
		u.setPassword("b62a565853f37fb1ec1efc287bfcebf9");
		u.setEmail("TestEmail@Test.it");
		u.setConfermato(1);
		
		UtenteModelDM.doRegistrazione(u, 123456);
		
		p.setUsername("TestUser");
		p.setId_progetto(99999);
		
		daoProg.doUpload(p, null, null);
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
		UtenteModelDM.deleteUtenteByUsername("TestUser");
	}
	
	public void testValuta() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet Valuta");
		
		int numeroVal = daoValCom.getNumeroValutazioniByIdProgetto(p.getId_progetto());		
		
		Utente utente = new Utente();
		utente.setUsername("TestEsempio");
		utente.setPassword("25f9e794323b453885f5181f1b624d0b");
		utente.setEmail("TestEsempio@Test.it");
		utente.setConfermato(1);
		UtenteModelDM.doRegistrazione(utente, 123456);
		
		Login login = new Login();
		request.addParameter("username", "TestEsempio");
		request.addParameter("password", "123456789");
		login.service(request, response);
		
		Valutazione v = new Valutazione();
		v.setId_progetto(p.getId_progetto());
		v.setId_valutazione(9999);
		v.setVoto(5);		
		
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		String voto =  String.valueOf(v.getVoto()).toString();
		
		Valuta valuta = new Valuta();
		request.addParameter("id_proge",idProgetto);
		request.addParameter("rating", voto);
		valuta.service(request, response);
		
		int numeroVal2 = daoValCom.getNumeroValutazioniByIdProgetto(99999);
		
		UtenteModelDM.deleteUtenteByUsername("TestEsempio");
		
		assertEquals(numeroVal+1, numeroVal2);
		
		System.out.println("\n");
	}
	
	public void testEliminaValutazione() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet EliminaValutazione");	
		
		Utente utente = new Utente();
		utente.setUsername("TestEsempio");
		utente.setPassword("25f9e794323b453885f5181f1b624d0b");
		utente.setEmail("TestEsempio@Test.it");
		utente.setConfermato(1);
		UtenteModelDM.doRegistrazione(utente, 123456);
		
		Login login = new Login();
		request.addParameter("username", "TestEsempio");
		request.addParameter("password", "123456789");
		login.service(request, response);
		
		Valutazione v = new Valutazione();
		v.setId_progetto(p.getId_progetto());
		v.setId_valutazione(9999);
		v.setVoto(5);		
		
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		String voto =  String.valueOf(v.getVoto()).toString();
		
		Valuta valuta = new Valuta();
		request.addParameter("id_proge",idProgetto);
		request.addParameter("rating", voto);
		valuta.service(request, response);
		
		int numeroVal = daoValCom.getNumeroValutazioniByIdProgetto(p.getId_progetto());	
		
		EliminaValutazione elimina = new EliminaValutazione();
		request.addParameter("id_proge", idProgetto);
		elimina.service(request, response);
		
		int numeroVal2 = daoValCom.getNumeroValutazioniByIdProgetto(99999);
		
		UtenteModelDM.deleteUtenteByUsername("TestEsempio");
		
		assertEquals(numeroVal, numeroVal2+1);
		
		System.out.println("\n");
	}
	
	public void testInserisciCommento() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet InserisciCommento");
		
		int numeroCom = daoValCom.getNumeroCommentiByIdProgetto(p.getId_progetto());	
	
		Login login = new Login();
		request.addParameter("username", "TestUser");
		request.addParameter("password", "testPass");
		login.service(request, response);
		
		Commento c = new Commento();
		c.setContenuto("bla bla bla");
		c.setId_progetto(p.getId_progetto());	
		
		String commento =  c.getContenuto();
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		
		InserisciCommento insert = new InserisciCommento();//servlet inserisci commento
		request.addParameter("inserisciCommento",commento);
		request.addParameter("id", idProgetto);
		insert.service(request, response);
		
		int numeroCom2 = daoValCom.getNumeroCommentiByIdProgetto(p.getId_progetto());
		
		assertEquals(numeroCom+1, numeroCom2);
		
		System.out.println("\n");
	}
	
	public void testInserisciRisposta() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet InserisciRisposta");
	
		Login login = new Login();
		request.addParameter("username", "TestUser");
		request.addParameter("password", "testPass");
		login.service(request, response);
		
		Commento c = new Commento();
		c.setContenuto("bla bla bla");
		c.setId_progetto(p.getId_progetto());	
		
		String commento =  c.getContenuto();
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		
		InserisciCommento insert = new InserisciCommento();//servlet inserisci commento
		request.addParameter("inserisciCommento",commento);
		request.addParameter("id", idProgetto);
		insert.service(request, response);
		
		int numeroRisp = daoValCom.getNumeroRisposteByIdCommento(daoValCom.getLastIdCommento());
		
		RispostaCommento rc = new RispostaCommento();
		rc.setContenuto("bla bla bla");
		rc.setId_commento(daoValCom.getLastIdCommento());
		
		String rispostaCommento =  rc.getContenuto();
		String idCommento =  String.valueOf(rc.getId_commento());
		
		InserisciRisposta insertRisposta = new InserisciRisposta();//servlet inserisci risposta
		request.addParameter("rispostaCommento",rispostaCommento);
		request.addParameter("id", idProgetto);
		request.addParameter("idCommento", idCommento);
		insertRisposta.service(request, response);
		
		int numeroRisp2 = daoValCom.getNumeroRisposteByIdCommento(daoValCom.getLastIdCommento());
		
		assertEquals(numeroRisp+1, numeroRisp2);
		
		System.out.println("\n");
	}
	
	public void testEliminaCommento() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet EliminaCommento");
		
		Login login = new Login();
		request.addParameter("username", "TestUser");
		request.addParameter("password", "testPass");
		login.service(request, response);
		
		Commento c = new Commento();
		c.setContenuto("bla bla bla");
		c.setId_progetto(p.getId_progetto());	
		
		String commento =  c.getContenuto();
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		
		InserisciCommento insert = new InserisciCommento();//servlet inserisci commento
		request.addParameter("inserisciCommento",commento);
		request.addParameter("id", idProgetto);
		insert.service(request, response);
		
		int numeroCommenti = daoValCom.getNumeroCommentiByIdProgetto(p.getId_progetto());
		
		String idCommento =  String.valueOf(daoValCom.getLastIdCommento());
		
		EliminaCommento elimina = new EliminaCommento();
		request.addParameter("id", idProgetto);
		request.addParameter("idcommento", idCommento);
		elimina.service(request, response);
		
		int numeroCommenti2 = daoValCom.getNumeroCommentiByIdProgetto(p.getId_progetto());
		
		assertEquals(numeroCommenti, numeroCommenti2+1);
		
		System.out.println("\n");
	}

	public void testEliminaRisposta() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Servlet EliminaRisposta");
		
		Login login = new Login();
		request.addParameter("username", "TestUser");
		request.addParameter("password", "testPass");
		login.service(request, response);
		
		Commento c = new Commento();
		c.setContenuto("bla bla bla");
		c.setId_progetto(p.getId_progetto());	
		
		String commento =  c.getContenuto();
		String idProgetto =  String.valueOf(p.getId_progetto()).toString();
		
		InserisciCommento insert = new InserisciCommento();//servlet inserisci commento
		request.addParameter("inserisciCommento",commento);
		request.addParameter("id", idProgetto);
		insert.service(request, response);
		
		RispostaCommento rc = new RispostaCommento();
		rc.setContenuto("bla bla bla");
		rc.setId_commento(daoValCom.getLastIdCommento());
		
		String rispostaCommento =  rc.getContenuto();
		String idCommento =  String.valueOf(rc.getId_commento());
		
		InserisciRisposta insertRisposta = new InserisciRisposta();//servlet inserisci risposta
		request.addParameter("rispostaCommento",rispostaCommento);
		request.addParameter("id", idProgetto);
		request.addParameter("idCommento", idCommento);
		insertRisposta.service(request, response);

		int numeroRisp = daoValCom.getNumeroRisposteByIdCommento(daoValCom.getLastIdCommento());
		
		String idRisposta = String.valueOf(daoValCom.getLastIdRisposta());
		EliminaRisposta elimina = new EliminaRisposta();
		request.addParameter("id", idProgetto);
		request.addParameter("idrisposta", idRisposta);
		elimina.service(request, response);
		
		int numeroRisp2 = daoValCom.getNumeroRisposteByIdCommento(daoValCom.getLastIdCommento());
		
		assertEquals(numeroRisp, numeroRisp2+1);
		
		System.out.println("\n");
	}
}