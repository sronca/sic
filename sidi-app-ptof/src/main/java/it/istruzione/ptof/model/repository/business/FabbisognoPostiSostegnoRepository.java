package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.FabbisognoPostiSostegno;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FabbisognoPostiSostegnoRepository extends JpaRepository<FabbisognoPostiSostegno, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, COD_SCU_ORD, QTY_POS_SOS_UD, QTY_POS_SOS_VI, QTY_POS_SOS_PF, QTY_POS_SOS_TOT FROM ( "
									+ " SELECT COD_SCU_ORD, SUM(QTY_POS_SOS_UD) QTY_POS_SOS_UD, SUM(QTY_POS_SOS_VI) QTY_POS_SOS_VI, "
									+ " SUM(QTY_POS_SOS_PF) QTY_POS_SOS_PF, SUM(QTY_POS_SOS_TOT) QTY_POS_SOS_TOT "
									+ " FROM UBSOFFFOR_OWN.VBS1151_PTFODPOSDOCSOS "
									+ " WHERE "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " GROUP BY COD_SCU_ORD) "
									+ " ORDER BY COD_SCU_ORD ")
	public LinkedList<FabbisognoPostiSostegno> find(@Param("codScuUtePri") String codScuUtePri);


}
