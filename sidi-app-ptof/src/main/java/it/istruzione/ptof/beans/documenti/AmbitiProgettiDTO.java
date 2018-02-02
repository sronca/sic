package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AmbitiProgettiDTO extends PtofItemsDTO {

	private String key;

	private BeanValueDTO ambito;

	private BeanValueDTO tipologiaProgetto;

	private String denominazione, destinatari, obiettiviAltrePriorita, principaliContenuti,
			modalitApprocciFormativiUtilizzati, periodoDiSvolgimento, areaTematicaPNSD,
			rilevanzaPerScuola, cooperazioneConAltreScuole, risorseUmaneArea, altreRisorseNecessarie, statoAvanzamento;

	private Date durataDal, durataAl;
	
	private BigDecimal risorseFinanziareNecessarie;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BeanValueDTO getAmbito() {
		return ambito;
	}
	public void setAmbito(BeanValueDTO ambito) {
		this.ambito = ambito;
	}
	public BeanValueDTO getTipologiaProgetto() {
		return tipologiaProgetto;
	}
	public void setTipologiaProgetto(BeanValueDTO tipologiaProgetto) {
		this.tipologiaProgetto = tipologiaProgetto;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getDestinatari() {
		return destinatari;
	}
	public void setDestinatari(String destinatari) {
		this.destinatari = destinatari;
	}
	public String getObiettiviAltrePriorita() {
		return obiettiviAltrePriorita;
	}
	public void setObiettiviAltrePriorita(String obiettiviAltrePriorita) {
		this.obiettiviAltrePriorita = obiettiviAltrePriorita;
	}
	public String getPrincipaliContenuti() {
		return principaliContenuti;
	}
	public void setPrincipaliContenuti(String principaliContenuti) {
		this.principaliContenuti = principaliContenuti;
	}
	public String getModalitApprocciFormativiUtilizzati() {
		return modalitApprocciFormativiUtilizzati;
	}
	public void setModalitApprocciFormativiUtilizzati(String modalitApprocciFormativiUtilizzati) {
		this.modalitApprocciFormativiUtilizzati = modalitApprocciFormativiUtilizzati;
	}
	public String getPeriodoDiSvolgimento() {
		return periodoDiSvolgimento;
	}
	public void setPeriodoDiSvolgimento(String periodoDiSvolgimento) {
		this.periodoDiSvolgimento = periodoDiSvolgimento;
	}
	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}
	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}
	public String getRilevanzaPerScuola() {
		return rilevanzaPerScuola;
	}
	public void setRilevanzaPerScuola(String rilevanzaPerScuola) {
		this.rilevanzaPerScuola = rilevanzaPerScuola;
	}
	public String getCooperazioneConAltreScuole() {
		return cooperazioneConAltreScuole;
	}
	public void setCooperazioneConAltreScuole(String cooperazioneConAltreScuole) {
		this.cooperazioneConAltreScuole = cooperazioneConAltreScuole;
	}
	public String getRisorseUmaneArea() {
		return risorseUmaneArea;
	}
	public void setRisorseUmaneArea(String risorseUmaneArea) {
		this.risorseUmaneArea = risorseUmaneArea;
	}
	public String getAltreRisorseNecessarie() {
		return altreRisorseNecessarie;
	}
	public void setAltreRisorseNecessarie(String altreRisorseNecessarie) {
		this.altreRisorseNecessarie = altreRisorseNecessarie;
	}
	public String getStatoAvanzamento() {
		return statoAvanzamento;
	}
	public void setStatoAvanzamento(String statoAvanzamento) {
		this.statoAvanzamento = statoAvanzamento;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDurataDal() {
		return durataDal;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDurataDal(Date durataDal) {
		this.durataDal = durataDal;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDurataAl() {
		return durataAl;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDurataAl(Date durataAl) {
		this.durataAl = durataAl;
	}
	
	public BigDecimal getRisorseFinanziareNecessarie() {
		return risorseFinanziareNecessarie;
	}
	public void setRisorseFinanziareNecessarie(BigDecimal risorseFinanziareNecessarie) {
		this.risorseFinanziareNecessarie = risorseFinanziareNecessarie;
	}

}
