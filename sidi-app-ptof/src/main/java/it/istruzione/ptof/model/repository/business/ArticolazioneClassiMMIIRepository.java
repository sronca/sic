package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.ArticolazioneClassiMMII;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticolazioneClassiMMIIRepository extends JpaRepository<ArticolazioneClassiMMII, String> {

	@Query(nativeQuery = true, value= " SELECT "
									+ " DES_SET_ECO, "
									+ " NVL(SUM(QTY_CL1),0) QTY_CL1, "
									+ " NVL(SUM(QTY_CL2),0) QTY_CL2, "
									+ " NVL(SUM(QTY_CL3),0) QTY_CL3, "
									+ " NVL(SUM(QTY_CL4),0) QTY_CL4, "
									+ " NVL(SUM(QTY_CL5),0) QTY_CL5, "
									+ " NVL(SUM(QTY_CL6),0) QTY_CL6 "
									+ " FROM "
									+ " UBSOFFFOR_OWN.VBS1149_PTFODACLA "
									+ " Where "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_TIP_SCU='SS' "
									+ " AND DES_SET_ECO IS NOT NULL "
									+ " GROUP BY DES_SET_ECO ")
	public LinkedList<ArticolazioneClassiMMII> findArticolazioneClassi(@Param("codScuUtePri") String codScuUtePri);

}
