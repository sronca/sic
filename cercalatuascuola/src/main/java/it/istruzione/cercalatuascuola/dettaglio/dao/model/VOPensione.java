package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOPensione implements java.io.Serializable{
	private Float perCes;
	private Float perCesReg;
	private Float perCesNaz;
	
	public Float getPerCes() {
		return perCes;
	}
	public void setPerCes(Float perCes) {
		this.perCes = perCes;
	}
	public Float getPerCesNaz() {
		return perCesNaz;
	}
	public void setPerCesNaz(Float perCesNaz) {
		this.perCesNaz = perCesNaz;
	}
	public Float getPerCesReg() {
		return perCesReg;
	}
	public void setPerCesReg(Float perCesReg) {
		this.perCesReg = perCesReg;
	}
}
