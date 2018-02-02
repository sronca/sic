package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FabbisogniScuola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="KEY")
	private String key;
	
	@Column(name="COD")
	private String cod;
	
	@Column(name="DES")
	private String des;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

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
