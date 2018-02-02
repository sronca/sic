package it.istruzione.poninchiaro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScuolaJson {
	
	@JsonProperty("value")
	private String des;
	
	@JsonProperty("data")
	private String cod;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
	
	

}
