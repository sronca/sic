package it.istruzione.ptof.model.repository;

import java.util.LinkedList;

import it.istruzione.ptof.model.entity.Tbs1043Fabdecretointerminper;
import it.istruzione.ptof.model.entity.Tbs1043FabdecretointerminperPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tbs1043FabdecretointerminperRepository extends JpaRepository<Tbs1043Fabdecretointerminper, Tbs1043FabdecretointerminperPK> {
	
	LinkedList<Tbs1043Fabdecretointerminper> findByIdPrgGesCatDocOrderByIdCodRegAscIdCodRifDecAsc(Long prgGesCatDoc);

}
