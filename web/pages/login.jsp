<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<html>
   <head>
	  <link type="text/css" href="../styles/default.css" rel="stylesheet"></link>
	  <!-- bootstrap -->
	  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
   </head>
   
   <body>
   		<%@ include file="../fragments/navbar.jsp" %>
   		
   		<!-- pagina -->
		<div class="container">
	   		<h2>Logga!</h2>
	 		<form method="POST" action="../loginServlet">
	 			Email: <input name="email" type="text"/><br>
	 			Password: <input name="password" type="password"/><br>
	 			<input name="categoria_utente" value="utente" type="radio" checked/>Utente<br>
	    		<input name="categoria_utente" value="pubblicatore" type="radio"/>Pubblicatore<br>
	    		<input name="categoria_utente" value="amministratore"  type="radio"/>Amministratore<br>
	    		
	 			<input name="operazione" value="login" type="submit" />
	 		</form>
	 		
	 		<a href="homepage_utente.jsp">vai alla homepage"</a>
	 		
	 		<!-- errore nel dispatching della login servlet -->
	 		<%
	 			String errore=request.getParameter("errore");
	 			if( errore != null ) {
	 		%>
	 		<div>c'è stato un errore nella loginServlet: <%= errore %></div>
	 		<%
	 			} //if
	 		%>
	 		<!-- messaggio per login fallito -->
	 		<%
	 			String logged_in=request.getParameter("logged_in");
	 			if( logged_in != null && logged_in.equals("false")) {
	 		%>
	 		<div>login fallito, riprova</div>
	 		<%
	 			} //if
	 		%>
	 		<!-- messaggio per logout -->
	 		<%
	 			logged_in=request.getParameter("logged_in");
	 			if( logged_in != null && logged_in.equals("logged_out")) {
	 		%>
	 		<div>logout effettuato con successo</div>
	 		<%
	 			} //if
	 		%>
 		</div>
   </body>
</html>

