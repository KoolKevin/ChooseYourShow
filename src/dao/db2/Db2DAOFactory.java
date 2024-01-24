package dao.db2;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.*;


public class Db2DAOFactory extends DAOFactory {


	public static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	public static final String DBURL = "jdbc:db2://diva.deis.unibo.it:50000/is_stud";
	//NON CAMBIARE ALTRIMENTI NON POTETE ACCEDERE ALLO SCHEMA DI KEVIN
	public static final String USERNAME = "A0989712";
	public static final String PASSWORD = "Gambadilegno11";

	// --------------------------------------------

	// static initializer block to load db driver class in memory
	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName()+": failed to load DB2 JDBC driver" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------- METODI PER OTTENERE/CHIUDERE UNA CONNESSIONE AL DB -----------------------------

	public static Connection createConnection() {
		try {
			return DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		} 
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".createConnection(): failed creating connection" + "\n" + e.toString());
			e.printStackTrace();
			System.err.println("Was the database started? Is the database URL right?");
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".closeConnection(): failed closing connection" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// ------------------ METODI CHE RITORNANO I DAO --------------------------
	
	@Override
	public UtenteDAO getUtenteDAO() {
		return new Db2UtenteDAO();
	}
	@Override
	public CittaDAO getCittaDAO() {
		return new Db2CittaDAO();
	}
	@Override
	public LocaleDAO getLocaleDAO() {
		return new Db2LocaleDAO();
	}
	@Override
	public SpettacoloDAO getSpettacoloDAO() {
		return new Db2SpettacoloDAO();
	}

	@Override
	public ArtistaDAO getArtistaDAO() {
		return new Db2ArtistaDAO();
	}
	@Override
	public SpettacoloArtistaMappingDAO getSpettacoloArtistaMappingDAO() {
		return new Db2SpettacoloArtistaMappingDAO();
	}
	
	@Override
	public PubblicatoreDAO getPubblicatoreDAO() {
		return new Db2PubblicatoreDAO();
	}
	
	@Override
	public SupportoSpettacoloDAO getSupportoSpettacoloDAO() {
		return new Db2SupportoSpettacoloDAO();
	}
	
	@Override
	public BigliettoDAO getBigliettoDAO() {
		return new Db2BigliettoDAO();
	}
	
	@Override
	public SettoreDAO getSettoreDAO() {
		return new Db2SettoreDAO();
	}
	
	@Override
	public PostoDAO getPostoDAO() {
		return new Db2PostoDAO();
	}
	
	public AmministratoreDAO getAmministratoreDAO() {
		return new Db2AmministratoreDAO();
	}
}
