<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	
    if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}
    
	ProgettoModelDM model = new ProgettoModelDM();
	ArrayList<Progetto> progetti = model.getPreferitiByUsername(utente.getUsername());
	System.out.println(progetti.isEmpty());
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Progetti salvati</title>
        <link rel="stylesheet" type="text/css" href="css/styleRicercaCategoria.css">
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
	 	<div class="racchiudi">
		 	<div class="blocco1" align="center">
		 		<br>
		 		<a href="#"><button class="btn bottoni">Notifiche (0)</button></a>
		 		<br><br>
		 		<a href="ModificaPassword.jsp"><button class="btn bottoni">Modifica Password</button></a>
		 		<br><br>
		 		<a href="ProgettiPubblicati.jsp"><button class="btn bottoni">Progetti Pubblicati</button></a>
		 		<br><br>
		 		<a href="ProgettiSalvati.jsp"><button class="btn bottoni">Progetti Salvati</button></a>
		 		<br><br>
		 		<a href="UploadProgetto.jsp"><button class="btn bottoni">Upload Progetto</button></a>
		 	</div>
		 	
		 	<div class="blocco2">
		 			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Progetti salvati</b></p>
		 			<%
		 			if(progetti == null || progetti.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'><b>Non hai aggiunto nessun progetto ai preferiti.</b></p>
		 			<%
						
		 			}
		 			else
		 			{%>
		 				<table id="tabella">
		 			<%	for(Progetto prog : progetti) 
		   				{
		 			%>	
		 				
							<tr>
								<td rowspan="3" class="img"><%	
										Blob image=prog.getImmagine();
										byte [] img=image.getBytes(1, (int) image.length()); 
										String encode = Base64.getEncoder().encodeToString(img); 
									 %>
									<a href="ProgettoView.jsp?id=<%=prog.getId_progetto() %>"><img id="imgtabella" src="data:image/jpeg;base64,<%=encode %>"></a>
								</td>
								<td class="titolo" style='font-size:1.1em;'><% String titolo=prog.getTitolo();
										if(titolo.length()>30)
											titolo= prog.getTitolo().substring(0, 30)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=prog.getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b></td>
								<td class="valutazione">valutazione</td>
								<td class="versione">Versione: <%= prog.getVersione()%></td>
								<td class="commenti">Commenti</td>
							</tr>
							<tr></tr>
							<tr>
								<td class="descrizione" style='font-size:0.9em;'><% String descrizione=prog.getDescrizione();
										if(descrizione.length()>30)
											descrizione= prog.getDescrizione().substring(0, 50)+"...";%>
											<%=descrizione %></td>		
							</tr>
						<%
		   				}
						%>
						</table>
					<%
		   			}
					%>
				</div>	
			</div>
			<%@ include file="footer.jsp" %>
	</body>
</html>