package dao;

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
	
	List<SupportoSpettacolo> readSupportiOfPubblicatore(int id);
}
