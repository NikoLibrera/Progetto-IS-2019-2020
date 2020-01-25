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

@WebServlet("/RimuoviPreferiti")
public class RimuoviPreferiti extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public RimuoviPreferiti() 
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
		
		int id = Integer.parseInt(request.getParameter("id"));

		try 
		{
			Progetto progetto = model.getProgettoById(id);
			if(model.isPreferito(progetto, utente)) {
				model.removeFromPreferiti(progetto,utente);
			} else {
				response.sendRedirect("http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+id);
				return;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+id+"','_self')");
			out.println("alert('Progetto rimosso dai preferiti.')");
			out.println("</script>");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
