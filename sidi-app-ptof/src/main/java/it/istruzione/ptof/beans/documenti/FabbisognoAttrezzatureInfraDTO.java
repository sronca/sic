package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.PtofItemsDTO;

import java.math.BigDecimal;

public class FabbisognoAttrezzatureInfraDTO extends PtofItemsDTO {

	private String key;
	private String tipologia, descrizione, areaTematicaPNSD, fonteFinanziamento;
	private BigDecimal numeroPezzi, stimaCosti;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

	public String getFonteFinanziamento() {
		return fonteFinanziamento;
	}

	public void setFonteFinanziamento(String fonteFinanziamento) {
		this.fonteFinanziamento = fonteFinanziamento;
	}

	public BigDecimal getNumeroPezzi() {
		return numeroPezzi;
	}

	public void setNumeroPezzi(BigDecimal numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}

	public BigDecimal getStimaCosti() {
		return stimaCosti;
	}

	public void setStimaCosti(BigDecimal stimaCosti) {
		this.stimaCosti = stimaCosti;
	}

}
