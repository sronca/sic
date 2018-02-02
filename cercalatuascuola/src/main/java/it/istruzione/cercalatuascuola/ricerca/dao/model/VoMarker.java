package it.istruzione.cercalatuascuola.ricerca.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoMarker {
	

	@JsonProperty("data")
	private String data;

	@JsonProperty("lat")
	private String lat;

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("address")
	private String address;

	@JsonProperty("icon")
	private String icon;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}





	
	


	
	

}
