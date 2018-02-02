package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

public class PianoNazionaleScuolaDigitaleDTO extends PtofItemsDTO{
	private String key;
	
	private String areaTematicaPNSD;
	private String numeroSezione;
	private String descrizioneSezione;
	private String contenutoLegatoPNSD;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}
	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}
	public String getNumeroSezione() {
		return numeroSezione;
	}
	public void setNumeroSezione(String numeroSezione) {
		this.numeroSezione = numeroSezione;
	}
	public String getDescrizioneSezione() {
		return descrizioneSezione;
	}
	public void setDescrizioneSezione(String descrizioneSezione) {
		this.descrizioneSezione = descrizioneSezione;
	}
	public String getContenutoLegatoPNSD() {
		return contenutoLegatoPNSD;
	}
	public void setContenutoLegatoPNSD(String contenutoLegatoPNSD) {
		this.contenutoLegatoPNSD = contenutoLegatoPNSD;
	}
}