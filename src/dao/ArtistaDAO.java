package dao;

import beans.Artista;

public interface ArtistaDAO {
	// --- CRUD -------------

	public void create(Artista a);
	
	public Artista read(int id);

	public boolean update(Artista a);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	
	public Artista read(String nomeCompleto);
}
