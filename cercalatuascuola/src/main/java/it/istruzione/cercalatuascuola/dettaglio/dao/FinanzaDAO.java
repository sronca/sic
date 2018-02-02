package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOFinanza;

import java.util.List;

/**
 * Created by simone on 30/08/2015.
 */
public interface FinanzaDAO {
	List<VOFinanza> getEntratePerFontiFin(String codForScuApp)
	throws Exception;

	List<VOFinanza> getEntratePerFontiFinSpesa(String codForScuApp)
	throws Exception;
}
