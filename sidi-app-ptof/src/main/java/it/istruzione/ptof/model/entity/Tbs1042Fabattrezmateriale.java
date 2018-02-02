package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1042_FABATTREZMATERIALE database table.
 * 
 */
@Entity
@Table(name="TBS1042_FABATTREZMATERIALE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1042Fabattrezmateriale.findAll", query="SELECT t FROM Tbs1042Fabattrezmateriale t")
public class Tbs1042Fabattrezmateriale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1042_FABATTREZMATERIALE_PRGFABATM_GENERATOR", sequenceName="Q1042FABATTREZMATERIALE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1042_FABATTREZMATERIALE_PRGFABATM_GENERATOR")
	@Column(name="PRG_FAB_ATM")
	private Long prgFabAtm;

	@Column(name="COD_ORD_SCU")
	private String codOrdScu;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_TIP_FAB")
	private String codTipFab;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Column(name="DAT_ANN_SCO_RIF")
	private long datAnnScoRif;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_ATM")
	private String desAtm;

	@Column(name="DES_FON_FIN")
	private String desFonFin;

	@Column(name="DES_STI_COS")
	private String desStiCos;

	@Column(name="DES_TIP_ATM")
	private String desTipAtm;

	@Column(name="NUM_ATM")
	private BigDecimal numAtm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1042Fabattrezmateriale() {
	}

	public Long getPrgFabAtm() {
		return this.prgFabAtm;
	}

	public void setPrgFabAtm(Long prgFabAtm) {
		this.prgFabAtm = prgFabAtm;
	}

	public String getCodOrdScu() {
		return this.codOrdScu;
	}

	public void setCodOrdScu(String codOrdScu) {
		this.codOrdScu = codOrdScu;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodTipFab() {
		return this.codTipFab;
	}

	public void setCodTipFab(String codTipFab) {
		this.codTipFab = codTipFab;
	}

	public String getCodUteUltMov() {
		return this.codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public long getDatAnnScoRif() {
		return this.datAnnScoRif;
	}

	public void setDatAnnScoRif(long datAnnScoRif) {
		this.datAnnScoRif = datAnnScoRif;
	}

	public Date getDatOraUltMov() {
		return this.datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesAreTemPsd() {
		return this.desAreTemPsd;
	}

	public void setDesAreTemPsd(String desAreTemPsd) {
		this.desAreTemPsd = desAreTemPsd;
	}

	public String getDesAtm() {
		return this.desAtm;
	}

	public void setDesAtm(String desAtm) {
		this.desAtm = desAtm;
	}

	public String getDesFonFin() {
		return this.desFonFin;
	}

	public void setDesFonFin(String desFonFin) {
		this.desFonFin = desFonFin;
	}

	public String getDesStiCos() {
		return this.desStiCos;
	}

	public void setDesStiCos(String desStiCos) {
		this.desStiCos = desStiCos;
	}

	public String getDesTipAtm() {
		return this.desTipAtm;
	}

	public void setDesTipAtm(String desTipAtm) {
		this.desTipAtm = desTipAtm;
	}

	public BigDecimal getNumAtm() {
		return this.numAtm;
	}

	public void setNumAtm(BigDecimal numAtm) {
		this.numAtm = numAtm;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}