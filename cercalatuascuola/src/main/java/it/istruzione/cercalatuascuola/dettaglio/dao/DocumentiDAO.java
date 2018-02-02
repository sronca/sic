package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumento;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipoDocumento;

import java.util.List;

public interface DocumentiDAO {
	List<VOTipoDocumento> getTipologieDocumento(String codScuUt, String datAnnScoRil, String flgBinFile)
	throws Exception;

	VOTipoDocumento getTipologiaDocumento(String codTipFile)
	throws Exception;

	List<VODocumento> getDocumentiScuola(String codScuUt, String datAnnScoRil,
										 String flgBinFile)
	throws Exception;

	VODocumento getPTOFScuola(String codScuUt, String datAnnScoRil, String flgBinFile)
	throws Exception;

	VODocumento getDocumentoScuola(String codScuUt, String datAnnScoRil,
								   String codTipFil, String prgDoc)
	throws Exception;

	int countDocumentoScuola(String codScuUt, String datAnnScoRil,
								   String codTipFil, String prgDoc)
	throws Exception;

	VODocumento getBlobLibro(String idLibro)	throws Exception;
}
