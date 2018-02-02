package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOEsameVotazione implements java.io.Serializable {

	private String codScuUt;	
	private String datAnnScoRil;
	private String desProEsa;
	private String numVotMedFas;
	
	public String getCodScuUt() {
		return codScuUt;
	}
	
	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}
	
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	public String getDesProEsa() {
		return desProEsa;
	}
	
	public void setDesProEsa(String desProEsa) {
		this.desProEsa = desProEsa;
	}
	
	public String getNumVotMedFas() {
		return numVotMedFas;
	}
	
	public void setNumVotMedFas(String numVotMedFas) {
		this.numVotMedFas = numVotMedFas;
	}
}
