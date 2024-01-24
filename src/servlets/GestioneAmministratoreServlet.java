package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Spettacolo;
import beans.SupportoSpettacolo;
import dao.DAOFactory;

public class GestioneAmministratoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int DAO = DAOFactory.DB2;
	private DAOFactory daoFactory;
	
	@Override
	public void init() {
		daoFactory = DAOFactory.getDAOFactory(DAO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String operazione = request.getParameter("operazione");
		
		//dispatching
		switch (operazione) {
	        case "lista":
	        	request.removeAttribute("operazione");
	        	listaRevisionaSpettacoli(request, response);
	            break;
	        default:
	        	//in caso di errore ritorno alla homepage del pubblicatore
	        	response.sendRedirect("pages/homepage_amministratore.jsp?errore=operazione: "+operazione+" non supportata");
	    }
	}

	public void listaRevisionaSpettacoli(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<SupportoSpettacolo> toReview = daoFactory.getSupportoSpettacoloDAO().readAllSpettacoliDaRevisionare();
		session.setAttribute("listaRevisione", toReview);
		response.sendRedirect("pages/revisiona_spettacoli.jsp");
	}
	
}
