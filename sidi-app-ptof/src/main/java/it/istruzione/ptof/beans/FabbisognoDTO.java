package it.istruzione.ptof.beans;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;


public class FabbisognoDTO extends DocumentoArchivioDTO {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String regione;
	private String siglaProvincia;
	private String comune;
	private String codiceMecUtente;
	private String denominazione;
	 
	 
	
	
	public FabbisognoDTO() {
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
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

 

 
 
}
