package it.istruzione.poninchiaro.model;

import java.math.BigDecimal;

public class VOProgettoIstituto {
	
	private int prgProgetto;
	private String codiceProgetto;
	private String titolo;
	private String azione;
	private String codiceAzione;
	private String sottoazione;
	private String codiceSottoazione;
	private String stato;
	private BigDecimal importoAutorizzato;
	private BigDecimal importoErogato;
	private String dataProtocollo;
	private String numeroProtocollo;
	private String tipoFinanziamento;
	private String annoAvviso;
	private String tipoIntervento;
	
	
	
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
	public String getTipoFinanziamento() {
		return tipoFinanziamento;
	}
	public void setTipoFinanziamento(String tipoFinanziamento) {
		this.tipoFinanziamento = tipoFinanziamento;
	}
	public String getCodiceProgetto() {
		return codiceProgetto;
	}
	public void setCodiceProgetto(String codiceProgetto) {
		this.codiceProgetto = codiceProgetto;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getSottoazione() {
		return sottoazione;
	}
	public void setSottoazione(String sottoazione) {
		this.sottoazione = sottoazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public BigDecimal getImportoAutorizzato() {
		return importoAutorizzato;
	}
	public void setImportoAutorizzato(BigDecimal importoAutorizzato) {
		this.importoAutorizzato = importoAutorizzato;
	}
	public BigDecimal getImportoErogato() {
		return importoErogato;
	}
	public void setImportoErogato(BigDecimal importoErogato) {
		this.importoErogato = importoErogato;
	}
	public String getCodiceAzione() {
		return codiceAzione;
	}
	public void setCodiceAzione(String codiceAzione) {
		this.codiceAzione = codiceAzione;
	}
	public String getCodiceSottoazione() {
		return codiceSottoazione;
	}
	public void setCodiceSottoazione(String codiceSottoazione) {
		this.codiceSottoazione = codiceSottoazione;
	}
	public String getDataProtocollo() {
		return dataProtocollo;
	}
	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	public int getPrgProgetto() {
		return prgProgetto;
	}
	public void setPrgProgetto(int prgProgetto) {
		this.prgProgetto = prgProgetto;
	}

	
	
	
}
