<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="beans.Amministratore"%>

<html>
	<head>
		<script type="text/javascript" src="../scripts/utils.js"></script>
		<!-- bootstrap -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
		
		<title>CYS Amministratori</title>
	</head>

	<body>	
		<%@ include file="../fragments/navbar.jsp" %>
		
		<!-- pagina -->
		<div class="container">
			<h1>Benvenuto!</h1>
			<%
				//u dichiarato nella navbar
				a = (Amministratore)session.getAttribute("amministratore");
				if(a != null) { 
			%> 
			<h3>amministratore corrente: <%= a.toString() %></h3>
			<a href="../loginServlet?operazione=logout">logout</a>
			<%
				} //if( u != null)
				else {
			%>
			<a href="login.jsp">login</a>
			<%		
				} //else
			%>
			
			<br>
			<a href="../gestioneAmministratoreServlet?operazione=lista">revisiona spettacoli</a>
			
			<!-- errore nel dispatching della  GestioneUtenteServlet -->
	 		<%
	 			String errore=request.getParameter("errore");
	 			if( errore != null ) {
	 		%>
	 		<div>c'è stato un errore nella GestioneUtenteServlet: <%= errore %></div>
	 		<%
	 			} //if
	 		%>
	 	</div>
	</body>
</html>