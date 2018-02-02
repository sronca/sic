package it.istruzione.ptof.model.repository.business;

import java.util.List;

import it.istruzione.ptof.model.entity.business.Scuola;
import it.istruzione.ptof.model.entity.business.ScuolaPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScuolaRepository extends JpaRepository<Scuola, ScuolaPK> {
	
	@Query(nativeQuery = true, value= " SELECT A.COD_SCU_UT_PRI, A.COD_SCU_UT, A.DAT_ANN_SCO_RIL, A.COD_FOR_SCU_APP, A.DES_NOM_SCU, A.DES_IND_SCU, A.DES_TIP_IST, B.DES_COM, C.DES_PRV,"
									+ " A.COD_TEL_SCU, A.COD_FAX_SCU, A.DES_IND_EML, A.DES_IND_WEB, A.DES_IND_EMA_PCE, D.COMP_COGN || ' ' || D.COMP_NOME DIRIGENTE "
									+ " FROM UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL A "
									+ " LEFT OUTER JOIN UFGFUNGEN_OWN.MFG1001_ASSRESISTSCOL D ON ( "
									+ " D.COD_FOR_SCU_APP = A.COD_FOR_SCU_APP "
									+ " and (D.dat_fin_inc > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') and D.dat_fin_att > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999')) "
									+ " and D.dat_ini_inc < TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') "
									+ " and D.cod_tip_inc in ('04','06','07') "
									+ " ), "
									+ " MFG1014_COMUNE B, UFGFUNGEN_OWN.MFG1029_PROVNUOIST C "
									+ " WHERE "
									+ " A.COD_COM = B.COD_COM "
									+ " AND B.COD_PRV_NIS = C.COD_PRV "
									+ " AND A.DAT_ANN_SCO_RIL = :annoScolastico "
									+ " AND A.COD_FOR_SCU_APP = :codForScuApp "
									+ " AND A.COD_SCU_UT = A.COD_SCU_UT_PRI "
									+ " AND (A.DAT_FIN_VAL IS NULL OR A.DAT_FIN_VAL > SYSDATE) "
									+ " AND (A.COD_CAR_SCU IS NULL OR A.COD_CAR_SCU <> '1') "
									+ " ORDER BY D.cod_tip_inc DESC ")
	public Scuola findIstitutoPrincipaleByCodiceForteAndAnnoScolastico(@Param("codForScuApp") String codForScuApp, @Param("annoScolastico") int annoScolastico);


	@Query(nativeQuery = true, value= " SELECT A.COD_SCU_UT_PRI, A.COD_SCU_UT, A.DAT_ANN_SCO_RIL, A.COD_FOR_SCU_APP, A.DES_NOM_SCU, A.DES_IND_SCU, A.DES_TIP_IST, B.DES_COM, C.DES_PRV,"
									+ " A.COD_TEL_SCU, A.COD_FAX_SCU, A.DES_IND_EML, A.DES_IND_WEB, A.DES_IND_EMA_PCE, ' ' DIRIGENTE "
									+ " FROM UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL A, MFG1014_COMUNE B, UFGFUNGEN_OWN.MFG1029_PROVNUOIST C "
									+ " WHERE "
									+ " A.COD_COM = B.COD_COM "
									+ " AND B.COD_PRV_NIS = C.COD_PRV "
									+ " AND DAT_ANN_SCO_RIL = :annoScolastico "
									+ " AND COD_SCU_UT_PRI = :codScuUtPri "
									+ " AND (A.COD_CAR_SCU IS NULL OR A.COD_CAR_SCU <> '1') "
									+ " AND (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL > SYSDATE) ")
	public List<Scuola> findPlessiByCodiceMeccanograficoIstitutoPrincipaleAndAnnoScolastico(@Param("codScuUtPri") String codScuUtPri, @Param("annoScolastico") int annoScolastico);

	@Query(nativeQuery = true, value= " SELECT A.COD_SCU_UT_PRI, A.COD_SCU_UT, A.DAT_ANN_SCO_RIL, A.COD_FOR_SCU_APP, A.DES_NOM_SCU, A.DES_IND_SCU, A.DES_TIP_IST, B.DES_COM, C.DES_PRV,"
									+ " A.COD_TEL_SCU, A.COD_FAX_SCU, A.DES_IND_EML, A.DES_IND_WEB, A.DES_IND_EMA_PCE, ' ' DIRIGENTE "
									+ " FROM UFGFUNGEN_OWN.MFG1002_ANAGISTSCOL A, MFG1014_COMUNE B, UFGFUNGEN_OWN.MFG1029_PROVNUOIST C "
									+ " WHERE "
									+ " A.COD_COM = B.COD_COM "
									+ " AND B.COD_PRV_NIS = C.COD_PRV "
									+ " AND DAT_ANN_SCO_RIL = :annoScolastico "
									+ " AND COD_SCU_UT = :codScuUt "
									+ " AND (A.COD_CAR_SCU IS NULL OR A.COD_CAR_SCU <> '1') "
									+ " AND (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL > SYSDATE) ")
	public Scuola findPlessoByCodiceMeccanograficoAndAnnoScolastico(@Param("codScuUt") String codScuUt, @Param("annoScolastico") int annoScolastico);


}
