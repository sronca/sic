package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MFG1012_REGIONE database table.
 * 
 */
@Entity
@Table(name="MFG1012_REGIONE", schema = "UFGFUNGEN_OWN", catalog = "")
@NamedQuery(name="Mfg1012Regione.findAll", query="SELECT m FROM Mfg1012Regione m")
public class Mfg1012Regione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_REG")
	private String codReg;

	@Column(name="COD_ARE_GEO")
	private String codAreGeo;

	@Column(name="COD_CIT")
	private String codCit;

	@Column(name="COD_IST")
	private BigDecimal codIst;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_REG_IDE")
	private String codRegIde;

	@Column(name="COD_REG_NAZ")
	private String codRegNaz;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_REG")
	private String desReg;
	
	@OneToMany(mappedBy="mfg1012Regione",fetch = FetchType.LAZY)
	@OrderBy("desPrv")
	private List<Mfg1029Provnuoist> mfg1029Provnuoists;

	public Mfg1012Regione() {
	}

	public String getCodReg() {
		return this.codReg;
	}

	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}

	public String getCodAreGeo() {
		return this.codAreGeo;
	}

	public void setCodAreGeo(String codAreGeo) {
		this.codAreGeo = codAreGeo;
	}

	public String getCodCit() {
		return this.codCit;
	}

	public void setCodCit(String codCit) {
		this.codCit = codCit;
	}

	public BigDecimal getCodIst() {
		return this.codIst;
	}

	public void setCodIst(BigDecimal codIst) {
		this.codIst = codIst;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodRegIde() {
		return this.codRegIde;
	}

	public void setCodRegIde(String codRegIde) {
		this.codRegIde = codRegIde;
	}

	public String getCodRegNaz() {
		return this.codRegNaz;
	}

	public void setCodRegNaz(String codRegNaz) {
		this.codRegNaz = codRegNaz;
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

	public String getDesReg() {
		return this.desReg;
	}

	public void setDesReg(String desReg) {
		this.desReg = desReg;
	}

	public List<Mfg1029Provnuoist> getMfg1029Provnuoists() {
		return mfg1029Provnuoists;
	}

	public void setMfg1029Provnuoists(List<Mfg1029Provnuoist> mfg1029Provnuoists) {
		this.mfg1029Provnuoists = mfg1029Provnuoists;
	}

}