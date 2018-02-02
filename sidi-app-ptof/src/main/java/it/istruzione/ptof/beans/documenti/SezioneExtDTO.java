package it.istruzione.ptof.beans.documenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;

public class SezioneExtDTO extends SezioneBaseDTO {
  
	
	/**
	 * Inserire almeno uno componente della sezione 
	 */
	private Boolean obbInsAlmenoUnCom ;
	
	private LinkedList<ComponenteDTO> componenti ;

	public LinkedList<ComponenteDTO> getComponenti() {
		return componenti;
	}

	public void setComponenti(LinkedList<ComponenteDTO> componenti) {
		this.componenti = componenti;
	}

	public Boolean getObbInsAlmenoUnCom() {
		return obbInsAlmenoUnCom;
	}

	public void setObbInsAlmenoUnCom(Boolean obbInsAlmenoUnCom) {
		this.obbInsAlmenoUnCom = obbInsAlmenoUnCom;
	}
	
	
}
