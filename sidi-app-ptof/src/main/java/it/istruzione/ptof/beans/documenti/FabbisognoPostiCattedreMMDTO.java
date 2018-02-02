package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro Sezione 13 - RF042– Fabbisogno di posti e cattedre 
  */
public class FabbisognoPostiCattedreMMDTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {
	
	private String key;
	
	private Integer cattedrePrimoAnnoTriennio;
	private Integer cattedreSecondoAnnoTriennio;
	private Integer cattedreTerzoAnnoTriennio;
	
	/**
	 *  codice utente della classe concorso - descrizione classe 
	 *  
	 */
	private String classeConcorso;
	
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

	public Integer getCattedrePrimoAnnoTriennio() {
		return cattedrePrimoAnnoTriennio;
	}


	public void setCattedrePrimoAnnoTriennio(Integer cattedrePrimoAnnoTriennio) {
		this.cattedrePrimoAnnoTriennio = cattedrePrimoAnnoTriennio;
	}


	public Integer getCattedreSecondoAnnoTriennio() {
		return cattedreSecondoAnnoTriennio;
	}


	public void setCattedreSecondoAnnoTriennio(Integer cattedreSecondoAnnoTriennio) {
		this.cattedreSecondoAnnoTriennio = cattedreSecondoAnnoTriennio;
	}


	public Integer getCattedreTerzoAnnoTriennio() {
		return cattedreTerzoAnnoTriennio;
	}


	public void setCattedreTerzoAnnoTriennio(Integer cattedreTerzoAnnoTriennio) {
		this.cattedreTerzoAnnoTriennio = cattedreTerzoAnnoTriennio;
	}


	public String getClasseConcorso() {
		return classeConcorso;
	}


	public void setClasseConcorso(String classeConcorso) {
		this.classeConcorso = classeConcorso;
	}


	 
 	
	
}
