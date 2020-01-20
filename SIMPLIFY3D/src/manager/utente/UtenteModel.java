package manager.utente;

import java.sql.SQLException;

import model.Utente;

public interface UtenteModel<T>
{
	public boolean doRegistrazione(T utente) throws SQLException;
	
	public Utente doLogin(String username, String password) throws SQLException;
	
	public Utente doPasswordDimenticata(T utente) throws SQLException;
	
	public void doModificaPassword(T utente) throws SQLException;
	
}
