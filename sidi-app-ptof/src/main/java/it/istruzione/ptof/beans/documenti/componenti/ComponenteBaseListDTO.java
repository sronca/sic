package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.helper.CommonsUtility;


abstract public class ComponenteBaseListDTO<E extends PtofItemsDTO> extends ComponenteDTO {

	private LinkedList<E> items ;

	private boolean itemsAggiungibili= true;
	
	public LinkedList<E> getItems() {
		
		if( tipoSezione!=null )
		if (!((	   tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_11_PROGETTICV) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC) == 0
				|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS) == 0
				
				)
				&& (statoDocumento.compareTo(TIPO_STATO_DOC.IN_COMPILAZIONE) == 0))
				){
			for( E i: items ){
				i.setVisualizzabile(true);
				i.setModificabile(CommonsUtility.sezioneIsModificabile(tipoSezione, statoDocumento));
				//i.setCancellabile(CommonsUtility.sezioneIsCancellabile(tipoSezione, statoDocumento, statoSezione));
				if (	   tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE) == 0
						|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO) == 0
						|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO) == 0
						|| tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI) == 0){
					i.setCancellabile(false);
				}else{
					i.setCancellabile(CommonsUtility.sezioneIsModificabile(tipoSezione, statoDocumento));
				}
			}
			itemsAggiungibili=CommonsUtility.sezioneIsModificabile(tipoSezione, statoDocumento);
		}
		return items;
	}

	public void setItems(LinkedList<E> items) {
		this.items = items;
	}

	public boolean isItemsAggiungibili() {
		return itemsAggiungibili;
	}

	public void setItemsAggiungibili(boolean itemsAggiungibili) {
		this.itemsAggiungibili = itemsAggiungibili;
	}

	
}
