package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;

import control.progetto.AggiungiPreferiti;
import control.progetto.CancellaProgetto;
import control.progetto.Modifica;
import control.progetto.RimuoviPreferiti;
import control.progetto.Upload;
import control.utente.Login;

import junit.framework.TestCase;
import manager.progetto.ProgettoModelDM;
import manager.utente.UtenteModelDM;
import model.Progetto;
import model.Utente;

import org.junit.*;

public class ControlProgettoTest extends TestCase{
	Utente utente=null;
	Progetto p=null;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("setUp");
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		utente=new Utente();
		utente.setUsername("username");
		utente.setPassword("25f9e794323b453885f5181f1b624d0b");
		utente.setEmail("test@email.it");
		UtenteModelDM.doRegistrazione(utente, 0);
		UtenteModelDM.setConfermato(utente);
		Login login=new Login();
		request.addParameter("username", "username");
		request.addParameter("password", "123456789");
		login.service(request, response);
		
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
		System.out.println("\n");
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown");
		UtenteModelDM.deleteUtenteByUsername(utente.getUsername());
		ProgettoModelDM model=new ProgettoModelDM();
		model.doCancellaProgetto(99999, "username");
		System.out.println("\n");
	}
	
	public void testUpload() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Upload");
		ProgettoModelDM model = new ProgettoModelDM();
		int before=ProgettoModelDM.getByUsername(utente.getUsername()).size();
		request.addParameter("titolo","titolo");
		request.addParameter("categoria","Art");
		request.addParameter("consigli","consigli");
		request.addParameter("descrizione","descrizione");
		MockPart part=new MockPart("progetto", null);
		MockPart part2=new MockPart("immagine", null);
		request.addPart(part);
		request.addPart(part2);
		Upload upload=new Upload();
		upload.service(request, response);
		int after=ProgettoModelDM.getByUsername(utente.getUsername()).size();
		assertEquals(before+1, after);
		model.doCancellaProgetto(model.getLastId(), utente.getUsername());
		System.out.println("\n");
	}
	
	public void testCancella() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Cancella");
		ProgettoModelDM model = new ProgettoModelDM();
		
		Progetto progetto=new Progetto();
		progetto.setUsername(utente.getUsername());
		progetto.setTitolo("titolo");
		progetto.setCategoria("Art");
		progetto.setConsigli("consigli");
		progetto.setDescrizione("descrizione");
		progetto.setVersione(1);
		progetto.setId_progetto(999999);
		model.doUpload(progetto, InputStream.nullInputStream(), InputStream.nullInputStream());
		int before=ProgettoModelDM.getByUsername(utente.getUsername()).size();
		CancellaProgetto cancella = new CancellaProgetto();
		request.addParameter("id", Integer.toString(progetto.getId_progetto()));
		cancella.service(request, response);
		int after=ProgettoModelDM.getByUsername(utente.getUsername()).size();
		assertEquals(before, after+1);
		System.out.println("\n");
	}
	
	public void testAggiungiPreferiti() throws ServletException, IOException, SQLException
	{
		System.out.println("Test AggiungiPreferiti");
		ProgettoModelDM model = new ProgettoModelDM();
		
		int before=ProgettoModelDM.getPreferitiByUsername(utente.getUsername()).size();
		AggiungiPreferiti preferiti= new AggiungiPreferiti();
		request.addParameter("id", Integer.toString(p.getId_progetto()));
		preferiti.service(request, response);
		int after=ProgettoModelDM.getPreferitiByUsername(utente.getUsername()).size();
		assertEquals(before+1, after);
		model.removeFromPreferiti(p, utente);
		System.out.println("\n");
	}
	
	public void testRimuoviPreferiti() throws ServletException, IOException, SQLException
	{
		System.out.println("Test RimuoviPreferiti");
		ProgettoModelDM model = new ProgettoModelDM();
		
		model.addToPreferiti(p, utente);
		int before=ProgettoModelDM.getPreferitiByUsername(utente.getUsername()).size();
		RimuoviPreferiti preferiti= new RimuoviPreferiti();
		request.addParameter("id", Integer.toString(p.getId_progetto()));
		preferiti.service(request, response);
		int after=ProgettoModelDM.getPreferitiByUsername(utente.getUsername()).size();
		assertEquals(before, after+1);
		System.out.println("\n");
	}
	
	public void testModifica() throws ServletException, IOException, SQLException
	{
		System.out.println("Test Modifica");
		ProgettoModelDM model = new ProgettoModelDM();
		
		int before=model.getProgettoById(p.getId_progetto()).getVersione();
		Modifica modifica=new Modifica();
		request.addParameter("id", Integer.toString(p.getId_progetto()));
		request.addParameter("titolo","titolo2");
		request.addParameter("categoria","Art");
		request.addParameter("consigli","consigli");
		request.addParameter("descrizione","descrizione");
		MockPart part=new MockPart("progetto", null);
		MockPart part2=new MockPart("immagine", null);
		request.addPart(part);
		request.addPart(part2);
		modifica.service(request, response);
		
		int after=model.getProgettoById(p.getId_progetto()).getVersione();
		assertEquals(before, after-1);
		System.out.println("\n");
	}
}
