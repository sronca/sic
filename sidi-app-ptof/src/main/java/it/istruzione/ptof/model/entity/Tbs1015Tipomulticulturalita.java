package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TBS1015_TIPOMULTICULTURALITA database table.
 * 
 */
@Entity
@Table(name="TBS1015_TIPOMULTICULTURALITA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1015Tipomulticulturalita.findAll", query="SELECT t FROM Tbs1015Tipomulticulturalita t")
public class Tbs1015Tipomulticulturalita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1015_TIPOMULTICULTURALITA_CODTIPMLT_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1015_TIPOMULTICULTURALITA_CODTIPMLT_GENERATOR")
	@Column(name="COD_TIP_MLT")
	private String codTipMlt;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_MLT")
	private String desTipMlt;

	public Tbs1015Tipomulticulturalita() {
	}

	public String getCodTipMlt() {
		return this.codTipMlt;
	}

	public void setCodTipMlt(String codTipMlt) {
		this.codTipMlt = codTipMlt;
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

	public String getDesTipMlt() {
		return this.desTipMlt;
	}

	public void setDesTipMlt(String desTipMlt) {
		this.desTipMlt = desTipMlt;
	}

}