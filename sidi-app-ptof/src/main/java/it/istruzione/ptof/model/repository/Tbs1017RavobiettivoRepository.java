package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1017Ravobiettivo;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Tbs1017RavobiettivoRepository extends JpaRepository<Tbs1017Ravobiettivo, Long> {
	
	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, A.* FROM UBSOFFFOR_OWN.TBS1017_RAVOBIETTIVO A WHERE A.COD_SCU_UTE = :codScuUte ")
	public LinkedList<Tbs1017Ravobiettivo> findByCodScuUte(@Param("codScuUte") String codScuUte);

}
