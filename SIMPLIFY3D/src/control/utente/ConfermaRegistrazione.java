package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.utente.UtenteModelDM;
import model.Utente;

@WebServlet("/ConfermaRegistrazione")
public class ConfermaRegistrazione extends HttpServlet 
{
  private static final long serialVersionUID = 1L;

  public ConfermaRegistrazione()
  {
	  super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    String username = request.getParameter("username");
    int codice = Integer.parseInt(request.getParameter("codice"));

    try 
    {

      Utente user = UtenteModelDM.getUtenteByUsername(username);

      System.out.println(user.toString());

      final int codice_db = UtenteModelDM.verificaCodice(user.getUsername());

      System.out.println("Codice conferma DB: " + codice_db);

      System.out.println("Codice conferma Get: " + codice);
      
      if (username.equals(user.getUsername())) 
      {
        if (codice_db == codice) 
        {
        	PrintWriter out = response.getWriter();
        	out.println("<script>");
            out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
            out.println("alert('Registrazione Confermata')");
            out.println("</script>");
            UtenteModelDM.setConfermato(user);
        } 
        else
        {
          System.out.println("Codice non presente nel DB");
          PrintWriter out = response.getWriter();
      	  out.println("<script>");
          out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
          out.println("alert('Registrazione Negata')");
          out.println("</script>");
        }
      } 
      else 
      {
    	  System.out.println("Username non presente nel DB");
          PrintWriter out = response.getWriter();
      	  out.println("<script>");
          out.println("window.open('http://localhost:8080/Simplify3D/LoginPage.jsp','_self')");
          out.println("alert('Registrazione Negata')");
          out.println("</script>");
      }
    } 
    catch (SQLException e) 
    {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
  {
    doGet(request, response);
  }
}