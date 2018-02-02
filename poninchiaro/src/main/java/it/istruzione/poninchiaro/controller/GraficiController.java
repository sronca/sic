package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.common.util.HighChartsOptions;
import it.istruzione.poninchiaro.common.util.HighChartsSeriesElement;
import it.istruzione.poninchiaro.model.VODistribuzionePartecipanti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgetti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammatoAutorizzato;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammazioneAreaTerritoriale;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GraficiController {
    
	private PoninchiaroService poninchiaroService;
	
    private static Logger log = Logger.getLogger(GraficiController.class);
    
    @Autowired
    public GraficiController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    

    @RequestMapping(value = "/ponincifre/grafici/progetti/per-tipo-intervento")
    public @ResponseBody HighChartsOptions getProgettiPerTipoIntervento() {

    	try {
    		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
    		VOEvento eventoPubblicazioneProgettiFornitori = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
    		List<VODistribuzioneProgetti> elenco = poninchiaroService.getDistribuzioneProgettiPerTipoIntervento(eventoPubblicazioneProgettiIstituti.getPrgEve(), eventoPubblicazioneProgettiFornitori.getPrgEve());

    		List<HighChartsSeriesElement<Double>> seriesElementList = new ArrayList<>();
    		if (elenco != null && !elenco.isEmpty()){
    			
    			for (VODistribuzioneProgetti voDistribuzioneProgetti: elenco) {
    				seriesElementList.add(new HighChartsSeriesElement<>(voDistribuzioneProgetti.getCategoriaIntervento(), Double.valueOf(voDistribuzioneProgetti.getPercentualeProgetti())));
    			}

    		}
    		return new HighChartsOptions<>("Distribuzione progetti per tipo di intervento", "", "", seriesElementList, null);
    		
/*			GESTIONE GRAFICO A BARRE
    		List<HighChartsSeriesElement<List<Double>>> seriesElementList = new ArrayList<>();
    		List<String> categories = new LinkedList<String>();

    		if (elenco != null && !elenco.isEmpty()){
    			List<Double> progettiPerCategoriaList = new LinkedList<Double>();

    			for (VODistribuzioneProgetti voDistribuzioneProgetti: elenco) {
    				progettiPerCategoriaList.add(Double.valueOf(voDistribuzioneProgetti.getPercentualeProgetti()));
    				categories.add(voDistribuzioneProgetti.getCategoriaIntervento());
    			}

    			HighChartsSeriesElement<List<Double>> seriesElement = new HighChartsSeriesElement<>("XXX", progettiPerCategoriaList);
    			seriesElementList.add(seriesElement);
    		}
    		return new HighChartsOptions<>("Distribuzione progetti per tipo di intervento", "", "", seriesElementList, categories);
*/
    		
    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/ponincifre/grafici/finanziamenti/importi-programmazione")
    public @ResponseBody HighChartsOptions getDistribuzioneImportiProgrammazione() {

    	try {
    		List<VODistribuzioneProgrammazioneAreaTerritoriale> elenco = poninchiaroService.getDistribuzioneProgrammazioneAreaTerritoriale();

    		List<HighChartsSeriesElement<Double>> seriesElementList = new ArrayList<>();

    		if (elenco != null && !elenco.isEmpty()){
    			
    			for (VODistribuzioneProgrammazioneAreaTerritoriale voDistribuzioneProgrammazioneAreaTerritoriale: elenco) {
    				seriesElementList.add(new HighChartsSeriesElement<>(voDistribuzioneProgrammazioneAreaTerritoriale.getCodiceAsse() + " - " + voDistribuzioneProgrammazioneAreaTerritoriale.getDescrizioneAsse(), voDistribuzioneProgrammazioneAreaTerritoriale.getPercentualeAsse(), voDistribuzioneProgrammazioneAreaTerritoriale.getImportoTotaleAsse()));
    			}

    		}
    		return new HighChartsOptions<>("Distribuzione percentuale degli importi della programmazione", "", "", seriesElementList, null);

    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/ponincifre/grafici/progetti/programmato-autorizzato")
    public @ResponseBody HighChartsOptions getProgrammatoAutorizzato() {

    	try {
    		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
    		VOEvento eventoPubblicazioneProgettiFornitori = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
    		List<VODistribuzioneProgrammatoAutorizzato> distribuzioneProgrammatoAutorizzato = poninchiaroService.getDistribuzioneProgrammatoAutorizzato(eventoPubblicazioneProgettiIstituti.getPrgEve(), eventoPubblicazioneProgettiFornitori.getPrgEve());


    		List<HighChartsSeriesElement<List<Double>>> seriesElementList = new ArrayList<>();
    		List<String> categories = new LinkedList<String>();

    		if (distribuzioneProgrammatoAutorizzato != null && !distribuzioneProgrammatoAutorizzato.isEmpty()){
    			List<Double> importoProgrammatoPerAsseList = new LinkedList<Double>();
    			List<Double> importoAutorizzatoPerAsseList = new LinkedList<Double>();

    			for (VODistribuzioneProgrammatoAutorizzato vo: distribuzioneProgrammatoAutorizzato) {
    				importoProgrammatoPerAsseList.add(Double.valueOf(vo.getImportoProgrammato()));
    				importoAutorizzatoPerAsseList.add(Double.valueOf(vo.getImportoAutorizzato()));
    				categories.add(vo.getCodiceAsse());
    			}

    			HighChartsSeriesElement<List<Double>> seriesElement1 = new HighChartsSeriesElement<>("Importo Programmato", importoProgrammatoPerAsseList);
    			HighChartsSeriesElement<List<Double>> seriesElement2 = new HighChartsSeriesElement<>("Importo Autorizzato", importoAutorizzatoPerAsseList);
    			seriesElementList.add(seriesElement1);
    			seriesElementList.add(seriesElement2);
    		}
    		return new HighChartsOptions<>("Programmato ed Impegnato", "", "", seriesElementList, categories);

    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/ponincifre/grafici/beneficiari/per-area")
    public @ResponseBody HighChartsOptions getPartecipantiPerArea() {

    	try {
    		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
    		List<VODistribuzionePartecipanti> distribuzionePartecipantiPerCicloScolastico = poninchiaroService.getDistribuzionePartecipantiPerAreaTerritoriale(eventoPubblicazioneProgettiIstituti.getPrgEve());


    		List<HighChartsSeriesElement<List<Long>>> seriesElementList = new ArrayList<>();
    		List<String> categories = new LinkedList<String>();

    		if (distribuzionePartecipantiPerCicloScolastico != null && !distribuzionePartecipantiPerCicloScolastico.isEmpty()){
    			List<Long> istituti = new LinkedList<Long>();
    			List<Long> partecipanti = new LinkedList<Long>();
    			List<Long> nonPartecipanti = new LinkedList<Long>();

    			for (VODistribuzionePartecipanti vo: distribuzionePartecipantiPerCicloScolastico) {
    				istituti.add(vo.getIstituti());
    				partecipanti.add(vo.getPartecipanti());
    				nonPartecipanti.add(vo.getNonPartecipanti());
    				categories.add(vo.getDescrizioneArea());
    			}

    			HighChartsSeriesElement<List<Long>> seriesElement1 = new HighChartsSeriesElement<>("Totale istituti", istituti);
    			HighChartsSeriesElement<List<Long>> seriesElement2 = new HighChartsSeriesElement<>("Istituti partecipanti", partecipanti);
    			HighChartsSeriesElement<List<Long>> seriesElement3 = new HighChartsSeriesElement<>("Istituti non partecipanti", nonPartecipanti);
    			seriesElementList.add(seriesElement1);
    			seriesElementList.add(seriesElement2);
    			seriesElementList.add(seriesElement3);
    		}
    		return new HighChartsOptions<>("Distribuzione istituti beneficiari per area territoriale", "", "", seriesElementList, categories);

    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/ponincifre/grafici/beneficiari/per-regione")
    public @ResponseBody HighChartsOptions getPartecipantiPerRegione() {

    	try {
    		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
    		List<VODistribuzionePartecipanti> distribuzionePartecipantiPerRegione = poninchiaroService.getDistribuzionePartecipantiPerRegione(eventoPubblicazioneProgettiIstituti.getPrgEve());


    		List<HighChartsSeriesElement<List<Long>>> seriesElementList = new LinkedList<>();
    		List<String> categories = new LinkedList<String>();

    		if (distribuzionePartecipantiPerRegione != null && !distribuzionePartecipantiPerRegione.isEmpty()){
    			List<Long> istituti = new LinkedList<Long>();
    			List<Long> partecipanti = new LinkedList<Long>();
    			List<Long> nonPartecipanti = new LinkedList<Long>();

    			for (VODistribuzionePartecipanti vo: distribuzionePartecipantiPerRegione) {
    				istituti.add(vo.getIstituti());
    				partecipanti.add(vo.getPartecipanti());
    				nonPartecipanti.add(vo.getNonPartecipanti());
    				categories.add(vo.getDescrizioneArea());
    			}

    			//HighChartsSeriesElement<List<Long>> seriesElement1 = new HighChartsSeriesElement<>("Totale istituti", istituti);
    			HighChartsSeriesElement<List<Long>> seriesElement2 = new HighChartsSeriesElement<>("Istituti partecipanti", partecipanti);
    			HighChartsSeriesElement<List<Long>> seriesElement3 = new HighChartsSeriesElement<>("Istituti non partecipanti", nonPartecipanti);
    			seriesElementList.add(seriesElement3);
    			seriesElementList.add(seriesElement2);
    			//seriesElementList.add(seriesElement1);
    		}
    		return new HighChartsOptions<>("Distribuzione istituti beneficiari per regione", "", "", seriesElementList, categories);

    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/ponincifre/grafici/beneficiari/per-ciclo-scolastico")
    public @ResponseBody HighChartsOptions getPartecipantiPerCicloScolastico() {

    	try {
    		VOEvento eventoPubblicazioneProgettiIstituti = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
    		List<VODistribuzionePartecipanti> distribuzionePartecipantiPerCicloScolastico = poninchiaroService.getDistribuzionePartecipantiPerCicloScolastico(eventoPubblicazioneProgettiIstituti.getPrgEve());


    		List<HighChartsSeriesElement<List<Long>>> seriesElementList = new ArrayList<>();
    		List<String> categories = new LinkedList<String>();

    		if (distribuzionePartecipantiPerCicloScolastico != null && !distribuzionePartecipantiPerCicloScolastico.isEmpty()){
    			List<Long> istituti = new LinkedList<Long>();
    			List<Long> partecipanti = new LinkedList<Long>();
    			List<Long> nonPartecipanti = new LinkedList<Long>();

    			for (VODistribuzionePartecipanti vo: distribuzionePartecipantiPerCicloScolastico) {
    				istituti.add(vo.getIstituti());
    				partecipanti.add(vo.getPartecipanti());
    				nonPartecipanti.add(vo.getNonPartecipanti());
    				categories.add(vo.getDescrizioneArea());
    			}

    			HighChartsSeriesElement<List<Long>> seriesElement1 = new HighChartsSeriesElement<>("Totale istituti", istituti);
    			HighChartsSeriesElement<List<Long>> seriesElement2 = new HighChartsSeriesElement<>("Istituti partecipanti", partecipanti);
    			HighChartsSeriesElement<List<Long>> seriesElement3 = new HighChartsSeriesElement<>("Istituti non partecipanti", nonPartecipanti);
    			seriesElementList.add(seriesElement1);
    			seriesElementList.add(seriesElement2);
    			seriesElementList.add(seriesElement3);
    		}
    		return new HighChartsOptions<>("Distribuzione partecipanti per ciclo scolastico", "", "", seriesElementList, categories);

    	} catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
    
}
