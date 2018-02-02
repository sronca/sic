package it.istruzione.ptof.service;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO;
import it.istruzione.ptof.beans.FabbisognoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniDTO;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.MonitoraggioStatisticheDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.monitoraggio.RicercaConsultazionePtofDTO;
import it.istruzione.ptof.monitoraggio.RicercaReportDTO;

import java.util.LinkedList;

public interface MonitoraggioService { 
	
	/**
	 * Restituisce Lista Reporto Statisctiche
	 * @param sidiUser
	 * @param gestioneCatalogoDTO
	 * @return
	 */
	LinkedList<MonitoraggioStatisticheDTO>  getStatistiche(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	/**
	 * @param keyPtof : chiave del documento
	 * @param tipoFileDaScaricare : VISUALIZZAZIONE_DOCUMENTO_CONVALIDATO, PUBBLICAZIONE_PARZIALE, PUBBLICAZIONE_COMPLETA

	 * @return FileDTO
	 * use: scaricare il documento ptof relativo al tipoFileDaScaricare richiesto in input
	 * 
	 */
	FileDTO loadPtofFile(String keyPtof, TIPO_FILE_PDF tipoFileDaScaricare);
	
	 
	/**
	 *  RF-070  Report COmpleto
	 *  use:
	 *  invocato dal tasto ricerca funzione di gestione Report completo per inizializzare la lista dei doc attivabili
	 *  e succevamente per il sort e la paginazione
	 *  Occorre ricercare tutte i documenti in: ---
		caso iniziale :
		paginaCorrente e sort = null  -> ordinamento di default
		
		numero di riche per pagina 20 
		
		i filtri sono : Regione,Provincia,Comune,Stto Ptof, Codice Meccanografico
		
		
     * @param ReportCompletoFiltroDTO
	 * @param PageDTO  
	 * @return
	 */
	ResponsePageDTO<ReportCompletoDTO> getReportCompleto(RicercaReportDTO form);
	
	
	/**
	 * RF073 -  Tabella A, B, C, D - Cruscotto Fabbisogno Posti Comuni
	 * @param sidiContesto
	 * @param gestioneCatalogoDTO
	 * @return
	 */
	CruscottoFabbisogniPostiComuniDTO  getCruscottoFabbisogniPostiComuni(String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	/**
	 * RF073 -  Tabella A, B, C, D - Cruscotto Fabbisogno Posti Comuni
	 * @param sidiContesto
	 * @param gestioneCatalogoDTO
	 * @return
	 */
	CruscottoFabbisogniPostiSostegnoDTO  getCruscottoFabbisogniPostiSostegno(String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	/**
	 * RF073 -  Tabella A, B, C, D - Cruscotto Fabbisogno Posti Comuni
	 * @param sidiContesto
	 * @param gestioneCatalogoDTO
	 * @return
	 */
	CruscottoFabbisogniPostiDiPotenziamentoDTO  getCruscottoFabbisogniPostiPotenziamento(String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	/**
	 * RF073 -  Tabella A, B, C, D - Cruscotto Fabbisogno Posti Comuni
	 * @param sidiContesto
	 * @param gestioneCatalogoDTO
	 * @return
	 */
	CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO  getCruscottoFabbisogniTotaleOrganica(String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO);
	
	
	/**
	 *  RF-070  Consultazione Puntuale PTOF
	 *  use:
	 *  i filtri sono : Regione,Provincia,Comune,Tipologia Scuola, Codice Meccanografico
		
		
     * @param ReportCompletoFiltroDTO
	 * @param PageDTO  
	 * @return
	 */
	ResponsePageDTO<FabbisognoDTO> getConsultazionFabbisogno(RicercaConsultazionePtofDTO form);
	
	
	 FabbisognoPostiComuniDTO  getFabbisognoPostiComuni(String key);
	
	 FabbisognoPostiSostegnoDTO  getPostiSostegno(String key);
	
	 FabbisognoPostiPotenziamentoDTO  getPostiPotenziamento(String key);
	 
	 IstitutoScolasticoDTO getScuolaByKeyDocumento(String key);
	
}
