package it.istruzione.cercalatuascuola.dettaglio.service;

import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.AnagraficaScuoleDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.CaratteristicaDiplomatoDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLinkIscrizioniOnline;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOMenu;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AnagraficaScuolaServiceImpl implements AnagraficaScuolaService {
    private Logger logger = Logger.getLogger(AnagraficaScuolaServiceImpl.class);
    private static final String URL_MAPPE = "https://maps.googleapis.com/maps/api/js?v=3.exp&amp;libraries=places&amp;sensor=false&amp;key=";
    private String annoScolasticoIndStudio;
    private String urlDocOrientamentoLicei;
    private String urlDocOrientamentoTecnici;
    private String chiaveGoogle;
    private AnagraficaScuoleDAO anagraficaScuoleDAO;
	private CaratteristicaDiplomatoDAO caratteristicaDiplomatoDAO;
    private String ANNO_CARATTERISTICHE_DIPLOMATO= "CAR_DIP";
    private String ANNO_ISCRIZIONE_ONLINE= "AS_IOL";

    @Autowired
    public AnagraficaScuolaServiceImpl(AnagraficaScuoleDAO anagraficaScuoleDAO, CaratteristicaDiplomatoDAO caratteristicaDiplomatoDAO) {
        this.anagraficaScuoleDAO = anagraficaScuoleDAO;
        this.caratteristicaDiplomatoDAO = caratteristicaDiplomatoDAO;
    }

    public VOAnagraficaScuola getAnagraficaScuola(String codScuUt) throws Exception {
        VOAnagraficaScuola scuola = null;
        String annoAccademico = Utility.annoAccademico();

		Integer annoAccademicoCaratteristica = this.getAnnoCaratteristicheDiplomato();

        if ("CF".equals(codScuUt.substring(2, 4))) {
            scuola = anagraficaScuoleDAO.getCentroFormazioneProfessionaleByPrimaryKey(codScuUt, annoAccademico);
        } else {
            scuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(codScuUt, annoAccademico);
        }

        if (scuola == null) {
            throw new Exception("scuola " + codScuUt + " non trovata");
        }

        if ("CF".equals(codScuUt.substring(2,4))) {
            scuola.setListaCorsi(anagraficaScuoleDAO.getCorsiCP(codScuUt, annoAccademico));
        }

        scuola.setFlgPon(anagraficaScuoleDAO.getIsPon(scuola.getCodScuUt()));

        // mod 05-09-2014
        if(annoScolasticoIndStudio != null && !annoScolasticoIndStudio.trim().equals("")){
            scuola.setDatAnnScoRil(annoScolasticoIndStudio);
        }

        logger.debug("CodTipSit: " + scuola.getCodTipSit());
        logger.debug("scuola.getCodReg(): " + scuola.getCodReg());

        if(scuola.getCodScuUt().substring(2, 4).compareTo("EE") == 0) {
            scuola.setDesTipSit("Scuola Primaria");
        }
        else if(scuola.getCodScuUt().substring(2, 4).compareTo("AA") == 0) {
            scuola.setDesTipSit("Scuola dell'Infanzia");
        }
        else if(scuola.getCodScuUt().substring(2, 3).compareTo("P") == 0 ||
                scuola.getCodScuUt().substring(2, 3).compareTo("S") == 0) {

            scuola.setUrlDocOrientamento(urlDocOrientamentoLicei);
            scuola.setDesTipSit("Liceo");
        }
        else if(scuola.getCodScuUt().substring(2, 3).compareTo("T") == 0) {

            scuola.setUrlDocOrientamento(urlDocOrientamentoTecnici);
            scuola.setDesTipSit("Istituto Tecnico");
        }
        else if(scuola.getCodScuUt().substring(2, 3).compareTo("R") == 0) {

            scuola.setUrlDocOrientamento(urlDocOrientamentoTecnici);
            scuola.setDesTipSit("Istituto Professionale");
        }
        
        scuola.setMostraPulsanteEdilizia(this.getMostraPulsanteEdilizia());
        
        scuola.setCaratteristicaDiplomato(loadMenu( scuola.getCodForScuApp(), annoAccademicoCaratteristica));
        
        logger.debug("desTipSit: " + scuola.getDesTipSit());
                

        return scuola;
    }

    public VOLinkIscrizioniOnline getUrlIscrizioniOnline(VOAnagraficaScuola scuola, String callBackUrl) throws Exception {
        String urlIscrizioniOnline = null;
        boolean provenienzaPortale = true;
        boolean visLinkIscr = false;
        if(scuola.getFlgIstSta().compareTo("0") == 0 &&
                scuola.getCodTipSit().compareTo("AA") != 0 &&
                scuola.getCodTipSit().compareTo("CT") != 0) {

            if (scuola.getCodCarScu() != null && scuola.getCodCarScu().equals("1")) {
                visLinkIscr = false;
            }
            else if(scuola.isSerale()) {
                visLinkIscr = false;
            }
            else if((scuola.getCodTipSit().compareTo("EE") == 0 && scuola.getCodScuUt().substring(7, 9).compareTo("00") > 0)) {
                visLinkIscr = true;
            }
            else if((scuola.getCodTipSit().compareTo("MM") == 0) ||
                    (scuola.getCodTipSit().compareTo("IS") != 0 && scuola.getCodTipSit().compareTo("MM") > 0)) {

                visLinkIscr = true;
            }
        }

        if(scuola.getFlgIstSta().compareTo("1") == 0){
            visLinkIscr = anagraficaScuoleDAO.scuolaNonStataleAderisceIscrizioniOnLine(scuola.getCodScuUt().trim().toUpperCase(), scuola.getDatAnnScoRil());
        }

        if("CF".equals(scuola.getCodScuUt().substring(2, 4))){
            visLinkIscr = anagraficaScuoleDAO.provinciaAderisceIscrizioniOnLineCFP(scuola.getCodPrv().trim().toUpperCase(), scuola.getDatAnnScoRil());
        }

        if(visLinkIscr) {
            if (callBackUrl != null && !callBackUrl.trim().equals("") && !callBackUrl.trim().equals("null")) {
                urlIscrizioniOnline = callBackUrl + "?p=" + scuola.getCodScuUt();
                //String callBackDiscr = PropertyConfigurator.getInstance().getProperty(PropertyConfigurator.DISCRIMINANTE_CALLBACK_URL);
                String callBackDiscr = ".do";
                if(callBackUrl.indexOf((callBackDiscr != null ? callBackDiscr : ".do")) > 0 ) {
                    provenienzaPortale = false;
                }
            }
            else {
                if ("TN".equalsIgnoreCase(scuola.getCodPrv())) {
                    urlIscrizioniOnline = anagraficaScuoleDAO.getUrlHomePageIol("ATT_TRE");
                } else {
                    urlIscrizioniOnline = anagraficaScuoleDAO.getUrlHomePageIol("ATT_IOL");
                }
            }

            logger.debug("URL iscrizioni: " + urlIscrizioniOnline);
        }
        
        VOLinkIscrizioniOnline out = new VOLinkIscrizioniOnline();
        out.setUrlIscrizioniOnline(urlIscrizioniOnline);
        out.setProvenienzaPortale(provenienzaPortale);
        out.setVisLinkIscr(visLinkIscr);
        
        return out;
    }

    public Integer getAnnoCaratteristicheDiplomato() throws Exception {
        Integer i = Integer.parseInt(anagraficaScuoleDAO.getUrlHomePageIol(ANNO_CARATTERISTICHE_DIPLOMATO));
        return i;
    }

    public String getAnnoIscrizioniOnline() throws Exception {
        String s = anagraficaScuoleDAO.getUrlHomePageIol(ANNO_ISCRIZIONE_ONLINE);
        return s;
    }

    public boolean getMostraPulsanteEdilizia() throws Exception {
        String flag = anagraficaScuoleDAO.getUrlHomePageIol("ATT_VIS_EDI");
        return "1".equals(flag);
    }
    
    public String getUrlHomePageIol(String desTipSer) throws Exception {
    	return anagraficaScuoleDAO.getUrlHomePageIol(desTipSer);
    }

    public boolean getMostraLibriDiTesto(VOAnagraficaScuola scuola) {
        return Utility.mostraMenuLibriDiTesto(scuola);
    }

    public String getUrlMappe() {
        return URL_MAPPE + chiaveGoogle;
    }

    @Value("${anno.scolastico.indirizzi.studio}")
    public void setAnnoScolasticoIndStudio(String annoScolasticoIndStudio) {
        this.annoScolasticoIndStudio = annoScolasticoIndStudio;
    }

    @Value("${url.doc.orientamento.licei}")
    public void setUrlDocOrientamentoLicei(String urlDocOrientamentoLicei) {
        this.urlDocOrientamentoLicei = urlDocOrientamentoLicei;
    }

    @Value("${url.doc.orientamento.tecnici}")
    public void setUrlDocOrientamentoTecnici(String urlDocOrientamentoTecnici) {
        this.urlDocOrientamentoTecnici = urlDocOrientamentoTecnici;
    }

    @Value("${CHIAVE_GOOGLE_SCUOLAINCHIARO}")
    public void setChiaveGoogle(String chiaveGoogle) {
        this.chiaveGoogle = chiaveGoogle;
    }
    
    public boolean isGraficoVisibile(VOGrafico $infoGrafico, VOAnagraficaScuola $scuola){
    	boolean visibile = false;
    	if($infoGrafico != null && $scuola != null){
    		visibile = "S".equals($infoGrafico.getCodStaPubbUff()) 
    				   //|| ( "F".equals($infoGrafico.getCodStaPubbUff()) && "S".equals($infoGrafico.getCodStaPubbScu()) && "1".equals($scuola.getFlgIstSta()) ) 
    				   //|| ( "F".equals($infoGrafico.getCodStaPubbUff()) && "0".equals($scuola.getFlgIstSta()) ) ;
    				|| ( "F".equals($infoGrafico.getCodStaPubbUff()) && !"N".equals($infoGrafico.getCodStaPubbScu()) ); 
		}
    	return visibile;
    }
    
	private List<VOMenu> loadMenu( String codScuUt, Integer annoAccademico)
	{
		List<VOMenu> listaVOMenu = new ArrayList<VOMenu>();
		try {
			List<VOTipologia> listaVOTipologia = this.getListaIndirizzoEsame(codScuUt);

			listaVOMenu = caratteristicaDiplomatoDAO.getMenu(listaVOTipologia, annoAccademico);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listaVOMenu;
	}

	public List<VOTipologia> getListaIndirizzoEsame(String codScuUt) throws Exception {
		try {
			List<VOTipologia> listaVO = anagraficaScuoleDAO.getListaIndirizzoEsame(codScuUt);

			return listaVO;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			throw new Exception("Non è stato trovato nessun indirizzo di esame per la scuola " + codScuUt);
		}
	}
}
