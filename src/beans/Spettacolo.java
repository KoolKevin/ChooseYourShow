package beans;

import java.util.Arrays;
import java.util.Date;

public class Spettacolo {
	private int id;
	private String nome;
	private Date data;
	private String tipologia;
	private String[] generi = new String[5];
	private Locale locale;
	
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
	
	@Override
	public String toString() {
		return "Spettacolo [id=" + id + ", nome=" + nome + ", data=" + data + ", tipologia=" + tipologia + ", generi="
				+ Arrays.toString(generi) + ", locale=" + locale + "]";
	}
}
