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
        <title>Simplify3D: ProfiloPersonale</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/styleProfiloPersonale.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
        
        <div class="blocco" >
        	<div class="bottoneprofilonelblocco" align="center">
			 	<button class="btn float-center profiloblocco_btn"><img src="images/utente_profilo.png" height= "70px" width="70px"></button>
			 </div>
		</div>
		
		<div class="blocco2">
			<br>
			<h2>Progetti pubblicati</h2>
		</div>

        <div class="diritti">
			<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
		</div>
	</div>
	
	 	<div class="infoutente float-left">
		 	<h6>Nome: &nbsp;<%= utente.getNome()%></h6>
		 	<h6>Cognome: &nbsp;<%= utente.getCognome() %></h6>
		 	<h6>Username: &nbsp;<%= utente.getUsername() %></h6>
		 	<h6>Password: &nbsp;<%= utente.getPassword() %></h6>
		 	<h6>Nazionalità: &nbsp;<%= utente.getNazionalita() %></h6>
		 	<h6>E-Mail: &nbsp;<%= utente.getEmail() %></h6>
		 	<h6>Data di nascita: &nbsp;<%= utente.getData_nascita() %></h6>
	 	</div>
	 	
	 	<div class="blocco3">
	 		<button class="btn float-center login_btn"> Notifiche (0)</button>
	 		<br><br>
	 		<a href="ModificaPassword.jsp"><button class="btn float-center login_btn"> Modifica Password</button></a>
	 		<br><br>
	 		<button class="btn float-center login_btn"> Progetti Pubblicati</button>
	 		<br><br>
	 		<button class="btn float-center login_btn"> Progetti Salvati</button>
	 		<br><br>
	 		<button class="btn float-center login_btn"> Upload Progetto</button>
	 	</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>