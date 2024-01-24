package dao.db2;

import java.util.List;

import beans.Locale;
import beans.Settore;


public class Db2LocaleProxy extends Locale {

	public Db2LocaleProxy() {
		super();
	}

	
	@Override
	public List<Settore> getSettori() {
		
		if(!this.isAlreadyLoadedSettori()) {
			Db2SettoreDAO settoreDAO = new Db2SettoreDAO();
			List<Settore> settori = settoreDAO.getSettoriOfLocale(this.getId());
			this.setSettori(settori);
			this.setAlreadyLoadedSettori(true);
		}
		
		return super.getSettori();
	}
	
	
}	
