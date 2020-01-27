<%@page import="model.Valutazione"%>
<%@page import="manager.valcom.ValcomModelDM"%>
<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.*,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	
    if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}
    
	ValcomModelDM model = new ValcomModelDM();
	ArrayList<Notifica> notifiche = model.getNotificheByUsername(utente.getUsername());
	System.out.println(notifiche.size());
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Notifiche </title>
        <link rel="stylesheet" type="text/css" href="css/styleRicercaCategoria.css">
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
	 	<div class="racchiudi">
		 	<div class="blocco1" align="center">
		 		<br>
		 		<a href="Notifiche.jsp"><button class="btn bottoni">Notifiche (<%=model.getNumeroNotificheNonLette(utente.getUsername()) %>)</button></a>
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
		 			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Notifiche </b></p>
		 			<%
		 			if(notifiche == null || notifiche.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'><b>Non ci sono ancora notifiche.</b></p>
		 			<%
						
		 			}
		 			else
		 			{%>
		 				<table id="tabella">
		 			<%	for(Notifica n : notifiche) 
		   				{%>
							<tr>
								<td rowspan="3" class="img"><%	
										Blob image=n.getImmagine();
										byte [] img=image.getBytes(1, (int) image.length()); 
										String encode = Base64.getEncoder().encodeToString(img); 
									 %>
									 <% if(n.isClicked()==1){ %>
									<a href="ProgettoView.jsp?id=<%=n.getId_progetto() %>"><img id="imgtabella" src="data:image/jpeg;base64,<%=encode %>" style='opacity: 0.5; '></a> 
								</td>
								<td class="titolo" style='font-size:1.1em;'><% String titolo=n.getTitolo();%>
											<b><a href="ProgettoView.jsp?id=<%=n.getId_progetto() %>" class="ancoraTitolo" style='opacity: 0.5; '><%=titolo %></a></b></td>
									<% }else{ %>
									<a href="ProgettoView.jsp?id=<%=n.getId_progetto() %>" onclick="<% model.setClickedNotifica(n); %>"><img id="imgtabella" src="data:image/jpeg;base64,<%=encode %>"></a> 
								</td>
								<td class="titolo" style='font-size:1.1em;'><% String titolo=n.getTitolo();%>
											<b><a href="ProgettoView.jsp?id=<%=n.getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b></td>
									<% } %>
								<td class="valutazione">
								
								</td>
								<td class="versione"></td>
							</tr>
							<tr></tr>
							<tr>
								<td class="descrizione" style='font-size:0.9em;'></td>		
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