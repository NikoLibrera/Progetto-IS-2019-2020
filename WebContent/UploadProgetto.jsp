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
        
        <link rel="stylesheet" type="text/css" href="css/styleUpload.css">
        <script type="text/javascript" src = "js/caricaImage.js"></script>
        <script type="text/javascript" src = "js/caricaFile.js"></script>
    </head>
    
	<body>
	
	<%@ include file="Header.jsp" %>
	
	<div class="contenitore">
	
		<div class="caricaFoto">
			<img alt="CARICA FOTO" src="images/caricaFoto.jpg" id="immagine">
		</div>
	
	<div class="infoUpload">
		<form action="Upload" method="post" id="formUpload" enctype="multipart/form-data">
		<p style='font-size:1.2em;' class="InputImage " align="center"><input type="file" name="immagine" required onchange="readURL(this);" id="image">
			<label for="image">Inserisci Immagine</label>
				<p style='font-size:1.2em;' align="center"><input type="text" size="43" name="titolo" required placeholder="Titolo" id="titolo">
				<p style='font-size:1.2em;' align="center"><textarea cols="40" rows="4" name="descrizione" required placeholder="Descrizione" maxlength="250"></textarea>
				<p style='font-size:1.2em;' align="center"><textarea cols="40" rows="4" name="consigli" required placeholder="Consigli di Stampa" maxlength="250"></textarea>
				<p style='font-size:1.2em;' class="inputFile" align="center"><input type="file" name="progetto" required placeholder="File" id="caricaFile">
				<p style='font-size:1.2em;' align="center">
				<select name="categoria" class="form-control" align="center">
					<option value="Seleziona" selected="selected">Seleziona la categoria</option>
					<option value="Art">Art</option>
					<option value="Fashion">Fashion</option>
					<option value="Toys">Toys</option>
					<option value="House">House</option>
					<option value="Office">Office</option>
					<option value="Gadget">Gadget</option>
					<option value="Hobby">Hobby</option>
					<option value="Tools">Tools</option>
				</select>

					<p align="center"><input type="submit" value="Upload progetto" class="inserisci_btn">
		</form>
	</div>
	</div>
	</body>
</html>