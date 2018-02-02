package it.istruzione.ptof.beans;



public class ConvalidaFabbisognoSingolaScuolaFiltroDTO extends BaseDTO { 
	

	private static final long serialVersionUID = 1L;
	
	private Long progresivoDocumento;

	private String regione;
	
	private String provincia;
 
	private String comune;
	
	private String codiceMeccanografico;
	
	private String denominazione;
	
	public ConvalidaFabbisognoSingolaScuolaFiltroDTO(){};

 
	public ConvalidaFabbisognoSingolaScuolaFiltroDTO(Long progresivoDocumento, String regione, String provincia, String comune,
			String denominazione, String codiceMeccanografico) {
		super();
		this.progresivoDocumento = progresivoDocumento;
		this.regione = regione;
		this.provincia = provincia;
		this.comune = comune;
		this.denominazione = denominazione;
		this.codiceMeccanografico = codiceMeccanografico;
	}


	public Long getProgresivoDocumento() {
		return progresivoDocumento;
	}


	public void setProgresivoDocumento(Long progresivoDocumento) {
		this.progresivoDocumento = progresivoDocumento;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getComune() {
		return comune;
	}


	public void setComune(String comune) {
		this.comune = comune;
	}


	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}


	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}


	public String getDenominazione() {
		return denominazione;
	}


	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}


	public String getRegione() {
		return regione;
	}


	public void setRegione(String regione) {
		this.regione = regione;
	}




 
	
	
 
}

