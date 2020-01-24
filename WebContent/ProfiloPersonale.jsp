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
        <link rel="stylesheet" type="text/css" href="css/styleProfiloPersonale.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
        
      
		<div class="blocco2">
			<br>
			<h2>Progetti pubblicati</h2>
		</div>

        <div class="diritti">
			<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
		</div>
	
		<div class="blocco1">
	 	<div class="infoutente float-left">
		 	<p style='font-size:1.2em;'><b>Nome:</b> &nbsp;<%= utente.getNome()%></p>
		 	<p style='font-size:1.2em;'><b>Cognome:</b> &nbsp;<%= utente.getCognome() %></p>
		 	<p style='font-size:1.2em;'><b>Username:</b> &nbsp;<%= utente.getUsername() %></p>
		 	<p style='font-size:1.2em;'><b>Password:</b> &nbsp;<%= utente.getPassword() %></p>
		 	<p style='font-size:1.2em;'><b>Nazionalità:</b> &nbsp;<%= utente.getNazionalita() %></p>
		 	<p style='font-size:1.2em;'><b>E-Mail:</b> &nbsp;<%= utente.getEmail() %></p>
		 	<p style='font-size:1.2em;'><b>Data di nascita:</b> &nbsp;<%= utente.getData_nascita() %></p>
	 	</div>
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
	 		<a href="UploadProgetto.jsp"><button class="btn float-center login_btn"> Upload Progetto</button></a>
	 	</div>
		<%@ include file="footer.jsp" %>
	</body>
</html>