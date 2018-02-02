package it.istruzione.ptof.beans.documenti;

 

import java.util.LinkedList;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro Sottosezione della Sezione 6- RF018– Articolazione Oraria
 *         Quadri Orari per ogni indirizzo di studio presente a sistema
 *         nell’anno in corso.
 */
public class ArticolazioneQuadriOrariDTO extends PtofItemsDTO {

	private String key;

	/**
	 * deve essere formattato aaaa/aa
	 */
	private String annoScolastico;

	private String descrizioneIndirizzoScolastico, descrizionePianoStudio;
	
	private LinkedList<ArticolazioneQuadriOrariMateriaDTO> items ;
	 
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public String getDescrizioneIndirizzoScolastico() {
		return descrizioneIndirizzoScolastico;
	}

	public void setDescrizioneIndirizzoScolastico(String descrizioneIndirizzoScolastico) {
		this.descrizioneIndirizzoScolastico = descrizioneIndirizzoScolastico;
	}

	public String getDescrizionePianoStudio() {
		return descrizionePianoStudio;
	}

	public void setDescrizionePianoStudio(String descrizionePianoStudio) {
		this.descrizionePianoStudio = descrizionePianoStudio;
	}

	public LinkedList<ArticolazioneQuadriOrariMateriaDTO> getItems() {
		return items;
	}

	public void setItems(LinkedList<ArticolazioneQuadriOrariMateriaDTO> items) {
		this.items = items;
	}

	 

}
