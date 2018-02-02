package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.DotazioniMultimediali;
import it.istruzione.ptof.model.entity.business.DotazioniMultimedialiPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DotazioniMultimedialiRepository extends JpaRepository<DotazioniMultimediali, DotazioniMultimedialiPK> {
	
	@Query(nativeQuery = true, value= " SELECT "
									+ " DAT_ANN_SCO_RIF, COD_SCU_UTE_PRI, "
									+ " SUM(NUM_CPT) COMPUTER, "
									+ " SUM(NUM_DIS_MOB) DISPOSITIVI_MOBILI, "
									+ " SUM(NUM_LIM) LIM, "
									+ " SUM(NUM_PRI) PROIETTORI_INTERATTIVI "
									+ " FROM TBS1033_DOTAZIONMUSICSCUOLA A, "
									+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL ANAG_ANNO_CORRENTE, "
									+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL ANAG_ANNO_DOTAZIONI "
									+ " WHERE "
									+ " ANAG_ANNO_CORRENTE.COD_SCU_UT = :codScuUt "
									+ " AND ANAG_ANNO_CORRENTE.DAT_ANN_SCO_RIL = :annoScolastico "
									+ " AND ANAG_ANNO_DOTAZIONI.COD_FOR_SCU_APP = ANAG_ANNO_CORRENTE.COD_FOR_SCU_APP "
									+ " AND ANAG_ANNO_DOTAZIONI.DAT_ANN_SCO_RIL = A.DAT_ANN_SCO_RIF "
									+ " AND A.COD_SCU_UTE_PRI = ANAG_ANNO_DOTAZIONI.COD_SCU_UT "
									+ " GROUP BY A.DAT_ANN_SCO_RIF, A.COD_SCU_UTE_PRI ")
	public DotazioniMultimediali findDotazioniMultimedialiByCodiceScuolaAndAnnoScolastico(@Param("codScuUt") String codScuUt, @Param("annoScolastico") long annoScolastico);


}
