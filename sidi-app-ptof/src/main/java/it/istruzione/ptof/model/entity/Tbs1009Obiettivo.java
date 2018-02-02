package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1009_OBIETTIVO database table.
 * 
 */
@Entity
@Table(name="TBS1009_OBIETTIVO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1009Obiettivo.findAll", query="SELECT t FROM Tbs1009Obiettivo t")
public class Tbs1009Obiettivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1009_OBIETTIVO_PRGOBI_GENERATOR", sequenceName="Q1009OBIETTIVO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1009_OBIETTIVO_PRGOBI_GENERATOR")
	@Column(name="PRG_OBI")
	private Long prgObi;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_BEN_ATT")
	private String desBenAtt;

	@Column(name="DES_FIN")
	private String desFin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRG_TIP_OBI")
	private Tbs1008Tipoobiettivo tbs1008Tipoobiettivo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COD_TIP_PRI")
	private Tbs1012Tipopriorita tbs1012Tipopriorita;

	public Tbs1009Obiettivo() {
	}

	public Long getPrgObi() {
		return this.prgObi;
	}

	public void setPrgObi(Long prgObi) {
		this.prgObi = prgObi;
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

	public String getDesBenAtt() {
		return this.desBenAtt;
	}

	public void setDesBenAtt(String desBenAtt) {
		this.desBenAtt = desBenAtt;
	}

	public String getDesFin() {
		return this.desFin;
	}

	public void setDesFin(String desFin) {
		this.desFin = desFin;
	}

	public Tbs1008Tipoobiettivo getTbs1008Tipoobiettivo() {
		return this.tbs1008Tipoobiettivo;
	}

	public void setTbs1008Tipoobiettivo(Tbs1008Tipoobiettivo tbs1008Tipoobiettivo) {
		this.tbs1008Tipoobiettivo = tbs1008Tipoobiettivo;
	}

	public Tbs1012Tipopriorita getTbs1012Tipopriorita() {
		return this.tbs1012Tipopriorita;
	}

	public void setTbs1012Tipopriorita(Tbs1012Tipopriorita tbs1012Tipopriorita) {
		this.tbs1012Tipopriorita = tbs1012Tipopriorita;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	
}