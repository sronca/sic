package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;

public class ComponenteObbiettiviPrioritariAltriDTO extends  ComponenteBaseListDTO<ObbiettiviFormativiDTO>   {
  
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI_ALTRI;
	}

	
}
