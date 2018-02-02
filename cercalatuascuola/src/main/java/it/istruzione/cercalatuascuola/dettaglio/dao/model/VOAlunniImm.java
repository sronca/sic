package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOAlunniImm implements java.io.Serializable {

	private String percentStudentiImm;
	private String percentStudentiNonImm;
	
	public String getPercentStudentiImm() {
		return percentStudentiImm;
	}
	
	public void setPercentStudentiImm(String percentStudentiImm) {
		this.percentStudentiImm = percentStudentiImm;
	}
	
	public String getPercentStudentiNonImm() {
		return percentStudentiNonImm;
	}
	
	public void setPercentStudentiNonImm(String percentStudentiNonImm) {
		this.percentStudentiNonImm = percentStudentiNonImm;
	}
}