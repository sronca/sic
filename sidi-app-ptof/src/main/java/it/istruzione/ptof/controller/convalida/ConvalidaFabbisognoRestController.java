package it.istruzione.ptof.controller.convalida;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.PageDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.beans.convalida.RicercaConvalidaSingolaDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.controller.ptof.GestionePtofForm;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.RequestNotAutorizedException;
import it.istruzione.ptof.monitoraggio.RicercaReportDTO;
import it.istruzione.ptof.service.ConvalidaFabbisognoService;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.MonitoraggioService;

@RestController
public class ConvalidaFabbisognoRestController {

	private static Logger logger = Logger.getLogger(ConvalidaFabbisognoRestController.class);
 
	@Autowired
	private MonitoraggioService monitoraggioService;
	
	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;
	
	@Autowired
	private ConvalidaFabbisognoService convalidaFabbisognoService;

	@RequestMapping(value = "/gestione-convalida-fabbisogno/convalida.json", method = RequestMethod.POST)
	public AttribFormDTO fabbisognoScuolaPostiComuni(@RequestBody ConvalidaFabbisognoForm form, HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(form.getProgressivoGestioneCatalogoDocumento());
		
		CruscottoFabbisogniPostiComuniDTO cruscottoFabbisogniPostiComuniDTO = convalidaFabbisognoService.getFabbisogniPostiComuni(sidiUser.getCurrentContesto(), gestioneCatalogoDTO);
		CruscottoFabbisogniPostiSostegnoDTO cruscottoFabbisogniPostiSostegnoDTO = convalidaFabbisognoService.getFabbisogniPostiSostegno(sidiUser.getCurrentContesto(),gestioneCatalogoDTO);
		CruscottoFabbisogniPostiDiPotenziamentoDTO cruscottoFabbisogniPostiDiPotenziamentoDTO = convalidaFabbisognoService.getFabbisogniPostiPotenziamento(sidiUser.getCurrentContesto(),gestioneCatalogoDTO);

		
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("regione", sidiUser.getCurrentContesto().getDescrizione());
		attribForm.add("cruscottoFabbisogniPostiComuniDTO", cruscottoFabbisogniPostiComuniDTO);
		attribForm.add("cruscottoFabbisogniPostiSostegnoDTO", cruscottoFabbisogniPostiSostegnoDTO);
		attribForm.add("cruscottoFabbisogniPostiDiPotenziamentoDTO", cruscottoFabbisogniPostiDiPotenziamentoDTO);
		return attribForm;
	}
	
	@RequestMapping(value = "/gestione-convalida-fabbisogno/set-m-convalida-massiva.json", method = RequestMethod.POST)
	public ResponseEntity<?> convalidaMassiva(@RequestBody ConvalidaFabbisognoForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(form.getProgressivoGestioneCatalogoDocumento());
		
		ResponseDTO<Boolean> res = convalidaFabbisognoService.convalidaMassiva(gestioneCatalogoDTO, sidiUser.getCurrentContesto());
		
		AttribFormDTO attribForm = new AttribFormDTO();
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}
	
	@RequestMapping(value = "/gestione-convalida-fabbisogno/set-m-rettifica-massiva.json", method = RequestMethod.POST)
	public ResponseEntity<?> rettificaMassiva(@RequestBody ConvalidaFabbisognoForm form, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(form.getProgressivoGestioneCatalogoDocumento());
		
		ResponseDTO<Boolean> res = convalidaFabbisognoService.rettificaMassiva(gestioneCatalogoDTO, sidiUser.getCurrentContesto());
		
		AttribFormDTO attribForm = new AttribFormDTO();
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}
	
	/****  
	 *   LISTA CON PAGINAZIONE
	***/
	@RequestMapping(value = "/gestione-convalida-fabbisogno/get-data.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO loadReportCompletoBase(@RequestBody RicercaConvalidaSingolaDTO ricercaConvalidaSingolaDTO, HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO(); 
		//ResponsePageDTO<ReportCompletoDTO>
		attribForm.add("richiestaT", convalidaFabbisognoService.getElencoScuolePerConvalidazioneSingola(ricercaConvalidaSingolaDTO));
		
		return attribForm;
	}
	
	@RequestMapping(value = "/gestione-convalida-fabbisogno/set-m-convalida-singola.json", method = RequestMethod.POST)
	public ResponseEntity<?> convalidaSingola(@RequestBody ConvalidaFabbisognoForm convalidaFabbisognoForm, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ResponseDTO<Boolean> res = convalidaFabbisognoService.convalidaSingola(convalidaFabbisognoForm.getKey());
		
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("key", convalidaFabbisognoForm.getKey());
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}
	
	@RequestMapping(value = "/gestione-convalida-fabbisogno/set-m-rettifica-singola.json", method = RequestMethod.POST)
	public ResponseEntity<?> rettificaSingola(@RequestBody ConvalidaFabbisognoForm convalidaFabbisognoForm, BindingResult bindingResult,
			HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ResponseDTO<Boolean> res = convalidaFabbisognoService.rettificaSingola(convalidaFabbisognoForm.getKey());
		
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("key", convalidaFabbisognoForm.getKey());
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}
}
