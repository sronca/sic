package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttrezzatureMultimediali;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologiaServizio;

import java.util.List;

public interface ServiziDAO {
	List<VOTipologiaServizio> getServiziAttivi(String codScuUt, String datAnnScoRil)
	throws Exception;

	List<VOTipologiaServizio> getServiziAttiviPerTipologia(String codTipSer, String codScuUt, String datAnnScoRil)
	throws Exception;

	VOAttrezzatureMultimediali getAttrezzatureMultimediali(String codForScuApp)
	throws Exception;

	Integer getNumeroAlunni(String codForScuApp)
	throws Exception;

	String getVisibilitaGrafici(String codGra) throws Exception;

	String getTitoloGrafici(String codGra) throws Exception;

	String getDescrizioneGrafici(String codGra) throws Exception;

	VOAttrezzatureMultimediali getAttrezzatureMultimedialiNewPlesso(String codForScuApp)
	throws Exception;

	VOAttrezzatureMultimediali getAttrezzatureMultimedialiNew(String codForScuApp)
	throws Exception;

	String getTrattino(String val) throws Exception;
}
