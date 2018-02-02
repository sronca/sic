package it.istruzione.poninchiaro.model;

import it.istruzione.poninchiaro.common.util.Utility;

import java.math.BigDecimal;

public class VOIstituto extends VOBeneficiario{
	
	private String codiceMeccanografico;
	private String comune;
	
	private String email;
	private String pec;
	private String sitoweb;
	private String datAnnScoRil;
	private String codiceForte;
	private String indirizzo;
	private String telefono;
	
	private String cap;
	private String codProvincia;
	
	private String datAgg;

	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}
	public String getCodiceMeccanograficoLowerCase() {
		return codiceMeccanografico.toLowerCase();
	}
	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
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

	public String getSitoweb() {
		return sitoweb;
	}

	public void setSitoweb(String sitoweb) {
		this.sitoweb = sitoweb;
	}

	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}

	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}

	public String getCodiceForte() {
		return codiceForte;
	}

	public void setCodiceForte(String codiceForte) {
		this.codiceForte = codiceForte;
	}

	public String getIndirizzo() {
		String out = "";
		if (indirizzo != null){
			out = indirizzo.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
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

	
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getIndirizzoCompleto() {
		String out = Utility.trimValue(indirizzo) +
				(Utility.trimValue(indirizzo).equals("")?" ":", ") + Utility.trimValue(cap) +
			    " " + Utility.trimValue(comune) +
			    " (" + Utility.trimValue(codProvincia) + ")"
			   ;
		
		out = out.replaceAll("\"", "").replaceAll("'", "");
		return out;
	}
	
	public String getDatAgg() {
		return datAgg;
	}
	public void setDatAgg(String datAgg) {
		this.datAgg = datAgg;
	}
	
	

}
