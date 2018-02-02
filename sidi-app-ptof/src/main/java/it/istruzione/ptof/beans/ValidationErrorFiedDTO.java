package it.istruzione.ptof.beans;

import it.istruzione.ptof.beans.constant.CODICE_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;

public class ValidationErrorFiedDTO {
	
	/**
	 * usato per identificare univocamente il campo/messaggio  
	 */
	private String field;
	
	private String resource;
	
	private CODICE_ERROR code;
	
	/**
	 * messaggio da presentare a video
	 */
	private String message;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public CODICE_ERROR getCode() {
		return code;
	}

	public void setCode(CODICE_ERROR code) {
		this.code = code;
	}

	}