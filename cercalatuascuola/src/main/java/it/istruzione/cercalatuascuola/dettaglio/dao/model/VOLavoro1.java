package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VOLavoro1 implements Serializable{
	
	private String annoDiploma;
	private BigDecimal scu;
	private BigDecimal reg;
	private BigDecimal ita;
	
	public String getAnnoDiploma() {
		return annoDiploma;
	}
	public void setAnnoDiploma(String annoDiploma) {
		this.annoDiploma = annoDiploma;
	}
	public BigDecimal getScu() {
		return scu;
	}
	public void setScu(BigDecimal scu) {
		this.scu = scu;
	}
	public BigDecimal getReg() {
		return reg;
	}
	public void setReg(BigDecimal reg) {
		this.reg = reg;
	}
	public BigDecimal getIta() {
		return ita;
	}
	public void setIta(BigDecimal ita) {
		this.ita = ita;
	}
	
	
	
}
