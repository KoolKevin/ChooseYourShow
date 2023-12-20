<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="dao.DAOFactory"%>
<%@ page import="dao.CittaDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.Citta"%>

<%!
	public static final int DAO = DAOFactory.DB2;
	DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
	CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
%>

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
	   		<h2>Iscriviti!</h2>
	 		<form method="POST" action="../iscrizioneServlet">
	 			<h3>Dati pubblicatore</h3>
	 			Email: <input name="email" type="text"/><br>
	 			Password: <input name="password" type="password"/><br>
	 			Nome organizzazione: <input name="nome_organizzazione" type="text"/><br>
	 			Nome completo: <input name="nome_completo" type="text"/><br>
	 			<input name="notifiche_app" value="true" type="radio"/>Desidero notifiche tramite app<br>
	    		<input name="notifiche_mail" value="true" type="radio"/>Desidero notifiche tramite mail<br>	
	    		 
	    		 <h3>Dati artista gestito</h3>
	    		 Nome completo: <input name="nome_completo_artista" type="text"/><br>
	    		 Nome d'arte: <input name="nome_arte" type="text"/><br>
	    		 Biografia: <textarea class="form-control" name="biografia" style="height: 100px">...</textarea>
	    		 
	 			<input name="operazione" value="iscrizione_pubblicatore" type="submit" />
	 		</form>
	 		
	 		<a href="homepage_utente.jsp">vai alla homepage"</a>
	 		
	 		<!-- errore nel dispatching della iscrizione servlet -->
	 		<%
	 			String errore=request.getParameter("errore");
	 			if( errore != null ) {
	 		%>
	 		<div>c'è stato un errore nella iscrizione servlet: <%= errore %></div>
	 		<%
	 			} //if
	 		%>
 		</div>
   </body>
</html>

