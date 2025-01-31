package control.valcom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.valcom.ValcomModelDM;
import model.Commento;
import model.Utente;

/**
 * Servlet implementation class EliminaCommento
 */
@WebServlet("/EliminaCommento")
public class EliminaCommento extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public EliminaCommento() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Utente u=(Utente) request.getSession().getAttribute("utente");
		
		if(u!=null)
		{
			
		String id=request.getParameter("id");
		
		String idC=request.getParameter("idcommento");
		int idCommento=Integer.parseInt(idC);
		
		ValcomModelDM daoCom=new ValcomModelDM();
		try
		{
			daoCom.cancellaCommento(idCommento);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		out.println("<script>");
        out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+id+"','_self')");
        out.println("alert('Commento cancellato Correttamente')");
        out.println("</script>");
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