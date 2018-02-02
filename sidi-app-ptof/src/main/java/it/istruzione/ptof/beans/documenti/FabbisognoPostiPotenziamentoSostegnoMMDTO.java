package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro
 *  RF044– Fabbisogno di posti di potenziamento 
 *  Infanzia e Primaria
 */
public class FabbisognoPostiPotenziamentoSostegnoMMDTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {

	private String key;
 	
	private String motivazione; 
	
	private Integer postiSostegnoPrimoAnno, postiSostegnoSecondoAnno, postiSostegnoTerzoAnno ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public Integer getPostiSostegnoPrimoAnno() {
		return postiSostegnoPrimoAnno;
	}

	public void setPostiSostegnoPrimoAnno(Integer postiSostegnoPrimoAnno) {
		this.postiSostegnoPrimoAnno = postiSostegnoPrimoAnno;
	}

	public Integer getPostiSostegnoSecondoAnno() {
		return postiSostegnoSecondoAnno;
	}

	public void setPostiSostegnoSecondoAnno(Integer postiSostegnoSecondoAnno) {
		this.postiSostegnoSecondoAnno = postiSostegnoSecondoAnno;
	}

	public Integer getPostiSostegnoTerzoAnno() {
		return postiSostegnoTerzoAnno;
	}

	public void setPostiSostegnoTerzoAnno(Integer postiSostegnoTerzoAnno) {
		this.postiSostegnoTerzoAnno = postiSostegnoTerzoAnno;
	}
 
	

 	 
 

	
	 
}
