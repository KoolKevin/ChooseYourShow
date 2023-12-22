package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Artista;
import beans.Locale;
import beans.Pubblicatore;
import beans.Spettacolo;
import beans.SupportoSpettacolo;
import dao.DAOFactory;
import dao.LocaleDAO;
import dao.SpettacoloArtistaMappingDAO;
import dao.SpettacoloDAO;
import dao.SupportoSpettacoloDAO;

public class GestionePubblicatoreServlet extends HttpServlet {
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
	        case "pubblica spettacolo":
	        	request.removeAttribute("operazione");
	        	pubblicaSpettacolo(request, response);
	            break;
	        default:
	        	//in caso di errore ritorno alla homepage del pubblicatore
	        	response.sendRedirect("pages/homepage_pubblicatore.jsp?errore=operazione: "+operazione+" non supportata");
	    }
	}
	
	public void pubblicaSpettacolo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		SpettacoloDAO spettacoloDAO = daoFactory.getSpettacoloDAO();
		//CittaDAO cittaDAO = daoFactory.getCittaDAO();
		LocaleDAO localeDAO = daoFactory.getLocaleDAO();
		SpettacoloArtistaMappingDAO saMappingDAO = daoFactory.getSpettacoloArtistaMappingDAO();
		SupportoSpettacoloDAO supportoDAO = daoFactory.getSupportoSpettacoloDAO();
		
		//acquisizione parametri ricevuti dal form
		//se il parametro non è specificato ottengo la stringa ""
		String nomeSpettacolo = request.getParameter("nome_spettacolo");
		String dataSpettacolo = request.getParameter("data_spettacolo");
		String tipoSpettacolo = request.getParameter("tipo_spettacolo");
		String genereSpettacolo = request.getParameter("genere_spettacolo");
		//int idCitta = Integer.parseInt( request.getParameter("citta_spettacolo") );
		int idLocale = Integer.parseInt( request.getParameter("locale_spettacolo") );
		//recupero di altre informazioni
		Pubblicatore pubblicatore = (Pubblicatore)session.getAttribute("pubblicatore");
		Locale locale = localeDAO.read(idLocale);
		Artista artista = pubblicatore.getArtistaGestito();
		
		//costruisco e creo lo spettacolo
		Spettacolo spettacolo = new Spettacolo();
		spettacolo.setNome(nomeSpettacolo);
		spettacolo.setData( Date.valueOf(LocalDate.parse(dataSpettacolo)) );
		spettacolo.setTipologia(tipoSpettacolo);
		String generi[] = new String[5];
		generi[0] = genereSpettacolo;
		spettacolo.setGeneri(generi);
		spettacolo.setLocale(locale);
		spettacoloDAO.create(spettacolo);

		//creazione del supporto
		SupportoSpettacolo supporto = new SupportoSpettacolo();
		supporto.setPubblicatore(pubblicatore);
		supporto.setSpettacoloNonApprovato(spettacolo);
		supportoDAO.create(supporto);
		//mapping con l'artista
		saMappingDAO.create(spettacolo.getId(), artista.getId());
		
		session.setAttribute("pubblicazione", true);
		//forward
		response.sendRedirect("pages/pubblica_spettacolo.jsp");
	}
}
