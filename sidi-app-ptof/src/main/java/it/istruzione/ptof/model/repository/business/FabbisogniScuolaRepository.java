package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.FabbisogniScuola;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FabbisogniScuolaRepository extends JpaRepository<FabbisogniScuola, String> {

	@Query(nativeQuery = true, value= " SELECT DISTINCT 'P' || TO_CHAR(A.PRG_PGT_AMB) KEY, TO_CHAR(A.PRG_PGT_AMB) COD, C.DES_DEN_PGT DES "
									+ " FROM TBS1021_AMBITOPROGETTOPTOF A, TBS1004_DATISEZIONESOTTOSEZ B, TBS1020_CLASSIFPROGETAMBITO C "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.PRG_PGT_AMB = C.PRG_PGT_AMB "
									+ " UNION ALL "
									+ " SELECT DISTINCT 'A' || A.DES_DEN_PGT_ALT KEY, A.DES_DEN_PGT_ALT COD, A.DES_DEN_PGT_ALT DES "
									+ " FROM TBS1022_AMBITOPROGETTOALTRO A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " ORDER BY DES ")
	public LinkedList<FabbisogniScuola> findTipoProgettiScuola(@Param("codScuUtePri") String codScuUtePri);
	
	@Query(nativeQuery = true, value= " SELECT 'P' || TO_CHAR(A.PRG_AMB_PGT_PTO) KEY, A.PRG_AMB_PGT_PTO COD, A.DES_DEN_PGT_CUR DES "
									+ " FROM TBS1021_AMBITOPROGETTOPTOF A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.PRG_PGT_AMB = :prgPgtAmb "
									+ " ORDER BY DES ")
	public LinkedList<FabbisogniScuola> findProgettiScuola(@Param("codScuUtePri") String codScuUtePri, @Param("prgPgtAmb") Long prgPgtAmb);
	
	@Query(nativeQuery = true, value= " SELECT 'A' || TO_CHAR(A.PRG_AMB_PGT_ALT) KEY, A.PRG_AMB_PGT_ALT COD, A.DES_DEN_PGT_CUR DES "
									+ " FROM TBS1022_AMBITOPROGETTOALTRO A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.DES_DEN_PGT_ALT = :desTipoProgetto "
									+ " ORDER BY DES ")
	public LinkedList<FabbisogniScuola> findAltriProgettiScuola(@Param("codScuUtePri") String codScuUtePri, @Param("desTipoProgetto") String desTipoProgetto);
	
	@Query(nativeQuery = true, value= " SELECT DISTINCT A.COD_TIP_AMB KEY, A.COD_TIP_AMB COD, C.DES_TIP_AMB DES "
									+ " FROM TBS1027_PERFORMAZPERSONALE A, TBS1004_DATISEZIONESOTTOSEZ B, TBS1019_TIPOAMBITO C "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.COD_TIP_AMB = C.COD_TIP_AMB "
									+ " ORDER BY DES ")
	public LinkedList<FabbisogniScuola> findAmbitiFormativiScuola(@Param("codScuUtePri") String codScuUtePri);
	
	@Query(nativeQuery = true, value= " SELECT A.PRG_FOR_PER KEY, A.PRG_FOR_PER COD, A.DES_PER_FOR DES "
									+ " FROM TBS1027_PERFORMAZPERSONALE A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.COD_TIP_AMB = :codTipAmb "
									+ " ORDER BY DES ")
	public LinkedList<FabbisogniScuola> findPercorsiFormativiScuola(@Param("codScuUtePri") String codScuUtePri, @Param("codTipAmb") String codTipAmb);
	
	
}
