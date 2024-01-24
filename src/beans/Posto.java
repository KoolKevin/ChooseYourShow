package beans;

public class Posto {
	private int id;
	private String fila;
	private String sedia;
	private boolean occupato;
	
	private Settore settore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getSedia() {
		return sedia;
	}

	public void setSedia(String sedia) {
		this.sedia = sedia;
	}

	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
	public char getOccupato() {
		if(this.occupato) {
			return 'y';
		}
		
		return 'n';
	}
	@Override
	public String toString() {
		return "Posto [id=" + id + ", fila=" + fila + ", sedia=" + sedia + ", occupato=" + occupato + ", settore="
				+ settore + "]";
	}

}
