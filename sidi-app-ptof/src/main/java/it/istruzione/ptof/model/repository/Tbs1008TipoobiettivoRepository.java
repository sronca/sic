package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1008Tipoobiettivo;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1008TipoobiettivoRepository extends JpaRepository<Tbs1008Tipoobiettivo, Long> {
	
	public LinkedList<Tbs1008Tipoobiettivo> findByOrderByPrgTipObiAsc();

}
