package it.istruzione.ptof.monitoraggio;

import it.istruzione.ptof.beans.ConsultazioneFabbisognoFiltroDTO;
import it.istruzione.ptof.beans.PageDTO;

public class RicercaConsultazionePtofDTO extends PageDTO { 
	 
	private static final long serialVersionUID = 1L;
	
	private ConsultazioneFabbisognoFiltroDTO consultazioneFabbisogno ;

	public ConsultazioneFabbisognoFiltroDTO getConsultazioneFabbisogno() {
		return consultazioneFabbisogno;
	}

	public void setConsultazioneFabbisogno(ConsultazioneFabbisognoFiltroDTO consultazioneFabbisogno) {
		this.consultazioneFabbisogno = consultazioneFabbisogno;
	}
 
}
