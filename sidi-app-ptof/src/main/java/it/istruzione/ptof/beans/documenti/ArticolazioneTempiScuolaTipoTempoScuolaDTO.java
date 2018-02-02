package it.istruzione.ptof.beans.documenti;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

/**
 * @author mcatanzaro
 *  RF018– Articolazione Oraria TipoTempoScuola
 */
public class ArticolazioneTempiScuolaTipoTempoScuolaDTO extends BaseDTO {

	private String ordineScuola, descrizioneTipoTempoScuola;

 	private String dataInizioValidita, dataFineValidita;
 
 	

	public String getOrdineScuola() {
		return ordineScuola;
	}

	public void setOrdineScuola(String ordineScuola) {
		this.ordineScuola = ordineScuola;
	}

	public String getDescrizioneTipoTempoScuola() {
		return descrizioneTipoTempoScuola;
	}

	public void setDescrizioneTipoTempoScuola(String descrizioneTipoTempoScuola) {
		this.descrizioneTipoTempoScuola = descrizioneTipoTempoScuola;
	}

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	 

}
