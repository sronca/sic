package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.ConvPotenzFormativoDTO;
/*
 Sottosezione della Sezione 10 - RF038– Collaborazioni con enti locali e territorio  
 */
public class ComponenteConvPotenzFormativoDTO extends  ComponenteBaseListDTO<ConvPotenzFormativoDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO;
	}

	
}
