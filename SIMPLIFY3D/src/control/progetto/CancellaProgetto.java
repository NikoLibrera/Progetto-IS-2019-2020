package control.progetto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import manager.progetto.ProgettoModelDM;
import model.Progetto;
import model.Utente;

@WebServlet("/CancellaProgetto")
@MultipartConfig(maxFileSize=16177216)
public class CancellaProgetto extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public CancellaProgetto() 
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
		String username = utente.getUsername();
		try {
			if(username.equalsIgnoreCase(model.getProgettoById(id).getUsername()) || utente.getIsAdmin()==1) {
				try 
				{
					model.doCancellaProgetto(id, username);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				finally
				{
					out.println("<script>");
					out.println("window.open('http://localhost:8080/Simplify3D/HomePage.jsp','_self')");
					out.println("alert('Progetto eliminato con successo')");
					out.println("</script>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
