package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.utente.UtenteModel;
import manager.utente.UtenteModelDM;
import model.Utente;

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
        
        String action = request.getParameter("action");//Sono le possibili azioni che possiamo fare tra le diverse pagine
		try 
		{ 
			if(action != null) 
			{
	            if(action.equalsIgnoreCase("registrati"))//Quando un utente si registra 
	            {
	            	String username = request.getParameter("username");	
	            	String cognome = request.getParameter("cognome");
	                String nome = request.getParameter("nome");		
	                //Date data_nascita = request.getParameter("data_nascita");
	                //boolean isAdmin = request.getParameter("isAdmin");
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
	                        out.println("window.open('http://localhost:8080/Simplify3D/RegistrationPage.jsp','_self')");
	                        out.println("alert('Esiste già un'account con lo stesso indirizzo email')");
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
	        out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
	        out.println("</script>");
	        out.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	        doGet(request, response);
	    }

}
