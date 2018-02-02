package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEdificio;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEdilizia;

import java.util.List;

public interface EdiliziaDAO {
	List<VOEdificio> getEdifici(String codiceMeccanograficoScuola) throws Exception;
	VOEdificio getEdificio(String codiceEdificio) throws Exception;
	VOEdilizia getVOEdilizia(String codiceEdificio) throws Exception;
	String getMaxAnnoSco() throws Exception;
}
