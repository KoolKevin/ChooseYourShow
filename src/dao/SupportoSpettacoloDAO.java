package dao;

<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> 861c96cd776d0b1c842b881d105704b50891cba6
import java.util.List;

import beans.SupportoSpettacolo;

public interface SupportoSpettacoloDAO {
	// --- CRUD -------------

	public void create(SupportoSpettacolo s);

	public SupportoSpettacolo read(int id);

	public boolean update(SupportoSpettacolo s);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	public SupportoSpettacolo readSupportoOfSpettacolo(int id);
	
	public List<SupportoSpettacolo> readSupportiOfPubblicatore(int id);
	
	public List<SupportoSpettacolo> readAllSpettacoliDaRevisionare();

	List<SupportoSpettacolo> readSupportiOfPubblicatore(int id);
}
