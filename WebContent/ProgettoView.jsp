<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
		
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
		
		<div id="cont" align="center">
		
			<div id="containerfoto" align="left">
			<div class="immagine">
				<%	Blob image=p.getImmagine();
					byte [] img=image.getBytes(1, (int) image.length()); 
					String encode = Base64.getEncoder().encodeToString(img); %>
				<p id="imm"><img src="data:image/jpeg;base64,<%=encode %>"></p>
			</div>
				<div id="divbottonedownload" align="center">
					<p style='font-size:1.2em;'><b>Autore:</b></p><%= p.getUsername() %></p>
					<input type="submit" value="Download" class="download_btn">
				</div>
			</div>
			
			<div id="containertesto" align="left">
				<p style='font-size:1.2em;'><b><%= p.getTitolo() %></b></p>
				<br>
				<p style='font-size:1.2em;'><b>Descrizione:</b></p><%= p.getDescrizione() %>
				<br>
				<p style='font-size:1.2em;'><b>Consigli di stampa</b></p><%= p.getConsigli() %>
				<br>
				<p style='font-size:1.2em;'><b>Download: </b>12
				<br>
				<p style='font-size:1.2em;'><b>Versione: </b><%= p.getVersione() %></p>
				
				<div align="right">
					<input type="submit" value="Aggiungi ai Preferiti" class="preferiti_btn">
				</div>	
			</div>		
	     </div>
	     
	     <div align="center">
	     	<br><br>
	     	<p style='font-size:1.2em;'>Valutazione:(2)</p>
	     	<br>
	     	<p style='font-size:1.2em;'>Commenti:(1)</p>
	     </div>
	</body>
</html>