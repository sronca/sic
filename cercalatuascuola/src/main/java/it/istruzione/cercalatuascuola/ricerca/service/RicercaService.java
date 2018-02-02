package it.istruzione.cercalatuascuola.ricerca.service;

import java.util.Map;

public interface RicercaService {

	public Map<String, String> getTipologiaList(String codiceOrdine, String stataleNonStatale) throws Exception;
	
	public Map<String, String> getTempiScuolaPrimariaList() throws Exception;
	
	public Map<String, String> getTempiScuolaSecondariaDiPrimoGrado() throws Exception;
	
	public Map<String, String> getIndirizziDiStudioList(String codiceTipologia, String codiceSettore, String biennioTriennio) throws Exception;
	
	public Map<String, String> getIndirizziDiStudioCFPList(String codiceTipologia) throws Exception;
	
	public Map<String, String> getSettoriScuolaList(String codiceIstituto) throws Exception;
	
	public Map<String, String> getSettoriCFPList(String codiceIstituto) throws Exception;
	
	public Map<String, String> getRegioniList() throws Exception;
	
	public Map<String, String> getProvinceList(String codiceRegione) throws Exception;
	
	public Map<String, String> getComuniList(String codiceProvincia) throws Exception;

}