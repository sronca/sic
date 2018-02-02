package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro
 * RAPPRESENTA LGI OBIETTIVI FORMATIVI DI CUI AL COMMA 7 DELLA LEGGE 107 e anche gli eventuali altri
 */
public class ObbiettiviFormativiDTO extends PtofItemsDTO {

	 private String key;
	
	 private BeanValueDTO obbiettivo;
	 
	 private String finalita;
	 
	 private String benefici ;
	 
	 private BeanValueDTO priorita;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	 

	public String getFinalita() {
		return finalita;
	}

	public void setFinalita(String finalita) {
		this.finalita = finalita;
	}

	public String getBenefici() {
		return benefici;
	}

	public void setBenefici(String benefici) {
		this.benefici = benefici;
	}

	public BeanValueDTO getPriorita() {
		return priorita;
	}

	public void setPriorita(BeanValueDTO priorita) {
		this.priorita = priorita;
	}

	public BeanValueDTO getObbiettivo() {
		return obbiettivo;
	}

	public void setObbiettivo(BeanValueDTO obbiettivo) {
		this.obbiettivo = obbiettivo;
	}

	 	 
 	 
}
