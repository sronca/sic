package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1026_ORGANRISORSEPTOF database table.
 * 
 */
@Entity
@Table(name="TBS1026_ORGANRISORSEPTOF", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1026Organrisorseptof.findAll", query="SELECT t FROM Tbs1026Organrisorseptof t")
public class Tbs1026Organrisorseptof implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1026_ORGANRISORSEPTOF_PRGORGRIS_GENERATOR", sequenceName="Q1026ORGANRISORSEPTOF", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1026_ORGANRISORSEPTOF_PRGORGRIS_GENERATOR")
	@Column(name="PRG_ORG_RIS")
	private Long prgOrgRis;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_NOT_ORG")
	private String desNotOrg;

	@Column(name="DES_NOT_RSP")
	private String desNotRsp;

	@Column(name="DES_RES_RUO")
	private String desResRuo;

	@Column(name="DES_RSP")
	private String desRsp;

	@Column(name="DES_RUO_ALT")
	private String desRuoAlt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	//bi-directional many-to-one association to Tbs1025Tiporuolo
	@ManyToOne
	@JoinColumn(name="COD_TIP_RUO")
	private Tbs1025Tiporuolo tbs1025Tiporuolo;

	public Tbs1026Organrisorseptof() {
	}

	public Long getPrgOrgRis() {
		return this.prgOrgRis;
	}

	public void setPrgOrgRis(Long prgOrgRis) {
		this.prgOrgRis = prgOrgRis;
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

	public String getDesNotOrg() {
		return this.desNotOrg;
	}

	public void setDesNotOrg(String desNotOrg) {
		this.desNotOrg = desNotOrg;
	}

	public String getDesNotRsp() {
		return this.desNotRsp;
	}

	public void setDesNotRsp(String desNotRsp) {
		this.desNotRsp = desNotRsp;
	}

	public String getDesResRuo() {
		return this.desResRuo;
	}

	public void setDesResRuo(String desResRuo) {
		this.desResRuo = desResRuo;
	}

	public String getDesRsp() {
		return this.desRsp;
	}

	public void setDesRsp(String desRsp) {
		this.desRsp = desRsp;
	}

	public String getDesRuoAlt() {
		return this.desRuoAlt;
	}

	public void setDesRuoAlt(String desRuoAlt) {
		this.desRuoAlt = desRuoAlt;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	public Tbs1025Tiporuolo getTbs1025Tiporuolo() {
		return this.tbs1025Tiporuolo;
	}

	public void setTbs1025Tiporuolo(Tbs1025Tiporuolo tbs1025Tiporuolo) {
		this.tbs1025Tiporuolo = tbs1025Tiporuolo;
	}

}