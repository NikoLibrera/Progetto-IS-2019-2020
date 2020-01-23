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
	
		
		 <a class="logo" href="HomePage.jsp"><img  class="imgLogo" src="images/logo.PNG" ></a>
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
				<a class="profilo" href="ProfiloPersonale.jsp"><button class="imgProfilo"><img src="images/utente_profilo.png" ><h3><%= u.getNome() + "\n" + u.getCognome() %></h3></button></a>
				<a class="logout" href="Logout"><button class="imgLogout"><img src="images/logout.png"><h3>Logout</h3></button></a>
        <%} %>
        
			
		
	</div>
	<div class="nav">
		<ul>
		  <li><a href="HomePage.jsp">Home</a></li>
		  <li><a href="#news">Categorie</a></li>
		  <li><a href="UploadProgetto.jsp">Upload</a></li>
		</ul>
		</div>
</header>
</body>
</html>