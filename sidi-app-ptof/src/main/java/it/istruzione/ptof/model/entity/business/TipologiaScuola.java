package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipologiaScuola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_TIP_SCU")
	private String codTipScu;

	public String getCodTipScu() {
		return codTipScu;
	}

	public void setCodTipScu(String codTipScu) {
		this.codTipScu = codTipScu;
	}
	

	
	

}
