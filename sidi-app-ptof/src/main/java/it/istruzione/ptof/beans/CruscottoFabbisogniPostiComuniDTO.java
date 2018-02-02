package it.istruzione.ptof.beans;

import java.util.LinkedList;

public class CruscottoFabbisogniPostiComuniDTO extends BaseDTO {

	private String data;
	
	private GestioneCatalogoDTO gestioneCatalogoDTO;
	
	private LinkedList<CruscottoFabbisogniPostiComuniItem> items;
	
	public CruscottoFabbisogniPostiComuniDTO() {
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

	public LinkedList<CruscottoFabbisogniPostiComuniItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<CruscottoFabbisogniPostiComuniItem> items) {
		this.items = items;
	}
	
	public Long getTotaleDecretoInfanzia(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getDecretoInfanzia();
			}
		}
		return out;
	}

	public Long getTotaleDecretoPrimaria(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getDecretoPrimaria();
			}
		}
		return out;
	}

	public Long getTotaleDecretoIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getDecretoIGrado();
			}
		}
		return out;
	}

	public Long getTotaleDecretoIIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getDecretoIIGrado();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoInfanzia(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getFabbisognoInfanzia();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPrimaria(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getFabbisognoPrimaria();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getFabbisognoIGrado();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoIIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiComuniItem item : items){
				out = out + item.getFabbisognoIIGrado();
			}
		}
		return out;
	}
	
	public Long getTotaleDecreto(){
		return this.getTotaleDecretoInfanzia() + this.getTotaleDecretoPrimaria() + this.getTotaleDecretoIGrado() + this.getTotaleDecretoIIGrado();
	}

	public Long getTotaleFabbisogno(){
		return this.getTotaleFabbisognoInfanzia() + this.getTotaleFabbisognoPrimaria() + this.getTotaleFabbisognoIGrado() + this.getTotaleFabbisognoIIGrado();
	}


}
