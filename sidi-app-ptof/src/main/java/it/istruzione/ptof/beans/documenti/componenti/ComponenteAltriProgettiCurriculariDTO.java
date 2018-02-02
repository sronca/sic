package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;

/**
 * @author mcatanzaro
 * RF020– Progetti curricolari
 * usato SOLO per caricare la form del requisito
 * i singoli items CRUD vengono salvati atomicamente e non viene inviato tutto l'oggetto in salvataggio
 * 
 */
public class ComponenteAltriProgettiCurriculariDTO extends  ComponenteBaseListDTO<AmbitiProgettiDTO>   {
  
	
	/**
	 * Ambiti e Progetti
	 */
	 

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ALTRI_PROGETTI_CURRICULARI_EXSTRA;
	}

	 
 
	 
	
	
}
