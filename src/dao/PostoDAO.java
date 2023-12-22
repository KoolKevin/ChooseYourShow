package dao;

import beans.Posto;

public interface PostoDAO {
	// --- CRUD -------------

	public void create(Posto p);
	
	public Posto read(int id);

	public boolean update(Posto p);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
}
