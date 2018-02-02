package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DotazioniMultimedialiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="COD_SCU_UTE_PRI")
	private String codScuUtePri;

	@Column(name="DAT_ANN_SCO_RIF")
	private long datAnnScoRif;

	public DotazioniMultimedialiPK() {
	}

	public String getCodScuUtePri() {
		return codScuUtePri;
	}

	public void setCodScuUtePri(String codScuUtePri) {
		this.codScuUtePri = codScuUtePri;
	}

	public long getDatAnnScoRif() {
		return datAnnScoRif;
	}

	public void setDatAnnScoRif(long datAnnScoRif) {
		this.datAnnScoRif = datAnnScoRif;
	}


	
	
	
	

}
