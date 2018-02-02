package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;

/**
 * @author mcatanzaro
 *   RF018– Articolazione Oraria
 */
public class ComponenteArticolazioneOrariaIndirizziStudioDTO extends  ComponenteBaseListDTO<ArticolazioneOrariaIndirizziStudioDTO>   {
 
	/**
     * nota informativa
     */
    private String nota;
    
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO;
	}
 
	public String getNota() {
		return nota;
	}
 
	public void setNota(String nota) {
		this.nota = nota;
	}

	 
}
