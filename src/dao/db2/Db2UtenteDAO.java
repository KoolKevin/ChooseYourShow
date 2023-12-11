package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Citta;
import beans.Utente;
import dao.UtenteDAO;

public class Db2UtenteDAO implements UtenteDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "utenti";
		private static final String ID = "id";
		private static final String USERNAME = "username";
		private static final String EMAIL = "email";
		private static final String PASSWORD = "password";
		private static final String FLAG_NOTIFICHE_APP = "flagnotificheapp";
		private static final String FLAG_NOTIFICHE_MAIL = "flagnotifichemail";
		private static final String FK_ID_CITTA = "idcitta";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+USERNAME+","+EMAIL+","+PASSWORD+","
																 +FLAG_NOTIFICHE_APP+","+FLAG_NOTIFICHE_MAIL+","+FK_ID_CITTA+")"
									   + " VALUES (?,?,?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String READ_BY_CREDENTIALS = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + EMAIL + " = ? "
			+"AND " + PASSWORD + " = ? ";

	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Utente utente) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (utente == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, utente.getUsername());
			prep_stmt.setString(2, utente.getEmail());
			prep_stmt.setString(3, utente.getPassword());
			//NB: le API di JDBC non prevedono un .setChar(), bisogna fare cosi
			prep_stmt.setString(4, String.valueOf(utente.getFlag_notifiche_app()) );	
			prep_stmt.setString(5, String.valueOf(utente.getFlag_notifiche_mail()) );
			prep_stmt.setInt(6, utente.getCitta().getId());

			prep_stmt.executeUpdate();

			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("create(): failed to insert entry in table "+ TABLE +": " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
	}

	@Override
	public Utente read(int id) {
		Utente result = null;
		int idCitta = -1;
		
		if (id < 0) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_ID);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			
			if (rs.next()) {
				Utente entry = new Utente();
				entry.setId(rs.getInt(ID));
				entry.setUsername(rs.getString(USERNAME));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				if( rs.getString(FLAG_NOTIFICHE_APP).compareTo("y") == 0) {
					entry.setFlag_notifiche_app(true);
				} 
				else {
					entry.setFlag_notifiche_app(false);
				}
				if( rs.getString(FLAG_NOTIFICHE_MAIL).compareTo("y") == 0) {
					entry.setFlag_notifiche_mail(true);
				} 
				else {
					entry.setFlag_notifiche_mail(false);
				}
				idCitta = rs.getInt(FK_ID_CITTA);
				
				//fetch eager: recupero subito la citta associata all'utente
				Db2CittaDAO cittaDAO = new Db2CittaDAO();
				Citta citta = cittaDAO.read(idCitta);
				
				entry.setCitta(citta);
				
				result = entry;
			}
				
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve entry with id = " + id + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}
	
	public Utente read(String email, String password) {
		Utente result = null;
		int idCitta = -1;
		
		if ( email.isEmpty() || password.isEmpty() ) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_CREDENTIALS);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, email);
			prep_stmt.setString(2, password);
			ResultSet rs = prep_stmt.executeQuery();
			
			if (rs.next()) {
				Utente entry = new Utente();
				entry.setId(rs.getInt(ID));
				entry.setUsername(rs.getString(USERNAME));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				if( rs.getString(FLAG_NOTIFICHE_APP).compareTo("y") == 0) {
					entry.setFlag_notifiche_app(true);
				} 
				else {
					entry.setFlag_notifiche_app(false);
				}
				if( rs.getString(FLAG_NOTIFICHE_MAIL).compareTo("y") == 0) {
					entry.setFlag_notifiche_mail(true);
				} 
				else {
					entry.setFlag_notifiche_mail(false);
				}
				idCitta = rs.getInt(FK_ID_CITTA);
				
				//fetch eager: recupero subito la citta associata all'utente
				//fetch eager: recupero subito la citta associata all'utente
				Db2CittaDAO cittaDAO = new Db2CittaDAO();
				Citta citta = cittaDAO.read(idCitta);
				
				entry.setCitta(citta);
				
				result = entry;
			}
				
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve entry with email = " + email+ ""
								+ " and password = "+ password +": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}
	
	//TODO: UPDATE e DELETE
	@Override
	public boolean update(Utente u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
