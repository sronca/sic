package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;

public class PrioritaTraguardiDTO extends BaseDTO {
     private String area, descPriorita , descTraguardo;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescPriorita() {
		return descPriorita;
	}

	public void setDescPriorita(String descPriorita) {
		this.descPriorita = descPriorita;
	}

	public String getDescTraguardo() {
		return descTraguardo;
	}

	public void setDescTraguardo(String descTraguardo) {
		this.descTraguardo = descTraguardo;
	}
     
}
