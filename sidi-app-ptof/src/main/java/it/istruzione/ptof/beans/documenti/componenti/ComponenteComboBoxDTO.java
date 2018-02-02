package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

public class ComponenteComboBoxDTO extends  ComponenteDTO   {
  
	/**
	 * chiave/valore selezionato nella lista degli itemSelezionabili 
	 * inizialmente !null e con label e value = scringa vuota
	 */
	private BeanValueDTO selected ;
	/**
	 * lista si opzioni possibili 
	 */
	
	private LinkedList<BeanValueDTO> itemSelezionabili ;

	 

	 

	public LinkedList<BeanValueDTO> getItemSelezionabili() {
		return itemSelezionabili;
	}

	public void setItemSelezionabili(LinkedList<BeanValueDTO> itemSelezionabili) {
		this.itemSelezionabili = itemSelezionabili;
	}

	public BeanValueDTO getSelected() {
		return selected;
	}

	public void setSelected(BeanValueDTO selected) {
		this.selected = selected;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.COMBO_BOX;
	}

	 
	
	
}
