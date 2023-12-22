package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Pubblicatore;
import beans.Spettacolo;
import beans.SupportoSpettacolo;
import dao.SupportoSpettacoloDAO;

public class Db2SupportoSpettacoloDAO implements SupportoSpettacoloDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "supporti_spettacoli";
		private static final String ID = "id";
		private static final String FK_ID_APPROVATO = "idSpettacoloApprovato";
		private static final String FK_ID_NON_APPROVATO = "idSpettacoloNonApprovato";
		private static final String FK_ID_PUBBLICATORE = "idPubblicatore";
	
	//query	
	private static final String INSERT_NUOVO_SPETTACOLO = "INSERT INTO "+TABLE+"("+FK_ID_NON_APPROVATO+","+FK_ID_PUBBLICATORE+")"
			   + " VALUES (?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String READ_BY_ID_PUBBLICATORE = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + FK_ID_PUBBLICATORE + " = ? ";
	
	private static final String READ_BY_ID_SPETTACOLO = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + FK_ID_APPROVATO + " = ? " +
			"OR " + FK_ID_NON_APPROVATO + " = ? ";
	
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(SupportoSpettacolo supportoSpettacolo) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (supportoSpettacolo == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT_NUOVO_SPETTACOLO);

			prep_stmt.clearParameters();
			prep_stmt.setInt(1, supportoSpettacolo.getSpettacoloNonApprovato().getId() );
			prep_stmt.setInt(2, supportoSpettacolo.getPubblicatore().getId() );
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
	public SupportoSpettacolo read(int id) {
		SupportoSpettacolo result = null;
		
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
				SupportoSpettacolo entry = new SupportoSpettacolo();
				entry.setId(rs.getInt(ID));				
                int idApprovato = rs.getInt(FK_ID_APPROVATO);
                int idNonApprovato = rs.getInt(FK_ID_NON_APPROVATO);
                int idPubblicatore = rs.getInt(FK_ID_PUBBLICATORE);
                
                //fetch eager: recupero subito gli spettacoli e il pubblicatore del supporto
				Db2SpettacoloDAO spettacoloDAO = new Db2SpettacoloDAO();
				Db2PubblicatoreDAO pubblicatoreDAO = new Db2PubblicatoreDAO();
				
				Spettacolo spettacoloApprovato = spettacoloDAO.read(idApprovato);
				Spettacolo spettacoloNonApprovato = spettacoloDAO.read(idNonApprovato);
				Pubblicatore pubblicatore = pubblicatoreDAO.read(idPubblicatore);
				
				entry.setSpettacoloApprovato(spettacoloApprovato);
				entry.setSpettacoloNonApprovato(spettacoloNonApprovato);
				entry.setPubblicatore(pubblicatore);
				
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
	public SupportoSpettacolo readSupportoOfSpettacolo(int id) {
		SupportoSpettacolo result = null;
		
		if (id < 0) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_ID_SPETTACOLO);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			prep_stmt.setInt(2, id);
			ResultSet rs = prep_stmt.executeQuery();
			
			if (rs.next()) {
				SupportoSpettacolo entry = new SupportoSpettacolo();
				entry.setId(rs.getInt(ID));				
                int idApprovato = rs.getInt(FK_ID_APPROVATO);
                int idNonApprovato = rs.getInt(FK_ID_NON_APPROVATO);
                int idPubblicatore = rs.getInt(FK_ID_PUBBLICATORE);
                
                //fetch eager: recupero subito gli spettacoli e il pubblicatore del supporto
				Db2SpettacoloDAO spettacoloDAO = new Db2SpettacoloDAO();
				Db2PubblicatoreDAO pubblicatoreDAO = new Db2PubblicatoreDAO();
				
				Spettacolo spettacoloApprovato = spettacoloDAO.read(idApprovato);
				Spettacolo spettacoloNonApprovato = spettacoloDAO.read(idNonApprovato);
				Pubblicatore pubblicatore = pubblicatoreDAO.read(idPubblicatore);
				
				entry.setSpettacoloApprovato(spettacoloApprovato);
				entry.setSpettacoloNonApprovato(spettacoloNonApprovato);
				entry.setPubblicatore(pubblicatore);
				
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
	public List<SupportoSpettacolo> readSupportiOfPubblicatore(int id) {
		List<SupportoSpettacolo> result = new ArrayList<SupportoSpettacolo>();
		
		if (id< 0) {
			System.err.println("read(): cannot read an entry with a negative id");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_ID_PUBBLICATORE);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			
			while (rs.next()) {
				SupportoSpettacolo entry = new SupportoSpettacolo();
				entry.setId(rs.getInt(ID));				
                int idApprovato = rs.getInt(FK_ID_APPROVATO);
                int idNonApprovato = rs.getInt(FK_ID_NON_APPROVATO);
                int idPubblicatore = rs.getInt(FK_ID_PUBBLICATORE);
                
                //fetch eager: recupero subito gli spettacoli e il pubblicatore del supporto
				Db2SpettacoloDAO spettacoloDAO = new Db2SpettacoloDAO();
				Db2PubblicatoreDAO pubblicatoreDAO = new Db2PubblicatoreDAO();
				
				Spettacolo spettacoloApprovato = spettacoloDAO.read(idApprovato);
				Spettacolo spettacoloNonApprovato = spettacoloDAO.read(idNonApprovato);
				Pubblicatore pubblicatore = pubblicatoreDAO.read(idPubblicatore);
				
				entry.setSpettacoloApprovato(spettacoloApprovato);
				entry.setSpettacoloNonApprovato(spettacoloNonApprovato);
				entry.setPubblicatore(pubblicatore);
				
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
	
	//TODO: UPDATE e DELETE
	@Override
	public boolean update(SupportoSpettacolo spettacolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
