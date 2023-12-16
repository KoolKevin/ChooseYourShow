package test;

import java.time.LocalDate;
import java.sql.Date;

import beans.Citta;
import beans.Locale;
import beans.RicercaSpettacolo;
import beans.Spettacolo;
import beans.Utente;
import dao.CittaDAO;
import dao.DAOFactory;
import dao.LocaleDAO;
import dao.SpettacoloDAO;
import dao.UtenteDAO;

public class MainPerTest {
	public static final int DAO = DAOFactory.DB2;
	// classe main per fare test
	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		
		CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
		Citta c = cittaDAO.read(21);
		
		LocaleDAO localeDAO = daoFactoryInstance.getLocaleDAO();
		Locale l = new Locale();
		l.setNome("arena di verona");
		l.setCitta(c);
		//localeDAO.create(l);
		
		l = localeDAO.read(4);
		
		System.out.println( l );
		
		SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
		Spettacolo s = new Spettacolo();
		s.setNome("spettacolo bello");
		s.setData(Date.valueOf(LocalDate.of(2018, 1, 14)));
		s.setTipologia("figata");
		s.getGeneri()[0] = "bello";
		s.getGeneri()[1] = "rock";
		s.setLocale(l);
		//spettacoloDAO.create(s);
		
		System.out.println( spettacoloDAO.read(4) );
		
		RicercaSpettacolo r = new RicercaSpettacolo();
		r.setGenereSpettacolo("metal");
		//r.setNomeSpettacolo("slayer");
		r.setInizioPeriodo(Date.valueOf(LocalDate.of(2018, 1, 14)));
		System.out.println(spettacoloDAO.cercaSpettacoli(r));
		
//		CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
//		Citta c = new Citta();
//		c.setNome("Bologna");
//		c.setCoordinate("boh");
//		cittaDAO.create(c);
//		
//		c = cittaDAO.read(21);
//		System.out.println(c);
//		
//		UtenteDAO utenteDAO = daoFactoryInstance.getUtenteDAO();
//		Utente u1 = new Utente();
//		u1.setUsername("mario");
//		u1.setEmail("mario@gmail.com");
//		u1.setPassword("pippo");
//		u1.setFlag_notifiche_app(false);
//		u1.setFlag_notifiche_mail(false);
//		u1.setCitta(c);
//		//utenteDAO.create(u1);
//		
//		Utente u2 = new Utente();
//		u2.setUsername("mirio");
//		u2.setEmail("mirio@gmail.com");
//		u2.setPassword("pluto");
//		u2.setFlag_notifiche_app(true);
//		u2.setFlag_notifiche_mail(true);
//		u2.setCitta(c);
//		//utenteDAO.create(u2);
//
//		Utente utente_letto = utenteDAO.read(4);
//		System.out.println(utente_letto);
//		
//		Utente utente_loggato = utenteDAO.read("mirio@gmail.com", "pluto");
//		System.out.println(utente_loggato);
//
//		utente_loggato = utenteDAO.read("mio@gmail.com", "pluto");
//		System.out.println(utente_loggato);
	}
}
