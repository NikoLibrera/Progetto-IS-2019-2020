<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");

    String categoria=null;
	try
	{
		categoria = request.getParameter("categoria");
	} 
	catch (NumberFormatException e) 
	{
		response.sendRedirect("./HomePage.jsp");
		return;
	} 
	if(categoria==null){
		categoria="Art";
	}
	ProgettoModelDM model = new ProgettoModelDM();
	ArrayList<Progetto> progetti = model.getByCategoria(categoria);
	System.out.println(progetti.isEmpty());
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Categorie</title>
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
		 			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b><%= categoria %></b></p>
		 			<%
		 			if(progetti == null || progetti.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'><b>Non è stato ancora caricato nessun progetto in questa categoria.</b></p>
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
	</body>
</html>