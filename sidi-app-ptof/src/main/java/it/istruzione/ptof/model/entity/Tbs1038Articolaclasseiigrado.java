package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1038_ARTICOLACLASSEIIGRADO database table.
 * 
 */
@Entity
@Table(name="TBS1038_ARTICOLACLASSEIIGRADO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1038Articolaclasseiigrado.findAll", query="SELECT t FROM Tbs1038Articolaclasseiigrado t")
public class Tbs1038Articolaclasseiigrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1038_ARTICOLACLASSEIIGRADO_PRGARCSGR_GENERATOR", sequenceName="Q1038ARTICOLACLASSEIIGRADO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1038_ARTICOLACLASSEIIGRADO_PRGARCSGR_GENERATOR")
	@Column(name="PRG_ARC_SGR")
	private Long prgArcSgr;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_IND")
	private String desInd;

	@Column(name="NUM_CLA_001")
	private BigDecimal numCla001;

	@Column(name="NUM_CLA_002")
	private BigDecimal numCla002;

	@Column(name="NUM_CLA_003")
	private BigDecimal numCla003;

	@Column(name="NUM_CLA_004")
	private BigDecimal numCla004;

	@Column(name="NUM_CLA_005")
	private BigDecimal numCla005;

	@Column(name="NUM_CLA_006")
	private BigDecimal numCla006;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1038Articolaclasseiigrado() {
	}

	public Long getPrgArcSgr() {
		return this.prgArcSgr;
	}

	public void setPrgArcSgr(Long prgArcSgr) {
		this.prgArcSgr = prgArcSgr;
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

	public String getDesInd() {
		return this.desInd;
	}

	public void setDesInd(String desInd) {
		this.desInd = desInd;
	}

	public BigDecimal getNumCla001() {
		return this.numCla001;
	}

	public void setNumCla001(BigDecimal numCla001) {
		this.numCla001 = numCla001;
	}

	public BigDecimal getNumCla002() {
		return this.numCla002;
	}

	public void setNumCla002(BigDecimal numCla002) {
		this.numCla002 = numCla002;
	}

	public BigDecimal getNumCla003() {
		return this.numCla003;
	}

	public void setNumCla003(BigDecimal numCla003) {
		this.numCla003 = numCla003;
	}

	public BigDecimal getNumCla004() {
		return this.numCla004;
	}

	public void setNumCla004(BigDecimal numCla004) {
		this.numCla004 = numCla004;
	}

	public BigDecimal getNumCla005() {
		return this.numCla005;
	}

	public void setNumCla005(BigDecimal numCla005) {
		this.numCla005 = numCla005;
	}

	public BigDecimal getNumCla006() {
		return this.numCla006;
	}

	public void setNumCla006(BigDecimal numCla006) {
		this.numCla006 = numCla006;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	
}