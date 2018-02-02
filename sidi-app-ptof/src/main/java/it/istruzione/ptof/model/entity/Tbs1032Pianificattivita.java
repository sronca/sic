package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1032_PIANIFICATTIVITA database table.
 * 
 */
@Entity
@Table(name="TBS1032_PIANIFICATTIVITA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1032Pianificattivita.findAll", query="SELECT t FROM Tbs1032Pianificattivita t")
public class Tbs1032Pianificattivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1032_PIANIFICATTIVITA_PRGPIAATT_GENERATOR", sequenceName="Q1032PIANIFICATTIVITA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1032_PIANIFICATTIVITA_PRGPIAATT_GENERATOR")
	@Column(name="PRG_PIA_ATT")
	private Long prgPiaAtt;

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

	@Column(name="DES_CAP_PTO")
	private String desCapPto;

	@Column(name="DES_INT_MON")
	private String desIntMon;

	@Column(name="DES_PIA_ATT")
	private String desPiaAtt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1032Pianificattivita() {
	}

	public Long getPrgPiaAtt() {
		return this.prgPiaAtt;
	}

	public void setPrgPiaAtt(Long prgPiaAtt) {
		this.prgPiaAtt = prgPiaAtt;
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

	public String getDesCapPto() {
		return this.desCapPto;
	}

	public void setDesCapPto(String desCapPto) {
		this.desCapPto = desCapPto;
	}

	public String getDesIntMon() {
		return this.desIntMon;
	}

	public void setDesIntMon(String desIntMon) {
		this.desIntMon = desIntMon;
	}

	public String getDesPiaAtt() {
		return this.desPiaAtt;
	}

	public void setDesPiaAtt(String desPiaAtt) {
		this.desPiaAtt = desPiaAtt;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}