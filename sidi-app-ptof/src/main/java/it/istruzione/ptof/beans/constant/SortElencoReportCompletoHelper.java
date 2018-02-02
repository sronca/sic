package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum SortElencoReportCompletoHelper {

	CODICE_MECCANOGRAFICO("1", "id.codScuUte"),
	REGIONE("2", "mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.mfg1012Regione.desReg"),
	DENOMINAZIONE("3", "mfg1002Anagistscol.desNomScu"),
	PROVINCIA("4", "mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.desPrv"),
	COMUNE("5", "mfg1002Anagistscol.mfg1014Comune.desCom");
	

	private BeanValueDTO bean;

	SortElencoReportCompletoHelper(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}
	
	public String getLabel() {
		return bean.getLabel();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static SortElencoReportCompletoHelper getInstanceFromCode(String value){
		SortElencoReportCompletoHelper instance = null;
		for (SortElencoReportCompletoHelper elem : SortElencoReportCompletoHelper.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	
	
	
	
	
}
