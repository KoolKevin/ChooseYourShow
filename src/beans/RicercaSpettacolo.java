package beans;

import java.util.Date;

public class RicercaSpettacolo {
	private int id;
	private String nomeSpettacolo;
	//private String nomeArtista;
	private Date dataSpettacolo;
	private Date inizioPeriodo;
	private Date finePeriodo;
	//questi gli ho fatti diventare stringhe al posto di fare una classe enum per colpa della rappresentazione del db
	private String tipoSpettacolo;
	private String genereSpettacolo;
	//qua invece di usare le classi metto solo il nome così dopo non devo recuperare gli oggetti dal db
	private String nomeCitta;
	private String nomeLocale;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeSpettacolo() {
		return nomeSpettacolo;
	}
	public void setNomeSpettacolo(String nomeSpettacolo) {
		this.nomeSpettacolo = nomeSpettacolo;
	}
	public Date getDataSpettacolo() {
		return dataSpettacolo;
	}
	public void setDataSpettacolo(Date dataSpettacolo) {
		this.dataSpettacolo = dataSpettacolo;
	}
	public Date getInizioPeriodo() {
		return inizioPeriodo;
	}
	public void setInizioPeriodo(Date inizioPeriodo) {
		this.inizioPeriodo = inizioPeriodo;
	}
	public Date getFinePeriodo() {
		return finePeriodo;
	}
	public void setFinePeriodo(Date finePeriodo) {
		this.finePeriodo = finePeriodo;
	}
	public String getTipoSpettacolo() {
		return tipoSpettacolo;
	}
	public void setTipoSpettacolo(String tipoSpettacolo) {
		this.tipoSpettacolo = tipoSpettacolo;
	}
	public String getGenereSpettacolo() {
		return genereSpettacolo;
	}
	public void setGenereSpettacolo(String genereSpettacolo) {
		this.genereSpettacolo = genereSpettacolo;
	}
	public String getNomeCitta() {
		return nomeCitta;
	}
	public void setNomeCitta(String nomeCitta) {
		this.nomeCitta = nomeCitta;
	}
	public String getNomeLocale() {
		return nomeLocale;
	}
	public void setNomeLocale(String nomeLocale) {
		this.nomeLocale = nomeLocale;
	}
	@Override
	public String toString() {
		return "RicercaSpettacolo [id=" + id + ", nomeSpettacolo=" + nomeSpettacolo + ", dataSpettacolo="
				+ dataSpettacolo + ", inizioPeriodo=" + inizioPeriodo + ", finePeriodo=" + finePeriodo
				+ ", tipoSpettacolo=" + tipoSpettacolo + ", genereSpettacolo=" + genereSpettacolo + ", nomeCitta="
				+ nomeCitta + ", nomeLocale=" + nomeLocale + "]";
	}
}
