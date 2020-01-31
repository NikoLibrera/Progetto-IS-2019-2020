package control;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import control.utente.Login;

import junit.framework.TestCase;
import model.Utente;

import org.junit.*;

public class ControlUtenteTest extends TestCase{
	
	
	Login login;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		login=new Login();
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLogin() throws ServletException, IOException
	{
		request.addParameter("username", "mniello");
		request.addParameter("password", "123456789");
		login.service(request, response);
		Utente u=(Utente) request.getAttribute("utente");
		assertEquals(u.getUsername(), "mniello");
	}

}
