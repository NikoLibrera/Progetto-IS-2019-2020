package model;

import java.sql.SQLException;

public interface UtenteModel<T>
{
	public boolean doRegistrazione(T utente) throws SQLException;
	
	public Utente doLogin(String username, String password) throws SQLException;
	
	public Utente doPasswordDimenticata(String username, String email) throws SQLException;
	
	public void doModificaPassword(T utente) throws SQLException;
}
