package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.LinkedHashMap;


public class VOEdilizia implements Serializable{
	
	private LinkedHashMap<String, VOSezione> sezioni;

	public LinkedHashMap<String, VOSezione> getSezioni() {
		return sezioni;
	}

	public void setSezioni(LinkedHashMap<String, VOSezione> sezioni) {
		this.sezioni = sezioni;
	}

	
}
