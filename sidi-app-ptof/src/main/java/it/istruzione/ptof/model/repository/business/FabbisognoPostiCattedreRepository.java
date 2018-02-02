package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.FabbisognoPostiCattedre;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FabbisognoPostiCattedreRepository extends JpaRepository<FabbisognoPostiCattedre, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, COD_SCU_ORD, COD_CLC, DES_CLC, QTY_POS FROM ( "
									+ " SELECT COD_SCU_ORD, COD_CLC, DES_CLC, SUM(QTY_POS) QTY_POS "
									+ " FROM UBSOFFFOR_OWN.VBS1150_PTFODPOSDOC "
									+ " WHERE "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " GROUP BY COD_SCU_ORD, COD_CLC, DES_CLC) "
									+ " ORDER BY COD_SCU_ORD, COD_CLC ")
	public LinkedList<FabbisognoPostiCattedre> find(@Param("codScuUtePri") String codScuUtePri);


}
