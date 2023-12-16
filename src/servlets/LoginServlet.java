package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utente;
import dao.DAOFactory;
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
		String operazione = request.getParameter("operazione");
		
		//dispatching
		switch (operazione) {
	        case "loginUtente":
	        	request.removeAttribute("operazione");
	        	loginUtente(request, response);
	            break;
	        case "logoutUtente":
	        	request.removeAttribute("operazione");
	        	logoutUtente(request, response);
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
	
	public void logoutUtente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().invalidate();
		response.sendRedirect("pages/login.jsp?logged_in=logged_out");
	}
}
