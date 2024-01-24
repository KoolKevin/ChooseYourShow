package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Amministratore;
import dao.AmministratoreDAO;

public class Db2AmministratoreDAO implements AmministratoreDAO {
	private static final String TABLE = "amministratori";
	private static final String ID = "id";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";

	
	private static final String INSERT = "INSERT INTO " + TABLE + " (" + EMAIL + ", " + PASSWORD + ")"
			+ " VALUES (?, ?)";
	
	private static final String READ_BY_ID = "SELECT * " + "FROM "+ TABLE + " " + " WHERE " + ID + " = ? ";
	
	private static final String READ_BY_CREDENTIALS = "SELECT * " + " FROM " + TABLE + " " + " WHERE " + EMAIL + " = ? " + "AND " + PASSWORD + " = ? ";

	@Override
	public void create(Amministratore a) {
		if (a == null) {
			System.out.println("create(): failed to insert a null entry");
			return;
		}
		
		Connection conn = Db2DAOFactory.createConnection();
		
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, a.getEmail());
			prep_stmt.setString(2, a.getPassword());
			
			prep_stmt.executeUpdate();
			prep_stmt.close();
		} catch (Exception e) {
			System.err.println("create(): failed to insert entry in table "+ TABLE +": " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
	}

	@Override
	public Amministratore read(int id) {
		Amministratore result = null;
		int idAmministratore = -1;

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
				Amministratore entry = new Amministratore();
				entry.setId(rs.getInt(ID));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				
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
	public boolean update(Amministratore u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Amministratore read(String email, String password) {
		Amministratore result = null;
		int idAmministratore = -1;
		
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_CREDENTIALS);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, email);
			prep_stmt.setString(2, password);
			ResultSet rs = prep_stmt.executeQuery();
			
			if (rs.next()) {
				Amministratore entry = new Amministratore();
				entry.setId(rs.getInt(ID));
				entry.setEmail(rs.getString(EMAIL));
				entry.setPassword(rs.getString(PASSWORD));
				
				result = entry;
			}
			
			rs.close();
			prep_stmt.close();
		} 
		catch (Exception e) {
			System.err.println("read(): failed to retrieve entry with credentials: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}

}
