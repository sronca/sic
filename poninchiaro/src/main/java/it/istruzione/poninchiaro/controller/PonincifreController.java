package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.model.VODistribuzionePartecipanti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgetti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammatoAutorizzato;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammazioneAreaTerritoriale;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOFiltroRicerca;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PonincifreController {
    
	private PoninchiaroService poninchiaroService;
	
    private static Logger log = Logger.getLogger(PonincifreController.class);
    
    @Autowired
    public PonincifreController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    

	@RequestMapping("/ponincifre/progetti")
	public String progetti(Model model, VOFiltroRicerca filtroRicerca) throws Exception {
		
		log.debug("in ponincifre - progetti");
		
		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		VOEvento eventoPubblicazioneProgettiFornitori = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
		
		List<VODistribuzioneProgetti> distribuzioneProgettiPerTipoIntervento = poninchiaroService.getDistribuzioneProgettiPerTipoIntervento(eventoPubblicazioneProgettiIstituti.getPrgEve(), eventoPubblicazioneProgettiFornitori.getPrgEve());
		
		long progettiAutorizzati = 0;
		double importoAutorizzato = 0;
		for(VODistribuzioneProgetti vo : distribuzioneProgettiPerTipoIntervento){
			progettiAutorizzati += vo.getNumeroProgetti();
			importoAutorizzato += vo.getImportoAutorizzato();
		}
		model.addAttribute("progettiAutorizzati", progettiAutorizzati);
		model.addAttribute("importoAutorizzato", importoAutorizzato);
		model.addAttribute("distribuzioneProgettiPerTipoIntervento", distribuzioneProgettiPerTipoIntervento);
		
		List<VODistribuzioneProgetti> distribuzioneProgettiIstitutiPerAreaTerritoriale = poninchiaroService.getDistribuzioneProgettiIstitutiPerAreaTerritoriale(eventoPubblicazioneProgettiIstituti.getPrgEve(), filtroRicerca.getArea());
		model.addAttribute("distribuzioneProgettiIstitutiPerAreaTerritoriale", distribuzioneProgettiIstitutiPerAreaTerritoriale);
		model.addAttribute("filtroRicerca", filtroRicerca);
		long progettiAutorizzatiPerArea = 0;
		double importoAutorizzatoPerArea = 0;
		for(VODistribuzioneProgetti vo : distribuzioneProgettiIstitutiPerAreaTerritoriale){
			progettiAutorizzatiPerArea += vo.getNumeroProgetti();
			importoAutorizzatoPerArea += vo.getImportoAutorizzato();
		}
		model.addAttribute("progettiAutorizzatiPerArea", progettiAutorizzatiPerArea);
		model.addAttribute("importoAutorizzatoPerArea", importoAutorizzatoPerArea);
		
		model.addAttribute("areeTerritoriali", poninchiaroService.getAreeTerritorialiList());
		
		
		
		model.addAttribute("title","Pon in Chiaro - Pon in cifre - Progetti");
		model.addAttribute("description","Dati relativi ai progetti dei Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, pon in cifre, istituti beneficiari, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Progetti - Pon in cifre");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/progetti/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/progetti/");
		
		return "ponincifre/progetti";
	}

	@RequestMapping("/ponincifre/programmazione")
	public String finanziamenti(Model model, VOFiltroRicerca filtroRicerca) throws Exception {
		
		log.debug("in ponincifre - programmazione");
		
		List<VODistribuzioneProgrammazioneAreaTerritoriale> distribuzioneProgrammazioneAreaTerritoriale = poninchiaroService.getDistribuzioneProgrammazioneAreaTerritoriale();
		long importoTotale = 0;
		long importoTotaleMS = 0;
		long importoTotaleTR = 0;
		long importoTotalePS = 0;
		for(VODistribuzioneProgrammazioneAreaTerritoriale vo : distribuzioneProgrammazioneAreaTerritoriale){
			importoTotale += vo.getImportoTotaleAsse();
			importoTotaleMS += vo.getImportoAreaMS();
			importoTotaleTR += vo.getImportoAreaTR();
			importoTotalePS += vo.getImportoAreaPS();
		}

		model.addAttribute("importoTotale", importoTotale);
		model.addAttribute("importoTotaleMS", importoTotaleMS);
		model.addAttribute("importoTotaleTR", importoTotaleTR);
		model.addAttribute("importoTotalePS", importoTotalePS);
		model.addAttribute("distribuzioneProgrammazioneAreaTerritoriale", distribuzioneProgrammazioneAreaTerritoriale);
		
		long importoTotaleImpegnato = 0;		
		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		VOEvento eventoPubblicazioneProgettiFornitori = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
		List<VODistribuzioneProgrammatoAutorizzato> distribuzioneProgrammatoAutorizzato = poninchiaroService.getDistribuzioneProgrammatoAutorizzato(eventoPubblicazioneProgettiIstituti.getPrgEve(), eventoPubblicazioneProgettiFornitori.getPrgEve());
		for(VODistribuzioneProgrammatoAutorizzato vo : distribuzioneProgrammatoAutorizzato){
			importoTotaleImpegnato += vo.getImportoAutorizzato();
		}
		
		model.addAttribute("importoTotaleImpegnato", importoTotaleImpegnato);
		
		model.addAttribute("title","Pon in Chiaro - Pon in cifre - Programmazione");
		model.addAttribute("description","Dati relativi ai progetti dei Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, pon in cifre, istituti beneficiari, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Progetti - Pon in cifre");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/programmazione/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/programmazione/");
		
		return "ponincifre/finanziamenti";
	}

	@RequestMapping("/ponincifre/beneficiari")
	public String beneficiari(Model model, VOFiltroRicerca filtroRicerca) throws Exception {
		
		log.debug("in ponincifre - beneficiari");
		
		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		List<VODistribuzionePartecipanti> distribuzionePartecipantiPerCicloScolastico = poninchiaroService.getDistribuzionePartecipantiPerCicloScolastico(eventoPubblicazioneProgettiIstituti.getPrgEve());
	
		long totaleIstituti = 0;
		long totalePartecipanti = 0;
		long totalePartecipantiPrimoCiclo = 0;
		long totalePartecipantiSecondoCiclo = 0;
		
		for(VODistribuzionePartecipanti vo : distribuzionePartecipantiPerCicloScolastico){
			totaleIstituti += vo.getIstituti();
			totalePartecipanti += vo.getPartecipanti();
			if (vo.getDescrizioneArea().equals("Primo Ciclo")){
				totalePartecipantiPrimoCiclo = vo.getPartecipanti();
			}else {
				totalePartecipantiSecondoCiclo = vo.getPartecipanti();
			}
		}
		
		model.addAttribute("totaleIstituti", totaleIstituti);
		model.addAttribute("totalePartecipanti", totalePartecipanti);
		model.addAttribute("totalePartecipantiPrimoCiclo", totalePartecipantiPrimoCiclo);
		model.addAttribute("totalePartecipantiSecondoCiclo", totalePartecipantiSecondoCiclo);
		
		model.addAttribute("title","Pon in Chiaro - Pon in cifre - Beneficiari");
		model.addAttribute("description","Dati relativi ai progetti dei Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, pon in cifre, istituti beneficiari, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Progetti - Pon in cifre");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/beneficiari/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/ponincifre/beneficiari/");
		
		return "ponincifre/beneficiari";
	}

}
