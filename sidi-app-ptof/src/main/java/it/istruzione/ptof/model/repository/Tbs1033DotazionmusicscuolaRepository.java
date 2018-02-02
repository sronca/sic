package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1033Dotazionmusicscuola;
import it.istruzione.ptof.model.entity.Tbs1033DotazionmusicscuolaPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1033DotazionmusicscuolaRepository extends JpaRepository<Tbs1033Dotazionmusicscuola, Tbs1033DotazionmusicscuolaPK> {
	
	List<Tbs1033Dotazionmusicscuola> findByCodScuUtePri(String codScuUtePri);

}
