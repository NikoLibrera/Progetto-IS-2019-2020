<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente" %>
    
    <%
	/*Utente utente = (Utente) request.getSession().getAttribute("utente");
	if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}*/

	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Upload Progetto</title>
        <link rel="stylesheet" type="text/css" href="css/styleUpload.css">
    </head>
	<body>

	<%@ include file="Header.jsp" %>
        
        <div id="contenitore">	
			
			<div class="contenitoreFoto">
			
			
			</div>
			
			<div class="informationiUpload">
				<form action="Upload" method="post" enctype="multipart/form-data">
				<p style='font-size:1.2em;'>Inserisci immagine:<br><br><input type="file" name="immagine" required><br></p>
				<p style='font-size:1.2em;'>Titolo:<br> <input type="text" size="43" name="titolo" required><br></p>
				<p style='font-size:1.2em;'>Descrizione: <br><textarea cols="40" rows="4" name="descrizione" required></textarea><br></p>
				<p style='font-size:1.2em;'>Consigli di stampa: <br><textarea cols="40" rows="4" name="consigli" required></textarea><br></p>
				<p style='font-size:1.2em;'>File: <input type="file" name="progetto" required><br></p>
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
				<br>
				</p>	
					<input type="submit" value="Upload progetto" class="inserisci_btn">
				
				</form>	
			</div>
		</div>		
	     
	     
	     <div><%@ include file="footer.jsp" %></div>
	</body>
</html>