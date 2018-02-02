package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticolazioneClassiMMI implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="QTY_CL1_N")
	private BigDecimal cl1N;
	
	@Column(name="QTY_CL2_N")
	private BigDecimal cl2N;
	
	@Column(name="QTY_CL3_N")
	private BigDecimal cl3N;
	
	@Column(name="QTY_CL1_P")
	private BigDecimal cl1P;
	
	@Column(name="QTY_CL2_P")
	private BigDecimal cl2P;
	
	@Column(name="QTY_CL3_P")
	private BigDecimal cl3P;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	
}
