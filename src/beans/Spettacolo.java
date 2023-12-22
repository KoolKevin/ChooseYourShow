package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Spettacolo {
	private int id;
	private String nome;
	private Date data;
	private String tipologia;
	private String[] generi = new String[5];
	private Locale locale;
	private List<Artista> artisti = new ArrayList<Artista>();
	private List<Biglietto> biglietti = new ArrayList<Biglietto>();
	private SupportoSpettacolo supporto = new SupportoSpettacolo();
	private boolean alreadyLoadedArtisti = false;
	private boolean alreadyLoadedBiglietti = false;
	private boolean alreadyLoadedSupporto = false;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String[] getGeneri() {
		return generi;
	}
	public void setGeneri(String[] generi) {
		this.generi = generi;
	}
	//utility per generi
	public int getNumGeneri() {
		int i=1;
		
		for( int j=1; j<5; j++) {
			if(this.generi[j] != null) {
				i++;
			}
		}
		
		return i;
	}
 	public String getGenere(int index) {
 		if( index<0 || index>4 )
 			return null;
 		
 		return this.generi[index];
 	}
 	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public List<Artista> getArtisti() {
		return artisti;
	}
	public void setArtisti(List<Artista> artisti) {
		this.artisti = artisti;
	}
	public boolean isAlreadyLoadedArtisti() {
		return alreadyLoadedArtisti;
	}
	public void setAlreadyLoadedArtisti(boolean alreadyLoaded) {
		this.alreadyLoadedArtisti = alreadyLoaded;
	}
	public SupportoSpettacolo getSupporto() {
		return supporto;
	}
	public void setSupporto(SupportoSpettacolo supporto) {
		this.supporto = supporto;
	}
	public boolean isAlreadyLoadedSupporto() {
		return alreadyLoadedSupporto;
	}
	public void setAlreadyLoadedSupporto(boolean alreadyLoadedSupporto) {
		this.alreadyLoadedSupporto = alreadyLoadedSupporto;
	}
	@Override
	public String toString() {
		return "Spettacolo [id=" + id + ", nome=" + nome + ", data=" + data + ", tipologia=" + tipologia + ", generi="
				+ Arrays.toString(generi) + ", locale=" + locale + ", artisti=" + artisti + ", biglietti=" + biglietti
				+ ", supporto=" + supporto + ", alreadyLoadedArtisti=" + alreadyLoadedArtisti
				+ ", alreadyLoadedBiglietti=" + alreadyLoadedBiglietti + ", alreadyLoadedSupporto="
				+ alreadyLoadedSupporto + "]";
	}
	public List<Biglietto> getBiglietti() {
		return biglietti;
	}
	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}
	public boolean isAlreadyLoadedBiglietti() {
		return alreadyLoadedBiglietti;
	}
	public void setAlreadyLoadedBiglietti(boolean alreadyLoadedBiglietti) {
		this.alreadyLoadedBiglietti = alreadyLoadedBiglietti;
	}
}
