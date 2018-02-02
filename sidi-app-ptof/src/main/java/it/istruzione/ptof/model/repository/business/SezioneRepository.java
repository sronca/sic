package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.Sezione;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SezioneRepository extends JpaRepository<Sezione, String> {
	
	@Query(nativeQuery = true, value= " SELECT C.PRG_DAT_PTF CHIAVE, A.COD_SEZ_SOT_SEZ, A.DES_SEZ, C.COD_STA, A.PRG_SEZ_SOT_SEZ, A.PRG_SEZ_SOT_SEZ_PAD, A.PRG_SEZ_SOT_SEZ_PLE, C.COD_SCU_UTE_PLE "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND A.PRG_SEZ_SOT_SEZ_PAD IS NULL "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " ORDER BY PRG_ORD ")
	public LinkedList<Sezione> findSezioniScuola(@Param("codScuUt") String codScuUt,
												 @Param("annoScolastico") Long annoScolastico,
												 @Param("prgGesCatDoc") Long prgGesCatDoc);
	
	@Query(nativeQuery = true, value= " SELECT C.PRG_DAT_PTF CHIAVE, A.COD_SEZ_SOT_SEZ, A.DES_SEZ, C.COD_STA, A.PRG_SEZ_SOT_SEZ, A.PRG_SEZ_SOT_SEZ_PAD, A.PRG_SEZ_SOT_SEZ_PLE, C.COD_SCU_UTE_PLE "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND A.PRG_SEZ_SOT_SEZ_PAD = :prgSezSotSezPad "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " AND C.COD_SCU_UTE_PLE IS NULL "
									+ " ORDER BY PRG_ORD ")
	public LinkedList<Sezione> findSottoSezioniScuola(@Param("codScuUt") String codScuUt,
			 										  @Param("annoScolastico") Long annoScolastico,
			 										  @Param("prgGesCatDoc") Long prgGesCatDoc,
			 										  @Param("prgSezSotSezPad") Long prgSezSotSezPad);
	
	
	@Query(nativeQuery = true, value= " SELECT C.PRG_DAT_PTF CHIAVE, A.COD_SEZ_SOT_SEZ, ANAG.COD_SCU_UT || ' - ' || ANAG.DES_NOM_SCU DES_SEZ, C.COD_STA, A.PRG_SEZ_SOT_SEZ, A.PRG_SEZ_SOT_SEZ_PAD, A.PRG_SEZ_SOT_SEZ_PLE, C.COD_SCU_UTE_PLE "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C, UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL ANAG "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND A.PRG_SEZ_SOT_SEZ = :prgSezSotSez "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " AND ANAG.COD_SCU_UT = C.COD_SCU_UTE_PLE "
									+ " AND ANAG.DAT_ANN_SCO_RIL = C.PER_ANN_SCO "
									+ " ORDER BY ANAG.COD_SCU_UT ")
	public LinkedList<Sezione> findSottoSezioniPlessiScuola(@Param("codScuUt") String codScuUt,
														    @Param("annoScolastico") Long annoScolastico,
														    @Param("prgGesCatDoc") Long prgGesCatDoc,
														    @Param("prgSezSotSez") Long prgSezSotSez);
	
	@Query(nativeQuery = true, value= " SELECT C.PRG_DAT_PTF CHIAVE, A.COD_SEZ_SOT_SEZ, A.DES_SEZ, C.COD_STA, A.PRG_SEZ_SOT_SEZ, A.PRG_SEZ_SOT_SEZ_PAD, A.PRG_SEZ_SOT_SEZ_PLE, C.COD_SCU_UTE_PLE "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " AND A.FLG_VAL = '1' "
									+ " AND C.COD_STA = '9' ")
	public LinkedList<Sezione> findSezioniScuolaObbligatorieNonCompilate(@Param("codScuUt") String codScuUt,
																		 @Param("annoScolastico") Long annoScolastico,
																		 @Param("prgGesCatDoc") Long prgGesCatDoc);


	@Query(nativeQuery = true, value= " SELECT C.PRG_DAT_PTF CHIAVE, A.COD_SEZ_SOT_SEZ, A.DES_SEZ, C.COD_STA, A.PRG_SEZ_SOT_SEZ, A.PRG_SEZ_SOT_SEZ_PAD, A.PRG_SEZ_SOT_SEZ_PLE, C.COD_SCU_UTE_PLE "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND A.PRG_SEZ_SOT_SEZ_PAD IS NULL "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " AND C.COD_STA = '9' "
									+ " AND A.PRG_SEZ_SOT_SEZ IN "
									+ " ( "
									+ " SELECT DISTINCT PRG_SEZ_SOT_SEZ_PAD "
									+ " FROM TBS1003_CATALOGOSEZIONE A, TBS1002_GESTIONEPTOF B, TBS1004_DATISEZIONESOTTOSEZ C "
									+ " WHERE "
									+ " B.COD_SCU_UTE = :codScuUt "
									+ " AND B.PER_ANN_SCO = :annoScolastico "
									+ " AND B.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND A.PRG_SEZ_SOT_SEZ_PAD IS NOT NULL "
									+ " AND B.COD_SCU_UTE = C.COD_SCU_UTE "
									+ " AND B.PER_ANN_SCO = C.PER_ANN_SCO "
									+ " AND B.PRG_GES_CAT_DOC = C.PRG_GES_CAT_DOC "
									+ " AND A.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ "
									+ " AND C.COD_STA = '8' "
									+ " ) ")
	public LinkedList<Sezione> findSezioniScuolaInStatoBozzaAventiSottosezioniCompilate(@Param("codScuUt") String codScuUt,
																					    @Param("annoScolastico") Long annoScolastico,
																					    @Param("prgGesCatDoc") Long prgGesCatDoc);
}
