package model;

public class Email {
	private String messaggio;
	private String mittente;
	private String destinatario;
	
	
	
	public Email(String messaggio, String mittente, String destinatario) {
		super();
		this.messaggio = messaggio;
		this.mittente = mittente;
		this.destinatario = destinatario;
	}
	
	
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public String getMittente() {
		return mittente;
	}
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	
}
