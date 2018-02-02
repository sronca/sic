package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOGrafico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codScuUt;
	
	private String codGra;
	
	private String desTitGra;
	
	private String desInfGra;
	
	private String desNotGra;
	
	private String codStaPubbUff;
	
	private String codStaPubbScu;
	
	private Integer annScoRif;
		
	public String getCodGra() {
		return codGra;
	}

	public void setCodGra(String codGra) {
		this.codGra = codGra;
	}
	
	public String getDesTitGra() {
		return desTitGra;
	}
	
	public void setDesTitGra(String desTitGra) {
		this.desTitGra = desTitGra;
	}
	
	public String getDesInfGra() {
		return desInfGra;
	}
	
	public void setDesInfGra(String desInfGra) {
		this.desInfGra = desInfGra;
	}

	public String getCodStaPubbUff() {
		return codStaPubbUff;
	}

	public void setCodStaPubbUff(String codStaPubbUff) {
		this.codStaPubbUff = codStaPubbUff;
	}

	public String getCodStaPubbScu() {
		return codStaPubbScu;
	}

	public void setCodStaPubbScu(String codStaPubbScu) {
		this.codStaPubbScu = codStaPubbScu;
	}

	public String getCodScuUt() {
		return codScuUt;
	}

	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}

	public String getDesNotGra() {
		return desNotGra;
	}

	public void setDesNotGra(String desNotGra) {
		this.desNotGra = desNotGra;
	}

	public Integer getAnnScoRif() {
		return annScoRif;
	}

	public void setAnnScoRif(Integer annScoRif) {
		this.annScoRif = annScoRif;
	}
	
}
