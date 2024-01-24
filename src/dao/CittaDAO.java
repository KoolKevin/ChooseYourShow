package dao;

import java.util.List;

import beans.Citta;

public interface CittaDAO {
	// --- CRUD -------------

	public void create(Citta c);

	public Citta read(int id);

	public boolean update(Citta c);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	
	public List<Citta> readAll();
}
