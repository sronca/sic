package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1001_GESTIONECATALOGODOC database table.
 * 
 */
@Entity
@Table(name="TBS1001_GESTIONECATALOGODOC", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1001Gestionecatalogodoc.findAll", query="SELECT t FROM Tbs1001Gestionecatalogodoc t")
public class Tbs1001Gestionecatalogodoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1001_GESTIONECATALOGODOC_PRGGESCATDOC_GENERATOR", sequenceName="Q1001GESTIONECATALOGODOC", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1001_GESTIONECATALOGODOC_PRGGESCATDOC_GENERATOR")
	@Column(name="PRG_GES_CAT_DOC")
	private Long prgGesCatDoc;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_FIN_VAL")
	private Date datFinVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_INI_VAL")
	private Date datIniVal;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_DOC")
	private String desDoc;

	@Column(name="NUM_VER_DOC")
	private BigDecimal numVerDoc;

	@Column(name="PER_TRI_RIF")
	private BigDecimal perTriRif;

	public Tbs1001Gestionecatalogodoc() {
	}

	public Long getPrgGesCatDoc() {
		return this.prgGesCatDoc;
	}

	public void setPrgGesCatDoc(Long prgGesCatDoc) {
		this.prgGesCatDoc = prgGesCatDoc;
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

	public Date getDatFinVal() {
		return this.datFinVal;
	}

	public void setDatFinVal(Date datFinVal) {
		this.datFinVal = datFinVal;
	}

	public Date getDatIniVal() {
		return this.datIniVal;
	}

	public void setDatIniVal(Date datIniVal) {
		this.datIniVal = datIniVal;
	}

	public Date getDatOraUltMov() {
		return this.datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesDoc() {
		return this.desDoc;
	}

	public void setDesDoc(String desDoc) {
		this.desDoc = desDoc;
	}

	public BigDecimal getNumVerDoc() {
		return this.numVerDoc;
	}

	public void setNumVerDoc(BigDecimal numVerDoc) {
		this.numVerDoc = numVerDoc;
	}

	public BigDecimal getPerTriRif() {
		return this.perTriRif;
	}

	public void setPerTriRif(BigDecimal perTriRif) {
		this.perTriRif = perTriRif;
	}

}