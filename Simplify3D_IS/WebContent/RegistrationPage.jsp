<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Registrati</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styleRegistration.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>
	
	<div class="container" align="center">
		<div class="foto">
			<img src="images/logo.PNG" height="150px" width="250px">
		</div>
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header" align="center">
				<h3>Registrazione</h3>
			</div>
			<div class="card-body">
				<form name="Registrazione" id="registration_form" action="UtenteControl" method="post" onsubmit="return validateFormRegistrazione()">
					<input type="hidden" name="action" value="registrati"><!-- Serve a comunicare con l'AccountControl sulla scelta che si fa -->
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-nome"><img src="images/user.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="nome" class="form-control" placeholder="Nome">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-cognome"><img src="images/user.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="cognome" class="form-control" placeholder="Cognome">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-username"><img src="images/user.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="username" class="form-control" placeholder="Username">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-email"><img src="images/email.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="email" class="form-control" placeholder="E-mail">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
						</div>
						<input type="password" name="password" class="form-control" placeholder="Password">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
						</div>
						<input type="password" name="confermapassword" class="form-control" placeholder="Conferma Password">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-nazionalita"><img src="images/world.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="nazionalita" class="form-control" placeholder="Nazionalità">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-data"><img src="images/data.png" height="25px" width="25px"></i></span>
						</div>
						<input type="date" name="datanascita" class="form-control">
					</div>
					
					<div class ="text">
						<input type="checkbox" name="contratto" value="no"/>Accetta le condizioni di utilizzo di Simplify3D
					</div>
					
					<div class="form-group">
					<input type="submit" value="Registrati" class="btn float-center registration_btn">
					</div>
				</form>
				
				<div class="card-header1" align="center">
					<h5>Hai già un account?</h5>
					<a href=LoginPage.jsp><input type="submit" value="Accedi" class="btn float-left login_btn"></a>
				</div>		
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