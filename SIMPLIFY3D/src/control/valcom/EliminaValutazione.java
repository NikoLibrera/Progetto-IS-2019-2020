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

@WebServlet("/EliminaValutazione")
public class EliminaValutazione extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public EliminaValutazione() 
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
		try{
			idProgetto=Integer.parseInt(request.getParameter("id_proge"));
		} catch (NumberFormatException e) {
			response.sendRedirect("./HomePage.jsp");
			return;
		} 
		
		ProgettoModelDM model2 = new ProgettoModelDM();
		Progetto progetto=null;
		try {
			progetto = model2.getProgettoById(idProgetto);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(progetto == null)
		{
			response.sendRedirect("./HomePage.jsp");
			return;
		}
		try 
		{
			if(model.isValutato(progetto, utente)) {
				model.eliminaValutazione(idProgetto, utente.getUsername());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+idProgetto+"','_self')");
			out.println("alert('Valutazione cancellata.')");
			out.println("</script>");
			
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
