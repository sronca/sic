package it.istruzione.poninchiaro.controller;


import static it.istruzione.poninchiaro.common.util.Constants.TIPO_PROGETTO_FORMAZIONE;
import static it.istruzione.poninchiaro.common.util.Constants.EMPTY_STRING;
import static it.istruzione.poninchiaro.common.util.Constants.DOC_GENERICO;
import static it.istruzione.poninchiaro.common.util.Constants.SOCIAL;
import static it.istruzione.poninchiaro.common.util.Constants.FOTO;
import static it.istruzione.poninchiaro.common.util.Constants.FOTO_TARGA;
import static it.istruzione.poninchiaro.common.util.Constants.VIDEO;
import static it.istruzione.poninchiaro.common.util.Constants.ESITO_OK;
import it.istruzione.poninchiaro.bean.ws.GetdocsRequestType;
import it.istruzione.poninchiaro.bean.ws.GetdocsResponseType;
import it.istruzione.poninchiaro.bean.ws.NusoapAut;
import it.istruzione.poninchiaro.bean.ws.NusoapAutPortType;
import it.istruzione.poninchiaro.bean.ws.Prgdocs;
import it.istruzione.poninchiaro.bean.ws.Prgpgt;
import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOIstituto;
import it.istruzione.poninchiaro.model.VOModuloRichiesta;
import it.istruzione.poninchiaro.model.VOProgetto;
import it.istruzione.poninchiaro.model.VOProgettoIstituto;
import it.istruzione.poninchiaro.model.VOProgettoOpendata;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchedaProgettoController {
    
	private PoninchiaroService poninchiaroService;
	
    
    private static Logger log = Logger.getLogger(SchedaProgettoController.class);
    private static final int PAGE_SIZE = 10;
    

    @Value("${ws.poninchiaro.docsws.wsdl}")
    private String endpoint;
    
    @Value("${poninchiaro.server.allegati}")
    private String serverAllegati;
    
    
	@Autowired
    public SchedaProgettoController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    

	@RequestMapping("/progetti/{codTipFon}/{prgProgetto}/{codScuUt}")
	public String dettaglioBando(@PathVariable(value = "codTipFon") String codTipFon,
            					 @PathVariable(value = "prgProgetto") String prgProgetto,
            					 @PathVariable(value = "codScuUt") String codScuUt,
            					 Model model,
			  				     HttpServletRequest request) throws Exception {
		
		log.debug("prgProgetto : " + prgProgetto);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt.toUpperCase());
		model.addAttribute("scuola", scuola);
		
		// recupero l'evento
		VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		
		// recupero i deati descrittivi del progetto (ALTO-SINISTRA)
		VOProgettoOpendata datiDescrittiviProg = poninchiaroService.getDatiDescrittiviProgetto(Integer.parseInt(prgProgetto), evento.getPrgEve());
		
		// recupero i dati finanziari del progetto (ALTO-DESTRA)  
		VOProgettoIstituto datiFinanziariProg = poninchiaroService.getDatiFinanziariProgetto(Integer.parseInt(prgProgetto), evento.getPrgEve());
		  
		// recupero i dati di progetto (BASSO_SINISTRA)
		VOProgetto datiProg = poninchiaroService.getDatiProgetto(Integer.parseInt(prgProgetto), evento.getPrgEve());
		
		boolean galleryPresent = false;
		boolean videoPresent = false;
		String facebookUrl = EMPTY_STRING;
		String twitterUrl = EMPTY_STRING;
		List<String> documentList = new ArrayList<String>();
		
		// creo l'oggetto request per il ws
		GetdocsRequestType requestObject = getRequestObject(prgProgetto);
		
		List<Prgdocs> prgDocsList = null;
		try {
			NusoapAut nusoapAut = new NusoapAut(new URL(endpoint));
			NusoapAutPortType nusoapAutPortType = nusoapAut.getNusoapAutPort();
			
			log.debug("Chiamata al webservice con il seguente endpoint: " + endpoint);
			GetdocsResponseType docsResponseType = nusoapAutPortType.getdocs(requestObject);
			log.debug("Chiamata effettua con successo");
			
			if (docsResponseType != null && docsResponseType.getPrgpgtdocs().getEsito().getCodice() == ESITO_OK) {
				prgDocsList = docsResponseType.getPrgpgtdocs().getPrgDocs();
				
				// ciclo sulla lista di oggeti ritornati
				for (Prgdocs prgdocs : prgDocsList) {
					String tipoDoc = prgdocs.getTipoDocumento();
					if (FOTO_TARGA.equals(tipoDoc) || FOTO.equals(tipoDoc)) {
						galleryPresent = true;
					}
					if (VIDEO.equals(tipoDoc)) {
						videoPresent = true;
					}
					if (SOCIAL.equals(tipoDoc)) {
						if (prgdocs.getLink().contains("facebook")) {
							facebookUrl = prgdocs.getLink();
							model.addAttribute("facebookUrl", facebookUrl);
						} else {
							twitterUrl = prgdocs.getLink();
							model.addAttribute("twitterUrl", twitterUrl);
						}
					}
					if (DOC_GENERICO.equals(tipoDoc)) {
						documentList.add(prgdocs.getLink());
					}
				}
			}
		} catch (Exception e) {
			log.error("La chiamata al Webservice non è andata a buon fine: " + e.getMessage());
		}
		
		model.addAttribute("datiDescrittiviProg", datiDescrittiviProg);
		model.addAttribute("datiFinanziariProg", datiFinanziariProg);
		model.addAttribute("datiProg", datiProg);
		model.addAttribute("galleryPresent", galleryPresent);
		model.addAttribute("videoPresent", videoPresent);
		model.addAttribute("documentList", documentList);
		model.addAttribute("codTipFon", codTipFon);
		model.addAttribute("serverAllegati", serverAllegati);
		model.addAttribute("prgProgetto", prgProgetto);
		
		if (TIPO_PROGETTO_FORMAZIONE.equals(codTipFon.toUpperCase())) { 
			return "dettaglio/schedaProgettoFormazione";
		} else {
			return "dettaglio/schedaProgettoInfrastruttura";
		}
		
	}
	
	
	private GetdocsRequestType getRequestObject(String prgProgetto){
		GetdocsRequestType docsRequestType = new GetdocsRequestType();
		Prgpgt prgpgt = new Prgpgt();
		prgpgt.setPrgPgt(new Long(prgProgetto));
		docsRequestType.setPrgpgt(prgpgt);
		return docsRequestType;
	}
	
	
	
	@RequestMapping("/progetti/{prgProgetto}/{codScuUt}")
	public String moduliRichiesta(@PathVariable(value = "prgProgetto") String prgProgetto,
            					  @PathVariable(value = "codScuUt") String codScuUt,
            					  Model model,
			  				      HttpServletRequest request) throws Exception {
		
		log.debug("prgProgetto : " + prgProgetto);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt.toUpperCase());
		model.addAttribute("scuola", scuola);
		
		// recupero la lista dei moduli richiesta 
		List<VOModuloRichiesta> moduliRichiestaList = poninchiaroService.getListaModuliRichiesta(Integer.parseInt(prgProgetto));
		
		model.addAttribute("moduliRichiestaList", moduliRichiestaList);
		model.addAttribute("prgProgetto", prgProgetto);
		model.addAttribute("codScuUt", codScuUt);

		String queryString = "?";
		if (request.getQueryString()!=null){
			queryString = "?" + request.getQueryString().toString();
		}
		
		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
		
		PagedListHolder<VOModuloRichiesta> pagedListHolder = new PagedListHolder<VOModuloRichiesta>(moduliRichiestaList);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(PAGE_SIZE);
		pagedListHolder.setMaxLinkedPages(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "dettaglio/elencoModuliRichiesta";
		
	}
	
	
	@RequestMapping("/progetti/gallery/{prgProgetto}/{codScuUt}/{codTipFon}")
	public String viewGallery(@PathVariable(value = "prgProgetto") String prgProgetto,
			 				  @PathVariable(value = "codScuUt") String codScuUt,
			 				  @PathVariable(value = "codTipFon") String codTipFon,
							  Model model,
			  				  HttpServletRequest request) throws Exception {
		
		log.debug("prgProgetto : " + prgProgetto);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt.toUpperCase());
		model.addAttribute("scuola", scuola);
		
		// creo l'oggetto request per il ws
		GetdocsRequestType requestObject = getRequestObject(prgProgetto);
		
		List<String> galleryList = new ArrayList<String>();
		try {
			NusoapAut nusoapAut = new NusoapAut(new URL(endpoint));
			NusoapAutPortType nusoapAutPortType = nusoapAut.getNusoapAutPort();
			
			log.debug("Chiamata al webservice con il seguente endpoint: " + endpoint);
			GetdocsResponseType docsResponseType = nusoapAutPortType.getdocs(requestObject);
			log.debug("Chiamata effettua con successo");
			
			if (docsResponseType != null && docsResponseType.getPrgpgtdocs().getEsito().getCodice() == 200) {
				List<Prgdocs> prgDocsList = docsResponseType.getPrgpgtdocs().getPrgDocs();
				if (!prgDocsList.isEmpty()) {
					for (Prgdocs prgdocs : prgDocsList) {
						if (FOTO_TARGA.equals(prgdocs.getTipoDocumento()) || "F".equals(prgdocs.getTipoDocumento())) {
							galleryList.add(prgdocs.getLink());
						}
					}
				}
			}
			
			model.addAttribute("galleryList", galleryList);
			model.addAttribute("serverAllegati", serverAllegati);
			model.addAttribute("codTipFon", codTipFon);
			model.addAttribute("prgProgetto", prgProgetto);
			
		} catch (Exception e) {
			log.error("La chiamata al Webservice non è andata a buon fine: " + e.getMessage());
		}
		
		return "dettaglio/gallery";
		
	}
	
	
	@RequestMapping("/progetti/video/{prgProgetto}/{codScuUt}/{codTipFon}")
	public String viewVideo(@PathVariable(value = "prgProgetto") String prgProgetto,
						  	@PathVariable(value = "codScuUt") String codScuUt,
						  	@PathVariable(value = "codTipFon") String codTipFon,
						  	Model model,
						  	HttpServletRequest request) throws Exception {
		
		log.debug("prgProgetto : " + prgProgetto);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt.toUpperCase());
		model.addAttribute("scuola", scuola);
		
		// creo l'oggetto request per il ws
		GetdocsRequestType requestObject = getRequestObject(prgProgetto);
		
		String video = EMPTY_STRING;
		
		try {
			NusoapAut nusoapAut = new NusoapAut(new URL(endpoint));
			NusoapAutPortType nusoapAutPortType = nusoapAut.getNusoapAutPort();
			
			log.debug("Chiamata al webservice con il seguente endpoint: " + endpoint);
			GetdocsResponseType docsResponseType = nusoapAutPortType.getdocs(requestObject);
			log.debug("Chiamata effettua con successo");
			
			if (docsResponseType != null && docsResponseType.getPrgpgtdocs().getEsito().getCodice() == 200) {
				List<Prgdocs> prgDocsList = docsResponseType.getPrgpgtdocs().getPrgDocs();
				if (!prgDocsList.isEmpty()) {
					for (Prgdocs prgdocs : prgDocsList) {
						if (VIDEO.equals(prgdocs.getTipoDocumento())) {
							video = getFormattedUrl(prgdocs.getLink()); 
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("La chiamata al Webservice non è andata a buon fine: " + e.getMessage());
		}
		
		model.addAttribute("video", video);
		model.addAttribute("codTipFon", codTipFon);
		model.addAttribute("prgProgetto", prgProgetto);
		
		return "dettaglio/video";
		
	}
	

	private String getFormattedUrl(String url){
		
		if (!url.contains("embed")) {
			url = url.replace("watch?v=", "embed/");
		}
		
		if (url.contains("&")) {
			url = url.split("&")[0];
		}
		
		return url;
	}
	
}
