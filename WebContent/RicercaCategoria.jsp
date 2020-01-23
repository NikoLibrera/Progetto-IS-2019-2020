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
        
        <div class="blocco" >
        	<div class="bottoneprofilonelblocco" align="center">
			 	<button class="btn float-center profiloblocco_btn"><img src="images/utente_profilo.png" height= "70px" width="70px"></button>
			 </div>
		</div>

        <div class="diritti">
			<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
		</div>
	 	
	 	<div class="blocco1" align="center">
	 		<br>
	 		<button class="btn bottoni"> Art</button>
	 		<br><br>
	 		<a href="ModificaPassword.jsp"><button class="btn bottoni">Fashion</button></a>
	 		<br><br>
	 		<button class="btn bottoni">Toys</button>
	 		<br><br>
	 		<button class="btn bottoni"> House</button>
	 		<br><br>
	 		<a href="UploadProgetto.jsp"><button class="btn bottoni">Office</button></a>
	 		<br><br>
	 		<button class="btn bottoni">Gadget</button>
	 		<br><br>
	 		<a href="RicercaCategoria.jsp?categoria=Hobby"><button class="btn bottoni">Hobby</button></a>
	 		<br><br>
	 		<button class="btn bottoni">Tools</button>
	 	</div>
	 	
	 	<div class="blocco2">
	 			<p style='font-size:1.2em;'>&nbsp;&nbsp;&nbsp;<b><%= categoria %></b></p>
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
								<img id="imgtabella" src="data:image/jpeg;base64,<%=encode %>">
							</td>
							<td class="titolo"><% String titolo=prog.getTitolo();
									if(titolo.length()>30)
										titolo= prog.getTitolo().substring(0, 30)+"...";%>
										<%=titolo %></td>
							<td class="valutazione">valutazione</td>
							<td class="versione"><%= prog.getVersione()%></td>
							<td class="commenti">Commenti</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="descrizione"><% String descrizione=prog.getDescrizione();
									if(descrizione.length()>30)
										descrizione= prog.getDescrizione().substring(0, 30)+"...";%>
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
	</body>
</html>