package it.istruzione.ptof.beans.ptof;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_TIPOLOGICHE_SEZIONE;

public class TipologicaDTO {
	
	private TIPO_TIPOLOGICHE_SEZIONE tipo;
	/**
	 * usato come paramentro di input per fitrare la lista di valori della tipologica richiesta 
	 * esempio : tendine regioni e province.
	 * per selezionare le provincie di una regione verra passato in questo campo la regine (label/value) su cui filtrare.  
	 *  
	 * 
	 */
	private ItemToFilterDTO itemToFilter;

	public TipologicaDTO() {
	}

	public TIPO_TIPOLOGICHE_SEZIONE getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_TIPOLOGICHE_SEZIONE tipo) {
		this.tipo = tipo;
	}

	public ItemToFilterDTO getItemToFilter() {
		return itemToFilter;
	}

	public void setItemToFilter(ItemToFilterDTO itemToFilter) {
		this.itemToFilter = itemToFilter;
	}

}