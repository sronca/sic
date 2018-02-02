package it.istruzione.ptof.beans;



public class FabbisognoPostiPotenziamentoPrimariaItem extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer postiPotenziamentoPrimoTriennio;
	private Integer postiPotenziamentoSecondoTriennio;
	private Integer postiPotenziamentoTerzoTriennio;
	
	private Integer postiPotenziamentoDiSostegnoPrimoTriennio;
	private Integer postiPotenziamentoDiSostegnoSecondoTriennio;
	private Integer postiPotenziamentoDiSostegnoTerzoTriennio;
	
	String motivazione;
	
	public FabbisognoPostiPotenziamentoPrimariaItem() {
		
	}

	public Integer getPostiPotenziamentoPrimoTriennio() {
		return postiPotenziamentoPrimoTriennio;
	}

	public void setPostiPotenziamentoPrimoTriennio(
			Integer postiPotenziamentoPrimoTriennio) {
		this.postiPotenziamentoPrimoTriennio = postiPotenziamentoPrimoTriennio;
	}

	public Integer getPostiPotenziamentoSecondoTriennio() {
		return postiPotenziamentoSecondoTriennio;
	}

	public void setPostiPotenziamentoSecondoTriennio(
			Integer postiPotenziamentoSecondoTriennio) {
		this.postiPotenziamentoSecondoTriennio = postiPotenziamentoSecondoTriennio;
	}

	public Integer getPostiPotenziamentoTerzoTriennio() {
		return postiPotenziamentoTerzoTriennio;
	}

	public void setPostiPotenziamentoTerzoTriennio(
			Integer postiPotenziamentoTerzoTriennio) {
		this.postiPotenziamentoTerzoTriennio = postiPotenziamentoTerzoTriennio;
	}

	public Integer getPostiPotenziamentoDiSostegnoPrimoTriennio() {
		return postiPotenziamentoDiSostegnoPrimoTriennio;
	}

	public void setPostiPotenziamentoDiSostegnoPrimoTriennio(
			Integer postiPotenziamentoDiSostegnoPrimoTriennio) {
		this.postiPotenziamentoDiSostegnoPrimoTriennio = postiPotenziamentoDiSostegnoPrimoTriennio;
	}

	public Integer getPostiPotenziamentoDiSostegnoSecondoTriennio() {
		return postiPotenziamentoDiSostegnoSecondoTriennio;
	}

	public void setPostiPotenziamentoDiSostegnoSecondoTriennio(
			Integer postiPotenziamentoDiSostegnoSecondoTriennio) {
		this.postiPotenziamentoDiSostegnoSecondoTriennio = postiPotenziamentoDiSostegnoSecondoTriennio;
	}

	public Integer getPostiPotenziamentoDiSostegnoTerzoTriennio() {
		return postiPotenziamentoDiSostegnoTerzoTriennio;
	}

	public void setPostiPotenziamentoDiSostegnoTerzoTriennio(
			Integer postiPotenziamentoDiSostegnoTerzoTriennio) {
		this.postiPotenziamentoDiSostegnoTerzoTriennio = postiPotenziamentoDiSostegnoTerzoTriennio;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
}
