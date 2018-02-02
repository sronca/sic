package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneTempiScuolaDTO;

/**
 * @author mcatanzaro
 * RF018– Articolazione Oraria
 */
public class ComponenteArticolazioneTempiScuolaDTO extends  ComponenteBaseListDTO<ArticolazioneTempiScuolaDTO> {
 
	/**
     * nota informativa
     */
    private String nota;
    
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA;
	}
 
	public String getNota() {
		return nota;
	}
 
	public void setNota(String nota) {
		this.nota = nota;
	}
 
	 
}
