<%@page import="manager.utente.UtenteModelDM"%>
<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    
    String username=null;
	try
	{
		username = request.getParameter("username");
	} 
	catch (NumberFormatException e) 
	{
		response.sendRedirect("./HomePage.jsp");
		return;
	} 
	if(username==null){
		response.sendRedirect("./HomePage.jsp");
		return;
	}else if(utente!=null && username.equalsIgnoreCase(utente.getUsername())){
		response.sendRedirect("./ProfiloPersonale.jsp");
		return;
	}
	
	
	UtenteModelDM utenteModel = new UtenteModelDM();
	Utente utente2 = utenteModel.getUtenteByUsername(username);
	if(utente2==null){
		response.sendRedirect("./HomePage.jsp");
		return;
	}
	ProgettoModelDM model = new ProgettoModelDM();
	ArrayList<Progetto> progetti = model.getByUsername(username);
	System.out.println(progetti.isEmpty());
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Utente</title>
        <link rel="stylesheet" type="text/css" href="css/styleRicercaCategoria.css">
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
	 	<div class="racchiudi">
		 	<div class="blocco1" align="center">
		 		<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Dati utente</b></p>
		 		<p ><img src="images/utente_profilo.png" ></p>
		 		<p style='font-size:1.2em;'><b>Nome:</b> &nbsp;<%= utente2.getNome()%></p>
		 		<p style='font-size:1.2em;'><b>Cognome:</b> &nbsp;<%= utente2.getCognome() %></p>
		 		<p style='font-size:1.2em;'><b>Username:</b> &nbsp;<%= utente2.getUsername() %></p>
		 	</div>
		 	
		 	<div class="blocco2">
		 			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Progetti pubblicati</b></p>
		 			<%
		 			if(progetti == null || progetti.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'>&nbsp;&nbsp;&nbsp;<b><%= utente2.getUsername() %> non ha ancora caricato nessun progetto.</b></p>
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