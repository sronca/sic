package it.istruzione.ptof.controller.monitoraggio;

import it.istruzione.ptof.beans.ptof.TipologicaDTO;

public class MonitoraggioPtofTipologicheForm {

	
	private TipologicaDTO tipologica = new TipologicaDTO();
	
	private String key;
	
	private String value;
	
	private String label;
	
	public TipologicaDTO getTipologica() {
		return tipologica;
	}

	public void setTipologica(TipologicaDTO tipologica) {
		this.tipologica = tipologica;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
 

}
