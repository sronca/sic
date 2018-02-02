package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TBS1006_TIPOSTATO database table.
 * 
 */
@Entity
@Table(name="TBS1006_TIPOSTATO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1006Tipostato.findAll", query="SELECT t FROM Tbs1006Tipostato t")
public class Tbs1006Tipostato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_STA")
	private String codSta;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_STA")
	private String desSta;

	public Tbs1006Tipostato() {
	}

	public String getCodSta() {
		return this.codSta;
	}

	public void setCodSta(String codSta) {
		this.codSta = codSta;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodUteUltMov() {
		return this.codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public Date getDatOraUltMov() {
		return this.datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesSta() {
		return this.desSta;
	}

	public void setDesSta(String desSta) {
		this.desSta = desSta;
	}

}