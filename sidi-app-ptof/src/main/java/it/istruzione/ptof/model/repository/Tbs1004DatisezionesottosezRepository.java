package it.istruzione.ptof.model.repository;

import java.util.List;

import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1004DatisezionesottosezRepository extends JpaRepository<Tbs1004Datisezionesottosez, Long> {
	
	Tbs1004Datisezionesottosez findByPrgDatPtfAndCodScuUte(Long prgDatPtf, String codScuUte);
	
	List<Tbs1004Datisezionesottosez> findByTbs1003CatalogosezionePrgSezSotSezPadAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long prgSezSotSezPad, String codScuUte, Long prgGesCatDoc, Long perAnnSco);
	
	Tbs1004Datisezionesottosez findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long prgSezSotSez, String codScuUte, Long prgGesCatDoc, Long perAnnSco);
	
	List<Tbs1004Datisezionesottosez> findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndCodScuUtePleIsNotNullAndPrgGesCatDocAndPerAnnSco(Long prgSezSotSez, String codScuUte, Long prgGesCatDoc, Long perAnnSco);
	
	Tbs1004Datisezionesottosez findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndCodScuUtePleIsNullAndPrgGesCatDocAndPerAnnSco(Long prgSezSotSez, String codScuUte, Long prgGesCatDoc, Long perAnnSco);
	
}
