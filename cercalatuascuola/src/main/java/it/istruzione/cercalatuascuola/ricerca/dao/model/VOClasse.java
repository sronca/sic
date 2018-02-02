package it.istruzione.cercalatuascuola.ricerca.dao.model;

public class VOClasse implements java.io.Serializable{
	private String descrizione = "";
	private String numeroClassi = "";
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNumeroClassi() {
		return numeroClassi;
	}
	public void setNumeroClassi(String numeroClassi) {
		this.numeroClassi = numeroClassi;
	}
	
}

