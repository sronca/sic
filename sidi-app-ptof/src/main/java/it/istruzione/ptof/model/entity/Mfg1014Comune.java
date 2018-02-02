package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MFG1014_COMUNE database table.
 * 
 */
@Entity
@Table(name="MFG1014_COMUNE", schema = "UFGFUNGEN_OWN", catalog = "")
@NamedQuery(name="Mfg1014Comune.findAll", query="SELECT m FROM Mfg1014Comune m")
public class Mfg1014Comune implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_COM")
	private String codCom;

	@Column(name="COD_CAP")
	private String codCap;

	@Column(name="COD_CAT_COM")
	private String codCatCom;

	@Column(name="COD_IST")
	private BigDecimal codIst;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_PRV_NIS", referencedColumnName = "COD_PRV", nullable = false, insertable = false, updatable = false)
	private Mfg1029Provnuoist mfg1029Provnuoist;

	@Column(name="COD_REG_NAZ")
	private String codRegNaz;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_COM")
	private String desCom;

	@Column(name="FLG_PRV")
	private String flgPrv;

	@Column(name="FLG_STA_COM")
	private BigDecimal flgStaCom;

	@Column(name="ISTAT_103")
	private BigDecimal istat103;

	@Column(name="ISTAT_107")
	private BigDecimal istat107;

	@Column(name="ISTAT_ALFA")
	private String istatAlfa;

	@Column(name="ISTAT_NUM")
	private BigDecimal istatNum;

	public Mfg1014Comune() {
	}

	public String getCodCom() {
		return this.codCom;
	}

	public void setCodCom(String codCom) {
		this.codCom = codCom;
	}

	public String getCodCap() {
		return this.codCap;
	}

	public void setCodCap(String codCap) {
		this.codCap = codCap;
	}

	public String getCodCatCom() {
		return this.codCatCom;
	}

	public void setCodCatCom(String codCatCom) {
		this.codCatCom = codCatCom;
	}

	public BigDecimal getCodIst() {
		return this.codIst;
	}

	public void setCodIst(BigDecimal codIst) {
		this.codIst = codIst;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodRegNaz() {
		return this.codRegNaz;
	}

	public void setCodRegNaz(String codRegNaz) {
		this.codRegNaz = codRegNaz;
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

	public String getDesCom() {
		return this.desCom;
	}

	public void setDesCom(String desCom) {
		this.desCom = desCom;
	}

	public String getFlgPrv() {
		return this.flgPrv;
	}

	public void setFlgPrv(String flgPrv) {
		this.flgPrv = flgPrv;
	}

	public BigDecimal getFlgStaCom() {
		return this.flgStaCom;
	}

	public void setFlgStaCom(BigDecimal flgStaCom) {
		this.flgStaCom = flgStaCom;
	}

	public BigDecimal getIstat103() {
		return this.istat103;
	}

	public void setIstat103(BigDecimal istat103) {
		this.istat103 = istat103;
	}

	public BigDecimal getIstat107() {
		return this.istat107;
	}

	public void setIstat107(BigDecimal istat107) {
		this.istat107 = istat107;
	}

	public String getIstatAlfa() {
		return this.istatAlfa;
	}

	public void setIstatAlfa(String istatAlfa) {
		this.istatAlfa = istatAlfa;
	}

	public BigDecimal getIstatNum() {
		return this.istatNum;
	}

	public void setIstatNum(BigDecimal istatNum) {
		this.istatNum = istatNum;
	}

	public Mfg1029Provnuoist getMfg1029Provnuoist() {
		return mfg1029Provnuoist;
	}

	public void setMfg1029Provnuoist(Mfg1029Provnuoist mfg1029Provnuoist) {
		this.mfg1029Provnuoist = mfg1029Provnuoist;
	}

	
}