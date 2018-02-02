package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOIndiceDiTempestivitaPagamenti implements java.io.Serializable {

	private String  codiceMeccanografico;
	private Integer datAnnScoRil;
	private Integer annoFinanz;
	private Integer codPrRif;
	private Double numValIndTem;
	private String numValIndTemStr;
	private String dataUltMod;
	
	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}
	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}
	public Integer getDatAnnScoRil() {
		return datAnnScoRil;
	}
	public void setDatAnnScoRil(Integer datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	public Integer getAnnoFinanz() {
		return annoFinanz;
	}
	public void setAnnoFinanz(Integer annoFinanz) {
		this.annoFinanz = annoFinanz;
	}
	public Integer getCodPrRif() {
		return codPrRif;
	}
	public void setCodPrRif(Integer codPrRif) {
		this.codPrRif = codPrRif;
	}
	public String getDescPrRif() {
		return EnumIndiceDiTempestivita.valueOf("i"+codPrRif).getDescrizione();
	}

	public Double getNumValIndTem() {
		return numValIndTem;
	}
	public void setNumValIndTem(Double numValIndTem) {
		this.numValIndTem = numValIndTem;
	}
	public String getNumValIndTemStr() {
		return numValIndTemStr;
	}
	public void setNumValIndTemStr(String numValIndTemStr) {
		this.numValIndTemStr = numValIndTemStr;
	}
	public String getDataUltMod() {
		return dataUltMod;
	}
	public void setDataUltMod(String dataUltMod) {
		this.dataUltMod = dataUltMod;
	}
	
	
}
