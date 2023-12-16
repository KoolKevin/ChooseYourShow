package beans;

public class Locale {
	private int id;
	private String nome;
	private Citta citta;
	
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
		return "Locale [id=" + id + ", nome=" + nome + ", citta=" + citta + "]";
	}
}
