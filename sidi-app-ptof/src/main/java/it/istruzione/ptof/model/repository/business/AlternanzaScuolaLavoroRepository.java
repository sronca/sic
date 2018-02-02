package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.AlternanzaScuolaLavoro;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlternanzaScuolaLavoroRepository extends JpaRepository<AlternanzaScuolaLavoro, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, A.DES_PER_SCU, A.DES_STR_SCU, A.DES_AZI, B.NUM_ALU_PRI_ANN, B.NUM_ALU_SEC_ANN, B.NUM_ALU_TER_ANN "
									+ " FROM UASARESTA_OWN.TAS1016_PERCORSOSCUOLA A "
									+ " LEFT OUTER JOIN UASARESTA_OWN.TAS1017_PERCORSOSCUOLATRI B ON (A.PRG_PER_SCU = B.PRG_PER_SCU AND A.PRG_STR_SCU = B.PRG_STR_SCU) "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte "
									+ " AND B.ANN_TRI = ( "
									+ " SELECT MAX(B.ANN_TRI) "
									+ " FROM UASARESTA_OWN.TAS1016_PERCORSOSCUOLA A "
									+ " LEFT OUTER JOIN UASARESTA_OWN.TAS1017_PERCORSOSCUOLATRI B ON (A.PRG_PER_SCU = B.PRG_PER_SCU AND A.PRG_STR_SCU = B.PRG_STR_SCU) "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte "
									+ " ) ")
	public LinkedList<AlternanzaScuolaLavoro> find(@Param("codScuUte") String codScuUte);


}
