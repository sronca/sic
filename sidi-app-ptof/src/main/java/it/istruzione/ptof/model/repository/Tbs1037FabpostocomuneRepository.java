package it.istruzione.ptof.model.repository;

import java.util.LinkedList;

import it.istruzione.ptof.model.entity.Tbs1037Fabpostocomune;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1037FabpostocomuneRepository extends JpaRepository<Tbs1037Fabpostocomune, Long> {
	
	LinkedList<Tbs1037Fabpostocomune> findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesClcAsc(Long prgDatPtf, String codOrdScu);

}
