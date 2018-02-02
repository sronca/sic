package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.PersonaleDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPersonale;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonaleController {
    private Logger logger = Logger.getLogger(PersonaleController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private PersonaleDAO personaleDAO;
    private GraficiDAO graficiDAO;

    @Autowired
    public PersonaleController(AnagraficaScuolaService anagraficaScuolaService,
                               PersonaleDAO personaleDAO,
                               GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.personaleDAO = personaleDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale")
    public String getPersonale(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOPersonale doc = null;

            VOPersonale ata = null;
            
            VOGrafico tabellaDoc = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_DOC_ATA, "1");
    		if(tabellaDoc.getCodStaPubbUff().equals("S") ||
    				(tabellaDoc.getCodStaPubbUff().equals("F") && tabellaDoc.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(tabellaDoc.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    			
    			doc = personaleDAO.getIndicatoriDocentiTabelle(scuola.getCodForScuApp());
    		}

            VOGrafico tabellaAta = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_PER, Constants.SEZIONE_GRA_PER_DOC_ATA, "2");
    		if(tabellaAta.getCodStaPubbUff().equals("S") ||
    				(tabellaAta.getCodStaPubbUff().equals("F") && tabellaAta.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(tabellaAta.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    			
    			ata = personaleDAO.getIndicatoriATATabelle(scuola.getCodForScuApp());
    		}

            model.addAttribute("doc", doc);
        	model.addAttribute("titoloTabellaDoc", tabellaDoc.getDesTitGra());
        	model.addAttribute("approfondisciTabellaDoc", tabellaDoc.getDesInfGra());
        	model.addAttribute("noteTabellaDoc", tabellaDoc.getDesNotGra());

            model.addAttribute("ata", ata);
        	model.addAttribute("titoloTabellaAta", tabellaAta.getDesTitGra());
        	model.addAttribute("approfondisciTabellaAta", tabellaAta.getDesInfGra());
        	model.addAttribute("noteTabellaAta", tabellaAta.getDesNotGra());

            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_PERSONALE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_PER_PER);
            model.addAttribute("title","Personale docente e ATA - Personale - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "personale/personale";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/mobilita")
    public String getPersonaleMobilita(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_PERSONALE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_PER_MOB);
            model.addAttribute("title","Mobilità - Personale - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "personale/mobilita";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/personale/assenze")
    public String getPersonaleAssenze(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_PERSONALE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_PER_ASS);
            model.addAttribute("title","Assenze - Personale - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "personale/assenze";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
}
