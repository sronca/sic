package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOTrasferimento implements java.io.Serializable {
	
	private Float perTraScu;
	private Float perTraReg;
	private Float perTraNaz;
	
	public Float getPerTraScu() {
		return perTraScu;
	}
	public void setPerTraScu(Float perTraScu) {
		this.perTraScu = perTraScu;
	}
	public Float getPerTraNaz() {
		return perTraNaz;
	}
	public void setPerTraNaz(Float perTraNaz) {
		this.perTraNaz = perTraNaz;
	}
	public Float getPerTraReg() {
		return perTraReg;
	}
	public void setPerTraReg(Float perTraReg) {
		this.perTraReg = perTraReg;
	}
}
