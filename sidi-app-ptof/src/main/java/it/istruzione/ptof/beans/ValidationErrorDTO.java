package it.istruzione.ptof.beans;

import java.util.LinkedList;
import java.util.List;


import it.istruzione.ptof.beans.constant.TIPO_ERROR;

 
public class ValidationErrorDTO {
	
	private String key;
	
	private TIPO_ERROR tipoErrore;

	/**
	 * messaggio opzionale per introddure i messagggi 
	 * es : Compilare tutti i dati delle sezioni/sottosezioni obbligatorie sotto indicate :
	 */
	private String message;
	
	/**
	 * lista di errori/messaggi da visualizzare a video
	 */
	private LinkedList<ValidationErrorFiedDTO> fieldErrors;
	
	public List<ValidationErrorFiedDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(LinkedList<ValidationErrorFiedDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TIPO_ERROR getTipoErrore() {
		return tipoErrore;
	}

	public void setTipoErrore(TIPO_ERROR tipoErrore) {
		this.tipoErrore = tipoErrore;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
