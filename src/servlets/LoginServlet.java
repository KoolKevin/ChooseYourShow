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
		UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Utente result = utenteDAO.read(email, password);
		
		//autenticazione fallita
		if( result == null ) {
			response.sendRedirect("pages/login.html");
		}
		//mi sono autenticato
		else {
			HttpSession session = request.getSession();
			session.setAttribute("utente", result);

			//forward
			response.sendRedirect("pages/homepage.jsp");
		}
	}
}
