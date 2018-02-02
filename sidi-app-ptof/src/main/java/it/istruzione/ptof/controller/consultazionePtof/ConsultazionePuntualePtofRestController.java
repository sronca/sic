package it.istruzione.ptof.controller.consultazionePtof;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniDTO;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PageDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.controller.ptof.GestionePtofForm;
import it.istruzione.ptof.monitoraggio.RicercaConsultazionePtofDTO;
import it.istruzione.ptof.service.MonitoraggioService;
import it.istruzione.ptof.service.TipologicheService;

@RestController
public class ConsultazionePuntualePtofRestController {
	
	@Autowired
	private TipologicheService tipogicheService;
	
	@Autowired
	private MonitoraggioService monitoraggioService;
	
							
	@RequestMapping(value = "/consultazione-ptof/init-consultazione-puntuale-ptof.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO initConsultazionePtof(@RequestBody GestionePtofForm form,HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		
		AttribFormDTO attribForm = new AttribFormDTO(); 
		LinkedList<BeanValueDTO> regioniL =  tipogicheService.getRegioni(  sidiUser.getCurrentContesto());
		attribForm.add("REGIONI" ,  regioniL  );
		if(regioniL.size()==1){
			attribForm.add("PROVINCE" , tipogicheService.getProvince(  regioniL.get(0).getValue()  ));
		}
		attribForm.add("TIPOLOGIA_SCUOLA" , tipogicheService.getTipologiaScuolaPtof());
	 
		return attribForm;
	}
	
	
	@RequestMapping(value = "/consultazione-ptof/load-consultazione-puntuale-ptof.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO loadConsultazionePuntualePtof(@RequestBody RicercaConsultazionePtofDTO form, HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO(); 
		  
		attribForm.add("richiestaT", monitoraggioService.getConsultazionFabbisogno(form));
		
		return attribForm;
	}
	
	
	@RequestMapping(value = "/consultazione-ptof/get-dettaglio-fabbisogno.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO getDettaglioFabbisogno(@RequestBody String key, HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO(); 
		  
		
		FabbisognoPostiComuniDTO fabbisognoPostiComuniDTO =  monitoraggioService.getFabbisognoPostiComuni(key);
		//attribForm.add("fabbisognoPostiComuni", monitoraggioService.getFabbisognoPostiComuni(key));
		attribForm.add("fabbisognoPostiComuniIntestaz", fabbisognoPostiComuniDTO );
		// TO DO DA CONTROLLARE
		//attribForm.add("fabbisognoPostiComuniL", fabbisognoPostiComuniDTO.getItems()); 
		
		FabbisognoPostiSostegnoDTO fabbisognoPostiSostegnoDTO =  monitoraggioService.getPostiSostegno(key);
		//attribForm.add("fabbisognoPostiSostegno", monitoraggioService.getPostiSostegno(key));
		attribForm.add("fabbisognoPostiSostegnoIntestaz", fabbisognoPostiSostegnoDTO );
		//attribForm.add("fabbisognoPostiSostegnoL", fabbisognoPostiSostegnoDTO.getItems()); //TO DO DA MODIFICARE PER SERGIO
		
		FabbisognoPostiPotenziamentoDTO fabbisognoPostiPotenziamentoDTO =  monitoraggioService.getPostiPotenziamento(key);
		//attribForm.add("fabbisognoPostiPotenziamento", monitoraggioService.getPostiPotenziamento(key));
		attribForm.add("fabbisognoPostiPotenziamentoIntestaz", fabbisognoPostiPotenziamentoDTO );
		//attribForm.add("fabbisognoPostiPotenziamentoL",   fabbisognoPostiPotenziamentoDTO.getItems());
		
		
	    IstitutoScolasticoDTO istScolastico = monitoraggioService.getScuolaByKeyDocumento(key);
	    attribForm.add("istitutoScolastico",   istScolastico);
		
		
		return attribForm;
	}
	
	
	
	
	

}
