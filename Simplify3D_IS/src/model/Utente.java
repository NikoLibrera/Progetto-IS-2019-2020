package model;

import java.io.Serializable;
import java.sql.Date;

public class Utente implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String username, cognome, nome, password, email, nazionalita;
	
	private Date data_nascita;
	
	private boolean isAdmin;
	
	public Utente()
	{
		this.username = null;
		this.cognome = null;
		this.nome = null;
		this.data_nascita = null;
		this.isAdmin = false;
		this.password = null;
		this.email = null;
		this.nazionalita = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", cognome=" + cognome + ", nome=" + nome + ", password=" + password
				+ ", email=" + email + ", nazionalita=" + nazionalita + ", data_nascita=" + data_nascita + ", isAdmin="
				+ isAdmin + "]";
	}	
}
