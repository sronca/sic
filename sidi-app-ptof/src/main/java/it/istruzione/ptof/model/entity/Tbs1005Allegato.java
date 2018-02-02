package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TBS1005_ALLEGATO database table.
 * 
 */
@Entity
@Table(name="TBS1005_ALLEGATO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1005Allegato.findAll", query="SELECT t FROM Tbs1005Allegato t")
public class Tbs1005Allegato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1005_ALLEGATO_PRGALL_GENERATOR", sequenceName="Q1005ALLEGATO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1005_ALLEGATO_PRGALL_GENERATOR")
	@Column(name="PRG_ALL")
	private Long prgAll;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Lob
	@Column(name="OGG_ALL")
	private byte[] oggAll;
	
	@Column(name="DES_NOM_ALL")
	private String desNomAll;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1005Allegato() {
	}

	public Long getPrgAll() {
		return this.prgAll;
	}

	public void setPrgAll(Long prgAll) {
		this.prgAll = prgAll;
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

	public byte[] getOggAll() {
		return this.oggAll;
	}

	public void setOggAll(byte[] oggAll) {
		this.oggAll = oggAll;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}

	public String getDesNomAll() {
		return desNomAll;
	}

	public void setDesNomAll(String desNomAll) {
		this.desNomAll = desNomAll;
	}



}