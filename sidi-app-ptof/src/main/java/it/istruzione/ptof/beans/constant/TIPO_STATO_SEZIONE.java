package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_STATO_SEZIONE {

	COMPILATA("8","COMPILATA"),
	BOZZA("9","BOZZA");
	 

	private BeanValueDTO bean;

	TIPO_STATO_SEZIONE ( String value, String label ) {
		
		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}


	public static TIPO_STATO_SEZIONE getInstanceFromCode(String value){
		TIPO_STATO_SEZIONE instance = null;
		for (TIPO_STATO_SEZIONE elem : TIPO_STATO_SEZIONE.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	 
}
