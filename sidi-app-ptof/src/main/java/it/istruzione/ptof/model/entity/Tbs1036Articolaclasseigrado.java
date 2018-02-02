package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1036_ARTICOLACLASSEIGRADO database table.
 * 
 */
@Entity
@Table(name="TBS1036_ARTICOLACLASSEIGRADO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1036Articolaclasseigrado.findAll", query="SELECT t FROM Tbs1036Articolaclasseigrado t")
public class Tbs1036Articolaclasseigrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1036_ARTICOLACLASSEIGRADO_PRGARCPGR_GENERATOR", sequenceName="Q1036ARTICOLACLASSEIGRADO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1036_ARTICOLACLASSEIGRADO_PRGARCPGR_GENERATOR")
	@Column(name="PRG_ARC_PGR")
	private Long prgArcPgr;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="NUM_CLA_TMP_N01")
	private BigDecimal numClaTmpN01;

	@Column(name="NUM_CLA_TMP_N02")
	private BigDecimal numClaTmpN02;

	@Column(name="NUM_CLA_TMP_N03")
	private BigDecimal numClaTmpN03;

	@Column(name="NUM_CLA_TMP_P01")
	private BigDecimal numClaTmpP01;

	@Column(name="NUM_CLA_TMP_P02")
	private BigDecimal numClaTmpP02;

	@Column(name="NUM_CLA_TMP_P03")
	private BigDecimal numClaTmpP03;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1036Articolaclasseigrado() {
	}

	public Long getPrgArcPgr() {
		return this.prgArcPgr;
	}

	public void setPrgArcPgr(Long prgArcPgr) {
		this.prgArcPgr = prgArcPgr;
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

	public BigDecimal getNumClaTmpN01() {
		return this.numClaTmpN01;
	}

	public void setNumClaTmpN01(BigDecimal numClaTmpN01) {
		this.numClaTmpN01 = numClaTmpN01;
	}

	public BigDecimal getNumClaTmpN02() {
		return this.numClaTmpN02;
	}

	public void setNumClaTmpN02(BigDecimal numClaTmpN02) {
		this.numClaTmpN02 = numClaTmpN02;
	}

	public BigDecimal getNumClaTmpN03() {
		return this.numClaTmpN03;
	}

	public void setNumClaTmpN03(BigDecimal numClaTmpN03) {
		this.numClaTmpN03 = numClaTmpN03;
	}

	public BigDecimal getNumClaTmpP01() {
		return this.numClaTmpP01;
	}

	public void setNumClaTmpP01(BigDecimal numClaTmpP01) {
		this.numClaTmpP01 = numClaTmpP01;
	}

	public BigDecimal getNumClaTmpP02() {
		return this.numClaTmpP02;
	}

	public void setNumClaTmpP02(BigDecimal numClaTmpP02) {
		this.numClaTmpP02 = numClaTmpP02;
	}

	public BigDecimal getNumClaTmpP03() {
		return this.numClaTmpP03;
	}

	public void setNumClaTmpP03(BigDecimal numClaTmpP03) {
		this.numClaTmpP03 = numClaTmpP03;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	
}