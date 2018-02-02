package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1029_DOTAZIONEMULTIMEDIALE database table.
 * 
 */
@Entity
@Table(name="TBS1029_DOTAZIONEMULTIMEDIALE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1029Dotazionemultimediale.findAll", query="SELECT t FROM Tbs1029Dotazionemultimediale t")
public class Tbs1029Dotazionemultimediale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1029_DOTAZIONEMULTIMEDIALE_PRGDOTMUS_GENERATOR", sequenceName="Q1029DOTAZIONEMULTIMEDIALE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1029_DOTAZIONEMULTIMEDIALE_PRGDOTMUS_GENERATOR")
	@Column(name="PRG_DOT_MUS")
	private Long prgDotMus;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_DOT_MUS")
	private String desDotMus;

	@Column(name="NUM_DOT_MLD")
	private long numDotMld;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;


	public Tbs1029Dotazionemultimediale() {
	}

	public Long getPrgDotMus() {
		return this.prgDotMus;
	}

	public void setPrgDotMus(Long prgDotMus) {
		this.prgDotMus = prgDotMus;
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

	public String getDesAreTemPsd() {
		return this.desAreTemPsd;
	}

	public void setDesAreTemPsd(String desAreTemPsd) {
		this.desAreTemPsd = desAreTemPsd;
	}

	public String getDesDotMus() {
		return this.desDotMus;
	}

	public void setDesDotMus(String desDotMus) {
		this.desDotMus = desDotMus;
	}

	public long getNumDotMld() {
		return this.numDotMld;
	}

	public void setNumDotMld(long numDotMld) {
		this.numDotMld = numDotMld;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}