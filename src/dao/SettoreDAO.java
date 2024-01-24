package dao;

import java.util.List;

import beans.Settore;

public interface SettoreDAO {
	// --- CRUD -------------

	public void create(Settore s);
	
	public Settore read(int id);

	public boolean update(Settore s);

	public boolean delete(int id);

	// -------- METODI PARTICOLARI --------------------------
	
	List<Settore> getSettoriOfLocale(int id);
	
}
