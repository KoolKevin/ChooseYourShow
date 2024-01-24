package dao.db2;

import java.util.List;

import beans.Pubblicatore;
import beans.SupportoSpettacolo;



public class Db2PubblicatoreProxy extends Pubblicatore {

	public Db2PubblicatoreProxy() {
		super();
	}

	
	@Override
	public List<SupportoSpettacolo> getSupportiSpettacoliPubblicati() {
		
		if(!this.isAlreadyLoadedSupporti()) {
			Db2SupportoSpettacoloDAO supportiDAO = new Db2SupportoSpettacoloDAO();
			List<SupportoSpettacolo> supporti = supportiDAO.readSupportiOfPubblicatore(this.getId());
			this.setSupportiSpettacoliPubblicati(supporti);
			this.setAlreadyLoadedSupporti(true);
		}
		
		return super.getSupportiSpettacoliPubblicati();
	}
	
	
}	
