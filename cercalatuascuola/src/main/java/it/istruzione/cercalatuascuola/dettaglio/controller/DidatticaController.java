package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.DidatticaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.DocumentiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLibro;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPon;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

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
public class DidatticaController {
    private Logger logger = Logger.getLogger(DidatticaController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private DidatticaDAO didatticaDAO;
    private DocumentiDAO documentiDAO;

    @Autowired
    public DidatticaController(AnagraficaScuolaService anagraficaScuolaService,
                               DidatticaDAO didatticaDAO,
                               DocumentiDAO documentiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.didatticaDAO = didatticaDAO;
        this.documentiDAO = documentiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica")
    public String getDidattica(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("listaDocumentiDidattica", documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                    scuola.getDatAnnScoRil(), Constants.DOCUMENTO_DIDATTICA));

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_DOC);
            model.addAttribute("title","Documenti - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "didattica/didattica";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

/*    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/attivita")
    public String getDidatticaAttivita(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            if(scuola.isIstitutoPrincipale()) {
                model.addAttribute("listaStages", didatticaDAO.getListaAttivitaIstPrinc(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_STAGE));

                model.addAttribute("listaCertificazioni", didatticaDAO.getListaAttivitaIstPrinc(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_CERTIFICAZIONE));

            } else {
                model.addAttribute("listaStages", didatticaDAO.getListaAttivita(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_STAGE));

                model.addAttribute("listaCertificazioni", didatticaDAO.getListaAttivita(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_CERTIFICAZIONE));
            }

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_ATT);

            return "didattica/attivita";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }*/

/*    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/iscrizioni")
    public String getDidatticaIscrizioni(@PathVariable(value = "codScuUt") String codScuUt,
                                       String desNomScuNorm,
                                       Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            model.addAttribute("listaDocumentiIscrizione", documentiDAO.getDocumentiScuola(scuola.getCodScuUtPri(),
                    scuola.getDatAnnScoRil(), Constants.DOCUMENTO_ISCRIZIONE));

            model.addAttribute("scuola", scuola);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);

            return "didattica/iscrizioni";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }*/

//   @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/libri")
//    public String getDidatticaLibri(@PathVariable(value = "codScuUt") String codScuUt,
//                                         String desNomScuNorm,
//                                         Model model) {
//
//        try {
//            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
//
//            String annoRifAnnoProssimo;
//            if(Utility.isAnnoScolasticoAttuale()) {
//                annoRifAnnoProssimo = Utility.annoAccademico();
//            } else {
//                annoRifAnnoProssimo = Utility.annoAccademicoSuccessivo(scuola.getDatAnnScoRil());
//            }
//
//            String codUtAnnoProssimo = didatticaDAO.getCodiceMeccanograficoAnnoSuccessivo(scuola.getCodForScuApp(),
//                    annoRifAnnoProssimo);
//
//            if (codUtAnnoProssimo == null || "".equals(codUtAnnoProssimo.trim())){
//                codUtAnnoProssimo = scuola.getCodScuUt();
//            }
//
//            List<VOLibro> listaLibri = didatticaDAO.getListaLibri(codUtAnnoProssimo, annoRifAnnoProssimo);
//            model.addAttribute("listaLibri", listaLibri);
//
//            model.addAttribute("scuola", scuola);
//            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
//            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
//            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_LIB);
//            model.addAttribute("title","Libri di testo - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
//
//            return "didattica/libri";
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return "error";
//        }
//    }
     
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/didattica/libri","/istituti/{codScuUt}/{desNomScuNorm}/didattica/libri/"})
    public ModelAndView getDidatticaLibriRedirect(
   		 @PathVariable(value = "codScuUt") String $codScuUt
   		,@PathVariable(value = "desNomScuNorm")String $desNomScuNorm){
    	logger.warn("getDidatticaLibriRedirect");
    	String redirectUrl = "/istituti/" + $codScuUt + "/" +$desNomScuNorm + "/didattica/libriAC/";
    	RedirectView redirectView = new RedirectView(redirectUrl, true);
    	redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    	System.out.println("redirecting to: " + redirectView.getUrl());
    	return new ModelAndView(redirectView);
    }
    
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/didattica/libriAC","/istituti/{codScuUt}/{desNomScuNorm}/didattica/libriAC/"})
    public String getDidatticaLibriAC(
    		 @PathVariable(value = "codScuUt") String codScuUt
    		,String desNomScuNorm
    		,Model model) {
    	// libri anno corrente
    	logger.info("getDidatticaLibriAC('" + codScuUt + "','" + desNomScuNorm + "')...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
            String annoRif = Utility.annoAccademico();
            List<VOLibro> listaLibri = didatticaDAO.getListaLibri(scuola.getCodScuUt(), annoRif);
            model.addAttribute("annoScolastico", "in corso");
            model.addAttribute("listaLibri", listaLibri);
            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_LIB);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_DID_LIB_AC);
            model.addAttribute("title","Libri di testo - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            return "didattica/libri";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
    
    @RequestMapping(value = {"/istituti/{codScuUt}/{desNomScuNorm}/didattica/libriAS","/istituti/{codScuUt}/{desNomScuNorm}/didattica/libriAS/"})
    public String getDidatticaLibriAS(
    		@PathVariable(value = "codScuUt") String codScuUt
    		,
                                         String desNomScuNorm,
                                         Model model) {
    	// libri anno successivo
    	logger.info("getDidatticaLibriAS('" + codScuUt + "','" + desNomScuNorm + "')...");
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
            String annoRif = Utility.annoAccademicoSuccessivo(scuola.getDatAnnScoRil());
            String codUtAnnoProssimo = didatticaDAO.getCodiceMeccanograficoAnnoSuccessivo(scuola.getCodForScuApp(),annoRif);
            if (codUtAnnoProssimo == null || "".equals(codUtAnnoProssimo.trim())){
                codUtAnnoProssimo = scuola.getCodScuUt();
            }
            List<VOLibro> listaLibri = didatticaDAO.getListaLibri(codUtAnnoProssimo, annoRif);
            model.addAttribute("annoScolastico", Utility.annoScolasticoSuccessivo());
            model.addAttribute("listaLibri", listaLibri);
            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_LIB);
            model.addAttribute("subSubFunctionalityOn", Constants.SUB_SUB_FUNCTIONALITY_DID_LIB_AS);
            model.addAttribute("title","Libri di testo - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
            return "didattica/libri";
        } 
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/progetti-fesr")
    public String getDidatticaProgettiFesr(@PathVariable(value = "codScuUt") String codScuUt,
                                         String desNomScuNorm,
                                         Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOPon> listaProgPonFesr = didatticaDAO.getListaProgPonFesr(scuola.getCodScuUt());
            model.addAttribute("lista", listaProgPonFesr);

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_PRO_FESR);
            model.addAttribute("title","Progetti FESR - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "didattica/progettazione-pon-fesr";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/progetti-fse")
    public String getDidatticaProgettiFse(@PathVariable(value = "codScuUt") String codScuUt,
                                           String desNomScuNorm,
                                           Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOPon> listaProgPonFse = didatticaDAO.getListaProgPonFse(scuola.getCodScuUt());
            model.addAttribute("lista", listaProgPonFse);

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_PRO_FSE);
            model.addAttribute("title","Progetti FSE - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "didattica/progettazione-pon-fse";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/progetti-incorso-fesr")
    public String getDidatticaProgettiInCorsoFesr(@PathVariable(value = "codScuUt") String codScuUt,
                                           String desNomScuNorm,
                                           Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOPon> listaProgPonInCorsoFesr = didatticaDAO.getListaProgPonInCorsoFesr(scuola.getCodScuUt());
            model.addAttribute("lista", listaProgPonInCorsoFesr);

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_PRO_INC_FESR);
            model.addAttribute("title","Progetti FESR in corso - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "didattica/progettazione-pon-fesr-incorso";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/didattica/progetti-incorso-fse")
    public String getDidatticaProgettiInCorsoFse(@PathVariable(value = "codScuUt") String codScuUt,
                                          String desNomScuNorm,
                                          Model model) {

        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOPon> listaProgPonInCorsoFse = didatticaDAO.getListaProgPonInCorsoFse(scuola.getCodScuUt());
            model.addAttribute("lista", listaProgPonInCorsoFse);

            model.addAttribute("scuola", scuola);
            model.addAttribute("mostraLinkMenuLibriDiTesto", anagraficaScuolaService.getMostraLibriDiTesto(scuola));
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_DIDATTICA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_DID_PRO_INC_FSE);
            model.addAttribute("title","Progetti FSE in corso - Didattica - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "didattica/progettazione-pon-fse-incorso";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
}
