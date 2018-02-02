package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TempoScuola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="TEMPO_SCUOLA")
	private String tempoScuola;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempoScuola() {
		return tempoScuola;
	}

	public void setTempoScuola(String tempoScuola) {
		this.tempoScuola = tempoScuola;
	}

	
	
}
