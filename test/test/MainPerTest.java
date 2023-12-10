package test;

import beans.Citta;
import beans.Utente;
import dao.DAOFactory;
import dao.UtenteDAO;

public class MainPerTest {
	public static final int DAO = DAOFactory.DB2;
	// classe main per fare test
	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		
		UtenteDAO utenteDAO = daoFactoryInstance.getUtenteDAO();
		
		Citta c = new Citta();
		c.setId(1);
		
		Utente u1 = new Utente();
		u1.setUsername("mario");
		u1.setEmail("mario@gmail.com");
		u1.setPassword("pippo");
		u1.setFlag_notifiche_app(false);
		u1.setFlag_notifiche_mail(false);
		u1.setCitta(c);
		//utenteDAO.create(u1);
		
		Utente u2 = new Utente();
		u2.setUsername("mirio");
		u2.setEmail("mirio@gmail.com");
		u2.setPassword("pluto");
		u2.setFlag_notifiche_app(true);
		u2.setFlag_notifiche_mail(true);
		u2.setCitta(c);
		//utenteDAO.create(u2);

		Utente utente_letto = utenteDAO.read(4);
		//System.out.println(utente_letto);
		
		Utente utente_loggato = utenteDAO.read("mirio@gmail.com", "pluto");
		System.out.println(utente_loggato);

		utente_loggato = utenteDAO.read("mio@gmail.com", "pluto");
		System.out.println(utente_loggato);
	}
}
