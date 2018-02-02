package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoProgettoDTO;

/**
 * @author sarellano
 * RF046–  Fabbisogno connesso a progetto
 * 
 */
public class ComponenteFabbisognoConnessoProgettoDTO extends  ComponenteBaseListDTO<FabbisognoConnessoProgettoDTO>   {
	private static final long serialVersionUID = 8447390037568321877L;
	/**
	 * Fabbisogno connesso a progetto
	 */

	 

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_FABBISOGNO_CONNESSO_PROGETTO;
	}
}
