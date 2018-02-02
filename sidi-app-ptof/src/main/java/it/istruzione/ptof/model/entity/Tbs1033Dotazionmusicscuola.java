package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1033_DOTAZIONMUSICSCUOLA database table.
 * 
 */
@Entity
@Table(name="TBS1033_DOTAZIONMUSICSCUOLA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1033Dotazionmusicscuola.findAll", query="SELECT t FROM Tbs1033Dotazionmusicscuola t")
public class Tbs1033Dotazionmusicscuola implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tbs1033DotazionmusicscuolaPK id;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_SCU_UTE_PRI")
	private String codScuUtePri;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="NUM_CPT")
	private long numCpt;

	@Column(name="NUM_DIS_MOB")
	private long numDisMob;

	@Column(name="NUM_LIM")
	private long numLim;

	@Column(name="NUM_PRI")
	private long numPri;

	public Tbs1033Dotazionmusicscuola() {
	}

	public Tbs1033DotazionmusicscuolaPK getId() {
		return this.id;
	}

	public void setId(Tbs1033DotazionmusicscuolaPK id) {
		this.id = id;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodScuUtePri() {
		return this.codScuUtePri;
	}

	public void setCodScuUtePri(String codScuUtePri) {
		this.codScuUtePri = codScuUtePri;
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

	public long getNumCpt() {
		return this.numCpt;
	}

	public void setNumCpt(long numCpt) {
		this.numCpt = numCpt;
	}

	public long getNumDisMob() {
		return this.numDisMob;
	}

	public void setNumDisMob(long numDisMob) {
		this.numDisMob = numDisMob;
	}

	public long getNumLim() {
		return this.numLim;
	}

	public void setNumLim(long numLim) {
		this.numLim = numLim;
	}

	public long getNumPri() {
		return this.numPri;
	}

	public void setNumPri(long numPri) {
		this.numPri = numPri;
	}

}