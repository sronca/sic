package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class AttivitaSostegnoDTO extends PtofItemsDTO {

	private String key;

	private String contenutiAttivitaSostegno, metodologieUtilizzate,
	coopServiziSSAssocSettore, aspettiSupportoLogistico, note;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public String getContenutiAttivitaSostegno() {
		return contenutiAttivitaSostegno;
	}

	public void setContenutiAttivitaSostegno(String contenutiAttivitaSostegno) {
		this.contenutiAttivitaSostegno = contenutiAttivitaSostegno;
	}

	public String getMetodologieUtilizzate() {
		return metodologieUtilizzate;
	}

	public void setMetodologieUtilizzate(String metodologieUtilizzate) {
		this.metodologieUtilizzate = metodologieUtilizzate;
	}

 	public String getAspettiSupportoLogistico() {
		return aspettiSupportoLogistico;
	}

	public void setAspettiSupportoLogistico(String aspettiSupportoLogistico) {
		this.aspettiSupportoLogistico = aspettiSupportoLogistico;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCoopServiziSSAssocSettore() {
		return coopServiziSSAssocSettore;
	}

	public void setCoopServiziSSAssocSettore(String coopServiziSSAssocSettore) {
		this.coopServiziSSAssocSettore = coopServiziSSAssocSettore;
	}
	
	

}
