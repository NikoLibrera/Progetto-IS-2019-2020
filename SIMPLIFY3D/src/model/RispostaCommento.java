package model;

public class RispostaCommento 
{
	private int id_risposta, id_commento;
	private String contenuto, username;
	
	public RispostaCommento()
	{
		
	}
	
	public RispostaCommento(int id_risposta, String contenuto, String username, int id_commento)
	{
		this.id_risposta = id_risposta;
		this.contenuto = contenuto;
		this.username = username;
		this.id_commento = id_commento;
	}
	
	public RispostaCommento(String contenuto, String username, int id_commento)
	{
		this.contenuto = contenuto;
		this.username = username;
		this.id_commento = id_commento;
	}

	public int getId_risposta() 
	{
		return id_risposta;
	}

	public void setId_risposta(int id_risposta)
	{
		this.id_risposta = id_risposta;
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

	@Override
	public String toString() 
	{
		return "RispostaCommento [id_risposta=" + id_risposta + ", id_commento=" + id_commento + ", contenuto="
				+ contenuto + ", username=" + username + "]";
	}	
}