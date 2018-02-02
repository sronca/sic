package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnnoWrapper;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumentoAVCP;

import java.util.List;

/**
 * Created by simone on 30/08/2015.
 */
public interface AVCPFinanzaDAO {
    List<VOAnnoWrapper> getAnniScolastici(String $codiceMecc);

    List<VOAnnoWrapper> getAnniBilancio(String $codiceMecc, Integer $annoScolastico);

    VODocumentoAVCP getAVCP(String $codiceMecc, Integer $annoScolastico, Integer $annoBilancio);
}
