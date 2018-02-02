package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1030_RAPPORTOENTE database table.
 * 
 */
@Entity
@Table(name="TBS1030_RAPPORTOENTE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1030Rapportoente.findAll", query="SELECT t FROM Tbs1030Rapportoente t")
public class Tbs1030Rapportoente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1030_RAPPORTOENTE_PRGRAPENT_GENERATOR", sequenceName="Q1030RAPPORTOENTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1030_RAPPORTOENTE_PRGRAPENT_GENERATOR")
	@Column(name="PRG_RAP_ENT")
	private Long prgRapEnt;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_TEM_PSD")
	private String desAreTemPsd;

	@Column(name="DES_AZI_INT")
	private String desAziInt;

	@Column(name="DES_ENT_LOC")
	private String desEntLoc;

	@Column(name="DES_PRO")
	private String desPro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1030Rapportoente() {
	}

	public Long getPrgRapEnt() {
		return this.prgRapEnt;
	}

	public void setPrgRapEnt(Long prgRapEnt) {
		this.prgRapEnt = prgRapEnt;
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

	public String getDesAziInt() {
		return this.desAziInt;
	}

	public void setDesAziInt(String desAziInt) {
		this.desAziInt = desAziInt;
	}

	public String getDesEntLoc() {
		return this.desEntLoc;
	}

	public void setDesEntLoc(String desEntLoc) {
		this.desEntLoc = desEntLoc;
	}

	public String getDesPro() {
		return this.desPro;
	}

	public void setDesPro(String desPro) {
		this.desPro = desPro;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}