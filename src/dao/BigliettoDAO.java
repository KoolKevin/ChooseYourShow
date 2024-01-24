package dao;

import java.util.List;

import beans.Biglietto;

public interface BigliettoDAO {
	// --- CRUD -------------

	public void create(Biglietto b);
	
	public Biglietto read(int id);

	public boolean update(Biglietto b);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	
	public List<Biglietto> getBigliettiOfSpettacolo(int idSpettacolo);
	
}
