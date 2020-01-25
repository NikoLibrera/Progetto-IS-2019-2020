<%@page import="model.Valutazione"%>
<%@page import="manager.valcom.ValcomModelDM"%>
<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");

    String contenuto= request.getParameter("contenuto");
	
	ProgettoModelDM model = new ProgettoModelDM();
	ArrayList<Progetto> progetti = model.ricercaBarra(contenuto);
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Ricerca</title>
        <link rel="stylesheet" type="text/css" href="css/styleRicercaCategoria.css">
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
	 	<div class="racchiudi">
		 	<div class="blocco1" align="center">
		 		<br>
		 		<a href="RicercaCategoria.jsp?categoria=Art"><button class="btn bottoni"> Art</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Fashion"><button class="btn bottoni">Fashion</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Toys"><button class="btn bottoni">Toys</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=House"><button class="btn bottoni"> House</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Office"><button class="btn bottoni">Office</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Gadget"><button class="btn bottoni">Gadget</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Hobby"><button class="btn bottoni">Hobby</button></a>
		 		<br><br>
		 		<a href="RicercaCategoria.jsp?categoria=Tools"><button class="btn bottoni">Tools</button></a>
		 	</div>
		 	
		 	<div class="blocco2">
		 			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Risultati ricerca per: <%= contenuto %></b></p>
		 			<%
		 			if(progetti == null || progetti.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'>&nbsp;&nbsp;&nbsp;<b>Non è stato ancora caricato nessun progetto.</b></p>
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
								<td class="valutazione">
								<% 
								ValcomModelDM modelValCom = new ValcomModelDM();
								int v = modelValCom.getMediaValutazioniById(prog.getId_progetto());
								switch(v){
						    	case 5:%>
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <%  break;
							    case 4:%>
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <%  break;
							    case 3:%>
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <%  break;
							    case 2:%>
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <%  break;
							    case 1:%>
							    <img id="idimg" src="images/StarPiena.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <%  break;
							    case 0:%>
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <img id="idimg" src="images/StarVuota.png">
							    <% }%>
								</td>
								<td class="versione">Versione: <%= prog.getVersione()%></td>
								<td class="commenti">Commenti: (<%=modelValCom.getNumeroCommentiByIdProgetto(prog.getId_progetto()) %>)</td>
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