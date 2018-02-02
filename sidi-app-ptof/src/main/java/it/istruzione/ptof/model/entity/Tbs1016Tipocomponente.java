package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1016_TIPOCOMPONENTE database table.
 * 
 */
@Entity
@Table(name="TBS1016_TIPOCOMPONENTE", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1016Tipocomponente.findAll", query="SELECT t FROM Tbs1016Tipocomponente t")
public class Tbs1016Tipocomponente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1016_TIPOCOMPONENTE_CODTIPCMP_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1016_TIPOCOMPONENTE_CODTIPCMP_GENERATOR")
	@Column(name="COD_TIP_CMP")
	private String codTipCmp;

	@Column(name="COD_CAT_CMP")
	private String codCatCmp;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_CMP")
	private String desTipCmp;

	public Tbs1016Tipocomponente() {
	}

	public String getCodTipCmp() {
		return this.codTipCmp;
	}

	public void setCodTipCmp(String codTipCmp) {
		this.codTipCmp = codTipCmp;
	}

	public String getCodCatCmp() {
		return this.codCatCmp;
	}

	public void setCodCatCmp(String codCatCmp) {
		this.codCatCmp = codCatCmp;
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

	public String getDesTipCmp() {
		return this.desTipCmp;
	}

	public void setDesTipCmp(String desTipCmp) {
		this.desTipCmp = desTipCmp;
	}

}