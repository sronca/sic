package it.istruzione.ptof.beans;

import java.util.LinkedList;

public class CruscottoFabbisogniPostiDiPotenziamentoDTO extends BaseDTO {

	private String data;
	
	private GestioneCatalogoDTO gestioneCatalogoDTO;
	
	private LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> items;
	
	public CruscottoFabbisogniPostiDiPotenziamentoDTO() {
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

	public LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> items) {
		this.items = items;
	}
	
	public Long getTotaleDecretoPrimaria(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getDecretoPrimaria();
			}
		}
		return out;
	}

	public Long getTotaleDecretoIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getDecretoIGrado();
			}
		}
		return out;
	}

	public Long getTotaleDecretoIIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getDecretoIIGrado();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPrimaria(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getFabbisognoPrimaria();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getFabbisognoIGrado();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoIIGrado(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem item : items){
				out = out + item.getFabbisognoIIGrado();
			}
		}
		return out;
	}
	
	public Long getTotaleDecreto(){
		return this.getTotaleDecretoPrimaria() + this.getTotaleDecretoIGrado() + this.getTotaleDecretoIIGrado();
	}

	public Long getTotaleFabbisogno(){
		return this.getTotaleFabbisognoPrimaria() + this.getTotaleFabbisognoIGrado() + this.getTotaleFabbisognoIIGrado();
	}


}
