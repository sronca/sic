package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1044_RICHIESTAPDF database table.
 * 
 */
@Entity
@Table(name="TBS1044_RICHIESTAPDF")
@NamedQuery(name="Tbs1044Richiestapdf.findAll", query="SELECT t FROM Tbs1044Richiestapdf t")
public class Tbs1044Richiestapdf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1044_RICHIESTAPDF_PRGRICPDF_GENERATOR", sequenceName="Q1044RICHIESTAPDF", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1044_RICHIESTAPDF_PRGRICPDF_GENERATOR")
	@Column(name="PRG_RIC_PDF")
	private Long prgRicPdf;
	
	@Column(name="COD_SCU_UTE")
	private String codScuUte;
	
	@Column(name="PER_ANN_SCO")
	private Long perAnnSco;
	
	@Column(name="PRG_GES_CAT_DOC")
	private Long prgGesCatDoc;
	
	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_EVA_RIC_PDF")
	private Date datEvaRicPdf;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_INS_RIC_PDF")
	private Date datInsRicPdf;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_MSG_ERR")
	private String desMsgErr;

	@Column(name="FLG_STO_PDF")
	private String flgStoPdf;

	public Tbs1044Richiestapdf() {
	}

	public Long getPrgRicPdf() {
		return prgRicPdf;
	}

	public void setPrgRicPdf(Long prgRicPdf) {
		this.prgRicPdf = prgRicPdf;
	}

	public String getCodScuUte() {
		return codScuUte;
	}

	public void setCodScuUte(String codScuUte) {
		this.codScuUte = codScuUte;
	}

	public Long getPerAnnSco() {
		return perAnnSco;
	}

	public void setPerAnnSco(Long perAnnSco) {
		this.perAnnSco = perAnnSco;
	}

	public Long getPrgGesCatDoc() {
		return prgGesCatDoc;
	}

	public void setPrgGesCatDoc(Long prgGesCatDoc) {
		this.prgGesCatDoc = prgGesCatDoc;
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

	public Date getDatEvaRicPdf() {
		return datEvaRicPdf;
	}

	public void setDatEvaRicPdf(Date datEvaRicPdf) {
		this.datEvaRicPdf = datEvaRicPdf;
	}

	public Date getDatInsRicPdf() {
		return datInsRicPdf;
	}

	public void setDatInsRicPdf(Date datInsRicPdf) {
		this.datInsRicPdf = datInsRicPdf;
	}

	public Date getDatOraUltMov() {
		return datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesMsgErr() {
		return desMsgErr;
	}

	public void setDesMsgErr(String desMsgErr) {
		this.desMsgErr = desMsgErr;
	}

	public String getFlgStoPdf() {
		return flgStoPdf;
	}

	public void setFlgStoPdf(String flgStoPdf) {
		this.flgStoPdf = flgStoPdf;
	}


	
	
	
}