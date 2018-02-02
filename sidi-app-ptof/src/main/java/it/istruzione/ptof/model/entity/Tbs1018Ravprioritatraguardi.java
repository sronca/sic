package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1018_RAVPRIORITATRAGUARDI database table.
 * 
 */
@Entity
@Table(name="TBS1018_RAVPRIORITATRAGUARDI", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1018Ravprioritatraguardi.findAll", query="SELECT t FROM Tbs1018Ravprioritatraguardi t")
public class Tbs1018Ravprioritatraguardi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="COD_SCU_UTE")
	private String codScuUte;

	@Column(name="PER_ANN_SCO")
	private long perAnnSco;
	
	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ESI_STU")
	private String desEsiStu;

	@Column(name="DES_MOT_SCE_PRI")
	private String desMotScePri;

	@Column(name="DES_PRI")
	private String desPri;

	@Column(name="DES_SES_ESI")
	private String desSesEsi;

	@Column(name="DES_TRA")
	private String desTra;

	@Column(name="PRG_PRI_TRA")
	private BigDecimal prgPriTra;

	public Tbs1018Ravprioritatraguardi() {
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

	public String getDesEsiStu() {
		return this.desEsiStu;
	}

	public void setDesEsiStu(String desEsiStu) {
		this.desEsiStu = desEsiStu;
	}

	public String getDesMotScePri() {
		return this.desMotScePri;
	}

	public void setDesMotScePri(String desMotScePri) {
		this.desMotScePri = desMotScePri;
	}

	public String getDesPri() {
		return this.desPri;
	}

	public void setDesPri(String desPri) {
		this.desPri = desPri;
	}

	public String getDesSesEsi() {
		return this.desSesEsi;
	}

	public void setDesSesEsi(String desSesEsi) {
		this.desSesEsi = desSesEsi;
	}

	public String getDesTra() {
		return this.desTra;
	}

	public void setDesTra(String desTra) {
		this.desTra = desTra;
	}

	public BigDecimal getPrgPriTra() {
		return this.prgPriTra;
	}

	public void setPrgPriTra(BigDecimal prgPriTra) {
		this.prgPriTra = prgPriTra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodScuUte() {
		return codScuUte;
	}

	public void setCodScuUte(String codScuUte) {
		this.codScuUte = codScuUte;
	}

	public long getPerAnnSco() {
		return perAnnSco;
	}

	public void setPerAnnSco(long perAnnSco) {
		this.perAnnSco = perAnnSco;
	}

	
	
}