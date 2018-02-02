package it.istruzione.poninchiaro.model;

public class VODistribuzioneProgrammatoAutorizzato {
	
	private String codiceAsse;
	private String descrizioneAsse;
	private double importoProgrammato;
	private double importoAutorizzatoIst;
	private double importoAutorizzatoFor;
	
	
	public String getCodiceAsse() {
		return codiceAsse;
	}
	public void setCodiceAsse(String codiceAsse) {
		this.codiceAsse = codiceAsse;
	}
	public String getDescrizioneAsse() {
		return descrizioneAsse;
	}
	public void setDescrizioneAsse(String descrizioneAsse) {
		this.descrizioneAsse = descrizioneAsse;
	}
	public double getImportoProgrammato() {
		return importoProgrammato;
	}
	public void setImportoProgrammato(double importoProgrammato) {
		this.importoProgrammato = importoProgrammato;
	}
	public double getImportoAutorizzatoIst() {
		return importoAutorizzatoIst;
	}
	public void setImportoAutorizzatoIst(double importoAutorizzatoIst) {
		this.importoAutorizzatoIst = importoAutorizzatoIst;
	}
	public double getImportoAutorizzatoFor() {
		return importoAutorizzatoFor;
	}
	public void setImportoAutorizzatoFor(double importoAutorizzatoFor) {
		this.importoAutorizzatoFor = importoAutorizzatoFor;
	}
	public double getImportoAutorizzato() {
		return importoAutorizzatoIst + importoAutorizzatoFor;
	}


	
	
}

