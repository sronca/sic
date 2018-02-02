package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.PromozioneRapportiEntiTerritorioDTO;
/*
 Sottosezione della Sezione 8 - RF032–  Programmazione Della Formazione Al Personale Docente  
 */
public class ComponentePromozioneRapportiEntiTerritorioDTO extends  ComponenteBaseListDTO<PromozioneRapportiEntiTerritorioDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO;
	}

	
}
