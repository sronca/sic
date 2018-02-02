package it.istruzione.cercalatuascuola.ricerca.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VOServizio implements Serializable {

	private String codSer;
	private String desSer;
	
	private List<VOAttivitaServizio> attivitaServizio = new ArrayList<VOAttivitaServizio>();
	
	public String getCodSer() {
		return codSer;
	}
	
	public void setCodSer(String codSer) {
		this.codSer = codSer;
	}
	
	public String getDesSer() {
		return desSer;
	}
	
	public void setDesSer(String desSer) {
		this.desSer = desSer;
	}

	public List<VOAttivitaServizio> getAttivitaServizio() {
		return attivitaServizio;
	}

	public void setAttivitaServizio(List<VOAttivitaServizio> attivitaServizio) {
		this.attivitaServizio = attivitaServizio;
	}
}
