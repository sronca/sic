package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.model.ScuolaJson;
import it.istruzione.poninchiaro.model.Suggestions;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SelectController {
    private PoninchiaroService poninchiaroService;
    
    private static Logger logger = Logger.getLogger(SelectController.class);

    @Autowired
    public SelectController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    
	@RequestMapping("/caricaProvincia.json")
	public @ResponseBody Map<String, String> getProvinceList(String codiceRegione) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = poninchiaroService.getProvinceList(codiceRegione);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaSottoCategoria.json")
	public @ResponseBody Map<String, String> getSottoCategoriaList(String tipoFondo) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = poninchiaroService.getSottoCategoriaList(tipoFondo);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/caricaComune.json")
	public @ResponseBody Map<String, String> getComuniList(String codiceProvincia) {
		Map<String, String> out = new LinkedHashMap<String, String>();
		try{
			out = poninchiaroService.getComuniList(codiceProvincia);
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}

	@RequestMapping("/getIstitutiList.json")
	public @ResponseBody Suggestions getIstitutiList(String query) {
		Suggestions out = new Suggestions();
		try{
			List<ScuolaJson> elenco = poninchiaroService.getIstitutiList(query);
			out.setSuggestions(elenco);
			
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		return out;		
	}
    
}

