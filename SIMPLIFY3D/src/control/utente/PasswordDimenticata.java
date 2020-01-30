package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.utente.UtenteModelDM;
import model.CryptWithMD5;
import model.Utente;

@WebServlet("/PasswordDimenticata")
public class PasswordDimenticata extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public PasswordDimenticata() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utente user = new Utente();
		UtenteModelDM model = new UtenteModelDM();
		
		PrintWriter out = response.getWriter();
		
		Utente utente = new Utente();
		
		String username = request.getParameter("username");		
		String email = request.getParameter("email");
		
		user.setEmail(email);
	    user.setUsername(username);
	    
	    try 
	    {
	    	//Generazione password casuale
	    	String password = "";
	    	String alfabeto = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    	Random rnd = new Random(System.currentTimeMillis());
	    	int lunghezza = 8;
        
	    	StringBuilder sb = new StringBuilder(lunghezza);

	    	for (int i = 0; i < lunghezza; i++)
	    		sb.append(alfabeto.charAt(rnd.nextInt(alfabeto.length())));
        
	    	password = sb.toString();
        
	    	final String email_platform = "Simplify3Dmail@gmail.com";
	    	final String pass_word = "esame_is";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
        
	         Session session =
		            Session.getInstance(
		                props,
		                new javax.mail.Authenticator() 
		                {
		                  protected PasswordAuthentication getPasswordAuthentication() 
		                  {
		                    return new PasswordAuthentication(email_platform, pass_word);
		                  }
		                });
		
		        Message message = new MimeMessage(session);
		        //Message messageBodyPort = new MimeBodyPart();
		        message.setFrom(new InternetAddress(username));
		        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		        message.setSubject("Reset Password Simplify3D");
		        message.setText(
		            "Ciao " + username + ", \n" + "La tua nuova password ши: " + password + "\n Grazie!");
        
	        String generatedPassword1 = CryptWithMD5.cryptWithMD5(password);

	        utente = model.doPasswordDimenticata(user,generatedPassword1);
	        
	        Transport.send(message);
        
	        out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/NotificaPasswordDimenticata.jsp','_self')");
			out.println("</script>");
			return;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
 
		if(utente != null)
		{
			request.getSession().setAttribute("utente", utente);
			request.setAttribute("utente", utente);
			
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
			out.println("</script>");
			return;
		}
		else
		{
			System.out.println("Username o email errata");
			out.println("<script>");
			out.println("window.history.back()");
			out.println("alert('Username o email errata')");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}