package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1012_TIPOPRIORITA database table.
 * 
 */
@Entity
@Table(name="TBS1012_TIPOPRIORITA", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1012Tipopriorita.findAll", query="SELECT t FROM Tbs1012Tipopriorita t")
public class Tbs1012Tipopriorita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1012_TIPOPRIORITA_CODTIPPRI_GENERATOR", sequenceName="SEQUENCENAME")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1012_TIPOPRIORITA_CODTIPPRI_GENERATOR")
	@Column(name="COD_TIP_PRI")
	private String codTipPri;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_PRI")
	private String desTipPri;

	public Tbs1012Tipopriorita() {
	}

	public String getCodTipPri() {
		return this.codTipPri;
	}

	public void setCodTipPri(String codTipPri) {
		this.codTipPri = codTipPri;
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

	public String getDesTipPri() {
		return this.desTipPri;
	}

	public void setDesTipPri(String desTipPri) {
		this.desTipPri = desTipPri;
	}
	
	
}