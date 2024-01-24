package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Locale;
import beans.Settore;
import dao.SettoreDAO;

public class Db2SettoreDAO implements SettoreDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "settori";
		private static final String ID = "id";
		private static final String NOME = "nome";
		private static final String DESCRIZIONE = "descrizione";
		private static final String CAPIENZA = "capienza";
		private static final String FK_ID_LOCALE = "idLocale";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+NOME+","+DESCRIZIONE+","+CAPIENZA
									   + ","+FK_ID_LOCALE +")"
									   + " VALUES (?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String GET_SETTORI_OF_LOCALE = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + FK_ID_LOCALE + " = ? ";
	
	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Settore settore) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (settore == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, settore.getNome());
			prep_stmt.setString(2, settore.getDescrizione());
			prep_stmt.setInt(3, settore.getCapienza());
			prep_stmt.setInt(4, settore.getLocale().getId());

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
	public Settore read(int id) {
		Settore result = null;
		
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
				Settore entry = new Settore();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				entry.setDescrizione(rs.getString(DESCRIZIONE));
				entry.setCapienza(rs.getInt(CAPIENZA));

				int idLocale = rs.getInt(FK_ID_LOCALE);
				
                //fetch eager del locale associato al settore
				Db2LocaleDAO localeDAO = new Db2LocaleDAO();
				Locale locale = localeDAO.read(idLocale);
				
				entry.setLocale(locale);
				
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
	public List<Settore> getSettoriOfLocale(int id) {
		List<Settore> result = new ArrayList<Settore>();
		
		if (id < 0) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(GET_SETTORI_OF_LOCALE);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			
			while (rs.next()) {
				Settore entry = new Settore();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				entry.setDescrizione(rs.getString(DESCRIZIONE));
				entry.setCapienza(rs.getInt(CAPIENZA));

				int idLocale = rs.getInt(FK_ID_LOCALE);
				
                //fetch eager del locale associato al settore
				Db2LocaleDAO localeDAO = new Db2LocaleDAO();
				Locale locale = localeDAO.read(idLocale);
				
				entry.setLocale(locale);
				
				result.add(entry);
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
	public boolean update(Settore s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
