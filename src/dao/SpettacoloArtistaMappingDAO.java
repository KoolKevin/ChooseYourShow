package dao;

import java.util.List;

import beans.Artista;
import beans.Spettacolo;

public interface SpettacoloArtistaMappingDAO {
	// --- CRUD -------------

	public void create(int idSpettacolo, int idArtista);
	
	//questi per una tabella di mapping non servono
	//public PiattoDTO read();
	//public boolean update();

	public boolean delete(int idSpettacolo, int idArtista);

	
	// -------- METODI PARTICOLARI --------------------------
	
	public List<Spettacolo> getSpettacoliOfArtista(Artista a);
	public List<Artista> getArtistiOfSpettacolo(Spettacolo s);
}
