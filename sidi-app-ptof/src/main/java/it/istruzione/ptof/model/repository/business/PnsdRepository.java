package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.Pnsd;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PnsdRepository extends JpaRepository<Pnsd, String> {

	@Query(nativeQuery = true, value= 
					"	SELECT ROWNUM ID, AREA, COD_SEZ, DES_SEZ, CONTENUTO FROM (                                                                             " +
					"	SELECT                                                                                  " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.COD_ARE_TEM AREA,                                                                                                                 " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Ambito: ' || E.DES_TIP_AMB || ' - Tipo progetto: ' || D.DES_DEN_PGT || ' - Progetto: ' || A.DES_DEN_PGT_CUR CONTENUTO              " +
					"	FROM TBS1021_AMBITOPROGETTOPTOF A,                                                                                                     " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C,                                                                                                        " +
					"		 TBS1020_CLASSIFPROGETAMBITO D,                                                                                                    " +
					"		 TBS1019_TIPOAMBITO E                                                                                                              " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	AND D.COD_TIP_AMB = A.COD_TIP_AMB                                                                                                      " +
					"	AND D.PRG_PGT_AMB = A.PRG_PGT_AMB                                                                                                      " +
					"	AND E.COD_TIP_AMB = D.COD_TIP_AMB                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                               " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.COD_ARE_TEM AREA,                                                                                                                 " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Ambito: ' || E.DES_TIP_AMB || ' - Tipo progetto: ' || A.DES_DEN_PGT_ALT || ' - Progetto: ' || A.DES_DEN_PGT_CUR CONTENUTO          " +
					"	FROM TBS1022_AMBITOPROGETTOALTRO A,                                                                                                    " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C,                                                                                                        " +
					"		 TBS1019_TIPOAMBITO E                                                                                                              " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	AND E.COD_TIP_AMB = A.COD_TIP_AMB                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                              " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PNS AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   A.DES_TIT CONTENUTO                                                                                                                 " +
					"	FROM TBS1023_ALTREINIZIDIDATTICHE A,                                                                                                   " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                         " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Ambito formativo: ' || E.DES_TIP_AMB || ' - Percorso: ' || A.DES_PER_FOR CONTENUTO                                                 " +
					"	FROM TBS1027_PERFORMAZPERSONALE A,                                                                                                     " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C,                                                                                                        " +
					"		 TBS1019_TIPOAMBITO E                                                                                                              " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	AND E.COD_TIP_AMB = A.COD_TIP_AMB                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                    " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   A.DES_STR_ATT CONTENUTO                                                                                                             " +
					"	FROM TBS1028_ATTREZZATURE A,                                                                                                           " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                                    " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   A.DES_DOT_MUS CONTENUTO                                                                                                             " +
					"	FROM TBS1029_DOTAZIONEMULTIMEDIALE A,                                                                                                  " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                                  " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Ente locale: ' || A.DES_ENT_LOC || ' - Tipo progetto: ' || A.DES_PRO CONTENUTO                                                     " +
					"	FROM TBS1030_RAPPORTOENTE A,                                                                                                           " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                                                   " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Convenzione: ' || A.DES_CNV || ' - Organizzazione: ' || A.DES_ORG CONTENUTO                                                        " +
					"	FROM TBS1031_CONVENZIONEFORMA A,                                                                                                       " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					"	UNION ALL                                                                                                                              " +
					"	SELECT                                                                                      " +
					//"	   --B.COD_SCU_UTE_PLE PLESSO,                                                                                                         " +
					"	   A.DES_ARE_TEM_PSD AREA,                                                                                                             " +
					"	   C.COD_SEZ_SOT_SEZ COD_SEZ,                                                                                                          " +
					"	   C.DES_SEZ DES_SEZ,                                                                                                                  " +
					"	   'Tipologia: ' || A.DES_TIP_ATM || ' - Descrizione: ' || A.DES_ATM CONTENUTO                                                         " +
					"	FROM TBS1042_FABATTREZMATERIALE A,                                                                                                     " +
					"		 TBS1004_DATISEZIONESOTTOSEZ B,                                                                                                    " +
					"		 TBS1003_CATALOGOSEZIONE C                                                                                                         " +
					"	WHERE                                                                                                                                  " +
					"	A.PRG_DAT_PTF = B.PRG_DAT_PTF                                                                                                          " +
					"	AND B.PRG_SEZ_SOT_SEZ = C.PRG_SEZ_SOT_SEZ                                                                                              " +
					"	AND B.COD_SCU_UTE = :codScuUtePri                                                                                                      " +
					" ) ")
	public LinkedList<Pnsd> find(@Param("codScuUtePri") String codScuUtePri);


}
