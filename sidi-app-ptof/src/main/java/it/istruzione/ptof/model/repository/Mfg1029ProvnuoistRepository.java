package it.istruzione.ptof.model.repository;

import it.istruzione.ptof.model.entity.Mfg1029Provnuoist;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Mfg1029ProvnuoistRepository extends JpaRepository<Mfg1029Provnuoist, String> {
	
	@Query(nativeQuery = true, value= " SELECT * FROM UFGFUNGEN_OWN.MFG1029_PROVNUOIST WHERE TRIM(COD_REG) = :codReg")
	public LinkedList<Mfg1029Provnuoist> findByCodiceRegione(@Param("codReg") String codReg);

}
