<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<html>
   <head>
      <title>Start Web Application</title>
	  <link type="text/css" href="../styles/default.css" rel="stylesheet"></link>
   </head>
   
   <body>
   		<h2>Logga!</h2>
 		<form method="POST" action="../loginServlet">
 			Email: <input name="email" type="text"/><br>
 			Password: <input name="password" type="password"/><br>
 			<input name="operazione" value="loginUtente" type="submit" />
 		</form>
 		
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
 		
   </body>
</html>

