package it.istruzione.ptof.beans;

public class CruscottoFabbisogniPostiSostegnoItem extends BaseDTO {

	private String regione;
	
	private Long decretoOrganicoDiritto;
	private Long decretoPotenziamentoPerSostegno;
	
	private Long fabbisognoSostegno;
	private Long fabbisognoPotenziamentoPerSostegno;

	public CruscottoFabbisogniPostiSostegnoItem() {
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public Long getDecretoOrganicoDiritto() {
		return decretoOrganicoDiritto;
	}

	public void setDecretoOrganicoDiritto(Long decretoOrganicoDiritto) {
		this.decretoOrganicoDiritto = decretoOrganicoDiritto;
	}

	public Long getDecretoPotenziamentoPerSostegno() {
		return decretoPotenziamentoPerSostegno;
	}

	public void setDecretoPotenziamentoPerSostegno(
			Long decretoPotenziamentoPerSostegno) {
		this.decretoPotenziamentoPerSostegno = decretoPotenziamentoPerSostegno;
	}

	public Long getFabbisognoSostegno() {
		return fabbisognoSostegno;
	}

	public void setFabbisognoSostegno(Long fabbisognoSostegno) {
		this.fabbisognoSostegno = fabbisognoSostegno;
	}

	public Long getFabbisognoPotenziamentoPerSostegno() {
		return fabbisognoPotenziamentoPerSostegno;
	}

	public void setFabbisognoPotenziamentoPerSostegno(
			Long fabbisognoPotenziamentoPerSostegno) {
		this.fabbisognoPotenziamentoPerSostegno = fabbisognoPotenziamentoPerSostegno;
	}


	
	


}
