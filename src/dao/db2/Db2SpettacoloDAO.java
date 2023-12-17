package dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Locale;
import beans.RicercaSpettacolo;
import beans.Spettacolo;
import dao.SpettacoloDAO;

public class Db2SpettacoloDAO implements SpettacoloDAO {
	//costanti nomi table e attributi per non sbagliarsi
	private static final String TABLE = "spettacoli";
		private static final String ID = "id";
		private static final String NOME = "nome";
		private static final String DATA = "data";
		private static final String TIPOLOGIA = "tipologia";
		private static final String GENERE1 = "genere1";
		private static final String GENERE2 = "genere2";
		private static final String GENERE3 = "genere3";
		private static final String GENERE4 = "genere4";
		private static final String GENERE5 = "genere5";
		private static final String FK_ID_LOCALE = "idlocale";
	
	//query
	private static final String INSERT = "INSERT INTO "+TABLE+"("+NOME+","+DATA+","+TIPOLOGIA+","
																 +GENERE1+","+GENERE2+","+GENERE3+","+GENERE4+","+GENERE5+","
																 +FK_ID_LOCALE+")"
									   + " VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String READ_BY_ID = "SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? ";
	//TODO: costanti per query UPDATE e DELETE
		
	@Override
	public void create(Spettacolo spettacolo) {
		//controllo argomenti
		//TODO: fai un controllo migliore
		if (spettacolo == null) {
			System.err.println("create(): failed to insert a null entry");
			return;
		}
		
		//inserimento
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement prep_stmt = conn.prepareStatement(INSERT);

			prep_stmt.clearParameters();
			prep_stmt.setString(1, spettacolo.getNome());
			//nelle date devo fare attenzione a passare da sql.Date(che va bene per i db), a util.Date(che va bene per i bean)
			prep_stmt.setDate(2, new java.sql.Date(spettacolo.getData().getTime()) );
			prep_stmt.setString(3, spettacolo.getTipologia());
			//generi
			//sicuramente c'è un modo migliore di farlo ma per adesso ho fatto così
			int numGeneri = spettacolo.getNumGeneri();
			for(int i=0; i<5; i++) {
				if(i < numGeneri) {
					prep_stmt.setString(4+i, spettacolo.getGenere(i));
				}
				else {
					prep_stmt.setString(4+i, null);
				}
			}
			prep_stmt.setInt(9, spettacolo.getLocale().getId());
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
	public Spettacolo read(int id) {
		Spettacolo result = null;
		
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
				Spettacolo entry = new Db2SpettacoloProxy();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				//anche qua devo gestire bene i due formati di data
				long secs = rs.getDate(DATA).getTime();
                java.util.Date data = new java.util.Date(secs);
                entry.setData(data);
                entry.setTipologia(TIPOLOGIA);
                entry.getGeneri()[0] = rs.getString(GENERE1);
                entry.getGeneri()[1] = rs.getString(GENERE2);
                entry.getGeneri()[2] = rs.getString(GENERE3);
                entry.getGeneri()[3] = rs.getString(GENERE4);
                entry.getGeneri()[4] = rs.getString(GENERE5);
                
                int idLocale = rs.getInt(FK_ID_LOCALE);
                
                //fetch eager: recupero subito il locale associato allo spettacolo
				Db2LocaleDAO localeDAO = new Db2LocaleDAO();
				Locale locale = localeDAO.read(idLocale);
				
				//fetch lazy degli artisti: se ne occupa il proxy
				
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
	
	
	//TODO: UPDATE e DELETE
	@Override
	public boolean update(Spettacolo spettacolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Spettacolo> cercaSpettacoli(RicercaSpettacolo r) {
		List<Spettacolo> result = new ArrayList<Spettacolo>();
		//base della query
		String query = "SELECT  s.* "
						+ "FROM SPETTACOLI s, ARTISTI a, MAPPING_ARTISTA_SPETTACOLO mas, LOCALI l, CITTA c "
						+ "WHERE s.IDLOCALE = l.ID "
						+ "AND l.IDCITTA = c.ID "
						+ "AND a.ID = mas.IDARTISTA "
						+ "AND s.ID= mas.IDSPETTACOLO ";
		
		//costruzione query
		if( r.getNomeSpettacolo() != null) {
			query = query + "AND LOWER(s.NOME) LIKE LOWER(?) ";
		}
		if( r.getNomeArtista() != null) {
			query = query + "AND LOWER(a.NOMEARTE) LIKE LOWER(?) ";
		}
		if( r.getDataSpettacolo() != null) {
			query = query + "AND s.DATA = ? ";
		}
		if( r.getInizioPeriodo() != null) {
			query = query + "AND s.DATA >= ? ";
		}
		if( r.getFinePeriodo() != null) {
			query = query + "AND s.DATA <= ? ";
		}
		if( r.getTipoSpettacolo() != null) {
			query = query + "AND LOWER(s.TIPOLOGIA) LIKE LOWER(?) ";
		}
		if( r.getGenereSpettacolo() != null) {
			query = query + "AND LOWER(s.GENERE1) LIKE LOWER(?) ";	//controllo solo un genere per semplicità
		}
		if( r.getNomeCitta() != null) {
			query = query + "AND LOWER(c.NOME) LIKE LOWER(?) ";	
		}
		if( r.getNomeLocale() != null) {
			query = query + "AND LOWER(l.NOME) LIKE LOWER(?) ";	
		}
		
		//esecuzione query
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(query);
			prep_stmt.clearParameters();
			int contaParametriSettati = 1;
			//setto i parametri
			if( r.getNomeSpettacolo() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getNomeSpettacolo()+"%");
				contaParametriSettati++;
			}
			if( r.getNomeArtista() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getNomeArtista()+"%");
				contaParametriSettati++;
			}
			if( r.getDataSpettacolo() != null) {
				prep_stmt.setDate(contaParametriSettati, new java.sql.Date(r.getDataSpettacolo().getTime()) );
				contaParametriSettati++;
			}
			if( r.getInizioPeriodo() != null) {
				prep_stmt.setDate(contaParametriSettati, new java.sql.Date(r.getInizioPeriodo().getTime()) );
				contaParametriSettati++;
			}
			if( r.getFinePeriodo() != null) {
				prep_stmt.setDate(contaParametriSettati, new java.sql.Date(r.getFinePeriodo().getTime()) );
				contaParametriSettati++;
			}
			if( r.getTipoSpettacolo() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getTipoSpettacolo()+"%");
				contaParametriSettati++;
			}
			if( r.getGenereSpettacolo() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getGenereSpettacolo()+"%");
				contaParametriSettati++;
			}
			if( r.getNomeCitta() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getNomeCitta()+"%");
				contaParametriSettati++;
			}
			if( r.getNomeLocale() != null) {
				prep_stmt.setString(contaParametriSettati, "%"+r.getNomeLocale()+"%");
				contaParametriSettati++;
			}
			ResultSet rs = prep_stmt.executeQuery();
			
			Db2LocaleDAO localeDAO = new Db2LocaleDAO();
			//costruisco il risultato
			while(rs.next()) {
				Spettacolo entry = new Db2SpettacoloProxy();
				entry.setId(rs.getInt(ID));
				entry.setNome(rs.getString(NOME));
				//anche qua devo gestire bene i due formati di data
				long secs = rs.getDate(DATA).getTime();
                java.util.Date data = new java.util.Date(secs);
                entry.setData(data);
                entry.setTipologia(TIPOLOGIA);
                entry.getGeneri()[0] = rs.getString(GENERE1);
                entry.getGeneri()[1] = rs.getString(GENERE2);
                entry.getGeneri()[2] = rs.getString(GENERE3);
                entry.getGeneri()[3] = rs.getString(GENERE4);
                entry.getGeneri()[4] = rs.getString(GENERE5);
                
                int idLocale = rs.getInt(FK_ID_LOCALE);
                
                //fetch eager: recupero subito il locale associato allo spettacolo
				
				Locale locale = localeDAO.read(idLocale);
				
				entry.setLocale(locale);
				
				//fetch LAZY degli artisti associati allo spettacolo, ci pensa il proxy
				
				result.add(entry);
			}
				
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.err.println("read(): failed to retrieve spettacoli with: " + r+ ": " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		
		
		return result;
	}
}
