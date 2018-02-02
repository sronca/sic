package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.DocumentiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiInvalsiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.TabelleInvalsiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ValutazioneController {
    private Logger logger = Logger.getLogger(ValutazioneController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private TabelleInvalsiDAO tabelleInvalsiDAO;
    private GraficiInvalsiDAO graficiInvalsiDAO;
    private DocumentiDAO documentiDAO;

    private static final String DEFAULT_IMG_PATH = "/img/grafico_dati_non_disponibili.gif";

    @Autowired
    public ValutazioneController(AnagraficaScuolaService anagraficaScuolaService,
                                 TabelleInvalsiDAO tabelleInvalsiDAO,
                                 GraficiInvalsiDAO graficiInvalsiDAO,
                                 DocumentiDAO documentiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.tabelleInvalsiDAO = tabelleInvalsiDAO;
        this.graficiInvalsiDAO = graficiInvalsiDAO;
        this.documentiDAO = documentiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/valutazione")
    public String getValutazione(@PathVariable(value = "codScuUt") String codScuUt,
                                 String desNomScuNorm,
                                 HttpServletResponse response,
                                 Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

/*            model.addAttribute("listaDocumentiValutazione",
                    documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                            scuola.getDatAnnScoRil(), Constants.DOCUMENTO_VALUTAZIONE));
*/
            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_VALUTAZIONE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_VAL_RAV);
            model.addAttribute("server_rav", anagraficaScuolaService.getUrlHomePageIol("ATT_LINK_RAV"));
            
            model.addAttribute("title","Rapporto di autovalutazione - Autovalutazione - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            
            response.setHeader("Access-Control-Allow-Origin", "*");

            return "valutazione/ilrav";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/valutazione/sintesi")
    public String getValutazioneSintesi(@PathVariable(value = "codScuUt") String codScuUt,
                                 String desNomScuNorm,
                                 HttpServletResponse response,
                                 Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

/*            model.addAttribute("listaDocumentiValutazione",
                    documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                            scuola.getDatAnnScoRil(), Constants.DOCUMENTO_VALUTAZIONE));
*/
            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_VALUTAZIONE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_VAL_SIN);
            model.addAttribute("server_rav", anagraficaScuolaService.getUrlHomePageIol("ATT_LINK_RAV"));
            
            model.addAttribute("title","Naviga il RAV - Autovalutazione - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            
            response.setHeader("Access-Control-Allow-Origin", "*");

            return "valutazione/sintesi";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/valutazione/naviga")
    public String getValutazioneNaviga(@PathVariable(value = "codScuUt") String codScuUt,
                                 String desNomScuNorm,
                                 HttpServletResponse response,
                                 Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

/*            model.addAttribute("listaDocumentiValutazione",
                    documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                            scuola.getDatAnnScoRil(), Constants.DOCUMENTO_VALUTAZIONE));
*/
            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_VALUTAZIONE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_VAL_NAV);
            model.addAttribute("server_rav", anagraficaScuolaService.getUrlHomePageIol("ATT_LINK_RAV"));
            
            model.addAttribute("title","Indicatori - Autovalutazione - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            
            response.setHeader("Access-Control-Allow-Origin", "*");

            return "valutazione/naviga";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    
    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/valutazione/documenti")
    public String getValutazioneDocumenti(@PathVariable(value = "codScuUt") String codScuUt,
                                 String desNomScuNorm,
                                 HttpServletResponse response,
                                 Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

/*            model.addAttribute("listaDocumentiValutazione",
                    documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                            scuola.getDatAnnScoRil(), Constants.DOCUMENTO_VALUTAZIONE));
*/
            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_VALUTAZIONE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_VAL_DOC);
            model.addAttribute("server_rav", anagraficaScuolaService.getUrlHomePageIol("ATT_LINK_RAV"));
            
            model.addAttribute("title","RAV in formato PDF - Autovalutazione - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            
            response.setHeader("Access-Control-Allow-Origin", "*");

            return "valutazione/documenti";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/valutazione/indicatori")
    public String getValutazioneIndicatori(@PathVariable(value = "codScuUt") String codScuUt,
                                 String desNomScuNorm,
                                 HttpServletResponse response,
                                 Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

/*            model.addAttribute("listaDocumentiValutazione",
                    documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                            scuola.getDatAnnScoRil(), Constants.DOCUMENTO_VALUTAZIONE));
*/
            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_VALUTAZIONE);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_VAL_IND);
            model.addAttribute("server_rav", anagraficaScuolaService.getUrlHomePageIol("ATT_LINK_RAV"));
            
            model.addAttribute("title","Autovalutazione - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            
            response.setHeader("Access-Control-Allow-Origin", "*");

            return "valutazione/indicatori";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    
}
