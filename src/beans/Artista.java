package beans;

import java.util.ArrayList;
import java.util.List;

public class Artista {
	private int id;
	private String nomeCompleto;
	private String nomeArte;
	private String biografia;
	private List<Spettacolo> spettacoli = new ArrayList<Spettacolo>();
	private boolean alreadyLoaded = false;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getNomeArte() {
		return nomeArte;
	}
	public void setNomeArte(String nomeArte) {
		this.nomeArte = nomeArte;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public List<Spettacolo> getSpettacoli() {
		return spettacoli;
	}
	public void setSpettacoli(List<Spettacolo> spettacoli) {
		this.spettacoli = spettacoli;
	}
	@Override
	public String toString() {
		return "Artista [id=" + id + ", nomeCompleto=" + nomeCompleto + ", nomeArte=" + nomeArte + ", biografia="
				+ biografia + ", spettacoli=" + spettacoli + "]";
	}
	public boolean isAlreadyLoaded() {
		return alreadyLoaded;
	}
	public void setAlreadyLoaded(boolean alreadyLoaded) {
		this.alreadyLoaded = alreadyLoaded;
	}
	
}
