package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

import java.math.BigDecimal;

public class FabbisognoConnessoFormazioneDTO extends PtofItemsDTO {
	private static final long serialVersionUID = 3972289344388942836L;

	private String key;

	private BeanValueDTO ambitoFormativo;
	private BeanValueDTO denominazionePercorsoFormativo;

	private String nomeBeneServizio;
	private String descrizioneBeneServizio;
	private String classificazione;
	private Integer numeroDocentiATACoinvolti;
	private BigDecimal importoStimato;
	private BigDecimal totaleImportoStimatoAmbitoFormativo;
	private String note;
	
	private String codiceMeccanograficoPlesso;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BeanValueDTO getAmbitoFormativo() {
		return ambitoFormativo;
	}
	public void setAmbitoFormativo(BeanValueDTO ambitoFormativo) {
		this.ambitoFormativo = ambitoFormativo;
	}
	public BeanValueDTO getDenominazionePercorsoFormativo() {
		return denominazionePercorsoFormativo;
	}
	public void setDenominazionePercorsoFormativo(
			BeanValueDTO denominazionePercorsoFormativo) {
		this.denominazionePercorsoFormativo = denominazionePercorsoFormativo;
	}
	public String getNomeBeneServizio() {
		return nomeBeneServizio;
	}
	public void setNomeBeneServizio(String nomeBeneServizio) {
		this.nomeBeneServizio = nomeBeneServizio;
	}
	public String getDescrizioneBeneServizio() {
		return descrizioneBeneServizio;
	}
	public void setDescrizioneBeneServizio(String descrizioneBeneServizio) {
		this.descrizioneBeneServizio = descrizioneBeneServizio;
	}
	public String getClassificazione() {
		return classificazione;
	}
	public void setClassificazione(String classificazione) {
		this.classificazione = classificazione;
	}
	public Integer getNumeroDocentiATACoinvolti() {
		return numeroDocentiATACoinvolti;
	}
	public void setNumeroDocentiATACoinvolti(Integer numeroDocentiATACoinvolti) {
		this.numeroDocentiATACoinvolti = numeroDocentiATACoinvolti;
	}
	public BigDecimal getImportoStimato() {
		return importoStimato;
	}
	public void setImportoStimato(BigDecimal importoStimato) {
		this.importoStimato = importoStimato;
	}
	public BigDecimal getTotaleImportoStimatoAmbitoFormativo() {
		return totaleImportoStimatoAmbitoFormativo;
	}
	public void setTotaleImportoStimatoAmbitoFormativo(
			BigDecimal totaleImportoStimatoAmbitoFormativo) {
		this.totaleImportoStimatoAmbitoFormativo = totaleImportoStimatoAmbitoFormativo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCodiceMeccanograficoPlesso() {
		return codiceMeccanograficoPlesso;
	}
	public void setCodiceMeccanograficoPlesso(String codiceMeccanograficoPlesso) {
		this.codiceMeccanograficoPlesso = codiceMeccanograficoPlesso;
	}
	
}
