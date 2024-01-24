<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

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
   		
   		<!-- pagina -->
		<div class="container">
	   		<h2>Cerca uno spettacolo!</h2>
	   		<!-- TODO: controlla che almeno un campo sia popolato -->
	 		<form method="POST" action="../gestioneUtenteServlet">
	 			nome spettacolo: <input name="nome_spettacolo" type="text"/><br>
	 			nome artista: <input name="nome_artista" type="text"/><br>
	 			data spettacolo: <input name="data_spettacolo" type="date"/><br>
	 			inizio periodo: <input name="inizio_periodo" type="date"/><br>
	 			fine periodo: <input name="fine_periodo" type="date"/><br>
	 			<!-- questi due dovevano essere degli enumerativi ma per semplicità ho lasciato come seplice text -->
	 			tipologia spettacolo: <input name="tipo_spettacolo" type="text"/><br>
	 			genere spettacolo: <input name="genere_spettacolo" type="text"/><br>
	 			<!-- TODO: questi due renderli dei menu a tendina popolati con ajax -->
	 			citta: <input name="citta_spettacolo" type="text"/><br>
	 			locale: <input name="locale_spettacolo" type="text"/><br>
	 			<input name="operazione" value="cerca spettacolo" type="submit" />
	 		</form>
	 		
	 		<!-- risultati -->
	 		
	 		<h2>Risultati:</h2>
	 		<div class="risultati">
	 		<%
	 			List<Spettacolo> risultati = (List<Spettacolo>)session.getAttribute("risultati");
	 			if( risultati == null || risultati.isEmpty() ) {
	 		%>
	 			<p>nessun risultato</p>
	 		<%
	 			}
	 			else {
	 				for( Spettacolo s : risultati) {
	 		%>
	 			<div class="risultato">
	 				<p><%= s.toString() %></p>
	 			</div>
	 		<%
	 				} // for
	 			}//else
	 		%>
	 		</div>
	 		<a href="homepage_utente.jsp">homepage_utente</a>
	 	</div>
   </body>
</html>

