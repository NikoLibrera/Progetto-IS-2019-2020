<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*"%>
    
    <%
	/*Utente utente = (Utente) request.getSession().getAttribute("utente");
	if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}*/
		String idProgetto=request.getParameter("id");
		ProgettoModelDM dao=new ProgettoModelDM();
		int id=Integer.parseInt(idProgetto);
		Progetto p=dao.getProgettoById(id);
		
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Modifica Progetto</title>
        
        <link rel="stylesheet" type="text/css" href="css/ModificaProgetto.css">
        <script type="text/javascript" src = "js/caricaImage.js"></script>
    </head>
    
	<body>
	
	<%@ include file="Header.jsp" %>
	
	<div class="contenitore">
	
		<%	Blob image=p.getImmagine();
					byte [] img=image.getBytes(1, (int) image.length()); 
					String encode = Base64.getEncoder().encodeToString(img); %>
		<div class="caricaFoto">
			<img src="data:image/jpeg;base64,<%=encode %>" id="immagine">
		</div>
	
	
	
	<div class="infoUpload">
		<form action="Upload" method="post" id="formUpload" enctype="multipart/form-data">
		<%System.out.println(p.getId_progetto()); %>
		<input type="hidden" value="7" name="id">
		<p style='font-size:1.2em;' class="InputImage"><input type="file" name="immagine" required onchange="readURL(this);" id="image">
			<label for="image">Inserisci Immagine</label>
				<p style='font-size:1.2em;'><input type="text" size="43" name="titolo" required placeholder="Titolo" id="titolo" value="<%=p.getTitolo()%>">
				<p style='font-size:1.2em;'><textarea cols="40" rows="4" name="descrizione" required placeholder="Descrizione"><%=p.getDescrizione()%></textarea>
				<p style='font-size:1.2em;'><textarea cols="40" rows="4" name="consigli" required placeholder="Consigli di Stampa"><%=p.getConsigli()%></textarea>
				<p style='font-size:1.2em;' class="inputFile"><input type="file" name="progetto" required placeholder="File" id="caricaFile" title="Seleziona un nuovo file ">
				<p style='font-size:1.2em;'>Versione:<input type="text" id="version" name="versione" value="<%=p.getVersione()%>">
				<p style='font-size:1.2em;'>Categoria: 
				<select name="categoria" class="form-control">
					<option value="Seleziona" selected="selected"><%=p.getCategoria()%></option>
					<option value="Art">Art</option>
					<option value="Fashion">Fashion</option>
					<option value="Toys">Toys</option>
					<option value="House">House</option>
					<option value="Office">Office</option>
					<option value="Gadget">Gadget</option>
					<option value="Hobby">Hobby</option>
					<option value="Tools">Tools</option>
				</select>
					
					<p><input type="submit" value="Modifica Progetto" class="inserisci_btn">
		</form>
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>
	
	</body>
</html>