package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TBS1020_CLASSIFPROGETAMBITO database table.
 * 
 */
@Embeddable
public class Tbs1020ClassifprogetambitoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_TIP_AMB", insertable=false, updatable=false)
	private String codTipAmb;

	@Column(name="PRG_PGT_AMB")
	private Long prgPgtAmb;

	public Tbs1020ClassifprogetambitoPK() {
	}
	public String getCodTipAmb() {
		return this.codTipAmb;
	}
	public void setCodTipAmb(String codTipAmb) {
		this.codTipAmb = codTipAmb;
	}
	public Long getPrgPgtAmb() {
		return this.prgPgtAmb;
	}
	public void setPrgPgtAmb(Long prgPgtAmb) {
		this.prgPgtAmb = prgPgtAmb;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tbs1020ClassifprogetambitoPK)) {
			return false;
		}
		Tbs1020ClassifprogetambitoPK castOther = (Tbs1020ClassifprogetambitoPK)other;
		return 
			this.codTipAmb.equals(castOther.codTipAmb)
			&& (this.prgPgtAmb == castOther.prgPgtAmb);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codTipAmb.hashCode();
		hash = hash * prime + ((int) (this.prgPgtAmb ^ (this.prgPgtAmb >>> 32)));
		
		return hash;
	}
	
}