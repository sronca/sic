package it.istruzione.ptof.beans.documenti;

import java.util.LinkedList;

 

/**
 * Rappresenta i dati comuni di una sezione
 */
public class SezioneDTO extends SezioneBaseDTO { 
	
	/**
	 * link alla sottosezione.
	 * l'ultima sezione ( FOGLIA ) e' null
	 */
	private LinkedList<SezioneDTO> sottoSezione;

	public LinkedList<SezioneDTO> getSottoSezione() {
		return sottoSezione;
	}

	public void setSottoSezione(LinkedList<SezioneDTO> sottoSezione) {
		this.sottoSezione = sottoSezione;
	}

	public boolean isRamo() {
		return !( sottoSezione==null ||sottoSezione.size()==0);
	}

}
