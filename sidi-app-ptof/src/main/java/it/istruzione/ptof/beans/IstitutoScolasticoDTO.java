package it.istruzione.ptof.beans;

import java.util.List;

 



/**
 * @author mcatanzaro
 *
 */
public class IstitutoScolasticoDTO extends BaseDTO { 
	
	
	/**
	 * Denominazione delle scuole del Gruppo (Primarie, Infanzia, Secondaria I grado, Secondaria II grado)
	 */
	private String tipologieScuoleAssociate;
	
	/**
	 * Nome del Dirigente scolastico
	 */
	private String dirigenteScolastico;
	/**
	 * Indirizzo della scuola
	 */
	private String indirizzo ;
	/**
	 * 
	 */
	private String telefono , fax , email , pec , sitoWeb ;
	
	private Integer numeroPlessi;

	/**
	 *  formato :
	 *  (es. Infanzia 4 – Primaria 3)
	 */
	private String numeroPlessiPerTipologiaScuola;
	
	
	/**
	 * chiave univoca dell'istituto principale ( alfa numerico )
	 */
	private String key;
	
	/**
	 * codice mec ist princiaple UTENTE ( mostrato a video!!!)
	 */
	private String codiceMecIstPrin;
	/**
	 * descrizione della tipologia scuola 
	 */
	private String tipologiaScuola;
	
	
	/**
	 * Nome dell'Istituto Principale
	 */
	private String denominazione;
	/**
	 * descrizione del comune in cui si trova la scuola 
	 */
	String comune;
	
	List<PlessiDTO> plessi;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCodiceMecIstPrin() {
		return codiceMecIstPrin;
	}
	public void setCodiceMecIstPrin(String codiceMecIstPrin) {
		this.codiceMecIstPrin = codiceMecIstPrin;
	}
	public String getTipologiaScuola() {
		return tipologiaScuola;
	}
	public void setTipologiaScuola(String tipologiaScuola) {
		this.tipologiaScuola = tipologiaScuola;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getTipologieScuoleAssociate() {
		return tipologieScuoleAssociate;
	}
	public void setTipologieScuoleAssociate(String tipologieScuoleAssociate) {
		this.tipologieScuoleAssociate = tipologieScuoleAssociate;
	}
	public String getDirigenteScolastico() {
		return dirigenteScolastico;
	}
	public void setDirigenteScolastico(String dirigenteScolastico) {
		this.dirigenteScolastico = dirigenteScolastico;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}
	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}
	public Integer getNumeroPlessi() {
		return numeroPlessi;
	}
	public void setNumeroPlessi(Integer numeroPlessi) {
		this.numeroPlessi = numeroPlessi;
	}
	public String getNumeroPlessiPerTipologiaScuola() {
		return numeroPlessiPerTipologiaScuola;
	}
	public void setNumeroPlessiPerTipologiaScuola(String numeroPlessiPerTipologiaScuola) {
		this.numeroPlessiPerTipologiaScuola = numeroPlessiPerTipologiaScuola;
	}
	public List<PlessiDTO> getPlessi() {
		return plessi;
	}
	public void setPlessi(List<PlessiDTO> plessi) {
		this.plessi = plessi;
	}
	
	
	
}
