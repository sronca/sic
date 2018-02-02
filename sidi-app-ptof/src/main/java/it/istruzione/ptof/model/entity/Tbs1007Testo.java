package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1007_TESTO database table.
 * 
 */
@Entity
@Table(name="TBS1007_TESTO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1007Testo.findAll", query="SELECT t FROM Tbs1007Testo t")
public class Tbs1007Testo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1007_TESTO_PRGTES_GENERATOR", sequenceName="Q1007TESTO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1007_TESTO_PRGTES_GENERATOR")
	@Column(name="PRG_TES")
	private Long prgTes;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Lob
	@Column(name="OGG_TES")
	private byte[] oggTes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;
	
	
	public Tbs1007Testo() {
	}

	public Long getPrgTes() {
		return this.prgTes;
	}

	public void setPrgTes(Long prgTes) {
		this.prgTes = prgTes;
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

	public byte[] getOggTes() {
		return this.oggTes;
	}

	public void setOggTes(byte[] oggTes) {
		this.oggTes = oggTes;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	

}