package control.progetto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import manager.progetto.ProgettoModelDM;
import model.Progetto;


@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Modifica() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String titolo=request.getParameter("titolo");
		System.out.println(titolo);
		String descrizione=request.getParameter("descrizione");
		String consigli=request.getParameter("consigli");
		
		
		String categoria=request.getParameter("categoria");
		Part file_modello = request.getPart("progetto");
		Part immagine = request.getPart("immagine");
		String id_progetto=request.getParameter("id");
		System.out.println(id_progetto);
		//int id=Integer.parseInt(id_progetto);
		
		ProgettoModelDM dao=new ProgettoModelDM();
		Progetto p=null;
		try {
			//p=dao.getProgettoById(id);
			p.incVersion();
			int versione=p.getVersione();
			p.setTitolo(titolo);
			p.setDescrizione(descrizione);
			p.setConsigli(consigli);
			p.setVersione(versione);
			p.setCategoria(categoria);
			
			dao.modificaProgetto(p, immagine.getInputStream(), file_modello.getInputStream());
			
			out.println("<script>");
			out.println("window.open('http://localhost:8080/Simplify3D/ProgettoView.jsp?id="+p.getId_progetto()+"','_self')");
			out.println("alert('Upload effettuato con successo')");
			out.println("</script>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
