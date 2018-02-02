package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
/*
 * RF028– Organizzazione Classi 
 *  Articolazione Classi  AA 
 */
public class ComponenteArticolazioneClassiAADTO extends  ComponenteBaseListDTO<ArticolazioneClassiAADTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ORGANIZZAZIONE_CLASSI_AA;
	}

	
}
