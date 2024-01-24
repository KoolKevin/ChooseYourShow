package dao.db2;

import java.util.List;

import beans.Artista;
import beans.Biglietto;
import beans.Spettacolo;
import beans.SupportoSpettacolo;



public class Db2SpettacoloProxy extends Spettacolo {

	public Db2SpettacoloProxy() {
		super();
	}

	
	@Override
	public List<Artista> getArtisti() {
		
		if(!this.isAlreadyLoadedArtisti()) {
			Db2SpettacoloArtistaMappingDAO mappingDAO = new Db2SpettacoloArtistaMappingDAO();
			List<Artista> artisti = mappingDAO.getArtistiOfSpettacolo(this);
			this.setArtisti(artisti);
			this.setAlreadyLoadedArtisti(true);
		}
		
		return super.getArtisti();
	}
	
	@Override
	public SupportoSpettacolo getSupporto() {
		
		if(!this.isAlreadyLoadedSupporto()) {
			Db2SupportoSpettacoloDAO supportoDAO = new Db2SupportoSpettacoloDAO();
			SupportoSpettacolo supporto = supportoDAO.readSupportoOfSpettacolo(this.getId());
			this.setSupporto(supporto);
			this.setAlreadyLoadedSupporto(true);
		}
		
		return super.getSupporto();
	}
	
	@Override
	public List<Biglietto> getBiglietti() {
		
		if(!this.isAlreadyLoadedBiglietti()) {
			Db2BigliettoDAO bigliettoDAO = new Db2BigliettoDAO();
			List<Biglietto> biglietti = bigliettoDAO.getBigliettiOfSpettacolo(this.getId());
			this.setBiglietti(biglietti);
			this.setAlreadyLoadedBiglietti(true);
		}
		
		return super.getBiglietti();
	}
}	
