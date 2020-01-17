<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D Login</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styleLogin.css">
        <script type="text/javascript" src = "js/formValidation.js"></script>
    </head>
	<body>
	<div class="container">
	
	<div class="d-flex justify-content-center h-100">
	
		<div class="card">
			<div class="card-header">
				<h3>Accedi</h3>
				<div class="d-flex justify-content-end social_icon">
					<span><i class="btn social_btn"><img src="images/fb.png" height="30px" width="30px"></i></span>
					<span><i class="btn social_btn"><img src="images/instagram.png" height="30px" width="30px" ></i></span>
					<span><i class="btn social_btn"><img src="images/google.png" height="30px" width="30px" ></i></span>
				</div>
			</div>
			<div class="card-body">
				<form name="Accedi" id="accedi_form" action="AccountControl" method="post" onsubmit="return validateFormAccedi()">
					<input type="hidden" name="action" value="login"><!-- Serve a comunicare con l'AccountControl sulla scelta che si fa -->
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"><img src="images/user.png" height="25px" width="25px"></i></span>
						</div>
						<input type="text" name="email" class="form-control" placeholder="E-mail">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
						</div>
						<input type="password" name="password" class="form-control" placeholder="Password">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox">Ricorda
					</div>
					<div class="form-group">
						<input type="submit" value="Accedi" class="btn float-right login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Non sei ancora registrato?<a href="RegistrationPage.jsp">Registrati</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="#">Password dimenticata?</a>
				</div>
			</div>
		</div>
	</div>
</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>