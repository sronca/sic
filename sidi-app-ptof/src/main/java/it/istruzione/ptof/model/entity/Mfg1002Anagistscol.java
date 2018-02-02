package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the MFG1002_ANAGISTSCOL database table.
 * 
 */
@Entity
@Table(name="MFG1002_ANAGISTSCOL", schema = "UFGFUNGEN_OWN", catalog = "")
@NamedQuery(name="Mfg1002Anagistscol.findAll", query="SELECT m FROM Mfg1002Anagistscol m")
public class Mfg1002Anagistscol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mfg1002AnagistscolPK id;

	@Column(name="COD_ABI_IBA")
	private String codAbiIba;

	@Column(name="COD_AMB_TER")
	private String codAmbTer;

	@Column(name="COD_CAB_IBA")
	private String codCabIba;

	@Column(name="COD_CAP_SCU")
	private String codCapScu;

	@Column(name="COD_CAR_SCU")
	private String codCarScu;

	@Column(name="COD_CHE_IBA")
	private String codCheIba;

	@Column(name="COD_CIN_IBA")
	private String codCinIba;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_COM", nullable = false, insertable = false, updatable = false)
	private Mfg1014Comune mfg1014Comune;

	@Column(name="COD_CON_IBA")
	private String codConIba;

	@Column(name="COD_DIS")
	private String codDis;

	@Column(name="COD_FAX_SCU")
	private String codFaxScu;

	@Column(name="COD_FIS")
	private String codFis;

	@Column(name="COD_FOR_SCU_APP")
	private String codForScuApp;

	@Column(name="COD_IST_RIF_ORG")
	private String codIstRifOrg;

	@Column(name="COD_PAE_IBA")
	private String codPaeIba;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_PRV_SCU")
	private String codPrvScu;

	@Column(name="COD_SCU_ANN")
	private String codScuAnn;

	@Column(name="COD_SCU_UT_PRI")
	private String codScuUtPri;

	@Column(name="COD_SED_AMM_OMN")
	private String codSedAmmOmn;

	@Column(name="COD_SED_AMM_SNS")
	private String codSedAmmSns;

	@Column(name="COD_TEL_SCU")
	private String codTelScu;

	@Column(name="COD_TIP_SIT")
	private String codTipSit;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_FIN_SOS_SNS")
	private Date datFinSosSns;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_FIN_VAL")
	private Date datFinVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_INI_VAL")
	private Date datIniVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DEN_SCU_PRI")
	private String denScuPri;

	@Column(name="DES_ENT_GES")
	private String desEntGes;

	@Column(name="DES_IND_EMA_PCE")
	private String desIndEmaPce;

	@Column(name="DES_IND_EML")
	private String desIndEml;

	@Column(name="DES_IND_SCU")
	private String desIndScu;

	@Column(name="DES_IND_WEB")
	private String desIndWeb;

	@Column(name="DES_LOC_SCU")
	private String desLocScu;

	@Column(name="DES_NOM_SCU")
	private String desNomScu;

	@Column(name="DES_TIP_IST")
	private String desTipIst;

	@Column(name="FLG_AUT_SCU")
	private String flgAutScu;

	@Column(name="FLG_CAP")
	private String flgCap;

	@Column(name="FLG_IST_ADU")
	private String flgIstAdu;

	@Column(name="FLG_IST_STA")
	private String flgIstSta;

	@Column(name="FLG_ONL")
	private String flgOnl;

	@Column(name="FLG_SCU_EUR")
	private String flgScuEur;

	@Column(name="FLG_SCU_PAR")
	private String flgScuPar;

	@Column(name="FLG_SED_AMM_OMN")
	private String flgSedAmmOmn;

	@Column(name="FLG_SED_DIR")
	private String flgSedDir;

	@Column(name="FLG_SED_IRC")
	private String flgSedIrc;

	@Column(name="FLG_SED_ORG")
	private String flgSedOrg;

	@Column(name="FLG_SED_ORG_ATA")
	private String flgSedOrgAta;

	@Column(name="FLG_SED_ORG_PED")
	private String flgSedOrgPed;

	@Column(name="FLG_SOS_SNS")
	private String flgSosSns;

	public Mfg1002Anagistscol() {
	}

	public Mfg1002AnagistscolPK getId() {
		return this.id;
	}

	public void setId(Mfg1002AnagistscolPK id) {
		this.id = id;
	}

	public String getCodAbiIba() {
		return this.codAbiIba;
	}

	public void setCodAbiIba(String codAbiIba) {
		this.codAbiIba = codAbiIba;
	}

	public String getCodAmbTer() {
		return this.codAmbTer;
	}

	public void setCodAmbTer(String codAmbTer) {
		this.codAmbTer = codAmbTer;
	}

	public String getCodCabIba() {
		return this.codCabIba;
	}

	public void setCodCabIba(String codCabIba) {
		this.codCabIba = codCabIba;
	}

	public String getCodCapScu() {
		return this.codCapScu;
	}

	public void setCodCapScu(String codCapScu) {
		this.codCapScu = codCapScu;
	}

	public String getCodCarScu() {
		return this.codCarScu;
	}

	public void setCodCarScu(String codCarScu) {
		this.codCarScu = codCarScu;
	}

	public String getCodCheIba() {
		return this.codCheIba;
	}

	public void setCodCheIba(String codCheIba) {
		this.codCheIba = codCheIba;
	}

	public String getCodCinIba() {
		return this.codCinIba;
	}

	public void setCodCinIba(String codCinIba) {
		this.codCinIba = codCinIba;
	}

	public Mfg1014Comune getMfg1014Comune() {
		return mfg1014Comune;
	}

	public void setMfg1014Comune(Mfg1014Comune mfg1014Comune) {
		this.mfg1014Comune = mfg1014Comune;
	}

	public String getCodConIba() {
		return this.codConIba;
	}

	public void setCodConIba(String codConIba) {
		this.codConIba = codConIba;
	}

	public String getCodDis() {
		return this.codDis;
	}

	public void setCodDis(String codDis) {
		this.codDis = codDis;
	}

	public String getCodFaxScu() {
		return this.codFaxScu;
	}

	public void setCodFaxScu(String codFaxScu) {
		this.codFaxScu = codFaxScu;
	}

	public String getCodFis() {
		return this.codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getCodForScuApp() {
		return this.codForScuApp;
	}

	public void setCodForScuApp(String codForScuApp) {
		this.codForScuApp = codForScuApp;
	}

	public String getCodIstRifOrg() {
		return this.codIstRifOrg;
	}

	public void setCodIstRifOrg(String codIstRifOrg) {
		this.codIstRifOrg = codIstRifOrg;
	}

	public String getCodPaeIba() {
		return this.codPaeIba;
	}

	public void setCodPaeIba(String codPaeIba) {
		this.codPaeIba = codPaeIba;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodPrvScu() {
		return this.codPrvScu;
	}

	public void setCodPrvScu(String codPrvScu) {
		this.codPrvScu = codPrvScu;
	}

	public String getCodScuAnn() {
		return this.codScuAnn;
	}

	public void setCodScuAnn(String codScuAnn) {
		this.codScuAnn = codScuAnn;
	}

	public String getCodScuUtPri() {
		return this.codScuUtPri;
	}

	public void setCodScuUtPri(String codScuUtPri) {
		this.codScuUtPri = codScuUtPri;
	}

	public String getCodSedAmmOmn() {
		return this.codSedAmmOmn;
	}

	public void setCodSedAmmOmn(String codSedAmmOmn) {
		this.codSedAmmOmn = codSedAmmOmn;
	}

	public String getCodSedAmmSns() {
		return this.codSedAmmSns;
	}

	public void setCodSedAmmSns(String codSedAmmSns) {
		this.codSedAmmSns = codSedAmmSns;
	}

	public String getCodTelScu() {
		return this.codTelScu;
	}

	public void setCodTelScu(String codTelScu) {
		this.codTelScu = codTelScu;
	}

	public String getCodTipSit() {
		return this.codTipSit;
	}

	public void setCodTipSit(String codTipSit) {
		this.codTipSit = codTipSit;
	}

	public String getCodUteUltMov() {
		return this.codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public Date getDatFinSosSns() {
		return this.datFinSosSns;
	}

	public void setDatFinSosSns(Date datFinSosSns) {
		this.datFinSosSns = datFinSosSns;
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

	public String getDenScuPri() {
		return this.denScuPri;
	}

	public void setDenScuPri(String denScuPri) {
		this.denScuPri = denScuPri;
	}

	public String getDesEntGes() {
		return this.desEntGes;
	}

	public void setDesEntGes(String desEntGes) {
		this.desEntGes = desEntGes;
	}

	public String getDesIndEmaPce() {
		return this.desIndEmaPce;
	}

	public void setDesIndEmaPce(String desIndEmaPce) {
		this.desIndEmaPce = desIndEmaPce;
	}

	public String getDesIndEml() {
		return this.desIndEml;
	}

	public void setDesIndEml(String desIndEml) {
		this.desIndEml = desIndEml;
	}

	public String getDesIndScu() {
		return this.desIndScu;
	}

	public void setDesIndScu(String desIndScu) {
		this.desIndScu = desIndScu;
	}

	public String getDesIndWeb() {
		return this.desIndWeb;
	}

	public void setDesIndWeb(String desIndWeb) {
		this.desIndWeb = desIndWeb;
	}

	public String getDesLocScu() {
		return this.desLocScu;
	}

	public void setDesLocScu(String desLocScu) {
		this.desLocScu = desLocScu;
	}

	public String getDesNomScu() {
		return this.desNomScu;
	}

	public void setDesNomScu(String desNomScu) {
		this.desNomScu = desNomScu;
	}

	public String getDesTipIst() {
		return this.desTipIst;
	}

	public void setDesTipIst(String desTipIst) {
		this.desTipIst = desTipIst;
	}

	public String getFlgAutScu() {
		return this.flgAutScu;
	}

	public void setFlgAutScu(String flgAutScu) {
		this.flgAutScu = flgAutScu;
	}

	public String getFlgCap() {
		return this.flgCap;
	}

	public void setFlgCap(String flgCap) {
		this.flgCap = flgCap;
	}

	public String getFlgIstAdu() {
		return this.flgIstAdu;
	}

	public void setFlgIstAdu(String flgIstAdu) {
		this.flgIstAdu = flgIstAdu;
	}

	public String getFlgIstSta() {
		return this.flgIstSta;
	}

	public void setFlgIstSta(String flgIstSta) {
		this.flgIstSta = flgIstSta;
	}

	public String getFlgOnl() {
		return this.flgOnl;
	}

	public void setFlgOnl(String flgOnl) {
		this.flgOnl = flgOnl;
	}

	public String getFlgScuEur() {
		return this.flgScuEur;
	}

	public void setFlgScuEur(String flgScuEur) {
		this.flgScuEur = flgScuEur;
	}

	public String getFlgScuPar() {
		return this.flgScuPar;
	}

	public void setFlgScuPar(String flgScuPar) {
		this.flgScuPar = flgScuPar;
	}

	public String getFlgSedAmmOmn() {
		return this.flgSedAmmOmn;
	}

	public void setFlgSedAmmOmn(String flgSedAmmOmn) {
		this.flgSedAmmOmn = flgSedAmmOmn;
	}

	public String getFlgSedDir() {
		return this.flgSedDir;
	}

	public void setFlgSedDir(String flgSedDir) {
		this.flgSedDir = flgSedDir;
	}

	public String getFlgSedIrc() {
		return this.flgSedIrc;
	}

	public void setFlgSedIrc(String flgSedIrc) {
		this.flgSedIrc = flgSedIrc;
	}

	public String getFlgSedOrg() {
		return this.flgSedOrg;
	}

	public void setFlgSedOrg(String flgSedOrg) {
		this.flgSedOrg = flgSedOrg;
	}

	public String getFlgSedOrgAta() {
		return this.flgSedOrgAta;
	}

	public void setFlgSedOrgAta(String flgSedOrgAta) {
		this.flgSedOrgAta = flgSedOrgAta;
	}

	public String getFlgSedOrgPed() {
		return this.flgSedOrgPed;
	}

	public void setFlgSedOrgPed(String flgSedOrgPed) {
		this.flgSedOrgPed = flgSedOrgPed;
	}

	public String getFlgSosSns() {
		return this.flgSosSns;
	}

	public void setFlgSosSns(String flgSosSns) {
		this.flgSosSns = flgSosSns;
	}

}