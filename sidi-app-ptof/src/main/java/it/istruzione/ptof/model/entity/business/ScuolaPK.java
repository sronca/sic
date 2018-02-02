package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScuolaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="COD_SCU_UT")
	private String codScuUt;

	@Column(name="DAT_ANN_SCO_RIL")
	private long datAnnScoRil;

	public ScuolaPK() {
	}

	public String getCodScuUt() {
		return codScuUt;
	}

	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}

	public long getDatAnnScoRil() {
		return datAnnScoRil;
	}

	public void setDatAnnScoRil(long datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	
	
	

}
