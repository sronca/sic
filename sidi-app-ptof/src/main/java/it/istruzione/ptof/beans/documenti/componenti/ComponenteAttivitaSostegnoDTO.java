package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
/*
 * RF025
 */
public class ComponenteAttivitaSostegnoDTO extends  ComponenteBaseListDTO<AttivitaSostegnoDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_ATTIVITA_SOSTEGNO;
	}

	
}
