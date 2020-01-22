<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}

	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: ModificaPassword</title>
        <link rel="stylesheet" type="text/css" href="css/styleProgettoView.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
		
		<div id="cont" align="center">
		
			<div id="containerfoto" align="left">
			
				<div id="divbottonedownload" align="center">
					<h3 id="testograssetto">Autore:</h3><h3>Giovanni75</h3>
					<input type="submit" value="Download" class="download_btn">
				</div>
			</div>
			
			<div id="containertesto" align="left">
				<h1>Guitar Picks</h1>
				<br>
				<h3 id="testograssetto">Descrizione:</h3>
				<h3>Questo supporto per plettro per chiatarra ha due scomparti:<br>
				uno profondo per riporlo e uno poco profondo per un rapido<br>
				accesso al tuo preferito. Il design è abbastanza semplice<br>
				ma tiene  al tuo preferito. Il design è abbastanza semplice<br>
				ma contiene abbastanza scelte per rimanere organizzato e non<br>
				dover cercare in giro per la casa.</h3>
				<br>
				<h3 id="testograssetto">Consigli di stampa</h3>
				<h3>Imposta una temperatura di 200 gradi all'estrusore e di<br>
				50 gradi al piatto di stampa. Imposta una velocità di 60<br>
				millimetri al secondo.</h3>
				<br>
				<h3 id="testograssetto">Download: </h3><h3>12</h3>
				<br>
				<h3 id="testograssetto">Versione: </h3><h3>V1</h3>
				
				<div align="right">
					<input type="submit" value="Aggiungi ai Preferiti" class="preferiti_btn">
				</div>	
			</div>		
	     </div>
	     
	     <div align="center">
	     	<br><br>
	     	<h3 id="testograssetto">Valutazione:(2)</h3>
	     	<br>
	     	<h3 id="testograssetto">Commenti:(1)</h3>
	     </div>
	</body>
</html>