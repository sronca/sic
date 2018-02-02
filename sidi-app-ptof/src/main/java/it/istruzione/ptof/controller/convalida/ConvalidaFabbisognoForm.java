package it.istruzione.ptof.controller.convalida;

public class ConvalidaFabbisognoForm {
	private String key;
	private Long progressivoGestioneCatalogoDocumento;
	private String regione;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getProgressivoGestioneCatalogoDocumento() {
		return progressivoGestioneCatalogoDocumento;
	}
	public void setProgressivoGestioneCatalogoDocumento(Long progressivoGestioneCatalogoDocumento) {
		this.progressivoGestioneCatalogoDocumento = progressivoGestioneCatalogoDocumento;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
}
