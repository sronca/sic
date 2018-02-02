package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1019_TIPOAMBITO database table.
 * 
 */
@Entity
@Table(name="TBS1019_TIPOAMBITO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1019Tipoambito.findAll", query="SELECT t FROM Tbs1019Tipoambito t")
public class Tbs1019Tipoambito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIP_AMB")
	private String codTipAmb;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_TIP_AMB")
	private String desTipAmb;

	@Column(name="FLG_TIP_AMB")
	private String flgTipAmb;

	//bi-directional many-to-one association to Tbs1020Classifprogetambito
	@OneToMany(mappedBy="tbs1019Tipoambito",fetch = FetchType.LAZY)
	@OrderBy("desDenPgt")
	private List<Tbs1020Classifprogetambito> tbs1020Classifprogetambitos;

//	@OneToMany(mappedBy="tbs1019Tipoambito")
//	private List<Tbs1022Ambitoprogettoaltro> tbs1022Ambitoprogettoaltros;

	public Tbs1019Tipoambito() {
	}

	public String getCodTipAmb() {
		return this.codTipAmb;
	}

	public void setCodTipAmb(String codTipAmb) {
		this.codTipAmb = codTipAmb;
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

	public String getDesTipAmb() {
		return this.desTipAmb;
	}

	public void setDesTipAmb(String desTipAmb) {
		this.desTipAmb = desTipAmb;
	}

	public List<Tbs1020Classifprogetambito> getTbs1020Classifprogetambitos() {
		return this.tbs1020Classifprogetambitos;
	}

	public void setTbs1020Classifprogetambitos(List<Tbs1020Classifprogetambito> tbs1020Classifprogetambitos) {
		this.tbs1020Classifprogetambitos = tbs1020Classifprogetambitos;
	}

	public Tbs1020Classifprogetambito addTbs1020Classifprogetambito(Tbs1020Classifprogetambito tbs1020Classifprogetambito) {
		getTbs1020Classifprogetambitos().add(tbs1020Classifprogetambito);
		tbs1020Classifprogetambito.setTbs1019Tipoambito(this);

		return tbs1020Classifprogetambito;
	}

	public Tbs1020Classifprogetambito removeTbs1020Classifprogetambito(Tbs1020Classifprogetambito tbs1020Classifprogetambito) {
		getTbs1020Classifprogetambitos().remove(tbs1020Classifprogetambito);
		tbs1020Classifprogetambito.setTbs1019Tipoambito(null);

		return tbs1020Classifprogetambito;
	}

	public String getFlgTipAmb() {
		return flgTipAmb;
	}

	public void setFlgTipAmb(String flgTipAmb) {
		this.flgTipAmb = flgTipAmb;
	}
	
	
	
}