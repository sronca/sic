package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
	private PoninchiaroService poninchiaroService;
    
    private static Logger log = Logger.getLogger(HomeController.class);
    
    @Autowired
    public HomeController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    
	@RequestMapping({ "/", "/index.html" })
	public String index(Model model) throws Exception {
		
		log.debug("in index");
		
		model.addAttribute("title","Pon in Chiaro - Le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("description","Pon in Chiaro &egrave; la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Infomazioni sul Programma Operativo Nazionale e contatti");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/");
		
		return "home/index";
	}

	@RequestMapping("/faq")
	public String faq(Model model) throws Exception {
		
		log.debug("in faq");
		
		model.addAttribute("elencoFaq", poninchiaroService.getFaq());
		
		model.addAttribute("title","FAQ - Pon in Chiaro");
		model.addAttribute("description","La pagina delle FAQ di Pon in Chiaro, la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","FAQ, pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","FAQ: infomazioni sul Programma Operativo Nazionale e contatti");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/faq/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/faq/");
		
		return "home/faq";
	}

	@RequestMapping("/contatti")
	public String contatti(Model model) throws Exception {
		
		log.debug("in contatti");
		
		model.addAttribute("title","Contatti - Pon in Chiaro");
		model.addAttribute("description","La pagina dei contatti di Pon in Chiaro, la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","Contatti, pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Contatti: infomazioni sul Programma Operativo Nazionale e contatti");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/contatti/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/contatti/");
		
		return "home/contatti";
	}

	@RequestMapping("/glossario")
	public String glossario(Model model) throws Exception {
		
		log.debug("in glossario");
		
		model.addAttribute("glossario", poninchiaroService.getGlossario());
		
		model.addAttribute("title","Glossario - Pon in Chiaro");
		model.addAttribute("description","La pagina del GLOSSARIO di Pon in Chiaro, la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","Glossario, pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Glossario: Programma Operativo Nazionale ");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/glossario/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/glossario/");
		
		return "home/glossario";
	}

	@RequestMapping("/opendata")
	public String opendata(Model model) throws Exception {
		
		log.debug("in opendata");
		
		String dataDB = poninchiaroService.getDataAggiornamentoIstitutiBandi();
		String dataAggiornamentoBandiScuole = "";
		if (!dataDB.isEmpty()){
			dataAggiornamentoBandiScuole = dataDB.substring(6, 8) + "/" + dataDB.substring(4, 6) + "/" + dataDB.substring(0, 4) ;
		}
		model.addAttribute("dataAggiornamentoBandiScuole", dataAggiornamentoBandiScuole);

		String dataBA = poninchiaroService.getDataAggiornamentoBandiAmministrazione();
		String dataAggiornamentoBandiAmministrazione = "";
		if (!dataBA.isEmpty()){
			dataAggiornamentoBandiAmministrazione = dataBA.substring(6, 8) + "/" + dataBA.substring(4, 6) + "/" + dataBA.substring(0, 4) ;
		}
		model.addAttribute("dataAggiornamentoBandiAmministrazione", dataAggiornamentoBandiAmministrazione);
		
		VOEvento eventoPI = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		model.addAttribute("dataAggiornamentoProgettiIstituti", eventoPI.getDataAggiornamento());
		VOEvento eventoPF = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
		model.addAttribute("dataAggiornamentoProgettiFornitori", eventoPF.getDataAggiornamento());
		
		model.addAttribute("title","Opendata - Pon in Chiaro");
		model.addAttribute("description","La pagina degli opendata di Pon in Chiaro, la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","Opendata, pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Opendata: Programma Operativo Nazionale ");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/opendata/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/opendata/");
		
		return "home/opendata";
	}
	
}
