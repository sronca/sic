package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

public class ComponenteIstitutoPrincipaleDTO extends  ComponenteDTO {
     
	/**
	 * lista istituti principali
	 * 
	 * in realta penso che che ne sia sempre uno
	 */
	private IstitutoScolasticoDTO istitutoScolasticoDTO ;

	public IstitutoScolasticoDTO getIstitutoScolasticoDTO() {
		return istitutoScolasticoDTO;
	}

	public void setIstitutoScolasticoDTO(IstitutoScolasticoDTO istitutoScolasticoDTO) {
		this.istitutoScolasticoDTO = istitutoScolasticoDTO;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.DATI_ISTITUTO_PRINCIPALE;
	}

	
	 
}
