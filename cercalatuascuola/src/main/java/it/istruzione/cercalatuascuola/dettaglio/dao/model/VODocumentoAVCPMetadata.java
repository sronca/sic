package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.util.Date;

public class VODocumentoAVCPMetadata {
	
	private String documentAbstract;
	
	private String entePubblicatore;
	
	private String titolo;
	
	private String urlFile;
	
	private Date dataPubblicazione;
	
	private Date dataUltimoAggiornamento;
	
	private Integer annoBilancio;

	public String getDocumentAbstract() {
		return documentAbstract;
	}

	public void setDocumentAbstract(String documentAbstract) {
		this.documentAbstract = documentAbstract;
	}

	public String getEntePubblicatore() {
		return entePubblicatore;
	}

	public void setEntePubblicatore(String entePubblicatore) {
		this.entePubblicatore = entePubblicatore;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getUrlFile() {
		return urlFile;
	}

	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public Integer getAnnoBilancio() {
		return annoBilancio;
	}

	public void setAnnoBilancio(Integer annoBilancio) {
		this.annoBilancio = annoBilancio;
	}

}
