package it.istruzione.cercalatuascuola.dettaglio.dao.model;


public class VOFascia implements java.io.Serializable{
	private String descrizione = "";
	private Float ruolo;
	private Float noRuolo;
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Float getRuolo() {
		return ruolo;
	}
	public void setRuolo(Float ruolo) {
		this.ruolo = ruolo;
	}
	public Float getNoRuolo() {
		return noRuolo;
	}
	public void setNoRuolo(Float noRuolo) {
		this.noRuolo = noRuolo;
	}
}
