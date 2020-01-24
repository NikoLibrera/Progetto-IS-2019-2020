package model;

public class Valutazione 
{
	private int id_valutazione, voto, id_progetto;
	private String username;
	
	public Valutazione()
	{
		
	}
	
	public Valutazione(int id_valutazione, int voto, int id_progetto, String username)
	{
		this.id_valutazione = id_valutazione;
		this.voto = voto;
		this.id_progetto = id_progetto;
		this.username = username;
	}

	public int getId_valutazione() 
	{
		return id_valutazione;
	}

	public void setId_valutazione(int id_valutazione) 
	{
		this.id_valutazione = id_valutazione;
	}

	public int getVoto() 
	{
		return voto;
	}

	public void setVoto(int voto) 
	{
		this.voto = voto;
	}

	public int getId_progetto() 
	{
		return id_progetto;
	}

	public void setId_progetto(int id_progetto) 
	{
		this.id_progetto = id_progetto;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	@Override
	public String toString() 
	{
		return "Valutazione [id_valutazione=" + id_valutazione + ", voto=" + voto + ", id_progetto=" + id_progetto
				+ ", username=" + username + "]";
	}
	
	
}
