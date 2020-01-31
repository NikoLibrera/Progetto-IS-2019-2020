package control;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.utente.ConfermaRegistrazione;
import control.utente.Login;
import control.utente.Logout;
import control.utente.ModificaPassword;
import control.utente.PasswordDimenticata;
import control.utente.Registrazione;
import junit.framework.TestCase;
import manager.utente.UtenteModelDM;
import model.CryptWithMD5;
import model.Utente;


import org.junit.*;

public class ControlUtenteTest extends TestCase{
	
	
	
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	Utente utente;
	String generatedPassword1 = CryptWithMD5.cryptWithMD5("testPass");
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		
		utente=new Utente();
		utente.setUsername("TestUtente");
		utente.setPassword(generatedPassword1);
		utente.setConfermato(1);
		utente.setEmail("test@test.it");
		UtenteModelDM.doRegistrazione(utente, 9999);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		UtenteModelDM.deleteUtenteByUsername("TestUtente");
	}
	
	public void testLogin() throws ServletException, IOException
	{
		Login login =new Login();
		request.addParameter("username", "TestUtente");
		request.addParameter("password", "testPass");
		login.service(request, response);
		
		Utente u=(Utente) request.getAttribute("utente");
		//response.isCommitted();
		
		assertEquals(u.getUsername(), "TestUtente");
		
	}
	
	public void testConfermaRegistrazione() throws SQLException, ServletException, IOException
	{
		Utente u=new Utente();
		u.setUsername("carminuccio");
		u.setPassword(generatedPassword1);
		UtenteModelDM.doRegistrazione(u, 99);
		request.addParameter("username",u.getUsername());
		Integer numero=99;
		request.addParameter("codice",numero.toString());
		
		
		ConfermaRegistrazione c=new ConfermaRegistrazione();
		c.service(request, response);
		int codice=((Utente)UtenteModelDM.getUtenteByUsername("carminuccio")).getConfermato();
		assertEquals(1, codice);
		UtenteModelDM.deleteUtenteByUsername("carminuccio");
		
	}
	
	public void testLogout() throws ServletException, IOException
	{
		Login l=new Login();
		request.addParameter("username", utente.getUsername());
		request.addParameter("password", "testPass");
		l.service(request, response);
		Logout logout=new Logout();
		logout.service(request, response);
		
		Utente u=(Utente) request.getSession().getAttribute("utente");
		assertNull(u);
	}
	
	public void testModificaPassword() throws ServletException, IOException, SQLException
	{
		String generatedPass = CryptWithMD5.cryptWithMD5("nuovoTest");
		ModificaPassword modifica=new ModificaPassword();
		
		Login l=new Login();
		request.addParameter("username", utente.getUsername());
		request.addParameter("password", "testPass");
		l.service(request, response);
		
		request.addParameter("password", "testPass");
		request.addParameter("nuovapassword", "nuovoTest");
		request.addParameter("ripetinuovapassword", "nuovoTest");
		
		String vecchia=utente.getPassword();
		System.out.println("vecchia----"+utente.getPassword());
		modifica.service(request, response);
		Utente u=UtenteModelDM.getUtenteByUsername(utente.getUsername());
		
		System.out.println("nuova---"+u.getPassword());
		String nuova=u.getPassword();
		
		assertNotEquals(vecchia, nuova);
	}
	
	public void testPasswordDimenticata() throws ServletException, IOException, SQLException
	{
		request.addParameter("username", utente.getUsername());
		
		request.addParameter("email",utente.getEmail());
		
		PasswordDimenticata pd=new PasswordDimenticata();
		
		String vecchia=utente.getPassword();
		System.out.println("vecchia ps "+vecchia);
		
		pd.service(request, response);
		
		Utente u=UtenteModelDM.getUtenteByUsername(utente.getUsername());
		
		String nuova=u.getPassword();
		System.out.println("nuova "+nuova);
		
		assertNotEquals(vecchia,nuova);
		
	}
	
	public void testRegistrazione() throws ServletException, IOException, SQLException
	{
		Registrazione reg=new Registrazione();
		request.addParameter("username", "utenteTest");
		request.addParameter("cognome", "cognomeTest");
		request.addParameter("nome", "nomeTest");
		request.addParameter("datanascita", "1998-07-21");
		request.addParameter("password", "TestPass");
		request.addParameter("confermapassword", "TestPass");
		request.addParameter("email", "utene@test.it");
		request.addParameter("nazionalita", "italia");
	
		reg.service(request, response);
		
		Utente u=UtenteModelDM.getUtenteByUsername("utenteTest");
		
		assertEquals("utenteTest", u.getUsername());
		assertEquals(0, u.getConfermato());
		
		UtenteModelDM.deleteUtenteByUsername("utenteTest");
	}

}
