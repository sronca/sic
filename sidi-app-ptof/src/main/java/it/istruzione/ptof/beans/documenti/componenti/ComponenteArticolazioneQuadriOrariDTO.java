package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariDTO;

public class ComponenteArticolazioneQuadriOrariDTO extends  ComponenteBaseListDTO<ArticolazioneQuadriOrariDTO>   {
 
	/**
     * nota informativa
     */
    private String nota;
    
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO;
	}
 
	public String getNota() {
		return nota;
	}
 
	public void setNota(String nota) {
		this.nota = nota;
	}

	 
	 
}
