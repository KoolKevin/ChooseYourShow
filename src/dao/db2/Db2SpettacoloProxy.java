package dao.db2;

import java.util.List;

import beans.Artista;
import beans.Spettacolo;



public class Db2SpettacoloProxy extends Spettacolo {

	public Db2SpettacoloProxy() {
		super();
	}

	
	@Override
	public List<Artista> getArtisti() {
		
		if(!this.isAlreadyLoaded()) {
			Db2SpettacoloArtistaMappingDAO mappingDAO = new Db2SpettacoloArtistaMappingDAO();
			List<Artista> artisti = mappingDAO.getArtistiOfSpettacolo(this);
			this.setArtisti(artisti);
			this.setAlreadyLoaded(true);
		}
		
		return super.getArtisti();
	}
	
	
}	
