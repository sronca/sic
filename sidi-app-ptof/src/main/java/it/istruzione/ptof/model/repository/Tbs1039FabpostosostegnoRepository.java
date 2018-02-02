package it.istruzione.ptof.model.repository;

import java.util.LinkedList;

import it.istruzione.ptof.model.entity.Tbs1037Fabpostocomune;
import it.istruzione.ptof.model.entity.Tbs1039Fabpostosostegno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1039FabpostosostegnoRepository extends JpaRepository<Tbs1039Fabpostosostegno, Long> {
	
	LinkedList<Tbs1039Fabpostosostegno> findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesAreDisAsc(Long prgDatPtf, String codOrdScu);

}
