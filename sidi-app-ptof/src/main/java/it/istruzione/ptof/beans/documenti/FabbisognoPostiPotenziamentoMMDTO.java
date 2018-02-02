package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro
 *  RF044– Fabbisogno di posti di potenziamento 
 *  Infanzia e Primaria
 */
public class FabbisognoPostiPotenziamentoMMDTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {

	private String key;
 	
	private String motivazione, classeConcorso; 
	
	private Integer  postiPotenziamentoPrimoAnno, postiPotenziamentoSecondoAnno, postiPotenziamentoTerzoAnno;

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

	public String getClasseConcorso() {
		return classeConcorso;
	}

	public void setClasseConcorso(String classeConcorso) {
		this.classeConcorso = classeConcorso;
	}

	public Integer getPostiPotenziamentoPrimoAnno() {
		return postiPotenziamentoPrimoAnno;
	}

	public void setPostiPotenziamentoPrimoAnno(Integer postiPotenziamentoPrimoAnno) {
		this.postiPotenziamentoPrimoAnno = postiPotenziamentoPrimoAnno;
	}

	public Integer getPostiPotenziamentoSecondoAnno() {
		return postiPotenziamentoSecondoAnno;
	}

	public void setPostiPotenziamentoSecondoAnno(
			Integer postiPotenziamentoSecondoAnno) {
		this.postiPotenziamentoSecondoAnno = postiPotenziamentoSecondoAnno;
	}

	public Integer getPostiPotenziamentoTerzoAnno() {
		return postiPotenziamentoTerzoAnno;
	}

	public void setPostiPotenziamentoTerzoAnno(Integer postiPotenziamentoTerzoAnno) {
		this.postiPotenziamentoTerzoAnno = postiPotenziamentoTerzoAnno;
	}
}
