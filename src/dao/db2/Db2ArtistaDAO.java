package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Artista;
import dao.ArtistaDAO;

public class Db2ArtistaDAO implements ArtistaDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "artisti";
		private static final String ID = "id";
		private static final String NOME_COMPLETO = "nomeCompleto";
		private static final String NOME_ARTE = "nomeArte";
		private static final String BIOGRAFIA = "biografia";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+NOME_COMPLETO+","+NOME_ARTE+","+BIOGRAFIA+")"
									   + " VALUES (?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	
	private static final String READ_BY_NAME = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + NOME_COMPLETO + " = ? ";
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Artista artista) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (artista == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, artista.getNomeCompleto());
			prep_stmt.setString(2, artista.getNomeArte());
			prep_stmt.setString(3, artista.getBiografia());

			prep_stmt.executeUpdate();
			
			/* quando creo un artista mi serve anche recuperare il suo id autogenerato dal db */
			
			//NB: se faccio così creo e chiudo due volte una connessione al db, ci metto un po' di tempo :/
			Artista artistaCreato = this.read(artista.getNomeCompleto());
			artista.setId(artistaCreato.getId());

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
	public Artista read(int id) {
		Artista result = null;
		
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
				Artista entry = new Db2ArtistaProxy();
				entry.setId(rs.getInt(ID));
				entry.setNomeCompleto(rs.getString(NOME_COMPLETO));
				entry.setNomeArte(rs.getString(NOME_ARTE));
				entry.setBiografia(rs.getString(BIOGRAFIA));
                
                //fetch lazy: la lista degli spettacoli la recupera il proxy
				
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
	public Artista read(String nomeCompleto) {
		Artista result = null;
		
		if ( nomeCompleto == null ) {
			System.err.println("read(): cannot read an entry with a null name");
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(READ_BY_NAME);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, nomeCompleto);
			ResultSet rs = prep_stmt.executeQuery();
			
			if (rs.next()) {
				Artista entry = new Db2ArtistaProxy();
				entry.setId(rs.getInt(ID));
				entry.setNomeCompleto(rs.getString(NOME_COMPLETO));
				entry.setNomeArte(rs.getString(NOME_ARTE));
				entry.setBiografia(rs.getString(BIOGRAFIA));
                
                //fetch lazy: la lista degli spettacoli la recupera il proxy
				
				result = entry;
			}
				
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve entry with name = " + nomeCompleto + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}

	@Override
	public boolean update(Artista a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
