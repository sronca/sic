package it.istruzione.ptof.service;

import java.util.LinkedList;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.SezioneBaseDTO;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;

/**
 * @author mcatanzaro
 * servizi per RF003– Gestione PTOF
 */
public interface GestionePtofService {
 	    
 
	    DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento);
	    
	    LinkedList<SezioneDTO> loadSezioni(GestionePtofDTO gestionePtofDTO);
	    
	    /**
	     * usato per elevare il livello di sicurezza 
	     * verifica che il documento appartena all' scuola passata in input e  
	     * in caso positivo ritorna il doc altrimenti null 
	     * @param keyDocumento
	     * @param istitutoScolasticoDTO
	     * @return
	     */
	    DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento,IstitutoScolasticoDTO istitutoScolasticoDTO);
	    
	    /**
	     * usato per caricare i componenti grafici della sezione 
	     * @param keySezione
	     * @param istitutoScolasticoDTO
	     * @return
	     */
	    SezioneExtDTO loadSezione(String keySezione , IstitutoScolasticoDTO istitutoScolasticoDTO );

		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 *  salva  i componenti della sezione e non cambia lo stato della sezione
		 */
		void saveSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO);

		/**
		 * @deprecated
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * cancella i componenti delle sezione e mette lo stato della sezione in bozza
		 */
		void deleteComponentiInSezione(SezioneBaseDTO sezione, LinkedList<ComponenteDTO> componeti , IstitutoScolasticoDTO istitutoScolasticoDTO);

		
		/**
		 * @param sezione
		 * @param componenti
		 * @param istitutoScolasticoDTO
		 * cancella gli items del componente ( passati in input ) delle sezione e imposta lo stato della sezione in bozza
		 * use 
		 *  caso 1 : nel componponte obbiettivi verra la passa la lista dei componeti da cancellare 
		 */
		void deleteItemsInComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componete , IstitutoScolasticoDTO istitutoScolasticoDTO);

		/**
		 * @param sezione
		 * @param componenti
		 * @param istitutoScolasticoDTO
		 * vengono salvati/modificati gli items del componente ( passati in input ) e ritornati in out ( con la chiave se new ) 
		 * lo stato della sezione viene impostato a bozza
		 * 
		 * ATTENZIONE : gli items con la key sono da modificare altrimenti sono nuovi da inserire
		 * use 
		 *  caso 1 : nel componponte obbiettivi verra la passa la lista dei componeti da SALVARE 
		 * @return 
		 */
		ComponenteDTO saveItemsInComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componete , IstitutoScolasticoDTO istitutoScolasticoDTO);

		
		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * salva il singolo componente delle sezione e cambia lo stato della sezione
		 * es: usato con allegato
		 */
		ComponenteDTO saveComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componente , IstitutoScolasticoDTO istitutoScolasticoDTO , TIPO_STATO_SEZIONE statoSezione);

		
		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * cancella solo i componenti delle sezione e cambia lo stato della sezione
		 * es: usato per cancella allegato
		 */
		void deleteComponentiInSezione(SezioneBaseDTO sezione, LinkedList<ComponenteDTO> componeti , IstitutoScolasticoDTO istitutoScolasticoDTO , TIPO_STATO_SEZIONE statoSezione);

		
		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * cancella tutti i componenti delle sezione a imposta lo stato a BOZZA
		 */
		void deleteSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO);

		
		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * @param statoSezione
		 * salva  i componenti della sezione e cambia lo stato in statoSezione (input)
		 */
		void saveSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO, TIPO_STATO_SEZIONE statoSezione);

		
		/**
		 * @param sezione
		 * @param istitutoScolasticoDTO
		 * @param statoSezione
		 * Controlla 
		 *	1.	Che  tutte le sezioni/sottosezioni obbligatorie sono state compilate.
		 *	2.	Che le date: “Data ratifica atto di indirizzo Dirigente Scolastico”, “Data predisposizione PTOF Collegio dei Docenti”, “Data approvazione Consiglio d’Istituto” siano state inserite“ (Vedi RF051– Dati Finali Scuola)
		 *  
		 *  nel caso in cui i controlli non siano superati:
		 *   - ritorna un oggetto ResponseDTO con validationErrorDTO !=null con il result == false
		 *    
		 *  nel caso in cui i controlli siano superati:
		 *   - ritorna un oggetto ResponseDTO con validationErrorDTO == null e il result == true
		 *   - cambia lo stato del ptof in CONVALIDATO
		 */
		ResponseDTO<Boolean> validaECambiaStatoPtof(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO );
		
		
		/**
		 * @param keyDocumento
		 * @param istitutoScolasticoDTO
		 *  A.	se lo stato del doc e' DOCUMENTO_IN_ANTEPRIMA or CONVALIDATO :
		 *  -   modifica il campo di STATO del documento  in  NON CONVALIDATO
         *  -   abilita in sola lettura  la sezione 14 - RF049–  Piano Nazionale della Scuola Digitale
         *  -   abiliterà l’inserimento, la modifica e la cancellazione della Fabbisogni   
         *  B. se lo stato del doc e' FABBISOGNO_NON_VALIDATO : 
		 *      - il campo di STATO del documento sara' impostato a PUBBLICATO_PARZIALMENTE     
		 *		- la data invio USR a null .
         *      - disalilita la modifica e la cancellazione della sezione Fabbisogni  
		 * @return
		 *  se non sono presenti errori di validazione ritorna un oggetto ResponseDTO con validationErrorDTO == null e il result == true
		 */
		ResponseDTO<Boolean> setAnnullaConvalidaPtof(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO );

		
}
