package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.util.List;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOCaratteristica;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOMenu;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;

public interface CaratteristicaDiplomatoDAO {

	public List<VOMenu> getMenu(List<VOTipologia> listaVOTipologia, Integer periodoAnnoScolastico) throws Exception;

	public List<VOCaratteristica> getListaCompetenza(String codiceIndirizzoEsame, Integer periodoAnnoScolastico,
			String codiceTitolo) throws Exception;

	public List<VOCaratteristica> getListaProfiloDiplomato(String codiceIndirizzoEsame, Integer periodoAnnoScolastico)
			throws Exception;

}
