package dao;

import beans.Amministratore;

public interface AmministratoreDAO {

	public void create(Amministratore a);

	public Amministratore read(int id);

	public boolean update(Amministratore u);

	public boolean delete(int id);
	
	public Amministratore read(String email, String password);
}
