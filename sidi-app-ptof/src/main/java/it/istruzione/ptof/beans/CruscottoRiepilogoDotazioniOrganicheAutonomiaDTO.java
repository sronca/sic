package it.istruzione.ptof.beans;

import java.util.LinkedList;

public class CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO extends BaseDTO {

	private String data;
	
	private GestioneCatalogoDTO gestioneCatalogoDTO;
	
	private LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem> items;
	
	public CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public GestioneCatalogoDTO getGestioneCatalogoDTO() {
		return gestioneCatalogoDTO;
	}

	public void setGestioneCatalogoDTO(GestioneCatalogoDTO gestioneCatalogoDTO) {
		this.gestioneCatalogoDTO = gestioneCatalogoDTO;
	}

	public LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem> items) {
		this.items = items;
	}
	
	public Long getTotaleDecretoPostiComuni(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getDecretoPostiComuni();
			}
		}
		return out;
	}

	public Long getTotaleDecretoPostiSostegno(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getDecretoPostiSostegno();
			}
		}
		return out;
	}

	public Long getTotaleDecretoPostiPotenziamento(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getDecretoPostiPotenziamento();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPostiComuni(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getFabbisognoPostiComuni();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPostiSostegno(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getFabbisognoPostiSostegno();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPostiPotenziamento(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item : items){
				out = out + item.getFabbisognoPostiPotenziamento();
			}
		}
		return out;
	}
	
	public Long getTotaleDecreto(){
		return this.getTotaleDecretoPostiComuni() + this.getTotaleDecretoPostiSostegno() + this.getTotaleDecretoPostiPotenziamento();
	}

	public Long getTotaleFabbisogno(){
		return this.getTotaleFabbisognoPostiComuni() + this.getTotaleFabbisognoPostiSostegno() + this.getTotaleFabbisognoPostiPotenziamento();
	}


}
