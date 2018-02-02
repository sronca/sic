package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnnoWrapper;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOIndiceDiTempestivitaPagamenti;

import java.util.List;

/**
 * Created by simone on 30/08/2015.
 */
public interface PagamentiAmmDAO {
	List<VOAnnoWrapper> getListaAnniSco(String cod_for_scu_app) throws Exception;

	List<VOIndiceDiTempestivitaPagamenti> getListaIndicatori(String cod_scu_ut,
															 String dat_ann_sco_ril,
															 String annoScolasticoDiRiferimento,
															 String annoFinanziarioDiRiferimento) throws Exception;
}
