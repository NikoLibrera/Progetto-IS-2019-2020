package model;

import java.sql.Blob;

public class Notifica 
{
	private int id_notifica, id_commento, id_risposta, id_progetto, id_valutazione;
	private Blob immagine;
	private String titolo, tipo, username;
	private int isClicked;
	
	public Notifica()
	{
		this.isClicked = 0;
	}
	
	public Notifica(int id_notifica, Blob immagine, String titolo, String tipo, int isClicked,
					int id_commento, int id_risposta, int id_progetto, int id_valutazione, String username)
	{
		this.id_notifica = id_notifica;
		this.immagine = immagine;
		this.titolo = titolo;
		this.tipo = tipo;
		this.isClicked = isClicked;
		this.id_commento = id_commento;
		this.id_risposta = id_risposta;
		this.id_progetto = id_progetto;
		this.id_valutazione = id_valutazione;
		this.username = username;
	}

	
	
	public Notifica(int id_commento, int id_risposta, int id_progetto, int id_valutazione, Blob immagine, String titolo,
			String tipo, String username, int isClicked) {
		super();
		this.id_commento = id_commento;
		this.id_risposta = id_risposta;
		this.id_progetto = id_progetto;
		this.id_valutazione = id_valutazione;
		this.immagine = immagine;
		this.titolo = titolo;
		this.tipo = tipo;
		this.username = username;
		this.isClicked = isClicked;
	}
	
	public Notifica(int id_commento,int id_progetto, Blob immagine, String titolo,
			String tipo, String username, int isClicked) {
		super();
		this.id_commento = id_commento;
		this.id_progetto = id_progetto;
		this.immagine = immagine;
		this.titolo = titolo;
		this.tipo = tipo;
		this.username = username;
		this.isClicked = isClicked;
	}
	
	public Notifica( int id_risposta,  Blob immagine, int id_progetto,String titolo,
			String tipo, String username, int isClicked) {
		super();
		this.id_risposta = id_risposta;
		this.id_progetto = id_progetto;
		this.immagine = immagine;
		this.titolo = titolo;
		this.tipo = tipo;
		this.username = username;
		this.isClicked = isClicked;
	}
	
	public Notifica( int id_progetto,  Blob immagine, String titolo,
			String tipo, String username, int isClicked, int id_valutazione) {
		super();
		this.id_progetto = id_progetto;
		this.id_valutazione = id_valutazione;
		this.immagine = immagine;
		this.titolo = titolo;
		this.tipo = tipo;
		this.username = username;
		this.isClicked = isClicked;
	}

	public int getId_notifica() 
	{
		return id_notifica;
	}

	public void setId_notifica(int id_notifica) 
	{
		this.id_notifica = id_notifica;
	}

	public int getId_commento()
	{
		return id_commento;
	}

	public void setId_commento(int id_commento) 
	{
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

	public int getId_progetto() 
	{
		return id_progetto;
	}

	public void setId_progetto(int id_progetto) 
	{
		this.id_progetto = id_progetto;
	}

	public int getId_valutazione() 
	{
		return id_valutazione;
	}

	public void setId_valutazione(int id_valutazione) 
	{
		this.id_valutazione = id_valutazione;
	}

	public Blob getImmagine() 
	{
		return immagine;
	}

	public void setImmagine(Blob immagine) 
	{
		this.immagine = immagine;
	}

	public String getTitolo() 
	{
		return titolo;
	}

	public void setTitolo(String titolo) 
	{
		this.titolo = titolo;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public int isClicked() 
	{
		return isClicked;
	}

	public void setClicked(int isClicked) 
	{
		this.isClicked = isClicked;
	}

	@Override
	public String toString() 
	{
		return "Notifica [id_notifica=" + id_notifica + ", id_commento=" + id_commento + ", id_risposta=" + id_risposta
				+ ", id_progetto=" + id_progetto + ", id_valutazione=" + id_valutazione + ", immagine=" + immagine
				+ ", titolo=" + titolo + ", tipo=" + tipo + ", username=" + username + ", isClicked=" + isClicked + "]";
	}
}
