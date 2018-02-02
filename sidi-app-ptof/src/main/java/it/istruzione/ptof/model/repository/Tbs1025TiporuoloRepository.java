package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1025Tiporuolo;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1025TiporuoloRepository extends JpaRepository<Tbs1025Tiporuolo, String> {

	
	public LinkedList<Tbs1025Tiporuolo> findByOrderByCodTipRuoAsc();

}
