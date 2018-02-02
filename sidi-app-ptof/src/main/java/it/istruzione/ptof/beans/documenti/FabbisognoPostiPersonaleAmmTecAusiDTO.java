package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

public class FabbisognoPostiPersonaleAmmTecAusiDTO extends PtofItemsDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5529444869697778563L;
	
	private String key;
	private String figuraProfessionale;
	private Integer numeroPostiPrimoAnnoTriennio;
	private Integer numeroPostiSecondoAnnoTriennio;
	private Integer numeroPostiTerzoAnnoTriennio;
	private String motivazione;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFiguraProfessionale() {
		return figuraProfessionale;
	}
	public void setFiguraProfessionale(String figuraProfessionale) {
		this.figuraProfessionale = figuraProfessionale;
	}
	public Integer getNumeroPostiPrimoAnnoTriennio() {
		return numeroPostiPrimoAnnoTriennio;
	}
	public void setNumeroPostiPrimoAnnoTriennio(Integer numeroPostiPrimoAnnoTriennio) {
		this.numeroPostiPrimoAnnoTriennio = numeroPostiPrimoAnnoTriennio;
	}
	public Integer getNumeroPostiSecondoAnnoTriennio() {
		return numeroPostiSecondoAnnoTriennio;
	}
	public void setNumeroPostiSecondoAnnoTriennio(
			Integer numeroPostiSecondoAnnoTriennio) {
		this.numeroPostiSecondoAnnoTriennio = numeroPostiSecondoAnnoTriennio;
	}
	public Integer getNumeroPostiTerzoAnnoTriennio() {
		return numeroPostiTerzoAnnoTriennio;
	}
	public void setNumeroPostiTerzoAnnoTriennio(Integer numeroPostiTerzoAnnoTriennio) {
		this.numeroPostiTerzoAnnoTriennio = numeroPostiTerzoAnnoTriennio;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}	
}
