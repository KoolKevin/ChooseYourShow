<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="dao.*"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.List"%>

<%!
	public static final int DAO = DAOFactory.DB2;
	DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
	CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
	LocaleDAO localeDAO = daoFactoryInstance.getLocaleDAO();
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
   		
   		<!-- check per vedere se sono loggato -->
   		<%
   			if(p == null ) {
   				response.sendRedirect("login.jsp?errore=esegui il login");
   			}
   		%>
   		
   		<!-- pagina -->
		<div class="container">
	   		<h2>Pubblica uno spettacolo!</h2>
	   		<!-- TODO: controlla che almeno un campo sia popolato -->
	 		<form method="POST" action="../gestionePubblicatoreServlet">
	 			<!-- SEZIONE SPETTACOLO -->
	 			nome spettacolo: <input name="nome_spettacolo" type="text"/><br>
	 			data spettacolo: <input name="data_spettacolo" type="date"/><br>
	 			tipologia spettacolo: <input name="tipo_spettacolo" type="text"/><br>
	 			genere spettacolo: <input name="genere_spettacolo" type="text"/><br>
	 			città: <select name="citta_spettacolo">
				    <% for( Citta c : cittaDAO.readAll() ) {%>
				    	<option value="<%= c.getId() %>"><%= c.getNome() %></option>
				    <%} %>
				</select><br>	
				
				<!-- TODO: con ajax mostrare solo i locali della citta selezionata -->
	 			locale: <select name="locale_spettacolo">
				    <% for( beans.Locale l : localeDAO.readAll() ) {%>
				    	<option value="<%= l.getId() %>"><%= l.getNome() %></option>
				    <%} %>
				</select><br>		
				<!--
					 questo è sempre qullo del pubblicatore
					 mi sa che aveveva più senso fare si che un pubblicatore gestisse più artisti
				 -->	
	 			<!-- artista: <input name="artista" type="text"/><br> -->
	 			<input name="operazione" value="pubblica spettacolo" type="submit" />
	 		</form>
	 		
	 		<!-- risultati -->
	 		
	 		<div class="esito">
	 		<%
	 			Boolean pubblicazione = (Boolean)session.getAttribute("pubblicazione");
	 		
	 			if( pubblicazione!=null && pubblicazione ) {
	 		%>
	 			<p>pubblicazione avvenuta con successo</p>
	 		<%
	 			}
	 		%>
	 		</div>
	 		
	 		<a href="homepage_pubblicatore.jsp">homepage_pubblicatore</a>
	 	</div>
   </body>
</html>

