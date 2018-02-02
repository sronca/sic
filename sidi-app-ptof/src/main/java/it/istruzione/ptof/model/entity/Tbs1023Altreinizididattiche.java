package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1023_ALTREINIZIDIDATTICHE database table.
 * 
 */
@Entity
@Table(name="TBS1023_ALTREINIZIDIDATTICHE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1023Altreinizididattiche.findAll", query="SELECT t FROM Tbs1023Altreinizididattiche t")
public class Tbs1023Altreinizididattiche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1023_ALTREINIZIDIDATTICHE_PRGALTINIDID_GENERATOR", sequenceName="Q1023ALTREINIZIDIDATTICHE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1023_ALTREINIZIDIDATTICHE_PRGALTINIDID_GENERATOR")
	@Column(name="PRG_ALT_INI_DID")
	private Long prgAltIniDid;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_FIN_VAL")
	private Date datFinVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_INI_VAL")
	private Date datIniVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PNS")
	private String desAreTemPns;

	@Column(name="DES_CNU")
	private String desCnu;

	@Column(name="DES_MOD")
	private String desMod;

	@Column(name="DES_NOT")
	private String desNot;

	@Column(name="DES_OBI")
	private String desObi;

	@Column(name="DES_TIT")
	private String desTit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1023Altreinizididattiche() {
	}

	public Long getPrgAltIniDid() {
		return this.prgAltIniDid;
	}

	public void setPrgAltIniDid(Long prgAltIniDid) {
		this.prgAltIniDid = prgAltIniDid;
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

	public Date getDatFinVal() {
		return this.datFinVal;
	}

	public void setDatFinVal(Date datFinVal) {
		this.datFinVal = datFinVal;
	}

	public Date getDatIniVal() {
		return this.datIniVal;
	}

	public void setDatIniVal(Date datIniVal) {
		this.datIniVal = datIniVal;
	}

	public Date getDatOraUltMov() {
		return this.datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesAreTemPns() {
		return this.desAreTemPns;
	}

	public void setDesAreTemPns(String desAreTemPns) {
		this.desAreTemPns = desAreTemPns;
	}

	public String getDesCnu() {
		return this.desCnu;
	}

	public void setDesCnu(String desCnu) {
		this.desCnu = desCnu;
	}

	public String getDesMod() {
		return this.desMod;
	}

	public void setDesMod(String desMod) {
		this.desMod = desMod;
	}

	public String getDesNot() {
		return this.desNot;
	}

	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}

	public String getDesObi() {
		return this.desObi;
	}

	public void setDesObi(String desObi) {
		this.desObi = desObi;
	}

	public String getDesTit() {
		return this.desTit;
	}

	public void setDesTit(String desTit) {
		this.desTit = desTit;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}