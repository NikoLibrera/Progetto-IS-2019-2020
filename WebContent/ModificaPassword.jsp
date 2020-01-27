<%@page import="manager.valcom.ValcomModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}
	ValcomModelDM valcomModel=new ValcomModelDM();
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: ModificaPassword</title>
        <link rel="stylesheet" type="text/css" href="css/styleModificaPassword.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
        <div class="racchiudi">
        	<div class="blocco1" align="center">
		 		<br>
		 		<a href="Notifiche.jsp"><button class="btn bottoni">Notifiche (<%=valcomModel.getNumeroNotificheNonLette(utente.getUsername()) %>)</button></a>
		 		<br><br>
		 		<a href="ModificaPassword.jsp"><button class="btn bottoni">Modifica Password</button></a>
		 		<br><br>
		 		<a href="ProgettiPubblicati.jsp"><button class="btn bottoni">Progetti Pubblicati</button></a>
		 		<br><br>
		 		<a href="ProgettiSalvati.jsp"><button class="btn bottoni">Progetti Salvati</button></a>
		 		<br><br>
		 		<a href="UploadProgetto.jsp"><button class="btn bottoni">Upload Progetto</button></a>
		 	</div>
        
	        <div class="blocco2">
	        <p style='font-size:1.3em; color:#FFC312; margin-left: 0;'>&nbsp;&nbsp;&nbsp;<b>Modifica Password </b></p>
		        <div class="card-body">
						<form name="ModificaPassword" id="accedi_form" action="ModificaPassword" method="post"  onsubmit="return validateFormModificaPassword()">
		       			
		       			<div class="input-group form-group">
							<input type="password" name="password" class="form-control" placeholder="Password Attuale">
						</div>
						
						<div class="input-group form-group">
							<input type="password" name="nuovapassword" class="form-control" placeholder="Nuova Password">
						</div>
						
						<div class="input-group form-group">
							<input type="password" name="ripetinuovapassword" class="form-control" placeholder="Ripeti Nuova Password">
						</div>
						
						<div class="form-group">
								<input type="submit" value="Conferma" class="conferma">
							</div>
						</form>
				</div>
			</div>
		</div>
		
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		
		<%@ include file="footer.jsp" %>
	</body>
</html>