package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_STATO_DOC {

	IN_COMPILAZIONE("2", "IN COMPILAZIONE"),
	CONVALIDATO("3", "CONVALIDATO"), 
	NON_INSERITO("5", "NON INSERITO"),
	PUBBLICATO_PARZIALMENTE("6", "PUBBLICATO PARZIALMENTE"),
	PUBBLICATO_COMPLETO("7", "PUBBLICATO COMPLETO"),
	FABBISOGNO_VALIDATO("10","FABBISOGNO VALIDATO"), // PDF completo 
	FABBISOGNO_NON_VALIDATO("12","FABBISOGNO NON VALIDATO"),
	/**
	 * Stato successivo alla convalida del documento 
	 * : use per visuliazzare nella funzione di pubblica ptof  la label 'pubblicazione parziale'
	 */
	DOCUMENTO_IN_ANTEPRIMA("11","DOCUMENTO IN ANTEPRIMA") ;
	
	
	

	private BeanValueDTO bean;

	TIPO_STATO_DOC(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_STATO_DOC getInstanceFromCode(String value){
		TIPO_STATO_DOC instance = null;
		for (TIPO_STATO_DOC elem : TIPO_STATO_DOC.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	
	
	
	
	
}
