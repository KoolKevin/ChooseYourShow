<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="beans.SupportoSpettacolo"%>
<%@ page import="beans.Spettacolo"%>
<%@ page import="java.util.*"%>

<html>
   <head>
	  <link type="text/css" href="../styles/default.css" rel="stylesheet"></link>
	  <!-- bootstrap -->
	  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
   </head>
   
   <body>
   		<%@ include file="../fragments/navbar.jsp" %>
   		<% 
   			if (session.getAttribute("listaRevisione") == null) {
   				response.sendRedirect("pages/homepage_amministratore.jsp/errore=revisione");
   			}
   			List<SupportoSpettacolo> toReview = (List<SupportoSpettacolo>)session.getAttribute("listaRevisione");
   			if (toReview.size() == 0) {
   		%>
   	<p>Nessuno spettacolo in attesa di approvazione...</p>	
   		<%	
   			} else {
   				%>
   				<div class=container mt-4>
   				<ul class="list-group">
   				<%
				   for (SupportoSpettacolo ss : (List<SupportoSpettacolo>)session.getAttribute("listaRevisione")) {
					  	Spettacolo s = ss.getSpettacoloNonApprovato();
					%>
				<li class="list-group-item">
					<h2><%= s.getNome()%></h2>
					<a href="#" class="btn btn-primary">Revisiona</a>
				</li>
					<%	   
				   }
   				%>
   				</ul>
   				</div>
   				<%
   			}
   		%>
   </body>
</html>
