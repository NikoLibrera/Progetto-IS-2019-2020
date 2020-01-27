<%@page import="manager.valcom.ValcomModelDM"%>
<%@page import="manager.progetto.ProgettoModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.Progetto,java.sql.*,model.Commento,model.RispostaCommento" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	int isAdmin=0,isAuthor=0;
    Integer id=0;
	try{
	id=Integer.parseInt(request.getParameter("id"));
	} catch (NumberFormatException e) {
		response.sendRedirect("./HomePage.jsp");
		return;
	} 
	
	ProgettoModelDM model = new ProgettoModelDM();
	Progetto p = model.getProgettoById(id);
	if(p == null)
	{
		response.sendRedirect("./HomePage.jsp");
		return;
	}
	if(utente != null)
	{
		if(utente.getIsAdmin()==1)
			isAdmin=1;
		if(utente.getUsername().equalsIgnoreCase(p.getUsername()))
			isAuthor=1;
	}
	ValcomModelDM daoCom=new ValcomModelDM();
	int v=daoCom.getMediaValutazioniById(id);
	
	ArrayList<Commento> commenti=ValcomModelDM.getCommentiByIdProgetto(id);
	ArrayList<RispostaCommento> risposte=new ArrayList<RispostaCommento>();
	
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: Progetto view</title>
        <link rel="stylesheet" type="text/css" href="css/styleProgettoView.css">
        <link rel="stylesheet" type="text/css" href="css/commenti.css">
    </head>
    
    
	<body>
	
	<div><%@ include file="Header.jsp" %></div>
		
		<div class="contenitore">
		
			<div class="immagineCont">
				<%	Blob image=p.getImmagine();
					byte [] img=image.getBytes(1, (int) image.length()); 
					String encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
			
			</div>
			
			<div class="informazioni">
				<p style='font-size:1.8em;'><b><%= p.getTitolo() %></b></p>
				<p style='font-size:1.2em;'><b>Descrizione:</b></p><%= p.getDescrizione() %>
				<p style='font-size:1.2em;'><b>Consigli di stampa</b></p><%= p.getConsigli() %>
				<p style='font-size:1.2em;'><b>Download: </b><%= model.getDownloadById(id) %></p>
				<p style='font-size:1.2em;'><b>Versione: </b><%= p.getVersione() %></p>
				<a style="font-size:1.2em;" href="ProfiloUtente.jsp?username=<%= p.getUsername() %>">Autore: <%= p.getUsername() %></a>
			
			</div>
			
			<div class="button">
			
				<% if(isAuthor==1) {%>
					<form action="CancellaProgetto" method="post">
					<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
					<p><input type="submit" value="Cancella Progetto">
					</form>
					<form action="ModificaProgetto.jsp?id=<%=id  %>" method="post">
					<p><input type="submit" value="Modifica Progetto">
					</form>
				<% } %>
				<% if(isAdmin==1 && isAuthor==0) {%>
					<form action="CancellaProgetto" method="post">
					<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
					<p><input type="submit" value="Cancella Progetto">
					</form>
				<% } %>
					<% if(utente==null) {%>
						<form action="#" method="post">
						<p><input type="submit" class="disab" value="Aggiungi ai Preferiti" disabled="disabled">
						</form>
					<% } else {
							if(!model.isPreferito(p, utente)){%>
								<form action="AggiungiPreferiti" method="post">
								<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
								<p><input type="submit" value="Aggiungi ai Preferiti">
								</form>
					<% 		}else{%>
								<form action="RimuoviPreferiti" method="post">
								<input type="hidden" name="id" value="<%=p.getId_progetto()%>">
								<p><input type="submit" value="Rimuovi dai preferiti">
								</form>
					<% 	}} %>
					
					<% if(utente==null) {%>
					<form action="Download" method="post" id="form1">
						<p><input type="submit" class="disab" value="Download" disabled="disabled">
						</form>
					<% } else { %>
						<form action="Download" method="post">
						<input type="hidden" name="download" value="<%=p.getId_progetto()%>">
						<p><input type="submit" value="Download">
						</form>
					<% } %>
			
			</div>
		
		</div>
	
		<div class="valCommenti">
		
		<div class ="valutazione">
				<form action="Valuta" method="post">
					<fieldset class="rating">
					<br>
					   Valutazione: (<%=daoCom.getNumeroValutazioniByIdProgetto(id) %>)
					    <% switch(v){
					    	case 5:%>
						    <input type="radio" id="star5" name="rating" value="5"  checked="checked"/>
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1"/>
						    <label class = "full" for="star1"></label>
						    <%  break;
						    case 4:%>
						    <input type="radio" id="star5" name="rating" value="5"  />
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"checked="checked"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1"/>
						    <label class = "full" for="star1"></label>
						    <%  break;
						    case 3:%>
						    <input type="radio" id="star5" name="rating" value="5"  />
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" checked="checked" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1"/>
						    <label class = "full" for="star1"></label>
						    <%  break;
						    case 2:%>
						    <input type="radio" id="star5" name="rating" value="5"  />
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2" checked="checked"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1"/>
						    <label class = "full" for="star1"></label>
						    <%  break;
						    case 1:%>
						    <input type="radio" id="star5" name="rating" value="5"  />
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1" checked="checked"/>
						    <label class = "full" for="star1"></label>
						    <%  break;
						    case 0:%>
						    <input type="radio" id="star5" name="rating" value="5"  />
						    <label class = "full" for="star5"></label>
						    
						    <input type="radio" id="star4" name="rating" value="4"/>
						    <label class = "full" for="star4"></label>
						    
						    <input type="radio" id="star3" name="rating" value="3" />
						    <label class = "full" for="star3"></label>
						    
						    <input type="radio" id="star2" name="rating" value="2"/>
						    <label class = "full" for="star2"></label>
						    
						    <input type="radio" id="star1" name="rating" value="1"/>
						    <label class = "full" for="star1"></label>
						   
						    
						    <% }%>
					</fieldset>
					<input type="hidden" name="id_proge" value="<%=p.getId_progetto()%>">
					<% 	if(utente!=null){
							if(daoCom.isValutato(p, utente)) {%>
							<br><p><input type="submit" value="Aggiorna Valutazione">
						<% }else{ %>	
							<p><input type="submit" value="Inserisci Valutazione">
						<% }
						} %>
				</form>
				<% 	if(utente!=null){
							if(daoCom.isValutato(p, utente)) {%>
								<form action="EliminaValutazione" method="post">
								<input type="hidden" name="id_proge" value="<%=p.getId_progetto()%>">
								<p><input type="submit" value="Elimina Valutazione">
								</form>
						<% } 
						} %>
			</div>
		<p style='font-size:1.2em;'>Commenti:(<%=daoCom.getNumeroCommentiByIdProgetto(id) %>)</p>
		
			<div class="insert">
				<form action="InserisciCommento" method="post">
					<input type="hidden" name="id" value="<%=id%>">
					<p><textarea rows="5" cols="50" placeholder="inserisci Commento MAX(250 caratteri)" maxlength="250" name="inserisciCommento" required="required"></textarea>
					<%if(utente!=null) {%>
					<p><input type="submit" value="INSERISCI COMMENTO" class="insertCom">
					<%}else{%>
					<p><input type="submit" value="INSERISCI " class="disab" disabled="disabled">
					<%} %>
					</form>
			</div>
			
		</div>
		
		<div class="commenti">
			<%
			for(int i=0;i<commenti.size();i++)
			{
			
			%>
			<div class="commento">
				<div class="contenitoreCommento">
				<form action="InserisciRisposta" method="post">
				<p><%=commenti.get(i).getUsername()%>
				<p><textarea rows="5" cols="50" disabled="disabled"><%=commenti.get(i).getContenuto() %></textarea>
				<%if(u!=null){ %>
				<p><input type="submit" value="INSERISCI RISPOSTA">
				<p><textarea rows="5" cols="50" maxlength="250" required="required" name="rispostaCommento"></textarea>
				<input type="hidden" name="idCommento" value="<%=commenti.get(i).getId_commento()%>">
				<input type="hidden" name="id" value="<%=id%>">
				<%} %>
				</form>
				</div>
				<div id="contElimina">
				<form action="EliminaCommento" method="post" id="eliminaCommento">
					
					<%
					if(u!=null)
					if(u.getUsername().equals(commenti.get(i).getUsername())){  %>
					<input type="submit" value="X"  style="color: white;" id="croce">
				<%} %>
					<input type="hidden" value="<%=id%>" name="id">
					<input type="hidden" name="idcommento" value="<%=commenti.get(i).getId_commento()%>">
				</form>
				</div>
			</div>
			<%
				Commento commento=commenti.get(i);
				risposte=ValcomModelDM.getRisposteByIdCommento(commento.getId_commento());
				
				for(int k=0;k<risposte.size();k++)
				{
			%>
			
			<div class="risposta">
				<div class="contenitoreCommento">
				<p>IN RISPOSTA(<%=commenti.get(i).getUsername()%>) <%=risposte.get(k).getUsername() %>
				<p><textarea rows="5" cols="50" disabled="disabled"><%=risposte.get(k).getContenuto() %></textarea>
				</div>
				<div id="contElimina">
				<%if(u!=null)
					if(u.getUsername().equals(risposte.get(k).getUsername())){ %>
				<form action="EliminaRisposta" method="post" id="deleteRisp">
				<input type="submit" value="X"  style="color: white;" id="croce">
				<input type="hidden" value="<%=id%>" name="id">
				<input type="hidden" value="<%= risposte.get(k).getId_risposta() %>" name="idrisposta">
				</form>
				<%} %>
				
				
			</div>
			</div>
			<%}} %>
		</div>
	
	</body>
</html>