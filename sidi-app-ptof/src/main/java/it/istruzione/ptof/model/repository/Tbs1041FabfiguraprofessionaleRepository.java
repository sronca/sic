package it.istruzione.ptof.model.repository;

import java.util.LinkedList;

import it.istruzione.ptof.model.entity.Tbs1040Fabpostopotenziamento;
import it.istruzione.ptof.model.entity.Tbs1041Fabfiguraprofessionale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1041FabfiguraprofessionaleRepository extends JpaRepository<Tbs1041Fabfiguraprofessionale, Long> {
	
	LinkedList<Tbs1041Fabfiguraprofessionale> findByTbs1004DatisezionesottosezPrgDatPtfOrderByDesFigPrfAsc(Long prgDatPtf);

}
