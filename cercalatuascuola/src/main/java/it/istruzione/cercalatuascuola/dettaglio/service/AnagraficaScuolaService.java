package it.istruzione.cercalatuascuola.dettaglio.service;

import java.util.List;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLinkIscrizioniOnline;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;

public interface AnagraficaScuolaService {
    VOAnagraficaScuola getAnagraficaScuola(String codScuUt) throws Exception;
    VOLinkIscrizioniOnline getUrlIscrizioniOnline(VOAnagraficaScuola scuola, String callBackUrl) throws Exception;
    Integer getAnnoCaratteristicheDiplomato() throws Exception;
    String getAnnoIscrizioniOnline() throws Exception;
    boolean getMostraPulsanteEdilizia() throws Exception;
    boolean getMostraLibriDiTesto(VOAnagraficaScuola scuola);
    String getUrlMappe();
    String getUrlHomePageIol(String desTipSer) throws Exception;
    public boolean isGraficoVisibile(VOGrafico $infoGrafico, VOAnagraficaScuola $scuola);
    public List<VOTipologia> getListaIndirizzoEsame(String codScuUt) throws Exception;
}
