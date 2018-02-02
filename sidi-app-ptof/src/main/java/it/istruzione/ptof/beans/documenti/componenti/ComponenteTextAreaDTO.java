package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

public class ComponenteTextAreaDTO extends  ComponenteDTO {
    /**
     * valore immesso dall'utente nella input text
     */
    private String valore;

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.TEXTEDITOR;
	}
	 
}
