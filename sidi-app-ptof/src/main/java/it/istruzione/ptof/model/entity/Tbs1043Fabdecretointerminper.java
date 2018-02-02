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
 * The persistent class for the TBS1043_FABDECRETOINTERMINPER database table.
 * 
 */
@Entity
@Table(name="TBS1043_FABDECRETOINTERMINPER")
@NamedQuery(name="Tbs1043Fabdecretointerminper.findAll", query="SELECT t FROM Tbs1043Fabdecretointerminper t")
public class Tbs1043Fabdecretointerminper implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tbs1043FabdecretointerminperPK id;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_REG")
	private String desReg;

	@Column(name="NUM_POS_COM")
	private Long numPosCom;

	@Column(name="NUM_POS_PTZ_IGR")
	private Long numPosPtzIgr;

	@Column(name="NUM_POS_PTZ_PRI")
	private Long numPosPtzPri;

	@Column(name="NUM_POS_PTZ_SGR")
	private Long numPosPtzSgr;

	@Column(name="NUM_POS_PTZ_SOS")
	private Long numPosPtzSos;

	@Column(name="NUM_POS_SOS")
	private Long numPosSos;

	@Column(name="NUM_TOT_POS_COM")
	private Long numTotPosCom;

	@Column(name="NUM_TOT_POS_PTZ")
	private Long numTotPosPtz;

	@Column(name="NUM_TOT_POS_SOS")
	private Long numTotPosSos;

	@Column(name="PRG_DEC")
	private Long prgDec;

	public Tbs1043Fabdecretointerminper() {
	}

	public Tbs1043FabdecretointerminperPK getId() {
		return this.id;
	}

	public void setId(Tbs1043FabdecretointerminperPK id) {
		this.id = id;
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

	public String getDesReg() {
		return this.desReg;
	}

	public void setDesReg(String desReg) {
		this.desReg = desReg;
	}

	public Long getNumPosCom() {
		return this.numPosCom;
	}

	public void setNumPosCom(Long numPosCom) {
		this.numPosCom = numPosCom;
	}

	public Long getNumPosPtzIgr() {
		return this.numPosPtzIgr;
	}

	public void setNumPosPtzIgr(Long numPosPtzIgr) {
		this.numPosPtzIgr = numPosPtzIgr;
	}

	public Long getNumPosPtzPri() {
		return this.numPosPtzPri;
	}

	public void setNumPosPtzPri(Long numPosPtzPri) {
		this.numPosPtzPri = numPosPtzPri;
	}

	public Long getNumPosPtzSgr() {
		return this.numPosPtzSgr;
	}

	public void setNumPosPtzSgr(Long numPosPtzSgr) {
		this.numPosPtzSgr = numPosPtzSgr;
	}

	public Long getNumPosPtzSos() {
		return this.numPosPtzSos;
	}

	public void setNumPosPtzSos(Long numPosPtzSos) {
		this.numPosPtzSos = numPosPtzSos;
	}

	public Long getNumPosSos() {
		return this.numPosSos;
	}

	public void setNumPosSos(Long numPosSos) {
		this.numPosSos = numPosSos;
	}

	public Long getNumTotPosCom() {
		return this.numTotPosCom;
	}

	public void setNumTotPosCom(Long numTotPosCom) {
		this.numTotPosCom = numTotPosCom;
	}

	public Long getNumTotPosPtz() {
		return this.numTotPosPtz;
	}

	public void setNumTotPosPtz(Long numTotPosPtz) {
		this.numTotPosPtz = numTotPosPtz;
	}

	public Long getNumTotPosSos() {
		return this.numTotPosSos;
	}

	public void setNumTotPosSos(Long numTotPosSos) {
		this.numTotPosSos = numTotPosSos;
	}

	public Long getPrgDec() {
		return this.prgDec;
	}

	public void setPrgDec(Long prgDec) {
		this.prgDec = prgDec;
	}

}