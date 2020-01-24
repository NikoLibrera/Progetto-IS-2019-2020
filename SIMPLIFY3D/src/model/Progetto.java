package model;

import java.sql.Blob;

public class Progetto
{
	
	private int id_progetto, versione;
	private String titolo, descrizione, consigli, categoria, username;
	private Blob file_modello, immagine;
	
	public Progetto()
	{
		
	}
	
	
	
	public Progetto(int id_progetto, String titolo, String descrizione, Blob file_modello, Blob immagine,
			String consigli, String categoria, int versione, String username) 
	{
		super();
		this.id_progetto = id_progetto;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.file_modello = file_modello;
		this.immagine = immagine;
		this.consigli = consigli;
		this.categoria = categoria;
		this.versione = versione;
		this.username = username;
	}

	public Progetto(String titolo, String descrizione, Blob file_modello, Blob immagine, String consigli, String categoria, String username, int versione)
	{
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.file_modello = file_modello;
		this.immagine = immagine;
		this.consigli = consigli;
		this.categoria = categoria;
		this.username = username;
		this.versione=versione;
	}

	public int getId_progetto()
	{
		return id_progetto;
	}
	
	public void setId_progetto(int id_progetto)
	{
		this.id_progetto = id_progetto;
	}
	
	public String getTitolo() 
	{
		return titolo;
	}
	
	public void setTitolo(String titolo) 
	{
		this.titolo = titolo;
	}
	
	public String getDescrizione()
	{
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) 
	{
		this.descrizione = descrizione;
	}
	
	public Blob getFile_modello() 
	{
		return file_modello;
	}
	
	public void setFile_modello(Blob file_modello)
	{
		this.file_modello = file_modello;
	}
	
	public Blob getImmagine()
	{
		return immagine;
	}
	
	public void setImmagine(Blob immagine) 
	{
		this.immagine = immagine;
	}
	
	public String getConsigli() 
	{
		return consigli;
	}
	
	public void setConsigli(String consigli) 
	{
		this.consigli = consigli;
	}
	
	public String getCategoria() 
	{
		return categoria;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	
	public int getVersione()
	{
		return versione;
	}
	
	public void setVersione(int versione) 
	{
		this.versione = versione;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void incVersion()
	{
		this.versione++;
	}

	@Override
	public String toString() 
	{
		return "Progetto [id_progetto=" + id_progetto + ", titolo=" + titolo + ", descrizione=" + descrizione
				+ ", file_modello=" + file_modello + ", immagine=" + immagine + ", consigli=" + consigli
				+ ", categoria=" + categoria + ", versione=" + versione + ", username=" + username + "]";
	}
}
