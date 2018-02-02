package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.ImportoTotale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImportoTotaleRepository extends JpaRepository<ImportoTotale, String> {

	@Query(nativeQuery = true, value= " SELECT '1' KEY, NVL(SUM(A.IMP_STI),0) IMP "
									+ " FROM TBS1021_AMBITOPROGETTOPTOF A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.PRG_PGT_AMB = :prgPgtAmb "
									+ " AND A.DES_NOM_BEN_SER IS NOT NULL ")
	public ImportoTotale findImportoTotalePerTipoProgetto(@Param("codScuUtePri") String codScuUtePri, @Param("prgPgtAmb") Long prgPgtAmb);
	
	@Query(nativeQuery = true, value= " SELECT '1' KEY, NVL(SUM(A.IMP_STI),0) IMP "
									+ " FROM TBS1022_AMBITOPROGETTOALTRO A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.DES_DEN_PGT_ALT = :desTipoProgetto "
									+ " AND A.DES_NOM_BEN_SER IS NOT NULL ")
	public ImportoTotale findImportoTotalePerTipoAltroProgetto(@Param("codScuUtePri") String codScuUtePri, @Param("desTipoProgetto") String desTipoProgetto);
	
	@Query(nativeQuery = true, value= " SELECT '1' KEY, NVL(SUM(A.IMP_STI),0) IMP "
									+ " FROM TBS1027_PERFORMAZPERSONALE A, TBS1004_DATISEZIONESOTTOSEZ B "
									+ " WHERE "
									+ " A.PRG_DAT_PTF = B.PRG_DAT_PTF "
									+ " AND B.COD_SCU_UTE = :codScuUtePri "
									+ " AND A.COD_TIP_AMB = :codTipAmb"
									+ " AND A.DES_NOM_BEN_SER IS NOT NULL ")
	public ImportoTotale findImportoTotalePerAmbitoFormativo(@Param("codScuUtePri") String codScuUtePri, @Param("codTipAmb") String codTipAmb);


}
