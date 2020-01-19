<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.Utente" %>
    
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
        <title>Simplify3D: HomePage</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styleHomePageLog.css">
    </head>
	<body>

	<div class="container" align="center">
		<div class="foto">
			<img src="images/logo.PNG" height="150px" width="250px">
		</div>
		
		<div class="bottoneprofilo">
			  <button class="btn float-left profilo_btn"><img src="images/utente_profilo.png" height= "70px" width="70px"><br><h6><%= utente.getNome() + "\n" + utente.getCognome() %></h6></button>
		</div>
		
		<div class="bottonelogout">
			  <a href="UtenteControl?action=logout"><button class="btn float-left logout_btn"><img src="images/logout.png" height= "70px" width="70px"><br><h6>Logout</h6></button></a>
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
		                        <button class="btn1 btn-lg btn-success" type="submit">Cerca</button>
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