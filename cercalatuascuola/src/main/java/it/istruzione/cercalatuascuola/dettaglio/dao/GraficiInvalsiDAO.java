package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGraficoInvalsi;

import java.util.List;

/**
 * Created by simone on 20/08/2015.
 */
public interface GraficiInvalsiDAO {
    byte[] getGraficoInvalsi(String $codMeccanografico, String $datAnnScoRil, String $materia) throws Exception;

    List<VOGraficoInvalsi> getGraficoInvalsiInfo(String $codMeccanografico, String $datAnnScoRil) throws Exception;
}
