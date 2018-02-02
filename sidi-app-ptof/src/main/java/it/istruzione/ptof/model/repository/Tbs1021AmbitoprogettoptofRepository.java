package it.istruzione.ptof.model.repository;

import java.util.List;

import it.istruzione.ptof.model.entity.Tbs1021Ambitoprogettoptof;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1021AmbitoprogettoptofRepository extends JpaRepository<Tbs1021Ambitoprogettoptof, Long> {
	
	List<Tbs1021Ambitoprogettoptof> findByTbs1004DatisezionesottosezCodScuUte(String codScuUte);

}
