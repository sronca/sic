package it.istruzione.ptof.service;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;

public interface HomeService {
	/**
	 * use : dopo aver selezionato il contesto tipo scuola 
	 * @param cmf ( forte )
	 * @return
	 */
	IstitutoScolasticoDTO  loadIstitutoScolasticoDTO(String cmf );
	
	/**
	 * usato per la load del componente DATI_PLESSI
	 * **/
	PlessiDTO  findPlessoByCodiceMeccanografico(String cmf );
	
	IstitutoScolasticoDTO  loadIstitutoScolasticoDTO(String codiceMeccanografico, Long annoScolastico);
	
}
