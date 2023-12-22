package dao;

import dao.db2.Db2DAOFactory;

public abstract class DAOFactory {

	// --- List of supported DAO types ---
	public static final int DB2 = 0;
	
	// --- Actual factory method ---
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
			case DB2:
				return new Db2DAOFactory();
			default:
				return null;
		}
	}
	
	// --- Factory specification: concrete factories implementing this spec must provide this methods! ---
	public abstract UtenteDAO getUtenteDAO();
	public abstract CittaDAO getCittaDAO();
	public abstract LocaleDAO getLocaleDAO();
	public abstract SpettacoloDAO getSpettacoloDAO();
	public abstract ArtistaDAO getArtistaDAO();
	public abstract SpettacoloArtistaMappingDAO getSpettacoloArtistaMappingDAO();
	public abstract BigliettoDAO getBigliettoDAO();
	public abstract SettoreDAO getSettoreDAO();
	public abstract PostoDAO getPostoDAO();
	public abstract PubblicatoreDAO getPubblicatoreDAO();
	public abstract SupportoSpettacoloDAO getSupportoSpettacoloDAO();
	
}
