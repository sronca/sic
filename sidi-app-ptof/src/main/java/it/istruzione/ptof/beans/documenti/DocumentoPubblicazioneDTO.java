package it.istruzione.ptof.beans.documenti;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.constant.TIPO_STATO_RICHIESTA_CREAZIONE_PDF;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

 
/*
 * USE : RF054 – Gestisci Documento CATALOGO DOCUMENTI (TPT1001_GESTIONECATALOGODOC)
 */
public class DocumentoPubblicazioneDTO extends DocumentoArchivioDTO {
 
	/**
	 * Individia lo stato richista anteprima/pubblicazione pdf 
	 */
	private TIPO_STATO_RICHIESTA_CREAZIONE_PDF statoRichiestaCreazione ;

	public TIPO_STATO_RICHIESTA_CREAZIONE_PDF getStatoRichiestaCreazione() {
		return statoRichiestaCreazione;
	}

	public void setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF statoRichiestaCreazione) {
		this.statoRichiestaCreazione = statoRichiestaCreazione;
	}

	 
}
