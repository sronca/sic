package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.AVCPFinanzaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.FinanzaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.PagamentiAmmDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import legge19010.PubblicazioneDocument;
import legge19010.PubblicazioneDocument.Pubblicazione.Data;
import legge19010.PubblicazioneDocument.Pubblicazione.Data.Lotto;
import legge19010.PubblicazioneDocument.Pubblicazione.Metadata;

@Controller
public class FinanzaController {
    private Logger logger = Logger.getLogger(FinanzaController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private FinanzaDAO finanzaDAO;
    private AVCPFinanzaDAO avcpFinanzaDAO;
    private PagamentiAmmDAO pagamentiAmmDAO;
    private GraficiDAO graficiDAO;

    @Autowired
    public FinanzaController(AnagraficaScuolaService anagraficaScuolaService,
                             PagamentiAmmDAO pagamentiAmmDAO,
                             FinanzaDAO finanzaDAO,
                             AVCPFinanzaDAO avcpFinanzaDAO,
                             GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.pagamentiAmmDAO = pagamentiAmmDAO;
        this.finanzaDAO = finanzaDAO;
        this.avcpFinanzaDAO = avcpFinanzaDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/finanza")
    public String getFinanza(@PathVariable(value = "codScuUt") String codScuUt,
                              String desNomScuNorm,
                              Model model) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico tabellaPercentualiEntrate = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_FIN, Constants.SEZIONE_GRA_FIN, "2");

            VOGrafico tabellaImportiEntrate = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_FIN, Constants.SEZIONE_GRA_FIN, "3");

            List<VOFinanza> listaEntrate = new ArrayList<VOFinanza>();
            
    		if(tabellaPercentualiEntrate.getCodStaPubbUff().equals("S") ||
    				(tabellaPercentualiEntrate.getCodStaPubbUff().equals("F") && tabellaPercentualiEntrate.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(tabellaPercentualiEntrate.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {
    			
    			listaEntrate = finanzaDAO.getEntratePerFontiFinSpesa(scuola.getCodForScuApp());
    		}        		

            model.addAttribute("titoloTabellaPercentualiEntrate", tabellaPercentualiEntrate.getDesTitGra());
            model.addAttribute("approfondisciTabellaPercentualiEntrate", tabellaPercentualiEntrate.getDesInfGra());
            model.addAttribute("noteTabellaPercentualiEntrate", tabellaPercentualiEntrate.getDesNotGra());
            
            model.addAttribute("titoloTabellaImportiEntrate", tabellaImportiEntrate.getDesTitGra());
            model.addAttribute("approfondisciTabellaImportiEntrate", tabellaImportiEntrate.getDesInfGra());
            model.addAttribute("noteTabellaImportiEntrate", tabellaImportiEntrate.getDesNotGra());

            model.addAttribute("listaEntrate", listaEntrate);

            model.addAttribute("scuola", scuola);

            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_FINANZA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_FIN_FIN);
            model.addAttribute("title","Entrate per fonti di finanziamento - Finanza - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            //return "finanza/schede_finanza";
            return "finanza/finanza";
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/finanza/pagamenti")
    public String getPagamenti(@PathVariable(value = "codScuUt") String codScuUt,
                               String desNomScuNorm,
     						  VOAnni voAnni,
                              Model model) {
            try {
            	
            String annoScolasticoSel = voAnni.getAnnoScolasticoSel();
            String annoFinanziarioSel = voAnni.getAnnoFinanziarioSel();
            model.addAttribute("voAnni", voAnni);
                
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            List<VOAnnoWrapper> anniScolastici = pagamentiAmmDAO.getListaAnniSco(scuola.getCodForScuApp());
            if (anniScolastici.isEmpty()) {
            	anniScolastici.add(new VOAnnoWrapper(Integer.parseInt(Utility.annoAccademico())));
            }
            
            Map<String, String> mapAnniScolastici = new LinkedHashMap<String, String>();
            for (VOAnnoWrapper vo : anniScolastici){
            	mapAnniScolastici.put(vo.getAnno().toString(), vo.getAnnoScolasticoDesc());
            }
            model.addAttribute("anniScolastici", mapAnniScolastici);
            //model.addAttribute("anniScolastici", anniScolastici);

            String primoAnnoScolatico = "";
            if (anniScolastici != null && !anniScolastici.isEmpty()){
            	primoAnnoScolatico = anniScolastici.get(0).getAnno().toString();
            	logger.debug("primoAnnoScolatico : " + primoAnnoScolatico);
            }
            String datAnnScoRilSel = annoScolasticoSel != null ? annoScolasticoSel : primoAnnoScolatico;
            logger.debug("datAnnScoRilSel : " + datAnnScoRilSel);
            model.addAttribute("annoScolasticoSel", datAnnScoRilSel);

            List<VOAnnoWrapper> anniFinanziari = getListaAnnoFinanz(datAnnScoRilSel);

            String annoFinSel = annoFinanziarioSel;
            if (annoFinSel == null){
            	if (anniFinanziari.size() > 0){
            		annoFinSel = anniFinanziari.get(0).getAnno().toString();
            	}
            }
            //analogamente se nel form il campo anno finanziario selezionato non è tra quelli presenti nella tendina imposto il primo anno finanziario della tendina
            else if (!isIn(annoFinSel, anniFinanziari) ){
                annoFinSel = anniFinanziari.get(0).getAnno().toString();
            }           
            model.addAttribute("annoFinanziarioSel", annoFinSel);
            logger.debug("annoFinSel : " + annoFinSel);
            
            
            Map<String, String> mapAnniFinanziari = new LinkedHashMap<String, String>();
            for (VOAnnoWrapper vo : anniFinanziari){
            	mapAnniFinanziari.put(vo.getAnno().toString(), vo.getAnnoBilancioDesc());
            }
            model.addAttribute("anniFinanziari", mapAnniFinanziari);
            //model.addAttribute("anniFinanziari", anniFinanziari);

            List<VOIndiceDiTempestivitaPagamenti> elencoIndici = new ArrayList<VOIndiceDiTempestivitaPagamenti>();

            if (datAnnScoRilSel != null
                    && !datAnnScoRilSel.isEmpty()
                    && annoFinSel != null
                    && !annoFinSel.isEmpty()) {

                elencoIndici = pagamentiAmmDAO.getListaIndicatori(codScuUt, scuola.getDatAnnScoRil(), datAnnScoRilSel, annoFinSel);
            }

            model.addAttribute("elencoIndici", elencoIndici);
            
            

            model.addAttribute("scuola", scuola);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_FINANZA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_FIN_PAG);
            model.addAttribute("title","Amministrazione trasparente - pagamenti dell'amministrazione - Finanza - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            //return "finanza/pagamentiAmm";
            return "finanza/pagamenti";
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/finanza/bandi")
    public String getAVCP(@PathVariable(value = "codScuUt") String codScuUt,
						  @PathVariable(value = "desNomScuNorm") String desNomScuNorm,
						  VOAnni voAnni,
                          Model model) {
        try {
        	
            String annoScolasticoSel = voAnni.getAnnoScolasticoSel();
            String annoBilancioSel = voAnni.getAnnoBilancioSel();
            logger.info("annoScolasticoSel : " + annoScolasticoSel);
            logger.info("annoBilancioSel : " + annoBilancioSel);
            
            model.addAttribute("voAnni", voAnni);
        	
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            boolean isDocOnDatabase = true;
            // lista anni scolastici

            logger.info("imposto lista anni scolastici");
            List<VOAnnoWrapper> listaAnniScolastici = avcpFinanzaDAO.getAnniScolastici(scuola.getCodScuUtPri());
            if (listaAnniScolastici.isEmpty()) {
                listaAnniScolastici.add(new VOAnnoWrapper(Integer.parseInt(Utility.annoAccademico())));
                isDocOnDatabase = false;
            }
            Collections.sort(listaAnniScolastici);
            //model.addAttribute("anniScolastici", listaAnniScolastici);
            
            Map<String, String> mapAnniScolastici = new LinkedHashMap<String, String>();
            for (VOAnnoWrapper vo : listaAnniScolastici){
            	mapAnniScolastici.put(vo.getAnno().toString(), vo.getAnnoScolasticoDesc());
            }
            model.addAttribute("anniScolastici", mapAnniScolastici);

            //Integer annoScolasticoSelInt = listaAnniScolastici.get(listaAnniScolastici.size() - 1).getAnno();
            Integer annoScolasticoSelInt = listaAnniScolastici.get(0).getAnno();
            if(annoScolasticoSel != null) {
                annoScolasticoSelInt = Integer.parseInt(annoScolasticoSel);
            }
            model.addAttribute("annoScolasticoSel", annoScolasticoSel);
            logger.info("annoScolasticoSelInt : " + annoScolasticoSelInt);

            logger.info("imposto lista anni bilancio");
            List<VOAnnoWrapper> anniBilancio = avcpFinanzaDAO.getAnniBilancio(scuola.getCodScuUtPri(), annoScolasticoSelInt);
            if(anniBilancio.isEmpty()){
                GregorianCalendar gcalendar = new GregorianCalendar();
                Integer annoCorrente = gcalendar.get(GregorianCalendar.YEAR);
                anniBilancio.add(new VOAnnoWrapper(annoCorrente));
            }
            Collections.sort(anniBilancio);
            //model.addAttribute("anniBilancio", anniBilancio);
            
            
            Map<String, String> mapAnniBilancio = new LinkedHashMap<String, String>();
            for (VOAnnoWrapper vo : anniBilancio){
            	mapAnniBilancio.put(vo.getAnno().toString(), vo.getAnnoBilancioDesc());
            }
            model.addAttribute("anniBilancio", mapAnniBilancio);


            //Integer annoBilancioSelInt = anniBilancio.get(anniBilancio.size() - 1).getAnno();
            Integer annoBilancioSelInt = anniBilancio.get(0).getAnno();
            if(annoBilancioSel != null && mapAnniBilancio.containsKey(annoBilancioSel)){
                annoBilancioSelInt = Integer.parseInt(annoBilancioSel);
            }
            model.addAttribute("annoBilancioSel", annoBilancioSel);
            logger.info("annoBilancioSelInt : " + annoBilancioSelInt);

            VOAVCPFinanza voAVCPFinanza = new VOAVCPFinanza();
            // carico documento dal DB
            if(isDocOnDatabase){
                logger.info("carico documento per anno scolastico: " + annoScolasticoSelInt + " , e anno finanziario: " + annoBilancioSelInt);
                VODocumentoAVCP documentoXml = avcpFinanzaDAO.getAVCP(scuola.getCodScuUtPri(), annoScolasticoSelInt, annoBilancioSelInt);
                if(documentoXml != null){
                    parse(voAVCPFinanza, documentoXml);
                }
            }

            model.addAttribute("scuola", scuola);
            model.addAttribute("voAVCPFinanza", voAVCPFinanza);
            model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_FINANZA);
            model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_FIN_BAN);
            model.addAttribute("title","Amministrazione trasparente - bandi di gara e contratti - Finanza - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

            //return "finanza/avcp";
            return "finanza/bandi";
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    private void parse(VOAVCPFinanza voAVCPFinanza, VODocumentoAVCP $docToParse){
    	voAVCPFinanza.setDocumentoSel($docToParse);
        // Bind the incoming XML to an XMLBeans type.
        PubblicazioneDocument xmlDocObject = null;
        Date start = null, end = null;
        int size = 0;
        try{
            start = new Date();
            File temp = File.createTempFile($docToParse.getCodiceMeccanografico() + "_" + $docToParse.getAnnoScolastico() + "_" + $docToParse.getAnnoBilancio(), ".xml");
            logger.debug("temp: " + temp + " - " + temp.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(temp.getAbsolutePath());
            fos.write($docToParse.getFile());
            fos.close();
            logger.debug("inizio parse file...");
            xmlDocObject = PubblicazioneDocument.Factory.parse(temp);
            logger.debug("estraggo metadati...");
            Metadata xmlMeta = xmlDocObject.getPubblicazione().getMetadata();
            VODocumentoAVCPMetadata metadata = new VODocumentoAVCPMetadata();
            metadata.setAnnoBilancio(xmlMeta.getAnnoRiferimento());
            metadata.setDocumentAbstract(xmlMeta.getAbstract());
            metadata.setTitolo(xmlMeta.getTitolo());
            metadata.setUrlFile(xmlMeta.getUrlFile());
            metadata.setEntePubblicatore(xmlMeta.getEntePubblicatore());
            if(xmlMeta.getDataPubbicazioneDataset() != null){
                metadata.setDataPubblicazione(xmlMeta.getDataPubbicazioneDataset().getTime());
            }
            if(xmlMeta.getDataUltimoAggiornamentoDataset() != null){
                metadata.setDataUltimoAggiornamento(xmlMeta.getDataUltimoAggiornamentoDataset().getTime());
            }
            voAVCPFinanza.setDocumentoMetadataSel(metadata);
            logger.debug("estraggo lotti...");
            List<VOLottoAVCP> lotti = new ArrayList<VOLottoAVCP>();
            Data data = xmlDocObject.getPubblicazione().getData();
            VOLottoAVCP currLotto;
            for(Lotto _lotto: data.getLottoArray()){
                currLotto = new VOLottoAVCP();
                currLotto.setCig(_lotto.getCig());
                currLotto.setStrutturaProponenteCF(_lotto.getStrutturaProponente().getCodiceFiscaleProp());
                currLotto.setStrutturaProponenteDesc(_lotto.getStrutturaProponente().getDenominazione());
                currLotto.setOggetto(_lotto.getOggetto());
                currLotto.setProceduraDiScelta(_lotto.getSceltaContraente().toString());
                if(_lotto.getImportoAggiudicazione() != null){
                    currLotto.setImportoAggiudicazione(_lotto.getImportoAggiudicazione().floatValue());
                }
                if(_lotto.getImportoSommeLiquidate() != null){
                    currLotto.setImportoLiquidato(_lotto.getImportoSommeLiquidate().floatValue());
                }
                if(_lotto.getTempiCompletamento() != null && _lotto.getTempiCompletamento().getDataInizio() != null){
                    currLotto.setDataInizio(_lotto.getTempiCompletamento().getDataInizio().getTime());
                }
                if(_lotto.getTempiCompletamento() != null && _lotto.getTempiCompletamento().getDataUltimazione() != null){
                    currLotto.setDataFine(_lotto.getTempiCompletamento().getDataUltimazione().getTime());
                }
                lotti.add(currLotto);
                size++;
            }
            voAVCPFinanza.setLotti(lotti);
            end = new Date();
            temp.delete();
            logger.debug("millisecondi: " + (end.getTime() - start.getTime()) + " per " + size + " lotti");
        }
        catch(FileNotFoundException ex)   {
        	logger.error("FileNotFoundException : " + ex.getMessage(), ex);
        }
        catch(IOException ioe)  {
        	logger.error("IOException : " + ioe.getMessage(), ioe);
        }
        catch (Exception e) {
        	logger.error("Exception : " + e.getMessage(), e);
        }
    }

    private List<VOAnnoWrapper> getListaAnnoFinanz(String annoSco){
        List<VOAnnoWrapper> listaAnniFinanz = new ArrayList<VOAnnoWrapper>();

        if (annoSco != null && !annoSco.isEmpty()){
            String anno1 = annoSco.substring(0, 4);
            String anno2 = annoSco.substring(4, 6);

            if(Integer.parseInt(anno1) >= 1999){
                anno2 = "20"+anno2;
            }
            else{
                anno2 = "19"+anno2;
            }

            listaAnniFinanz.add(new VOAnnoWrapper(Integer.valueOf(anno1)));
            listaAnniFinanz.add(new VOAnnoWrapper(Integer.valueOf(anno2)));
        }

        return listaAnniFinanz;
    }

    private boolean isIn(String anno, List<VOAnnoWrapper> anniFinanziari) {

        boolean esito = false;
        for (VOAnnoWrapper voAnnoWrapper : anniFinanziari){
            if (voAnnoWrapper.getAnno().toString().equals(anno))
                esito = true;
        }

        return esito;
    }
    
    @RequestMapping(value = "/istituti/{codScuUt}/finanza/AVCP")
    public void getAVCPDocument(@PathVariable(value = "codScuUt") String codScuUt,
                                  Integer annoScolastico,
                                  Integer annoBilancio,
                                  String contentDisp,
                                  HttpServletResponse response) {

        BufferedOutputStream out = null;
        InputStream inputStream = null;
        try {

            if(annoScolastico != null && annoBilancio != null) {
            	
            	VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

                VODocumentoAVCP documentoXml = avcpFinanzaDAO.getAVCP(scuola.getCodScuUtPri(), annoScolastico, annoBilancio);

                if (documentoXml != null && documentoXml.getFile() != null) {
                    out = new BufferedOutputStream(response.getOutputStream());
                    inputStream = new ByteArrayInputStream( documentoXml.getFile() );

                    response.reset();
                    response.setContentType("application/xml");

                    if(Constants.CONTENT_DISPOSITION_ATTACHMENT.equals(contentDisp)) {
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + documentoXml.getNomeFile() + "\"");
                    } else {
                        response.setHeader("Content-disposition", "inline; filename=\"" + documentoXml.getNomeFile() + "\"");
                    }

                    int data;
                    while ((data = inputStream.read()) != -1) {
                        out.write(data);
                    }

                    out.flush();
                }
            }

            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    
}
