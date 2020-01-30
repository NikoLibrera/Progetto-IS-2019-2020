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

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
     public Login() 
     {
        super();
     }
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UtenteModelDM model = new UtenteModelDM();
		Utente utente = null;
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");		
		String password = request.getParameter("password");
		String generatedPassword1 = CryptWithMD5.cryptWithMD5(password);
		try {
			utente = model.doLogin(username,generatedPassword1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(utente != null)
		{
			request.getSession().setAttribute("utente", utente);
			request.setAttribute("utente", utente);
			
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/HomePage.jsp','_self')");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}