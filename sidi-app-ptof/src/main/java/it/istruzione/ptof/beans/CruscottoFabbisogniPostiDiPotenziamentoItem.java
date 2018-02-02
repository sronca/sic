package it.istruzione.ptof.beans;

public class CruscottoFabbisogniPostiDiPotenziamentoItem extends BaseDTO {

	private String regione;
	
	private Long decretoPrimaria;
	private Long decretoIGrado;
	private Long decretoIIGrado;
	
	private Long fabbisognoPrimaria;
	private Long fabbisognoIGrado;
	private Long fabbisognoIIGrado;

	public CruscottoFabbisogniPostiDiPotenziamentoItem() {
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public Long getDecretoPrimaria() {
		return decretoPrimaria;
	}

	public void setDecretoPrimaria(Long decretoPrimaria) {
		this.decretoPrimaria = decretoPrimaria;
	}

	public Long getDecretoIGrado() {
		return decretoIGrado;
	}

	public void setDecretoIGrado(Long decretoIGrado) {
		this.decretoIGrado = decretoIGrado;
	}

	public Long getDecretoIIGrado() {
		return decretoIIGrado;
	}

	public void setDecretoIIGrado(Long decretoIIGrado) {
		this.decretoIIGrado = decretoIIGrado;
	}

	public Long getFabbisognoPrimaria() {
		return fabbisognoPrimaria;
	}

	public void setFabbisognoPrimaria(Long fabbisognoPrimaria) {
		this.fabbisognoPrimaria = fabbisognoPrimaria;
	}

	public Long getFabbisognoIGrado() {
		return fabbisognoIGrado;
	}

	public void setFabbisognoIGrado(Long fabbisognoIGrado) {
		this.fabbisognoIGrado = fabbisognoIGrado;
	}

	public Long getFabbisognoIIGrado() {
		return fabbisognoIIGrado;
	}

	public void setFabbisognoIIGrado(Long fabbisognoIIGrado) {
		this.fabbisognoIIGrado = fabbisognoIIGrado;
	}
	
	


}
