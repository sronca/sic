package it.istruzione.ptof.beans;

public class PlessiDTO extends BaseDTO {
	
	 
	/**
	 * codiceMecUtente, comune , denominazione sono usati nei req rf19 in poi 
	 */
	private String codiceMecUtente, comune , denominazione , email, indirizzo, key, telefono , plessoScuola;

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPlessoScuola() {
		return plessoScuola;
	}

	public void setPlessoScuola(String plessoScuola) {
		this.plessoScuola = plessoScuola;
	}

	public String getCodiceMecUtente() {
		return codiceMecUtente;
	}

	public void setCodiceMecUtente(String codiceMecUtente) {
		this.codiceMecUtente = codiceMecUtente;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	 
}
