package beans;

public class Pubblicatore {
	private int id;
	private String email;
	private String password;
	private String nomeOrganizzazione;
	private String nomeCompleto;
	private boolean flag_notifiche_app;
	private boolean flag_notifiche_mail;
	private boolean isAutorizzato;
	
	private Artista artistaGestito;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNomeOrganizzazione() {
		return nomeOrganizzazione;
	}

	public void setNomeOrganizzazione(String nomeOrganizzazione) {
		this.nomeOrganizzazione = nomeOrganizzazione;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public boolean isFlag_notifiche_app() {
		return flag_notifiche_app;
	}
	public char getFlag_notifiche_app() {
		if(this.flag_notifiche_app) {
			return 'y';
		}
		
		return 'n';
	}
	public void setFlag_notifiche_app(boolean flag_notifiche_app) {
		this.flag_notifiche_app = flag_notifiche_app;
	}

	public boolean isFlag_notifiche_mail() {
		return flag_notifiche_mail;
	}
	public char getFlag_notifiche_mail() {
		if(this.flag_notifiche_mail) {
			return 'y';
		}
		
		return 'n';
	}
	public void setFlag_notifiche_mail(boolean flag_notifiche_mail) {
		this.flag_notifiche_mail = flag_notifiche_mail;
	}

	public boolean isAutorizzato() {
		return isAutorizzato;
	}
	public char getAutorizzato() {
		if(this.isAutorizzato) {
			return 'y';
		}
		
		return 'n';
	}
	public void setAutorizzato(boolean isAutorizzato) {
		this.isAutorizzato = isAutorizzato;
	}

	public Artista getArtistaGestito() {
		return artistaGestito;
	}

	public void setArtistaGestito(Artista artistaGestito) {
		this.artistaGestito = artistaGestito;
	}

	@Override
	public String toString() {
		return "Pubblicatore [id=" + id + ", email=" + email + ", nomeOrganizzazione=" + nomeOrganizzazione
				+ ", nomeCompleto=" + nomeCompleto + ", flag_notifiche_app=" + flag_notifiche_app
				+ ", flag_notifiche_mail=" + flag_notifiche_mail + ", isAutorizzato=" + isAutorizzato
				+ ", artistaGestito=" + artistaGestito + "]";
	}	
	
}
