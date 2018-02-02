package it.istruzione.cercalatuascuola.ricerca.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoCriteriPrecedenza {
	
	@JsonProperty("esito")
	private int esito;

	@JsonProperty("descrizioneEsito")
	private String descrizioneEsito;

	@JsonProperty("codiceScuola")
	private String codiceScuola;

	@JsonProperty("regoleIscrizione")
	private String regoleIscrizione;

	public int getEsito() {
		return esito;
	}

	public void setEsito(int esito) {
		this.esito = esito;
	}

	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public String getRegoleIscrizione() {
		return regoleIscrizione;
	}

	public void setRegoleIscrizione(String regoleIscrizione) {
		this.regoleIscrizione = regoleIscrizione;
	}
	
	




	
	


	
	

}
