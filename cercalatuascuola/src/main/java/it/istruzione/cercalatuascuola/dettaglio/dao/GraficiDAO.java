package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;

import java.util.List;

public interface GraficiDAO {
	List<VOGrafico> getListaGraficiScuola(String codScuUt, String datAnnScoRil,
										  String codSch, String codSez)
            throws Exception;

    VOGrafico getGraficoScuola(String codScuUt, String datAnnScoRil,
                               String codSch, String codSez, String codGra)
            throws Exception;
}
