package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOSuccursale
{
	
	private String codiceMeccanografico = ""; // COD_SCU_UT
	private String annoRiferimento = "";      // DAT_ANN_SCO_RIF
	private String desAnnScoRil = "";			
	private String progressivo = "";          // PRG_SCU_SUC
	
	private String indirizzo = "";      	 //DES_IND_SUC
	private String latitudine = "";     	 //NUM_LAT_SUC
	private String longitudine = "";    	 //NUM_LON_SUC


	private String tipologiaScuola = "";
	private String codiceSettore = "";
	private String percorsoFormativo = "";

	private String flagScuolaStatale = "";
	private String flagScuolaParitaria = "";
	
	
	private String denominazione = ""; //DES_DEN_SUC
	private String telefono = "";      //COD_TEL_SUC
	private String fax = "";           //COD_FAX_SUC
	private String email = "";         //DES_IND_EMA 


	private String utente = "";

	
	//campi composti:
	private String descrizioneTipologiaScuola = "";
	
	
	public String getDescrizioneTipologiaScuola() {
		return descrizioneTipologiaScuola;
	}

	public void setDescrizioneTipologiaScuola(String descrizioneTipologiaScuola) {
		this.descrizioneTipologiaScuola = descrizioneTipologiaScuola;
	}

	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}

	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}

	public String getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(String annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(String progressivo) {
		this.progressivo = progressivo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	public String getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}

	public String getTipologiaScuola() {
		return tipologiaScuola;
	}

	public void setTipologiaScuola(String tipologiaScuola) {
		this.tipologiaScuola = tipologiaScuola;
	}

	public String getCodiceSettore() {
		return codiceSettore;
	}

	public void setCodiceSettore(String codiceSettore) {
		this.codiceSettore = codiceSettore;
	}

	public String getPercorsoFormativo() {
		return percorsoFormativo;
	}

	public void setPercorsoFormativo(String percorsoFormativo) {
		this.percorsoFormativo = percorsoFormativo;
	}

	public String getFlagScuolaStatale() {
		return flagScuolaStatale;
	}

	public void setFlagScuolaStatale(String flagScuolaStatale) {
		this.flagScuolaStatale = flagScuolaStatale;
	}

	public String getFlagScuolaParitaria() {
		return flagScuolaParitaria;
	}

	public void setFlagScuolaParitaria(String flagScuolaParitaria) {
		this.flagScuolaParitaria = flagScuolaParitaria;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public void setDesAnnScoRil(String desAnnScoRil) {
		this.desAnnScoRil = desAnnScoRil;
	}
	
	public String getDesAnnScoRil() {
		return desAnnScoRil;
	}		
}