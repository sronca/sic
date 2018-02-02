package it.istruzione.poninchiaro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Suggestions {
	
	@JsonProperty("suggestions")
	private List<ScuolaJson> suggestions;

	public List<ScuolaJson> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<ScuolaJson> suggestions) {
		this.suggestions = suggestions;
	}
	
	

}
