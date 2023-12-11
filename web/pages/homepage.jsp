<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="beans.Utente"%>
<%@ page import="beans.Citta"%>

<html>
	<head>
		<script type="text/javascript" src="../scripts/utils.js"></script>
		<title>CYS Homepage</title>
	</head>

	<body>	
		<h1>Benvenuto!</h1>
		<%
			Utente u = (Utente)session.getAttribute("utente");
			if(u != null) { 
		%> 
		<h3>utente corrente: <%= u.toString() %></h3>
		<a href="../loginServlet?operazione=logoutUtente">logout</a>
		<%
			} //if( u != null)
			else {
		%>
		<a href="login.jsp">login</a>
		<%		
			} //else
		%>
	</body>
</html>