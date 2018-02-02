package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.CountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountEntityRepository extends JpaRepository<CountEntity, String> {
	
	@Query(" SELECT count(tbs1002) "
			+ " FROM "
			+ " Tbs1002Gestioneptof tbs1002 "
			+ " WHERE "
			+ " tbs1002.id.prgGesCatDoc = :prgGesCatDoc "
			+ " AND (TRIM(tbs1002.mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.mfg1012Regione.codReg) = :codReg OR :codReg IS NULL) "
			+ " AND (tbs1002.mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.codPrv = :codPrv OR :codPrv IS NULL) "
			+ " AND (tbs1002.mfg1002Anagistscol.mfg1014Comune.codCom = :codCom OR :codCom IS NULL) "
			+ " AND (tbs1002.id.codScuUte = :codScuUt OR :codScuUt IS NULL) "
			+ " AND (tbs1002.tbs1006Tipostato.codSta = :codSta OR :codSta IS NULL) ")
	public Integer findCountTbs1002GestioneptofByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
												   @Param("codReg") String codReg,
												   @Param("codPrv") String codPrv,
												   @Param("codCom") String codCom,
												   @Param("codScuUt") String codScuUt,
												   @Param("codSta") String codSta
												   );

	@Query(" SELECT count(mfg1002) "
			+ " FROM "
			+ " Mfg1002Anagistscol mfg1002 "
			+ " WHERE "
			+ " mfg1002.id.datAnnScoRil = :annoScolastico "
			+ " AND mfg1002.id.codScuUt = mfg1002.codScuUtPri "
			+ " AND mfg1002.flgIstSta = '1' "
			+ " AND (mfg1002.codCarScu IS NULL OR mfg1002.codCarScu  <> '1' ) "
			+ " AND (TRIM(mfg1002.mfg1014Comune.mfg1029Provnuoist.mfg1012Regione.codReg) = :codReg OR :codReg IS NULL) "
			+ " AND (mfg1002.mfg1014Comune.mfg1029Provnuoist.codPrv = :codPrv OR :codPrv IS NULL) "
			+ " AND (mfg1002.mfg1014Comune.codCom = :codCom OR :codCom IS NULL) ")
	public Integer findCountMfg1002AnagistscolByFilter(@Param("annoScolastico") Long annoScolastico,
														   @Param("codReg") String codReg,
														   @Param("codPrv") String codPrv,
														   @Param("codCom") String codCom
														   );
	
	@Query(nativeQuery = true, value = " SELECT "
									+ " :codReg || :scuOrd || :prgGesCatDoc ID, "
									+ " NVL(SUM(TBS1037.NUM_POS_PRI),0) NUM_POS_PRI, "
									+ " NVL(SUM(TBS1037.NUM_POS_SEC),0) NUM_POS_SEC, "
									+ " NVL(SUM(TBS1037.NUM_POS_TER),0) NUM_POS_TER "
									+ " FROM UBSOFFFOR_OWN.TBS1037_FABPOSTOCOMUNE TBS1037, "
									+ " UBSOFFFOR_OWN.TBS1004_DATISEZIONESOTTOSEZ TBS1004, "
									+ " UBSOFFFOR_OWN.TBS1002_GESTIONEPTOF TBS1002, "
									+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL MFG1002, "
									+ " UFGFUNGEN_OWN.MFG1014_COMUNE MFG1014, "
									+ " UFGFUNGEN_OWN.MFG1029_PROVNUOIST MFG1029 "
									+ " WHERE "
									+ " TBS1037.PRG_DAT_PTF = TBS1004.PRG_DAT_PTF "
									+ " AND TBS1004.COD_SCU_UTE = TBS1002.COD_SCU_UTE "
									+ " AND TBS1004.PER_ANN_SCO = TBS1002.PER_ANN_SCO "
									+ " AND TBS1004.PRG_GES_CAT_DOC = TBS1002.PRG_GES_CAT_DOC "
									+ " AND (TBS1002.COD_STA = '6' OR TBS1002.COD_STA = '7' OR TBS1002.COD_STA = '10') "
									+ " AND TBS1002.COD_SCU_UTE = MFG1002.COD_SCU_UT "
									+ " AND TBS1002.PER_ANN_SCO = MFG1002.DAT_ANN_SCO_RIL "
									+ " AND MFG1002.COD_COM = MFG1014.COD_COM "
									+ " AND MFG1014.COD_PRV_NIS = MFG1029.COD_PRV "
									+ " AND (TBS1037.COD_ORD_SCU = :scuOrd OR :scuOrd IS NULL) "
									+ " AND TBS1002.PRG_GES_CAT_DOC = :prgGesCatDoc "
									+ " AND TRIM(MFG1029.COD_REG) = :codReg ")
	public CountEntity findFabbisogniPostiComuni(@Param("prgGesCatDoc") Long prgGesCatDoc,
											     @Param("codReg") String codReg,
											     @Param("scuOrd") String scuOrd
											     );

	@Query(nativeQuery = true, value = " SELECT "
										+ " 'FabbisogniPostiSostegno' || :codReg || :prgGesCatDoc ID, "
										+ " NVL(SUM(NVL(TBS1039.NUM_POS_UDI_PRI,0) + NVL(TBS1039.NUM_POS_VIS_PRI,0) + NVL(TBS1039.NUM_POS_PSI_PRI,0) + NVL(TBS1039.NUM_POS_SSG_PRI,0)),0) NUM_POS_PRI, "
										+ " NVL(SUM(NVL(TBS1039.NUM_POS_UDI_SEC,0) + NVL(TBS1039.NUM_POS_VIS_SEC,0) + NVL(TBS1039.NUM_POS_PSI_SEC,0) + NVL(TBS1039.NUM_POS_SSG_SEC,0)),0) NUM_POS_SEC, "
										+ " NVL(SUM(NVL(TBS1039.NUM_POS_UDI_TER,0) + NVL(TBS1039.NUM_POS_VIS_TER,0) + NVL(TBS1039.NUM_POS_PSI_TER,0) + NVL(TBS1039.NUM_POS_SSG_TER,0)),0) NUM_POS_TER "
										+ " FROM UBSOFFFOR_OWN.TBS1039_FABPOSTOSOSTEGNO TBS1039, "
										+ " UBSOFFFOR_OWN.TBS1004_DATISEZIONESOTTOSEZ TBS1004, "
										+ " UBSOFFFOR_OWN.TBS1002_GESTIONEPTOF TBS1002, "
										+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL MFG1002, "
										+ " UFGFUNGEN_OWN.MFG1014_COMUNE MFG1014, "
										+ " UFGFUNGEN_OWN.MFG1029_PROVNUOIST MFG1029 "
										+ " WHERE "
										+ " TBS1039.PRG_DAT_PTF = TBS1004.PRG_DAT_PTF "
										+ " AND TBS1004.COD_SCU_UTE = TBS1002.COD_SCU_UTE "
										+ " AND TBS1004.PER_ANN_SCO = TBS1002.PER_ANN_SCO "
										+ " AND TBS1004.PRG_GES_CAT_DOC = TBS1002.PRG_GES_CAT_DOC "
										+ " AND (TBS1002.COD_STA = '6' OR TBS1002.COD_STA = '7' OR TBS1002.COD_STA = '10') "
										+ " AND TBS1002.COD_SCU_UTE = MFG1002.COD_SCU_UT "
										+ " AND TBS1002.PER_ANN_SCO = MFG1002.DAT_ANN_SCO_RIL "
										+ " AND MFG1002.COD_COM = MFG1014.COD_COM "
										+ " AND MFG1014.COD_PRV_NIS = MFG1029.COD_PRV "
										+ " AND TBS1002.PRG_GES_CAT_DOC = :prgGesCatDoc "
										+ " AND TRIM(MFG1029.COD_REG) = :codReg ")
	public CountEntity findFabbisogniPostiSostegno(@Param("prgGesCatDoc") Long prgGesCatDoc,
											       @Param("codReg") String codReg
											       );

	@Query(nativeQuery = true, value = " SELECT "
										+ " 'FabbisogniPostiPotenziamentoAlSostegno' || :codReg || :prgGesCatDoc ID, "
										+ " NVL(SUM(NVL(TBS1040.NUM_POS_SOS_PRI,0)),0) NUM_POS_PRI, "
										+ " NVL(SUM(NVL(TBS1040.NUM_POS_SOS_SEC,0)),0) NUM_POS_SEC, "
										+ " NVL(SUM(NVL(TBS1040.NUM_POS_SOS_TER,0)),0) NUM_POS_TER "
										+ " FROM UBSOFFFOR_OWN.TBS1040_FABPOSTOPOTENZIAMENTO TBS1040, "
										+ " UBSOFFFOR_OWN.TBS1004_DATISEZIONESOTTOSEZ TBS1004, "
										+ " UBSOFFFOR_OWN.TBS1002_GESTIONEPTOF TBS1002, "
										+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL MFG1002, "
										+ " UFGFUNGEN_OWN.MFG1014_COMUNE MFG1014, "
										+ " UFGFUNGEN_OWN.MFG1029_PROVNUOIST MFG1029 "
										+ " WHERE "
										+ " TBS1040.PRG_DAT_PTF = TBS1004.PRG_DAT_PTF "
										+ " AND TBS1004.COD_SCU_UTE = TBS1002.COD_SCU_UTE "
										+ " AND TBS1004.PER_ANN_SCO = TBS1002.PER_ANN_SCO "
										+ " AND TBS1004.PRG_GES_CAT_DOC = TBS1002.PRG_GES_CAT_DOC "
										+ " AND (TBS1002.COD_STA = '6' OR TBS1002.COD_STA = '7' OR TBS1002.COD_STA = '10') "
										+ " AND TBS1002.COD_SCU_UTE = MFG1002.COD_SCU_UT "
										+ " AND TBS1002.PER_ANN_SCO = MFG1002.DAT_ANN_SCO_RIL "
										+ " AND MFG1002.COD_COM = MFG1014.COD_COM "
										+ " AND MFG1014.COD_PRV_NIS = MFG1029.COD_PRV "
										+ " AND TBS1002.PRG_GES_CAT_DOC = :prgGesCatDoc "
										+ " AND TRIM(MFG1029.COD_REG) = :codReg ")
	public CountEntity findFabbisogniPostiPotenziamentoAlSostegno(@Param("prgGesCatDoc") Long prgGesCatDoc,
														          @Param("codReg") String codReg
														          );

	@Query(nativeQuery = true, value = " SELECT "
			+ " 'FabbisogniPostiPotenziamento' || :codReg || :prgGesCatDoc || :scuOrd ID, "
			+ " NVL(SUM(NVL(TBS1040.NUM_POS_COM_PRI,0)),0) NUM_POS_PRI, "
			+ " NVL(SUM(NVL(TBS1040.NUM_POS_COM_SEC,0)),0) NUM_POS_SEC, "
			+ " NVL(SUM(NVL(TBS1040.NUM_POS_COM_TER,0)),0) NUM_POS_TER "
			+ " FROM UBSOFFFOR_OWN.TBS1040_FABPOSTOPOTENZIAMENTO TBS1040, "
			+ " UBSOFFFOR_OWN.TBS1004_DATISEZIONESOTTOSEZ TBS1004, "
			+ " UBSOFFFOR_OWN.TBS1002_GESTIONEPTOF TBS1002, "
			+ " UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL MFG1002, "
			+ " UFGFUNGEN_OWN.MFG1014_COMUNE MFG1014, "
			+ " UFGFUNGEN_OWN.MFG1029_PROVNUOIST MFG1029 "
			+ " WHERE "
			+ " TBS1040.PRG_DAT_PTF = TBS1004.PRG_DAT_PTF "
			+ " AND TBS1004.COD_SCU_UTE = TBS1002.COD_SCU_UTE "
			+ " AND TBS1004.PER_ANN_SCO = TBS1002.PER_ANN_SCO "
			+ " AND TBS1004.PRG_GES_CAT_DOC = TBS1002.PRG_GES_CAT_DOC "
			+ " AND (TBS1002.COD_STA = '6' OR TBS1002.COD_STA = '7' OR TBS1002.COD_STA = '10') "
			+ " AND TBS1002.COD_SCU_UTE = MFG1002.COD_SCU_UT "
			+ " AND TBS1002.PER_ANN_SCO = MFG1002.DAT_ANN_SCO_RIL "
			+ " AND MFG1002.COD_COM = MFG1014.COD_COM "
			+ " AND MFG1014.COD_PRV_NIS = MFG1029.COD_PRV "
			+ " AND (TBS1040.COD_ORD_SCU = :scuOrd OR :scuOrd IS NULL) "
			+ " AND TBS1002.PRG_GES_CAT_DOC = :prgGesCatDoc "
			+ " AND TRIM(MFG1029.COD_REG) = :codReg ")
	public CountEntity findFabbisogniPostiPotenziamento(@Param("prgGesCatDoc") Long prgGesCatDoc,
											            @Param("codReg") String codReg,
													    @Param("scuOrd") String scuOrd
											            );

}
