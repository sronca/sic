package it.istruzione.ptof.beans;

import java.util.LinkedList;

public class CruscottoFabbisogniPostiSostegnoDTO extends BaseDTO {

	private String data;
	
	private GestioneCatalogoDTO gestioneCatalogoDTO;
	
	private LinkedList<CruscottoFabbisogniPostiSostegnoItem> items;
	
	public CruscottoFabbisogniPostiSostegnoDTO() {
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

	public LinkedList<CruscottoFabbisogniPostiSostegnoItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<CruscottoFabbisogniPostiSostegnoItem> items) {
		this.items = items;
	}
	
	public Long getTotaleDecretoOrganicoDiritto(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiSostegnoItem item : items){
				out = out + item.getDecretoOrganicoDiritto();
			}
		}
		return out;
	}

	public Long getTotaleDecretoPotenziamentoPerSostegno(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiSostegnoItem item : items){
				out = out + item.getDecretoPotenziamentoPerSostegno();
			}
		}
		return out;
	}


	public Long getTotaleFabbisognoSostegno(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiSostegnoItem item : items){
				out = out + item.getFabbisognoSostegno();
			}
		}
		return out;
	}

	public Long getTotaleFabbisognoPotenziamentoPerSostegno(){
		Long out = 0L;
		if (this.items != null && !this.items.isEmpty()){
			for (CruscottoFabbisogniPostiSostegnoItem item : items){
				out = out + item.getFabbisognoPotenziamentoPerSostegno();
			}
		}
		return out;
	}


	
	public Long getTotaleDecreto(){
		return this.getTotaleDecretoOrganicoDiritto() + this.getTotaleDecretoPotenziamentoPerSostegno();
	}

	public Long getTotaleFabbisogno(){
		return this.getTotaleFabbisognoSostegno() + this.getTotaleFabbisognoPotenziamentoPerSostegno();
	}


}
