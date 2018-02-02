package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTracciaInvalsi;

import java.util.List;

/**
 * Created by simone on 20/08/2015.
 */
public interface TabelleInvalsiDAO {
    List<VOTracciaInvalsi> getTabelleInvalsi(String $codMeccanografico, String $datAnnScoRil) throws Exception;

    VOGrafico getTitoloGrafici(String codScheda, String codSezione, String codGra) throws Exception;
}
