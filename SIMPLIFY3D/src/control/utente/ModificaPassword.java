package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.utente.UtenteModelDM;
import model.CryptWithMD5;
import model.Utente;

@WebServlet("/ModificaPassword")
public class ModificaPassword extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public ModificaPassword()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null)
		{	
			response.sendRedirect("./HomePage.jsp");
			return;
		}
		UtenteModelDM model = new UtenteModelDM();
		
		String vecchiapassword = request.getParameter("password");
		String nuovapassword = request.getParameter("nuovapassword");
		String ripetinuovapassword = request.getParameter("ripetinuovapassword");
		
		if(!nuovapassword.equals(ripetinuovapassword)) 
		{
			System.out.println("Le password non combaciano");
			out.println("<script>");
			out.println("window.history.back()");
			out.println("alert('Le password non combaciano')");
			out.println("</script>");
			return;
    	}
		else if(nuovapassword.equals(vecchiapassword)) 
		{
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
		try 
		{
			u=model.doModificaPassword(u,generatedPassword2);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(u != null)
		{
			request.getSession().setAttribute("utente", utente);
			request.setAttribute("utente", utente);
            out.println("<script>");
            out.println("window.open('http://localhost:8080/Simplify3D/ProfiloPersonale.jsp','_self')");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
