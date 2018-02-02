package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1031_CONVENZIONEFORMA database table.
 * 
 */
@Entity
@Table(name="TBS1031_CONVENZIONEFORMA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1031Convenzioneforma.findAll", query="SELECT t FROM Tbs1031Convenzioneforma t")
public class Tbs1031Convenzioneforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1031_CONVENZIONEFORMA_PRGCNVFOR_GENERATOR", sequenceName="Q1031CONVENZIONEFORMA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1031_CONVENZIONEFORMA_PRGCNVFOR_GENERATOR")
	@Column(name="PRG_CNV_FOR")
	private Long prgCnvFor;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_CNV")
	private String desCnv;

	@Column(name="DES_NOT")
	private String desNot;

	@Column(name="DES_ORG")
	private String desOrg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1031Convenzioneforma() {
	}

	public Long getPrgCnvFor() {
		return this.prgCnvFor;
	}

	public void setPrgCnvFor(Long prgCnvFor) {
		this.prgCnvFor = prgCnvFor;
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

	public String getDesCnv() {
		return this.desCnv;
	}

	public void setDesCnv(String desCnv) {
		this.desCnv = desCnv;
	}

	public String getDesNot() {
		return this.desNot;
	}

	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}

	public String getDesOrg() {
		return this.desOrg;
	}

	public void setDesOrg(String desOrg) {
		this.desOrg = desOrg;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}