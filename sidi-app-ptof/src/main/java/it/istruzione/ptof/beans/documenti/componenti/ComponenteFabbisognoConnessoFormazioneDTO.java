package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoFormazioneDTO;

import java.util.LinkedList;

/**
 * @author sarellano
 * RF047–  Fabbisogno connesso a formazione
 * 
 */
public class ComponenteFabbisognoConnessoFormazioneDTO extends  ComponenteBaseListDTO<FabbisognoConnessoFormazioneDTO>   {
	private static final long serialVersionUID = 5426780221354092375L;
	/**
	 * Fabbisogno connesso a formazione
	 */

 

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		return TIPO_COMPONENTE.S_FABBISOGNO_CONNESSO_FORMAZIONE;
	}
}
