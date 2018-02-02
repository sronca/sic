package it.istruzione.poninchiaro.service;

import it.istruzione.poninchiaro.dao.DocumentiDAO;
import it.istruzione.poninchiaro.model.VODocumento;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentiServiceImpl implements DocumentiService {
	
    private Logger log = Logger.getLogger(DocumentiServiceImpl.class);
    private DocumentiDAO documentiDAO;

    @Autowired
    public DocumentiServiceImpl(DocumentiDAO documentiDAO) {
        this.documentiDAO = documentiDAO;
    }
    
    public List<VODocumento> getDocumentiScuola(String codScuUt, String datAnnScoRil,String flgBinFile) throws Exception{
    	return documentiDAO.getDocumentiScuola(codScuUt, datAnnScoRil, flgBinFile);
    }
    
    public VODocumento getDocumentoScuola(String codScuUt, String datAnnScoRil,String codTipFil, String prgDoc) throws Exception{
    	return documentiDAO.getDocumentoScuola(codScuUt, datAnnScoRil, codTipFil, prgDoc);
    }
    
}

