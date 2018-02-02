package it.istruzione.poninchiaro.model;


public class VOModuloRichiesta {
	

	private String titoloRichiesta;
	private String descRichiesta;
	private int numOre; 
	private int numPartecipanti;
	private int numEffPartecipanti;
	private int numAttestati;
	private String dataInizio;
	private String dataFine;
	
	
	
	public String getTitoloRichiesta() {
		return titoloRichiesta;
	}
	public void setTitoloRichiesta(String titoloRichiesta) {
		this.titoloRichiesta = titoloRichiesta;
	}
	public String getDescRichiesta() {
		return descRichiesta;
	}
	public void setDescRichiesta(String descRichiesta) {
		this.descRichiesta = descRichiesta;
	}
	public int getNumOre() {
		return numOre;
	}
	public void setNumOre(int numOre) {
		this.numOre = numOre;
	}
	public int getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	public int getNumEffPartecipanti() {
		return numEffPartecipanti;
	}
	public void setNumEffPartecipanti(int numEffPartecipanti) {
		this.numEffPartecipanti = numEffPartecipanti;
	}
	public int getNumAttestati() {
		return numAttestati;
	}
	public void setNumAttestati(int numAttestati) {
		this.numAttestati = numAttestati;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	
}
