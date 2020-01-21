package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CryptWithMD5;
import model.Utente;
import manager.utente.*;

@WebServlet(name = "UtenteControl", urlPatterns = {"/UtenteControl"})
public class UtenteControl extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
	
    static UtenteModel<Utente> model = new UtenteModelDM();
        
    public UtenteControl()
    {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        
        String action = request.getParameter("action");
		try 
		{ 
			if(action != null) 
			{
	            if(action.equalsIgnoreCase("registrati"))
	            {
	            	String username = request.getParameter("username");	
	            	String cognome = request.getParameter("cognome");
	                String nome = request.getParameter("nome");		
	                String data_nascita = request.getParameter("datanascita");
	                String password = request.getParameter("password");
	                String confermapassword = request.getParameter("confermapassword");
	                String email = request.getParameter("email");
	                String nazionalita = request.getParameter("nazionalita");
	                if(!confermapassword.equals(password)) {
						System.out.println("La due passord non coincidono");
						out.println("<script>");
						out.println("window.history.back()");
						out.println("alert('La due passord non coincidono')");
						out.println("</script>");
						return;
                	}
	                
	                boolean emailGiaEsistente;
	                Utente u = new Utente();
	                u.setUsername(username);
	                u.setCognome(cognome);
	                u.setNome(nome);
	                u.setData_nascita(data_nascita);
	                u.setPassword(password);
	                u.setEmail(email);
	                u.setNazionalita(nazionalita);
	                
	                emailGiaEsistente = model.doRegistrazione(u);
	                if(emailGiaEsistente == false)
	                {
	                        out.println("<script>");
	                        out.println("alert('Registrazione effettuata con successo')");
	                        out.println("</script>");
	                }
	                else
	                {
	                        out.println("<script>");
	                        out.println("window.open('http://localhost:8080/Simplify3D/RegistrationPage.jsp','_self')");
	                        out.println("alert('Esiste già un'account con lo stesso indirizzo email')");
	                        out.println("</script>");
	                }
	            }
	            else if(action.equalsIgnoreCase("login"))
				{
					String username = request.getParameter("username");		
					String password = request.getParameter("password");
					String generatedPassword1 = CryptWithMD5.cryptWithMD5(password);
					utente = model.doLogin(username,generatedPassword1);
					if(utente != null)
					{
						request.getSession().setAttribute("utente", utente);
						request.setAttribute("utente", utente);
						
						out.println("<script>");
						out.println("window.open('http://localhost:8080/Simplify3D/HomePageLog.jsp','_self')");
						out.println("</script>");
						return;
					}
					else
					{
						System.out.println("Username o password errata");
						out.println("<script>");
						out.println("window.history.back()");
						out.println("alert('Username o password errata')");
						out.println("</script>");
					}
				}
	            else if(action.equalsIgnoreCase("password_dimenticata"))
				{
	            	
	            	Utente user = new Utente();
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
					                new javax.mail.Authenticator() {
					                  protected PasswordAuthentication getPasswordAuthentication() {
					                    return new PasswordAuthentication(email_platform, pass_word);
					                  }
					                });
					
					        Message message = new MimeMessage(session);
					        message.setFrom(new InternetAddress(username));
					        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
					        message.setSubject("Reset Password Simplify3D");
					        message.setText(
					            "Ciao " + username + ", \n" + "La tua nuova password è¨: " + password + "\n Grazie!");
			        
				        String generatedPassword1 = CryptWithMD5.cryptWithMD5(password);
	
				   
				        
				        utente = model.doPasswordDimenticata(user,generatedPassword1);
				        
				        Transport.send(message);
			        
				        out.println("<script>");
						out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
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
	            else if(action.equalsIgnoreCase("logout"))
				{			
					request.getSession().removeAttribute("utente");
					request.getSession().invalidate();

					out.println("<script>");
					out.println("window.open('http://localhost:8080/Simplify3D/HomePage.jsp','_self')");
					out.println("</script>");
					return;
				}
	            else if(action.equalsIgnoreCase("modificapassword"))
				{
	            	String vecchiapassword = request.getParameter("password");
					String nuovapassword = request.getParameter("nuovapassword");
					String ripetinuovapassword = request.getParameter("ripetinuovapassword");
					
					if(!nuovapassword.equals(ripetinuovapassword)) {
						System.out.println("Le password non combaciano");
						out.println("<script>");
						out.println("window.history.back()");
						out.println("alert('Le password non combaciano')");
						out.println("</script>");
						return;
                	}
					else if(nuovapassword.equals(vecchiapassword)) {
						System.out.println("La nuova password non può essere uguale alla vecchia");
						out.println("<script>");
						out.println("window.history.back()");
						out.println("alert('La nuova password non può essere uguale alla vecchia')");
						out.println("</script>");
						return;
                	}
					
					String username = utente.getUsername();
					
					String generatedPassword1 = CryptWithMD5.cryptWithMD5(vecchiapassword);
					String generatedPassword2 = CryptWithMD5.cryptWithMD5(nuovapassword);
					
					Utente u = new Utente();
					u.setPassword(generatedPassword1);
					u.setUsername(username);
					u=model.doModificaPassword(u,generatedPassword2);
					if(u != null)
					{
						request.getSession().setAttribute("utente", utente);
						request.setAttribute("utente", utente);
	                    out.println("<script>");
	                    out.println("alert('Password Modificata Correttamente')");
	                    out.println("</script>");
					}
                    else
					{
						System.out.println("Password errata");
						out.println("<script>");
						out.println("window.history.back()");
						out.println("alert('Password errata')");
						out.println("</script>");
					}
				}
				} 
			}
	        catch(SQLException e) 
	        {
	            System.out.println("Error: "+ e.getMessage());
	            request.setAttribute("error", e.getMessage());
	        }	
	        
	        out.println("<script>");
	        out.println("window.open('http://localhost:8080/Simplify3D/HomePage.jsp','_self')");
	        out.println("</script>");
	        out.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	        doGet(request, response);
	    }

}
