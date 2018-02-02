package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1014_COMPONENTESEZIONE database table.
 * 
 */
@Entity
@Table(name="TBS1014_COMPONENTESEZIONE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1014Componentesezione.findAll", query="SELECT t FROM Tbs1014Componentesezione t")
public class Tbs1014Componentesezione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1014_COMPONENTESEZIONE_PRGCMPSEZ_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1014_COMPONENTESEZIONE_PRGCMPSEZ_GENERATOR")
	@Column(name="PRG_CMP_SEZ")
	private Long prgCmpSez;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_TIP_CMP")
	private String codTipCmp;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="FLG_OBL")
	private String flgObl;

	@Column(name="PRG_SEZ_SOT_SEZ")
	private Long prgSezSotSez;

	@Column(name="QTY_MCA")
	private int qtyMca;
	
	@Column(name="PRG_CMP_SEZ_PDR")
	private Long prgCmpSezPdr;

	//bi-directional many-to-one association to Tbs1014Componentesezione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_CMP_SEZ_PDR", insertable=false, updatable=false)
	private Tbs1014Componentesezione tbs1014ComponentesezionePadre;

	//bi-directional many-to-one association to Tbs1014Componentesezione
	@OneToMany(mappedBy="tbs1014ComponentesezionePadre", fetch = FetchType.LAZY)
	@OrderBy("prgOrd")
	private List<Tbs1014Componentesezione> tbs1014ComponentesezioneFigli;
	
	@Column(name="PRG_ORD")
	private int prgOrd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COD_TIP_CMP", insertable=false, updatable=false)
	private Tbs1016Tipocomponente tbs1016Tipocomponente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_SEZ_SOT_SEZ", insertable=false, updatable=false)
	private Tbs1003Catalogosezione tbs1003Catalogosezione;

	public Tbs1014Componentesezione() {
	}

	public Long getPrgCmpSez() {
		return this.prgCmpSez;
	}

	public void setPrgCmpSez(Long prgCmpSez) {
		this.prgCmpSez = prgCmpSez;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodTipCmp() {
		return this.codTipCmp;
	}

	public void setCodTipCmp(String codTipCmp) {
		this.codTipCmp = codTipCmp;
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

	public String getFlgObl() {
		return this.flgObl;
	}

	public void setFlgObl(String flgObl) {
		this.flgObl = flgObl;
	}

	public Long getPrgSezSotSez() {
		return this.prgSezSotSez;
	}

	public void setPrgSezSotSez(Long prgSezSotSez) {
		this.prgSezSotSez = prgSezSotSez;
	}

	public int getQtyMca() {
		return this.qtyMca;
	}

	public void setQtyMca(int qtyMca) {
		this.qtyMca = qtyMca;
	}

	public Tbs1016Tipocomponente getTbs1016Tipocomponente() {
		return tbs1016Tipocomponente;
	}

	public void setTbs1016Tipocomponente(Tbs1016Tipocomponente tbs1016Tipocomponente) {
		this.tbs1016Tipocomponente = tbs1016Tipocomponente;
	}

	public Tbs1003Catalogosezione getTbs1003Catalogosezione() {
		return tbs1003Catalogosezione;
	}

	public void setTbs1003Catalogosezione(
			Tbs1003Catalogosezione tbs1003Catalogosezione) {
		this.tbs1003Catalogosezione = tbs1003Catalogosezione;
	}

	public Long getPrgCmpSezPdr() {
		return prgCmpSezPdr;
	}

	public void setPrgCmpSezPdr(Long prgCmpSezPdr) {
		this.prgCmpSezPdr = prgCmpSezPdr;
	}

	public Tbs1014Componentesezione getTbs1014ComponentesezionePadre() {
		return tbs1014ComponentesezionePadre;
	}

	public void setTbs1014ComponentesezionePadre(
			Tbs1014Componentesezione tbs1014ComponentesezionePadre) {
		this.tbs1014ComponentesezionePadre = tbs1014ComponentesezionePadre;
	}

	public List<Tbs1014Componentesezione> getTbs1014ComponentesezioneFigli() {
		return tbs1014ComponentesezioneFigli;
	}

	public void setTbs1014ComponentesezioneFigli(
			List<Tbs1014Componentesezione> tbs1014ComponentesezioneFigli) {
		this.tbs1014ComponentesezioneFigli = tbs1014ComponentesezioneFigli;
	}

	public int getPrgOrd() {
		return prgOrd;
	}

	public void setPrgOrd(int prgOrd) {
		this.prgOrd = prgOrd;
	}

	
	
}