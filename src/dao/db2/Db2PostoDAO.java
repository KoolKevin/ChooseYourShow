package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Posto;
import beans.Settore;
import dao.PostoDAO;

public class Db2PostoDAO implements PostoDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "posti";
		private static final String ID = "id";
		private static final String FILA= "fila";
		private static final String SEDIA = "sedia";
		private static final String OCCUPATO = "occupato";
		private static final String FK_ID_SETTORE = "idSettore";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+FILA+","+SEDIA+","+OCCUPATO
													   +","+FK_ID_SETTORE+")"
									   + " VALUES (?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Posto posto) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (posto == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, posto.getFila());
			prep_stmt.setString(2, posto.getSedia());
			prep_stmt.setString(3, String.valueOf(posto.getOccupato()));
			prep_stmt.setInt(4, posto.getSettore().getId());

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
	public Posto read(int id) {
		Posto result = null;
		
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
				Posto entry = new Posto();
				entry.setId(rs.getInt(ID));
				entry.setFila(rs.getString(FILA));
				entry.setSedia(rs.getString(SEDIA));
				if( rs.getString(OCCUPATO).compareTo("y") == 0) {
					entry.setOccupato(true);
				} 
				else {
					entry.setOccupato(false);
				}
                
                //fetch eager del settore di cui fa parte il posto
				int idSettore = rs.getInt(FK_ID_SETTORE);
				
				Db2SettoreDAO settoreDAO = new Db2SettoreDAO();
				Settore settore = settoreDAO.read(idSettore);
				
				entry.setSettore(settore);
				
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
	public boolean update(Posto a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
