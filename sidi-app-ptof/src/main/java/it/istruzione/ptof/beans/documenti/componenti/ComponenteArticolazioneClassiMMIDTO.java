package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
/*
 * RF028– Organizzazione Classi 
 *  Articolazione Classi  'MM' ( Scuola I° Grado)    
 */
public class ComponenteArticolazioneClassiMMIDTO extends  ComponenteBaseListDTO<ArticolazioneClassiIMMIDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ORGANIZZAZIONE_CLASSI_MMI;
	}

	
}
