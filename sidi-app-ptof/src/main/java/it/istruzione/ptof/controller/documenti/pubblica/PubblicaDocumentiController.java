package it.istruzione.ptof.controller.documenti.pubblica;

import java.io.IOException;
import java.io.InputStream;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.controller.documenti.GestioneDocumentiForm;
import it.istruzione.ptof.helper.RequestNotAutorizedException;
import it.istruzione.ptof.service.GestioneDocumentiService;
 
@RestController
public class PubblicaDocumentiController {

	private static Logger logger = Logger.getLogger(PubblicaDocumentiController.class);

	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;

	@RequestMapping(value = "/pubblicazione-ptof/init-form.json", method = RequestMethod.POST)
	public AttribFormDTO initPubblicaDocumentiForm(@RequestBody PubblicaDocumentiForm action, BindingResult bindingResult,
			HttpSession session) {	
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documenti", gestioneDocumentiService.loadDocumentiPubblicazione(globalFunctionsDTO.getIstitutoScolasticoDTO()));
		return attribForm;
	}
	
	@RequestMapping(value = "pubblicazione-ptof/download-doc/{value}", method = RequestMethod.GET)
	public void downloadDocumenti(HttpServletResponse response, @PathVariable String value, @RequestParam String key , HttpSession session ) {

		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());

		try {
		 
			FileDTO file = gestioneDocumentiService.loadFilePtofPubblicato(value,  globalFunctionsDTO.getIstitutoScolasticoDTO() );
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
	
	
	@RequestMapping(value = "/pubblicazione-ptof/pubblica.json", method = RequestMethod.POST)
	public AttribFormDTO richiestaPubblicazionePtof(@RequestBody String keyDoc, BindingResult bindingResult,
			HttpSession session) {	
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		gestioneDocumentiService.richiestaPubblicazionePtof(keyDoc, globalFunctionsDTO.getIstitutoScolasticoDTO());	
		AttribFormDTO attribForm = new AttribFormDTO();
		return attribForm;
	}

}
