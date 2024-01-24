package dao;


import java.sql.SQLException;

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
	
	public List<SupportoSpettacolo> readSupportiOfPubblicatore(int id);
	
	public List<SupportoSpettacolo> readAllSpettacoliDaRevisionare();
}
