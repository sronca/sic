package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttivita;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLibro;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPon;

import java.util.List;

public interface DidatticaDAO {
	List<VOAttivita> getListaAttivita(String codScuUt, String datAnnScoRil, String desAtt)
	throws Exception;

	List<VOAttivita> getListaAttivitaIstPrinc(String codScuUt, String datAnnScoRil, String desAtt)
	throws Exception;

	List<VOLibro> getListaLibri(String codScuUt, String datAnnScoRil) throws Exception;

	String getCodiceMeccanograficoAnnoSuccessivo(String codForte, String annoRif) throws Exception;

	String getDesBeneficio(String codBen)
	throws Exception;

	List<VOPon> getListaProgPonFse(String codScuUt)
	throws Exception;

	List<VOPon> getListaProgPonFesr(String codScuUt)
	throws Exception;

	String getTitolo(String codProg, String codFon)
	throws Exception;

	List<VOPon> getListaProgPonInCorsoFse(String codScuUt)
	throws Exception;

	List<VOPon> getListaProgPonInCorsoFesr(String codScuUt)
	throws Exception;
}
