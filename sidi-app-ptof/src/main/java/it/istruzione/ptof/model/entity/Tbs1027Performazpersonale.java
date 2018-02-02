package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1027_PERFORMAZPERSONALE database table.
 * 
 */
@Entity
@Table(name="TBS1027_PERFORMAZPERSONALE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1027Performazpersonale.findAll", query="SELECT t FROM Tbs1027Performazpersonale t")
public class Tbs1027Performazpersonale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1027_PERFORMAZPERSONALE_PRGFORPER_GENERATOR", sequenceName="Q1027PERFORMAZPERSONALE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1027_PERFORMAZPERSONALE_PRGFORPER_GENERATOR")
	@Column(name="PRG_FOR_PER")
	private Long prgForPer;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@ManyToOne
	@JoinColumn(name="COD_TIP_AMB")
	private Tbs1019Tipoambito tbs1019Tipoambito;

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

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_CNT")
	private String desCnt;

	@Column(name="DES_OBI")
	private String desObi;

	@Column(name="DES_ORE_CMP")
	private String desOreCmp;

	@Column(name="DES_PER_FOR")
	private String desPerFor;

	@Column(name="FLG_CND")
	private String flgCnd;

	@Column(name="FLG_COL_RES")
	private String flgColRes;

	@Column(name="FLG_FOR_OTS")
	private String flgForOts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;
	
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

	public Tbs1027Performazpersonale() {
	}

	public Long getPrgForPer() {
		return this.prgForPer;
	}

	public void setPrgForPer(Long prgForPer) {
		this.prgForPer = prgForPer;
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

	public String getDesAreTemPsd() {
		return this.desAreTemPsd;
	}

	public void setDesAreTemPsd(String desAreTemPsd) {
		this.desAreTemPsd = desAreTemPsd;
	}

	public String getDesCnt() {
		return this.desCnt;
	}

	public void setDesCnt(String desCnt) {
		this.desCnt = desCnt;
	}

	public String getDesObi() {
		return this.desObi;
	}

	public void setDesObi(String desObi) {
		this.desObi = desObi;
	}

	public String getDesOreCmp() {
		return this.desOreCmp;
	}

	public void setDesOreCmp(String desOreCmp) {
		this.desOreCmp = desOreCmp;
	}

	public String getDesPerFor() {
		return this.desPerFor;
	}

	public void setDesPerFor(String desPerFor) {
		this.desPerFor = desPerFor;
	}

	public String getFlgCnd() {
		return this.flgCnd;
	}

	public void setFlgCnd(String flgCnd) {
		this.flgCnd = flgCnd;
	}

	public String getFlgColRes() {
		return this.flgColRes;
	}

	public void setFlgColRes(String flgColRes) {
		this.flgColRes = flgColRes;
	}

	public String getFlgForOts() {
		return this.flgForOts;
	}

	public void setFlgForOts(String flgForOts) {
		this.flgForOts = flgForOts;
	}

	public Tbs1019Tipoambito getTbs1019Tipoambito() {
		return tbs1019Tipoambito;
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