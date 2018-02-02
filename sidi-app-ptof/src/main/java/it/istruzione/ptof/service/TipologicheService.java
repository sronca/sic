package it.istruzione.ptof.service;

import java.util.LinkedList;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ptof.TipologicaDTO;


public interface TipologicheService { 
	
	/**
	 * Restituisce la tipologica richiesta 
	 * @param istitutoScolastico TODO
	 * @param form
	 * @return
	 */
	LinkedList<BeanValueDTO>  getTipologica(TipologicaDTO tipologica, IstitutoScolasticoDTO istitutoScolastico);
	
	/**
	 * Restituisce l'elenco delle regioni filtrate per il contesto di input.
	 * Se input = null restituisce l'elenco di tutte le regioni
	 * @param SidiContesto contesto selezionato in fase di login
	 * @return
	 */
	LinkedList<BeanValueDTO>  getRegioni(SidiContesto contesto);
	
	/**
	 * Restituisce l'elenco delle province filtrate per il codice regione di input.
	 * @param codiceRegione
	 * @return
	 */
	LinkedList<BeanValueDTO>  getProvince(String codiceRegione);
	
	/**
	 * Restituisce l'elenco dei comuni filtrati per il codice provincia di input.
	 * @param codiceProvincia
	 * @return
	 */
	LinkedList<BeanValueDTO>  getComuni(String codiceProvincia);
	
	/**
	 * Restituisce l'elenco degli stati del PTOF contenente i due stati : PUBBLICATO_PARZIALEMENTE, PUBBLICATO_COMPLETO
	 * @param 
	 * @return
	 */
	LinkedList<BeanValueDTO> getStatoPtof() ;
	
	/**
	 * Restituisce l'elenco degli stati del PTOF contenente i due stati : PUBBLICATO_PARZIALEMENTE, PUBBLICATO_COMPLETO
	 * @param 
	 * @return
	 */
	LinkedList<BeanValueDTO> getTipologiaScuolaPtof() ;
	
}
