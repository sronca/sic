package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IndirizzoStudio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="INDIRIZZO")
	private String indirizzo;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="ANNO_INIZIO")
	private String annoInizio;
	
	@Column(name="ANNO_FINE")
	private String annoFine;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(String annoInizio) {
		this.annoInizio = annoInizio;
	}

	public String getAnnoFine() {
		return annoFine;
	}

	public void setAnnoFine(String annoFine) {
		this.annoFine = annoFine;
	}
	
	


	
	

}
