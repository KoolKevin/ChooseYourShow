package dao;

import java.util.List;

import beans.RicercaSpettacolo;
import beans.Spettacolo;

public interface SpettacoloDAO {
	// --- CRUD -------------

	public void create(Spettacolo s);

	public Spettacolo read(int id);

	public boolean update(Spettacolo s);

	public boolean delete(int id);
	
	// -------- METODI PARTICOLARI --------------------------
	
	public List<Spettacolo> cercaSpettacoli(RicercaSpettacolo r);
}
