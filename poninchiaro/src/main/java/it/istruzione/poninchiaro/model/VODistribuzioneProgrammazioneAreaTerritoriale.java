package it.istruzione.poninchiaro.model;

public class VODistribuzioneProgrammazioneAreaTerritoriale {
	
	private String codiceAsse;
	private String descrizioneAsse;
	private double importoAreaPS;
	private double importoAreaMS;
	private double importoAreaTR;
	private double percentualeAsse;
	
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
	public double getImportoAreaPS() {
		return importoAreaPS;
	}
	public void setImportoAreaPS(double importoAreaPS) {
		this.importoAreaPS = importoAreaPS;
	}
	public double getImportoAreaMS() {
		return importoAreaMS;
	}
	public void setImportoAreaMS(double importoAreaMS) {
		this.importoAreaMS = importoAreaMS;
	}
	public double getImportoAreaTR() {
		return importoAreaTR;
	}
	public void setImportoAreaTR(double importoAreaTR) {
		this.importoAreaTR = importoAreaTR;
	}
	public double getPercentualeAsse() {
		return percentualeAsse;
	}
	public void setPercentualeAsse(double percentualeAsse) {
		this.percentualeAsse = percentualeAsse;
	}
	public double getImportoTotaleAsse() {
		return importoAreaPS+importoAreaMS+importoAreaTR;
	}
	
	
	

}
