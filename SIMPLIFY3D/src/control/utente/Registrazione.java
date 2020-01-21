package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.utente.UtenteModelDM;
import model.Utente;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public Registrazione() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String email = null;
		
		Utente u = new Utente();
        u.setUsername(request.getParameter("username"));
        u.setCognome(request.getParameter("cognome"));
        u.setNome(request.getParameter("nome"));
        u.setData_nascita(request.getParameter("data_nascita"));
        u.setPassword(request.getParameter("password"));
        String confermapassword=request.getParameter("confermapassword");
        u.setEmail(request.getParameter("email"));
        u.setNazionalita(request.getParameter("nazionalita"));
        
        if(!confermapassword.equals(u.getPassword())) {
			System.out.println("La due passord non coincidono");
			out.println("<script>");
			out.println("window.history.back()");
			out.println("alert('La due passord non coincidono')");
			out.println("</script>");
			return;
    	}
        
        //Codifica in Hash Password
        String passwordToHash = request.getParameter("password");
        String generatedPassword = null;

        try 
        {
          // Create MessageDigest instance for MD5
          MessageDigest md = MessageDigest.getInstance("MD5");
          // Add password bytes to digest
          md.update(passwordToHash.getBytes());
          // Get the hash's bytes
          byte[] bytes = md.digest();
          // This bytes[] has bytes in decimal format;
          // Convert it to hexadecimal format
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) 
          {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }
          // Get complete hashed password in hex format
          generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
          e.printStackTrace();
        }
        
        u.setPassword(generatedPassword);
        u.setConfermato(0);

        Random random = new Random();

        int codice = random.nextInt(50000);
        System.out.println("Codice: " + codice);
        
        try 
        {
        	boolean f=UtenteModelDM.doRegistrazione(u, codice);
            if(f == false) 
            {
            	out.println("<script>");
                out.println("window.open('http://localhost:8080/Simplify3D/RegistrationPage.jsp','_self')");
                out.println("alert('Email già presente nel DB')");
                out.println("</script>");
            }
            else 
            {
            	String username = request.getParameter("username");	
            	String cognome = request.getParameter("cognome");
                String nome = request.getParameter("nome");		
                String data_nascita = request.getParameter("datanascita");
                String password = request.getParameter("password");
                email = request.getParameter("email");
                String nazionalita = request.getParameter("nazionalita");

                 try
                 {
                   final String email_gmail = "simplify3dmail@gmail.com";
                   final String password_gmail = "esame_is";
                   
                   Properties props = new Properties();
                   props.put("mail.smtp.auth", "true");
                   props.put("mail.smtp.starttls.enable", "true");
                   props.put("mail.smtp.host", "smtp.gmail.com");
                   props.put("mail.smtp.port", "587");
                   Session session =
                       Session.getInstance(
                           props,
                           new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() {
                               return new PasswordAuthentication(email_gmail, password_gmail);
                             }
                           });

                   Message message = new MimeMessage(session);
                   message.setFrom(new InternetAddress(username));
                   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                   message.setSubject("Conferma Registrazione Simplify3D");
                   message.setText(
                       "Ciao "
                           + nome
                           + ", \n"
                           + "Per confermare la registrazione vai al seguente link: http://localhost:8080/Simplify3D/ConfermaRegistrazione?codice="
                           + codice
                           + "&username="
                           + username
                           + " \n Grazie!");

                   Transport.send(message);
                   out.println("<script>");
                   out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
                   out.println("alert('Abbiamo inviato una mail di conferma al seguente indirizzo: "+email+"')");
                   out.println("</script>");     
                   out.close();
                 }
                 catch (Exception e) 
                 {
                     e.printStackTrace();
                 }
              }
        } 
        catch (SQLException e1) 
        {
        	out.println("<script>");
            out.println("window.open('http://localhost:8080/Simplify3D/RegistrationPage.jsp','_self')");
            out.println("alert('Registrazione Negata')");
            out.println("</script>");
            e1.printStackTrace();
        } 
	}
}
