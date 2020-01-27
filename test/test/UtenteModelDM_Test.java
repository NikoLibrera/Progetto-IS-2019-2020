package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import manager.utente.UtenteModelDM;
import model.Utente;

public class UtenteModelDM_Test 
{
	Connection connection = null;
	
	@Test
	public final void testDoRegistrazione() throws SQLException 
	{
	    System.out.println("Test DoRegistrazione");

	    Utente utente = new Utente();
	    utente.setUsername("Gennaro97");
	    utente.setCognome("Confuorto");
	    utente.setNome("Gennaro");
	    utente.setData_nascita("1997-11-07");
	    utente.setIsAdmin(0);
	    utente.setPassword("123456789");
	    utente.setEmail("confuortogennaro@gmail.com");
	    utente.setNazionalita("Italy");
	    utente.setPassword("0123456789");
	    utente.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utente, codice);

	    Utente result = UtenteModelDM.getUtenteByUsername("Gennaro97");
	    assertEquals(utente.getUsername(), result.getUsername());
	    
	    System.out.println("\n");
	  }
	
	@Test
	public final void testDoLogin() throws SQLException 
	{
	    System.out.println("Test DoLogin");
	
	    Utente utente = new Utente();
	    utente.setUsername("Gennaro97");
	    utente.setCognome("Confuorto");
	    utente.setNome("Gennaro");
	    utente.setData_nascita("1997-11-07");
	    utente.setIsAdmin(0);
	    utente.setPassword("123456789");
	    utente.setEmail("confuortogennaro@gmail.com");
	    utente.setNazionalita("Italy");
	    utente.setPassword("0123456789");
	    utente.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utente, codice);
	
	    String email = "confuortogennaro@gmail.com";
	    
	    String username = utente.getUsername();
	    String password = utente.getPassword();
	    
	    UtenteModelDM model = new UtenteModelDM();
	    
	    Utente result = model.doLogin(username, password);

	    assertEquals(result.getUsername(), username);

	    System.out.println("\n");
	  }
	
	/*
	@Test
	public final void testDoModificaPassword() throws SQLException
	{
		System.out.println("TestDoPasswordDimenticata");
	    
	    Utente utentedb = new Utente();
	    utentedb.setUsername("Gennaro97");
	    utentedb.setCognome("Confuorto");
	    utentedb.setNome("Gennaro");
	    utentedb.setData_nascita("1997-11-07");
	    utentedb.setIsAdmin(0);
	    utentedb.setPassword("123456789");
	    utentedb.setEmail("confuortogennaro@gmail.com");
	    utentedb.setNazionalita("Italy");
	    utentedb.setPassword("0123456789");
	    utentedb.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utentedb, codice);
	    
	    final String nuova_password = "gennarino";

	    utentedb.setPassword(nuova_password);

	    UtenteModelDM model = new UtenteModelDM();
	    
	    model.doModificaPassword(utentedb, nuova_password);

	    Utente result = model.getUtenteByUsername("Gennaro97");

	    assertEquals(nuova_password, result.getPassword());
	    
	    System.out.println("\n");
	}

	@Test
	public final void testGetUtenteByUsername() throws SQLException 
	{
	    System.out.println("TestGetUtenteByUsername");

	    Utente utentedb = new Utente();
	    utentedb.setUsername("Gennaro97");
	    utentedb.setCognome("Confuorto");
	    utentedb.setNome("Gennaro");
	    utentedb.setData_nascita("1997-11-07");
	    utentedb.setIsAdmin(0);
	    utentedb.setPassword("123456789");
	    utentedb.setEmail("confuortogennaro@gmail.com");
	    utentedb.setNazionalita("Italy");
	    utentedb.setPassword("0123456789");
	    utentedb.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utentedb, codice);
	    
	    UtenteModelDM model = new UtenteModelDM();

	    Utente result = model.getUtenteByUsername("Gennaro97");

	    assertEquals(utentedb.getUsername(), result.getUsername());
	    
	    System.out.println("\n");
	  }

	@Test
	public final void testVerificaCodice() throws SQLException 
	{
	    System.out.println("TestVerificaCodice");
	    Utente utente = new Utente();

	    Utente utentedb = new Utente();
	    utentedb.setUsername("Gennaro97");
	    utentedb.setCognome("Confuorto");
	    utentedb.setNome("Gennaro");
	    utentedb.setData_nascita("1997-11-07");
	    utentedb.setIsAdmin(0);
	    utentedb.setPassword("123456789");
	    utentedb.setEmail("confuortogennaro@gmail.com");
	    utentedb.setNazionalita("Italy");
	    utentedb.setPassword("123456789");
	    utentedb.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utentedb, codice);
	    
	    UtenteModelDM model = new UtenteModelDM();
	    utente = model.getUtenteByUsername("Gennaro97");

	    int result = model.verificaCodice(utente.getUsername());
	    assertEquals(codice, result);
	    
	    System.out.println("\n");
	  }

	@Test
	public final void testDoPasswordDimenticata() throws SQLException 
	{
	    System.out.println("TestDoPasswordDimenticata");

	    Utente utentedb = new Utente();
	    utentedb.setUsername("Gennaro97");
	    utentedb.setCognome("Confuorto");
	    utentedb.setNome("Gennaro");
	    utentedb.setData_nascita("1997-11-07");
	    utentedb.setIsAdmin(0);
	    utentedb.setPassword("123456789");
	    utentedb.setEmail("confuortogennaro@gmail.com");
	    utentedb.setNazionalita("Italy");
	    utentedb.setPassword("123456789");
	    utentedb.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utentedb, codice);
	    
	    UtenteModelDM model = new UtenteModelDM();
	    
	    Utente result = model.doPasswordDimenticata(utentedb, "123456");
	    
	    assertTrue(true);

	    System.out.println("\n");
	  }
	  */
}
