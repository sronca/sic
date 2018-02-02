package it.istruzione.ptof.beans.documenti;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

 
/*
 * USE : RF002 – Gestisci Documento CATALOGO DOCUMENTI (TPT1001_GESTIONECATALOGODOC)
 */
public class DocumentoAnnoIncorsoDTO extends DocumentoArchivioDTO {
 
	private Date dataInizioValidita, dataFineValidita;

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

}
