package it.istruzione.ptof.beans;



public class FabbisognoPostiPotenziamentoDiSostegnoItem extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer postiPotenziamentoPrimoTriennio;
	private Integer postiPotenziamentoSecondoTriennio;
	private Integer postiPotenziamentoTerzoTriennio;
	
	String motivazione;

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

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public FabbisognoPostiPotenziamentoDiSostegnoItem() {
		
	}

	
 
}
