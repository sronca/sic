package it.istruzione.ptof.controller.catalogo;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.FileMetaDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorFiedDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.catalogo.DecretiHelper;
import it.istruzione.ptof.catalogo.SummaryImportDecreti;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.RequestNotAutorizedException;
import it.istruzione.ptof.service.GestioneDocumentiService;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
public class CatalogoDocumentoRestController {

	private static Logger logger = Logger.getLogger(CatalogoDocumentoRestController.class);
 
	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;
	
	
	
	@RequestMapping(value = "/gestione-catalogo-documento/{progresivoGestioneCatalogoDocumento}", method = RequestMethod.GET)
	public AttribFormDTO getCatalogoDocumento(@PathVariable Long progresivoGestioneCatalogoDocumento, HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progresivoGestioneCatalogoDocumento);
		
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("gestioneCatalogoDTO", gestioneCatalogoDTO);
		return attribForm;
	}
	
	@RequestMapping(value = "/gestione-catalogo-documento/generaVersione", method = RequestMethod.GET)
	public AttribFormDTO generaVersione(HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.generaNuovoCatalogo();
		
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());
		
		if ( globalFunctionsDTO== null ){
			// tentativo di passare un doc a cui non si e' autorizzati ad accedere
			throw new RequestNotAutorizedException( );
		}
		AttribFormDTO attribForm = new AttribFormDTO();
		attribForm.add("gestioneCatalogoDTO", gestioneCatalogoDTO);
		return attribForm;
	}
	
	@RequestMapping(value = "/gestione-catalogo-documento/set-m-salvaCatalogoDocumento.json", method = RequestMethod.POST)
	public ResponseEntity<?> salvaCatalogoDocumento(@RequestBody GestioneCatalogoDTO gestioneCatalogoDTO, HttpSession session) {
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());

		ResponseDTO<Boolean> res = gestioneDocumentiService.salvaCatalogoDocumento(gestioneCatalogoDTO);
		
		AttribFormDTO attribForm = new AttribFormDTO();
		return CommonsUtility.sendMessageToClient(res, attribForm);
	}
	
	@RequestMapping(value = "/gestione-catalogo-documento/set-m-caricaDecretiExcel.json", method = RequestMethod.POST ,    produces={"application/json"}  )
	@ResponseBody
	public ResponseEntity<LinkedList<FileMetaDTO>> caricaDecretiExcel(MultipartHttpServletRequest request, HttpSession session ) {
		LinkedList<FileMetaDTO> files = new LinkedList< FileMetaDTO>();
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GlobalFunctionsDTO  globalFunctionsDTO = (GlobalFunctionsDTO) session.getAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name());

		ResponseDTO<Boolean> res = null;
		DecretiHelper decretiHelper = new DecretiHelper();
		FileMetaDTO fileMeta = null;
		
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) request.getFile("upload");

		fileMeta = new FileMetaDTO();
		fileMeta.setFileName(commonsMultipartFile.getOriginalFilename());
		fileMeta.setFileSize(commonsMultipartFile.getSize());
		fileMeta.setFileType(commonsMultipartFile.getContentType());

		try {
			if (commonsMultipartFile.getOriginalFilename().toLowerCase().endsWith(".csv")){
				SummaryImportDecreti summaryImportDecreti = decretiHelper.importDecretiExcel(commonsMultipartFile.getInputStream());
				
				if (summaryImportDecreti.getValidationErrorDTO()==null){
					GestioneCatalogoDTO gestioneCatalogoDTO = new GestioneCatalogoDTO();
					gestioneCatalogoDTO.setProgresivoGestioneCatalogoDocumento(new Long(request.getParameter("progressivoGestioneCatalogoDocumento")));
					gestioneDocumentiService.importDatiDecreto(summaryImportDecreti.getListDatiDecretoDTO(), gestioneCatalogoDTO);
				}else{

					List<ValidationErrorFiedDTO> listError = summaryImportDecreti.getValidationErrorDTO().getFieldErrors();
					String messaggio = "";
					int maxMessage = 5;
					int aux = maxMessage;
					for (ValidationErrorFiedDTO validationErrorFiedDTO : listError) {
						
						messaggio += validationErrorFiedDTO.getMessage() + "\r\n";
						
						if (--maxMessage == 0){
							messaggio += "Numero massimo di messaggi : " + aux  + "\r\n";
							break;
						}
					}
					
					return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta, messaggio, "000");
				}
			}else{
				return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta, "Attenzione, il formato supportato è csv.", "000");
			}
			files.add(fileMeta);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return CommonsUtility.setErrorMessageLoadingAllegato(files, fileMeta, "Attenzione, si sono verificati problemi con il caricamento del File.", "000");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);

		ResponseEntity<LinkedList<FileMetaDTO>> out = new ResponseEntity<LinkedList<FileMetaDTO>>(files, headers, HttpStatus.OK);
		return out;
	}
}
