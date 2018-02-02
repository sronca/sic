package it.istruzione.cercalatuascuola.ricerca.dao.model;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoCookieScuoleSelezionate {
	
	@JsonProperty("items")
	private List<HashMap<String, String>> items;

	public List<HashMap<String, String>> getItems() {
		return items;
	}

	public void setItems(List<HashMap<String, String>> items) {
		this.items = items;
	}




	
	


	
	

}
