package it.istruzione.ptof.beans;



public class FabbisognoPostiSostegnoIIGradoItem extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer postiSostegnoPrimoTriennio;
	private Integer postiSostegnoSecondoTriennio;
	private Integer postiSostegnoTerzoTriennio;

	private String motivazione;

	public FabbisognoPostiSostegnoIIGradoItem() {

	}

	public Integer getPostiSostegnoPrimoTriennio() {
		return postiSostegnoPrimoTriennio;
	}

	public void setPostiSostegnoPrimoTriennio(Integer postiSostegnoPrimoTriennio) {
		this.postiSostegnoPrimoTriennio = postiSostegnoPrimoTriennio;
	}

	public Integer getPostiSostegnoSecondoTriennio() {
		return postiSostegnoSecondoTriennio;
	}

	public void setPostiSostegnoSecondoTriennio(Integer postiSostegnoSecondoTriennio) {
		this.postiSostegnoSecondoTriennio = postiSostegnoSecondoTriennio;
	}

	public Integer getPostiSostegnoTerzoTriennio() {
		return postiSostegnoTerzoTriennio;
	}

	public void setPostiSostegnoTerzoTriennio(Integer postiSostegnoTerzoTriennio) {
		this.postiSostegnoTerzoTriennio = postiSostegnoTerzoTriennio;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}




	
}
