package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VORav24c1 implements Serializable{
	
	private String annoScolastico;
	private String area;
	private BigDecimal numCon;
	private BigDecimal prcCon;
	
	
	public String getAnnoScolastico() {
		return annoScolastico;
	}
	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public BigDecimal getNumCon() {
		return numCon;
	}
	public void setNumCon(BigDecimal numCon) {
		this.numCon = numCon;
	}
	public BigDecimal getPrcCon() {
		return prcCon;
	}
	public void setPrcCon(BigDecimal prcCon) {
		this.prcCon = prcCon;
	}
	
	
	}
