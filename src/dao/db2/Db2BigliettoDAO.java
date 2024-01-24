package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Biglietto;
import beans.Settore;
import beans.Spettacolo;
import dao.BigliettoDAO;

public class Db2BigliettoDAO implements BigliettoDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "biglietti";
		private static final String ID = "id";
		private static final String DESCRIZIONE = "descrizione";
		private static final String QUANTITA = "quantita";
		private static final String COSTO = "costo";
		private static final String FK_ID_SPETTACOLO = "idSpettacolo";
		private static final String FK_ID_SETTORE = "idSettore";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+DESCRIZIONE+","+QUANTITA+","+COSTO
									   + ","+FK_ID_SPETTACOLO +","+FK_ID_SETTORE+")"
									   + " VALUES (?,?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String GET_BIGLIETTI_OF_SPETTACOLO = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + FK_ID_SPETTACOLO + " = ? ";
	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Biglietto biglietto) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (biglietto == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, biglietto.getDescrizione());
			prep_stmt.setInt(2, biglietto.getBigliettiDisponibili());
			prep_stmt.setFloat(3, biglietto.getCosto());
			prep_stmt.setInt(4, biglietto.getSpettacolo().getId());
			prep_stmt.setInt(5, biglietto.getSettore().getId());

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
	public Biglietto read(int id) {
		Biglietto result = null;
		
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
				Biglietto entry = new Biglietto();
				entry.setId(rs.getInt(ID));
				entry.setDescrizione(rs.getString(DESCRIZIONE));
				entry.setBigliettiDisponibili(rs.getInt(QUANTITA));
				entry.setCosto(rs.getFloat(COSTO));
				int idSpettacolo = rs.getInt(FK_ID_SPETTACOLO);
				int idSettore = rs.getInt(FK_ID_SETTORE);
				
                //fetch eager dello spettacolo e del settore associato al biglietto
				Db2SpettacoloDAO spettacoloDAO = new Db2SpettacoloDAO();
				Db2SettoreDAO settoreDAO = new Db2SettoreDAO();
				Spettacolo spettacolo = spettacoloDAO.read(idSpettacolo);
				Settore settore= settoreDAO.read(idSettore);
				
				entry.setSpettacolo(spettacolo);
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
	public boolean update(Biglietto a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Biglietto> getBigliettiOfSpettacolo(int id) {
		List<Biglietto> result = new ArrayList<Biglietto>();
		
		if (id < 0) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(GET_BIGLIETTI_OF_SPETTACOLO);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			
			while (rs.next()) {
				Biglietto entry = new Biglietto();
				entry.setId(rs.getInt(ID));
				entry.setDescrizione(rs.getString(DESCRIZIONE));
				entry.setBigliettiDisponibili(rs.getInt(QUANTITA));
				entry.setCosto(rs.getFloat(COSTO));
				int idSpettacolo = rs.getInt(FK_ID_SPETTACOLO);
				int idSettore = rs.getInt(FK_ID_SETTORE);
				
                //fetch eager dello spettacolo e del settore associato al biglietto
				Db2SpettacoloDAO spettacoloDAO = new Db2SpettacoloDAO();
				Db2SettoreDAO settoreDAO = new Db2SettoreDAO();
				Spettacolo spettacolo = spettacoloDAO.read(idSpettacolo);
				Settore settore= settoreDAO.read(idSettore);
				
				entry.setSpettacolo(spettacolo);
				entry.setSettore(settore);
				
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
}
