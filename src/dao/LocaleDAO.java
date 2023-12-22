package dao;

import java.util.List;

import beans.Locale;

public interface LocaleDAO {
	// --- CRUD -------------

	public void create(Locale l);

	public Locale read(int id);

	public boolean update(Locale c);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	
	public List<Locale> readAll();
}
