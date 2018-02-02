package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.FabbisognoPostiPotenziamento;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FabbisognoPostiPotenziamentoRepository extends JpaRepository<FabbisognoPostiPotenziamento, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM || COD_SCU_ORD ID, COD_SCU_ORD, '' COD_CLC, '' DES_CLC, QTY_POS_POT, QTY_POS_POT_SOS FROM ( "
									+ " SELECT COD_SCU_ORD, SUM(QTY_POS_POT) QTY_POS_POT, SUM(QTY_POS_POT_SOS) QTY_POS_POT_SOS "
									+ " FROM UBSOFFFOR_OWN.VBS1153_PTFODPOSPOT "
									+ " WHERE "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_SCU_ORD = 'EE' "
									+ " GROUP BY COD_SCU_ORD) ")
	public LinkedList<FabbisognoPostiPotenziamento> findEE(@Param("codScuUtePri") String codScuUtePri);

	@Query(nativeQuery = true, value= " SELECT ROWNUM || COD_SCU_ORD ID, COD_SCU_ORD, COD_CLC, DES_CLC, QTY_POS_POT, QTY_POS_POT_SOS FROM ( "
									+ " SELECT COD_SCU_ORD, COD_CLC, DES_CLC, SUM(QTY_POS_POT) QTY_POS_POT, SUM(QTY_POS_POT_SOS) QTY_POS_POT_SOS "
									+ " FROM UBSOFFFOR_OWN.VBS1153_PTFODPOSPOT "
									+ " WHERE "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " AND COD_SCU_ORD <> 'EE' "
									+ " GROUP BY COD_SCU_ORD, COD_CLC, DES_CLC) "
									+ " ORDER BY COD_SCU_ORD, COD_CLC ")
	public LinkedList<FabbisognoPostiPotenziamento> findMMSS(@Param("codScuUtePri") String codScuUtePri);


}
