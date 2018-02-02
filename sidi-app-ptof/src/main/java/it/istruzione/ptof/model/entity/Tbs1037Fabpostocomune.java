package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1037_FABPOSTOCOMUNE database table.
 * 
 */
@Entity
@Table(name="TBS1037_FABPOSTOCOMUNE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1037Fabpostocomune.findAll", query="SELECT t FROM Tbs1037Fabpostocomune t")
public class Tbs1037Fabpostocomune implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1037_FABPOSTOCOMUNE_PRGFABPOSCOM_GENERATOR", sequenceName="Q1037FABPOSTOCOMUNE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1037_FABPOSTOCOMUNE_PRGFABPOSCOM_GENERATOR")
	@Column(name="PRG_FAB_POS_COM")
	private Long prgFabPosCom;

	@Column(name="COD_CLC")
	private String codClc;

	@Column(name="COD_ORD_SCU")
	private String codOrdScu;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_CLC")
	private String desClc;

	@Column(name="DES_MOT")
	private String desMot;

	@Column(name="NUM_POS_PRI")
	private BigDecimal numPosPri;
	
	@Column(name="NUM_POS_SEC")
	private BigDecimal numPosSec;
	
	@Column(name="NUM_POS_TER")
	private BigDecimal numPosTer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1037Fabpostocomune() {
	}

	public Long getPrgFabPosCom() {
		return this.prgFabPosCom;
	}

	public void setPrgFabPosCom(Long prgFabPosCom) {
		this.prgFabPosCom = prgFabPosCom;
	}

	public String getCodClc() {
		return this.codClc;
	}

	public void setCodClc(String codClc) {
		this.codClc = codClc;
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

	public String getDesClc() {
		return this.desClc;
	}

	public void setDesClc(String desClc) {
		this.desClc = desClc;
	}

	public String getDesMot() {
		return this.desMot;
	}

	public void setDesMot(String desMot) {
		this.desMot = desMot;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	public BigDecimal getNumPosPri() {
		return numPosPri;
	}

	public void setNumPosPri(BigDecimal numPosPri) {
		this.numPosPri = numPosPri;
	}

	public BigDecimal getNumPosSec() {
		return numPosSec;
	}

	public void setNumPosSec(BigDecimal numPosSec) {
		this.numPosSec = numPosSec;
	}

	public BigDecimal getNumPosTer() {
		return numPosTer;
	}

	public void setNumPosTer(BigDecimal numPosTer) {
		this.numPosTer = numPosTer;
	}


	

}