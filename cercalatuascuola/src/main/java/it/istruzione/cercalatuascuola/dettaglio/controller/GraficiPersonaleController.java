package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.PersonaleDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraficiPersonaleController {
    private Logger logger = Logger.getLogger(GraficiPersonaleController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private PersonaleDAO personaleDAO;
    private GraficiDAO graficiDAO;

    private static final String NAZIONE = "Italia";
    private static final String REGIONE = "Regione";
    private static final String SCUOLA = "Scuola";

    @Autowired
    public GraficiPersonaleController(AnagraficaScuolaService anagraficaScuolaService,
                                      PersonaleDAO personaleDAO,
                                      GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.personaleDAO = personaleDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/grafici/docenti-fasce-eta")
    public @ResponseBody
    HighChartsOptions getDocentiFasceEta(@PathVariable(value = "codScuUt") String codScuUt,
                                         String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_DOC_ATA, "7");
            
            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			Integer totDoc = personaleDAO.getNumTotDocenti(scuola.getCodForScuApp());



    			//Fascia d'età per ruolo
    			if(totDoc != null && totDoc > 0) {
    				List<VOFascia> listaFasciaEta = personaleDAO.getIndicatoriIstogrammaFascia(
    						scuola.getCodForScuApp(), totDoc);

    				Map<String, Float> docentiFasciaRuoloMap = new HashMap<>();
    				Map<String, Float> docentiFasciaNoRuoloMap = new HashMap<>();
    				for (VOFascia voFascia: listaFasciaEta) {
    					docentiFasciaRuoloMap.put(voFascia.getDescrizione(), voFascia.getRuolo());
    					docentiFasciaNoRuoloMap.put(voFascia.getDescrizione(), voFascia.getNoRuolo());
    				}

    				List<Float> docentiRuoloList = new ArrayList<>();
    				List<Float> docentiNoRuoloList = new ArrayList<>();
    				String[] fasce = new String[]{"<35","35-44","45-54","55+"};
    				for (String fascia: fasce) {
    					docentiRuoloList.add(docentiFasciaRuoloMap.get(fascia));
    					docentiNoRuoloList.add(docentiFasciaNoRuoloMap.get(fascia));
    				}

    				HighChartsSeriesElement<List<Float>> docentiRuolo = new HighChartsSeriesElement<>("Tempo indeterminato", docentiRuoloList);
    				HighChartsSeriesElement<List<Float>> docentiNoRuolo = new HighChartsSeriesElement<>("Tempo determinato", docentiNoRuoloList);


    				highChartsSeriesElementList.add(docentiRuolo);
    				highChartsSeriesElementList.add(docentiNoRuolo);
    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/grafici/docenti-trasferiti")
    public @ResponseBody
    HighChartsOptions getDocentiTrasferiti(@PathVariable(value = "codScuUt") String codScuUt,
                                         String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_MOB, "3");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			VOTrasferimento voTrasfDoc = personaleDAO.getIndicatoriPersonaleIstogramma1(scuola.getCodForScuApp());
    			if(voTrasfDoc != null) {

    				List<Float> listaNazione = new ArrayList<>();
    				listaNazione.add(voTrasfDoc.getPerTraNaz());

    				List<Float> listaRegione = new ArrayList<>();
    				listaRegione.add(voTrasfDoc.getPerTraReg());

    				List<Float> listaScuola = new ArrayList<>();
    				listaScuola.add(voTrasfDoc.getPerTraScu());

    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(SCUOLA, listaScuola));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(REGIONE, listaRegione));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(NAZIONE, listaNazione));

    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/grafici/docenti-pensionati")
    public @ResponseBody
    HighChartsOptions getDocentiPensionati(@PathVariable(value = "codScuUt") String codScuUt,
                                         String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_MOB, "4");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			VOPensione voPensione = personaleDAO.getIndicatoriPersonaleIstogramma2(scuola.getCodForScuApp());
    			if (voPensione != null) {

    				List<Float> listaNazione = new ArrayList<>();
    				listaNazione.add(voPensione.getPerCesNaz());

    				List<Float> listaRegione = new ArrayList<>();
    				listaRegione.add(voPensione.getPerCesReg());

    				List<Float> listaScuola = new ArrayList<>();
    				listaScuola.add(voPensione.getPerCes());

    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(SCUOLA, listaScuola));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(REGIONE, listaRegione));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>(NAZIONE, listaNazione));
    			}

    		}
            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/grafici/assenze-docenti")
    public @ResponseBody
    HighChartsOptions getAssenzeDocenti(@PathVariable(value = "codScuUt") String codScuUt,
                                           String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_ASS, "5");

            List<HighChartsSeriesElement<List<Integer>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAssenza> listaAssenze = personaleDAO.getAssenzeDocenti(scuola.getCodForScuApp());

    			if (listaAssenze != null && !listaAssenze.isEmpty()){

    				Map<String, List<Integer>> assenzeMap = new LinkedHashMap<>();

    				assenzeMap.put(SCUOLA, new ArrayList<Integer>());
    				assenzeMap.put(REGIONE, new ArrayList<Integer>());
    				assenzeMap.put(NAZIONE, new ArrayList<Integer>());



    				for (VOAssenza voAssenza: listaAssenze) {
    					assenzeMap.get(SCUOLA).add(voAssenza.getGiorniAssenza());
    					assenzeMap.get(REGIONE).add(voAssenza.getGiorniAssenzaProv());
    					assenzeMap.get(NAZIONE).add(voAssenza.getGiorniAssenzaNaz());
    				}


    				for (Map.Entry<String, List<Integer>> entry: assenzeMap.entrySet()) {
    					HighChartsSeriesElement<List<Integer>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
    					highChartsSeriesElementList.add(seriesElement);
    				}
    			}

    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/grafici/assenze-ata")
    public @ResponseBody
    HighChartsOptions getAssenzeATA(@PathVariable(value = "codScuUt") String codScuUt,
                                        String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_ASS, "6");

            List<HighChartsSeriesElement<List<Integer>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAssenza> listaAssenze = personaleDAO.getAssenzeATA(scuola.getCodForScuApp());

    			if (listaAssenze != null && !listaAssenze.isEmpty()){
    				Map<String, List<Integer>> assenzeMap = new LinkedHashMap<>();

    				assenzeMap.put(SCUOLA, new ArrayList<Integer>());
    				assenzeMap.put(REGIONE, new ArrayList<Integer>());
    				assenzeMap.put(NAZIONE, new ArrayList<Integer>());


    				for (VOAssenza voAssenza: listaAssenze) {
    					assenzeMap.get(SCUOLA).add(voAssenza.getGiorniAssenza());
    					assenzeMap.get(REGIONE).add(voAssenza.getGiorniAssenzaProv());
    					assenzeMap.get(NAZIONE).add(voAssenza.getGiorniAssenzaNaz());
    				}


    				for (Map.Entry<String, List<Integer>> entry: assenzeMap.entrySet()) {
    					HighChartsSeriesElement<List<Integer>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
    					highChartsSeriesElementList.add(seriesElement);
    				}
    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
