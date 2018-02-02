package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticolazioneClassiMMII implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="DES_SET_ECO")
	private String desSetEco;

	@Column(name="QTY_CL1")
	private BigDecimal cl1;
	
	@Column(name="QTY_CL2")
	private BigDecimal cl2;

	@Column(name="QTY_CL3")
	private BigDecimal cl3;
	
	@Column(name="QTY_CL4")
	private BigDecimal cl4;
	
	@Column(name="QTY_CL5")
	private BigDecimal cl5;
	
	@Column(name="QTY_CL6")
	private BigDecimal cl6;

	public String getDesSetEco() {
		return desSetEco;
	}

	public void setDesSetEco(String desSetEco) {
		this.desSetEco = desSetEco;
	}

	public BigDecimal getCl1() {
		return cl1;
	}

	public void setCl1(BigDecimal cl1) {
		this.cl1 = cl1;
	}

	public BigDecimal getCl2() {
		return cl2;
	}

	public void setCl2(BigDecimal cl2) {
		this.cl2 = cl2;
	}

	public BigDecimal getCl3() {
		return cl3;
	}

	public void setCl3(BigDecimal cl3) {
		this.cl3 = cl3;
	}

	public BigDecimal getCl4() {
		return cl4;
	}

	public void setCl4(BigDecimal cl4) {
		this.cl4 = cl4;
	}

	public BigDecimal getCl5() {
		return cl5;
	}

	public void setCl5(BigDecimal cl5) {
		this.cl5 = cl5;
	}

	public BigDecimal getCl6() {
		return cl6;
	}

	public void setCl6(BigDecimal cl6) {
		this.cl6 = cl6;
	}
	
	
	

}
