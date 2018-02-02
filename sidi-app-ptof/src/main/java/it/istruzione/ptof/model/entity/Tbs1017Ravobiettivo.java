package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1017_RAVOBIETTIVO database table.
 * 
 */
@Entity
@Table(name="TBS1017_RAVOBIETTIVO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1017Ravobiettivo.findAll", query="SELECT t FROM Tbs1017Ravobiettivo t")
public class Tbs1017Ravobiettivo implements Serializable {
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

	@Column(name="DES_ARE_PRC")
	private String desArePrc;

	@Column(name="DES_MOT_OBI_PRC")
	private String desMotObiPrc;

	@Column(name="DES_OBI_PRC")
	private String desObiPrc;

	@Column(name="DES_SES_PRC")
	private String desSesPrc;

	@Column(name="PRG_OBI_PRC")
	private BigDecimal prgObiPrc;

	public Tbs1017Ravobiettivo() {
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

	public String getDesArePrc() {
		return this.desArePrc;
	}

	public void setDesArePrc(String desArePrc) {
		this.desArePrc = desArePrc;
	}

	public String getDesMotObiPrc() {
		return this.desMotObiPrc;
	}

	public void setDesMotObiPrc(String desMotObiPrc) {
		this.desMotObiPrc = desMotObiPrc;
	}

	public String getDesObiPrc() {
		return this.desObiPrc;
	}

	public void setDesObiPrc(String desObiPrc) {
		this.desObiPrc = desObiPrc;
	}

	public String getDesSesPrc() {
		return this.desSesPrc;
	}

	public void setDesSesPrc(String desSesPrc) {
		this.desSesPrc = desSesPrc;
	}

	public BigDecimal getPrgObiPrc() {
		return this.prgObiPrc;
	}

	public void setPrgObiPrc(BigDecimal prgObiPrc) {
		this.prgObiPrc = prgObiPrc;
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