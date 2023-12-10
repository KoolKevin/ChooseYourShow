package servlets;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utente;


public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
//		ServletContext ctx = getServletContext();
//		HttpSession session = request.getSession();
//		Utente user = (Utente) session.getAttribute("utenteLoggato");
//		
//		//rimuovo l'utente dalla mappa degli utenti loggati
//		Map<Utente, Boolean> mappaUtentiLoggati = (Map<Utente, Boolean>)ctx.getAttribute("mappaUtentiLoggati");
//		mappaUtentiLoggati.remove(user);
//		ctx.setAttribute("mappaUtentiLoggati", mappaUtentiLoggati);
//		
//		//controllo se è l'ultimo di un gruppo, nel caso elimino il carrello di quel gruppo
//		boolean trovato = false;
//		for(Utente u: mappaUtentiLoggati.keySet() ) {
//			if( u.getGroupId() == user.getGroupId() ) {
//				trovato=true;
//			}
//		}
//		
//		if(!trovato) {
//			ctx.removeAttribute("carrelloGruppo"+user.getGroupId());
//		}
//		
//		//rimuovo l'attributo nella sessione dell'utente e invalido quest'ultima 
//		session.removeAttribute("utenteLoggato");
//		session.invalidate();
//			
//		response.sendRedirect("pages/login.html");
		
	}
}
