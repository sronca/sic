package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.model.ScuolaJson;
import it.istruzione.poninchiaro.model.VOBandoIstituto;
import it.istruzione.poninchiaro.model.VODistribuzionePartecipanti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgetti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammatoAutorizzato;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammazioneAreaTerritoriale;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOFaq;
import it.istruzione.poninchiaro.model.VOFiltroRicerca;
import it.istruzione.poninchiaro.model.VOFornitore;
import it.istruzione.poninchiaro.model.VOIstituto;
import it.istruzione.poninchiaro.model.VOModuloRichiesta;
import it.istruzione.poninchiaro.model.VOProgetto;
import it.istruzione.poninchiaro.model.VOProgettoIstituto;
import it.istruzione.poninchiaro.model.VOProgettoOpendata;

import java.util.List;

public interface PoninchiaroDAO {

	public List<VOFaq> getFaq() throws Exception;
	
	public List<LabelValueBean> getAnniBandoList() throws Exception;
	
	public List<LabelValueBean> getTipoFondoList() throws Exception;
	
	public List<LabelValueBean> getRegioniList() throws Exception;
	
	public List<LabelValueBean> getProvinceList(String codRegione) throws Exception;
	
	public List<LabelValueBean> getComuniList(String codProvincia) throws Exception;
	
	public List<ScuolaJson> getIstitutiList(String filter) throws Exception;
	
	public LabelValueBean getRegioneByCod(String codiceRegione) throws Exception;
	
	public LabelValueBean getProvinciaByCod(String codiceProvincia) throws Exception;
	
	public LabelValueBean getComuneByCod(String codiceComune) throws Exception;
	
	public VOIstituto getIstituto(String codiceMeccanografico) throws Exception;
	
	public String getDirigenteScolastico(String codScuUt, String datAnnScoRil) throws Exception;
	
	public List<VOIstituto> getIstitutiBeneficiari(VOFiltroRicerca filter, int prgEve) throws Exception;
	
	public VOEvento getPrgEvePubblicato(String tipoEvento) throws Exception;
	
	public List<VOProgettoIstituto> getProgettiIstituto(String codiceMeccanografico, String anno, String codTipFon, int prgEve) throws Exception;
	
	public List<VOFornitore> getFornitoriBeneficiari(int prgEvePubblicazione) throws Exception;
	
	public VOFornitore getFornitore(String prgFornitore) throws Exception;
	
	public List<VOProgettoIstituto> getProgettiFornitore(String prgFornitore, int prgEve) throws Exception;
	
	public List<LabelValueBean> getTipoBandoList() throws Exception;
	
	public List<VOIstituto> getIstitutiBandi(VOFiltroRicerca filter) throws Exception;
	
	public String getDataAggiornamentoIstitutiBandi() throws Exception;
	
	public List<VOBandoIstituto> getBandiIstituto(String codiceMeccanografico, String tipoBando) throws Exception;
	
	public List<VOProgettoOpendata> getProgettiIstitutiBeneficiariOpendata(int prgEve) throws Exception;
	
	public List<VOProgettoOpendata> getProgettiFornitoriBeneficiariOpendata(int prgEve) throws Exception;
	
	public List<VOProgettoOpendata> getBandiScuoleOpendata() throws Exception;
	
	public String getDataAggiornamentoBandiAmministrazione() throws Exception;
	
	public List<VOProgettoOpendata> getBandiAmministrazioneOpendata() throws Exception;
	
	public List<LabelValueBean> getSottoCategoriaList(String tipoFondo) throws Exception;
	
	public LabelValueBean getSottoCategoriaByCod(String codiceSottoCategoria) throws Exception;
	
	public List<VOIstituto> getIstitutiScuoledelpon(VOFiltroRicerca filter, int prgEvePubblicazione) throws Exception;
	
	public VOBandoIstituto getBando(String codBando) throws Exception;
	
	public List<VODistribuzioneProgetti> getDistribuzioneProgettiPerTipoIntervento(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception;
	
	public List<VODistribuzioneProgetti> getDistribuzioneProgettiIstitutiPerAreaTerritoriale(int prgEvePubblicazioneIstituti, String areaTerritoriale) throws Exception;
	
	public List<LabelValueBean> getAreeTerritorialiList() throws Exception;
	
	public List<VODistribuzioneProgrammazioneAreaTerritoriale> getDistribuzioneProgrammazioneAreaTerritoriale() throws Exception;
	
	public List<VODistribuzioneProgrammatoAutorizzato> getDistribuzioneProgrammatoAutorizzato(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception;

	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerAreaTerritoriale(int prgEvePubblicazioneIstituti) throws Exception;
	
	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerRegione(int prgEvePubblicazioneIstituti) throws Exception;
	
	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerCicloScolastico(int prgEvePubblicazioneIstituti) throws Exception;
	
	public VOProgettoOpendata getDatiDescrittiviProgetto(int prgPgt, int prgEve) throws Exception;

	public VOProgettoIstituto getDatiFinanziariProgetto(int prgPgt, int prgEve)throws Exception;

	public VOProgetto getDatiProgetto(int prgPgt, int prgEve) throws Exception;

	public List<VOModuloRichiesta> getListaModuliRichiesta(int parseInt) throws Exception;

	
	


}