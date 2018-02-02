package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.FabbisognoPostiAta;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FabbisognoPostiAtaRepository extends JpaRepository<FabbisognoPostiAta, String> {

	@Query(nativeQuery = true, value= " SELECT ROWNUM ID, COD_PRO_ATA, DES_PRO_ATA, QTY_POS "
									+ " FROM UBSOFFFOR_OWN.VBS1152_PTFODPOSATA "
									+ " WHERE "
									+ " COD_SCU_UT_PRI = :codScuUtePri "
									+ " ORDER BY DES_PRO_ATA ")
	public LinkedList<FabbisognoPostiAta> find(@Param("codScuUtePri") String codScuUtePri);


}
