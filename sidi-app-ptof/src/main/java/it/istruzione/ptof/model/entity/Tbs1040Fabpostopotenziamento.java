package it.istruzione.ptof.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBS1040_FABPOSTOPOTENZIAMENTO database table.
 * 
 */
@Entity
@Table(name="TBS1040_FABPOSTOPOTENZIAMENTO", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1040Fabpostopotenziamento.findAll", query="SELECT t FROM Tbs1040Fabpostopotenziamento t")
public class Tbs1040Fabpostopotenziamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1040_FABPOSTOPOTENZIAMENTO_PRGFABPOSPTZ_GENERATOR", sequenceName="Q1040FABPOSTOPOTENZIAMENTO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1040_FABPOSTOPOTENZIAMENTO_PRGFABPOSPTZ_GENERATOR")
	@Column(name="PRG_FAB_POS_PTZ")
	private Long prgFabPosPtz;

	@Column(name="COD_CLC")
	private String codClc;

	@Column(name="COD_ORD_SCU")
	private String codOrdScu;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;

	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;

	@Column(name="DES_CLC")
	private String desClc;

	@Column(name="DES_MOT")
	private String desMot;

	@Column(name="NUM_POS_COM_PRI")
	private BigDecimal numPosComPri;
	
	@Column(name="NUM_POS_COM_SEC")
	private BigDecimal numPosComSec;
	
	@Column(name="NUM_POS_COM_TER")
	private BigDecimal numPosComTer;

	@Column(name="NUM_POS_SOS_PRI")
	private BigDecimal numPosSosPri;
	
	@Column(name="NUM_POS_SOS_SEC")
	private BigDecimal numPosSosSec;
	
	@Column(name="NUM_POS_SOS_TER")
	private BigDecimal numPosSosTer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRG_DAT_PTF")
	private Tbs1004Datisezionesottosez tbs1004Datisezionesottosez;

	public Tbs1040Fabpostopotenziamento() {
	}

	public Long getPrgFabPosPtz() {
		return prgFabPosPtz;
	}

	public void setPrgFabPosPtz(Long prgFabPosPtz) {
		this.prgFabPosPtz = prgFabPosPtz;
	}

	public String getCodClc() {
		return codClc;
	}

	public void setCodClc(String codClc) {
		this.codClc = codClc;
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

	public String getDesClc() {
		return desClc;
	}

	public void setDesClc(String desClc) {
		this.desClc = desClc;
	}

	public String getDesMot() {
		return desMot;
	}

	public void setDesMot(String desMot) {
		this.desMot = desMot;
	}

	public BigDecimal getNumPosComPri() {
		return numPosComPri;
	}

	public void setNumPosComPri(BigDecimal numPosComPri) {
		this.numPosComPri = numPosComPri;
	}

	public BigDecimal getNumPosComSec() {
		return numPosComSec;
	}

	public void setNumPosComSec(BigDecimal numPosComSec) {
		this.numPosComSec = numPosComSec;
	}

	public BigDecimal getNumPosComTer() {
		return numPosComTer;
	}

	public void setNumPosComTer(BigDecimal numPosComTer) {
		this.numPosComTer = numPosComTer;
	}

	public BigDecimal getNumPosSosPri() {
		return numPosSosPri;
	}

	public void setNumPosSosPri(BigDecimal numPosSosPri) {
		this.numPosSosPri = numPosSosPri;
	}

	public BigDecimal getNumPosSosSec() {
		return numPosSosSec;
	}

	public void setNumPosSosSec(BigDecimal numPosSosSec) {
		this.numPosSosSec = numPosSosSec;
	}

	public BigDecimal getNumPosSosTer() {
		return numPosSosTer;
	}

	public void setNumPosSosTer(BigDecimal numPosSosTer) {
		this.numPosSosTer = numPosSosTer;
	}

	public Tbs1004Datisezionesottosez getTbs1004Datisezionesottosez() {
		return tbs1004Datisezionesottosez;
	}

	public void setTbs1004Datisezionesottosez(
			Tbs1004Datisezionesottosez tbs1004Datisezionesottosez) {
		this.tbs1004Datisezionesottosez = tbs1004Datisezionesottosez;
	}


	


}