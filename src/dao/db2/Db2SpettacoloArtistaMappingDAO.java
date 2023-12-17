package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Artista;
import beans.Locale;
import beans.Spettacolo;
import dao.SpettacoloArtistaMappingDAO;

public class Db2SpettacoloArtistaMappingDAO implements SpettacoloArtistaMappingDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "mapping_artista_spettacolo";
		private static final String ID_SPETTACOLO = "idSpettacolo";
		private static final String ID_ARTISTA = "idArtista";
	private static final String TABLE_SPETTACOLI = "spettacoli";
	private static final String TABLE_ARTISTI = "artisti";
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+ID_SPETTACOLO+","+ID_ARTISTA+")"
									   + " VALUES (?,?)";
	
	private static final String DELETE = "DELETE FROM "+TABLE+" WHERE ("+ID_SPETTACOLO+","+ID_ARTISTA+") = (?,?)";
	
	private static final String SPETTACOLI_OF_ARTISTA = "SELECT s.* "
													  + "FROM "+TABLE_SPETTACOLI+" s,"+TABLE+" mas "
													  + "WHERE s.ID = mas."+ID_SPETTACOLO+" "
													  + "AND mas."+ID_ARTISTA+" = ?";
	
	private static final String ARTISTI_OF_SPETTACOLO = "SELECT a.* "
													  + "FROM "+TABLE_ARTISTI+" a,"+TABLE+" mas "
													  + "WHERE a.ID = mas."+ID_ARTISTA+" "
													  + "AND mas."+ID_SPETTACOLO+" = ?";
	
	@Override
	public void create(int idSpettacolo, int idArtista) {
		//TODO: controllo argomenti
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idSpettacolo);
			prep_stmt.setInt(2, idArtista);

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
	public boolean delete(int idSpettacolo, int idArtista) {
		//TODO: controllo argomenti
		
		//delete
		boolean result = true;
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(DELETE);

			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idSpettacolo);
			prep_stmt.setInt(2, idArtista);

			prep_stmt.executeUpdate();

			prep_stmt.close();
		}
		catch (Exception e) {
			result = false;
			System.err.println("create(): failed to DELETE entry in table "+ TABLE +": " + e.getMessage());
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		return result;
	}

	
	@Override
	public List<Spettacolo> getSpettacoliOfArtista(Artista a) {
		List<Spettacolo> result = new ArrayList<Spettacolo>();
		
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(SPETTACOLI_OF_ARTISTA);
			
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, a.getId());
			
			ResultSet rs = prep_stmt.executeQuery();
			
			while(rs.next()) {
				Spettacolo entry = new Db2SpettacoloProxy();
				//non ho voglia di definire le costanti anche per lo schema di spettacoli
				entry.setId(rs.getInt("id"));
				entry.setNome(rs.getString("nome"));
				//anche qua devo gestire bene i due formati di data
				long secs = rs.getDate("data").getTime();
                java.util.Date data = new java.util.Date(secs);
                entry.setData(data);
                entry.setTipologia("tipologia");
                entry.getGeneri()[0] = rs.getString("genere1");
                entry.getGeneri()[1] = rs.getString("genere2");
                entry.getGeneri()[2] = rs.getString("genere3");
                entry.getGeneri()[3] = rs.getString("genere4");
                entry.getGeneri()[4] = rs.getString("genere5");
                
                int idLocale = rs.getInt("idLocale");
                
                //fetch eager: recupero subito il locale associato allo spettacolo
				Db2LocaleDAO localeDAO = new Db2LocaleDAO();
				Locale locale = localeDAO.read(idLocale);
				
				//fetch lazy: la lista degli artisti la recupera il proxy
				
				entry.setLocale(locale);
				
				result.add(entry);
			}
			
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve list of entries of id = " + a.getId() + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<Artista> getArtistiOfSpettacolo(Spettacolo s) {
		List<Artista> result = new ArrayList<Artista>();
		
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(ARTISTI_OF_SPETTACOLO);
			
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, s.getId());
			
			ResultSet rs = prep_stmt.executeQuery();
			
			while(rs.next()) {
				Artista entry = new Db2ArtistaProxy();
				entry.setId(rs.getInt("id"));
				entry.setNomeCompleto(rs.getString("nomeCompleto"));
				entry.setNomeArte(rs.getString("nomeArte"));
				entry.setBiografia(rs.getString("biografia"));
                
                //fetch lazy: la lista degli spettacoli la recupera il proxy
				
				result.add(entry);
			}
			
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve list of entries of id = " + s.getId() + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}
}
