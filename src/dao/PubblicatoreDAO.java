package dao;

import beans.Pubblicatore;

public interface PubblicatoreDAO {
	// --- CRUD -------------

	public void create(Pubblicatore p);

	public Pubblicatore read(int id);

	public boolean update(Pubblicatore p);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	public Pubblicatore read(String email, String password);
}
