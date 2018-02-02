package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.AlunniDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class GraficiAlunniController {
    private Logger logger = Logger.getLogger(GraficiAlunniController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private AlunniDAO alunniDAO;
    private GraficiDAO graficiDAO;

    private static final String NAZIONE = "Italia";
    private static final String REGIONE = "Regione";
    private static final String SCUOLA = "Scuola";
    private static final String ENTRATE = "Entrate";
    private static final String USCITE = "Uscite";

    @Autowired
    public GraficiAlunniController(AnagraficaScuolaService anagraficaScuolaService,
                                   AlunniDAO alunniDAO,
                                   GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.alunniDAO = alunniDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/andamento-alunni")
    public @ResponseBody HighChartsOptions getAndamentoAlunni(@PathVariable(value = "codScuUt") String codScuUt,
                                                              String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALU_E_CLA, "3");

            Map<String, List<Integer>> alunniAnnoCorsoMap = new HashMap<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaAndamento = alunniDAO.getAlunniIscrittiUltimiDueAnni(
    					scuola.getCodForScuApp());

    			
    			for (VOAndamento voAndamento: listaAndamento) {
    				List<Integer> alunni = alunniAnnoCorsoMap.get(voAndamento.getAnnoScolastico());
    				if (alunni == null) {
    					alunni = new ArrayList<>();
    					alunniAnnoCorsoMap.put(voAndamento.getAnnoScolastico(), alunni);
    				}
    				alunni.add(Integer.parseInt(voAndamento.getNumeroAlunni()));
    			}
    		}

            List<HighChartsSeriesElement<List<Integer>>> highChartsSeriesElementList = new ArrayList<>();
            for (Map.Entry<String, List<Integer>> entry: alunniAnnoCorsoMap.entrySet()) {
                HighChartsSeriesElement<List<Integer>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
                highChartsSeriesElementList.add(seriesElement);
            }

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/studenti-ripetenti")
    public @ResponseBody HighChartsOptions getStudentiRipetenti(@PathVariable(value = "codScuUt") String codScuUt,
                                                              String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIP, "4");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaRipetenti = alunniDAO.getAlunniRipetenti(scuola.getCodForScuApp());

    			if (listaRipetenti != null && !listaRipetenti.isEmpty()){
    				Map<String, List<Float>> alunniRipetentiAnnoCorsoMap = new LinkedHashMap<>();

    				alunniRipetentiAnnoCorsoMap.put(SCUOLA, new ArrayList<Float>());
    				alunniRipetentiAnnoCorsoMap.put(REGIONE, new ArrayList<Float>());
    				alunniRipetentiAnnoCorsoMap.put(NAZIONE, new ArrayList<Float>());


    				for (VOAndamento voAndamento: listaRipetenti) {
    					alunniRipetentiAnnoCorsoMap.get(NAZIONE).add(voAndamento.getPercentualeRipetentiNaz());
    					alunniRipetentiAnnoCorsoMap.get(REGIONE).add(voAndamento.getPercentualeRipetentiProv());
    					alunniRipetentiAnnoCorsoMap.get(SCUOLA).add(voAndamento.getPercentualeRipetentiPlesso());
    				}


    				for (Map.Entry<String, List<Float>> entry: alunniRipetentiAnnoCorsoMap.entrySet()) {
    					HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
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

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/trasferimenti")
    public @ResponseBody HighChartsOptions getTrasferimenti(@PathVariable(value = "codScuUt") String codScuUt,
                                                            String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIP, "5");
            
            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaEntrateUscite = alunniDAO.getAlunniIngressoUscita(scuola.getCodForScuApp());
    			logger.debug("listaEntrateUscite : " + listaEntrateUscite.size());

    			if (listaEntrateUscite != null && !listaEntrateUscite.isEmpty()){
    				Map<String, List<Float>> trasferimentiAnnoCorsoMap = new HashMap<>();

    				trasferimentiAnnoCorsoMap.put(ENTRATE, new ArrayList<Float>());
    				trasferimentiAnnoCorsoMap.put(USCITE, new ArrayList<Float>());

    				for (VOAndamento voAndamento: listaEntrateUscite) {
    					trasferimentiAnnoCorsoMap.get(ENTRATE).add(voAndamento.getPercentualeEntrate());
    					trasferimentiAnnoCorsoMap.get(USCITE).add(voAndamento.getPercentualeUscite());
    				}


    				for (Map.Entry<String, List<Float>> entry: trasferimentiAnnoCorsoMap.entrySet()) {
    					HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
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

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/abbandoni")
    public @ResponseBody HighChartsOptions getAbbandoni(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm) {

    	try {
    		VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

    		VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
    				scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIP, "6");

    		List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaAbbandoni = alunniDAO.getIndicatoriAlunniIstogramma4(scuola.getCodForScuApp());

    			if (listaAbbandoni != null && !listaAbbandoni.isEmpty()){
    				Map<String, List<Float>> abbandoniAnnoCorsoMap = new LinkedHashMap<>();

    				abbandoniAnnoCorsoMap.put(SCUOLA, new ArrayList<Float>());
    				abbandoniAnnoCorsoMap.put(REGIONE, new ArrayList<Float>());
    				abbandoniAnnoCorsoMap.put(NAZIONE, new ArrayList<Float>());

    				for (VOAndamento voAndamento: listaAbbandoni) {
    					abbandoniAnnoCorsoMap.get(NAZIONE).add(voAndamento.getPercentualeAbbandonoNaz());
    					abbandoniAnnoCorsoMap.get(REGIONE).add(voAndamento.getPercentualeAbbandonoProv());
    					abbandoniAnnoCorsoMap.get(SCUOLA).add(voAndamento.getPercentualeAbbandonoPlesso());
    				}


    				for (Map.Entry<String, List<Float>> entry: abbandoniAnnoCorsoMap.entrySet()) {
    					HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
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

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/esiti-giugno")
    public @ResponseBody HighChartsOptions getEsitiGiugno(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_SCR, "1");
            
            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VOAndamento esitiScrutini = alunniDAO.getEsitiScrutini(scuola.getCodForScuApp(), scuola.isSecondariaSecGrado());



    			if (esitiScrutini != null) {
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Ammessi", esitiScrutini.getAlunniAmmessi()));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Non Ammessi", esitiScrutini.getAlunniNonAmmessi()));
    				if (scuola.isSecondariaSecGrado()){
    					highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Sospesi", esitiScrutini.getAlunniSospesi()));
    				}
    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/esiti-giugno-settembre")
    public @ResponseBody HighChartsOptions getEsitiGiugnoSettembre(@PathVariable(value = "codScuUt") String codScuUt,
                                                          String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_SCR, "2");
            
            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VOAndamento esitiScrutini = alunniDAO.getEsitiScrutini(scuola.getCodForScuApp(), scuola.isSecondariaSecGrado());



    			if (esitiScrutini != null) {
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Ammessi", esitiScrutini.getAlunniAmmessiSett()));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Non Ammessi", esitiScrutini.getAlunniNonAmmessiSett()));
    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/ammessi-giugno")
    public @ResponseBody HighChartsOptions getAmmessiGiugno(@PathVariable(value = "codScuUt") String codScuUt,
                                                          String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_SCR, "3");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaAmmessi = alunniDAO.getAmmessiGiugno(scuola.getCodForScuApp());


    			if (listaAmmessi != null && !listaAmmessi.isEmpty()){
    				Map<String, List<Float>> ammessiMap = new LinkedHashMap<>();

    				ammessiMap.put(NAZIONE, new ArrayList<Float>());
    				ammessiMap.put(REGIONE, new ArrayList<Float>());
    				ammessiMap.put(SCUOLA, new ArrayList<Float>());

    				for (VOAndamento voAndamento: listaAmmessi) {
    					ammessiMap.get(NAZIONE).add(voAndamento.getPercentualeAmmessoNaz().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoNaz());
    					ammessiMap.get(REGIONE).add(voAndamento.getPercentualeAmmessoProv().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoProv());
    					ammessiMap.get(SCUOLA).add(voAndamento.getPercentualeAmmessoPlesso().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoPlesso());
    				}


    				for (Map.Entry<String, List<Float>> entry: ammessiMap.entrySet()) {
    					HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
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

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/ammessi-settembre")
    public @ResponseBody HighChartsOptions getAmmessiSettembre(@PathVariable(value = "codScuUt") String codScuUt,
                                                            String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_SCR, "4");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAndamento> listaAmmessi = alunniDAO.getAmmessiSettembre(scuola.getCodForScuApp());

    			if (listaAmmessi != null && !listaAmmessi.isEmpty()){
    				Map<String, List<Float>> ammessiMap = new LinkedHashMap<>();

    				ammessiMap.put(NAZIONE, new ArrayList<Float>());
    				ammessiMap.put(REGIONE, new ArrayList<Float>());
    				ammessiMap.put(SCUOLA, new ArrayList<Float>());

    				for (VOAndamento voAndamento: listaAmmessi) {
    					ammessiMap.get(NAZIONE).add(voAndamento.getPercentualeAmmessoNaz().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoNaz());
    					ammessiMap.get(REGIONE).add(voAndamento.getPercentualeAmmessoProv().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoProv());
    					ammessiMap.get(SCUOLA).add(voAndamento.getPercentualeAmmessoPlesso().equals(Float.valueOf("0"))?null:voAndamento.getPercentualeAmmessoPlesso());
    				}


    				for (Map.Entry<String, List<Float>> entry: ammessiMap.entrySet()) {
    					HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>(entry.getKey(), entry.getValue());
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

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/diplomati-esaminati")
    public @ResponseBody HighChartsOptions getDiplomatiEsaminati(@PathVariable(value = "codScuUt") String codScuUt,
                                                               String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ESA, "5");

            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VOAndamento voAndamento = alunniDAO.getIndicatoriAlunniIstogramma3_Risultati(scuola.getCodForScuApp());


    			if (voAndamento != null) {
    				//                List<Float> listaNazione = new ArrayList<>();
    				//                listaNazione.add(voAndamento.getPercentualeAmmessoNaz());
    				//
    				//                List<Float> listaRegione = new ArrayList<>();
    				//                listaRegione.add(voAndamento.getPercentualeAmmessoProv());
    				//
    				//                List<Float> listaScuola = new ArrayList<>();
    				//                listaScuola.add(voAndamento.getPercentualeAmmessoPlesso());
    				//
    				//                highChartsSeriesElementList.add(new HighChartsSeriesElement<>(SCUOLA, listaScuola));
    				//                highChartsSeriesElementList.add(new HighChartsSeriesElement<>(REGIONE, listaRegione));
    				//                highChartsSeriesElementList.add(new HighChartsSeriesElement<>(NAZIONE, listaNazione));

    				List<Float> lista = new ArrayList<>();
    				lista.add(voAndamento.getPercentualeAmmessoPlesso());
    				lista.add(voAndamento.getPercentualeAmmessoProv());
    				lista.add(voAndamento.getPercentualeAmmessoNaz());

    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Alunni diplomati", lista));

    			}

    		}
    		
            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/distribuzione-votazioni-esame")
    public @ResponseBody HighChartsOptions getDistribuzioneVotazioniEsami(@PathVariable(value = "codScuUt") String codScuUt,
                                                                 String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ESA, "6");

            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			List<VOEsameAlunni> listaEsameAlunni = alunniDAO.getIndicatoriAlunniTorta2(scuola.getCodForScuApp());


    			if (listaEsameAlunni != null && !listaEsameAlunni.isEmpty()){
    				List<Object[]> fasciaVoto = new ArrayList<>();
    				for (VOEsameAlunni voEsameAlunni: listaEsameAlunni) {
    					voEsameAlunni.setDescrizioneFasciaVoto(Utility.decodificaFasciaVoto(
    							scuola.getCodScuUt(),
    							voEsameAlunni.getCodiceFasciaVoto()));

    					Object[] arr = new Object[2];
    					arr[0] = voEsameAlunni.getDescrizioneFasciaVoto();
    					arr[1] = Float.parseFloat(voEsameAlunni.getNumeroAlunni());

    					fasciaVoto.add(arr);

    					HighChartsSeriesElement<Float> highChartsSeriesElement = new HighChartsSeriesElement<>(voEsameAlunni.getDescrizioneFasciaVoto(), Float.valueOf(voEsameAlunni.getNumeroAlunni()));
    					highChartsSeriesElementList.add(highChartsSeriesElement);
    				}


    			}
    		}
            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/immatricolati-universita")
    public @ResponseBody HighChartsOptions getImmatricolatiUniversita(@PathVariable(value = "codScuUt") String codScuUt,
                                                                          String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_UNI, "2");

            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VOAlunniImm voAlunniImm = null;
    			List<VOAlunniImm> listaAlunni = alunniDAO.getAlunniImmatricolati(scuola.getCodForScuApp());
    			if (listaAlunni != null && !listaAlunni.isEmpty()) {
    				voAlunniImm = listaAlunni.get(0);
    			}

    			if (voAlunniImm != null) {
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Immatricolati", Float.parseFloat(voAlunniImm.getPercentStudentiImm())));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Non Immatricolati", Float.parseFloat(voAlunniImm.getPercentStudentiNonImm())));
    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/immatricolati-universita-area-didattica")
    public @ResponseBody HighChartsOptions getImmatricolatiUniversitaAreaDidattica(@PathVariable(value = "codScuUt") String codScuUt,
                                                                      String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_UNI, "1");
            
            List<HighChartsSeriesElement<List<Float>>> seriesElementList = new ArrayList<>();
            List<String> aree = new LinkedList<String>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			List<VOAlunniImmAD> listaAlunni = alunniDAO.getAlunniImmatricolatiPerAreaDidattica(scuola.getCodForScuApp());

    			if (listaAlunni != null && !listaAlunni.isEmpty()){
    				//Map<String, Float> alunniAreaDidatticaMap = new HashMap<>();
    				List<Float> alunniAreaDidatticaList = new LinkedList<Float>();

    				for (VOAlunniImmAD voAlunniImmAD: listaAlunni) {
    					alunniAreaDidatticaList.add(Float.parseFloat(voAlunniImmAD.getPercentStudenti()));
    					aree.add(voAlunniImmAD.getDesAreaDidattica());
    				}

    				HighChartsSeriesElement<List<Float>> seriesElement = new HighChartsSeriesElement<>("Percentuale studenti", alunniAreaDidatticaList);
    				seriesElementList.add(seriesElement);
    			}
    		}
            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), seriesElementList, aree);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/rav-24c1")
    public @ResponseBody HighChartsOptions getRav24c1(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
            
            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_PAS, "1");
            
            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			List<VORav24c1> voRav24c1List = alunniDAO.getIndicatoriRav24c1(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue());


    			if (voRav24c1List != null && !voRav24c1List.isEmpty()) {
    				for (VORav24c1 voRav24c1 : voRav24c1List){
    					highChartsSeriesElementList.add(new HighChartsSeriesElement<>(voRav24c1.getArea(), toFloatSafe(voRav24c1.getPrcCon()) ));
    				}
    			}

    		}
    		
            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/rav-24c2")
    public @ResponseBody HighChartsOptions getRav24c2(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm,
                                                        String i) {

    	try {
    		VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
    		
            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_PAS, "2");
            
            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VORav24c2 voRav24c2 = alunniDAO.getIndicatoriRav24c2(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue(),i.equals("I"));

/*    			logger.debug("XXX");
    			logger.debug(ReflectionToStringBuilder.toString(voRav24c2,ToStringStyle.MULTI_LINE_STYLE));*/
    			
    			List<Float> consigliCorrispondenti = new ArrayList<Float>();
    			List<Float> consigliNonCorrispondenti = new ArrayList<Float>();

    			if (voRav24c2 != null){
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getScuConCor()));
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getRegConCor()));
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getItaConCor()));


    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getScuConNonCor()));
    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getRegConNonCor()));
    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getItaConNonCor()));

    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Consigli Corrispondenti", consigliCorrispondenti));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Consigli non Corrispondenti", consigliNonCorrispondenti));

/*    				for(Float f: consigliCorrispondenti){
    					logger.debug(f.toString());
    				}
    				for(Float f: consigliNonCorrispondenti){
    					logger.debug(f);
    				}*/
    			}
    		}
    		
    		return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return null;
    	}
    }
    
    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/rav-24c3")
    public @ResponseBody HighChartsOptions getRav24c3(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm,
                                                        String i) {

    	try {
    		VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_PAS, "3");
            
            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VORav24c2 voRav24c2 = alunniDAO.getIndicatoriRav24c3(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue(),i.equals("I"));

    			List<Float> consigliCorrispondenti = new ArrayList<Float>();
    			List<Float> consigliNonCorrispondenti = new ArrayList<Float>();

    			if (voRav24c2 != null){
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getScuConCor()));
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getRegConCor()));
    				consigliCorrispondenti.add(toFloatSafe(voRav24c2.getItaConCor()));


    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getScuConNonCor()));
    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getRegConNonCor()));
    				consigliNonCorrispondenti.add(toFloatSafe(voRav24c2.getItaConNonCor()));

    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Ammessi che hanno seguito il Consiglio Orientativo", consigliCorrispondenti));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Ammessi che non hanno seguito il Consiglio Orientativo", consigliNonCorrispondenti));

    			}

    		}

    		return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);
    		
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return null;
    	}
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/grafici/rav-24c5")
    public @ResponseBody HighChartsOptions getRav24c5(@PathVariable(value = "codScuUt") String codScuUt,
                                                        String desNomScuNorm) {

    	try {
    		VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_PAS, "4");
            
            List<HighChartsSeriesElement<List<Float>>> highChartsSeriesElementList = new ArrayList<>();

    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {

    			VORav24C5 voRav24c5 = alunniDAO.getIndicatoriRav24c5(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue());

    			if (voRav24c5 != null){

    				List<Float> sei = new ArrayList<Float>();
    				List<Float> sette = new ArrayList<Float>();
    				List<Float> otto = new ArrayList<Float>();
    				List<Float> nove = new ArrayList<Float>();
    				List<Float> dieci = new ArrayList<Float>();
    				List<Float> lode = new ArrayList<Float>();

    				sei.add(toFloatSafe(voRav24c5.getScu6())==0?null:toFloatSafe(voRav24c5.getScu6()));
    				sei.add(toFloatSafe(voRav24c5.getReg6())==0?null:toFloatSafe(voRav24c5.getReg6()));
    				sei.add(toFloatSafe(voRav24c5.getIta6())==0?null:toFloatSafe(voRav24c5.getIta6()));

    				sette.add(toFloatSafe(voRav24c5.getScu7())==0?null:toFloatSafe(voRav24c5.getScu7()));
    				sette.add(toFloatSafe(voRav24c5.getReg7())==0?null:toFloatSafe(voRav24c5.getReg7()));
    				sette.add(toFloatSafe(voRav24c5.getIta7())==0?null:toFloatSafe(voRav24c5.getIta7()));

    				otto.add(toFloatSafe(voRav24c5.getScu8())==0?null:toFloatSafe(voRav24c5.getScu8()));
    				otto.add(toFloatSafe(voRav24c5.getReg8())==0?null:toFloatSafe(voRav24c5.getReg8()));
    				otto.add(toFloatSafe(voRav24c5.getIta8())==0?null:toFloatSafe(voRav24c5.getIta8()));

    				nove.add(toFloatSafe(voRav24c5.getScu9())==0?null:toFloatSafe(voRav24c5.getScu9()));
    				nove.add(toFloatSafe(voRav24c5.getReg9())==0?null:toFloatSafe(voRav24c5.getReg9()));
    				nove.add(toFloatSafe(voRav24c5.getIta9())==0?null:toFloatSafe(voRav24c5.getIta9()));

    				dieci.add(toFloatSafe(voRav24c5.getScu10())==0?null:toFloatSafe(voRav24c5.getScu10()));
    				dieci.add(toFloatSafe(voRav24c5.getReg10())==0?null:toFloatSafe(voRav24c5.getReg10()));
    				dieci.add(toFloatSafe(voRav24c5.getIta10())==0?null:toFloatSafe(voRav24c5.getIta10()));

    				lode.add(toFloatSafe(voRav24c5.getScuL())==0?null:toFloatSafe(voRav24c5.getScuL()));
    				lode.add(toFloatSafe(voRav24c5.getRegL())==0?null:toFloatSafe(voRav24c5.getRegL()));
    				lode.add(toFloatSafe(voRav24c5.getItaL())==0?null:toFloatSafe(voRav24c5.getItaL()));


    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("Lode", lode));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("10", dieci));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("9", nove));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("8", otto));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("7", sette));
    				highChartsSeriesElementList.add(new HighChartsSeriesElement<>("6", sei));

    			}


    		}
    		
    		return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return null;
    	}
    }
    
    private Float toFloatSafe(BigDecimal in){
    	if (in != null){
    		return Float.valueOf(in.floatValue());
    	}else{
    		return null;
    	}
    }
    
}
