package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1027Performazpersonale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1027PerformazpersonaleRepository extends JpaRepository<Tbs1027Performazpersonale, Long> {
	
	List<Tbs1027Performazpersonale> findByTbs1004DatisezionesottosezCodScuUte(String codScuUte);

}
