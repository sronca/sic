package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.model.VODocumento;

import java.util.List;


public interface DocumentiDAO {

	public List<VODocumento> getDocumentiScuola(String codScuUt, String datAnnScoRil,String flgBinFile) throws Exception;
	
	public VODocumento getDocumentoScuola(String codScuUt, String datAnnScoRil,
			String codTipFil, String prgDoc)
					throws Exception;


}