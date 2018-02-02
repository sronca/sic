package it.istruzione.ptof.beans;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;


public class FabbisognoPostiComuniItem extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String classeDiConcorso ; /** use only for IGrado and IIGrado **/
	private Long postiAnnoZero;
	private Long postiAnnoUno;
	private Long postiAnnoDue;
	private String motivazione;
	
	public FabbisognoPostiComuniItem() {
	 
	}

	public String getClasseDiConcorso() {
		return classeDiConcorso;
	}

	public void setClasseDiConcorso(String classeDiConcorso) {
		this.classeDiConcorso = classeDiConcorso;
	}

	public Long getPostiAnnoZero() {
		return postiAnnoZero;
	}

	public void setPostiAnnoZero(Long postiAnnoZero) {
		this.postiAnnoZero = postiAnnoZero;
	}

	public Long getPostiAnnoUno() {
		return postiAnnoUno;
	}

	public void setPostiAnnoUno(Long postiAnnoUno) {
		this.postiAnnoUno = postiAnnoUno;
	}

	public Long getPostiAnnoDue() {
		return postiAnnoDue;
	}

	public void setPostiAnnoDue(Long postiAnnoDue) {
		this.postiAnnoDue = postiAnnoDue;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
 
	 
}
