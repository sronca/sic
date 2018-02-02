package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOInvalsiBase {

	private Integer annoScolasticoRiferimento;
	
	private Integer annoScolasticoRilevazione;

	private String codMeccanografico;
	
	private String materiaDesc;
	
	public Integer getAnnoScolasticoRiferimento() {
		return annoScolasticoRiferimento;
	}

	public void setAnnoScolasticoRiferimento(Integer annoScolasticoRiferimento) {
		this.annoScolasticoRiferimento = annoScolasticoRiferimento;
	}

	public Integer getAnnoScolasticoRilevazione() {
		return annoScolasticoRilevazione;
	}

	public void setAnnoScolasticoRilevazione(Integer annoScolasticoRilevazione) {
		this.annoScolasticoRilevazione = annoScolasticoRilevazione;
	}
	
	public String getCodMeccanografico() {
		return codMeccanografico;
	}

	public void setCodMeccanografico(String codMeccanografico) {
		this.codMeccanografico = codMeccanografico;
	}
	
	public String getMateriaDesc() {
		return materiaDesc;
	}

	public void setMateriaDesc(String materiaDesc) {
		this.materiaDesc = materiaDesc;
	}

	public String getMateriaDescUC() {
		return materiaDesc!=null?materiaDesc.trim().toUpperCase():"";
	}
	
	public String getMateriaDescLC() {
		return materiaDesc!=null?materiaDesc.trim().toLowerCase():"";
	}
	
}
