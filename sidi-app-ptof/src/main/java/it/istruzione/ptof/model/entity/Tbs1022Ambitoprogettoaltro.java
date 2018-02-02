package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1022_AMBITOPROGETTOALTRO database table.
 * 
 */
@Entity
@Table(name="TBS1022_AMBITOPROGETTOALTRO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1022Ambitoprogettoaltro.findAll", query="SELECT t FROM Tbs1022Ambitoprogettoaltro t")
public class Tbs1022Ambitoprogettoaltro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1022_AMBITOPROGETTOALTRO_PRGAMBPGTALT_GENERATOR", sequenceName="Q1022AMBITOPROGETTOALTRO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1022_AMBITOPROGETTOALTRO_PRGAMBPGTALT_GENERATOR")
	@Column(name="PRG_AMB_PGT_ALT")
	private Long prgAmbPgtAlt;

	@Column(name="COD_ARE_TEM")
	private String codAreTem;

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

	@Column(name="DES_ALT_RIS")
	private String desAltRis;

	@Column(name="DES_COO_SCU")
	private String desCooScu;

	@Column(name="DES_DEN_PGT_ALT")
	private String desDenPgtAlt;

	@Column(name="DES_DEN_PGT_CUR")
	private String desDenPgtCur;

	@Column(name="DES_DST_PGT")
	private String desDstPgt;

	@Column(name="DES_MOD_FOR_UTI")
	private String desModForUti;

	@Column(name="DES_OBI_PRI")
	private String desObiPri;

	@Column(name="DES_PER_SVO")
	private String desPerSvo;

	@Column(name="DES_PRP_CNT")
	private String desPrpCnt;

	@Column(name="DES_RIL_SCU")
	private String desRilScu;

	@Column(name="DES_RIS_UMA")
	private String desRisUma;

	@Column(name="DES_STA_AVA")
	private String desStaAva;

	@Column(name="IMP_RIS_FIN")
	private BigDecimal impRisFin;
	
	@Column(name="DES_NOM_BEN_SER")
	private String desNomBenSer;
	
	@Column(name="DES_BEN_SER")
	private String desBenSer;
	
	@Column(name="DES_CLS")
	private String desCls;
	
	@Column(name="NUM_DOC_ATA")
	private Integer numDocAta;
	
	@Column(name="IMP_STI")
	private BigDecimal impSti;
	
	@Column(name="DES_NOT")
	private String desNot;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	//bi-directional many-to-one association to Tbs1019Tipoambito
	@ManyToOne
	@JoinColumn(name="COD_TIP_AMB")
	private Tbs1019Tipoambito tbs1019Tipoambito;

	public Tbs1022Ambitoprogettoaltro() {
	}

	public Long getPrgAmbPgtAlt() {
		return this.prgAmbPgtAlt;
	}

	public void setPrgAmbPgtAlt(Long prgAmbPgtAlt) {
		this.prgAmbPgtAlt = prgAmbPgtAlt;
	}

	public String getCodAreTem() {
		return this.codAreTem;
	}

	public void setCodAreTem(String codAreTem) {
		this.codAreTem = codAreTem;
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

	public String getDesAltRis() {
		return this.desAltRis;
	}

	public void setDesAltRis(String desAltRis) {
		this.desAltRis = desAltRis;
	}

	public String getDesCooScu() {
		return this.desCooScu;
	}

	public void setDesCooScu(String desCooScu) {
		this.desCooScu = desCooScu;
	}

	public String getDesDenPgtAlt() {
		return this.desDenPgtAlt;
	}

	public void setDesDenPgtAlt(String desDenPgtAlt) {
		this.desDenPgtAlt = desDenPgtAlt;
	}

	public String getDesDenPgtCur() {
		return this.desDenPgtCur;
	}

	public void setDesDenPgtCur(String desDenPgtCur) {
		this.desDenPgtCur = desDenPgtCur;
	}

	public String getDesDstPgt() {
		return this.desDstPgt;
	}

	public void setDesDstPgt(String desDstPgt) {
		this.desDstPgt = desDstPgt;
	}

	public String getDesModForUti() {
		return this.desModForUti;
	}

	public void setDesModForUti(String desModForUti) {
		this.desModForUti = desModForUti;
	}

	public String getDesObiPri() {
		return this.desObiPri;
	}

	public void setDesObiPri(String desObiPri) {
		this.desObiPri = desObiPri;
	}

	public String getDesPerSvo() {
		return this.desPerSvo;
	}

	public void setDesPerSvo(String desPerSvo) {
		this.desPerSvo = desPerSvo;
	}

	public String getDesPrpCnt() {
		return this.desPrpCnt;
	}

	public void setDesPrpCnt(String desPrpCnt) {
		this.desPrpCnt = desPrpCnt;
	}

	public String getDesRilScu() {
		return this.desRilScu;
	}

	public void setDesRilScu(String desRilScu) {
		this.desRilScu = desRilScu;
	}

	public String getDesRisUma() {
		return this.desRisUma;
	}

	public void setDesRisUma(String desRisUma) {
		this.desRisUma = desRisUma;
	}

	public String getDesStaAva() {
		return this.desStaAva;
	}

	public void setDesStaAva(String desStaAva) {
		this.desStaAva = desStaAva;
	}

	public BigDecimal getImpRisFin() {
		return this.impRisFin;
	}

	public void setImpRisFin(BigDecimal impRisFin) {
		this.impRisFin = impRisFin;
	}

	public Tbs1019Tipoambito getTbs1019Tipoambito() {
		return this.tbs1019Tipoambito;
	}

	public void setTbs1019Tipoambito(Tbs1019Tipoambito tbs1019Tipoambito) {
		this.tbs1019Tipoambito = tbs1019Tipoambito;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	public String getDesNomBenSer() {
		return desNomBenSer;
	}

	public void setDesNomBenSer(String desNomBenSer) {
		this.desNomBenSer = desNomBenSer;
	}

	public String getDesBenSer() {
		return desBenSer;
	}

	public void setDesBenSer(String desBenSer) {
		this.desBenSer = desBenSer;
	}

	public String getDesCls() {
		return desCls;
	}

	public void setDesCls(String desCls) {
		this.desCls = desCls;
	}

	public Integer getNumDocAta() {
		return numDocAta;
	}

	public void setNumDocAta(Integer numDocAta) {
		this.numDocAta = numDocAta;
	}

	public BigDecimal getImpSti() {
		return impSti;
	}

	public void setImpSti(BigDecimal impSti) {
		this.impSti = impSti;
	}

	public String getDesNot() {
		return desNot;
	}

	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}

	
}