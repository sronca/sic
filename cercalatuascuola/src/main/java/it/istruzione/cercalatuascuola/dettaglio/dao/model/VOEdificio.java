package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.util.List;

public class VOEdificio {
	
	private String codiceEdificio;
	private List<VOAnagraficaScuola> scuole;
	private String indirizzo;
	private String indirizzoMarker;
	private String latitudine;
	private String longitudine;
	private String datAnnScoRil;
	
	public String getIndirizzoMarker() {
		return indirizzoMarker;
	}
	public void setIndirizzoMarker(String indirizzoMarker) {
		this.indirizzoMarker = indirizzoMarker;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public String getCodiceEdificio() {
		return codiceEdificio;
	}
	public void setCodiceEdificio(String codiceEdificio) {
		this.codiceEdificio = codiceEdificio;
	}
	public List<VOAnagraficaScuola> getScuole() {
		return scuole;
	}
	public void setScuole(List<VOAnagraficaScuola> scuole) {
		this.scuole = scuole;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	public int getNumeroPlessi() {
		return (scuole != null)?scuole.size():0;
	}
	

}
