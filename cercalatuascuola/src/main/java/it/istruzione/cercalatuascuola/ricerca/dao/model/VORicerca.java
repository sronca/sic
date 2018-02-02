package it.istruzione.cercalatuascuola.ricerca.dao.model;

import java.util.ArrayList;
import java.util.List;

public class VORicerca
{

	private String tipoRicerca = "";
	
	/** RAPIDA **/
	private String rapida = "";
	
	/** INTORNO A ME **/
	private String indirizzoRiferimento = "";
	private String raggio = "";
	private String codiceOrdine = "";
	private String checkStatale = "";
	private String checkNonStatale = "";
	private String radioTipoScuola = "";
	private String codiceTipologiaStataleNuovoOrdinamento = "";
	private String codiceTipologiaNonStatale = "";
	private String codiceSettoreScuola = "";
	private String radioBiennioTriennio = "";
	private String codiceIndirizzo = "";
	private String codiceCFPPercorso = "";
	private String codiceCFPSettore = "";
	private String codiceCFPIndirizzo = "";
	private String codiceTempoPrimaria = "";
	private String codiceTempoSecondaria1Grado = "";
	private String checkIndirizzoMusicale = "";
	
	/** AVANZATA **/
	private String codiceRegione = "";
	private String codiceProvincia = "";
	private String codiceComune = "";
	private String denominazione = "";
	private String codMecc = "";
	
	private String coordinateIndirizzoRiferimento = "";
	private String coordinateIndirizzoDiretto = "";
	
	private String latIndirizzoRiferimento = "";
	private String lngIndirizzoRiferimento = "";
	
	private String gidf = "";	
	
	
	public String getLatIndirizzoRiferimento() {
		return latIndirizzoRiferimento;
	}
	public void setLatIndirizzoRiferimento(String latIndirizzoRiferimento) {
		this.latIndirizzoRiferimento = latIndirizzoRiferimento;
	}
	public String getLngIndirizzoRiferimento() {
		return lngIndirizzoRiferimento;
	}
	public void setLngIndirizzoRiferimento(String lngIndirizzoRiferimento) {
		this.lngIndirizzoRiferimento = lngIndirizzoRiferimento;
	}
	public String getTipoRicerca() {
		return tipoRicerca;
	}
	public void setTipoRicerca(String tipoRicerca) {
		this.tipoRicerca = tipoRicerca;
	}
	public String getRapida() {
		return rapida;
	}
	public void setRapida(String rapida) {
		this.rapida = rapida;
	}
	public String getIndirizzoRiferimento() {
		return indirizzoRiferimento;
	}
	public void setIndirizzoRiferimento(String indirizzoRiferimento) {
		this.indirizzoRiferimento = indirizzoRiferimento;
	}
	public String getRaggio() {
		return raggio;
	}
	public void setRaggio(String raggio) {
		this.raggio = raggio;
	}
	public String getCodiceOrdine() {
		return codiceOrdine;
	}
	public void setCodiceOrdine(String codiceOrdine) {
		this.codiceOrdine = codiceOrdine;
	}
	public String getCheckStatale() {
		return checkStatale;
	}
	public void setCheckStatale(String checkStatale) {
		this.checkStatale = checkStatale;
	}
	public String getCheckNonStatale() {
		return checkNonStatale;
	}
	public void setCheckNonStatale(String checkNonStatale) {
		this.checkNonStatale = checkNonStatale;
	}
	public String getRadioTipoScuola() {
		return radioTipoScuola;
	}
	public void setRadioTipoScuola(String radioTipoScuola) {
		this.radioTipoScuola = radioTipoScuola;
	}
	public String getCodiceTipologiaStataleNuovoOrdinamento() {
		return codiceTipologiaStataleNuovoOrdinamento;
	}
	public void setCodiceTipologiaStataleNuovoOrdinamento(
			String codiceTipologiaStataleNuovoOrdinamento) {
		this.codiceTipologiaStataleNuovoOrdinamento = codiceTipologiaStataleNuovoOrdinamento;
	}
	public String getCodiceTipologiaNonStatale() {
		return codiceTipologiaNonStatale;
	}
	public void setCodiceTipologiaNonStatale(String codiceTipologiaNonStatale) {
		this.codiceTipologiaNonStatale = codiceTipologiaNonStatale;
	}
	public String getCodiceSettoreScuola() {
		return codiceSettoreScuola;
	}
	public void setCodiceSettoreScuola(String codiceSettoreScuola) {
		this.codiceSettoreScuola = codiceSettoreScuola;
	}
	public String getRadioBiennioTriennio() {
		return radioBiennioTriennio;
	}
	public void setRadioBiennioTriennio(String radioBiennioTriennio) {
		this.radioBiennioTriennio = radioBiennioTriennio;
	}
	public String getCodiceIndirizzo() {
		return codiceIndirizzo;
	}
	public void setCodiceIndirizzo(String codiceIndirizzo) {
		this.codiceIndirizzo = codiceIndirizzo;
	}
	public String getCodiceCFPPercorso() {
		return codiceCFPPercorso;
	}
	public void setCodiceCFPPercorso(String codiceCFPPercorso) {
		this.codiceCFPPercorso = codiceCFPPercorso;
	}
	public String getCodiceCFPSettore() {
		return codiceCFPSettore;
	}
	public void setCodiceCFPSettore(String codiceCFPSettore) {
		this.codiceCFPSettore = codiceCFPSettore;
	}
	public String getCodiceCFPIndirizzo() {
		return codiceCFPIndirizzo;
	}
	public void setCodiceCFPIndirizzo(String codiceCFPIndirizzo) {
		this.codiceCFPIndirizzo = codiceCFPIndirizzo;
	}
	public String getCodiceTempoPrimaria() {
		return codiceTempoPrimaria;
	}
	public void setCodiceTempoPrimaria(String codiceTempoPrimaria) {
		this.codiceTempoPrimaria = codiceTempoPrimaria;
	}
	public String getCodiceTempoSecondaria1Grado() {
		return codiceTempoSecondaria1Grado;
	}
	public void setCodiceTempoSecondaria1Grado(String codiceTempoSecondaria1Grado) {
		this.codiceTempoSecondaria1Grado = codiceTempoSecondaria1Grado;
	}
	public String getCheckIndirizzoMusicale() {
		return checkIndirizzoMusicale;
	}
	public void setCheckIndirizzoMusicale(String checkIndirizzoMusicale) {
		this.checkIndirizzoMusicale = checkIndirizzoMusicale;
	}
	public String getCodiceRegione() {
		return codiceRegione;
	}
	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}
	public String getCodiceProvincia() {
		return codiceProvincia;
	}
	public void setCodiceProvincia(String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}
	public String getCodiceComune() {
		return codiceComune;
	}
	public void setCodiceComune(String codiceComune) {
		this.codiceComune = codiceComune;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getCodMecc() {
		return codMecc;
	}
	public void setCodMecc(String codMecc) {
		this.codMecc = codMecc;
	}
	public String getCoordinateIndirizzoRiferimento() {
		return coordinateIndirizzoRiferimento;
	}
	public void setCoordinateIndirizzoRiferimento(
			String coordinateIndirizzoRiferimento) {
		this.coordinateIndirizzoRiferimento = coordinateIndirizzoRiferimento;
	}
	public String getCoordinateIndirizzoDiretto() {
		return coordinateIndirizzoDiretto;
	}
	public void setCoordinateIndirizzoDiretto(String coordinateIndirizzoDiretto) {
		this.coordinateIndirizzoDiretto = coordinateIndirizzoDiretto;
	}
	public String getGidf() {
		return gidf;
	}
	public void setGidf(String gidf) {
		this.gidf = gidf;
	}
		

	

}
