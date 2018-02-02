package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBS1025_TIPORUOLO database table.
 * 
 */
@Entity
@Table(name="TBS1025_TIPORUOLO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1025Tiporuolo.findAll", query="SELECT t FROM Tbs1025Tiporuolo t")
public class Tbs1025Tiporuolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIP_RUO")
	private String codTipRuo;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_RUO")
	private String desTipRuo;
	
	public Tbs1025Tiporuolo() {
	}

	public String getCodTipRuo() {
		return this.codTipRuo;
	}

	public void setCodTipRuo(String codTipRuo) {
		this.codTipRuo = codTipRuo;
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

	public String getDesTipRuo() {
		return this.desTipRuo;
	}

	public void setDesTipRuo(String desTipRuo) {
		this.desTipRuo = desTipRuo;
	}


}