package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Citta;
import beans.Locale;
import dao.LocaleDAO;

public class Db2LocaleDAO implements LocaleDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "locali";
		private static final String ID = "id";
		private static final String NOME = "nome";
		private static final String FK_ID_CITTA = "idcitta";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+NOME+", "+FK_ID_CITTA+")"
									   + " VALUES (?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String READ_ALL = "SELECT * FROM " + TABLE;
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Locale locale) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (locale == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, locale.getNome());
			prep_stmt.setInt(2, locale.getCitta().getId());

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
	public Locale read(int id) {
		Locale result = null;
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
				Locale entry = new Db2LocaleProxy();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				idCitta = rs.getInt(FK_ID_CITTA);
				
				//fetch eager: recupero subito la citta associata al locale
				Db2CittaDAO cittaDAO = new Db2CittaDAO();
				Citta citta = cittaDAO.read(idCitta);
				
				//fetch lazy dei settori associati al locale, ci pensa il proxy
				
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
	
	@Override
	public List<Locale> readAll() {
		List<Locale> result = new ArrayList<Locale>();
		int idCitta = -1;

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_ALL);
			ResultSet rs = prep_stmt.executeQuery();
			
			while(rs.next()) {
				Locale entry = new Db2LocaleProxy();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				idCitta = rs.getInt(FK_ID_CITTA);
				
				//fetch eager: recupero subito la citta associata al locale
				Db2CittaDAO cittaDAO = new Db2CittaDAO();
				Citta citta = cittaDAO.read(idCitta);
				
				//fetch lazy dei settori associati al locale, ci pensa il proxy
				
				entry.setCitta(citta);
				
				result.add(entry);
			}
				
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve entries" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}
	
	//TODO: UPDATE e DELETE
	@Override
	public boolean update(Locale locale) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
