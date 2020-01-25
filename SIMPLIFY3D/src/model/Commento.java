package model;

public class Commento
{
	private int id_commento, id_progetto;
	private String contenuto, username;
	
	public Commento()
	{
		
	}
	
	public Commento(int id_commento, String contenuto, String username, int id_progetto)
	{
		this.id_commento = id_commento;
		this.contenuto = contenuto;
		this.username = username;
		this.id_progetto = id_progetto;
	}
	
	public Commento(String contenuto, String username, int id_progetto)
	{
		this.contenuto = contenuto;
		this.username = username;
		this.id_progetto = id_progetto;
	}

	public int getId_commento()
	{
		return id_commento;
	}

	public void setId_commento(int id_commento)
	{
		this.id_commento = id_commento;
	}

	public String getContenuto() 
	{
		return contenuto;
	}

	public void setContenuto(String contenuto) 
	{
		this.contenuto = contenuto;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public int getId_progetto()
	{
		return id_progetto;
	}

	public void setId_progetto(int id_progetto) 
	{
		this.id_progetto = id_progetto;
	}

	@Override
	public String toString() 
	{
		return "Commento [id_commento=" + id_commento + ", contenuto=" + contenuto + ", username=" + username
				+ ", id_progetto=" + id_progetto + "]";
	}	
}
