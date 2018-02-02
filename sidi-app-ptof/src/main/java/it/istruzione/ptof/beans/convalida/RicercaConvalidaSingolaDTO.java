package it.istruzione.ptof.beans.convalida;

import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaFiltroDTO;
import it.istruzione.ptof.beans.PageDTO;

public class RicercaConvalidaSingolaDTO extends PageDTO { 
	 
	private static final long serialVersionUID = 1L;
	
	private ConvalidaFabbisognoSingolaScuolaFiltroDTO convalidaFabbisognoSingolaScuolaFiltro ;

	public ConvalidaFabbisognoSingolaScuolaFiltroDTO getConvalidaFabbisognoSingolaScuolaFiltro() {
		return convalidaFabbisognoSingolaScuolaFiltro;
	}

	public void setConvalidaFabbisognoSingolaScuolaFiltro(
			ConvalidaFabbisognoSingolaScuolaFiltroDTO convalidaFabbisognoSingolaScuolaFiltro) {
		this.convalidaFabbisognoSingolaScuolaFiltro = convalidaFabbisognoSingolaScuolaFiltro;
	}


	
}
