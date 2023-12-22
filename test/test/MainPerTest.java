package test;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

import beans.*;
import dao.*;

public class MainPerTest {
	public static final int DAO = DAOFactory.DB2;
	// classe main per fare test
	public static void main(String[] args) {
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
		BigliettoDAO bigliettoDAO = daoFactoryInstance.getBigliettoDAO();
		LocaleDAO localeDAO = daoFactoryInstance.getLocaleDAO();
		SettoreDAO settoreDAO = daoFactoryInstance.getSettoreDAO();
		PostoDAO postoDAO = daoFactoryInstance.getPostoDAO();
		
		Spettacolo s = spettacoloDAO.read(2);
		s.getBiglietti();
		
		Locale l = localeDAO.read(1);
		l.getSettori();
		
		Settore set = new Settore();
		set.setNome("parterre");
		set.setDescrizione("parterre");
		set.setCapienza(50);
		set.setLocale(l);
		//settoreDAO.create(set);
		set = settoreDAO.read(1);
		System.out.println(set);
		
		Biglietto b = new Biglietto();
		b.setDescrizione("biglietto SFIGATO");
		b.setBigliettiDisponibili(300);
		b.setCosto(20.50F);
		b.setSpettacolo(s);
		b.setSettore(set);
		bigliettoDAO.create(b);
		
		System.out.println(bigliettoDAO.read(2));
				
		Posto p = new Posto();
		p.setFila("A");
		p.setSedia("00");
		p.setOccupato(true);
		p.setSettore(set);
		//postoDAO.create(p);
		System.out.println(postoDAO.read(2));
		
		System.out.println(localeDAO.readAll());
		
		
/* ------------------------- TEST VECCHI ------------------------*/ 	
//		SupportoSpettacoloDAO supportoSpettacoloDAO = daoFactoryInstance.getSupportoSpettacoloDAO();
//		SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
//		PubblicatoreDAO pubblicatoreDAO = daoFactoryInstance.getPubblicatoreDAO();
//
//		Pubblicatore p = pubblicatoreDAO.read(1);
//		//System.out.println(p);
//		p.getSupportiSpettacoliPubblicati();
//		//System.out.println(p);
//		
//		Spettacolo s = p.getArtistaGestito().getSpettacoli().get(0);
//		s.getSupporto();
//		
//		SupportoSpettacolo ss = new SupportoSpettacolo();
//		ss.setPubblicatore(p);
//		ss.setSpettacoloNonApprovato(s);
//		supportoSpettacoloDAO.create(ss);
		
		//System.out.println( supportoSpettacoloDAO.readSupportiOfPubblicatore(p.getId()) );
//		ArtistaDAO artistaDAO = daoFactoryInstance.getArtistaDAO();
//		
//		Artista a = artistaDAO.read(1);
//		
//		PubblicatoreDAO pubblicatoreDAO = daoFactoryInstance.getPubblicatoreDAO();
//		Pubblicatore p = new Pubblicatore();
//		p.setEmail("kevin@gmail.com");
//		p.setPassword("pubblicatore");
//		p.setNomeOrganizzazione("cattiveria music");
//		p.setNomeCompleto("Koltraka Kevin");
//		p.setAutorizzato(false);
//		p.setFlag_notifiche_app(true);
//		p.setFlag_notifiche_mail(true);
//		p.setArtistaGestito(a);
//		//pubblicatoreDAO.create(p);
//		System.out.println( pubblicatoreDAO.read(1) );
//		System.out.println( pubblicatoreDAO.read("kevin@gmail.com", "pubblicatore") );
//	
//		CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
//		System.out.println( cittaDAO.readAll() );
//		
//		Artista a_creato = new Artista();
//		a_creato.setNomeCompleto("Napoleone Bonaparte");
//		a_creato.setNomeArte("lil uzi");
//		a_creato.setBiografia("fuoco");
//		//artistaDAO.create(a_creato);
//		System.out.println(a_creato);
//	    SpettacoloArtistaMappingDAO spettacoloArtistaMappingDAO = daoFactoryInstance.getSpettacoloArtistaMappingDAO();
//	    SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
//		ArtistaDAO artistaDAO = daoFactoryInstance.getArtistaDAO();
//	
//		Artista a = artistaDAO.read(1);
//		Spettacolo s = spettacoloDAO.read(4);
//		System.out.println(a);
//		System.out.println(s);
//		System.out.println(a.getSpettacoli());
//		System.out.println(s.getArtisti());
//		//spettacoloArtistaMappingDAO.create(4, 23);
//		//spettacoloArtistaMappingDAO.delete(4, 1);
//		
//		RicercaSpettacolo r = new RicercaSpettacolo();
//		r.setNomeArtista("foo");
//		//r.setGenereSpettacolo("metal");
//		//r.setNomeSpettacolo("slayer");
//		//r.setInizioPeriodo(Date.valueOf(LocalDate.of(2018, 1, 14)));
//		
//		List<Spettacolo> spettacoli = spettacoloDAO.cercaSpettacoli(r);
//		for(Spettacolo spect : spettacoli) {
//			System.out.println( spect );
//			spect.getArtisti();
//			System.out.println( spect );
//		}
		
//		CittaDAO cittaDAO = daoFactoryInstance.getCittaDAO();
//		Citta c = cittaDAO.read(21);
//		
//		LocaleDAO localeDAO = daoFactoryInstance.getLocaleDAO();
//		Locale l = new Locale();
//		l.setNome("arena di verona");
//		l.setCitta(c);
//		//localeDAO.create(l);
//		
//		l = localeDAO.read(4);
//		
//		System.out.println( l );
//		
//		SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
//		Spettacolo s = new Spettacolo();
//		s.setNome("spettacolo bello");
//		s.setData(Date.valueOf(LocalDate.of(2018, 1, 14)));
//		s.setTipologia("figata");
//		s.getGeneri()[0] = "bello";
//		s.getGeneri()[1] = "rock";
//		s.setLocale(l);
//		//spettacoloDAO.create(s);
//		
//		System.out.println( spettacoloDAO.read(4) );
//		
//		RicercaSpettacolo r = new RicercaSpettacolo();
//		r.setGenereSpettacolo("metal");
//		//r.setNomeSpettacolo("slayer");
//		r.setInizioPeriodo(Date.valueOf(LocalDate.of(2018, 1, 14)));
//		System.out.println(spettacoloDAO.cercaSpettacoli(r));
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
