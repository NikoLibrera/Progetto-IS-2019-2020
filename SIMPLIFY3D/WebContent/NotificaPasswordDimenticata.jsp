<%@page contentType="text/html" pageEncoding="UTF-8"
	import="java.util.*,model.Utente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D Password Dimenticata</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/styleNotificaPasswordDimenticata.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
    
	<body>
	
	<% 	
		Utente u=(Utente)session.getAttribute("utente");
		if(u!=null){%>
			<script>
			window.open('http://localhost:8080/Simplify3D/HomePage.jsp','_self')
			</script>
	<% 	} %>
		<div class="container" align="center">
			<div class="foto">
				<img src="images/logo.PNG" height="150px" width="250px">
			</div>
			
			<div class="d-flex justify-content-center h-100">
	
			<div class="card">
				<div class="card-header" align="center">
					<h3>Recupera Password</h3>
				</div>
			
				<div class="card-body">
					<form name="Dimenticata" id="accedi_form" action="UtenteControl" method="post" onsubmit="return validateFormPassowordDimenticata()">
						<input type="hidden" name="action" value="password_dimenticata">
						
						<div class="bloccotext">
							<br>
							<h6> Abbiamo inviato una nuova</h6>
							<h6> password all'indirizzo </h6>
							<h6> specificato.</h6>
						</div>
						
						<div class="form-group">
							<input type="submit" value="Conferma" class="btn float-center login_btn">
						</div>
					</form>
				</div>
			</div>
			</div>
			<div class="diritti">
				<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
			</div>
		</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>