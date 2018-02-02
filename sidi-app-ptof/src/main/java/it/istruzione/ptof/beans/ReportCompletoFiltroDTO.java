package it.istruzione.ptof.beans;

/**
 * @author dc
 *
 */
public class ReportCompletoFiltroDTO extends BaseDTO { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long progresivoDocumento;

	private String regione;
	
	private String provincia;
 
	private String comune;
	
	private String statoPTOF;
	
	private String codiceMeccanografico;
	
	public ReportCompletoFiltroDTO(){};

 
	public ReportCompletoFiltroDTO(Long progresivoDocumento, String regione, String provincia, String comune,
			String statoPTOF, String codiceMeccanografico) {
		super();
		this.progresivoDocumento = progresivoDocumento;
		this.regione = regione;
		this.provincia = provincia;
		this.comune = comune;
		this.statoPTOF = statoPTOF;
		this.codiceMeccanografico = codiceMeccanografico;
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

	public String getStatoPTOF() {
		return statoPTOF;
	}

	public void setStatoPTOF(String statoPTOF) {
		this.statoPTOF = statoPTOF;
	}

	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}

	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}

	public Long getProgresivoDocumento() {
		return progresivoDocumento;
	}

	public void setProgresivoDocumento(Long progresivoDocumento) {
		this.progresivoDocumento = progresivoDocumento;
	}
 
	
	
 
}
