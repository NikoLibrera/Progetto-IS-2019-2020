<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	int isAdmin=0,isAuthor=0;
    Integer id=0;
	try{
	id=Integer.parseInt(request.getParameter("id"));
	} catch (NumberFormatException e) {
		response.sendRedirect("./HomePage.jsp");
		return;
	} 
	
	ProgettoModelDM model = new ProgettoModelDM();
	Progetto p = model.getProgettoById(id);
	if(p == null)
	{
		response.sendRedirect("./HomePage.jsp");
		return;
	}
	if(utente != null)
	{
		if(utente.getIsAdmin()==1)
			isAdmin=1;
		if(utente.getUsername().equalsIgnoreCase(p.getUsername()))
			isAuthor=1;
	}
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Progetto view</title>
        <link rel="stylesheet" type="text/css" href="css/styleProgettoView.css">
    </head>
    
    
	<body>
	
	<div><%@ include file="Header.jsp" %></div>
		
		<div class="contenitore">
		
			<div class="immagineCont">
				<%	Blob image=p.getImmagine();
					byte [] img=image.getBytes(1, (int) image.length()); 
					String encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
			
			</div>
			
			<div class="informazioni">
				<p style='font-size:1.8em;'><b><%= p.getTitolo() %></b></p>
				<p style='font-size:1.2em;'><b>Descrizione:</b></p><%= p.getDescrizione() %>
				<p style='font-size:1.2em;'><b>Consigli di stampa</b></p><%= p.getConsigli() %>
				<p style='font-size:1.2em;'><b>Download: </b>12
				<p style='font-size:1.2em;'><b>Versione: </b><%= p.getVersione() %></p>
				<a style="font-size:1.2em;" href="#">Autore: <%= p.getUsername() %></a>
			
			</div>
			
			<div class="button">
			
				<% if(isAuthor==1) {%>
					<form action="#" method="post">
					<p><input type="submit" value="Cancella Progetto">
					</form>
					<form action="#" method="post">
					<p><input type="submit" value="Modifica Progetto">
					</form>
				<% } %>
				<% if(isAdmin==1 && isAuthor==0) {%>
					<form action="#" method="post">
					<p><input type="submit" value="Cancella Progetto">
					</form>
				<% } %>
					<% if(utente==null) {%>
						<form action="#" method="post">
						<p><input type="submit" class="disab" value="Aggiungi ai Preferiti" disabled="disabled">
						</form>
					<% } else {
							if(!model.isPreferito(p, utente)){%>
								<form action="AggiungiPreferiti" method="post">
								<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
								<p><input type="submit" value="Aggiungi ai Preferiti">
								</form>
					<% 		}else{%>
								<form action="RimuoviPreferiti" method="post">
								<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
								<p><input type="submit" value="Rimuovi dai preferiti">
								</form>
					<% 	}} %>
					
					<% if(utente==null) {%>
					<form action="Download" method="post" id="form1">
						<p><input type="submit" class="disab" value="Download" disabled="disabled">
						</form>
					<% } else { %>
						<form action="Download" method="post">
						<input type="hidden" name="download" value="<%=p.getId_progetto()%>">
						<p><input type="submit" value="Download">
						</form>
					<% } %>
			
			</div>
		
		</div>
	
		<div class="valCommenti">
		
		<p style='font-size:1.2em;'>Valutazione:(2)</p>
		<p style='font-size:1.2em;'>Commenti:(1)</p>
		
		</div>
	
		<div><%@ include file="footer.jsp" %></div>
	</body>
</html>