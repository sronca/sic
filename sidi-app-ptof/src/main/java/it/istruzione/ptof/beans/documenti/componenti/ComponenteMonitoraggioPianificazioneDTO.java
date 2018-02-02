package it.istruzione.ptof.beans.documenti.componenti;
  
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

/**
 * @author mcatanzaro
 * RF039–  Pianificazione interventi e monitoraggio
 * 
 * MONITORAGGIO PIANIFICAZIONE INSERITA
 */
public class ComponenteMonitoraggioPianificazioneDTO extends  ComponenteDTO {
	/**
	 * Modalità di verifica
	 */
	private String modalitaVerifica ;
	
	/*
	 * Soluzioni adottate per il rispetto della pianificazione
	 */
	private String soluzioniAdottateRispettoPianificazione;
	
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_MONITORAGGIO_PIANIFICAZIONE;
	}

	public String getModalitaVerifica() {
		return modalitaVerifica;
	}

	public void setModalitaVerifica(String modalitaVerifica) {
		this.modalitaVerifica = modalitaVerifica;
	}

	public String getSoluzioniAdottateRispettoPianificazione() {
		return soluzioniAdottateRispettoPianificazione;
	}

	public void setSoluzioniAdottateRispettoPianificazione(String soluzioniAdottateRispettoPianificazione) {
		this.soluzioniAdottateRispettoPianificazione = soluzioniAdottateRispettoPianificazione;
	}

	
	 
}
