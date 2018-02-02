package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.PianoNazionaleScuolaDigitaleDTO;

import java.util.LinkedList;

/**
 * @author sarellano
 * RF049– Piano Nazionale della Scuola Digitale
 * Il sistema permette l’inserimento delle informazioni riguardanti la Sottosezione 
 * “Piano Nazionale della Scuola Digitale” .  
 * 
 */
public class ComponentePianoNazionaleScuolaDigitaleDTO extends  ComponenteBaseListDTO<PianoNazionaleScuolaDigitaleDTO>   {
	
	private static final long serialVersionUID = -5487565962389425519L;
	/**
	 * Alternanza Scuola Lavoro
	 */


	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_PIANO_NAZIONALE_SCUOLA_DIGITALE;
	}
}
