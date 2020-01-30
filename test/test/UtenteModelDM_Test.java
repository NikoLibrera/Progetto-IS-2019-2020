package test;

import java.sql.SQLException;
import junit.framework.TestCase;
import manager.utente.UtenteModelDM;
import model.Utente;

public class UtenteModelDM_Test extends TestCase
{ 
	Utente utente = new Utente();
	
	@Override
	protected void setUp() throws Exception 
	{
	    utente.setUsername("Pako98");
	    utente.setCognome("Sorrentino");
	    utente.setNome("Pasquale");
	    utente.setData_nascita("1997-11-07");
	    utente.setIsAdmin(0);
	    utente.setPassword("123456789");
	    utente.setEmail("pasqualesorrentino@gmail.com");
	    utente.setNazionalita("Italy");
	    utente.setConfermato(0);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utente, codice);
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		UtenteModelDM.deleteUtenteByUsername(utente.getUsername());
	}
	
	public final void testDoRegistrazione() throws SQLException 
	{
	    System.out.println("Test DoRegistrazione");

	    Utente utente = new Utente();
	    utente.setUsername("A.Papini");
	    utente.setCognome("Papini");
	    utente.setNome("Antonio");
	    utente.setData_nascita("1996-09-10");
	    utente.setIsAdmin(0);
	    utente.setPassword("123456789");
	    utente.setEmail("antoniopapini@gmail.com");
	    utente.setNazionalita("Italy");
	    utente.setConfermato(1);
	    int codice = 8912;
	    UtenteModelDM.doRegistrazione(utente, codice);

	    Utente result = UtenteModelDM.getUtenteByUsername("A.Papini");
	    assertEquals(utente.getUsername(), result.getUsername());
	    
	    UtenteModelDM.deleteUtenteByUsername(utente.getUsername());
	    System.out.println("\n");
	  }
	
	public final void testDoLogin() throws SQLException 
	{
	    System.out.println("Test DoLogin");
	    
	    String usernameDaTrovare = "Pako98";
	    
	    String username = utente.getUsername();
	    String password = utente.getPassword();
	    
	    UtenteModelDM model = new UtenteModelDM();
	    
	    UtenteModelDM.setConfermato(utente);
	    Utente result = model.doLogin(username, password);

	    assertEquals(result.getUsername(), usernameDaTrovare);

	    System.out.println("\n");
	  }
	
	public final void testDoModificaPassword() throws SQLException 
	{
	    System.out.println("Test DoModificaPassword");
	    
	    String nuovaPassword = "Pako98";
	    
	    UtenteModelDM model = new UtenteModelDM();
	    
	    Utente result = model.doModificaPassword(utente, nuovaPassword);

	    assertEquals(result.getPassword(), nuovaPassword);

	    System.out.println("\n");
	  }
	
	public final void testSetConfermato() throws SQLException 
	{
	    System.out.println("Test setConfermato");
	    
	    int confermato = 1;
	    
	    UtenteModelDM.setConfermato(utente);
	    
	    utente = UtenteModelDM.getUtenteByUsername(utente.getUsername());

	    assertEquals(utente.getConfermato(),confermato);

	    System.out.println("\n");
	  }
	
	public final void testGetUtenteByUsername() throws SQLException
	{
		System.out.println("Test getUtenteByUsername");
		
		String usernameDaTrovare = "Pako98";
		
		utente = UtenteModelDM.getUtenteByUsername(utente.getUsername());
		
		assertEquals(utente.getUsername(), usernameDaTrovare);
		
		System.out.println("\n");
	}
	
	public final void testVerificaCodice() throws SQLException 
	{
		System.out.println("Test verificaCodice");
		
		int codiceDaTrovare = 8912;
		
		UtenteModelDM.verificaCodice(utente.getUsername());
		
		utente = UtenteModelDM.getUtenteByUsername(utente.getUsername());
		assertEquals(utente.getCodice(), codiceDaTrovare);
		
		System.out.println("\n");
	}
	
	public final void testDeleteUtenteByUsername() throws SQLException
	{
		System.out.println("Test deleteUtenteByUsername");
		
		String usernameDaTrovare = "Pako98";
		
		UtenteModelDM.deleteUtenteByUsername(utente.getUsername());
		
		assertEquals(utente.getUsername(), usernameDaTrovare);
		
		System.out.println("\n");
	}
	
	public final void testDoPasswordDimenticata() throws SQLException 
	{
	    System.out.println("Test doPasswordDimenticata");
	    
	    String passwordDaInserire = "987654321";
	    
	    UtenteModelDM model = new UtenteModelDM();
	    
	    model.doPasswordDimenticata(utente, passwordDaInserire);
	    
	    utente = UtenteModelDM.getUtenteByUsername(utente.getUsername());
	    
	    assertEquals(utente.getPassword(), passwordDaInserire);

	    System.out.println("\n");
	  }
}