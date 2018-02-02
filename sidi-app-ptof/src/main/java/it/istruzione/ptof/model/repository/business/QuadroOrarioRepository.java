package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.QuadroOrario;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuadroOrarioRepository extends JpaRepository<QuadroOrario, Long> {

	@Query(nativeQuery = true, value= " SELECT PRG_PIA_STU, COD_IND_MIN INDIRIZZO, PER_ANN_SCO ANNO, DES_PIA_STU PIANO_STUDIO "
									+ " FROM UASARESTA_OWN.TAS1008_QUADROORARIOSCUOLA "
									+ " WHERE "
									+ " SCU_PRV || SCU_ORD || SCU_CIR || SCU_PLE || SCU_CHK = :codScuUte ")
	public LinkedList<QuadroOrario> find(@Param("codScuUte") String codScuUte);


}
