package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.IndirizzoStudio;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IndirizzoStudioRepository extends JpaRepository<IndirizzoStudio, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, COD_IND_MIN INDIRIZZO, DES_IND_MIN DESCRIZIONE, DAT_INI_ERO_IND ANNO_INIZIO, DAT_FIN_ERO_IND ANNO_FINE "
									+ " FROM UASARESTA_OWN.TAS1006_INDIRIZZOSCUOLA "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte ")
	public LinkedList<IndirizzoStudio> find(@Param("codScuUte") String codScuUte);


}
