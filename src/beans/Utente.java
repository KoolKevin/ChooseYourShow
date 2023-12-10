package beans;

public class Utente {
	private int id;
	private String username;
	private String email;
	private String password;
	private boolean flag_notifiche_app;
	private boolean flag_notifiche_mail;
	private Citta citta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public Citta getCitta() {
		return citta;
	}
	public void setCitta(Citta citta) {
		this.citta = citta;
	}
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", flag_notifiche_app=" + flag_notifiche_app + ", flag_notifiche_mail=" + flag_notifiche_mail
				+ ", citta=" + citta + "]";
	}	
}
