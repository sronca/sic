package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.ArticolazioneClassiMMI;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticolazioneClassiMMIRepository extends JpaRepository<ArticolazioneClassiMMI, Long> {

	@Query(nativeQuery = true, value= " SELECT "
									+ " 1 ID, "
									+ " NVL(SUM(QTY_CL1_N),0) QTY_CL1_N, "
									+ " NVL(SUM(QTY_CL2_N),0) QTY_CL2_N,"
									+ " NVL(SUM(QTY_CL3_N),0) QTY_CL3_N, "
									+ " NVL(SUM(QTY_CL1_P),0) QTY_CL1_P, "
									+ " NVL(SUM(QTY_CL2_P),0) QTY_CL2_P, "
									+ " NVL(SUM(QTY_CL3_P),0) QTY_CL3_P "
									+ " FROM "
									+ " ( "
									+ " SELECT "
									+ " NVL(SUM(QTY_CL1),0) QTY_CL1_N, "
									+ " NVL(SUM(QTY_CL2),0) QTY_CL2_N, "
									+ " NVL(SUM(QTY_CL3),0) QTY_CL3_N, "
									+ " 0 QTY_CL1_P, "
									+ " 0 QTY_CL2_P, "
									+ " 0 QTY_CL3_P "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='MM' "
									+ " AND COD_TEM_SCU = 'O' "
									+ " UNION ALL "
									+ " SELECT "
									+ " 0 QTY_CL1_N, "
									+ " 0 QTY_CL2_N, "
									+ " 0 QTY_CL3_N, "
									+ " NVL(SUM(QTY_CL1),0) QTY_CL1_P, "
									+ " NVL(SUM(QTY_CL2),0) QTY_CL2_P, "
									+ " NVL(SUM(QTY_CL3),0) QTY_CL3_P "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='MM' "
									+ " AND COD_TEM_SCU = 'P' "
									+ " ) ")
	public LinkedList<ArticolazioneClassiMMI> findArticolazioneClassi(@Param("codScuUtePri") String codScuUtePri);

}
