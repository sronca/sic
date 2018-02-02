package it.istruzione.ptof.beans.documenti;

import java.math.BigDecimal;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class DotazioneIstPriDTO extends PtofItemsDTO {
	
	private String key, dotazioniMultimediali,areaTematicaPNSD;
	private BigDecimal numero;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDotazioniMultimediali() {
		return dotazioniMultimediali;
	}

	public void setDotazioniMultimediali(String dotazioniMultimediali) {
		this.dotazioniMultimediali = dotazioniMultimediali;
	}

	 

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

	public BigDecimal getNumero() {
		return numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

}
