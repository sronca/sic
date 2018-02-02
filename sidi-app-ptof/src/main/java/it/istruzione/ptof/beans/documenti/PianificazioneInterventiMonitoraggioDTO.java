package it.istruzione.ptof.beans.documenti;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

public class PianificazioneInterventiMonitoraggioDTO extends PtofItemsDTO {
	private String key, pianificazioneAttivita, descrizione, capitoloPTOF;
	
	private Date dataInizio, dataFine;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPianificazioneAttivita() {
		return pianificazioneAttivita;
	}

	public void setPianificazioneAttivita(String pianificazioneAttivita) {
		this.pianificazioneAttivita = pianificazioneAttivita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCapitoloPTOF() {
		return capitoloPTOF;
	}

	public void setCapitoloPTOF(String capitoloPTOF) {
		this.capitoloPTOF = capitoloPTOF;
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
}
