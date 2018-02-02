package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1035_ARTICOCLASSEPRIMARIA database table.
 * 
 */
@Entity
@Table(name="TBS1035_ARTICOCLASSEPRIMARIA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1035Articoclasseprimaria.findAll", query="SELECT t FROM Tbs1035Articoclasseprimaria t")
public class Tbs1035Articoclasseprimaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1035_ARTICOCLASSEPRIMARIA_PRGARCPRI_GENERATOR", sequenceName="Q1035TARTICOCLASSEPRIMARIA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1035_ARTICOCLASSEPRIMARIA_PRGARCPRI_GENERATOR")
	@Column(name="PRG_ARC_PRI")
	private Long prgArcPri;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_TIP_POS")
	private String codTipPos;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_POS")
	private String desTipPos;

	@Column(name="NUM_CLA_TMP_N01")
	private BigDecimal numClaTmpN01;

	@Column(name="NUM_CLA_TMP_N02")
	private BigDecimal numClaTmpN02;

	@Column(name="NUM_CLA_TMP_N03")
	private BigDecimal numClaTmpN03;

	@Column(name="NUM_CLA_TMP_N04")
	private BigDecimal numClaTmpN04;

	@Column(name="NUM_CLA_TMP_N05")
	private BigDecimal numClaTmpN05;

	@Column(name="NUM_CLA_TMP_NPL")
	private BigDecimal numClaTmpNpl;

	@Column(name="NUM_CLA_TMP_P01")
	private BigDecimal numClaTmpP01;

	@Column(name="NUM_CLA_TMP_P02")
	private BigDecimal numClaTmpP02;

	@Column(name="NUM_CLA_TMP_P03")
	private BigDecimal numClaTmpP03;

	@Column(name="NUM_CLA_TMP_P04")
	private BigDecimal numClaTmpP04;

	@Column(name="NUM_CLA_TMP_P05")
	private BigDecimal numClaTmpP05;

	@Column(name="NUM_CLA_TMP_PPL")
	private BigDecimal numClaTmpPpl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1035Articoclasseprimaria() {
	}

	public Long getPrgArcPri() {
		return this.prgArcPri;
	}

	public void setPrgArcPri(Long prgArcPri) {
		this.prgArcPri = prgArcPri;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodTipPos() {
		return this.codTipPos;
	}

	public void setCodTipPos(String codTipPos) {
		this.codTipPos = codTipPos;
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

	public String getDesTipPos() {
		return this.desTipPos;
	}

	public void setDesTipPos(String desTipPos) {
		this.desTipPos = desTipPos;
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

	public BigDecimal getNumClaTmpN04() {
		return this.numClaTmpN04;
	}

	public void setNumClaTmpN04(BigDecimal numClaTmpN04) {
		this.numClaTmpN04 = numClaTmpN04;
	}

	public BigDecimal getNumClaTmpN05() {
		return this.numClaTmpN05;
	}

	public void setNumClaTmpN05(BigDecimal numClaTmpN05) {
		this.numClaTmpN05 = numClaTmpN05;
	}

	public BigDecimal getNumClaTmpNpl() {
		return this.numClaTmpNpl;
	}

	public void setNumClaTmpNpl(BigDecimal numClaTmpNpl) {
		this.numClaTmpNpl = numClaTmpNpl;
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

	public BigDecimal getNumClaTmpP04() {
		return this.numClaTmpP04;
	}

	public void setNumClaTmpP04(BigDecimal numClaTmpP04) {
		this.numClaTmpP04 = numClaTmpP04;
	}

	public BigDecimal getNumClaTmpP05() {
		return this.numClaTmpP05;
	}

	public void setNumClaTmpP05(BigDecimal numClaTmpP05) {
		this.numClaTmpP05 = numClaTmpP05;
	}

	public BigDecimal getNumClaTmpPpl() {
		return this.numClaTmpPpl;
	}

	public void setNumClaTmpPpl(BigDecimal numClaTmpPpl) {
		this.numClaTmpPpl = numClaTmpPpl;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}