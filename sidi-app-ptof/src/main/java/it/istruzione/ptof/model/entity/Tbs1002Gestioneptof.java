package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1002_GESTIONEPTOF database table.
 * 
 */
@Entity
@Table(name="TBS1002_GESTIONEPTOF", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1002Gestioneptof.findAll", query="SELECT t FROM Tbs1002Gestioneptof t")
public class Tbs1002Gestioneptof implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tbs1002GestioneptofPK id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRG_GES_CAT_DOC", nullable = false, insertable = false, updatable = false)
	private Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="COD_SCU_UTE", referencedColumnName="COD_SCU_UT", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name="PER_ANN_SCO", referencedColumnName="DAT_ANN_SCO_RIL", nullable = false, insertable = false, updatable = false)
		})
	private Mfg1002Anagistscol mfg1002Anagistscol;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_STA", nullable = false)
	private Tbs1006Tipostato tbs1006Tipostato;

	@Column(name="COD_UTE_PUB_PDF_PTO_COM")
	private String codUtePubPdfPtoCom;

	@Column(name="COD_UTE_PUB_PDF_PTO_PAR")
	private String codUtePubPdfPtoPar;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_PUB_PDF_PTO_COM")
	private Date datPubPdfPtoCom;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_PUB_PDF_PTO_PAR")
	private Date datPubPdfPtoPar;

	@Lob
	@Column(name="OGG_PDF_PTO_COM")
	private byte[] oggPdfPtoCom;

	@Lob
	@Column(name="OGG_PDF_PTO_PAR")
	private byte[] oggPdfPtoPar;
	
	@Lob
	@Column(name="OGG_PDF_VIS_CNV")
	private byte[] oggPdfVisCnv;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DAT_PDF_VIS_CNV")
	private Date datPdfVisCnv;
	
	@Column(name="COD_UTE_PDF_VIS_CNV")
	private String codUtePdfVisCnv;

	public Tbs1002Gestioneptof() {
	}

	public Tbs1002GestioneptofPK getId() {
		return this.id;
	}

	public void setId(Tbs1002GestioneptofPK id) {
		this.id = id;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodUtePubPdfPtoCom() {
		return this.codUtePubPdfPtoCom;
	}

	public void setCodUtePubPdfPtoCom(String codUtePubPdfPtoCom) {
		this.codUtePubPdfPtoCom = codUtePubPdfPtoCom;
	}

	public String getCodUtePubPdfPtoPar() {
		return this.codUtePubPdfPtoPar;
	}

	public void setCodUtePubPdfPtoPar(String codUtePubPdfPtoPar) {
		this.codUtePubPdfPtoPar = codUtePubPdfPtoPar;
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

	public Date getDatPubPdfPtoCom() {
		return this.datPubPdfPtoCom;
	}

	public void setDatPubPdfPtoCom(Date datPubPdfPtoCom) {
		this.datPubPdfPtoCom = datPubPdfPtoCom;
	}

	public Date getDatPubPdfPtoPar() {
		return this.datPubPdfPtoPar;
	}

	public void setDatPubPdfPtoPar(Date datPubPdfPtoPar) {
		this.datPubPdfPtoPar = datPubPdfPtoPar;
	}

	public byte[] getOggPdfPtoCom() {
		return this.oggPdfPtoCom;
	}

	public void setOggPdfPtoCom(byte[] oggPdfPtoCom) {
		this.oggPdfPtoCom = oggPdfPtoCom;
	}

	public byte[] getOggPdfPtoPar() {
		return this.oggPdfPtoPar;
	}

	public void setOggPdfPtoPar(byte[] oggPdfPtoPar) {
		this.oggPdfPtoPar = oggPdfPtoPar;
	}

	public Tbs1001Gestionecatalogodoc getTbs1001Gestionecatalogodoc() {
		return tbs1001Gestionecatalogodoc;
	}

	public void setTbs1001Gestionecatalogodoc(
			Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc) {
		this.tbs1001Gestionecatalogodoc = tbs1001Gestionecatalogodoc;
	}

	public byte[] getOggPdfVisCnv() {
		return oggPdfVisCnv;
	}

	public void setOggPdfVisCnv(byte[] oggPdfVisCnv) {
		this.oggPdfVisCnv = oggPdfVisCnv;
	}

	public Date getDatPdfVisCnv() {
		return datPdfVisCnv;
	}

	public void setDatPdfVisCnv(Date datPdfVisCnv) {
		this.datPdfVisCnv = datPdfVisCnv;
	}

	public String getCodUtePdfVisCnv() {
		return codUtePdfVisCnv;
	}

	public void setCodUtePdfVisCnv(String codUtePdfVisCnv) {
		this.codUtePdfVisCnv = codUtePdfVisCnv;
	}

	public Tbs1006Tipostato getTbs1006Tipostato() {
		return tbs1006Tipostato;
	}

	public void setTbs1006Tipostato(Tbs1006Tipostato tbs1006Tipostato) {
		this.tbs1006Tipostato = tbs1006Tipostato;
	}

	public Mfg1002Anagistscol getMfg1002Anagistscol() {
		return mfg1002Anagistscol;
	}

	public void setMfg1002Anagistscol(Mfg1002Anagistscol mfg1002Anagistscol) {
		this.mfg1002Anagistscol = mfg1002Anagistscol;
	}
	
	

}