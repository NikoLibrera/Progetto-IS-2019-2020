<%@page contentType="text/html" pageEncoding="UTF-8"
	import="java.util.*,model.Utente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D Login</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">
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
				<h3>Login</h3>
			</div>
			
			<div class="card-body">
				<form name="Accedi" id="accedi_form" action="Login" method="post">
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-username"><img src="images/user.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="username" class="form-control" placeholder="Username">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
						</div>
						<input type="password" name="password" class="form-control" placeholder="Password">
					</div>
					
					<div class="form-group">
						<input type="submit" value="Accedi" class="btn float-center login_btn">
					</div>
				</form>
				
				</div>
					<div class="passworddimenticata">
						<h6><a href="PasswordDimenticataPage.jsp">Password dimenticata? Clicca qui !</a></h6>
					</div>
					
					<div class="card-header1" align="center">
						<h6>Non sei ancora registrato?</h6>
						<a href=RegistrationPage.jsp><input type="submit" value="Registrati" class="btn float-left registration_btn"></a>
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