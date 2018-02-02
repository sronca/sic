package it.istruzione.ptof.model.repository.business;

import it.istruzione.ptof.model.entity.business.Documento;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentoRepository extends JpaRepository<Documento, String> {
	
	@Query(nativeQuery = true, value= " SELECT "
									+ " :codScuUte || :annoScolastico || A.PRG_GES_CAT_DOC CHIAVE, "
									+ " A.PRG_GES_CAT_DOC PRG_GES_CAT_DOC, :codScuUte COD_SCU_UTE, :annoScolastico PER_ANN_SCO, "
									+ " A.DES_DOC DES_DOC, A.DAT_INI_VAL DAT_INI_VAL, "
									+ " A.DAT_FIN_VAL DAT_FIN_VAL, A.PER_TRI_RIF PER_TRI_RIF, A.NUM_VER_DOC NUM_VER_DOC, B.COD_STA COD_STA, C.DES_STA DES_STA "
									+ " FROM TBS1001_GESTIONECATALOGODOC A "
									+ " LEFT OUTER JOIN TBS1002_GESTIONEPTOF B ON (A.PRG_GES_CAT_DOC = B.PRG_GES_CAT_DOC AND B.COD_SCU_UTE = :codScuUte AND B.PER_ANN_SCO = :annoScolastico) "
									+ " LEFT OUTER JOIN TBS1006_TIPOSTATO C ON (B.COD_STA = C.COD_STA) "
									+ " WHERE "
									+ " SYSDATE BETWEEN A.DAT_INI_VAL AND A.DAT_FIN_VAL "
									+ " ORDER BY A.PER_TRI_RIF, A.NUM_VER_DOC ")
	public LinkedList<Documento> findDocumentiIncorso(@Param("codScuUte") String codScuUte, @Param("annoScolastico") int annoScolastico);

	@Query(nativeQuery = true, value= " SELECT "
			+ " B.COD_SCU_UTE || B.PER_ANN_SCO || A.PRG_GES_CAT_DOC CHIAVE, "
			+ " A.PRG_GES_CAT_DOC, B.COD_SCU_UTE, B.PER_ANN_SCO, "
			+ " A.DES_DOC, A.DAT_INI_VAL, "
			+ " A.DAT_FIN_VAL, A.PER_TRI_RIF, A.NUM_VER_DOC, B.COD_STA, C.DES_STA "
			+ " FROM TBS1001_GESTIONECATALOGODOC A "
			+ " INNER JOIN TBS1002_GESTIONEPTOF B ON (A.PRG_GES_CAT_DOC = B.PRG_GES_CAT_DOC) "
			+ " INNER JOIN TBS1006_TIPOSTATO C ON (B.COD_STA = C.COD_STA) "
			+ " WHERE "
			+ " A.PRG_GES_CAT_DOC = :prgGesCatDoc "
			+ " AND B.COD_SCU_UTE = :codScuUte "
			+ " AND B.PER_ANN_SCO = :annoScolastico ")
	public Documento getDocumentoByKey(@Param("codScuUte") String codScuUte, @Param("annoScolastico") Long annoScolastico, @Param("prgGesCatDoc") Long prgGesCatDoc);
	
	@Query(nativeQuery = true, value= " SELECT "
			+ " :codScuUte || :annoScolastico || A.PRG_GES_CAT_DOC CHIAVE, "
			+ " A.PRG_GES_CAT_DOC, :codScuUte COD_SCU_UTE, :annoScolastico PER_ANN_SCO, "
			+ " A.DES_DOC, A.DAT_INI_VAL, "
			+ " A.DAT_FIN_VAL, A.PER_TRI_RIF, A.NUM_VER_DOC, B.COD_STA, C.DES_STA "
			+ " FROM TBS1001_GESTIONECATALOGODOC A "
			+ " LEFT OUTER JOIN TBS1002_GESTIONEPTOF B ON (A.PRG_GES_CAT_DOC = B.PRG_GES_CAT_DOC AND B.COD_SCU_UTE = :codScuUte AND B.PER_ANN_SCO = :annoScolastico) "
			+ " LEFT OUTER JOIN TBS1006_TIPOSTATO C ON (B.COD_STA = C.COD_STA) "
			+ " WHERE "
			+ " SYSDATE NOT BETWEEN DAT_INI_VAL AND DAT_FIN_VAL "
			+ " ORDER BY A.PER_TRI_RIF, A.NUM_VER_DOC ")
	public LinkedList<Documento> findDocumentiArchivio(@Param("codScuUte") String codScuUte, @Param("annoScolastico") int annoScolastico);
	
	@Query(nativeQuery = true, value= " SELECT "
			+ " B.COD_SCU_UTE || B.PER_ANN_SCO || A.PRG_GES_CAT_DOC CHIAVE, "
			+ " A.PRG_GES_CAT_DOC PRG_GES_CAT_DOC, B.COD_SCU_UTE COD_SCU_UTE, B.PER_ANN_SCO PER_ANN_SCO, "
			+ " A.DES_DOC DES_DOC, A.DAT_INI_VAL DAT_INI_VAL, "
			+ " A.DAT_FIN_VAL DAT_FIN_VAL, A.PER_TRI_RIF PER_TRI_RIF, A.NUM_VER_DOC NUM_VER_DOC, B.COD_STA COD_STA, C.DES_STA DES_STA "
			+ " FROM TBS1001_GESTIONECATALOGODOC A "
			+ " INNER JOIN TBS1002_GESTIONEPTOF B ON (A.PRG_GES_CAT_DOC = B.PRG_GES_CAT_DOC) "
			+ " INNER JOIN TBS1006_TIPOSTATO C ON (B.COD_STA = C.COD_STA) "
			+ " WHERE "
			+ " B.COD_SCU_UTE = :codScuUte "
			+ " AND B.PER_ANN_SCO = :annoScolastico "
			+ " AND B.COD_STA IN ('3','11','6','10','7') "
			+ " ORDER BY A.PER_TRI_RIF, A.NUM_VER_DOC ")
	public LinkedList<Documento> findDocumentiPubblicazione(@Param("codScuUte") String codScuUte, @Param("annoScolastico") int annoScolastico);


}
