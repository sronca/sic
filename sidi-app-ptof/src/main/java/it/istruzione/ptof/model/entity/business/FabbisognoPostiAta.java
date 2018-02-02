package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FabbisognoPostiAta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="COD_PRO_ATA")
	private String codProAta;

	@Column(name="DES_PRO_ATA")
	private String desProAta;

	@Column(name="QTY_POS")
	private BigDecimal numPosti;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodProAta() {
		return codProAta;
	}

	public void setCodProAta(String codProAta) {
		this.codProAta = codProAta;
	}

	public String getDesProAta() {
		return desProAta;
	}

	public void setDesProAta(String desProAta) {
		this.desProAta = desProAta;
	}

	public BigDecimal getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(BigDecimal numPosti) {
		this.numPosti = numPosti;
	}
	
	
	
	


	

}
