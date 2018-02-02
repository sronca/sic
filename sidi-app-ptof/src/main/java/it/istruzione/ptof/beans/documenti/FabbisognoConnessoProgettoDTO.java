package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

import java.math.BigDecimal;

public class FabbisognoConnessoProgettoDTO extends PtofItemsDTO {
	private static final long serialVersionUID = -1749601747782620097L;

	private String key;

	private BeanValueDTO tipoProgetti;
	private BeanValueDTO denominazioneProgettiScuola;

	private String nomeBeneServizio;
	private String descrizioneBeneServizio;
	private String classificazione;
	private Integer numeroDocentiATACoinvolti;
	private BigDecimal importoStimato;
	private BigDecimal totaleImportoStimatoProgetto;
	private String note;
	
	private String codiceMeccanograficoPlesso;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BeanValueDTO getTipoProgetti() {
		return tipoProgetti;
	}
	public void setTipoProgetti(BeanValueDTO tipoProgetti) {
		this.tipoProgetti = tipoProgetti;
	}
	public BeanValueDTO getDenominazioneProgettiScuola() {
		return denominazioneProgettiScuola;
	}
	public void setDenominazioneProgettiScuola(
			BeanValueDTO denominazioneProgettiScuola) {
		this.denominazioneProgettiScuola = denominazioneProgettiScuola;
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
	public BigDecimal getTotaleImportoStimatoProgetto() {
		return totaleImportoStimatoProgetto;
	}
	public void setTotaleImportoStimatoProgetto(
			BigDecimal totaleImportoStimatoProgetto) {
		this.totaleImportoStimatoProgetto = totaleImportoStimatoProgetto;
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
