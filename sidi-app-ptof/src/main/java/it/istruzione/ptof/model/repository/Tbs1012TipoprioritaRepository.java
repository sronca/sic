package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1012Tipopriorita;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1012TipoprioritaRepository extends JpaRepository<Tbs1012Tipopriorita, String> {

	
	public LinkedList<Tbs1012Tipopriorita> findByOrderByCodTipPriAsc();
}
