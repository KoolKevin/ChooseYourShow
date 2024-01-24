package beans;

import java.util.List;

public class Amministratore {
	private String email;
	private String password;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Amministratore [email=" + email + ", password=" + password + ", id=" + id + "]";
	}
	
}
