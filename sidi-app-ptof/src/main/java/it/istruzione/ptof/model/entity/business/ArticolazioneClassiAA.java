package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticolazioneClassiAA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="QTA_SEZ_N")
	private BigDecimal sezioniN;
	
	@Column(name="QTA_SEZ_R")
	private BigDecimal sezioniR;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getSezioniN() {
		return sezioniN;
	}

	public void setSezioniN(BigDecimal sezioniN) {
		this.sezioniN = sezioniN;
	}

	public BigDecimal getSezioniR() {
		return sezioniR;
	}

	public void setSezioniR(BigDecimal sezioniR) {
		this.sezioniR = sezioniR;
	}
	
	

}
