package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1028_ATTREZZATURE database table.
 * 
 */
@Entity
@Table(name="TBS1028_ATTREZZATURE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1028Attrezzature.findAll", query="SELECT t FROM Tbs1028Attrezzature t")
public class Tbs1028Attrezzature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1028_ATTREZZATURE_PRGATT_GENERATOR", sequenceName="Q1028ATTREZZATURE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1028_ATTREZZATURE_PRGATT_GENERATOR")
	@Column(name="PRG_ATT")
	private Long prgAtt;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_OBB_IST")
	private String desObbIst;

	@Column(name="DES_STR_ATT")
	private String desStrAtt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1028Attrezzature() {
	}

	public Long getPrgAtt() {
		return this.prgAtt;
	}

	public void setPrgAtt(Long prgAtt) {
		this.prgAtt = prgAtt;
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

	public String getDesObbIst() {
		return this.desObbIst;
	}

	public void setDesObbIst(String desObbIst) {
		this.desObbIst = desObbIst;
	}

	public String getDesStrAtt() {
		return this.desStrAtt;
	}

	public void setDesStrAtt(String desStrAtt) {
		this.desStrAtt = desStrAtt;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}


}