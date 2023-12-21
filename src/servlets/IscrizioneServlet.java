package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Artista;
import beans.Citta;
import beans.Pubblicatore;
import beans.Utente;
import dao.ArtistaDAO;
import dao.DAOFactory;
import dao.PubblicatoreDAO;
import dao.UtenteDAO;

public class IscrizioneServlet extends HttpServlet {
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
	        case "iscrizione_utente":
	        	request.removeAttribute("operazione");
	        	iscrizioneUtente(request, response);
	        	
	            break;
	        case "iscrizione_pubblicatore":
	        	request.removeAttribute("operazione");
	        	iscrizionePubblicatore(request, response);
	            break;
	        default:
	        	//nella loginServlet in caso di errore ritorno al login
	        	response.sendRedirect("pages/homepage_utente.jsp?errore=operazione: "+operazione+" non supportata");
	    }
	}
	
	public void iscrizioneUtente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
		
		//recupero le informazioni dal form
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int idCitta = Integer.parseInt(request.getParameter("id_citta"));
		String notificheApp = request.getParameter("notifiche_app");
		String notificheMail = request.getParameter("notifiche_mail");
		
		//costruisco l'utente da salvare
		Utente result = new Utente();
		result.setUsername(username);
		result.setEmail(email);
		result.setPassword(password);
		Citta c = new Citta();
		c.setId(idCitta);
		result.setCitta(c);	//mi basta solo l'id dato che ho bisogno solo di una FK
		if(notificheApp != null) {
			result.setFlag_notifiche_app(true);
		}
		else {
			result.setFlag_notifiche_app(false);
		}
		if(notificheMail != null) {
			result.setFlag_notifiche_mail(true);
		}
		else {
			result.setFlag_notifiche_mail(false);
		}
			
		utenteDAO.create(result);
		
		//forward alla login servlet che mi autentica dopo l'iscrizione
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginServlet");	
		request.setAttribute("operazione", "login");
		request.setAttribute("categoria_utente", "utente");
		//email e password ci sono gia
		rd.forward(request, response);
		
	}
	
	public void iscrizionePubblicatore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ArtistaDAO artistaDAO = daoFactory.getArtistaDAO();
		PubblicatoreDAO pubblicatoreDAO = daoFactory.getPubblicatoreDAO();
		
		//recupero le informazioni dal form
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nomeOrganizzazione = request.getParameter("nome_organizzazione");
		String nomeCompleto = request.getParameter("nome_completo");
		String notificheApp = request.getParameter("notifiche_app");
		String notificheMail = request.getParameter("notifiche_mail");
		
		String nomeCompletoArtista = request.getParameter("nome_completo_artista");
		String nomeArte = request.getParameter("nome_arte");
		String biografia = request.getParameter("biografia");
		
		//costruisco l'artista da salvare e associare al pubblicatore
		Artista artistaGestito = new Artista();
		artistaGestito.setNomeCompleto(nomeCompletoArtista);
		artistaGestito.setNomeArte(nomeArte);
		artistaGestito.setBiografia(biografia);
		
		artistaDAO.create(artistaGestito); 
		
		//costruisco il pubblicatore
		Pubblicatore nuovoPubblicatore = new Pubblicatore();
		nuovoPubblicatore.setEmail(email);
		nuovoPubblicatore.setPassword(password);
		nuovoPubblicatore.setNomeOrganizzazione(nomeOrganizzazione);
		nuovoPubblicatore.setNomeCompleto(nomeCompleto);
		if(notificheApp != null) {
			nuovoPubblicatore.setFlag_notifiche_app(true);
		}
		else {
			nuovoPubblicatore.setFlag_notifiche_app(false);
		}
		if(notificheMail != null) {
			nuovoPubblicatore.setFlag_notifiche_mail(true);
		}
		else {
			nuovoPubblicatore.setFlag_notifiche_mail(false);
		}
		nuovoPubblicatore.setAutorizzato(false);
		nuovoPubblicatore.setArtistaGestito(artistaGestito);
		
		pubblicatoreDAO.create(nuovoPubblicatore);
		
		//forward alla login servlet che mi autentica dopo l'iscrizione
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginServlet");	
		request.setAttribute("operazione", "login");
		request.setAttribute("categoria_utente", "pubblicatore");
		//email e password ci sono gia
		rd.forward(request, response);
	}
}
