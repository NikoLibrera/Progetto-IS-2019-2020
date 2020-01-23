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
    </head>
    
	<body>
	
	<%@ include file="Header.jsp" %>
	
	<div class="contenitore">
	
		<div class="caricaFoto">
			<img alt="CARICA FOTO" src="" id="immagine">
		</div>
	
	
	
	<div class="infoUpload">
		<p style='font-size:1.2em;'>Inserisci immagine:<input type="file" name="immagine" required onchange="readURL(this);" >
				<p style='font-size:1.2em;'><input type="text" size="43" name="titolo" required placeholder="Titolo">
				<p style='font-size:1.2em;'><textarea cols="40" rows="4" name="descrizione" required placeholder="Descrizione"></textarea>
				<p style='font-size:1.2em;'><textarea cols="40" rows="4" name="consigli" required placeholder="Consigli di Stampa"></textarea>
				<p style='font-size:1.2em;'>File: <input type="file" name="progetto" required placeholder="File">
				<p style='font-size:1.2em;'>Categoria: 
				<select name="categoria" class="form-control">
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
					
					<p><input type="submit" value="Upload progetto" class="inserisci_btn">
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>
	
	</body>
</html>