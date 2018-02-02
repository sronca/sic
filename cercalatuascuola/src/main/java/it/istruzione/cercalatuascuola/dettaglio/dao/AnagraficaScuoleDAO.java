package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;

import java.util.List;

public interface AnagraficaScuoleDAO {
    List<VOCorsiCFP> getCorsiCP(String codMeccanografico, String datAnnScoRil) throws Exception;
    VOAnagraficaScuola getScuolaByPrimaryKey(String codMeccanografico, String datAnnScoRil) throws Exception;
    List<VOAnagraficaScuola> getScuoleByIstScol(String codMeccanografico, String datAnnScoRil) throws Exception;
    List<VONews> getListaNews(String codUt, String annoRif) throws Exception;
    List<VOSuccursale> getSuccursaliScuola(String codiceMeccanografico, String annoAccademico) throws Exception;
    String getDescrizioneIndirizzoDiStudioScuoleStatali(String codSet) throws Exception;
    String getDescrizioneTipoSito(String codTipSit) throws Exception;
    List<VOClasse> getTempoScuola(String codiceMeccanografico, String annoAccademico, String codTipSer, String codSer) throws Exception;
    String getUrlHomePageIol(String desTipSer) throws Exception;
    VOAnagraficaScuola getCentroFormazioneProfessionaleByPrimaryKey(String codMeccanografico, String datAnnScoRil) throws Exception;
    boolean getRadioButtonServizi(String desTipSer) throws Exception;
    List<VOOffertaFormativa> getListaIndirizziAnnoSuccessivo(String scuola,int anno) throws Exception;
    List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrente(String scuola,int anno,int annoCorso,String codTipSit) throws Exception;
    List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrenteScuolaSerale(String scuola,int anno,List<Integer> anniCorso,String codTipSit) throws Exception;
    List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrente(String scuola,int anno,String codTipSit) throws Exception;
    List<VOOffertaFormativa> getListaPercorsi(String scuola,int anno) throws Exception;
    List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrenteSucc(String scuola,int anno,int annoCorso,String codTipSit) throws Exception;
    List<VOTempiScuola> getListaTempiScuola(String scuola,int anno) throws Exception;
    List<VOTempiScuola> getListaTempiScuolaAnnoCorso(String scuola,int anno,int annoCorso,String codTipSit) throws Exception;
    String getIsPon(String codScuUt) throws Exception;
    List<VOPon> getListaDatiSintesiPon1(String scuola) throws Exception;
    List<VOPon> getListaDatiSintesiPon2(String scuola) throws Exception;
    List<VOPon> getListaDatiSintesiPon3(String scuola) throws Exception;
    List<VOPon> getListaDatiSintesiPon4(String scuola) throws Exception;
    List<VOPon> getListaDatiDettFse(String scuola) throws Exception;
    List<VOPon> getListaDatiDettFesr(String scuola) throws Exception;
    String getDirigenteScolastico(String codScuUt, String datAnnScoRil) throws Exception;
    boolean provinciaAderisceIscrizioniOnLineCFP(String codPrv, String datAnnScoRil) throws Exception;
    boolean scuolaNonStataleAderisceIscrizioniOnLine(String codScuUt, String datAnnScoRil) throws Exception;
    
	public List<VOTipologia> getListaIndirizzoEsame(String codiceScuolaUtente, Integer periodoAnnoScolastico) throws Exception;
	
	public List<VOTipologia> getListaIndirizzoEsame(String codiceScuolaUtente) throws Exception;
    
}
