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
        <title>Simplify3D: ModificaPassword</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styleModificaPassword.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
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
        
        <div class="blocco" >
        	<div class="bottoneprofilonelblocco" align="center">
			 	<button class="btn float-center profiloblocco_btn"><img src="images/utente_profilo.png" height= "70px" width="70px"><br><h6><%= utente.getNome() + " " + utente.getCognome() %></h6></button>
			</div>
        </div>
        
        <div class="blocco2">
	        <div class="card-body">
					<form name="ModificaPassword" id="accedi_form" action="UtenteControl" method="post"  onsubmit="return validateFormModificaPassword()">
	      		 	<input type="hidden" name="action" value="modificapassword">
	       			
	       			<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
							</div>
						<input type="password" name="password" class="form-control" placeholder="Password Attuale">
					</div>
					
					<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
							</div>
						<input type="password" name="nuovapassword" class="form-control" placeholder="Nuova Password">
					</div>
					
					<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"><img src="images/password.png" height="25px" width="25px"></i></span>
							</div>
						<input type="password" name="ripetinuovapassword" class="form-control" placeholder="Ripeti Nuova Password">
					</div>
					
					<div class="form-group">
							<input type="submit" value="Conferma" class="btn float-center login_btn">
						</div>
					</form>
			</div>
		</div>
		
		<div class="blocco3">
		
		</div>
				
       
       
        <div class="diritti">
			<img id="copyright" src="images/copyright.png" height="20px" width="20px"><h6>2020 Simplify3D Inc. Tutti i diritti riservati</h6>
		</div>
	</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>