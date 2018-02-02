package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FabbisognoPostiCattedre implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="COD_SCU_ORD")
	private String codScuOrd;

	@Column(name="COD_CLC")
	private String codClc;

	@Column(name="DES_CLC")
	private String desClc;

	@Column(name="QTY_POS")
	private BigDecimal numPosti;

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

	public String getCodClc() {
		return codClc;
	}

	public void setCodClc(String codClc) {
		this.codClc = codClc;
	}

	public String getDesClc() {
		return desClc;
	}

	public void setDesClc(String desClc) {
		this.desClc = desClc;
	}

	public BigDecimal getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(BigDecimal numPosti) {
		this.numPosti = numPosti;
	}


	

}
