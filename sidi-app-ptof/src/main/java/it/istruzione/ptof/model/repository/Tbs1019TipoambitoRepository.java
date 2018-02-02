package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Tbs1019Tipoambito;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1019TipoambitoRepository extends JpaRepository<Tbs1019Tipoambito, String> {

	
	public LinkedList<Tbs1019Tipoambito> findByFlgTipAmbOrderByCodTipAmbAsc(String flgTipAmb);

}
