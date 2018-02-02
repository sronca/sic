package it.istruzione.poninchiaro.service;

import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.dao.GestionaleDAO;
import it.istruzione.poninchiaro.dao.PoninchiaroDAO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoninchiaroServiceImpl implements PoninchiaroService {
	
    private Logger log = Logger.getLogger(PoninchiaroServiceImpl.class);
    private PoninchiaroDAO poninchiaroDAO;
    private GestionaleDAO gestionaleDAO;
	private static Map<String, String> stati = new LinkedHashMap<String, String>();
	private static Map<String, String> tipiIstruzione = new LinkedHashMap<String, String>();

	static{
		stati.put("I", "IN CORSO");
		stati.put("S", "SCADUTI");
		stati.put("T", "TUTTI");
		
		tipiIstruzione.put("", "");
		tipiIstruzione.put("PC", "PRIMO CICLO");
		tipiIstruzione.put("SC", "SECONDO CICLO");
		tipiIstruzione.put("CPIA", "C.P.I.A.");
	}
	
    @Autowired
    public PoninchiaroServiceImpl(PoninchiaroDAO poninchiaroDAO, GestionaleDAO gestionaleDAO) {
        this.poninchiaroDAO = poninchiaroDAO;
        this.gestionaleDAO = gestionaleDAO;
    }
    
    public List<VOFaq> getFaq() throws Exception{
    	return poninchiaroDAO.getFaq();
    }
    
    public Map<String, String> getAnniBandoList(boolean addEmptyValue) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getAnniBandoList();
    		if (addEmptyValue){
    			map.put("", "Tutti");
    		}
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }
    
    public Map<String, String> getTipoFondoList(boolean addEmptyValue) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getTipoFondoList();
    		if (addEmptyValue){
    			map.put("", "");
    		}
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }
    
    public Map<String, String> getRegioniList() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getRegioniList();
    		map.put("", "");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }
    
    public Map<String, String> getProvinceList(String codRegione) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getProvinceList(codRegione);
    		map.put("", "");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }

    public Map<String, String> getComuniList(String codProvincia) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getComuniList(codProvincia);
    		map.put("", "");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }

    public Map<String, String> getSottoCategoriaList(String tipoFondo) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getSottoCategoriaList(tipoFondo);
    		map.put("", "");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }

    public List<ScuolaJson> getIstitutiList(String filter) throws Exception{
    	
    	return poninchiaroDAO.getIstitutiList(filter);
    }

    public LabelValueBean getRegioneByCod(String codiceRegione) throws Exception{
    	
    	return poninchiaroDAO.getRegioneByCod(codiceRegione);
    }

    public LabelValueBean getProvinciaByCod(String codiceProvincia) throws Exception{
    	
    	return poninchiaroDAO.getProvinciaByCod(codiceProvincia);
    }

    public LabelValueBean getComuneByCod(String codiceComune) throws Exception{
    	
    	return poninchiaroDAO.getComuneByCod(codiceComune);
    }

    public LabelValueBean getSottoCategoriaByCod(String codiceSottoCategoria) throws Exception{
    	
    	return poninchiaroDAO.getSottoCategoriaByCod(codiceSottoCategoria);
    }

    public VOIstituto getIstituto(String codiceMeccanografico) throws Exception{
    	
    	return poninchiaroDAO.getIstituto(codiceMeccanografico);
    }

    public String getDirigenteScolastico(String codScuUt, String datAnnScoRil) throws Exception{
    	
    	return poninchiaroDAO.getDirigenteScolastico(codScuUt,datAnnScoRil);
    }
    
    public List<VOIstituto> getIstitutiBeneficiari(VOFiltroRicerca filter, int prgEvePubblicazione) throws Exception{

    	List<VOIstituto> out = new ArrayList<VOIstituto>();
    	if (filter.getAnno() != null && !filter.getAnno().isEmpty()
    			&& filter.getTipoFondo() != null && !filter.getTipoFondo().isEmpty()){
    		
    		out = poninchiaroDAO.getIstitutiBeneficiari(filter, prgEvePubblicazione);

    	}

    	return out;

    }

    public List<VOIstituto> getIstitutiScuoledelpon(VOFiltroRicerca filter, int prgEvePubblicazione) throws Exception{
    	return poninchiaroDAO.getIstitutiScuoledelpon(filter, prgEvePubblicazione);
    }

    public List<VOProgettoIstituto> getProgettiIstituto(String codiceMeccanografico, String anno, String codTipFon, int prgEvePubblicazione) throws Exception{

    	List<VOProgettoIstituto> out = new ArrayList<VOProgettoIstituto>();

    	out = poninchiaroDAO.getProgettiIstituto(codiceMeccanografico, anno, codTipFon, prgEvePubblicazione);

    	return out;

    }
    
    public VOEvento getPrgEvePubblicato(String tipoEvento) throws Exception{

    	return poninchiaroDAO.getPrgEvePubblicato(tipoEvento);

    }

    public List<VOFornitore> getFornitoriBeneficiari(int prgEvePubblicazione) throws Exception{

    	return poninchiaroDAO.getFornitoriBeneficiari(prgEvePubblicazione);

    }
    
    public VOProgettoOpendata getDatiDescrittiviProgetto(int prgPgt, int prgEve) throws Exception{
    	return poninchiaroDAO.getDatiDescrittiviProgetto(prgPgt, prgEve);
    }

    public VOFornitore getFornitore(String prgFornitore) throws Exception{

    	return poninchiaroDAO.getFornitore(prgFornitore);

    }

    public List<VOProgettoIstituto> getProgettiFornitore(String prgFornitore, int prgEve) throws Exception{

    	return poninchiaroDAO.getProgettiFornitore(prgFornitore, prgEve);

    }

    public Map<String, String> getTipoBandoList() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getTipoBandoList();
    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }

    public Map<String, String> getStatiList() throws Exception{

    	return stati;
    }

    public Map<String, String> getTipoIstruzioneList() throws Exception{

    	return tipiIstruzione;
    }

    public String getDecodificaStato(String codiceStato) throws Exception{

    	String out = "";
    	if (stati.containsKey(codiceStato)){
    		out = stati.get(codiceStato);
    	}
    	return out;
    }
 
    public String getDecodificaTipoIstruzione(String codiceTipoIstruzione) throws Exception{

    	String out = "";
    	if (tipiIstruzione.containsKey(codiceTipoIstruzione)){
    		out = tipiIstruzione.get(codiceTipoIstruzione);
    	}
    	return out;
    }
    
    public List<VOIstituto> getIstitutiBandi(VOFiltroRicerca filter) throws Exception{

    	List<VOIstituto> out = new ArrayList<VOIstituto>();
    	if (filter.getTipoBando() != null && !filter.getTipoBando().isEmpty()){
    		
    		out = poninchiaroDAO.getIstitutiBandi(filter);

    	}

    	return out;

    }
    
    public String getDataAggiornamentoIstitutiBandi() throws Exception{
    	
    	return poninchiaroDAO.getDataAggiornamentoIstitutiBandi();
    }

    public String getDataAggiornamentoBandiAmministrazione() throws Exception{
    	
    	return poninchiaroDAO.getDataAggiornamentoBandiAmministrazione();
    }

    public List<VOBandoIstituto> getBandiIstituto(String codiceMeccanografico, String tipoBando) throws Exception{
    	
    	return poninchiaroDAO.getBandiIstituto(codiceMeccanografico, tipoBando);
    }

    public Map<String,List<LabelValueBean>> getGlossario() throws Exception{
    	
    	return gestionaleDAO.getGlossario();
    }
 
    public List<VOProgettoOpendata> getProgettiIstitutiBeneficiariOpendata(int prgEve) throws Exception{
    	
    	return poninchiaroDAO.getProgettiIstitutiBeneficiariOpendata(prgEve);
    }

    public List<VOProgettoOpendata> getProgettiFornitoriBeneficiariOpendata(int prgEve) throws Exception{
    	
    	return poninchiaroDAO.getProgettiFornitoriBeneficiariOpendata(prgEve);
    }

    public List<VOProgettoOpendata> getBandiScuoleOpendata() throws Exception{
    	
    	return poninchiaroDAO.getBandiScuoleOpendata();
    }

    public List<VOProgettoOpendata> getBandiAmministrazioneOpendata() throws Exception{
    	
    	return poninchiaroDAO.getBandiAmministrazioneOpendata();
    }

    public VOBandoIstituto getBando(String codBando) throws Exception{
    	return poninchiaroDAO.getBando(codBando);
    }

    public List<VODistribuzioneProgetti> getDistribuzioneProgettiPerTipoIntervento(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception{

    	List<VODistribuzioneProgetti> elenco = poninchiaroDAO.getDistribuzioneProgettiPerTipoIntervento(prgEvePubblicazioneIstituti, prgEvePubblicazioneFornitori);
		long progettiAutorizzati = 0;
		for(VODistribuzioneProgetti vo : elenco){
			progettiAutorizzati += vo.getNumeroProgetti();
		}
		if (progettiAutorizzati > 0){
			for(VODistribuzioneProgetti vo : elenco){
				vo.setPercentualeProgetti( BigDecimal.valueOf((vo.getNumeroProgetti()*100d)/progettiAutorizzati).setScale(1, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			}			
		}
		
		return elenco;
    }
    
    public List<VODistribuzioneProgetti> getDistribuzioneProgettiIstitutiPerAreaTerritoriale(int prgEvePubblicazioneIstituti, String areaTerritoriale) throws Exception{
    	
    	return poninchiaroDAO.getDistribuzioneProgettiIstitutiPerAreaTerritoriale(prgEvePubblicazioneIstituti, areaTerritoriale);
    }

    public Map<String, String> getAreeTerritorialiList() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<LabelValueBean> elenco = poninchiaroDAO.getAreeTerritorialiList();
    		map.put("TUTTE", "Tutte le aree");
    		if (elenco != null){
    			for (LabelValueBean vo : elenco){
    				map.put(vo.getValue(), vo.getLabel());
    			}
    		}
    	return map;
    }

    public List<VODistribuzioneProgrammazioneAreaTerritoriale> getDistribuzioneProgrammazioneAreaTerritoriale() throws Exception{

    	List<VODistribuzioneProgrammazioneAreaTerritoriale> elenco = poninchiaroDAO.getDistribuzioneProgrammazioneAreaTerritoriale();
//    	/** MOC **/
//    	List<VODistribuzioneProgrammazioneAreaTerritoriale> elenco = new ArrayList<VODistribuzioneProgrammazioneAreaTerritoriale>();
//    	VODistribuzioneProgrammazioneAreaTerritoriale obj = new VODistribuzioneProgrammazioneAreaTerritoriale();
//    	obj.setCodiceAsse("MO 1");
//    	obj.setDescrizioneAsse("DESC 1");
//    	obj.setImportoAreaMS(1000000d);
//    	obj.setImportoAreaPS(500000d);
//    	obj.setImportoAreaTR(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammazioneAreaTerritoriale();
//    	obj.setCodiceAsse("MO 2");
//    	obj.setDescrizioneAsse("DESC 2");
//    	obj.setImportoAreaMS(1000000d);
//    	obj.setImportoAreaPS(500000d);
//    	obj.setImportoAreaTR(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammazioneAreaTerritoriale();
//    	obj.setCodiceAsse("MO 3");
//    	obj.setDescrizioneAsse("DESC 3");
//    	obj.setImportoAreaMS(1000000d);
//    	obj.setImportoAreaPS(500000d);
//    	obj.setImportoAreaTR(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammazioneAreaTerritoriale();
//    	obj.setCodiceAsse("MO 4");
//    	obj.setDescrizioneAsse("DESC 4");
//    	obj.setImportoAreaMS(1000000d);
//    	obj.setImportoAreaPS(500000d);
//    	obj.setImportoAreaTR(500000d);
//    	elenco.add(obj);
    			
		long importoTotale = 0;
		for(VODistribuzioneProgrammazioneAreaTerritoriale vo : elenco){
			importoTotale += vo.getImportoTotaleAsse();
		}
		if (importoTotale > 0){
			for(VODistribuzioneProgrammazioneAreaTerritoriale vo : elenco){
				vo.setPercentualeAsse( BigDecimal.valueOf((vo.getImportoTotaleAsse()*100d)/importoTotale).setScale(1, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			}			
		}
		
		return elenco;
    }
    
    public List<VODistribuzioneProgrammatoAutorizzato> getDistribuzioneProgrammatoAutorizzato(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception{

    	List<VODistribuzioneProgrammatoAutorizzato> elenco = poninchiaroDAO.getDistribuzioneProgrammatoAutorizzato(prgEvePubblicazioneIstituti, prgEvePubblicazioneFornitori);
//    	/** MOC **/
//    	List<VODistribuzioneProgrammatoAutorizzato> elenco = new ArrayList<VODistribuzioneProgrammatoAutorizzato>();
//    	VODistribuzioneProgrammatoAutorizzato obj = new VODistribuzioneProgrammatoAutorizzato();
//    	obj.setCodiceAsse("MO 1");
//    	obj.setDescrizioneAsse("DESC 1");
//    	obj.setImportoAutorizzatoIst(1000000d);
//    	obj.setImportoAutorizzatoFor(500000d);
//    	obj.setImportoProgrammato(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammatoAutorizzato();
//    	obj.setCodiceAsse("MO 2");
//    	obj.setDescrizioneAsse("DESC 2");
//    	obj.setImportoAutorizzatoIst(1000000d);
//    	obj.setImportoAutorizzatoFor(500000d);
//    	obj.setImportoProgrammato(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammatoAutorizzato();
//    	obj.setCodiceAsse("MO 3");
//    	obj.setDescrizioneAsse("DESC 3");
//    	obj.setImportoAutorizzatoIst(1000000d);
//    	obj.setImportoAutorizzatoFor(500000d);
//    	obj.setImportoProgrammato(500000d);
//    	elenco.add(obj);
//    	obj = new VODistribuzioneProgrammatoAutorizzato();
//    	obj.setCodiceAsse("MO 4");
//    	obj.setDescrizioneAsse("DESC 4");
//    	obj.setImportoAutorizzatoIst(1000000d);
//    	obj.setImportoAutorizzatoFor(500000d);
//    	obj.setImportoProgrammato(500000d);
//    	elenco.add(obj);
    			
		return elenco;
    }

    public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerAreaTerritoriale(int prgEvePubblicazioneIstituti) throws Exception{
    	
    	return poninchiaroDAO.getDistribuzionePartecipantiPerAreaTerritoriale(prgEvePubblicazioneIstituti);
    }

    public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerRegione(int prgEvePubblicazioneIstituti) throws Exception{
    	
    	return poninchiaroDAO.getDistribuzionePartecipantiPerRegione(prgEvePubblicazioneIstituti);
    }

    public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerCicloScolastico(int prgEvePubblicazioneIstituti) throws Exception{
    	
    	return poninchiaroDAO.getDistribuzionePartecipantiPerCicloScolastico(prgEvePubblicazioneIstituti);
    }
  
	@Override
	public VOProgettoIstituto getDatiFinanziariProgetto(int parseInt, int prgEve) throws Exception {
		return poninchiaroDAO.getDatiFinanziariProgetto(parseInt, prgEve);
	}

	@Override  
	public VOProgetto getDatiProgetto(int parseInt, int prgEve) throws Exception {
		return poninchiaroDAO.getDatiProgetto(parseInt, prgEve);
	}

	@Override
	public List<VOModuloRichiesta> getListaModuliRichiesta(int parseInt) throws Exception {
		return poninchiaroDAO.getListaModuliRichiesta(parseInt);
	}
    
    
}

