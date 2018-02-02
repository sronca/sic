package it.istruzione.cercalatuascuola.ricerca.controller;

import it.istruzione.cercalatuascuola.ricerca.service.RicercaService;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SelectController {
    private RicercaService ricercaService;
    
    private static Logger logger = Logger.getLogger(SelectController.class);

    @Autowired
    public SelectController(RicercaService ricercaService) {
        this.ricercaService = ricercaService;
    }
    

	@RequestMapping("/caricaTipologia.json")
	public @ResponseBody Map<String, String> getTipologiaList(String codiceOrdine, String stataleNonStatale) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getTipologiaList(codiceOrdine, stataleNonStatale);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}
	
	@RequestMapping("/caricaTempiScuolaPrimaria.json")
	public @ResponseBody Map<String, String> getTempiScuolaPrimariaList() {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getTempiScuolaPrimariaList();
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaTempiScuolaSecondariaDiPrimoGrado.json")
	public @ResponseBody Map<String, String> getTempiScuolaSecondariaDiPrimoGrado() {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getTempiScuolaSecondariaDiPrimoGrado();
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaIndirizziDiStudio.json")
	public @ResponseBody Map<String, String> getIndirizziDiStudioList(String codiceTipologia, String codiceSettore, String biennioTriennio) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getIndirizziDiStudioList(codiceTipologia, codiceSettore, biennioTriennio);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaSettoreScuola.json")
	public @ResponseBody Map<String, String> getSettoriScuolaList(String codiceIstituto) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getSettoriScuolaList(codiceIstituto);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaSettoreCFP.json")
	public @ResponseBody Map<String, String> getSettoriCFPList(String codiceIstituto) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getSettoriCFPList(codiceIstituto);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaIndirizzoCFP.json")
	public @ResponseBody Map<String, String> getIndirizziDiStudioCFPList(String codiceTipologia) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getIndirizziDiStudioCFPList(codiceTipologia);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaProvincia.json")
	public @ResponseBody Map<String, String> getProvinceList(String codiceRegione) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getProvinceList(codiceRegione);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaComune.json")
	public @ResponseBody Map<String, String> getComuniList(String codiceProvincia) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = ricercaService.getComuniList(codiceProvincia);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

}

