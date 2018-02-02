package it.istruzione.ptof.controller.ptof;



import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.FileMetaDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.controller.monitoraggio.MonitoraggioPtofTipologicheForm;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.RequestNotAutorizedException;
import it.istruzione.ptof.service.GestionePtofService;
import it.istruzione.ptof.service.TipologicheService;
 

@RestController
public class GestionePtofController {

	private static Logger logger = Logger.getLogger(GestionePtofController.class);
 
	@Autowired
	private GestionePtofService gestionePtofService;

	@Autowired
	private TipologicheService tipogicheService;

	
	@RequestMapping(value = "/gestitone-ptof/init-form.json", method = RequestMethod.POST)
	public AttribFormDTO initGestionePtofForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		AttribFormDTO attribForm = new AttribFormDTO();

		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}

		
		DocumentoAnnoIncorsoDTO documento = gestionePtofService.loadDocumentoAnnoIncorso(form.getKeyDocumento(), globalFunctionsDTO.getIstitutoScolasticoDTO() );
		if ( documento== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		
		GestionePtofDTO gestionePtofDTO = new GestionePtofDTO();
		gestionePtofDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());
		gestionePtofDTO.setDocumentoAnnoIncorsoDTO(documento);
		
		
		attribForm.add("istitutoScolastico", globalFunctionsDTO.getIstitutoScolasticoDTO());
		attribForm.add("documento", documento);
		attribForm.add("sezioni", gestionePtofService.loadSezioni(gestionePtofDTO)); 
	 
		
		return attribForm;
	}
	

	@RequestMapping(value = "/gestitone-ptof/init-sezione-form.json", method = RequestMethod.POST)
	public AttribFormDTO initGestioneSezioneForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());

		if ( globalFunctionsDTO== null|| form==null){
			// tentativo di passare una sezione del doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		SezioneExtDTO sezione = gestionePtofService.loadSezione(form.getKeySezione() , globalFunctionsDTO.getIstitutoScolasticoDTO() );

		if ( sezione== null ){
			// tentativo di passare una sezione del doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		attribForm.add("sezione", sezione );
		session.setAttribute(SESSION_CONS.FUNCTION_SEZIONE.name(), form.getKeySezione()  );
		return attribForm;
	}

	@RequestMapping(value = "/gestitone-ptof/get-titologica-sezione.json", method = RequestMethod.POST)
	public AttribFormDTO getTipologicheSezioniForm(@RequestBody GestionePtofTipologicheForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add(form.getTipologica().getTipo().name(), tipogicheService.getTipologica(form.getTipologica(), globalFunctionsDTO.getIstitutoScolasticoDTO()));
		return attribForm;
	}

	
	@RequestMapping(value = "/gestitone-ptof/set-sezione-cancella-form.json", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> cancellaSezioneForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	
 	
		gestionePtofService.deleteSezione( sezione , globalFunctionsDTO.getIstitutoScolasticoDTO() );
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gestitone-ptof/set-sezione-cancella-allegato-form.json", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> cancellaAllegatoInSezioneForm(@RequestBody String key  , BindingResult bindingResult,
			HttpSession session) {
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	
		LinkedList<ComponenteDTO> componenti  = new LinkedList<ComponenteDTO>();
	    for ( ComponenteDTO comp : sezione.getComponenti()  ){
				if ( comp.getTipoComponente().compareTo(TIPO_COMPONENTE.ALLEGATO) ==0 ){
					ComponenteAllegatoDTO allegato = (ComponenteAllegatoDTO) comp;
					 if( allegato.getKey().equalsIgnoreCase(key) ){
						 componenti.add( allegato );
					 }
				}
		}
		
		gestionePtofService.deleteComponentiInSezione( sezione , componenti , globalFunctionsDTO.getIstitutoScolasticoDTO() , TIPO_STATO_SEZIONE.BOZZA );
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/gestitone-ptof/set-sezione-form.json", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> salvaSezioneForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	
	 
		if ( sezione==null ){
			throw new RequestNotAutorizedException( );
		}
		/*******
		 * 
		 * ESCUDO IL COMPONENTI ALLEGATO CHE VIAGGIANO IN MODO AUTONOMO
		 */
		 LinkedList<ComponenteDTO> temp = new LinkedList<ComponenteDTO>();
		for ( ComponenteDTO comp :  form.getComponenti() ){
			if ( comp.getTipoComponente().compareTo(TIPO_COMPONENTE.ALLEGATO)!=0 ){
				temp.add( comp )	;
			}
			
		}
		sezione.setComponenti(temp); 
		
		gestionePtofService.saveSezione( sezione , globalFunctionsDTO.getIstitutoScolasticoDTO() , TIPO_STATO_SEZIONE.COMPILATA );
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/gestitone-ptof/set-convalida-ptof-form.json", method = RequestMethod.POST)
	public ResponseEntity setConvalidaPtofForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		ResponseDTO<Boolean> res = gestionePtofService.validaECambiaStatoPtof(form.getKeyDocumento() , globalFunctionsDTO.getIstitutoScolasticoDTO());
		DocumentoAnnoIncorsoDTO documento = gestionePtofService.loadDocumentoAnnoIncorso(form.getKeyDocumento(), globalFunctionsDTO.getIstitutoScolasticoDTO() );
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documento", documento);
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}

	@RequestMapping(value = "/gestitone-ptof/set-annulla-convalida-ptof-form.json", method = RequestMethod.POST)
	public ResponseEntity setAnnullaConvalidaPtofForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		ResponseDTO<Boolean> res = gestionePtofService.setAnnullaConvalidaPtof(form.getKeyDocumento() , globalFunctionsDTO.getIstitutoScolasticoDTO());
		DocumentoAnnoIncorsoDTO documento = gestionePtofService.loadDocumentoAnnoIncorso(form.getKeyDocumento(), globalFunctionsDTO.getIstitutoScolasticoDTO() );
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documento", documento);
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}

	@RequestMapping(value = "/gestitone-ptof/save-item-componente-form.json", method = RequestMethod.POST)
	public AttribFormDTO saveItemComponenteForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	
	 
		if ( sezione==null ){
			throw new RequestNotAutorizedException( );
		}
 		sezione.setComponenti(form.getComponenti()); 
 		// TODO VIENE PASSATO SOLO UN COMPONETE!!!!
		ComponenteDTO comp = gestionePtofService.saveItemsInComponenteInSezione( sezione , form.getComponenti().getFirst() ,globalFunctionsDTO.getIstitutoScolasticoDTO());
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("componente", comp );
		return attribForm;
	}
	
	@RequestMapping(value = "/gestitone-ptof/delete-item-componente-form.json", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> deleteItemComponenteForm(@RequestBody GestionePtofForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	
	 
		if ( sezione==null ){
			throw new RequestNotAutorizedException( );
		}
 		sezione.setComponenti(form.getComponenti()); 
 		// TODO VIENE PASSATO SOLO UN COMPONETE!!!!
		gestionePtofService.deleteItemsInComponenteInSezione( sezione , form.getComponenti().getFirst() ,globalFunctionsDTO.getIstitutoScolasticoDTO());
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/gestitone-ptof/set-sezione-01-indice-allegato.json", method = RequestMethod.POST ,    produces={"application/json"}  )
	@ResponseBody
	public ResponseEntity<LinkedList<FileMetaDTO>> setSezione01IndiceAllegato(
			MultipartHttpServletRequest request, @RequestParam String keyComponente ,
			HttpSession session ) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	

			
		LinkedList<FileMetaDTO> files = new LinkedList< FileMetaDTO>();
		FileMetaDTO fileMeta = null;
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		CommonsMultipartFile mpf = null;

		// E' POSSIBLE ALLEGARE SOLO UN FILE 
		if (itr.hasNext()) {
			mpf = (CommonsMultipartFile) request.getFile(itr.next());
 
			if (files.size() >= 10)
				files.pop();
 	
			fileMeta = new FileMetaDTO();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize());
			fileMeta.setFileType(mpf.getContentType());

			if (mpf.getSize() > 1000000) {
			//if (mpf.getSize() > 1 ) {	
			     String message = "Dimensione massima consentita 1 MB";
				 return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta,	message, keyComponente);
			}
 			
			try {
				
				/*****************************/
				// TODO SALVA IL FILE 
				// ESISTE SOLO UN COMPONENTE ALLEGATO  PER OGNI SEZIONE
				ComponenteAllegatoDTO sezioneAllegato = null;
				for ( ComponenteDTO comp :  sezione.getComponenti() ){
					if ( comp.getTipoComponente().compareTo(TIPO_COMPONENTE.ALLEGATO)==0 
							&& comp.getKey().equalsIgnoreCase(keyComponente)){
						sezioneAllegato = (ComponenteAllegatoDTO) comp	;
					}
					
				}
				if ( sezioneAllegato==null){
					logger.error("ATTENZIONE KEY NON TROVATA IN ALLEGATO : impossibile!!");
					throw new RequestNotAutorizedException( );
				}

						
				//CONTROLLO CHE IL TIPO DI FILE SIA ACCETTATO ( PDF)
				if( sezioneAllegato.getTipoFileAccettato()!=null ){
					 if ( !sezioneAllegato.getTipoFileAccettato().isValidFileName( FilenameUtils.getExtension(  mpf.getOriginalFilename() ) ) ){

						 String message = "Errore nel caricamento, il file risulta di tipologia diversa tra quelle previste ( "+ sezioneAllegato.getTipoFileAccettato().getDesc() +")" ;
						 return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta,	message, keyComponente);
						 
					 }else if ( sezioneAllegato.getTipoFileAccettato().compareTo(TIPO_FILE_ACCETTATO.ZIP)==0 
							 && !CommonsUtility.checkFileZIP(mpf)){
						 String message = "Errore nel caricamento, il file ZIP contiene file di tipologia diversa tra quelle previste ( pdf e/o jpg  )" ;
						 return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta,	message, keyComponente);
						 
					 }
				}

				
				FileDTO file = new FileDTO();
				file.setFileName(mpf.getOriginalFilename());
				file.setFile(mpf.getInputStream());
				sezioneAllegato.setFile(file);
				
				/****
				 SALVO ATOMICAMENTE IL COMPONENTE ALLEGATO 
				*****/
				
				LinkedList<ComponenteDTO> componentiAllegati = new LinkedList<ComponenteDTO>();
				componentiAllegati.add(sezioneAllegato);
				sezione.setComponenti(componentiAllegati);
				
				gestionePtofService.saveComponenteInSezione( sezione , sezioneAllegato, globalFunctionsDTO.getIstitutoScolasticoDTO()   , TIPO_STATO_SEZIONE.BOZZA );
				
                /*****************************/
				
			} catch (Exception e) {
				 logger.error(ExceptionUtils.getFullStackTrace(e));
				 String message = "Errore nel caricamento!!! file corrotto e/o non valido.";
				 return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta,	message ,keyComponente);
                
			}
			// 2.4 add to files
			files.add(fileMeta);

		} else {
			// NESSUN FILE ALLEGATO
			// CAMBIO LO STATO DELLA PAGINA
			ComponenteAllegatoDTO sezioneAllegato = (ComponenteAllegatoDTO) sezione.getComponenti().getFirst();
			sezioneAllegato.setFile(null);
			gestionePtofService.saveComponenteInSezione( sezione , sezioneAllegato , globalFunctionsDTO.getIstitutoScolasticoDTO() , TIPO_STATO_SEZIONE.BOZZA );
		}
 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);

		ResponseEntity<LinkedList<FileMetaDTO>> out = new ResponseEntity<LinkedList<FileMetaDTO>>(files, headers, HttpStatus.OK);
		return out;
	}
	
	@RequestMapping(value = "gestitone-ptof/download-allegato-sezione/{value}", method = RequestMethod.GET)
	public void downloadAllegatoInSezione(HttpServletResponse response, @PathVariable String value, @RequestParam String key , HttpSession session ) {

		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());
		String  sezioneKey = (String) session.getAttribute(SESSION_CONS.FUNCTION_SEZIONE.name());
		SezioneExtDTO sezione = gestionePtofService.loadSezione( sezioneKey , globalFunctionsDTO.getIstitutoScolasticoDTO() );	

		try {
			FileDTO file = new FileDTO();
			for ( ComponenteDTO comp : sezione.getComponenti()  ){
				if ( comp.getTipoComponente().compareTo(TIPO_COMPONENTE.ALLEGATO) ==0 && file!=null ){
					ComponenteAllegatoDTO allegato = (ComponenteAllegatoDTO) comp;
					 if( allegato.getKey().equalsIgnoreCase(key) ){
						 file = allegato.getFile();
					 }
				}
			}
		 	
 			it.istruzione.ptof.helper.CommonsUtility.setResponseFileOut(response,file.getFileName() );
			InputStream is = file.getFile() ; 
			try {
				org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e);
			} finally {
				try {
					is.close();
				} catch (Exception e) { }
			}
			
			response.flushBuffer();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream",ex );
		}
	}
	
	/***RF072***/
	@RequestMapping(value = "/gestitone-ptof/get-tipologica-regioni.json", method = RequestMethod.POST)
	public AttribFormDTO getTipologicheRegioniForm(@RequestBody MonitoraggioPtofTipologicheForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add(form.getTipologica().getTipo().name(), tipogicheService.getRegioni(  sidiUser.getCurrentContesto() ));
		return attribForm;
	}

	
	@RequestMapping(value = "/gestitone-ptof/get-tipologica-province.json", method = RequestMethod.POST)
	public AttribFormDTO getTipologicheProvinceForm(@RequestBody MonitoraggioPtofTipologicheForm form,  BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		//String codiceRegione = form.getKey() ;
		String codiceRegione = form.getValue() ;
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add(form.getTipologica().getTipo().name(), tipogicheService.getProvince(  codiceRegione  ));
		return attribForm;
	}
	
	
	@RequestMapping(value = "/gestitone-ptof/get-tipologica-comuni.json", method = RequestMethod.POST)
	public AttribFormDTO getTipologicheComuniForm(@RequestBody MonitoraggioPtofTipologicheForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		//String codiceProvincia = form.getKey() ;
		String codiceProvincia = form.getValue() ;
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add(form.getTipologica().getTipo().name(), tipogicheService.getComuni(  codiceProvincia ));
		return attribForm;
	}
	
	
	@RequestMapping(value = "/gestitone-ptof/get-tipologica-statoPtof.json", method = RequestMethod.POST)
	public AttribFormDTO geStatoPtofForm(@RequestBody MonitoraggioPtofTipologicheForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add(form.getTipologica().getTipo().name(), tipogicheService.getStatoPtof());
		return attribForm;
	}
	
	
	
}
