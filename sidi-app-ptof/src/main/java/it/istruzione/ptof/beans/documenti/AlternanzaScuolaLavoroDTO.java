package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

public class AlternanzaScuolaLavoroDTO extends PtofItemsDTO{
	private String key;
	
	String identificativoPercorso;
	String struttura;
	String azienda;
	Integer numeroAlunniPrimoAnnoTriennio;
	Integer numeroAlunniSecondoAnnoTriennio;
	Integer numeroAlunniTerzoAnnoTriennio;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIdentificativoPercorso() {
		return identificativoPercorso;
	}
	public void setIdentificativoPercorso(String identificativoPercorso) {
		this.identificativoPercorso = identificativoPercorso;
	}
	public String getStruttura() {
		return struttura;
	}
	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}
	public String getAzienda() {
		return azienda;
	}
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	public Integer getNumeroAlunniPrimoAnnoTriennio() {
		return numeroAlunniPrimoAnnoTriennio;
	}
	public void setNumeroAlunniPrimoAnnoTriennio(
			Integer numeroAlunniPrimoAnnoTriennio) {
		this.numeroAlunniPrimoAnnoTriennio = numeroAlunniPrimoAnnoTriennio;
	}
	public Integer getNumeroAlunniSecondoAnnoTriennio() {
		return numeroAlunniSecondoAnnoTriennio;
	}
	public void setNumeroAlunniSecondoAnnoTriennio(
			Integer numeroAlunniSecondoAnnoTriennio) {
		this.numeroAlunniSecondoAnnoTriennio = numeroAlunniSecondoAnnoTriennio;
	}
	public Integer getNumeroAlunniTerzoAnnoTriennio() {
		return numeroAlunniTerzoAnnoTriennio;
	}
	public void setNumeroAlunniTerzoAnnoTriennio(
			Integer numeroAlunniTerzoAnnoTriennio) {
		this.numeroAlunniTerzoAnnoTriennio = numeroAlunniTerzoAnnoTriennio;
	}
}
