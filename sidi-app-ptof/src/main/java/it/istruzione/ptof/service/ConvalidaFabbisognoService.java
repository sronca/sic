package it.istruzione.ptof.service;

import java.util.List;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.convalida.RicercaConvalidaSingolaDTO;

public interface ConvalidaFabbisognoService { 
	
	/**
	 *  RF080 - Cruscotto Convalida Fabbisogno
	 *  use:
	 *  consentire di ricercare un Istituto Principale della regione di appartenenza dell'utente USR
	 *  e di visualizzarne la lista degli Istituti Principali che hanno Pubblicato Parzialmente il PTOF
	 *  e per i quali la sezione del Fabbisogno non è stata VALIDATA (STATO del Documento  PUBBLICATO PARZIALMENTE)
		
		i filtri sono : Regione,Provincia,Comune, Codice Meccanografico, denominazione scuola (in like)
		
     * @param ReportCompletoFiltroDTO
	 * @param PageDTO  
	 * @return
	 */
	ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO> getElencoScuolePerConvalidazioneSingola(RicercaConvalidaSingolaDTO pagefilter);
	
	CruscottoFabbisogniPostiComuniDTO getFabbisogniPostiComuni(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	CruscottoFabbisogniPostiSostegnoDTO getFabbisogniPostiSostegno(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	CruscottoFabbisogniPostiDiPotenziamentoDTO getFabbisogniPostiPotenziamento(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	ResponseDTO<Boolean> convalidaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto);
	
	ResponseDTO<Boolean> rettificaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto);
	
	ResponseDTO<Boolean> convalidaSingola(String keyDocumentoPtof);
	
	ResponseDTO<Boolean> rettificaSingola(String keyDocumentoPtof);
	

	
}
