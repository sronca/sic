package it.istruzione.ptof.controller.ptof;

import java.util.LinkedList;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;

public class GestionePtofForm {

	private String keyDocumento ;
	private String keySezione ;

	private LinkedList<ComponenteDTO> componenti ;
	
	public String getKeyDocumento() {
		return keyDocumento;
	}

	public void setKeyDocumento(String keyDocumento) {
		this.keyDocumento = keyDocumento;
	}

	public String getKeySezione() {
		return keySezione;
	}

	public void setKeySezione(String keySezione) {
		this.keySezione = keySezione;
	}

	public LinkedList<ComponenteDTO> getComponenti() {
		return componenti;
	}

	public void setComponenti(LinkedList<ComponenteDTO> componenti) {
		this.componenti = componenti;
	}
	
}
