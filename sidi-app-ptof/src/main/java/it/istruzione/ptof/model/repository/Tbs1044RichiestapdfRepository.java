package it.istruzione.ptof.model.repository;

import java.util.LinkedList;
import java.util.List;

import it.istruzione.ptof.model.entity.Tbs1044Richiestapdf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Tbs1044RichiestapdfRepository extends JpaRepository<Tbs1044Richiestapdf, Long> {
	
	@Query(value = " SELECT tbs1044 "
				 + " FROM "
				 + " Tbs1044Richiestapdf tbs1044 "
				 + " WHERE "
				 + " tbs1044.prgGesCatDoc = :prgGesCatDoc "
				 + " AND tbs1044.perAnnSco = :perAnnSco "
				 + " AND tbs1044.codScuUte = :codScuUte "
				 + " AND tbs1044.flgStoPdf = :flgStoPdf ")
	Tbs1044Richiestapdf findByDocAndStato(@Param("prgGesCatDoc") Long prgGesCatDoc,
												 @Param("perAnnSco") Long perAnnSco,
												 @Param("codScuUte") String codScuUte,
												 @Param("flgStoPdf") String flgStoPdf
												 );
	
	LinkedList<Tbs1044Richiestapdf> findByFlgStoPdfOrderByDatInsRicPdfAsc(@Param("flgStoPdf") String flgStoPdf);


}
