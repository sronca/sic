package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro
 *  RF018– Articolazione Oraria
 */
public class ArticolazioneTempiScuolaDTO extends PtofItemsDTO {

	private String key;

	private String descrizioneTempoScuola ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescrizioneTempoScuola() {
		return descrizioneTempoScuola;
	}

	public void setDescrizioneTempoScuola(String descrizioneTempoScuola) {
		this.descrizioneTempoScuola = descrizioneTempoScuola;
	} 
}
