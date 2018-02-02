package it.istruzione.ptof.component.report;

public class PtofIndiceDTO {

	 
	public PtofIndiceDTO(String key, String descrizione, int numeroPagina, int livello) {
		super();
		this.key = key;
		this.descrizione = descrizione;
		this.numeroPagina = numeroPagina;
		this.livello = livello;
	}
	private String key , descrizione ;
	 int numeroPagina , livello ;
	 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public int getLivello() {
		return livello;
	}
	public void setLivello(int livello) {
		this.livello = livello;
	}
}
