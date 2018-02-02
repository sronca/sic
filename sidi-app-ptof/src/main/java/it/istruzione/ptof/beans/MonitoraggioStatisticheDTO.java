package it.istruzione.ptof.beans;

public class MonitoraggioStatisticheDTO extends BaseDTO {
	private static final long serialVersionUID = 4180359313877368369L;

	private String usr;
	private Integer numeroIstitutiPartecipanti;
	private Integer numeroIstitutiCompilati;
	private Integer numeroIstitutiConvalidati;
	private Integer numeroIstitutiInAnteprima;
	private Integer numeroIstitutiFabbisognoValidato;
	private Integer numeroIstitutiFabbisognoNonValidato;
	private Integer numeroIstitutiPubblicatiParziali;
	private Integer numeroIstitutiPubblicatiDefinitivi;
	private Integer numeroIstitutiNonRispondenti;
	
	public MonitoraggioStatisticheDTO() {
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public Integer getNumeroIstitutiPartecipanti() {
		return numeroIstitutiPartecipanti;
	}

	public void setNumeroIstitutiPartecipanti(final Integer numeroIstitutiPartecipanti) {
		this.numeroIstitutiPartecipanti = numeroIstitutiPartecipanti;
	}

	public Integer getNumeroIstitutiCompilati() {
		return numeroIstitutiCompilati;
	}

	public void setNumeroIstitutiCompilati(final Integer numeroIstitutiCompilati) {
		this.numeroIstitutiCompilati = numeroIstitutiCompilati;
	}

	public Integer getNumeroIstitutiFabbisognoValidato() {
		return numeroIstitutiFabbisognoValidato;
	}

	public void setNumeroIstitutiFabbisognoValidato(final Integer numeroIstitutiFabbisognoValidato) {
		this.numeroIstitutiFabbisognoValidato = numeroIstitutiFabbisognoValidato;
	}

	public Integer getNumeroIstitutiFabbisognoNonValidato() {
		return numeroIstitutiFabbisognoNonValidato;
	}

	public void setNumeroIstitutiFabbisognoNonValidato(final Integer numeroIstitutiFabbisognoNonValidato) {
		this.numeroIstitutiFabbisognoNonValidato = numeroIstitutiFabbisognoNonValidato;
	}

	public Integer getNumeroIstitutiPubblicatiParziali() {
		return numeroIstitutiPubblicatiParziali;
	}

	public void setNumeroIstitutiPubblicatiParziali(final Integer numeroIstitutiPubblicatiParziali) {
		this.numeroIstitutiPubblicatiParziali = numeroIstitutiPubblicatiParziali;
	}

	public Integer getNumeroIstitutiPubblicatiDefinitivi() {
		return numeroIstitutiPubblicatiDefinitivi;
	}

	public void setNumeroIstitutiPubblicatiDefinitivi(final Integer numeroIstitutiPubblicatiDefinitivi) {
		this.numeroIstitutiPubblicatiDefinitivi = numeroIstitutiPubblicatiDefinitivi;
	}

	public Integer getNumeroIstitutiNonRispondenti() {
		return numeroIstitutiNonRispondenti;
	}

	public void setNumeroIstitutiNonRispondenti(final Integer numeroIstitutiNonRispondenti) {
		this.numeroIstitutiNonRispondenti = numeroIstitutiNonRispondenti;
	}

	public Integer getNumeroIstitutiConvalidati() {
		return numeroIstitutiConvalidati;
	}

	public void setNumeroIstitutiConvalidati(Integer numeroIstitutiConvalidati) {
		this.numeroIstitutiConvalidati = numeroIstitutiConvalidati;
	}

	public Integer getNumeroIstitutiInAnteprima() {
		return numeroIstitutiInAnteprima;
	}

	public void setNumeroIstitutiInAnteprima(Integer numeroIstitutiInAnteprima) {
		this.numeroIstitutiInAnteprima = numeroIstitutiInAnteprima;
	};
	
	
}
