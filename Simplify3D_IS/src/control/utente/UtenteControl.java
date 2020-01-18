package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteModel;
import model.UtenteModelDM;

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
	                //String data_nascita = request.getParameter("data_nascita");
	                //int isAdmin = (Integer)request.getParameter("isAdmin");
	                String password = request.getParameter("password");
	                String email = request.getParameter("email");
	                String nazionalita = request.getParameter("nazionalita");
	                
	                boolean emailGiaEsistente;
	                Utente u = new Utente();
	                u.setUsername(username);
	                u.setCognome(cognome);
	                u.setNome(nome);
	                //u.setData_nascita(data_nascita);
	                //u.setIsAdmin(isAdmin);
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
	                        out.println("window.open('http://localhost/Simplify3D_IS/RegistrationPage.jsp','_self')");
	                        out.println("alert('Esiste già un'account con lo stesso indirizzo email')");
	                        out.println("</script>");
	                }
	            }
	            else if(action.equalsIgnoreCase("login"))
				{
					String username = request.getParameter("username");		
					String password = request.getParameter("password");
					
					utente = model.doLogin(username,password);
					if(utente != null)
					{
						request.getSession().setAttribute("utente", utente);
						request.setAttribute("utente", utente);
						
						out.println("<script>");
						out.println("window.open('http://localhost/Simplify3D_IS/HomePage.jsp','_self')");
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
					String username = request.getParameter("username");		
					String email = request.getParameter("email");
					
					utente = model.doPasswordDimenticata(username,email);
					if(utente != null)
					{
						request.getSession().setAttribute("utente", utente);
						request.setAttribute("utente", utente);
						
						out.println("<script>");
						out.println("window.open('http://localhost/Simplify3D_IS/LoginPage.jsp','_self')");
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
				} 
			}
	        catch(SQLException e) 
	        {
	            System.out.println("Error: "+ e.getMessage());
	            request.setAttribute("error", e.getMessage());
	        }	
	        
	        out.println("<script>");
	        out.println("window.open('http://localhost/Simplify3D_IS/HomePage.jsp','_self')");
	        out.println("</script>");
	        out.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	        doGet(request, response);
	    }

}
