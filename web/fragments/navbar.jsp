<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="beans.Utente"%>
<%@ page import="beans.Pubblicatore"%>

<%
	Utente u = (Utente)session.getAttribute("utente");
	Pubblicatore p = (Pubblicatore)session.getAttribute("pubblicatore");
	if(u != null) { 
%> 
	<!-- utente loggato -->
	<div class="container">
	    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
	      <div class="col-md-3 mb-2 mb-md-0">
	        <a href="/ChooseYourShow/pages/homepage_utente.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<img src="/ChooseYourShow/images/thanos-gangnam-style.gif" alt="mdo" width="48" height="48"></img>
	        	<span>CYS-UTENTI</span>
	        </a>
	      </div>
	
	      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
	        <li><a href="/ChooseYourShow/pages/homepage_utente.jsp" class="nav-link px-2 link-secondary">Home</a></li>
	        <li><a href="/ChooseYourShow/pages/ricerca.jsp" class="nav-link px-2">Cerca</a></li>
	        <li><a href="#" class="nav-link px-2">Confronta</a></li>
	        <li><a href="#" class="nav-link px-2">Compra</a></li>
	      </ul>
	
	      <div class="col-md-3 text-end">
	     	<a href="/ChooseYourShow/pages/login.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-outline-primary me-2">Login</button>
	       	</a>
	       	<a href="/ChooseYourShow/pages/iscrizioneUtente.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-primary">Sign-up</button>
	        </a>
	      </div>
	    </header>
	</div>
<%
	} else if(p != null) {
%>
	<!-- pubblicatore loggato -->
	<div class="container">
	    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
	      <div class="col-md-3 mb-2 mb-md-0">
	        <a href="/ChooseYourShow/pages/homepage_pubblicatore.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<img src="/ChooseYourShow/images/thanos-gangnam-style.gif" alt="mdo" width="48" height="48"></img>
	        	<span>CYS-Pubblicatori</span>
	        </a>
	      </div>
	
	      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
	        <li><a href="/ChooseYourShow/pages/homepage_pubblicatore.jsp" class="nav-link px-2 link-secondary">Home</a></li>
	        <li><a href="/ChooseYourShow/pages/pubblica_spettacolo.jsp" class="nav-link px-2">pubblica</a></li>
	        <li><a href="#" class="nav-link px-2">modifica</a></li>
	      </ul>
	
	      <div class="col-md-3 text-end">
	     	<a href="/ChooseYourShow/pages/login.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-outline-primary me-2">Login</button>
	       	</a>
	       	<a href="/ChooseYourShow/pages/iscrizionePubblicatore.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-primary">Sign-up</button>
	        </a>
	      </div>
	    </header>
	</div>
<%
	} else {
%>
	<!-- Utente non loggato -->
	<div class="container">
	    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
	      <div class="col-md-3 mb-2 mb-md-0">
	        <a href="/ChooseYourShow/pages/homepage_utente.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<img src="/ChooseYourShow/images/thanos-gangnam-style.gif" alt="mdo" width="48" height="48"></img>
	        	<span>CYS</span>
	        </a>
	      </div>
	
	      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
	        <li><a href="/ChooseYourShow/pages/homepage_utente.jsp" class="nav-link px-2 link-secondary">Home</a></li>
	        <li><a href="/ChooseYourShow/pages/ricerca.jsp" class="nav-link px-2">Cerca</a></li>
	        <li><a href="#" class="nav-link px-2">Confronta</a></li>
	      </ul>
	
	      <div class="col-md-3 text-end">
	     	<a href="/ChooseYourShow/pages/login.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-outline-primary me-2">Login</button>
	       	</a>
	       	<a href="/ChooseYourShow/pages/iscrizioneUtente.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-primary">Sign-up utente</button>
	        </a>
	        <a href="/ChooseYourShow/pages/iscrizionePubblicatore.jsp" class="d-inline-flex link-body-emphasis text-decoration-none">
	        	<button type="button" class="btn btn-primary">Sign-up pubblicatore</button>
	        </a>
	      </div>
	    </header>
	</div>
<%
	}
%>

