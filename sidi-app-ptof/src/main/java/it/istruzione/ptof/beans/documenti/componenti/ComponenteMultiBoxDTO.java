package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

public class ComponenteMultiBoxDTO extends  ComponenteDTO   {
  
	private LinkedList<ComponenteComboBoxDTO> comboBox ;

	public LinkedList<ComponenteComboBoxDTO> getComboBox() {
		return comboBox;
	}

	public void setComboBox(LinkedList<ComponenteComboBoxDTO> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.MULTI_BOX;
	}
 	
	
}
