package control.progetto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.progetto.ProgettoModelDM;
import model.Progetto;
import model.Utente;

@WebServlet("/AggiungiPreferiti")
public class AggiungiPreferiti extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public AggiungiPreferiti() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ProgettoModelDM model = new ProgettoModelDM();
		PrintWriter out = response.getWriter();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");

		if(utente == null)
		{	
			response.sendRedirect("./HomePage.jsp");
			return;
		}
		
		Integer idProgetto=0;
		try
		{
			idProgetto=Integer.parseInt(request.getParameter("id"));
		} 
		catch (NumberFormatException e)
		{
			response.sendRedirect("./HomePage.jsp");
			return;
		} 
		
		ProgettoModelDM model2 = new ProgettoModelDM();
		Progetto progetto=null;
		
		try 
		{
			progetto = model2.getProgettoById(idProgetto);
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		if(progetto == null)
		{
			response.sendRedirect("./HomePage.jsp");
			return;
		}

		try 
		{
			model.addToPreferiti(progetto,utente);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+idProgetto+"','_self')");
			out.println("alert('Progetto aggiunto ai preferiti.')");
			out.println("</script>");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}