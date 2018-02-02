package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1001Gestionecatalogodoc;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1001GestionecatalogodocRepository extends JpaRepository<Tbs1001Gestionecatalogodoc, Long> {
	
	//@Query("SELECT v FROM Tfs1010Anagtipobando v where v.tfs1036Anagtipointervento.prgTipInt = ?1 ")
	//public List<Tbs1001Gestionecatalogodoc> findByTipoIntervento(Long $idTipInt);
	
	public LinkedList<Tbs1001Gestionecatalogodoc> findByDatIniValBeforeAndDatFinValAfter(Date datIniVal, Date datFinVal);


}
