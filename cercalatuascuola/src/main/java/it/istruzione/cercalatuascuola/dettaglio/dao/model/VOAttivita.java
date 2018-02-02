package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOAttivita implements Serializable {

	private String codScuUt;
	private String codScuUtPri;
	private String datAnnScoRil;
	private String desAtt;
	private String prgAtt;
	private String desNot;
	private String numAlu;
	private String codStaPubb;
	
	public String getCodScuUt() {
		return codScuUt;
	}
	
	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}
	
	public String getCodScuUtPri() {
		return codScuUtPri;
	}
	
	public void setCodScuUtPri(String codScuUtPri) {
		this.codScuUtPri = codScuUtPri;
	}
	
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	public String getDesAtt() {
		return desAtt;
	}
	
	public void setDesAtt(String desAtt) {
		this.desAtt = desAtt;
	}
	
	public String getPrgAtt() {
		return prgAtt;
	}
	
	public void setPrgAtt(String prgAtt) {
		this.prgAtt = prgAtt;
	}
	
	public String getDesNot() {
		return desNot;
	}
	
	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}
	
	public String getNumAlu() {
		return numAlu;
	}
	
	public void setNumAlu(String numAlu) {
		this.numAlu = numAlu;
	}
	
	public String getCodStaPubb() {
		return codStaPubb;
	}
	
	public void setCodStaPubb(String codStaPubb) {
		this.codStaPubb = codStaPubb;
	}
}