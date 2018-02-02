package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro RF038– Collaborazioni con enti locali e territorio
 *         CONVENZIONI STIPULATE PER IL POTENZIAMENTO FORMATIVO
 */
public class ConvPotenzFormativoDTO extends PtofItemsDTO {

	private String key, convenzioni, organizzazione, note, areaTematicaPNSD;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getConvenzioni() {
		return convenzioni;
	}

	public void setConvenzioni(String convenzioni) {
		this.convenzioni = convenzioni;
	}

	public String getOrganizzazione() {
		return organizzazione;
	}

	public void setOrganizzazione(String organizzazione) {
		this.organizzazione = organizzazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

}
