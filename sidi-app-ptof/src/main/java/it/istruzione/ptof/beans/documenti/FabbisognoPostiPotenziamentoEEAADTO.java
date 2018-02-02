package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro
 *  RF044– Fabbisogno di posti di potenziamento 
 *  Infanzia e Primaria
 */
public class FabbisognoPostiPotenziamentoEEAADTO extends PtofItemsDTO   {

	private String key;
 	
	private String motivazione; 
	
	private Integer  postiComuniPrimoAnno, postiComuniSecondoAnno, postiComuniTerzoAnno, postiSostegnoPrimoAnno, postiSostegnoSecondoAnno, postiSostegnoTerzoAnno;

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

	public Integer getPostiComuniPrimoAnno() {
		return postiComuniPrimoAnno;
	}

	public void setPostiComuniPrimoAnno(Integer postiComuniPrimoAnno) {
		this.postiComuniPrimoAnno = postiComuniPrimoAnno;
	}

	public Integer getPostiComuniSecondoAnno() {
		return postiComuniSecondoAnno;
	}

	public void setPostiComuniSecondoAnno(Integer postiComuniSecondoAnno) {
		this.postiComuniSecondoAnno = postiComuniSecondoAnno;
	}

	public Integer getPostiComuniTerzoAnno() {
		return postiComuniTerzoAnno;
	}

	public void setPostiComuniTerzoAnno(Integer postiComuniTerzoAnno) {
		this.postiComuniTerzoAnno = postiComuniTerzoAnno;
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
