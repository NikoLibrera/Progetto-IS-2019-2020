<%@page contentType="text/html" pageEncoding="UTF-8"%>         
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: HomePage</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styleHomePage.css">
    </head>
	<body>

	<div class="container" align="center">
		<div class="foto">
			<img src="images/logo.PNG" height="150px" width="250px">
		</div>
		
		<div class="bottoneaccediregistrati" align="center">
			<a href=LoginPage.jsp><input type="submit" value="Accedi o Registrati" class="btn float-left registration_btn"></a>
		</div>
		
		<div class="barra">
			<div class="row justify-content-center">
	        	<div class="col-12 col-md-10 col-lg-8">
	            	<form class="card card-sm">
		                <div class="card-body row no-gutters align-items-center">
		                    <div class="col-auto">
		                        <i class="fas fa-search h4 text-body"></i>
		                    </div>
		                    
		                    <div class="col">
		                        <input class="form-control form-control-lg form-control-borderless" type="search" placeholder="Digita qui per ricercare un progetto...">
		                    </div>
		                    
		                    <div class="col-auto">
		                        <button class="btn btn-lg btn-success" type="submit">Cerca</button>
		                    </div>
		                </div>
	                </form>
	           	</div>
	        </div>
        </div>
        
        <div class="blocco">
        	<br><br>
        	<p id ="textgiallo"> La più grande piattaforma Open Source</p>
        	
        	<p id="textnero"> dalla quale potrai scaricare migliaia di progetti </p>
        	
        	<p id="textnero"> messi a disposizione dagli utenti già registrati. </p>
        	
        	<p id="textgiallo"> Cosa aspetti? </p>
        	
        	<p id="textnero"> Iscriviti subito per accedere alle risorse </p>
        	
        	<p id="textnero"> presenti nel sistema. </p>
        </div>
        <div class="diritti">
			<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
		</div>
	</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>