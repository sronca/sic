package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOLibro implements Serializable {

	private String annoScolastico;
	private String codiceMeccanografico;
	private String classe;
	private String sezione;
	private String indirizzoDiStudio;
	
	private String nomeFile;
	private byte[] contenutoFile;
	private String nomeProgrammaUltimoMovimento;
	private String nomeUtenteUltimoMovimento;
	private String progressivo;
	private String dataAggiornamento;
	
	private String id;
	
	public String getAnnoScolastico() {
		return annoScolastico;
	}
	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}
	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}
	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public String getIndirizzoDiStudio() {
		return indirizzoDiStudio;
	}
	public void setIndirizzoDiStudio(String indirizzoDiStudio) {
		this.indirizzoDiStudio = indirizzoDiStudio;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getNomeProgrammaUltimoMovimento() {
		return nomeProgrammaUltimoMovimento;
	}
	public void setNomeProgrammaUltimoMovimento(String nomeProgrammaUltimoMovimento) {
		this.nomeProgrammaUltimoMovimento = nomeProgrammaUltimoMovimento;
	}
	public String getNomeUtenteUltimoMovimento() {
		return nomeUtenteUltimoMovimento;
	}
	public void setNomeUtenteUltimoMovimento(String nomeUtenteUltimoMovimento) {
		this.nomeUtenteUltimoMovimento = nomeUtenteUltimoMovimento;
	}
	public byte[] getContenutoFile() {
		return contenutoFile;
	}
	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}
	public String getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(String progressivo) {
		this.progressivo = progressivo;
	}
	public String getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}