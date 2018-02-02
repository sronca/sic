package it.istruzione.ptof.controller.documenti;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.helper.RequestNotAutorizedException;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.MonitoraggioService;
 
@RestController
public class GestioneDocumentiController {

	private static Logger logger = Logger.getLogger(GestioneDocumentiController.class);

	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;

	@Autowired
	private MonitoraggioService  monitoraggioService;
	
	
	@RequestMapping(value = "/gestitone-documenti/init-form.json", method = RequestMethod.POST)
	public AttribFormDTO initGestioneDocumentiForm(@RequestBody GestioneDocumentiForm action, BindingResult bindingResult,
			HttpSession session) {	
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());
		
		
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documentiAnnoIncorso", gestioneDocumentiService.loadDocumentiAnnoIncorso(gestioneDocumentiServiceDTO));
		attribForm.add("documentiArchivi", gestioneDocumentiService.loadDocumentiArchivio(gestioneDocumentiServiceDTO));
		return attribForm;
	
	}
	
 
	
	@RequestMapping(value = "gestitone-documenti/download-ptof-file-archivio/{value}", method = RequestMethod.GET)
	public void downloadPtofArchivioFile(HttpServletResponse response, @PathVariable String value, @RequestParam("tipo") String tipo , HttpSession session ) {

		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());


		try {
		 
			FileDTO file = monitoraggioService.loadPtofFile(value, TIPO_FILE_PDF.getInstanceFromCode(tipo));
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
	
	@RequestMapping(value = "/gestitone-documenti/documenti-attivabili.json", method = RequestMethod.POST)
	public AttribFormDTO getDocumentiAttivabili(@RequestBody GestioneDocumentiForm action, BindingResult bindingResult,
			HttpSession session) {	
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documentiAttivabili", gestioneDocumentiService.loadDocumentiAttivabili());
		return attribForm;
	}
							  
	@RequestMapping(value = "/gestione-documenti/catalogo-documento/{progressivoGestioneCatalogoDocumento}", method = RequestMethod.GET)
	public AttribFormDTO getGestioneCatalogoById(HttpServletResponse response, @PathVariable Long progressivoGestioneCatalogoDocumento,
												 HttpSession session) {	
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progressivoGestioneCatalogoDocumento);
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("gestioneCatalogoDTO", gestioneCatalogoDTO);
		return attribForm;
	}
	
	@RequestMapping(value = "/gestitone-documenti/documenti-attivabili-in-corso.json", method = RequestMethod.POST)
	public AttribFormDTO getDocumentiAttivabiliInCorso(@RequestBody GestioneDocumentiForm action, BindingResult bindingResult,
			HttpSession session) {	
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("documentiAttivabiliInCorso", gestioneDocumentiService.loadDocumentiAttivabiliInCorso());
		return attribForm;
	}
}
