package it.istruzione.ptof.controller.monitoraggio;



import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.istruzione.ptof.beans.PageDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.ReportCompletoFiltroDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.controller.ptof.GestionePtofForm;
import it.istruzione.ptof.monitoraggio.RicercaReportDTO;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.MonitoraggioService;
import it.istruzione.ptof.service.TipologicheService;
 
 

@RestController
public class ReportCompletoController {

 	private static Logger logger = Logger.getLogger(ReportCompletoController.class);
 
	@Autowired
	private MonitoraggioService monitoraggioService;

	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;
	 
	@Autowired
	private TipologicheService tipogicheService;
	
	
	
	@RequestMapping(value = "/monitoraggio-report/init-mon-report-completo-base.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO initReportCompletoBase(@RequestBody GestionePtofForm form,HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO(); 
		LinkedList<BeanValueDTO> regioniL =  tipogicheService.getRegioni(  sidiUser.getCurrentContesto());
		attribForm.add("REGIONI" ,  regioniL  );
		if(regioniL.size()==1){
			attribForm.add("PROVINCE" , tipogicheService.getProvince(  regioniL.get(0).getValue()  ));
		}
		attribForm.add("STATO_PTOF", tipogicheService.getStatoPtof());
		
		return attribForm;
	}
	
	
	/****  
	 *   LISTA CON PAGINAZIONE
	***/
	@RequestMapping(value = "/monitoraggio-report/get-mon-report-completo-base.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO loadReportCompletoBase(@RequestBody RicercaReportDTO form, HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO(); 
		//ResponsePageDTO<ReportCompletoDTO>
		attribForm.add("richiestaT", monitoraggioService.getReportCompleto(form));
		
		return attribForm;
	}
	
	
	@RequestMapping(value = "/monitoraggio-report/get-mon-report-completo-pageable.json", method = RequestMethod.POST)
	@ResponseBody
	public AttribFormDTO loadReportCompletoPageable(@RequestBody RicercaReportDTO form, HttpSession session) {
	     
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		PageDTO page = new ResponsePageDTO<>();
		AttribFormDTO attribForm = new AttribFormDTO();  
		attribForm.add("richiestaT", monitoraggioService.getReportCompleto(form));
		
		return attribForm;
	}
	
	
	@RequestMapping(value = "monitoraggio-report/download-ptof-file/{value}", method = RequestMethod.GET)
	public void downloadCvFile(HttpServletResponse response, @PathVariable String value, @RequestParam String key, @RequestParam String statoPtof , HttpSession session ) {

		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GestioneDocumentiDTO gestioneDocumentiServiceDTO = new GestioneDocumentiDTO();
		gestioneDocumentiServiceDTO.setIstitutoScolastico(globalFunctionsDTO.getIstitutoScolasticoDTO());

		TIPO_FILE_PDF stato = TIPO_FILE_PDF.getInstanceFromCode(statoPtof) ;
		
		
		try {
			FileDTO file = monitoraggioService.loadPtofFile(value, stato);
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
	
	
}
