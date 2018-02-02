package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1014Componentesezione;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1014ComponentesezioneRepository extends JpaRepository<Tbs1014Componentesezione, Long> {
	
	public LinkedList<Tbs1014Componentesezione> findByPrgSezSotSezAndPrgCmpSezPdrIsNullOrderByPrgOrdAsc(Long prgSezSotSez);

}
