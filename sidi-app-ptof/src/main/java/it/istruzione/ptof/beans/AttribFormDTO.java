package it.istruzione.ptof.beans;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AttribFormDTO extends BaseDTO {
	
	private LinkedHashMap<String, Object> attribForm = new LinkedHashMap<>();
	
	@JsonIgnore
	public void add(String name, Object obj){
		attribForm.put(name , obj);
	}

	public LinkedHashMap<String, Object> getAttribForm() {
		return attribForm;
	}

	public void setAttribForm(LinkedHashMap<String, Object> attribForm) {
		this.attribForm = attribForm;
	}
	
}
