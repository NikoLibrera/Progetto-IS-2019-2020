<%@page import="manager.progetto.ProgettoModelDM"%>
<%@page import="manager.valcom.ValcomModelDM"%>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.Utente,model.*,java.sql.*" %>
    
    <%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if(utente == null)
	{	
		response.sendRedirect("./HomePage.jsp");
		return;
	}
	ProgettoModelDM model = new ProgettoModelDM();
	ArrayList<Progetto> progetti = model.getByUsername(utente.getUsername());
	System.out.println(progetti.isEmpty());
	ValcomModelDM valcomModel=new ValcomModelDM();
	%>          
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: ProfiloPersonale</title>
        <link rel="stylesheet" type="text/css" href="css/styleProfiloPersonale.css">
        <script type="text/javascript" src = "js/formValidationUtente.js"></script>
    </head>
	<body>

	<div><%@ include file="Header.jsp" %></div>
        
      <div class="all">
		
	 	<div class="infoutente">
	 	<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Dati utente</b></p>
	 		<p align="center"><img src="images/utente_profilo.png" ></p>
		 	<p style='font-size:1.2em;'><b>Nome:</b> &nbsp;<%= utente.getNome()%></p>
		 	<p style='font-size:1.2em;'><b>Cognome:</b> &nbsp;<%= utente.getCognome() %></p>
		 	<p style='font-size:1.2em;'><b>Username:</b> &nbsp;<%= utente.getUsername() %></p>
		 	<p style='font-size:1.2em;'><b>Password:</b> &nbsp;* * * * * * * *</p>
		 	<p style='font-size:1.2em;'><b>Nazionalità:</b> &nbsp;<%= utente.getNazionalita() %></p>
		 	<p style='font-size:1.2em;'><b>E-Mail:</b> &nbsp;<%= utente.getEmail() %></p>
		 	<p style='font-size:1.2em;'><b>Data di nascita:</b> &nbsp;<%= utente.getData_nascita() %></p>
	 	</div>
	 	
	 	<div class="bott">
	 		<a href="Notifiche.jsp"><button class="btn bottoni">Notifiche (<%=valcomModel.getNumeroNotificheNonLette(utente.getUsername()) %>)</button></a>
	 		<br><br>
	 		<a href="ModificaPassword.jsp"><button class="bottoni"> Modifica Password</button></a>
	 		<br><br>
	 		<a href="ProgettiPubblicati.jsp"><button class="bottoni"> Progetti Pubblicati</button></a>
	 		<br><br>
	 		<a href="ProgettiSalvati.jsp"><button class="bottoni"> Progetti Salvati</button></a>
	 		<br><br>
	 		<a href="UploadProgetto.jsp"><button class="bottoni"> Upload Progetto</button></a>
	 	</div>
	 	
	 	<div class="pubblicati">
			<p style='font-size:1.4em; color:#FFC312;'>&nbsp;&nbsp;&nbsp;<b>Progetti pubblicati</b></p>
			<%
		 			if(progetti == null || progetti.size()==0)
					{
		 			%>
		 				<p style='font-size:1.2em;'><b>Non hai ancora pubbicato progetti.</b></p>
		 			<%
						
		 			}
		 			else
		 			{
		 			%>
		 				<table id="tabella">
		 			<%	for(Progetto prog : progetti) 
		   				{
		 			%>	
							<tr>
								<td rowspan="3" class="img"><%	
										Blob image=prog.getImmagine();
										byte [] img=image.getBytes(1, (int) image.length()); 
										String encode = Base64.getEncoder().encodeToString(img); 
									 %>
									<a href="ProgettoView.jsp?id=<%=prog.getId_progetto() %>"><img id="imgtabella" src="data:image/jpeg;base64,<%=encode %>"></a>
								</td>
								<td class="titolo" style='font-size:1.1em;'><% String titolo=prog.getTitolo();
										if(titolo.length()>30)
											titolo= prog.getTitolo().substring(0, 30)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=prog.getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b></td>
							</tr>
							<tr></tr>
							<tr>
								<td class="descrizione" style='font-size:0.9em;'><% String descrizione=prog.getDescrizione();
										if(descrizione.length()>30)
											descrizione= prog.getDescrizione().substring(0, 50)+"...";%>
											<%=descrizione %></td>		
							</tr>
						<%
		   				}
						%>
						</table>
					<%
		   			}
					%>
			</div>
	  </div>
	  <div class="footer"><%@ include file="footer.jsp" %></div>
	</body>
</html>