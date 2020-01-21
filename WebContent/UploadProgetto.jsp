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
        <title>Simplify3D: Upload Progetto</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
        
       	<form method="post" action="InserisciProgetto" enctype="multipart/form-data">
			<p style='font-size:1.0em;'>Titolo: <input type="text" name="titolo" required><br><br></p>
			<p style='font-size:1.0em;'>Descrizione: <textarea cols="40" rows="4" name="descrizione" required></textarea><br><br></p>
			<p style='font-size:1.0em;'>Immagine: <input type="file" name="immagine" required><br><br></p>
			<p style='font-size:1.0em;'>File: <input type="file" name="progetto" required><br><br></p>
			<p style='font-size:1.0em;'>Categoria: 
			<select name="categoria" placeholder="cazzo">
			<option value="Art">Art</option>
			<option value="Fashion">Fashion</option>
			<option value="Toys">Toys</option>
			<option value="House">House</option>
			<option value="Office">Office</option>
			<option value="Gadget">Gadget</option>
			<option value="Hobby">Hobby</option>
			<option value="Tools">Tools</option>
			</select>
			<br><br></p>
			<input type="submit" value="Inserisci progetto">
		</form>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	
	</body>
</html>