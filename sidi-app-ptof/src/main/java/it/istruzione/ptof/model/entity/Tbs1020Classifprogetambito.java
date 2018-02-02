package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1020_CLASSIFPROGETAMBITO database table.
 * 
 */
@Entity
@Table(name="TBS1020_CLASSIFPROGETAMBITO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1020Classifprogetambito.findAll", query="SELECT t FROM Tbs1020Classifprogetambito t")
public class Tbs1020Classifprogetambito implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tbs1020ClassifprogetambitoPK id;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_DEN_PGT")
	private String desDenPgt;

	//bi-directional many-to-one association to Tbs1019Tipoambito
	@ManyToOne
	@JoinColumn(name="COD_TIP_AMB", insertable=false, updatable=false)
	private Tbs1019Tipoambito tbs1019Tipoambito;

	public Tbs1020Classifprogetambito() {
	}

	public Tbs1020ClassifprogetambitoPK getId() {
		return this.id;
	}

	public void setId(Tbs1020ClassifprogetambitoPK id) {
		this.id = id;
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

	public String getDesDenPgt() {
		return this.desDenPgt;
	}

	public void setDesDenPgt(String desDenPgt) {
		this.desDenPgt = desDenPgt;
	}

	public Tbs1019Tipoambito getTbs1019Tipoambito() {
		return this.tbs1019Tipoambito;
	}

	public void setTbs1019Tipoambito(Tbs1019Tipoambito tbs1019Tipoambito) {
		this.tbs1019Tipoambito = tbs1019Tipoambito;
	}
	
	
}