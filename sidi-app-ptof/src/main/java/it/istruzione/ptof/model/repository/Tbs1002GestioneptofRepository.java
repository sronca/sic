package it.istruzione.ptof.model.repository;

import java.util.List;

import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Tbs1002GestioneptofRepository extends JpaRepository<Tbs1002Gestioneptof, Tbs1002GestioneptofPK> {
	
	static String queryBaseFindByFilter = " SELECT tbs1002 "
										+ " FROM "
										+ " Tbs1002Gestioneptof tbs1002 "
										+ "		join fetch tbs1002.mfg1002Anagistscol mfg1002 "
										+ "		join fetch mfg1002.mfg1014Comune mfg1014 "
										+ "		join fetch mfg1014.mfg1029Provnuoist mfg1029 "
										+ "		join fetch mfg1029.mfg1012Regione mfg1012 "
										+ "		join fetch tbs1002.tbs1006Tipostato tbs1006 "
										+ "		join fetch tbs1002.tbs1001Gestionecatalogodoc tbs1001 "
										+ " WHERE "
										+ " tbs1002.id.prgGesCatDoc = :prgGesCatDoc "
										+ " AND (TRIM(mfg1012.codReg) = :codReg OR :codReg IS NULL)"
										+ " AND (mfg1029.codPrv = :codPrv OR :codPrv IS NULL) "
										+ " AND (mfg1014.codCom = :codCom OR :codCom IS NULL) "
										+ " AND (tbs1002.id.codScuUte = :codScuUt OR :codScuUt IS NULL) "
										+ " AND (UPPER(mfg1002.desNomScu) LIKE UPPER(:denominazione) OR :denominazione IS NULL) "
										+ " AND (tbs1006.codSta = '6' or tbs1006.codSta = '7' ) "
										+ " AND (tbs1006.codSta = :codSta OR :codSta IS NULL) ";
	
	
	static String countQueryBaseFindByFilter = " SELECT count(tbs1002) "
											 + " FROM "
											 + " Tbs1002Gestioneptof tbs1002 "
											 + " WHERE "
											 + " tbs1002.id.prgGesCatDoc = :prgGesCatDoc "
											 + " AND (TRIM(tbs1002.mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.mfg1012Regione.codReg) = :codReg OR :codReg IS NULL) "
											 + " AND (tbs1002.mfg1002Anagistscol.mfg1014Comune.mfg1029Provnuoist.codPrv = :codPrv OR :codPrv IS NULL) "
											 + " AND (tbs1002.mfg1002Anagistscol.mfg1014Comune.codCom = :codCom OR :codCom IS NULL) "
											 + " AND (tbs1002.id.codScuUte = :codScuUt OR :codScuUt IS NULL) "
											 + " AND (UPPER(tbs1002.mfg1002Anagistscol.desNomScu) LIKE UPPER(:denominazione) OR :denominazione IS NULL) "
											 + " AND (tbs1002.tbs1006Tipostato.codSta = '6' or tbs1002.tbs1006Tipostato.codSta = '7' ) "
											 + " AND (tbs1002.tbs1006Tipostato.codSta = :codSta OR :codSta IS NULL) ";
	
	@Query(value = queryBaseFindByFilter,
			countQuery = countQueryBaseFindByFilter)
	public Page<Tbs1002Gestioneptof> findByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
												   @Param("codReg") String codReg,
												   @Param("codPrv") String codPrv,
												   @Param("codCom") String codCom,
												   @Param("codScuUt") String codScuUt,
												   @Param("codSta") String codSta,
												   @Param("denominazione") String denominazione,
												   Pageable page
												   );
	
	@Query(value = queryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'AA') ",
			countQuery = countQueryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'AA') ")
	public Page<Tbs1002Gestioneptof> findInfanziaByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
													      @Param("codReg") String codReg,
													      @Param("codPrv") String codPrv,
													      @Param("codCom") String codCom,
													      @Param("codScuUt") String codScuUt,
													      @Param("codSta") String codSta,
													      @Param("denominazione") String denominazione,
													      Pageable page
													      );
	
	
	@Query(value = queryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'EE' OR SUBSTR(tbs1002.id.codScuUte,3,2) = 'IC') ",
			countQuery = countQueryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'EE' OR SUBSTR(tbs1002.id.codScuUte,3,2) = 'IC') ")
	public Page<Tbs1002Gestioneptof> findPrimariaByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
													      @Param("codReg") String codReg,
													      @Param("codPrv") String codPrv,
													      @Param("codCom") String codCom,
													      @Param("codScuUt") String codScuUt,
													      @Param("codSta") String codSta,
													      @Param("denominazione") String denominazione,
													      Pageable page
													      );

	@Query(value = queryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'MM' OR SUBSTR(tbs1002.id.codScuUte,3,2) = 'IC') ",
			countQuery = countQueryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'MM' OR SUBSTR(tbs1002.id.codScuUte,3,2) = 'IC') ")
	public Page<Tbs1002Gestioneptof> findIGradoByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
													      @Param("codReg") String codReg,
													      @Param("codPrv") String codPrv,
													      @Param("codCom") String codCom,
													      @Param("codScuUt") String codScuUt,
													      @Param("codSta") String codSta,
													      @Param("denominazione") String denominazione,
													      Pageable page
													      );

	@Query(value = queryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'IS' OR SUBSTR(tbs1002.id.codScuUte,3,2) > 'MM') ",
			countQuery = countQueryBaseFindByFilter + " AND (SUBSTR(tbs1002.id.codScuUte,3,2) = 'IS' OR SUBSTR(tbs1002.id.codScuUte,3,2) > 'MM') ")
	public Page<Tbs1002Gestioneptof> findIIGradoByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
													      @Param("codReg") String codReg,
													      @Param("codPrv") String codPrv,
													      @Param("codCom") String codCom,
													      @Param("codScuUt") String codScuUt,
													      @Param("codSta") String codSta,
													      @Param("denominazione") String denominazione,
													      Pageable page
													      );
	
	@Query(value = " SELECT tbs1002 "
			+ " FROM "
			+ " Tbs1002Gestioneptof tbs1002 "
			+ "		join fetch tbs1002.mfg1002Anagistscol mfg1002 "
			+ "		join fetch mfg1002.mfg1014Comune mfg1014 "
			+ "		join fetch mfg1014.mfg1029Provnuoist mfg1029 "
			+ "		join fetch mfg1029.mfg1012Regione mfg1012 "
			+ "		join fetch tbs1002.tbs1006Tipostato tbs1006 "
			+ "		join fetch tbs1002.tbs1001Gestionecatalogodoc tbs1001 "
			+ " WHERE "
			+ " tbs1002.id.prgGesCatDoc = :prgGesCatDoc "
			+ " AND (TRIM(mfg1012.codReg) = :codReg OR :codReg IS NULL)"
			+ " AND (mfg1029.codPrv = :codPrv OR :codPrv IS NULL) "
			+ " AND (mfg1014.codCom = :codCom OR :codCom IS NULL) "
			+ " AND (tbs1002.id.codScuUte = :codScuUt OR :codScuUt IS NULL) "
			+ " AND (UPPER(mfg1002.desNomScu) LIKE UPPER(:denominazione) OR :denominazione IS NULL) "
			+ " AND (tbs1006.codSta = '6' or tbs1006.codSta = '7' ) "
			+ " AND (tbs1006.codSta = :codSta OR :codSta IS NULL) ")
	public List<Tbs1002Gestioneptof> findByFilter(@Param("prgGesCatDoc") Long prgGesCatDoc,
												   @Param("codReg") String codReg,
												   @Param("codPrv") String codPrv,
												   @Param("codCom") String codCom,
												   @Param("codScuUt") String codScuUt,
												   @Param("codSta") String codSta,
												   @Param("denominazione") String denominazione
												   );

}
