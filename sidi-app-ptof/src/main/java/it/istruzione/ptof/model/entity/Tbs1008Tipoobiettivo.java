package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1008_TIPOOBIETTIVO database table.
 * 
 */
@Entity
@Table(name="TBS1008_TIPOOBIETTIVO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1008Tipoobiettivo.findAll", query="SELECT t FROM Tbs1008Tipoobiettivo t")
public class Tbs1008Tipoobiettivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1008_TIPOOBIETTIVO_PRGTIPOBI_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1008_TIPOOBIETTIVO_PRGTIPOBI_GENERATOR")
	@Column(name="PRG_TIP_OBI")
	private Long prgTipObi;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_OBI")
	private String desTipObi;

	public Tbs1008Tipoobiettivo() {
	}

	public Long getPrgTipObi() {
		return this.prgTipObi;
	}

	public void setPrgTipObi(Long prgTipObi) {
		this.prgTipObi = prgTipObi;
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

	public String getDesTipObi() {
		return this.desTipObi;
	}

	public void setDesTipObi(String desTipObi) {
		this.desTipObi = desTipObi;
	}
	
	
}