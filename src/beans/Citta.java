package beans;

public class Citta {
	private int id;
	private String nome;
	private String coordinate;
	
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
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public String toString() {
		return "Citta [id=" + id + ", nome=" + nome + ", coordinate=" + coordinate + "]";
	}
	
	
}
