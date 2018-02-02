package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.AlunniDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro1;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro2;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro3;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro4;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPercorsiAttivati;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VORav24b1;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOStruttureOspitanti;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AlunniController {
    private Logger logger = Logger.getLogger(AlunniController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private AlunniDAO alunniDAO;
    private GraficiDAO graficiDAO;

    @Autowired
    public AlunniController(AnagraficaScuolaService anagraficaScuolaService,
                            AlunniDAO alunniDAO,
                            GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.alunniDAO = alunniDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni")
    public String getAlunni(@PathVariable(value = "codScuUt") String codScuUt,
                          String desNomScuNorm,
                          Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOAnniCorsoAlunni> alunniPerAnnoCorso = new ArrayList<VOAnniCorsoAlunni>();
            List<VOClasse> listaClassi = new ArrayList<VOClasse>();

            VOGrafico tabellaAlunniPerAnnoCorso = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALU_E_CLA, "1");
            logger.debug("tabellaAlunniPerAnnoCorso : " + tabellaAlunniPerAnnoCorso.getDesTitGra() + " - " + tabellaAlunniPerAnnoCorso.getDesInfGra());
    		if( tabellaAlunniPerAnnoCorso.getCodStaPubbUff().equals("S") 
    			|| (tabellaAlunniPerAnnoCorso.getCodStaPubbUff().equals("F") && tabellaAlunniPerAnnoCorso.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) 
    			|| (tabellaAlunniPerAnnoCorso.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    			alunniPerAnnoCorso = alunniDAO.getAlunniPerAnnoCorso(scuola.getCodForScuApp());
    		}

            VOGrafico tabellaListaClassi = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALU_E_CLA, "2");
            logger.debug("tabellaListaClassi : " + tabellaListaClassi.getDesTitGra() + " - " + tabellaListaClassi.getDesInfGra());
    		if(tabellaListaClassi.getCodStaPubbUff().equals("S") ||
    				(tabellaListaClassi.getCodStaPubbUff().equals("F") && tabellaListaClassi.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(tabellaListaClassi.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    		
    			listaClassi = alunniDAO.getDistribuzioneClassiIndirizzi(scuola.getCodForScuApp());
    			
    		}

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_CLA);
            model.addAttribute("title","Alunni e classi - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("titoloTabellaAlunniPerAnnoCorso", tabellaAlunniPerAnnoCorso.getDesTitGra());
            model.addAttribute("titoloTabellaListaClassi", tabellaListaClassi.getDesTitGra());
            model.addAttribute("approfondisciTabellaAlunniPerAnnoCorso", tabellaAlunniPerAnnoCorso.getDesInfGra());
            model.addAttribute("approfondisciTabellaListaClassi", tabellaListaClassi.getDesInfGra());
            
            model.addAttribute("noteTabellaAlunniPerAnnoCorso", tabellaAlunniPerAnnoCorso.getDesNotGra());
            model.addAttribute("noteTabellaListaClassi", tabellaListaClassi.getDesNotGra());
            
            model.addAttribute("alunniPerAnnoCorso", alunniPerAnnoCorso);
            model.addAttribute("listaClassi", listaClassi);
            model.addAttribute("scuola", scuola);
            
            boolean presenteAnno6 = false;
            for (VOAnniCorsoAlunni vo : alunniPerAnnoCorso){
            	if (vo.getPerAnnCor().equals("6")){
            		presenteAnno6 = true;
            	}
            }
            model.addAttribute("presenteAnno6", presenteAnno6);
            
            String notaTabellaListaClassi = "";
            if (scuola.isSecondariaSecGrado()){
            	notaTabellaListaClassi = "Nota: il numero totale di classi pu� differire da quello della tabella precedente nel caso di classi articolate.";
            }else if (scuola.getCodTipSit().equals("MM") || scuola.getCodTipSit().equals("1M")){
            	notaTabellaListaClassi = "Nota: il numero totale di classi pu� differire da quello della tabella precedente nel caso in cui convivono l'indirizzo musicale e quello ordinario.";
            }
            model.addAttribute("notaTabellaListaClassi", notaTabellaListaClassi);
            

            return "alunni/alunni";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/ripetenti")
    public String getAlunniRipetenti(@PathVariable(value = "codScuUt") String codScuUt,
                          String desNomScuNorm,
                          Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_RIP);
            model.addAttribute("title","Ripetenti, abbandoni e trasferiti - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);

            return "alunni/ripetenti";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/risultati/scrutini")
    public String getAlunniRisultatiScrutini(@PathVariable(value = "codScuUt") String codScuUt,
                            String desNomScuNorm,
                            Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("scuola", scuola);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_SCR);
            model.addAttribute("title","Esiti scrutini - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            if (scuola.getCodTipSit().equals("MM") || scuola.getCodTipSit().equals("1M")){
            	return "alunni/scrutiniI";
            }else{
            	return "alunni/scrutini";
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/risultati/esami")
    public String getAlunniRisultatiEsami(@PathVariable(value = "codScuUt") String codScuUt,
                            String desNomScuNorm,
                            Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("scuola", scuola);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ESA);
            model.addAttribute("title","Esami di stato - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "alunni/esami";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    
    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/universita")
    public String getAlunniUniversita(@PathVariable(value = "codScuUt") String codScuUt,
                                     String desNomScuNorm,
                                     Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_RIS);
            model.addAttribute("title","Risultati a distanza - universit� - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);
            
            if (scuola != null){
            	List<VORav24b1> indicatoriRav1 = new ArrayList<VORav24b1>();
            	List<VORav24b1> indicatoriRav2 = new ArrayList<VORav24b1>();
            	
                VOGrafico tabellaIndicatoriRav1 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_UNI, "3");
        		if(tabellaIndicatoriRav1.getCodStaPubbUff().equals("S") ||
        				(tabellaIndicatoriRav1.getCodStaPubbUff().equals("F") && tabellaIndicatoriRav1.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaIndicatoriRav1.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			indicatoriRav1 = alunniDAO.getIndicatoriRav24b1(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue(), 1);
        		}

                VOGrafico tabellaIndicatoriRav2 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_UNI, "4");
        		if(tabellaIndicatoriRav2.getCodStaPubbUff().equals("S") ||
        				(tabellaIndicatoriRav2.getCodStaPubbUff().equals("F") && tabellaIndicatoriRav2.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaIndicatoriRav2.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			indicatoriRav2 = alunniDAO.getIndicatoriRav24b1(scuola.getCodScuUt(), Integer.valueOf(scuola.getDatAnnScoRil()).intValue(), 2);
        		}        		
        		
            	model.addAttribute("indicatoriRav1", indicatoriRav1);
            	model.addAttribute("titoloTabellaIndicatoriRav1", tabellaIndicatoriRav1.getDesTitGra());
            	model.addAttribute("approfondisciTabellaIndicatoriRav1", tabellaIndicatoriRav1.getDesInfGra());
            	model.addAttribute("noteTabellaIndicatoriRav1", tabellaIndicatoriRav1.getDesNotGra());

            	model.addAttribute("indicatoriRav2", indicatoriRav2);
            	model.addAttribute("titoloTabellaIndicatoriRav2", tabellaIndicatoriRav2.getDesTitGra());
            	model.addAttribute("approfondisciTabellaIndicatoriRav2", tabellaIndicatoriRav2.getDesInfGra());
            	model.addAttribute("noteTabellaIndicatoriRav2", tabellaIndicatoriRav2.getDesNotGra());

            	
            	if (indicatoriRav1 != null && !indicatoriRav1.isEmpty()){
            		String annoScolastico = indicatoriRav1.get(0).getAnnoScolastico();
            		int anno = Integer.valueOf(annoScolastico.substring(0,4)).intValue();
            		model.addAttribute("annoRif", String.valueOf(anno) + "/" + String.valueOf(anno+1));
            		model.addAttribute("annoSucc", String.valueOf(anno+1) + "/" + String.valueOf(anno+2));
            	}
            }

            return "alunni/universita";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/lavoro")
    public String getAlunniLavoro(@PathVariable(value = "codScuUt") String codScuUt,
                                     String desNomScuNorm,
                                     Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_LAV);
            model.addAttribute("title","Risultati a distanza - lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);
            
            if (scuola != null){
            	List<VOLavoro1> datiLavoro1 = new ArrayList<VOLavoro1>();
            	List<VOLavoro2> datiLavoro2 = new ArrayList<VOLavoro2>();
            	List<VOLavoro3> datiLavoro3 = new ArrayList<VOLavoro3>();
            	List<VOLavoro4> datiLavoro4 = new ArrayList<VOLavoro4>();
            	
                VOGrafico tabellaLavoro1 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_LAV, "1");
        		if(tabellaLavoro1.getCodStaPubbUff().equals("S") ||
        				(tabellaLavoro1.getCodStaPubbUff().equals("F") && tabellaLavoro1.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaLavoro1.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			datiLavoro1 = alunniDAO.getDatiLavoro1(scuola.getCodForScuApp());
        		}
        		
                VOGrafico tabellaLavoro2 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_LAV, "2");
        		if(tabellaLavoro2.getCodStaPubbUff().equals("S") ||
        				(tabellaLavoro2.getCodStaPubbUff().equals("F") && tabellaLavoro2.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaLavoro2.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			datiLavoro2 = alunniDAO.getDatiLavoro2(scuola.getCodForScuApp());
        		}
        		
                VOGrafico tabellaLavoro3 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_LAV, "3");
        		if(tabellaLavoro3.getCodStaPubbUff().equals("S") ||
        				(tabellaLavoro3.getCodStaPubbUff().equals("F") && tabellaLavoro3.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaLavoro3.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			datiLavoro3 = alunniDAO.getDatiLavoro3(scuola.getCodForScuApp());
        		}
        		
                VOGrafico tabellaLavoro4 = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_RIS_LAV, "4");
        		if(tabellaLavoro4.getCodStaPubbUff().equals("S") ||
        				(tabellaLavoro4.getCodStaPubbUff().equals("F") && tabellaLavoro4.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
        				(tabellaLavoro4.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
        			
        			datiLavoro4 = alunniDAO.getDatiLavoro4(scuola.getCodForScuApp());
        		}         		

            	
            	
            	model.addAttribute("datiLavoro1", datiLavoro1);
            	model.addAttribute("titoloTabellaLavoro1", tabellaLavoro1.getDesTitGra());
            	model.addAttribute("approfondisciTabellaLavoro1", tabellaLavoro1.getDesInfGra());
            	model.addAttribute("noteTabellaLavoro1", tabellaLavoro1.getDesNotGra());
            	
            	model.addAttribute("datiLavoro2", datiLavoro2);
            	model.addAttribute("titoloTabellaLavoro2", tabellaLavoro2.getDesTitGra());
            	model.addAttribute("approfondisciTabellaLavoro2", tabellaLavoro2.getDesInfGra());
            	model.addAttribute("noteTabellaLavoro2", tabellaLavoro2.getDesNotGra());
            	
            	model.addAttribute("datiLavoro3", datiLavoro3);
            	model.addAttribute("titoloTabellaLavoro3", tabellaLavoro3.getDesTitGra());
            	model.addAttribute("approfondisciTabellaLavoro3", tabellaLavoro3.getDesInfGra());
            	model.addAttribute("noteTabellaLavoro3", tabellaLavoro3.getDesNotGra()); 
            	
            	model.addAttribute("datiLavoro4", datiLavoro4);
            	model.addAttribute("titoloTabellaLavoro4", tabellaLavoro4.getDesTitGra());
            	model.addAttribute("approfondisciTabellaLavoro4", tabellaLavoro4.getDesInfGra());
            	model.addAttribute("noteTabellaLavoro4", tabellaLavoro4.getDesNotGra()); 
            	
            	
            }

            return "alunni/lavoro";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/passaggioII")
    public String getAlunniPassaggioII(@PathVariable(value = "codScuUt") String codScuUt,
                                     String desNomScuNorm,
                                     Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_PAS_II);
            model.addAttribute("title","Passaggio dal I al II ciclo - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);
            
            return "alunni/passaggioII";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/passaggioI")
    public String getAlunniPassaggioI(@PathVariable(value = "codScuUt") String codScuUt,
                                     String desNomScuNorm,
                                     Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_PAS_I);
            model.addAttribute("title","Passaggio dal I al II ciclo - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);
            
            return "alunni/passaggioI";

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

//    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro")
//    public String getAlunniScuolalavoro(@PathVariable(value = "codScuUt") String codScuUt,
//                          String desNomScuNorm,
//                          Model model) {
//
//        try {
//            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
//
//            List<VOAlternanzaScuolaLavoro> alternanzaScuolaLavoro = alunniDAO.getDatiAlternanzaScuolaLavoro(scuola.getCodForScuApp());
//
//            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
//            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ALT);
//            model.addAttribute("title","Alternanza scuola lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
//
//            model.addAttribute("alternanzaScuolaLavoro", alternanzaScuolaLavoro);
//            model.addAttribute("scuola", scuola);
//
//            return "alunni/scuolalavoro";
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return "error";
//        }
//    }
   
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro","/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/"})
    public ModelAndView getAlunniScuolaLavoroStruttureRedirect(
   		 @PathVariable(value = "codScuUt") String $codScuUt
   		,@PathVariable(value = "desNomScuNorm")String $desNomScuNorm){
    	logger.warn("getAlunniScuolaLavoroStruttureRedirect");
    	String redirectUrl = "/istituti/" + $codScuUt + "/" +$desNomScuNorm + "/alunni/scuolalavoro/strutture/";
    	RedirectView redirectView = new RedirectView(redirectUrl, true);
    	redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    	return new ModelAndView(redirectView);
    }
 
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/strutture","/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/strutture/"})
    public String getAlunniScuolaLavoroStrutture(
    		 @PathVariable(value = "codScuUt") String $codScuUt
    		,@PathVariable(value = "desNomScuNorm")String desNomScuNorm
    		,Model model) {
    	logger.info("getAlunniScuolaLavoroStrutture(" + $codScuUt + ")...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola($codScuUt);
            VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV, "1");
            List<VOStruttureOspitanti> listaStrutture = null;
            if(anagraficaScuolaService.isGraficoVisibile(infoGrafico, scuola)){
	            Integer anno = infoGrafico.getAnnScoRif();
	            if(anno == null || anno == 0){
	            	anno = Calendar.getInstance().get(Calendar.YEAR);
	            	logger.warn("anno non definito su tabella grafico, utilizzo anno corrente: " + anno);
	            }
	            listaStrutture = alunniDAO.getStruttureOspitanti($codScuUt, anno, null);
            }
            else{
            	logger.info("grafico [" + Constants.SCHEDA_GRA_ALU + "|" + Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV + "|1] non visibile per la scuola " + $codScuUt );
            }
            model.addAttribute("scuola", scuola);
            model.addAttribute("infoGrafico", infoGrafico);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ALT);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_ALU_ALT_STR);
            model.addAttribute("title","Alternanza scuola lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            model.addAttribute("listaStrutture", listaStrutture);
            return "alunni/scuolalavoroStrutture";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    
    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/strutture/{prgStrScu}")
    public String getAlunniScuolaLavoroStruttureDettaglio(
    		 @PathVariable(value = "codScuUt") String $codScuUt
    		,@PathVariable(value = "desNomScuNorm")String desNomScuNorm
    		,@PathVariable(value = "prgStrScu")Long $prgStrScu
    		,Model model) {
    	logger.info("getAlunniScuolaLavoroStruttureDettaglio(" + $codScuUt + ")...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola($codScuUt);
            VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV, "3");
            VOStruttureOspitanti struttura = null;
            List<VOPercorsiAttivati> percorsiStruttura = null;
            if(anagraficaScuolaService.isGraficoVisibile(infoGrafico, scuola)){
            	Integer anno = infoGrafico.getAnnScoRif();
	            if(anno == null || anno == 0){
	            	anno = Calendar.getInstance().get(Calendar.YEAR);
	            	logger.warn("anno non definito su tabella grafico, utilizzo anno corrente: " + anno);
	            }
	            List<VOStruttureOspitanti> listaStrutture = alunniDAO.getStruttureOspitanti($codScuUt, anno, $prgStrScu);
	            if(listaStrutture != null && !listaStrutture.isEmpty()){
	            	struttura = listaStrutture.get(0);
	            	percorsiStruttura = alunniDAO.getPercorsiStruttura($codScuUt, anno, $prgStrScu);
	            }
            }
            else{
            	logger.info("grafico [" + Constants.SCHEDA_GRA_ALU + "|" + Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV + "|3] non visibile per la scuola " + $codScuUt );
            }
            model.addAttribute("scuola", scuola);
            model.addAttribute("infoGrafico", infoGrafico);
            model.addAttribute("struttura", struttura);
            model.addAttribute("percorsiStruttura", percorsiStruttura);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ALT);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_ALU_ALT_STR);
            model.addAttribute("title","Alternanza scuola lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            return "alunni/scuolalavoroStruttureDettaglio";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/percorsi","/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/percorsi/"})
    public String getAlunniScuolalavoroPercorsi(
    		 @PathVariable(value = "codScuUt") String $codScuUt
    		,@PathVariable(value = "desNomScuNorm")String desNomScuNorm
    		,Model model) {
    	logger.info("getAlunniScuolalavoroPercorsi(" + $codScuUt + ")...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola($codScuUt);
            VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV, "2");
            List<VOPercorsiAttivati> listaPercorsi = null;
            if(anagraficaScuolaService.isGraficoVisibile(infoGrafico, scuola)){
	            Integer anno = infoGrafico.getAnnScoRif();
	            if(anno == null || anno == 0){
	            	anno = Calendar.getInstance().get(Calendar.YEAR);
	            	logger.warn("anno non definito su tabella grafico, utilizzo anno corrente: " + anno);
	            }
	            listaPercorsi = alunniDAO.getPercorsiAttivati($codScuUt, anno, null);
            }
            else{
            	logger.info("grafico [" + Constants.SCHEDA_GRA_ALU + "|" + Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV + "|2] non visibile per la scuola " + $codScuUt );
            }
            model.addAttribute("scuola", scuola);
            model.addAttribute("infoGrafico", infoGrafico);
            model.addAttribute("listaPercorsi", listaPercorsi);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ALT);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_ALU_ALT_PER);
			model.addAttribute("title","Alternanza scuola lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            return "alunni/scuolalavoroPercorsi";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    
    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/alunni/scuolalavoro/percorsi/{prgPerScu}")
    public String getAlunniScuolalavoroPercorsiDettaglio(
    		 @PathVariable(value = "codScuUt") String $codScuUt
    		,@PathVariable(value = "desNomScuNorm")String $desNomScuNorm
    		,@PathVariable(value = "prgPerScu")Long $prgPerScu
    		,Model model) {
    	logger.info("getAlunniScuolalavoroPercorsiDettaglio(" + $codScuUt + "," + $prgPerScu + ")...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola($codScuUt);
            VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV, "4");
            VOPercorsiAttivati percorso = null;
            List<VOStruttureOspitanti> struttureOspitanti = null;
            if(anagraficaScuolaService.isGraficoVisibile(infoGrafico, scuola)){
	            Integer anno = infoGrafico.getAnnScoRif();
	            if(anno == null || anno == 0){
	            	anno = Calendar.getInstance().get(Calendar.YEAR);
	            	logger.warn("anno non definito su tabella grafico, utilizzo anno corrente: " + anno);
	            }
	            List<VOPercorsiAttivati> listaPercorsi = alunniDAO.getPercorsiAttivati($codScuUt, anno, $prgPerScu);
	            if(listaPercorsi != null && !listaPercorsi.isEmpty()){
	            	percorso = listaPercorsi.get(0);
	            	struttureOspitanti = alunniDAO.getStruttureOspitantiPercorso($codScuUt, anno, $prgPerScu);
	            }
            }
            else{
            	logger.info("grafico [" + Constants.SCHEDA_GRA_ALU + "|" + Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV + "|4] non visibile per la scuola " + $codScuUt );
            }
            model.addAttribute("scuola", scuola);
            model.addAttribute("infoGrafico", infoGrafico);
            model.addAttribute("percorso", percorso);
            model.addAttribute("struttureOspitanti", struttureOspitanti);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_ALU_ALT);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_ALU_ALT_PER);
			model.addAttribute("title","Alternanza scuola lavoro - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            return "alunni/scuolalavoroPercorsiDettaglio";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/alunni/frequentanti","/istituti/{codScuUt}/{desNomScuNorm}/alunni/frequentanti/"})
    public String getAlunniFrequentanti(
    		 @PathVariable(value = "codScuUt") String $codScuUt
    		,String desNomScuNorm
    		,Model model) {
    	logger.info("getAlunniFrequentanti(" + $codScuUt + ")...");
        try {
        	VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola($codScuUt);
            List<VOAnniCorsoAlunni> alunniPerAnnoCorso = new ArrayList<VOAnniCorsoAlunni>();
            VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_STU_FRQ, "1");
            logger.info("tabellaAlunniPerAnnoCorso : " + infoGrafico.getDesTitGra() + " - " + infoGrafico.getDesInfGra());
    		if( infoGrafico.getCodStaPubbUff().equals("S") 
    			|| (infoGrafico.getCodStaPubbUff().equals("F") && infoGrafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) 
    			|| (infoGrafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    			alunniPerAnnoCorso = alunniDAO.getAlunniPerAnnoCorsoScuoleSerali(scuola.getCodForScuApp());
    		}
            else{
            	logger.info("grafico [" + Constants.SCHEDA_GRA_ALU + "|" + Constants.SEZIONE_GRA_STU_FRQ + "|1] non visibile per la scuola " + $codScuUt );
            }
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_ALUNNI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_STU_FRQ);
            model.addAttribute("title","Alunni e classi - Alunni - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            model.addAttribute("titoloTabellaAlunniPerAnnoCorso", infoGrafico.getDesTitGra());
            model.addAttribute("approfondisciTabellaAlunniPerAnnoCorso", infoGrafico.getDesInfGra());
            model.addAttribute("noteTabellaAlunniPerAnnoCorso", infoGrafico.getDesNotGra());
            model.addAttribute("alunniPerAnnoCorso", alunniPerAnnoCorso);
            model.addAttribute("scuola", scuola);
//            boolean presenteAnno6 = false;
//            for (VOAnniCorsoAlunni vo : alunniPerAnnoCorso){
//            	if (vo.getPerAnnCor().equals("6")){
//            		presenteAnno6 = true;
//            	}
//            }
//            model.addAttribute("presenteAnno6", presenteAnno6);
            return "alunni/frequentanti";
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
}
