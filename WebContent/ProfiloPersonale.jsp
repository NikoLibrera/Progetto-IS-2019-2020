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
        
      <div class="all">
		

	 	<div class="infoutente">
		 	<p style='font-size:1.2em;'><b>Nome:</b> &nbsp;<%= utente.getNome()%></p>
		 	<p style='font-size:1.2em;'><b>Cognome:</b> &nbsp;<%= utente.getCognome() %></p>
		 	<p style='font-size:1.2em;'><b>Username:</b> &nbsp;<%= utente.getUsername() %></p>
		 	<p style='font-size:1.2em;'><b>Password:</b> &nbsp;<%= utente.getPassword() %></p>
		 	<p style='font-size:1.2em;'><b>Nazionalità:</b> &nbsp;<%= utente.getNazionalita() %></p>
		 	<p style='font-size:1.2em;'><b>E-Mail:</b> &nbsp;<%= utente.getEmail() %></p>
		 	<p style='font-size:1.2em;'><b>Data di nascita:</b> &nbsp;<%= utente.getData_nascita() %></p>
	 	</div>

	 	
	 	<div class="bott">
	 		<button class="bottoni"> Notifiche (0)</button>
	 		<br><br>
	 		<a href="ModificaPassword.jsp"><button class="bottoni"> Modifica Password</button></a>
	 		<br><br>
	 		<a href="ProgettiPubblicati.jsp"><button class="bottoni"> Progetti Pubblicati</button></a>
	 		<br><br>
	 		<a href="ProgettiSalvati.jsp"><button class="bottoni"> Progetti Salvati</button></a>
	 		<br><br>
	 		<a href="UploadProgetto.jsp"><button class="bottoni"> Upload Progetto</button></a>
	 	</div>
	 	
	 	<div class="pubblicati">
			<h2>Progetti pubblicati</h2>
		</div>
	 	
	  </div>
	  <div class="footer"><%@ include file="footer.jsp" %></div>
	</body>
</html>