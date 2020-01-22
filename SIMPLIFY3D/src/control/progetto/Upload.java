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

import com.mysql.cj.jdbc.Blob;

import manager.progetto.ProgettoModelDM;
import model.Progetto;
import model.Utente;

@WebServlet("/Upload")
public class Upload extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public Upload() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ProgettoModelDM model = new ProgettoModelDM();
		PrintWriter out = response.getWriter();
		
		
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		String username = u.getUsername();
		
		String titolo = request.getParameter("titolo");		
		String descrizione = request.getParameter("descrizione");
		String consigli = request.getParameter("consigli");
		String categoria = request.getParameter("categoria");
		Part file_modello = request.getPart("progetto");
		Part immagine = request.getPart("immagine");
		
		Progetto p = new Progetto();
		p.setId_progetto(1);
		p.setTitolo(titolo);
		p.setDescrizione(descrizione);
		model.caricaModello(p,file_modello.getInputStream());//.setFile_modello(file_modello);
		model.caricaImmagine(p,immagine.getInputStream());//p.setImmagine(immagine);
		p.setConsigli(consigli);
		p.setCategoria(categoria);
		p.setVersione(1);
		p.setUsername(username);
		
		
		try 
		{
			model.doUpload(p);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
			out.println("<script>");
			out.println("alert('Upload effettuato con successo')");
			out.println("</script>");
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
