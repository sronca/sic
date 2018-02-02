package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;

import java.util.List;

public interface PersonaleDAO {
	VOTrasferimento getIndicatoriPersonaleIstogramma1(String codForScuApp) throws Exception;

	VOPensione getIndicatoriPersonaleIstogramma2(String codForScuApp)
	throws Exception;

	List<VOAssenza> getAssenzeDocenti(String codForScuApp)
	throws Exception;

	List<VOAssenza> getAssenzeATA(String codForScuApp)
	throws Exception;

	String getTipologieMalattia(String codForScuApp)
	throws Exception;

	Integer getNumTotDocenti(String codForScuApp) throws Exception;

	List<VOFascia> getIndicatoriIstogrammaFascia(String codForScuApp, Integer totDoc)
	throws Exception;

	VOPersonale getIndicatoriATATabelle(String codForScuApp)
	throws Exception;

	VOPersonale getIndicatoriDocentiTabelle(String codForScuApp)
	throws Exception;
}
