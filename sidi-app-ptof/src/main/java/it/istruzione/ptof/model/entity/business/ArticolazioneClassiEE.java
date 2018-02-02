package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticolazioneClassiEE implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="QTY_CL1_N")
	private BigDecimal cl1N;
	
	
	@Column(name="QTY_CL2_N")
	private BigDecimal cl2N;
	
	
	@Column(name="QTY_CL3_N")
	private BigDecimal cl3N;
	
	
	@Column(name="QTY_CL4_N")
	private BigDecimal cl4N;
	
	
	@Column(name="QTY_CL5_N")
	private BigDecimal cl5N;
	
	
	@Column(name="QTY_PLU_N")
	private BigDecimal pluN;

	@Column(name="QTY_CL1_P")
	private BigDecimal cl1P;
	
	
	@Column(name="QTY_CL2_P")
	private BigDecimal cl2P;
	
	
	@Column(name="QTY_CL3_P")
	private BigDecimal cl3P;
	
	
	@Column(name="QTY_CL4_P")
	private BigDecimal cl4P;
	
	
	@Column(name="QTY_CL5_P")
	private BigDecimal cl5P;
	
	
	@Column(name="QTY_PLU_P")
	private BigDecimal pluP;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public BigDecimal getCl1N() {
		return cl1N;
	}


	public void setCl1N(BigDecimal cl1n) {
		cl1N = cl1n;
	}


	public BigDecimal getCl2N() {
		return cl2N;
	}


	public void setCl2N(BigDecimal cl2n) {
		cl2N = cl2n;
	}


	public BigDecimal getCl3N() {
		return cl3N;
	}


	public void setCl3N(BigDecimal cl3n) {
		cl3N = cl3n;
	}


	public BigDecimal getCl4N() {
		return cl4N;
	}


	public void setCl4N(BigDecimal cl4n) {
		cl4N = cl4n;
	}


	public BigDecimal getCl5N() {
		return cl5N;
	}


	public void setCl5N(BigDecimal cl5n) {
		cl5N = cl5n;
	}


	public BigDecimal getPluN() {
		return pluN;
	}


	public void setPluN(BigDecimal pluN) {
		this.pluN = pluN;
	}


	public BigDecimal getCl1P() {
		return cl1P;
	}


	public void setCl1P(BigDecimal cl1p) {
		cl1P = cl1p;
	}


	public BigDecimal getCl2P() {
		return cl2P;
	}


	public void setCl2P(BigDecimal cl2p) {
		cl2P = cl2p;
	}


	public BigDecimal getCl3P() {
		return cl3P;
	}


	public void setCl3P(BigDecimal cl3p) {
		cl3P = cl3p;
	}


	public BigDecimal getCl4P() {
		return cl4P;
	}


	public void setCl4P(BigDecimal cl4p) {
		cl4P = cl4p;
	}


	public BigDecimal getCl5P() {
		return cl5P;
	}


	public void setCl5P(BigDecimal cl5p) {
		cl5P = cl5p;
	}


	public BigDecimal getPluP() {
		return pluP;
	}


	public void setPluP(BigDecimal pluP) {
		this.pluP = pluP;
	}
	
	
	
	
	
}
