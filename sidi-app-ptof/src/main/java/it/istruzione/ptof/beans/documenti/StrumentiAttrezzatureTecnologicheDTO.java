package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class StrumentiAttrezzatureTecnologicheDTO extends PtofItemsDTO {

	private String key , strumentiAttrezzature , descrizione ,areaTematicaPNSD ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStrumentiAttrezzature() {
		return strumentiAttrezzature;
	}

	public void setStrumentiAttrezzature(String strumentiAttrezzature) {
		this.strumentiAttrezzature = strumentiAttrezzature;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

	 
}
