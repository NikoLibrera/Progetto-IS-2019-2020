<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D Password Dimenticata</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="stylePasswordDimenticata.css">
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
					<h3>Recupera Password</h3>
				</div>
			
				<div class="card-body">
					<form name="Dimenticata" id="accedi_form" action="UtenteControl" method="post" onsubmit="return validateFormPassowordDimenticata()">
						<input type="hidden" name="action" value="password_dimenticata">
						
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