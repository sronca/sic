package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.TipologiaScuola;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipologiaScuolaRepository extends JpaRepository<TipologiaScuola, String> {

	@Query(nativeQuery = true, value= " SELECT DISTINCT COD_TIP_SCU FROM UBSOFFFOR_OWN.VBS1149_PTFODACLA WHERE COD_SCU_UT_PRI = :codScuUtePri AND COD_TIP_SCU = :codTipScu")
	public TipologiaScuola findTipologiaScuola(@Param("codScuUtePri") String codScuUtePri, @Param("codTipScu") String codTipScu);

}
