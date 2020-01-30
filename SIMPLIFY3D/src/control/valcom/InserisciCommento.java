package control.valcom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import manager.valcom.ValcomModelDM;
import model.Commento;
import model.Utente;

/**
 * Servlet implementation class InserisciCommento
 */
@WebServlet("/InserisciCommento")
public class InserisciCommento extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public InserisciCommento() 
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Utente u=(Utente) request.getSession().getAttribute("utente");
		String username = null;
		if(u!=null)
		{
			username=u.getUsername();
			
		String contenuto=request.getParameter("inserisciCommento");
		String id=request.getParameter("id");
		int id_progetto=Integer.parseInt(id);
		Commento commento=new Commento(contenuto, username, id_progetto);
		ValcomModelDM daoCom=new ValcomModelDM();
		try 
		{
			daoCom.inserisciCommento(commento, id_progetto);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		out.println("<script>");
        out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+id+"','_self')");
        out.println("alert('Commento inserito Correttamente')");
        out.println("</script>");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		doGet(request, response);
	}
}