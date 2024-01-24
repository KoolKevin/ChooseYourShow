package beans;

import java.util.ArrayList;
import java.util.List;

public class Locale {
	private int id;
	private String nome;
	private Citta citta;
	
	private List<Settore> settori = new ArrayList<>();
	private boolean alreadyLoadedSettori = false;
	
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
	public Citta getCitta() {
		return citta;
	}
	public void setCitta(Citta citta) {
		this.citta = citta;
	}
	
	@Override
	public String toString() {
		return "Locale [id=" + id + ", nome=" + nome + ", citta=" + citta + ", settori=" + settori
				+ ", alreadyLoadedSettori=" + alreadyLoadedSettori + "]";
	}
	public List<Settore> getSettori() {
		return settori;
	}
	public void setSettori(List<Settore> settori) {
		this.settori = settori;
	}
	public boolean isAlreadyLoadedSettori() {
		return alreadyLoadedSettori;
	}
	public void setAlreadyLoadedSettori(boolean alreadyLoadedSettori) {
		this.alreadyLoadedSettori = alreadyLoadedSettori;
	}
}
