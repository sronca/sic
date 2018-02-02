package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TBS1043_FABDECRETOINTERMINPER database table.
 * 
 */
@Embeddable
public class Tbs1043FabdecretointerminperPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRG_GES_CAT_DOC", insertable=false, updatable=false)
	private Long prgGesCatDoc;

	@Column(name="COD_REG")
	private String codReg;

	@Column(name="COD_RIF_DEC")
	private String codRifDec;

	public Tbs1043FabdecretointerminperPK() {
	}
	public Long getPrgGesCatDoc() {
		return this.prgGesCatDoc;
	}
	public void setPrgGesCatDoc(Long prgGesCatDoc) {
		this.prgGesCatDoc = prgGesCatDoc;
	}
	public String getCodReg() {
		return this.codReg;
	}
	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}
	public String getCodRifDec() {
		return this.codRifDec;
	}
	public void setCodRifDec(String codRifDec) {
		this.codRifDec = codRifDec;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tbs1043FabdecretointerminperPK)) {
			return false;
		}
		Tbs1043FabdecretointerminperPK castOther = (Tbs1043FabdecretointerminperPK)other;
		return 
			(this.prgGesCatDoc == castOther.prgGesCatDoc)
			&& this.codReg.equals(castOther.codReg)
			&& this.codRifDec.equals(castOther.codRifDec);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.prgGesCatDoc ^ (this.prgGesCatDoc >>> 32)));
		hash = hash * prime + this.codReg.hashCode();
		hash = hash * prime + this.codRifDec.hashCode();
		
		return hash;
	}
}