package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FabbisognoPostiSostegno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="COD_SCU_ORD")
	private String codScuOrd;

	@Column(name="QTY_POS_SOS_UD")
	private BigDecimal numPostiSosUD;
	
	@Column(name="QTY_POS_SOS_VI")
	private BigDecimal numPostiSosVI;
	
	@Column(name="QTY_POS_SOS_PF")
	private BigDecimal numPostiSosPF;
	
	@Column(name="QTY_POS_SOS_TOT")
	private BigDecimal numPostiSosTOT;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodScuOrd() {
		return codScuOrd;
	}

	public void setCodScuOrd(String codScuOrd) {
		this.codScuOrd = codScuOrd;
	}

	public BigDecimal getNumPostiSosUD() {
		return numPostiSosUD;
	}

	public void setNumPostiSosUD(BigDecimal numPostiSosUD) {
		this.numPostiSosUD = numPostiSosUD;
	}

	public BigDecimal getNumPostiSosVI() {
		return numPostiSosVI;
	}

	public void setNumPostiSosVI(BigDecimal numPostiSosVI) {
		this.numPostiSosVI = numPostiSosVI;
	}

	public BigDecimal getNumPostiSosPF() {
		return numPostiSosPF;
	}

	public void setNumPostiSosPF(BigDecimal numPostiSosPF) {
		this.numPostiSosPF = numPostiSosPF;
	}

	public BigDecimal getNumPostiSosTOT() {
		return numPostiSosTOT;
	}

	public void setNumPostiSosTOT(BigDecimal numPostiSosTOT) {
		this.numPostiSosTOT = numPostiSosTOT;
	}
	
	
	
	




	

}
