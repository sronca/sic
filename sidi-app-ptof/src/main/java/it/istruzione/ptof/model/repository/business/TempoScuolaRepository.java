package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.TempoScuola;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TempoScuolaRepository extends JpaRepository<TempoScuola, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, B.DES_TIP_TSC TEMPO_SCUOLA "
									+ " FROM UASARESTA_OWN.TAS1015_TEMPOSCUOLATRI A "
									+ " INNER JOIN UASARESTA_OWN.TAS1014_TIPOTEMPOSCUOLA B ON (A.PRG_TIP_TSC = B.PRG_TIP_TSC) "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte "
									+ " AND A.ANN_TRI = ( "
									+ " SELECT MAX(A.ANN_TRI) "
									+ " FROM UASARESTA_OWN.TAS1015_TEMPOSCUOLATRI A "
									+ " INNER JOIN UASARESTA_OWN.TAS1014_TIPOTEMPOSCUOLA B ON (A.PRG_TIP_TSC = B.PRG_TIP_TSC) "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte "
									+ " ) ")
	public LinkedList<TempoScuola> find(@Param("codScuUte") String codScuUte);


}
