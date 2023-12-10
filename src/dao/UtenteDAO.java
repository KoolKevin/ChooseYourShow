package dao;

import beans.Utente;

public interface UtenteDAO {
	// --- CRUD -------------

	public void create(Utente u);

	public Utente read(int id);

	public boolean update(Utente u);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	public Utente read(String email, String password);
}
