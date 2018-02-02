package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class PromozioneRapportiEntiTerritorioDTO extends PtofItemsDTO {

	private String key, entiLocali, tipologiaPromozione, azioniIntraprese, areaTematicaPNSD;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEntiLocali() {
		return entiLocali;
	}

	public void setEntiLocali(String entiLocali) {
		this.entiLocali = entiLocali;
	}

	public String getTipologiaPromozione() {
		return tipologiaPromozione;
	}

	public void setTipologiaPromozione(String tipologiaPromozione) {
		this.tipologiaPromozione = tipologiaPromozione;
	}

	public String getAzioniIntraprese() {
		return azioniIntraprese;
	}

	public void setAzioniIntraprese(String azioniIntraprese) {
		this.azioniIntraprese = azioniIntraprese;
	}

	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}

	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}

}
