package it.istruzione.ptof.model.repository;

import java.util.LinkedList;

import it.istruzione.ptof.model.entity.Mfg1012Regione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Mfg1012RegioneRepository extends JpaRepository<Mfg1012Regione, String> {
	
	@Query(nativeQuery = true, value= " SELECT * FROM UFGFUNGEN_OWN.MFG1012_REGIONE WHERE TRIM(COD_REG) = :codReg")
	public Mfg1012Regione findByCodiceRegione(@Param("codReg") String codReg);
	
	@Query(nativeQuery = true, value= " SELECT * FROM UFGFUNGEN_OWN.MFG1012_REGIONE WHERE COD_CIT = 200 ORDER BY DES_REG")
	public LinkedList<Mfg1012Regione> findAllRegioniItaliane();

}
