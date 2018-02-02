package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

public class ComponentePlessoDTO extends  ComponenteDTO {
     
	private PlessiDTO plesso ;

	public PlessiDTO getPlesso() {
		return plesso;
	}

	public void setPlesso(PlessiDTO plesso) {
		this.plesso = plesso;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.DATI_PLESSO;
	}
	 
}
