package control.valcom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.progetto.ProgettoModelDM;
import manager.valcom.ValcomModelDM;
import model.Progetto;
import model.Utente;
import model.Valutazione;

@WebServlet("/Valuta")
public class Valuta extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public Valuta() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ValcomModelDM model = new ValcomModelDM();
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
			idProgetto=Integer.parseInt(request.getParameter("id_proge"));
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
		int voto=Integer.parseInt(request.getParameter("rating"));
		
		if(voto<1 || voto >5)
		{	
			response.sendRedirect("./ProgettoView.jsp?id="+idProgetto);
			return;
		}
		Valutazione valutazione=new Valutazione(voto, idProgetto, utente.getUsername());
		boolean presente=false;
		try 
		{
			if(model.isValutato(progetto, utente)) 
			{
				model.aggiornaValutazione(valutazione, idProgetto);
				presente=true;
			}
			else
				model.inserisciValutazione(valutazione, idProgetto);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(presente) 
			{
				out.println("<script>");
				out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+idProgetto+"','_self')");
				out.println("alert('Valutazione aggiornata.')");
				out.println("</script>");
			} 
			else 
			{
				out.println("<script>");
				out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+idProgetto+"','_self')");
				out.println("alert('Valutazione inserita.')");
				out.println("</script>");
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}
}