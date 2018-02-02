package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro
 *  RF043– Fabbisogno di posti di sostegno 
 *   MM  ( Secondarie di II° Grado)  
 */
public class FabbisognoPostiSostegnoMMIIDTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {

	private String key;

	private String motivazione; 
	
	private Integer numeroPostiPrimoAnnoTriennio;
	private Integer numeroPostiSecondoAnnoTriennio;
	private Integer numeroPostiTerzoAnnoTriennio;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
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
 
	
	 
}
