package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Artista;
import beans.Pubblicatore;
import dao.PubblicatoreDAO;

public class Db2PubblicatoreDAO implements PubblicatoreDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "pubblicatori";
		private static final String ID = "id";
		private static final String EMAIL = "email";
		private static final String PASSWORD = "password";
		private static final String NOME_ORGANIZZAZIONE = "nomeOrganizzazione";
		private static final String NOME_COMPLETO = "nomeCompleto";
		private static final String IS_AUTORIZZATO = "isAutorizzato";
		private static final String FLAG_NOTIFICHE_APP = "flagNotificaApp";
		private static final String FLAG_NOTIFICHE_MAIL = "flagNotificaEmail";
		private static final String FK_ID_ARTISTA = "idArtista";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+EMAIL+","+PASSWORD+","+NOME_ORGANIZZAZIONE+","
																 +NOME_COMPLETO+","+IS_AUTORIZZATO+","
																 +FLAG_NOTIFICHE_APP+","+FLAG_NOTIFICHE_MAIL+","+FK_ID_ARTISTA+")"
									   + " VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String READ_BY_CREDENTIALS = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + EMAIL + " = ? "
			+"AND " + PASSWORD + " = ? ";

	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Pubblicatore pubblicatore) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (pubblicatore == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, pubblicatore.getEmail());
			prep_stmt.setString(2, pubblicatore.getPassword());
			prep_stmt.setString(3, pubblicatore.getNomeOrganizzazione());
			prep_stmt.setString(4, pubblicatore.getNomeCompleto());
			//NB: le API di JDBC non prevedono un .setChar(), bisogna fare cosi
			prep_stmt.setString(5, String.valueOf(pubblicatore.getAutorizzato()) );
			prep_stmt.setString(6, String.valueOf(pubblicatore.getFlag_notifiche_app()) );	
			prep_stmt.setString(7, String.valueOf(pubblicatore.getFlag_notifiche_mail()) );
			prep_stmt.setInt(8, pubblicatore.getArtistaGestito().getId());

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
	public Pubblicatore read(int id) {
		Pubblicatore result = null;
		int idArtista = -1;
		
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
				Pubblicatore entry = new Pubblicatore();
				entry.setId(rs.getInt(ID));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				entry.setNomeOrganizzazione(rs.getString(NOME_ORGANIZZAZIONE));
				entry.setNomeCompleto(rs.getString(NOME_COMPLETO));
				if( rs.getString(IS_AUTORIZZATO).compareTo("y") == 0) {
					entry.setAutorizzato(true);
				} 
				else {
					entry.setAutorizzato(false);
				}
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
				idArtista = rs.getInt(FK_ID_ARTISTA);
				
				//fetch eager: recupero subito l'artista associato al pubblicatore
				Db2ArtistaDAO artistaDAO = new Db2ArtistaDAO();
				Artista artista = artistaDAO.read(idArtista);
				
				entry.setArtistaGestito(artista);
				
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
	
	public Pubblicatore read(String email, String password) {
		Pubblicatore result = null;
		int idArtista = -1;
		
		if ( email.isEmpty() || password.isEmpty() ) {
			System.err.println("read(): invalid email/password");
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
				Pubblicatore entry = new Pubblicatore();
				entry.setId(rs.getInt(ID));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				entry.setNomeOrganizzazione(rs.getString(NOME_ORGANIZZAZIONE));
				entry.setNomeCompleto(rs.getString(NOME_COMPLETO));
				if( rs.getString(IS_AUTORIZZATO).compareTo("y") == 0) {
					entry.setAutorizzato(true);
				} 
				else {
					entry.setAutorizzato(false);
				}
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
				idArtista = rs.getInt(FK_ID_ARTISTA);
				
				//fetch eager: recupero subito l'artista associato al pubblicatore
				Db2ArtistaDAO artistaDAO = new Db2ArtistaDAO();
				Artista artista = artistaDAO.read(idArtista);
				
				entry.setArtistaGestito(artista);
				
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
	public boolean update(Pubblicatore p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
