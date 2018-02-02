package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1022Ambitoprogettoaltro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1022AmbitoprogettoaltroRepository extends JpaRepository<Tbs1022Ambitoprogettoaltro, Long> {
	
	List<Tbs1022Ambitoprogettoaltro> findByTbs1004DatisezionesottosezCodScuUte(String codScuUte);

}
