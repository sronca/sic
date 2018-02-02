package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.EdiliziaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEdificio;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEdilizia;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EdiliziaController {
    private Logger logger = Logger.getLogger(EdiliziaController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private EdiliziaDAO ediliziaDAO;

    @Autowired
    public EdiliziaController(AnagraficaScuolaService anagraficaScuolaService,
                              EdiliziaDAO ediliziaDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.ediliziaDAO = ediliziaDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/edilizia")
    public String getEdilizia(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
                               String t,
                               Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOEdificio> listEdifici = ediliziaDAO.getEdifici(scuola.getCodScuUt());
            String annoScolastico = "";
            if (listEdifici != null && !listEdifici.isEmpty()){
            	annoScolastico = listEdifici.get(0).getDatAnnScoRil();
            }else {
            	annoScolastico = ediliziaDAO.getMaxAnnoSco();
            }
            
    		if(annoScolastico!=null)
    			annoScolastico = annoScolastico.substring(0, 4) + "/" + annoScolastico.substring(4, 6);


            model.addAttribute("scuola", scuola);
            model.addAttribute("listEdifici", listEdifici);
            model.addAttribute("annoScolastico", annoScolastico);
            model.addAttribute("t", t);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_EDILIZIA);
            model.addAttribute("title","Edilizia - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "edilizia/edilizia-home";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/edilizia/dettaglio")
    public String getDettaglioEdilizia(@PathVariable(value = "codScuUt") String codScuUt,
                                       String desNomScuNorm,
                                       String edificio,
                                       int n,
                                       String a,
                                       Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOEdificio voEdificio = ediliziaDAO.getEdificio(edificio);       
            VOEdilizia voEdilizia = ediliziaDAO.getVOEdilizia(edificio);

            model.addAttribute("scuola", scuola);
            model.addAttribute("voEdificio", voEdificio);
            model.addAttribute("numero", n);
            model.addAttribute("voEdilizia", voEdilizia);
            model.addAttribute("annoScolastico", a);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_EDILIZIA);
            model.addAttribute("title","Dettaglio edificio - Edilizia - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            return "edilizia/edilizia-dettaglio";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
}
