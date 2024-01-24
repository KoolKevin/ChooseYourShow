package beans;

public class SupportoSpettacolo {
	private int id;
	private Spettacolo spettacoloApprovato;
	private Spettacolo spettacoloNonApprovato;
	private Pubblicatore pubblicatore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Spettacolo getSpettacoloApprovato() {
		return spettacoloApprovato;
	}
	public void setSpettacoloApprovato(Spettacolo spettacoloApprovato) {
		this.spettacoloApprovato = spettacoloApprovato;
	}
	public Spettacolo getSpettacoloNonApprovato() {
		return spettacoloNonApprovato;
	}
	public void setSpettacoloNonApprovato(Spettacolo spettacoloNonApprovato) {
		this.spettacoloNonApprovato = spettacoloNonApprovato;
	}
	public Pubblicatore getPubblicatore() {
		return pubblicatore;
	}
	public void setPubblicatore(Pubblicatore pubblicatore) {
		this.pubblicatore = pubblicatore;
	}
	@Override
	public String toString() {
		return "SupportoSpettacolo [id=" + id + ", spettacoloApprovato=" + spettacoloApprovato
				+ ", spettacoloNonApprovato=" + spettacoloNonApprovato + ", pubblicatore=" + pubblicatore + "]";
	}
}
