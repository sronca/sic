package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1034_ARTICOCLASSEINFANZIA database table.
 * 
 */
@Entity
@Table(name="TBS1034_ARTICOCLASSEINFANZIA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1034Articoclasseinfanzia.findAll", query="SELECT t FROM Tbs1034Articoclasseinfanzia t")
public class Tbs1034Articoclasseinfanzia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1034_ARTICOCLASSEINFANZIA_PRGARCINF_GENERATOR", sequenceName="Q1034ARTICOCLASSEINFANZIA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1034_ARTICOCLASSEINFANZIA_PRGARCINF_GENERATOR")
	@Column(name="PRG_ARC_INF")
	private Long prgArcInf;

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

	@Column(name="NUM_SEZ_NOR")
	private BigDecimal numSezNor;

	@Column(name="NUM_SEZ_RID")
	private BigDecimal numSezRid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1034Articoclasseinfanzia() {
	}

	public Long getPrgArcInf() {
		return this.prgArcInf;
	}

	public void setPrgArcInf(Long prgArcInf) {
		this.prgArcInf = prgArcInf;
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

	public BigDecimal getNumSezNor() {
		return this.numSezNor;
	}

	public void setNumSezNor(BigDecimal numSezNor) {
		this.numSezNor = numSezNor;
	}

	public BigDecimal getNumSezRid() {
		return this.numSezRid;
	}

	public void setNumSezRid(BigDecimal numSezRid) {
		this.numSezRid = numSezRid;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}