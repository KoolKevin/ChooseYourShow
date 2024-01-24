package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Amministratore;
import beans.Pubblicatore;
import beans.Utente;
import dao.AmministratoreDAO;

import dao.DAOFactory;
import dao.PubblicatoreDAO;
import dao.UtenteDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int DAO = DAOFactory.DB2;
	private DAOFactory daoFactory;
	
	@Override
	public void init() {
		daoFactory = DAOFactory.getDAOFactory(DAO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * In questo 3 righe sotto uso un hack per essere sicuro di ottenere l'operazione giusta:
		 * Se la servlet viene chiamata da un form i parametri possono essere recuperati solo con request.getParameter().
		 * Se la servlet viene chiamata da un'altra servlet con un forward i parametri possono essere recuperati solo
		 * con request.getAttribute().
		 * 
		 * Dato che la servlet viene chiamata in tutti e due i modi devo fare come scritto sotto.
		 * Brutto, non so perchè hanno separato i due casi >:(
		 */
		String operazione = (String)request.getAttribute("operazione");
		if( operazione == null ) {
			operazione = request.getParameter("operazione");
		}
		
		//dispatching
		switch(operazione) {
	        case "login":
	        	request.removeAttribute("operazione");
	        	//stessa cosa per categoria_utente come per operazione
	        	String categoria_utente = (String)request.getAttribute("categoria_utente");
	        	if( categoria_utente == null ) {
	        		categoria_utente = request.getParameter("categoria_utente");
	    		}
	        	
	        	if( categoria_utente.equals("utente") ) {
	        		loginUtente(request, response);
	        	} else if( categoria_utente.equals("pubblicatore") ) {
	        		loginPubblicatore(request, response);
	        	} else if( categoria_utente.equals("amministratore") ) {
	        		loginAmministratore(request, response);
	        		System.out.println("amministratore");
	        	} else {
	        		response.sendRedirect("pages/login.jsp?errore=categoria utente misteriosa");
	        	}
	        	
	            break;
	        case "logout":
	        	request.removeAttribute("operazione");
	        	logout(request, response);
	            break;
	        default:
	        	//nella loginServlet in caso di errore ritorno al login
	        	response.sendRedirect("pages/login.jsp?errore=operazione: "+operazione+" non supportata");
	    }
	}
	
	public void loginUtente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Utente result = utenteDAO.read(email, password);
		
		//autenticazione fallita
		if( result == null ) {
			response.sendRedirect("pages/login.jsp?logged_in=false");
		}
		//mi sono autenticato
		else {
			HttpSession session = request.getSession();
			session.setAttribute("utente", result);

			//forward
			response.sendRedirect("pages/homepage_utente.jsp");
		}
	}
	
	public void loginPubblicatore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PubblicatoreDAO pubblicatoreDAO = daoFactory.getPubblicatoreDAO();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Pubblicatore result = pubblicatoreDAO.read(email, password);
		
		//autenticazione fallita
		if( result == null ) {
			response.sendRedirect("pages/login.jsp?logged_in=false");
		}
		//mi sono autenticato
		else {
			HttpSession session = request.getSession();
			session.setAttribute("pubblicatore", result);

			//forward
			response.sendRedirect("pages/homepage_pubblicatore.jsp");
		}
	}
	
	public void loginAmministratore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AmministratoreDAO amministratoreDAO = daoFactory.getAmministratoreDAO();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Amministratore result = amministratoreDAO.read(email, password);
		//autenticazione fallita
		if( result == null ) {
			response.sendRedirect("pages/login.jsp?logged_in=false");
		}
		//mi sono autenticato
		else {
			HttpSession session = request.getSession();
			session.setAttribute("amministratore", result);

			//forward
			response.sendRedirect("pages/homepage_amministratore.jsp");
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().invalidate();
		response.sendRedirect("pages/login.jsp?logged_in=logged_out");
	}
}
