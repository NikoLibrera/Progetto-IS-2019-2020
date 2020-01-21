<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>

<header>
	<div class="intestazione">
	
		
		<img src="images/logo.PNG">
			<form action="#" class="formRicerca">
				
				<input class="ricerca" type="search" placeholder="Digita qui per ricercare un progetto...">
				<button class="button_cerca" type="submit">Cerca</button>
				</form>
			<% 	
		Utente u=(Utente)session.getAttribute("utente");
		if(u==null){%>
				
			  <form action="LoginPage.jsp" class="formAccedi">
				<button class="button_accedi">Accedi o Registrati</button>
			</form>
	<% 	}else{ %>
	<form action="#" class="formAccedi">
				<a href="ProfiloPersonale.jsp"><img src="images/utente_profilo.png"></a>
				<a href="Logout"><img src="images/logout.png" ></a>
			</form>
        <%} %>
        
			
		
	</div>
	<div class="nav">
		<ul>
		  <li><a href="#home">Home</a></li>
		  <li><a href="#news">Categorie</a></li>
		  <li><a href="#contact">Upload</a></li>
		</ul>
		</div>
</header>
</body>
</html>