package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TBS1033_DOTAZIONMUSICSCUOLA database table.
 * 
 */
@Embeddable
public class Tbs1033DotazionmusicscuolaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_SCU_UTE")
	private String codScuUte;

	@Column(name="DAT_ANN_SCO_RIF")
	private long datAnnScoRif;

	public Tbs1033DotazionmusicscuolaPK() {
	}
	public String getCodScuUte() {
		return this.codScuUte;
	}
	public void setCodScuUte(String codScuUte) {
		this.codScuUte = codScuUte;
	}
	public long getDatAnnScoRif() {
		return this.datAnnScoRif;
	}
	public void setDatAnnScoRif(long datAnnScoRif) {
		this.datAnnScoRif = datAnnScoRif;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tbs1033DotazionmusicscuolaPK)) {
			return false;
		}
		Tbs1033DotazionmusicscuolaPK castOther = (Tbs1033DotazionmusicscuolaPK)other;
		return 
			this.codScuUte.equals(castOther.codScuUte)
			&& (this.datAnnScoRif == castOther.datAnnScoRif);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codScuUte.hashCode();
		hash = hash * prime + ((int) (this.datAnnScoRif ^ (this.datAnnScoRif >>> 32)));
		
		return hash;
	}
}