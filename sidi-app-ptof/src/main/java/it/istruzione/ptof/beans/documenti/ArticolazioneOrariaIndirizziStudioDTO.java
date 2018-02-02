package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro
 *  Sottosezione della Sezione 6- RF018– Articolazione Oraria
 *  
 */
public class ArticolazioneOrariaIndirizziStudioDTO extends PtofItemsDTO {

	private String key;
	
	private String indirizzoStudio, descrizione;

	public String getIndirizzoStudio() {
		return indirizzoStudio;
	}

	public void setIndirizzoStudio(String indirizzoStudio) {
		this.indirizzoStudio = indirizzoStudio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
