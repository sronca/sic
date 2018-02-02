package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author mcatanzaro
 * RAPPRESENTA LGI OBIETTIVI FORMATIVI DI CUI AL COMMA 7 DELLA LEGGE 107 e anche gli eventuali altri
 */
public class IniziativeDidatticheEducativeDTO extends PtofItemsDTO {

	 private String key;
	
	 private String titoli ;
	 
	 private String obiettivi  ;
	 
	 private String contenuti ;

	 private String modalita ;

	 private Date dataInizio ;

	 private Date dataFine ;

	 private String areaTematicaPNSD ;

	 private String note ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getObiettivi() {
		return obiettivi;
	}

	public void setObiettivi(String obiettivi) {
		this.obiettivi = obiettivi;
	}

	public String getContenuti() {
		return contenuti;
	}

	public void setContenuti(String contenuti) {
		this.contenuti = contenuti;
	}

	public String getModalita() {
		return modalita;
	}

	public void setModalita(String modalita) {
		this.modalita = modalita;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataInizio() {
		return dataInizio;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataFine() {
		return dataFine;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTitoli() {
		return titoli;
	}

	public void setTitoli(String titolo) {
		this.titoli = titolo;
	}
	 	 
 	 
}
