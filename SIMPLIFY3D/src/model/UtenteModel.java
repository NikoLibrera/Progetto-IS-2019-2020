package model;

import java.sql.SQLException;

public interface UtenteModel<T>
{
	public boolean doRegistrazione(T utente) throws SQLException;
}
