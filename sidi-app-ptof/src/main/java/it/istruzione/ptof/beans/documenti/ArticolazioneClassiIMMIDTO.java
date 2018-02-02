package it.istruzione.ptof.beans.documenti;

import java.math.BigDecimal;
import java.util.LinkedList;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class ArticolazioneClassiIMMIDTO extends PtofItemsDTO {

	private String key;
	
	private BigDecimal tempoNormaleTotaliClassiI, tempoNormaleTotaliClassiII, tempoNormaleTotaliClassiIII;
	private BigDecimal tempoProlungatoTotaliClassiI, tempoProlungatoTotaliClassiII, tempoProlungatoTotaliClassiIII;

	private LinkedList<String> datiLingua;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public BigDecimal getTempoNormaleTotaliClassiI() {
		return tempoNormaleTotaliClassiI;
	}

	public void setTempoNormaleTotaliClassiI(BigDecimal tempoNormaleTotaliClassiI) {
		this.tempoNormaleTotaliClassiI = tempoNormaleTotaliClassiI;
	}

	public BigDecimal getTempoNormaleTotaliClassiII() {
		return tempoNormaleTotaliClassiII;
	}

	public void setTempoNormaleTotaliClassiII(BigDecimal tempoNormaleTotaliClassiII) {
		this.tempoNormaleTotaliClassiII = tempoNormaleTotaliClassiII;
	}

	public BigDecimal getTempoNormaleTotaliClassiIII() {
		return tempoNormaleTotaliClassiIII;
	}

	public void setTempoNormaleTotaliClassiIII(BigDecimal tempoNormaleTotaliClassiIII) {
		this.tempoNormaleTotaliClassiIII = tempoNormaleTotaliClassiIII;
	}

	public BigDecimal getTempoProlungatoTotaliClassiI() {
		return tempoProlungatoTotaliClassiI;
	}

	public void setTempoProlungatoTotaliClassiI(BigDecimal tempoProlungatoTotaliClassiI) {
		this.tempoProlungatoTotaliClassiI = tempoProlungatoTotaliClassiI;
	}

	public BigDecimal getTempoProlungatoTotaliClassiII() {
		return tempoProlungatoTotaliClassiII;
	}

	public void setTempoProlungatoTotaliClassiII(BigDecimal tempoProlungatoTotaliClassiII) {
		this.tempoProlungatoTotaliClassiII = tempoProlungatoTotaliClassiII;
	}

	public BigDecimal getTempoProlungatoTotaliClassiIII() {
		return tempoProlungatoTotaliClassiIII;
	}

	public void setTempoProlungatoTotaliClassiIII(BigDecimal tempoProlungatoTotaliClassiIII) {
		this.tempoProlungatoTotaliClassiIII = tempoProlungatoTotaliClassiIII;
	}

	public LinkedList<String> getDatiLingua() {
		return datiLingua;
	}

	public void setDatiLingua(LinkedList<String> datiLingua) {
		this.datiLingua = datiLingua;
	}

}
