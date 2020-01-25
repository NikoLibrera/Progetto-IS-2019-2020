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
import model.RispostaCommento;
import model.Utente;

/**
 * Servlet implementation class InserisciRisposta
 */
@WebServlet("/InserisciRisposta")
public class InserisciRisposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciRisposta() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Utente u=(Utente) request.getSession().getAttribute("utente");
		String username = null;
		if(u!=null)
		{
			
		
			username=u.getUsername();
			
		String contenuto=request.getParameter("rispostaCommento");
		String id=request.getParameter("id");
		int id_progetto=Integer.parseInt(id);
		String idC=request.getParameter("idCommento");
		int id_commento=Integer.parseInt(idC);
		RispostaCommento rispostaCommento=new RispostaCommento(contenuto, username, id_commento);
		
		ValcomModelDM daoCom=new ValcomModelDM();
		try {
			daoCom.inserisciRisposta(rispostaCommento, id_commento);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<script>");
        out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+id+"','_self')");
        out.println("alert('Commento inserito Correttamente')");
        out.println("</script>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
