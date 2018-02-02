package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1041_FABFIGURAPROFESSIONALE database table.
 * 
 */
@Entity
@Table(name="TBS1041_FABFIGURAPROFESSIONALE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1041Fabfiguraprofessionale.findAll", query="SELECT t FROM Tbs1041Fabfiguraprofessionale t")
public class Tbs1041Fabfiguraprofessionale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1041_FABFIGURAPROFESSIONALE_PRGFABPOSFPF_GENERATOR", sequenceName="Q1041FABFIGURAPROFESSIONALE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1041_FABFIGURAPROFESSIONALE_PRGFABPOSFPF_GENERATOR")
	@Column(name="PRG_FAB_POS_FPF")
	private Long prgFabPosFpf;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_FIG_PRF")
	private String desFigPrf;

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

	public Tbs1041Fabfiguraprofessionale() {
	}

	public Long getPrgFabPosFpf() {
		return prgFabPosFpf;
	}

	public void setPrgFabPosFpf(Long prgFabPosFpf) {
		this.prgFabPosFpf = prgFabPosFpf;
	}

	public String getCodPgmUltMov() {
		return codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodUteUltMov() {
		return codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public Date getDatOraUltMov() {
		return datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesFigPrf() {
		return desFigPrf;
	}

	public void setDesFigPrf(String desFigPrf) {
		this.desFigPrf = desFigPrf;
	}

	public String getDesMot() {
		return desMot;
	}

	public void setDesMot(String desMot) {
		this.desMot = desMot;
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

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}


	



}