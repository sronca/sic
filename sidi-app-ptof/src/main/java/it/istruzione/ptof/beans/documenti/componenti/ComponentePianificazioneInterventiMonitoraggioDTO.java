package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.PianificazioneInterventiMonitoraggioDTO;
/*
   RF039–  Pianificazione interventi e monitoraggio 
   PIANIFICAZIONE ATTIVITA’
 */
public class ComponentePianificazioneInterventiMonitoraggioDTO extends  ComponenteBaseListDTO<PianificazioneInterventiMonitoraggioDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_PIANIFICAZIONE_ATTIVITA;
	}

	
}
