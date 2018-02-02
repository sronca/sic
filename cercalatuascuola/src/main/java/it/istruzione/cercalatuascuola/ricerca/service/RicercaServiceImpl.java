package it.istruzione.cercalatuascuola.ricerca.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.istruzione.cercalatuascuola.ricerca.dao.CercaLaTuaScuolaDAO;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOCommon;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RicercaServiceImpl implements RicercaService {
    private Logger logger = Logger.getLogger(RicercaServiceImpl.class);
    private CercaLaTuaScuolaDAO cercaLaTuaScuolaDAO;

    @Autowired
    public RicercaServiceImpl(CercaLaTuaScuolaDAO cercaLaTuaScuolaDAO) {
        this.cercaLaTuaScuolaDAO = cercaLaTuaScuolaDAO;
    }

    public Map<String, String> getTipologiaList(String codiceOrdine, String stataleNonStatale) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceOrdine != null && 
    			(codiceOrdine.equals("4") || codiceOrdine.equals("5"))){

    		List<VOCommon> elenco = null;
    		if (codiceOrdine.equals("4")){
    			if (stataleNonStatale != null && stataleNonStatale.equals("Statale"))
    				elenco = cercaLaTuaScuolaDAO.getTipologie();
    			else
    				elenco = cercaLaTuaScuolaDAO.getTipologieParitarie();
    		}else
    			elenco = cercaLaTuaScuolaDAO.getPercorsiCFP();

    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }
    
    public Map<String, String> getTempiScuolaPrimariaList() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getTempiScuolaPrimaria();
    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	return map;
    }
    
    public Map<String, String> getTempiScuolaSecondariaDiPrimoGrado() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getTempiScuolaSecondariaDiPrimoGrado();
    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	return map;
    }

    public Map<String, String> getIndirizziDiStudioList(String codiceTipologia, String codiceSettore, String biennioTriennio) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceTipologia != null && !codiceTipologia.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getIndirizziDiStudio(codiceTipologia, codiceSettore, biennioTriennio);

    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }

    public Map<String, String> getIndirizziDiStudioCFPList(String codiceTipologia) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceTipologia != null && !codiceTipologia.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getIndirizziDiStudioCFP(codiceTipologia);

    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }

    public Map<String, String> getSettoriScuolaList(String codiceIstituto) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceIstituto != null && !codiceIstituto.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getSettoriScuola(codiceIstituto);

    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }

    public Map<String, String> getSettoriCFPList(String codiceIstituto) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceIstituto != null && !codiceIstituto.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getSettoriCFP(codiceIstituto);

    		//map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }
 
    public Map<String, String> getRegioniList() throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();

    	List<VOCommon> elenco = cercaLaTuaScuolaDAO.getListaRegioni();

    	map.put("", "Seleziona");
    	if (elenco != null){
    		for (VOCommon vo : elenco){
    			map.put(vo.getCodice(), vo.getDescrizione());
    		}
    	}

    	return map;
    }
    
    public Map<String, String> getProvinceList(String codiceRegione) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceRegione != null && !codiceRegione.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getListaProvincePerRegione(codiceRegione);

    		map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }

    public Map<String, String> getComuniList(String codiceProvincia) throws Exception{
    	Map<String, String> map = new LinkedHashMap<String, String>();


    	if (codiceProvincia != null && !codiceProvincia.equals("")){

    		List<VOCommon> elenco = cercaLaTuaScuolaDAO.getListaComuniPerProvincia(codiceProvincia);

    		map.put("", "Seleziona");
    		if (elenco != null){
    			for (VOCommon vo : elenco){
    				map.put(vo.getCodice(), vo.getDescrizione());
    			}
    		}
    	}


    	return map;
    }


}

