package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOLinkIscrizioniOnline {
	
	private String urlIscrizioniOnline;
	private boolean provenienzaPortale;
	private boolean visLinkIscr;
	
	public String getUrlIscrizioniOnline() {
		return urlIscrizioniOnline;
	}
	public void setUrlIscrizioniOnline(String urlIscrizioniOnline) {
		this.urlIscrizioniOnline = urlIscrizioniOnline;
	}
	public boolean isProvenienzaPortale() {
		return provenienzaPortale;
	}
	public void setProvenienzaPortale(boolean provenienzaPortale) {
		this.provenienzaPortale = provenienzaPortale;
	}
	public boolean isVisLinkIscr() {
		return visLinkIscr;
	}
	public void setVisLinkIscr(boolean visLinkIscr) {
		this.visLinkIscr = visLinkIscr;
	}	
	

}
