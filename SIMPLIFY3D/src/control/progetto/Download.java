package control.progetto;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.progetto.ProgettoModelDM;
import model.Progetto;
import model.Utente;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public Download()
{
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			String id_progetto=request.getParameter("download");
			Utente utente = (Utente) request.getSession().getAttribute("utente");
			if(utente == null)
			{	
				response.sendRedirect("./HomePage.jsp");
				return;
			}
			ProgettoModelDM dao=new ProgettoModelDM();
			Integer idProgetto=0;
			try{
				idProgetto=Integer.parseInt(request.getParameter("download"));
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
				progetto = dao.getProgettoById(idProgetto);
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			if(progetto!=null)
				try {
					dao.aggiornaDownload(progetto, utente);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			int off=1;
			String mime=getServletContext().getMimeType("application/zip");
			response.setContentType(mime);
			OutputStream out=response.getOutputStream();
			ZipOutputStream zip=new ZipOutputStream(out);
			response.setHeader("Content-Disposition", "attachment; filename=\""+progetto.getTitolo()+".zip\"");
			
			
			Blob file=progetto.getFile_modello();
			String nome=progetto.getTitolo();
			
			try 
			{
				zip.putNextEntry(new ZipEntry(nome+".stl"));
				
				byte[] b=file.getBytes(1,(int)file.length());
				
				zip.write(b);
				
				off=off+(int)file.length()+1;
				
				zip.closeEntry();
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
					
			zip.close();
			
			response.setContentType("text/html");
			response.sendRedirect("ProgettoView.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
