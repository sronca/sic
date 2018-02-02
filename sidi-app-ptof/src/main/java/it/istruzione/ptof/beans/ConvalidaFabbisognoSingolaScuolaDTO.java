package it.istruzione.ptof.beans;

import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;

public class ConvalidaFabbisognoSingolaScuolaDTO extends DocumentoArchivioDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String regione;
	private String provincia;
	private String comune;
	private String codiceMecUtente;
	private String denominazione;


	public ConvalidaFabbisognoSingolaScuolaDTO() {
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getComune() {
		return comune;
	}


	public void setComune(String comune) {
		this.comune = comune;
	}


	public String getCodiceMecUtente() {
		return codiceMecUtente;
	}


	public void setCodiceMecUtente(String codiceMecUtente) {
		this.codiceMecUtente = codiceMecUtente;
	}


	public String getDenominazione() {
		return denominazione;
	}


	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}


	public String getRegione() {
		return regione;
	}


	public void setRegione(String regione) {
		this.regione = regione;
	}

	

}

