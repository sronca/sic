package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;

/**
 * @author mcatanzaro
 * Sottosezione della Sezione 6 - RF024–  Altre iniziative didattico/educative 
 * usato SOLO per caricare la form del requisito
 * i singoli items CRUD vengono salvati atomicamente e non viene inviato tutto l'oggetto in salvataggio
 * 
 */
public class ComponenteAltreIniziativeDidaDTO extends  ComponenteBaseListDTO<IniziativeDidatticheEducativeDTO>   {
 	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8991396119258464297L;
	/**
	 * iniziative didattiche
	 */ 

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ALTRE_INIZIATIVE_DIDATTICO;
	}

		
}
