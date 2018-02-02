package it.istruzione.poninchiaro.model;


public class VOProgetto extends VOProgettoIstituto{
	

	private String annoAvviso;
	private String tipoIntervento;
	private int numInterventi; 
	private int numEffPartecipanti; 
	private int numAttestati;
	private String dataInizioProgetto;
	private String dataChiusuraAttivita;
	private String destinatari;
	private String codTipoFondo;
	
	
	
	
	public String getCodTipoFondo() {
		return codTipoFondo;
	}
	public void setCodTipoFondo(String codTipoFondo) {
		this.codTipoFondo = codTipoFondo;
	}
	public String getDataChiusuraAttivita() {
		return dataChiusuraAttivita;
	}
	public void setDataChiusuraAttivita(String dataChiusuraAttivita) {
		this.dataChiusuraAttivita = dataChiusuraAttivita;
	}
	public String getDestinatari() {
		return destinatari;
	}
	public void setDestinatari(String destinatari) {
		this.destinatari = destinatari;
	}
	public String getDataInizioProgetto() {
		return dataInizioProgetto;
	}
	public void setDataInizioProgetto(String dataInizioProgetto) {
		this.dataInizioProgetto = dataInizioProgetto;
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
	public int getNumInterventi() {
		return numInterventi;
	}
	public void setNumInterventi(int numInterventi) {
		this.numInterventi = numInterventi;
	}
	public String getTipoIntervento() {
		return tipoIntervento;
	}
	public void setTipoIntervento(String tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}
	public String getAnnoAvviso() {
		return annoAvviso;
	}
	public void setAnnoAvviso(String annoAvviso) {
		this.annoAvviso = annoAvviso;
	}
	

	
	
	
}
