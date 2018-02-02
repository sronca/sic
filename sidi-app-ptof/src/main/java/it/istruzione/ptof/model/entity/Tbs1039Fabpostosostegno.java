package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1039_FABPOSTOSOSTEGNO database table.
 * 
 */
@Entity
@Table(name="TBS1039_FABPOSTOSOSTEGNO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1039Fabpostosostegno.findAll", query="SELECT t FROM Tbs1039Fabpostosostegno t")
public class Tbs1039Fabpostosostegno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1039_FABPOSTOSOSTEGNO_PRGFABPOSSOS_GENERATOR", sequenceName="Q1039FABPOSTOSOSTEGNO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1039_FABPOSTOSOSTEGNO_PRGFABPOSSOS_GENERATOR")
	@Column(name="PRG_FAB_POS_SOS")
	private Long prgFabPosSos;

	@Column(name="COD_ARE_DIS")
	private String codAreDis;

	@Column(name="COD_ORD_SCU")
	private String codOrdScu;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_ARE_DIS")
	private String desAreDis;

	@Column(name="DES_MOT")
	private String desMot;

	@Column(name="NUM_POS_UDI_PRI")
	private BigDecimal numPosUdiPri;

	@Column(name="NUM_POS_UDI_SEC")
	private BigDecimal numPosUdiSec;

	@Column(name="NUM_POS_UDI_TER")
	private BigDecimal numPosUdiTer;

	@Column(name="NUM_POS_VIS_PRI")
	private BigDecimal numPosVisPri;
	
	@Column(name="NUM_POS_VIS_SEC")
	private BigDecimal numPosVisSec;

	@Column(name="NUM_POS_VIS_TER")
	private BigDecimal numPosVisTer;
	
	@Column(name="NUM_POS_PSI_PRI")
	private BigDecimal numPosPsiPri;
	
	@Column(name="NUM_POS_PSI_SEC")
	private BigDecimal numPosPsiSec;

	@Column(name="NUM_POS_PSI_TER")
	private BigDecimal numPosPsiTer;
	
	@Column(name="NUM_POS_SSG_PRI")
	private BigDecimal numPosSsgPri;
	
	@Column(name="NUM_POS_SSG_SEC")
	private BigDecimal numPosSsgSec;

	@Column(name="NUM_POS_SSG_TER")
	private BigDecimal numPosSsgTer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1039Fabpostosostegno() {
	}

	public Long getPrgFabPosSos() {
		return prgFabPosSos;
	}

	public void setPrgFabPosSos(Long prgFabPosSos) {
		this.prgFabPosSos = prgFabPosSos;
	}

	public String getCodAreDis() {
		return codAreDis;
	}

	public void setCodAreDis(String codAreDis) {
		this.codAreDis = codAreDis;
	}

	public String getCodOrdScu() {
		return codOrdScu;
	}

	public void setCodOrdScu(String codOrdScu) {
		this.codOrdScu = codOrdScu;
	}

	public String getCodPgmUltMov() {
		return codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodUteUltMov() {
		return codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public Date getDatOraUltMov() {
		return datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public String getDesAreDis() {
		return desAreDis;
	}

	public void setDesAreDis(String desAreDis) {
		this.desAreDis = desAreDis;
	}

	public String getDesMot() {
		return desMot;
	}

	public void setDesMot(String desMot) {
		this.desMot = desMot;
	}

	public BigDecimal getNumPosUdiPri() {
		return numPosUdiPri;
	}

	public void setNumPosUdiPri(BigDecimal numPosUdiPri) {
		this.numPosUdiPri = numPosUdiPri;
	}

	public BigDecimal getNumPosUdiSec() {
		return numPosUdiSec;
	}

	public void setNumPosUdiSec(BigDecimal numPosUdiSec) {
		this.numPosUdiSec = numPosUdiSec;
	}

	public BigDecimal getNumPosUdiTer() {
		return numPosUdiTer;
	}

	public void setNumPosUdiTer(BigDecimal numPosUdiTer) {
		this.numPosUdiTer = numPosUdiTer;
	}

	public BigDecimal getNumPosVisPri() {
		return numPosVisPri;
	}

	public void setNumPosVisPri(BigDecimal numPosVisPri) {
		this.numPosVisPri = numPosVisPri;
	}

	public BigDecimal getNumPosVisSec() {
		return numPosVisSec;
	}

	public void setNumPosVisSec(BigDecimal numPosVisSec) {
		this.numPosVisSec = numPosVisSec;
	}

	public BigDecimal getNumPosVisTer() {
		return numPosVisTer;
	}

	public void setNumPosVisTer(BigDecimal numPosVisTer) {
		this.numPosVisTer = numPosVisTer;
	}

	public BigDecimal getNumPosPsiPri() {
		return numPosPsiPri;
	}

	public void setNumPosPsiPri(BigDecimal numPosPsiPri) {
		this.numPosPsiPri = numPosPsiPri;
	}

	public BigDecimal getNumPosPsiSec() {
		return numPosPsiSec;
	}

	public void setNumPosPsiSec(BigDecimal numPosPsiSec) {
		this.numPosPsiSec = numPosPsiSec;
	}

	public BigDecimal getNumPosPsiTer() {
		return numPosPsiTer;
	}

	public void setNumPosPsiTer(BigDecimal numPosPsiTer) {
		this.numPosPsiTer = numPosPsiTer;
	}

	public BigDecimal getNumPosSsgPri() {
		return numPosSsgPri;
	}

	public void setNumPosSsgPri(BigDecimal numPosSsgPri) {
		this.numPosSsgPri = numPosSsgPri;
	}

	public BigDecimal getNumPosSsgSec() {
		return numPosSsgSec;
	}

	public void setNumPosSsgSec(BigDecimal numPosSsgSec) {
		this.numPosSsgSec = numPosSsgSec;
	}

	public BigDecimal getNumPosSsgTer() {
		return numPosSsgTer;
	}

	public void setNumPosSsgTer(BigDecimal numPosSsgTer) {
		this.numPosSsgTer = numPosSsgTer;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}


	



}