package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.AnagraficaScuoleDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.DidatticaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.ServiziDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttrezzatureMultimediali;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologiaServizio;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ServiziController {
    private Logger logger = Logger.getLogger(ServiziController.class);

    private AnagraficaScuolaService anagraficaScuolaService;
    private ServiziDAO serviziDAO;
    private AnagraficaScuoleDAO anagraficaScuoleDAO;
    private DidatticaDAO didatticaDAO;

    @Autowired
    public ServiziController(AnagraficaScuolaService anagraficaScuolaService,
                             AnagraficaScuoleDAO anagraficaScuoleDAO,
                             DidatticaDAO didatticaDAO,
                             ServiziDAO serviziDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.anagraficaScuoleDAO = anagraficaScuoleDAO;
        this.serviziDAO = serviziDAO;
        this.didatticaDAO = didatticaDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/servizi")
    public String getServizi(@PathVariable(value = "codScuUt") String codScuUt,
                             String desNomScuNorm,
                             Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            if(scuola.isIstitutoPrincipale()) {
                VOAnagraficaScuola voScuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUt(), scuola.getDatAnnScoRil());
                if(voScuola != null) {
                    model.addAttribute("flgPlesso", "true");
                } else {
                    model.addAttribute("flgPlesso", "false");
                }
            } else {
                model.addAttribute("flgPlesso", "true");
            }

            /**
             * SERVIZI WEB
             * **/
            List<VOTipologiaServizio> listaServiziWeb = serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_WEB,
                    scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil());
            model.addAttribute("listaServiziWeb", listaServiziWeb);
            
            /**
             * ATTREZZATURE A SUPPORTO
             * 
             * **/
            
            List<VOTipologiaServizio> serviziSupportoList = serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_ATTREZZATURE_A_SUPPORTO,
                    scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil());
            model.addAttribute("listaServiziSupporto", serviziSupportoList);

            model.addAttribute("codStaPubbPC", serviziDAO.getVisibilitaGrafici("1"));
            model.addAttribute("codStaPubbLIM", serviziDAO.getVisibilitaGrafici("2"));
            model.addAttribute("codStaPubbAule", serviziDAO.getVisibilitaGrafici("3"));
            
            
            /**
             * ALTRI SERVIZI
             * 
             * **/
            
            List<VOTipologiaServizio> altriServiziList = serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_ATTIVITA_E_ALTRI,
                    scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil());

            model.addAttribute("listaAltriServizi", altriServiziList);

            
            /**
             * ATTREZZATURE MULTIMEDIALI
             * 
             * **/
            VOAttrezzatureMultimediali voAttrezzatureMultimediali = serviziDAO.getAttrezzatureMultimedialiNew(scuola.getCodForScuApp());
            if(voAttrezzatureMultimediali != null) {
                model.addAttribute("compLab", voAttrezzatureMultimediali.getCompLab());
                model.addAttribute("compBib", voAttrezzatureMultimediali.getCompBib());
                model.addAttribute("limLab", voAttrezzatureMultimediali.getLimLab());
                model.addAttribute("limBib", voAttrezzatureMultimediali.getLimBib());
                model.addAttribute("proiettLab", voAttrezzatureMultimediali.getProiettLab());
                model.addAttribute("proiettBib", voAttrezzatureMultimediali.getProiettBib());
                model.addAttribute("compScu", voAttrezzatureMultimediali.getCompScu());
                model.addAttribute("dispMobScu", voAttrezzatureMultimediali.getDispMobScu());
                model.addAttribute("limScu", voAttrezzatureMultimediali.getLimScu());
                model.addAttribute("proiettScu", voAttrezzatureMultimediali.getProiettScu());
                model.addAttribute("numAuleDid", voAttrezzatureMultimediali.getNumAuleDid());
                model.addAttribute("percAuleConn", voAttrezzatureMultimediali.getPercAuleConn());
                model.addAttribute("flgDati", "true");
            } else {
                model.addAttribute("flgDati", "false");
            }

            //Gestione titoli
            model.addAttribute("titoloPC", serviziDAO.getTitoloGrafici("1"));
            model.addAttribute("descrizionePC", serviziDAO.getDescrizioneGrafici("1"));

            model.addAttribute("titoloLIM", serviziDAO.getTitoloGrafici("2"));
            model.addAttribute("descrizioneLIM", serviziDAO.getDescrizioneGrafici("2"));

            model.addAttribute("titoloAule", serviziDAO.getTitoloGrafici("3"));
            model.addAttribute("descrizioneAule", serviziDAO.getDescrizioneGrafici("3"));
            
            
            
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_SERVIZI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_SER_SER);
            model.addAttribute("title","Servizi - Servizi e attività - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);

            return "servizi/servizi";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

/*    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/servizi/attrezzature")
    public String getAttrezzature(@PathVariable(value = "codScuUt") String codScuUt,
                                String desNomScuNorm,
                                Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            if(scuola.isIstitutoPrincipale()) {
                VOAnagraficaScuola voScuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUt(), scuola.getDatAnnScoRil());
                if(voScuola != null) {
                    model.addAttribute("flgPlesso", "true");
                } else {
                    model.addAttribute("flgPlesso", "false");
                }
            } else {
                model.addAttribute("flgPlesso", "true");
            }


            List<VOTipologiaServizio> serviziSupportoList = serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_ATTREZZATURE_A_SUPPORTO,
                    scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil());


            model.addAttribute("listaServiziSupporto", serviziSupportoList);

            model.addAttribute("scuola", scuola);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_SERVIZI);

            return "servizi/schede_servizi_attrezzature";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }*/

/*    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/servizi/attrezzature-multimediali")
    public String getAttrezzatureMultimediali(@PathVariable(value = "codScuUt") String codScuUt,
                                  String desNomScuNorm,
                                  Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            if(scuola.isIstitutoPrincipale()) {
                VOAnagraficaScuola voScuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUt(), scuola.getDatAnnScoRil());
                if(voScuola != null) {
                    model.addAttribute("flgPlesso", "true");
                } else {
                    model.addAttribute("flgPlesso", "false");
                }
            } else {
                model.addAttribute("flgPlesso", "true");
            }

            VOAttrezzatureMultimediali voAttrezzatureMultimediali = serviziDAO.getAttrezzatureMultimedialiNew(scuola.getCodForScuApp(),scuola.getDatAnnScoRil());

            if(voAttrezzatureMultimediali != null) {
                model.addAttribute("compLab", voAttrezzatureMultimediali.getCompLab());
                model.addAttribute("compBib", voAttrezzatureMultimediali.getCompBib());
                model.addAttribute("limLab", voAttrezzatureMultimediali.getLimLab());
                model.addAttribute("limBib", voAttrezzatureMultimediali.getLimBib());
                model.addAttribute("proiettLab", voAttrezzatureMultimediali.getProiettLab());
                model.addAttribute("proiettBib", voAttrezzatureMultimediali.getProiettBib());
                model.addAttribute("compScu", voAttrezzatureMultimediali.getCompScu());
                model.addAttribute("dispMobScu", voAttrezzatureMultimediali.getDispMobScu());
                model.addAttribute("limScu", voAttrezzatureMultimediali.getLimScu());
                model.addAttribute("proiettScu", voAttrezzatureMultimediali.getProiettScu());
                model.addAttribute("numAuleDid", voAttrezzatureMultimediali.getNumAuleDid());
                model.addAttribute("percAuleConn", voAttrezzatureMultimediali.getPercAuleConn());
                model.addAttribute("flgDati", "true");
            } else {
                model.addAttribute("flgDati", "false");
            }

            //Gestione titoli
            model.addAttribute("titoloPC", serviziDAO.getTitoloGrafici("1"));
            model.addAttribute("descrizionePC", serviziDAO.getDescrizioneGrafici("1"));

            model.addAttribute("titoloLIM", serviziDAO.getTitoloGrafici("2"));
            model.addAttribute("descrizioneLIM", serviziDAO.getDescrizioneGrafici("2"));

            model.addAttribute("titoloAule", serviziDAO.getTitoloGrafici("3"));
            model.addAttribute("descrizioneAule", serviziDAO.getDescrizioneGrafici("3"));

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_SERVIZI);

            model.addAttribute("scuola", scuola);

            return "servizi/schede_servizi_attrezzature_multimediali";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }*/

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/servizi/attivita")
    public String getAttivita(@PathVariable(value = "codScuUt") String codScuUt,
                                              String desNomScuNorm,
                                              Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            if(scuola.isIstitutoPrincipale()) {
                VOAnagraficaScuola voScuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUt(), scuola.getDatAnnScoRil());
                if(voScuola != null) {
                    model.addAttribute("flgPlesso", "true");
                } else {
                    model.addAttribute("flgPlesso", "false");
                }
            } else {
                model.addAttribute("flgPlesso", "true");
            }

            
            if(scuola.isIstitutoPrincipale()) {
            	
                model.addAttribute("listaCertificazioni", didatticaDAO.getListaAttivitaIstPrinc(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_CERTIFICAZIONE));

            } else {
            	
                model.addAttribute("listaCertificazioni", didatticaDAO.getListaAttivita(scuola.getCodScuUt(),
                        scuola.getDatAnnScoRil(), Constants.ATTIVITA_CERTIFICAZIONE));
            }
            
            
            List<VOTipologiaServizio> altriServiziList = serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_ATTIVITA_E_ALTRI,
                    scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil());

            model.addAttribute("listaAltriServizi", altriServiziList);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_SERVIZI);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_SER_ATT);
            model.addAttribute("title","Attività - Servizi e attività - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            model.addAttribute("scuola", scuola);

            return "servizi/attivita";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }
}
