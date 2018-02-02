package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;

/**
 * @author mcatanzaro
 *  RF043– Fabbisogno di posti di sostegno 
 *  Nome label	Tipo campo	Modificabile	Note
    Anno Scolastico	Yyyy/yy	NO	Devono essere prospettati tutti e tre gli anni scolastici che compongono il triennio
    Posti di Sostegno Udito	Numero	SI	
    Posti di Sostegno Vista	Numero	SI	
    Posti di Sostegno Psicofisico	Numero	SI	
    Motivazione	Testo	SI	
 */
public class FabbisognoPostiSostegnoEEAADTO extends PtofItemsDTO implements PtofTableItemActionFilterDTO {

	private String key;
 	
	private String motivazione; 
	
	private Integer postiSostegnoUditoPrimoTriennio;
	private Integer postiSostegnoUditoSecondoTriennio;
	private Integer postiSostegnoUditoTerzoTriennio;
	
	private Integer postiSostegnoVistaPrimoTriennio;
	private Integer postiSostegnoVistaSecondoTriennio;
	private Integer postiSostegnoVistaTerzoTriennio;
	
	private Integer postiSostegnoPsicofisicoPrimoTriennio;
	private Integer postiSostegnoPsicofisicoSecondoTriennio;
	private Integer postiSostegnoPsicofisicoTerzoTriennio;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	public Integer getPostiSostegnoUditoPrimoTriennio() {
		return postiSostegnoUditoPrimoTriennio;
	}
	public void setPostiSostegnoUditoPrimoTriennio(
			Integer postiSostegnoUditoPrimoTriennio) {
		this.postiSostegnoUditoPrimoTriennio = postiSostegnoUditoPrimoTriennio;
	}
	public Integer getPostiSostegnoUditoSecondoTriennio() {
		return postiSostegnoUditoSecondoTriennio;
	}
	public void setPostiSostegnoUditoSecondoTriennio(
			Integer postiSostegnoUditoSecondoTriennio) {
		this.postiSostegnoUditoSecondoTriennio = postiSostegnoUditoSecondoTriennio;
	}
	public Integer getPostiSostegnoUditoTerzoTriennio() {
		return postiSostegnoUditoTerzoTriennio;
	}
	public void setPostiSostegnoUditoTerzoTriennio(
			Integer postiSostegnoUditoTerzoTriennio) {
		this.postiSostegnoUditoTerzoTriennio = postiSostegnoUditoTerzoTriennio;
	}
	public Integer getPostiSostegnoVistaPrimoTriennio() {
		return postiSostegnoVistaPrimoTriennio;
	}
	public void setPostiSostegnoVistaPrimoTriennio(
			Integer postiSostegnoVistaPrimoTriennio) {
		this.postiSostegnoVistaPrimoTriennio = postiSostegnoVistaPrimoTriennio;
	}
	public Integer getPostiSostegnoVistaSecondoTriennio() {
		return postiSostegnoVistaSecondoTriennio;
	}
	public void setPostiSostegnoVistaSecondoTriennio(
			Integer postiSostegnoVistaSecondoTriennio) {
		this.postiSostegnoVistaSecondoTriennio = postiSostegnoVistaSecondoTriennio;
	}
	public Integer getPostiSostegnoVistaTerzoTriennio() {
		return postiSostegnoVistaTerzoTriennio;
	}
	public void setPostiSostegnoVistaTerzoTriennio(
			Integer postiSostegnoVistaTerzoTriennio) {
		this.postiSostegnoVistaTerzoTriennio = postiSostegnoVistaTerzoTriennio;
	}
	public Integer getPostiSostegnoPsicofisicoPrimoTriennio() {
		return postiSostegnoPsicofisicoPrimoTriennio;
	}
	public void setPostiSostegnoPsicofisicoPrimoTriennio(
			Integer postiSostegnoPsicofisicoPrimoTriennio) {
		this.postiSostegnoPsicofisicoPrimoTriennio = postiSostegnoPsicofisicoPrimoTriennio;
	}
	public Integer getPostiSostegnoPsicofisicoSecondoTriennio() {
		return postiSostegnoPsicofisicoSecondoTriennio;
	}
	public void setPostiSostegnoPsicofisicoSecondoTriennio(
			Integer postiSostegnoPsicofisicoSecondoTriennio) {
		this.postiSostegnoPsicofisicoSecondoTriennio = postiSostegnoPsicofisicoSecondoTriennio;
	}
	public Integer getPostiSostegnoPsicofisicoTerzoTriennio() {
		return postiSostegnoPsicofisicoTerzoTriennio;
	}
	public void setPostiSostegnoPsicofisicoTerzoTriennio(
			Integer postiSostegnoPsicofisicoTerzoTriennio) {
		this.postiSostegnoPsicofisicoTerzoTriennio = postiSostegnoPsicofisicoTerzoTriennio;
	}
 	

	
	
}
