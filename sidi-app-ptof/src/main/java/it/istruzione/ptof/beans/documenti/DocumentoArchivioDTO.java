package it.istruzione.ptof.beans.documenti;

import java.util.Date;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;

/*
 * 
 * USE : RF002 – Gestisci Documento CATALOGO DOCUMENTI (TPT1001_GESTIONECATALOGODOC)
 * Documento Anno Scolastico Triennio Versione
 * 
 */ 
public class DocumentoArchivioDTO extends BaseDTO {
 
	/**
	 * formato alfanumerico ( non usare caratteri speciali )
	 * la key è formata dalla concatenzazione in ordine di 
	 * 		10 caratteri per il codice meccanografico
	 * 		6 cifre per l'anno scolastico
	 * 		il progressivo documento di tipo numerico
	 */
	private String key;
	
	
	/**
	 * Nome del Documento con link all’applicazione, esempio: Piano Triennale
	 * Offerta Formativa
	 */
	private String nomeDocumento;
 
	/**
	 * formato AAAA/AA
	 */
	private String annoScolastico, triennio;

	/**
	 * numero del documento: il numero di revisione è 0 se si tratta della prima
	 * redazione, 1 o 2 per le revisione degli anni successivi
	 */
	private String versione;

	/**
	 * stato del documento 
	 */
	private TIPO_STATO_DOC statoDocumento ;
	
	private String  statoDocumentoAsString ;
	
	
	public String getStatoDocumentoAsString() {
		return statoDocumentoAsString;
	}

	public void setStatoDocumentoAsString(String statoDocumentoAsString) {
		this.statoDocumentoAsString = statoDocumentoAsString;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public String getTriennio() {
		return triennio;
	}

	public void setTriennio(String triennio) {
		this.triennio = triennio;
	}

	public String getVersione() {
		return versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TIPO_STATO_DOC getStatoDocumento() {
		return statoDocumento;
	}

	public void setStatoDocumento(TIPO_STATO_DOC statoDocumento) {
		this.statoDocumento = statoDocumento;
	}
	
}
