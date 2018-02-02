package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TBS1010_TIPOAGGREGACONTESTO database table.
 * 
 */
@Entity
@Table(name="TBS1010_TIPOAGGREGACONTESTO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1010Tipoaggregacontesto.findAll", query="SELECT t FROM Tbs1010Tipoaggregacontesto t")
public class Tbs1010Tipoaggregacontesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1010_TIPOAGGREGACONTESTO_CODTIPCTS_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1010_TIPOAGGREGACONTESTO_CODTIPCTS_GENERATOR")
	@Column(name="COD_TIP_CTS")
	private String codTipCts;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_CTS")
	private String desTipCts;

	public Tbs1010Tipoaggregacontesto() {
	}

	public String getCodTipCts() {
		return this.codTipCts;
	}

	public void setCodTipCts(String codTipCts) {
		this.codTipCts = codTipCts;
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

	public String getDesTipCts() {
		return this.desTipCts;
	}

	public void setDesTipCts(String desTipCts) {
		this.desTipCts = desTipCts;
	}

}