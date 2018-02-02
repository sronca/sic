package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.text.DecimalFormat;

public class VOAnniCorsoAlunni implements java.io.Serializable {
	private DecimalFormat df = new DecimalFormat("#.#");
	
	private String codScuUt;	
	private String datAnnScoRil;
	private String perAnnCor;
	private String numAlu;
	private String numCla;
	private Float aluPerCla;
	
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
	
	public String getPerAnnCor() {
		return perAnnCor;
	}
	
	public void setPerAnnCor(String perAnnCor) {
		this.perAnnCor = perAnnCor;
	}
	
	public String getNumAlu() {
		return numAlu;
	}
	
	public void setNumAlu(String numAlu) {
		this.numAlu = numAlu;
	}
	
	public String getNumCla() {
		return numCla;
	}
	
	public void setNumCla(String numCla) {
		this.numCla = numCla;
	}

	public String getAluPerClaFormatted() {
		return df.format(aluPerCla);
	}

	public Float getAluPerCla() {
		return aluPerCla;
	}

	public void setAluPerCla(Float aluPerCla) {
		this.aluPerCla = aluPerCla;
	}
}
