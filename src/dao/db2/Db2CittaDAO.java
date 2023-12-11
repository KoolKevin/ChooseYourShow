package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Citta;
import dao.CittaDAO;

public class Db2CittaDAO implements CittaDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "citta";
		private static final String ID = "id";
		private static final String NOME = "nome";
		private static final String COORDINATE = "coordinate";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+NOME+", "+COORDINATE+")"
									   + " VALUES (?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Citta citta) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (citta == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, citta.getNome());
			prep_stmt.setString(2, citta.getCoordinate());

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
	public Citta read(int id) {
		Citta result = null;
		
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
				Citta entry = new Citta();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				entry.setCoordinate(rs.getString(COORDINATE));
				
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
	
	
	//TODO: UPDATE e DELETE
	@Override
	public boolean update(Citta c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
