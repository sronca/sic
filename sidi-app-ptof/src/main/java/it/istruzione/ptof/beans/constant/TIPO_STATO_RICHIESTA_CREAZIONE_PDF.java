package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_STATO_RICHIESTA_CREAZIONE_PDF {

	/**
	 * Attesa generazione pdf
	 */
	RICHIESTA_DA_EVADERE("0", "RICHIESTA DA EVADERE"),
	/**
	 * generazione pdf terminata 
	 */
	RICHIESTA_EVASA_CON_SUCCESSO("1", "RICHIESTA EVASA CON SUCCESSO"),
	
	NESSUNA_RICHIESTA("3", "RICHIESTA EVASA CON SUCCESSO"),
	
	RICHIESTA_EVASA_CON_ERRORE("2", "RICHIESTA EVASA CON ERRORE");
	
	
	private BeanValueDTO bean;

	TIPO_STATO_RICHIESTA_CREAZIONE_PDF(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_STATO_RICHIESTA_CREAZIONE_PDF getInstanceFromCode(String value){
		TIPO_STATO_RICHIESTA_CREAZIONE_PDF instance = null;
		for (TIPO_STATO_RICHIESTA_CREAZIONE_PDF elem : TIPO_STATO_RICHIESTA_CREAZIONE_PDF.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	 
}

