package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.ArticolazioneClassiAA;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticolazioneClassiAARepository extends JpaRepository<ArticolazioneClassiAA, String> {

	@Query(nativeQuery = true, value= " SELECT "
									+ " '1' ID, "
									+ " NVL(SUM(QTA_SEZ_N),0) QTA_SEZ_N, "
									+ " NVL(SUM(QTA_SEZ_R),0) QTA_SEZ_R "
									+ " FROM "
									+ " ( "
									+ " SELECT "
									+ " COD_TIP_POS, DES_TIP_POS, "
									+ " NVL(SUM(QTY_SEZ_INF),0) QTA_SEZ_N, "
									+ " 0 QTA_SEZ_R "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='AA' "
									+ " AND COD_TEM_SCU = 'N' "
									+ " GROUP BY COD_TIP_POS, DES_TIP_POS "
									+ " UNION ALL "
									+ " SELECT "
									+ " COD_TIP_POS, DES_TIP_POS, "
									+ " 0 QTA_SEZ_N, "
									+ " NVL(SUM(QTY_SEZ_INF),0) QTA_SEZ_R "
									+ " FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='AA' "
									+ " AND COD_TEM_SCU = 'R' "
									+ " GROUP BY COD_TIP_POS, DES_TIP_POS "
									+ " ) ")
	public LinkedList<ArticolazioneClassiAA> findArticolazioneClassi(@Param("codScuUtePri") String codScuUtePri);


}
