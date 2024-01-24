package dao.db2;

import java.util.List;

import beans.Artista;
import beans.Spettacolo;



public class Db2ArtistaProxy extends Artista {

	public Db2ArtistaProxy() {
		super();
	}

	
	@Override
	public List<Spettacolo> getSpettacoli() {
		
		if(!this.isAlreadyLoaded()) {
			Db2SpettacoloArtistaMappingDAO mappingDAO = new Db2SpettacoloArtistaMappingDAO();
			List<Spettacolo> spettacoli = mappingDAO.getSpettacoliOfArtista(this);
			this.setSpettacoli(spettacoli);
			this.setAlreadyLoaded(true);
		}
		
		return super.getSpettacoli();
	}
	
	
}	
