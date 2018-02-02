package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1040Fabpostopotenziamento;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1040FabpostopotenziamentoRepository extends JpaRepository<Tbs1040Fabpostopotenziamento, Long> {
	
	LinkedList<Tbs1040Fabpostopotenziamento> findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesClcAsc(Long prgDatPtf, String codOrdScu);

	LinkedList<Tbs1040Fabpostopotenziamento> findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuAndDesClcNotOrderByDesClcAsc(Long prgDatPtf, String codOrdScu, String desClc);
	
	LinkedList<Tbs1040Fabpostopotenziamento> findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuAndDesClcOrderByDesClcAsc(Long prgDatPtf, String codOrdScu, String desClc);
	
	
}
