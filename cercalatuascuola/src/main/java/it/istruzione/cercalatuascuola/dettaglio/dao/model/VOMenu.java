package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.List;

public class VOMenu implements Serializable {
	private static final long serialVersionUID = 6714446427233074383L;

	private VOTipologia tipologia;
	private String area;

	private List<VOTipologia> sottomenu;

	public VOTipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(VOTipologia tipologia) {
		this.tipologia = tipologia;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<VOTipologia> getSottomenu() {
		return sottomenu;
	}

	public void setSottomenu(List<VOTipologia> sottomenu) {
		this.sottomenu = sottomenu;
	}

}
