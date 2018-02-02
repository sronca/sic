package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.QuadroOrarioDettaglio;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuadroOrarioDettaglioRepository extends JpaRepository<QuadroOrarioDettaglio, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, DES_MAT_SCU, "
									+ " NUN_SAN_PRI_ACO, NUN_SAN_SEC_ACO, NUN_SAN_TER_ACO, "
									+ " NUN_SAN_QUA_ACO, NUN_SAN_QUI_ACO, NUN_SAN_SES_ACO "
									+ " FROM UASARESTA_OWN.TAS1009_QUADROORASCUOLADET "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte "
									+ " AND PRG_PIA_STU = :prgPiaStu ")
	public LinkedList<QuadroOrarioDettaglio> find(@Param("codScuUte") String codScuUte, @Param("prgPiaStu") Long prgPiaStu);


}
