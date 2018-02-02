package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_TIPOLOGICHE_SEZIONE {
	
	/**
	 * use : RF033
	 */
	AMBITI_FORMATIVI ( "6" ,"abitiFormativiL"),    
	RUOLI ( "5" ,"ruoliL"),
	PRIORITA("1","prioritaL"),
	OBBIETTIVI("2","obbiettiviL"),	
	AMBITI("3","ambitiL") , 
	TIPOPROGETTI("4","tipoProgettiL"), // UTILIZZATI NELLA RF 20 per legati ambiti -> tipoProgetti.
	TIPOPROGETTI_SCUOLA("9","tipoProgettiScuolaL"), // UTILIZZATI nel RF 46 legato all'istituto
	DENOMINAZIONE_PROGETTI_SCUOLA("7","denominazioneProgettiScuolaL"), // UTILIZZATI NELLA RF 46 legato al tipo progetto
	AMBITI_FORMATIVI_SCUOLA ( "10" ,"abitiFormativiL"),  // UTILIZZATI NELLA RF 47 Formazione
	PERCORSI_FORMATIVI("8","percorsoFormativiL"), // UTILIZZATI NELLA RF 47 Formazione
	
	
	/**
	 * use : RF072 
	 */
	REGIONI("11","regioniL"), 
	PROVINCE("12","provinceL"),
	COMUNI("13","comuniL"),
	STATO_PTOF("14","statoPtofL");
	
	
	private BeanValueDTO bean;

	TIPO_TIPOLOGICHE_SEZIONE(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_TIPOLOGICHE_SEZIONE getInstanceFromCode(String value){
		TIPO_TIPOLOGICHE_SEZIONE instance = null;
		for (TIPO_TIPOLOGICHE_SEZIONE elem : TIPO_TIPOLOGICHE_SEZIONE.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	
}
