package beans;

public class Biglietto {
	private int id;
	private String descrizione;
	private float costo;	//non c'è la classe Costo, solo prezzo in euro
	private int bigliettiDisponibili;
	
	private Spettacolo spettacolo;
	private Settore settore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getBigliettiDisponibili() {
		return bigliettiDisponibili;
	}
	public void setBigliettiDisponibili(int bigliettiDisponibili) {
		this.bigliettiDisponibili = bigliettiDisponibili;
	}
	public Spettacolo getSpettacolo() {
		return spettacolo;
	}
	public void setSpettacolo(Spettacolo spettacolo) {
		this.spettacolo = spettacolo;
	}
	public Settore getSettore() {
		return settore;
	}
	public void setSettore(Settore settore) {
		this.settore = settore;
	}
	@Override
	public String toString() {
		return "Biglietto [id=" + id + ", descrizione=" + descrizione + ", costo=" + costo + ", bigliettiDisponibili="
				+ bigliettiDisponibili + ", spettacolo=" + spettacolo + ", settore=" + settore + "]";
	}
}
