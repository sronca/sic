package it.istruzione.cercalatuascuola.dettaglio.dao.model;


public class VOEsameAlunni implements java.io.Serializable{
	private String codiceFasciaVoto = "";
	private String descrizioneFasciaVoto = "";
	private String numeroAlunni = "";
	
	public String getDescrizioneFasciaVoto() {
		return descrizioneFasciaVoto;
	}
	public void setDescrizioneFasciaVoto(String descrizioneFasciaVoto) {
		this.descrizioneFasciaVoto = descrizioneFasciaVoto;
	}
	public String getNumeroAlunni() {
		return numeroAlunni;
	}
	public void setNumeroAlunni(String numeroAlunni) {
		this.numeroAlunni = numeroAlunni;
	}
	public String getCodiceFasciaVoto() {
		return codiceFasciaVoto;
	}
	public void setCodiceFasciaVoto(String codiceFasciaVoto) {
		this.codiceFasciaVoto = codiceFasciaVoto;
	}
	
}
