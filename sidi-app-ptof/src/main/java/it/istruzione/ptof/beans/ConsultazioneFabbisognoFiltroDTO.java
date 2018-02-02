package it.istruzione.ptof.beans;

public class ConsultazioneFabbisognoFiltroDTO extends BaseDTO{
	
	
private static final long serialVersionUID = 1L;
	
	private Long progresivoDocumento;

	private String regione;
	
	private String provincia;
 
	private String comune;
	
	private String tipologiaScuola;
	
	private String codiceMeccanografico;
	
	public ConsultazioneFabbisognoFiltroDTO(){}

	public ConsultazioneFabbisognoFiltroDTO(Long progresivoDocumento, String regione, String provincia, String comune,
			String tipologiaScuola, String codiceMeccanografico) {
		super();
		this.progresivoDocumento = progresivoDocumento;
		this.regione = regione;
		this.provincia = provincia;
		this.comune = comune;
		this.tipologiaScuola = tipologiaScuola;
		this.codiceMeccanografico = codiceMeccanografico;
	}

	public Long getProgresivoDocumento() {
		return progresivoDocumento;
	}

	public void setProgresivoDocumento(Long progresivoDocumento) {
		this.progresivoDocumento = progresivoDocumento;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
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

	public String getTipologiaScuola() {
		return tipologiaScuola;
	}

	public void setTipologiaScuola(String tipologiaScuola) {
		this.tipologiaScuola = tipologiaScuola;
	}

	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}

	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	};
	
	
	

}
