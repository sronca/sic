package it.istruzione.ptof.service;

import it.istruzione.ptof.beans.DatiDecretoDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAttivabileDTO;
import it.istruzione.ptof.beans.documenti.DocumentoPubblicazioneDTO;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.miur.eds.gestioneutenze.shared.dto.RichiestaDTO;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcatanzaro
 * RF002– Gestione Documenti
 */
public interface GestioneDocumentiService {

	
	
	
	
		 /**
		 * load dei documenti da pubblicare 
		 * use : funzione pubblicazione
	     * @param gestioneDocumentiServiceDTO
	     * @return
	     * 
	     */
	    LinkedList<DocumentoPubblicazioneDTO> loadDocumentiPubblicazione(IstitutoScolasticoDTO istitutoScolasticoDTO);

	
	    
	    /**
		 * @param keyPtof : chiave del documento selezionato tra i doc trovati con il metodo loadDocumentiPubblicazione
		 * @param gestioneDocumentiServiceDTO
		 * @return
		 * use: scaricare il ptof nell lista dei pubblicati
		 * 
		 */
		FileDTO loadFilePtofPubblicato(String keyPtof, IstitutoScolasticoDTO istitutoScolasticoDTO);
		
	
	    /**
		 * @param keyPtof : chiave del documento selezionato tra i doc trovati con il metodo loadDocumentiPubblicazione
		 * @param gestioneDocumentiServiceDTO
		 * @return
		 * use: imposta lo stato del doc in attesa di anteprima/pubblicazione 
		 *   
		 */
		ResponseDTO<Boolean> richiestaPubblicazionePtof(String keyPtof, IstitutoScolasticoDTO istitutoScolasticoDTO);
		
	    
	
	
	 	 /**
	     * @param gestioneDocumentiServiceDTO
	     * @return
	     * 
	     */
	    LinkedList<DocumentoAnnoIncorsoDTO> loadDocumentiAnnoIncorso(GestioneDocumentiDTO gestioneDocumentiServiceDTO);
	    /**
	     * @param gestioneDocumentiServiceDTO
	     * @return
	     * 
	     * 
	     */
	    LinkedList<DocumentoArchivioDTO> loadDocumentiArchivio(GestioneDocumentiDTO gestioneDocumentiServiceDTO);
 	    
	    
		/**
		 * @param keyPtof : chiave del documento selezionato tra i doc trovati con il metodo loadDocumentiArchivio
		 * @param gestioneDocumentiServiceDTO
		 * @param tipoFile tipo file che occorre scarica ( parziale o completo )
		 * @return
		 * use: scaricare il ptof nell'archivio  RF002 – Gestisci Documento 
		 * 
		 */
		//FileDTO loadPtofFile(String keyPtof, GestioneDocumentiDTO gestioneDocumentiServiceDTO, TIPO_FILE_PDF tipoFile);
		
		/**
	     * @return
	     * Lista dei documenti attivabili
	     */
	    LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabili();
	    
	    LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabiliInCorso();
	    
	    /**
	     * @return
	     * Gestione CatalogoDocumento
	     */
	    GestioneCatalogoDTO getTbs1001GestionecatalogodocById(Long prgGesCatDoc);
	    
	    
	    /**
	     * passare la key del doc e l'istituto dentro il dto  DocumentoAnnoIncorsoDTO
	     * @return
	     */
	    LinkedList<GestionePtofDTO>    getListaPtofGeneraPDF () ;
	    
	    
	    /**
	     * salva il pdf del ptof 
	     * @param tof
	     * @param ptof
	     */
	    void setPtofFile(GestionePtofDTO tof,  ByteArrayOutputStream ptof );
	    
	    ResponseDTO<Boolean> salvaCatalogoDocumento(GestioneCatalogoDTO gestioneCatalogoDTO);
	    
	    /**
	     * @return
	     * Gestione CatalogoDocumento
	     */
	    GestioneCatalogoDTO generaNuovoCatalogo();
	    
	    LinkedList<DatiDecretoDTO> getDatiDecreto(GestioneCatalogoDTO gestioneCatalogoDTO);
	    
	    void importDatiDecreto(List<DatiDecretoDTO> items, GestioneCatalogoDTO gestioneCatalogoDTO);
	    
}
