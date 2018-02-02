package it.istruzione.ptof.beans.documenti;

import java.math.BigDecimal;
import java.util.LinkedList;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class ArticolazioneClassiMMIIDTO extends PtofItemsDTO {
	private String key;
	private String indirizzo;
	private BigDecimal classiI, classiII, classiIII, classiIV, classiV, classiVI;

	private LinkedList<String> datiLingua;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public BigDecimal getClassiI() {
		return classiI;
	}

	public void setClassiI(BigDecimal classiI) {
		this.classiI = classiI;
	}

	public BigDecimal getClassiII() {
		return classiII;
	}

	public void setClassiII(BigDecimal classiII) {
		this.classiII = classiII;
	}

	public BigDecimal getClassiIII() {
		return classiIII;
	}

	public void setClassiIII(BigDecimal classiIII) {
		this.classiIII = classiIII;
	}

	public BigDecimal getClassiIV() {
		return classiIV;
	}

	public void setClassiIV(BigDecimal classiIV) {
		this.classiIV = classiIV;
	}

	public BigDecimal getClassiV() {
		return classiV;
	}

	public void setClassiV(BigDecimal classiV) {
		this.classiV = classiV;
	}

	public BigDecimal getClassiVI() {
		return classiVI;
	}

	public void setClassiVI(BigDecimal classiVI) {
		this.classiVI = classiVI;
	}

	public LinkedList<String> getDatiLingua() {
		return datiLingua;
	}

	public void setDatiLingua(LinkedList<String> datiLingua) {
		this.datiLingua = datiLingua;
	}

}
