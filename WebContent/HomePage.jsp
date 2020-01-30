<%@page import="manager.progetto.ProgettoModelDM"%>        
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8" import="java.util.*,model.*,java.sql.*" %>
    <% 
    	ProgettoModelDM model=new ProgettoModelDM();
    	ArrayList<Progetto> progetti=model.getMostRated();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simplify3D: HomePage</title>
         
        <link rel="stylesheet" type="text/css" href="css/HomePage.css">
   
    </head>
	<body>

	<%@ include file="Header.jsp" %>
        
    <div class="contenitore">
    
        <table class="blocco">
        	<tr>
        		<td>
        	
        	<p id ="textgiallo" align="center">La più grande piattaforma Open Source</p>
        	
        	<p id="textnero" align="center"> dalla quale potrai scaricare migliaia di progetti </p>
        	
        	<p id="textnero" align="center"> messi a disposizione dagli utenti già registrati. </p>
        	
        	<p id="textgiallo" align="center"> Cosa aspetti? </p>
        	
        	<p id="textnero" align="center"> Iscriviti subito per accedere alle risorse </p>
        	
        	<p id="textnero" align="center"> presenti nel sistema. </p>
        	
        		</td>
        	</tr>
        </table>
        
        <div class="progetti">
        
        	<table class="featured" >
   				<tr>
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(0).getId_progetto() %>">
   						 <%	Blob image=progetti.get(0).getImmagine();
					byte [] img=image.getBytes(1, (int) image.length()); 
					String encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% String titolo=progetti.get(0).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(0).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(0).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   						
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(1).getId_progetto() %>">
   						 <%	 image=progetti.get(1).getImmagine();
					img=image.getBytes(1, (int) image.length()); 
					 encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% titolo=progetti.get(1).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(1).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(1).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   					
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(2).getId_progetto() %>">
   						 <%	 image=progetti.get(2).getImmagine();
					img=image.getBytes(1, (int) image.length()); 
					 encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% titolo=progetti.get(2).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(2).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(2).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   				
   				</tr>
   				
   				<tr>
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(3).getId_progetto() %>">
   						 <%	 image=progetti.get(3).getImmagine();
					img=image.getBytes(1, (int) image.length()); 
					 encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% titolo=progetti.get(3).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(3).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(3).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   					
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(4).getId_progetto() %>">
   						 <%	 image=progetti.get(4).getImmagine();
					img=image.getBytes(1, (int) image.length()); 
					 encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% titolo=progetti.get(4).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(4).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(4).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   					
   					<td>
   						<a href="ProgettoView.jsp?id=<%=progetti.get(5).getId_progetto() %>">
   						 <%	 image=progetti.get(5).getImmagine();
					img=image.getBytes(1, (int) image.length()); 
					 encode = Base64.getEncoder().encodeToString(img); %>
				<img src="data:image/jpeg;base64,<%=encode %>">
   						</a>
   						<p align="center"><% titolo=progetti.get(5).getTitolo();
										if(titolo.length()>17)
											titolo= progetti.get(5).getTitolo().substring(0, 17)+"...";%>
											<b><a href="ProgettoView.jsp?id=<%=progetti.get(5).getId_progetto() %>" class="ancoraTitolo"><%=titolo %></a></b>
   					</td>
   				</tr>
   			
   			</table>
        
        </div >
        
     </div>
	
	<%@ include file="footer.jsp" %>
	</body>
</html>