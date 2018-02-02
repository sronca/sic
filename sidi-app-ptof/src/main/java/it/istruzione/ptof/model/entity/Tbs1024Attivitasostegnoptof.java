package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1024_ATTIVITASOSTEGNOPTOF database table.
 * 
 */
@Entity
@Table(name="TBS1024_ATTIVITASOSTEGNOPTOF", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1024Attivitasostegnoptof.findAll", query="SELECT t FROM Tbs1024Attivitasostegnoptof t")
public class Tbs1024Attivitasostegnoptof implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1024_ATTIVITASOSTEGNOPTOF_PRGATTSOS_GENERATOR", sequenceName="Q1024ATTIVITASOSTEGNOPTOF", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1024_ATTIVITASOSTEGNOPTOF_PRGATTSOS_GENERATOR")
	@Column(name="PRG_ATT_SOS")
	private Long prgAttSos;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ASP_SUP_LOG")
	private String desAspSupLog;

	@Column(name="DES_CNT_ATT_SOS")
	private String desCntAttSos;

	@Column(name="DES_COO_SSS_ASS")
	private String desCooSssAss;

	@Column(name="DES_MET_UTI")
	private String desMetUti;

	@Column(name="DES_NOT")
	private String desNot;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1024Attivitasostegnoptof() {
	}

	public Long getPrgAttSos() {
		return this.prgAttSos;
	}

	public void setPrgAttSos(Long prgAttSos) {
		this.prgAttSos = prgAttSos;
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

	public String getDesAspSupLog() {
		return this.desAspSupLog;
	}

	public void setDesAspSupLog(String desAspSupLog) {
		this.desAspSupLog = desAspSupLog;
	}

	public String getDesCntAttSos() {
		return this.desCntAttSos;
	}

	public void setDesCntAttSos(String desCntAttSos) {
		this.desCntAttSos = desCntAttSos;
	}

	public String getDesCooSssAss() {
		return this.desCooSssAss;
	}

	public void setDesCooSssAss(String desCooSssAss) {
		this.desCooSssAss = desCooSssAss;
	}

	public String getDesMetUti() {
		return this.desMetUti;
	}

	public void setDesMetUti(String desMetUti) {
		this.desMetUti = desMetUti;
	}

	public String getDesNot() {
		return this.desNot;
	}

	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}



}