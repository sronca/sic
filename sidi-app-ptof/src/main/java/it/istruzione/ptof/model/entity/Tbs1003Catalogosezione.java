package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1003_CATALOGOSEZIONE database table.
 * 
 */
@Entity
@Table(name="TBS1003_CATALOGOSEZIONE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1003Catalogosezione.findAll", query="SELECT t FROM Tbs1003Catalogosezione t")
public class Tbs1003Catalogosezione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1003_CATALOGOSEZIONE_PRGSEZSOTSEZ_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1003_CATALOGOSEZIONE_PRGSEZSOTSEZ_GENERATOR")
	@Column(name="PRG_SEZ_SOT_SEZ")
	private Long prgSezSotSez;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_SEZ_SOT_SEZ")
	private String codSezSotSez;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_SEZ")
	private String desSez;

	@Column(name="FLG_VAL")
	private String flgVal;

	@Column(name="PRG_ORD")
	private int prgOrd;

	@Column(name="PRG_SEZ_SOT_SEZ_PAD")
	private Long prgSezSotSezPad;
	
	@Column(name="PRG_SEZ_SOT_SEZ_PLE")
	private Long prgSezSotSezPle;
	
	@Column(name="DES_MSG_INT")
	private String desMsgInt;

	public Tbs1003Catalogosezione() {
	}

	public Long getPrgSezSotSez() {
		return this.prgSezSotSez;
	}

	public void setPrgSezSotSez(Long prgSezSotSez) {
		this.prgSezSotSez = prgSezSotSez;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodSezSotSez() {
		return this.codSezSotSez;
	}

	public void setCodSezSotSez(String codSezSotSez) {
		this.codSezSotSez = codSezSotSez;
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

	public String getDesSez() {
		return this.desSez;
	}

	public void setDesSez(String desSez) {
		this.desSez = desSez;
	}

	public String getFlgVal() {
		return this.flgVal;
	}

	public void setFlgVal(String flgVal) {
		this.flgVal = flgVal;
	}

	public int getPrgOrd() {
		return this.prgOrd;
	}

	public void setPrgOrd(int prgOrd) {
		this.prgOrd = prgOrd;
	}

	public Long getPrgSezSotSezPad() {
		return this.prgSezSotSezPad;
	}

	public void setPrgSezSotSezPad(Long prgSezSotSezPad) {
		this.prgSezSotSezPad = prgSezSotSezPad;
	}

	public Long getPrgSezSotSezPle() {
		return prgSezSotSezPle;
	}

	public void setPrgSezSotSezPle(Long prgSezSotSezPle) {
		this.prgSezSotSezPle = prgSezSotSezPle;
	}

	public String getDesMsgInt() {
		return desMsgInt;
	}

	public void setDesMsgInt(String desMsgInt) {
		this.desMsgInt = desMsgInt;
	}

	
}