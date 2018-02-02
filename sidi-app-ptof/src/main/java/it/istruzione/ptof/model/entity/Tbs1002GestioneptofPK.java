package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TBS1002_GESTIONEPTOF database table.
 * 
 */
@Embeddable
public class Tbs1002GestioneptofPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_SCU_UTE")
	private String codScuUte;

	@Column(name="PER_ANN_SCO")
	private Long perAnnSco;

	@Column(name="PRG_GES_CAT_DOC", insertable=false, updatable=false)
	private Long prgGesCatDoc;

	public Tbs1002GestioneptofPK() {
	}
	public String getCodScuUte() {
		return this.codScuUte;
	}
	public void setCodScuUte(String codScuUte) {
		this.codScuUte = codScuUte;
	}
	public Long getPerAnnSco() {
		return this.perAnnSco;
	}
	public void setPerAnnSco(Long perAnnSco) {
		this.perAnnSco = perAnnSco;
	}
	public Long getPrgGesCatDoc() {
		return this.prgGesCatDoc;
	}
	public void setPrgGesCatDoc(Long prgGesCatDoc) {
		this.prgGesCatDoc = prgGesCatDoc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tbs1002GestioneptofPK)) {
			return false;
		}
		Tbs1002GestioneptofPK castOther = (Tbs1002GestioneptofPK)other;
		return 
			this.codScuUte.equals(castOther.codScuUte)
			&& (this.perAnnSco == castOther.perAnnSco)
			&& (this.prgGesCatDoc == castOther.prgGesCatDoc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codScuUte.hashCode();
		hash = hash * prime + ((int) (this.perAnnSco ^ (this.perAnnSco >>> 32)));
		hash = hash * prime + ((int) (this.prgGesCatDoc ^ (this.prgGesCatDoc >>> 32)));
		
		return hash;
	}
}