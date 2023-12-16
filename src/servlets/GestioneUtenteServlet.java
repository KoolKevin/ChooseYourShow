package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.RicercaSpettacolo;
import beans.Spettacolo;
import dao.DAOFactory;
import dao.SpettacoloDAO;

public class GestioneUtenteServlet extends HttpServlet {
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
	        case "cerca spettacolo":
	        	request.removeAttribute("operazione");
	        	cercaSpettacolo(request, response);
	            break;
	        default:
	        	//in caso di errore ritorno alla homepage dell'utente
	        	response.sendRedirect("pages/homepage_utente.jsp?errore=operazione: "+operazione+" non supportata");
	    }
	}
	
	public void cercaSpettacolo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SpettacoloDAO spettacoloDAO = daoFactory.getSpettacoloDAO();
		//acquisizione parametri ricevuti
		//se il parametro non è specificato ottengo la stringa ""
		String nomeSpettacolo = request.getParameter("nome_spettacolo");
		String dataSpettacolo = request.getParameter("data_spettacolo");
		String inizioPeriodo = request.getParameter("inizio_periodo");
		String finePeriodo = request.getParameter("fine_periodo");
		String tipoSpettacolo = request.getParameter("tipo_spettacolo");
		String genereSpettacolo = request.getParameter("genere_spettacolo");
		String nomeCitta = request.getParameter("citta_spettacolo");
		String nomeLocale = request.getParameter("locale_spettacolo");
		
		//costruisco l'oggetto ricerca
		RicercaSpettacolo ricercaSpettacolo = new RicercaSpettacolo();
		if( !nomeSpettacolo.isEmpty() )
			ricercaSpettacolo.setNomeSpettacolo(nomeSpettacolo);
		if( !dataSpettacolo.isEmpty() )
			ricercaSpettacolo.setDataSpettacolo( Date.valueOf(LocalDate.parse(dataSpettacolo)) );
		if( !inizioPeriodo.isEmpty() )
			ricercaSpettacolo.setInizioPeriodo( Date.valueOf(LocalDate.parse(inizioPeriodo)) );
		if( !finePeriodo.isEmpty() )
			ricercaSpettacolo.setFinePeriodo( Date.valueOf(LocalDate.parse(finePeriodo)) );
		if( !tipoSpettacolo.isEmpty() )
			ricercaSpettacolo.setTipoSpettacolo(tipoSpettacolo);
		if( !genereSpettacolo.isEmpty() )
			ricercaSpettacolo.setGenereSpettacolo(genereSpettacolo);
		if( !nomeCitta.isEmpty() )
			ricercaSpettacolo.setNomeCitta(nomeCitta);
		if( !nomeLocale.isEmpty() )
			ricercaSpettacolo.setNomeLocale(nomeLocale);
		
		//restituisco il risultato
		List<Spettacolo> risultati = spettacoloDAO.cercaSpettacoli(ricercaSpettacolo);
		
		HttpSession session = request.getSession();
		session.setAttribute("risultati", risultati);
		//forward
		response.sendRedirect("pages/ricerca.jsp");
	}
}
