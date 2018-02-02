package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_FILE_PDF {

	VISUALIZZAZIONE_DOCUMENTO_CONVALIDATO("VISUALIZZAZIONE_DOCUMENTO_CONVALIDATO", "CONVALIDATO"),
	PUBBLICAZIONE_PARZIALE("PUBBLICATO_PARZIALMENTE", "PARZIALE"),
	PUBBLICAZIONE_COMPLETA("PUBBLICATO_COMPLETO", "COMPLETO");
	
	
	private BeanValueDTO bean;

	TIPO_FILE_PDF(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_FILE_PDF getInstanceFromCode(String value){
		TIPO_FILE_PDF instance = null;
		for (TIPO_FILE_PDF elem : TIPO_FILE_PDF.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}

	 public static void main(String args[]){
		 String pp = "PUBBLICAZIONE_COMPLETA";
		 TIPO_FILE_PDF instance = TIPO_FILE_PDF.getInstanceFromCode(pp) ;
		 
		 System.out.println(instance);
		 
	 }
	 
	 
	 
}

