package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.ArticolazioneClassiEE;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticolazioneClassiEERepository extends JpaRepository<ArticolazioneClassiEE, String> {

	@Query(nativeQuery = true, value= " SELECT "
									+ " '1' ID, "
									+ " NVL(SUM(QTY_CL1_N),0) QTY_CL1_N, "
									+ " NVL(SUM(QTY_CL2_N),0) QTY_CL2_N,"
									+ " NVL(SUM(QTY_CL3_N),0) QTY_CL3_N, "
									+ " NVL(SUM(QTY_CL4_N),0) QTY_CL4_N, "
									+ " NVL(SUM(QTY_CL5_N),0) QTY_CL5_N, "
									+ " NVL(SUM(QTY_PLU_N),0) QTY_PLU_N, "
									+ " NVL(SUM(QTY_CL1_P),0) QTY_CL1_P, "
									+ " NVL(SUM(QTY_CL2_P),0) QTY_CL2_P, "
									+ " NVL(SUM(QTY_CL3_P),0) QTY_CL3_P, "
									+ " NVL(SUM(QTY_CL4_P),0) QTY_CL4_P, "
									+ " NVL(SUM(QTY_CL5_P),0) QTY_CL5_P, "
									+ " NVL(SUM(QTY_PLU_P),0) QTY_PLU_P "
									+ " FROM "
									+ " ( "
									+ " SELECT "
									+ " COD_TIP_POS, DES_TIP_POS, "
									+ " NVL(SUM(QTY_CL1),0) QTY_CL1_N, "
									+ " NVL(SUM(QTY_CL2),0) QTY_CL2_N, "
									+ " NVL(SUM(QTY_CL3),0) QTY_CL3_N, "
									+ " NVL(SUM(QTY_CL4),0) QTY_CL4_N, "
									+ " NVL(SUM(QTY_CL5),0) QTY_CL5_N, "
									+ " NVL(SUM(QTY_PLU),0) QTY_PLU_N, "
									+ " 0 QTY_CL1_P, "
									+ " 0 QTY_CL2_P, "
									+ " 0 QTY_CL3_P, "
									+ " 0 QTY_CL4_P, "
									+ " 0 QTY_CL5_P, "
									+ " 0 QTY_PLU_P "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='EE' "
									+ " AND COD_TEM_SCU = 'N' "
									+ " GROUP BY COD_TIP_POS, DES_TIP_POS "
									+ " UNION ALL "
									+ " SELECT "
									+ " COD_TIP_POS, DES_TIP_POS, "
									+ " 0 QTY_CL1_N, "
									+ " 0 QTY_CL2_N, "
									+ " 0 QTY_CL3_N, "
									+ " 0 QTY_CL4_N, "
									+ " 0 QTY_CL5_N, "
									+ " 0 QTY_PLU_N, "
									+ " NVL(SUM(QTY_CL1),0) QTY_CL1_P, "
									+ " NVL(SUM(QTY_CL2),0) QTY_CL2_P, "
									+ " NVL(SUM(QTY_CL3),0) QTY_CL3_P, "
									+ " NVL(SUM(QTY_CL4),0) QTY_CL4_P, "
									+ " NVL(SUM(QTY_CL5),0) QTY_CL5_P, "
									+ " NVL(SUM(QTY_PLU),0) QTY_PLU_P "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='EE' "
									+ " AND COD_TEM_SCU = 'P' "
									+ " GROUP BY COD_TIP_POS, DES_TIP_POS "
									+ " ) ")
	public LinkedList<ArticolazioneClassiEE> findArticolazioneClassi(@Param("codScuUtePri") String codScuUtePri);

}
