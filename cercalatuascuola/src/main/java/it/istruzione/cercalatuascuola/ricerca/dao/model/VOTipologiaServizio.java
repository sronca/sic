package it.istruzione.cercalatuascuola.ricerca.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VOTipologiaServizio implements Serializable {

	private String codTipSer;
	private String desTipSer;
	
	private List<VOServizio> servizi = new ArrayList<VOServizio>();
	
	public String getCodTipSer() {
		return codTipSer;
	}
	
	public void setCodTipSer(String codTipSer) {
		this.codTipSer = codTipSer;
	}
	
	public String getDesTipSer() {
		return desTipSer;
	}
	
	public void setDesTipSer(String desTipSer) {
		this.desTipSer = desTipSer;
	}

	public List<VOServizio> getServizi() {
		return servizi;
	}

	public void setServizi(List<VOServizio> servizi) {
		this.servizi = servizi;
	}
}

