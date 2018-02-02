package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class VOSezione implements Serializable {
	
	private String codice;
	private String descrizione;
	private int abilitato;
	private LinkedHashMap<String, VOCampo> campi;
	
	public LinkedHashMap<String, VOCampo> getCampi() {
		return campi;
	}
	public void setCampi(LinkedHashMap<String, VOCampo> campi) {
		this.campi = campi;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getAbilitato() {
		return abilitato;
	}
	public void setAbilitato(int abilitato) {
		this.abilitato = abilitato;
	}
	public int getNumeroDiCampiPresenti() {
		int count = 0;
		
		if (campi != null && !campi.isEmpty()){
			Iterator<VOCampo> it = campi.values().iterator();
			while (it.hasNext()){
				VOCampo campo = it.next();
				if (campo.getValore() != null && !campo.getValore().trim().equals(""))
					count ++;
			}
			
		}
		return count;
	}
	

}
