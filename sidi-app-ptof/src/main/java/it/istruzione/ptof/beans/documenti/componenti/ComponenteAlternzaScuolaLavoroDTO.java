package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.AlternanzaScuolaLavoroDTO;

import java.util.LinkedList;

/**
 * @author sarellano
 * RF023– Alternanza Scuola Lavoro
 * Il sistema permette l’inserimento delle informazioni riguardanti la Sottosezione 
 * “Attività di sostegno” . 
 * 
 */
public class ComponenteAlternzaScuolaLavoroDTO extends  ComponenteBaseListDTO<AlternanzaScuolaLavoroDTO>  {
	private static final long serialVersionUID = 1297296769405711754L;
	 
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ALTERNANZA_SCUOLA_LAVORO;
	}
}
