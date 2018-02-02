package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro Sezione 13 - RF042– Fabbisogno di posti e cattedre 
 *         Anno Scolastico Posti Comuni Motivazione
 */
public class FabbisognoPostiCattedreDTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {
	
	private String key;

	private Integer postiComuniPrimoAnnoTriennio;
	private Integer postiComuniSecondoAnnoTriennio;
	private Integer postiComuniTerzoAnnoTriennio;
	
	private String motivazione;
	
	
	/**
	 * impostazione delle azioni affettuali sul items in maschera
	 */
	private boolean modificabile, visualizzabile,cancellabile;


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPostiComuniPrimoAnnoTriennio() {
		return postiComuniPrimoAnnoTriennio;
	}


	public void setPostiComuniPrimoAnnoTriennio(Integer postiComuniPrimoAnnoTriennio) {
		this.postiComuniPrimoAnnoTriennio = postiComuniPrimoAnnoTriennio;
	}


	public Integer getPostiComuniSecondoAnnoTriennio() {
		return postiComuniSecondoAnnoTriennio;
	}


	public void setPostiComuniSecondoAnnoTriennio(
			Integer postiComuniSecondoAnnoTriennio) {
		this.postiComuniSecondoAnnoTriennio = postiComuniSecondoAnnoTriennio;
	}


	public Integer getPostiComuniTerzoAnnoTriennio() {
		return postiComuniTerzoAnnoTriennio;
	}


	public void setPostiComuniTerzoAnnoTriennio(Integer postiComuniTerzoAnnoTriennio) {
		this.postiComuniTerzoAnnoTriennio = postiComuniTerzoAnnoTriennio;
	}


	public String getMotivazione() {
		return motivazione;
	}


	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}


	public boolean isModificabile() {
		return modificabile;
	}


	public void setModificabile(boolean modificabile) {
		this.modificabile = modificabile;
	}


	public boolean isVisualizzabile() {
		return visualizzabile;
	}


	public void setVisualizzabile(boolean visualizzabile) {
		this.visualizzabile = visualizzabile;
	}


	public boolean isCancellabile() {
		return cancellabile;
	}


	public void setCancellabile(boolean cancellabile) {
		this.cancellabile = cancellabile;
	}
 	
	
}
