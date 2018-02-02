package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MFG1029_PROVNUOIST database table.
 * 
 */
@Entity
@Table(name="MFG1029_PROVNUOIST", schema = "UFGFUNGEN_OWN", catalog = "")
@NamedQuery(name="Mfg1029Provnuoist.findAll", query="SELECT m FROM Mfg1029Provnuoist m")
public class Mfg1029Provnuoist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_PRV")
	private String codPrv;

	@Column(name="COD_IST")
	private BigDecimal codIst;

	@Column(name="COD_IST_MET")
	private String codIstMet;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_REG", nullable = false, insertable = false, updatable = false)
	private Mfg1012Regione mfg1012Regione;

	@Column(name="COD_REG_NAZ")
	private String codRegNaz;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_PRV")
	private String desPrv;

	@Column(name="FLG_CAP_PRV")
	private String flgCapPrv;

	@Column(name="FLG_PTO_PRV_COM")
	private BigDecimal flgPtoPrvCom;
	
	@OneToMany(mappedBy="mfg1029Provnuoist",fetch = FetchType.LAZY)
	@OrderBy("desCom")
	private List<Mfg1014Comune> mfg1014Comunes;

	public Mfg1029Provnuoist() {
	}

	public String getCodPrv() {
		return this.codPrv;
	}

	public void setCodPrv(String codPrv) {
		this.codPrv = codPrv;
	}

	public BigDecimal getCodIst() {
		return this.codIst;
	}

	public void setCodIst(BigDecimal codIst) {
		this.codIst = codIst;
	}

	public String getCodIstMet() {
		return this.codIstMet;
	}

	public void setCodIstMet(String codIstMet) {
		this.codIstMet = codIstMet;
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

	public String getDesPrv() {
		return this.desPrv;
	}

	public void setDesPrv(String desPrv) {
		this.desPrv = desPrv;
	}

	public String getFlgCapPrv() {
		return this.flgCapPrv;
	}

	public void setFlgCapPrv(String flgCapPrv) {
		this.flgCapPrv = flgCapPrv;
	}

	public BigDecimal getFlgPtoPrvCom() {
		return this.flgPtoPrvCom;
	}

	public void setFlgPtoPrvCom(BigDecimal flgPtoPrvCom) {
		this.flgPtoPrvCom = flgPtoPrvCom;
	}

	public Mfg1012Regione getMfg1012Regione() {
		return mfg1012Regione;
	}

	public void setMfg1012Regione(Mfg1012Regione mfg1012Regione) {
		this.mfg1012Regione = mfg1012Regione;
	}

	public List<Mfg1014Comune> getMfg1014Comunes() {
		return mfg1014Comunes;
	}

	public void setMfg1014Comunes(List<Mfg1014Comune> mfg1014Comunes) {
		this.mfg1014Comunes = mfg1014Comunes;
	}

	
}