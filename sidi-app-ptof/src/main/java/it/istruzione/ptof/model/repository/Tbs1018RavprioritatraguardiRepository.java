package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1018Ravprioritatraguardi;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Tbs1018RavprioritatraguardiRepository extends JpaRepository<Tbs1018Ravprioritatraguardi, Long> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, A.* FROM UBSOFFFOR_OWN.TBS1018_RAVPRIORITATRAGUARDI A WHERE A.COD_SCU_UTE = :codScuUte ")
	public LinkedList<Tbs1018Ravprioritatraguardi> findByCodScuUte(@Param("codScuUte") String codScuUte);
}
