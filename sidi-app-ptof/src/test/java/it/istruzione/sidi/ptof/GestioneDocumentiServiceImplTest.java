package it.istruzione.sidi.ptof;

import static org.junit.Assert.assertTrue;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.service.GestioneDocumentiService;

import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class GestioneDocumentiServiceImplTest {
	
	private final static String COD_SCU_UT = "RMIC81600D";
	private static Logger logger = Logger.getLogger(GestioneDocumentiServiceImplTest.class);
	
	@Autowired
	private GestioneDocumentiService tester;
	
	//@Test
	public void loadDocumentiAnnoIncorsoTest(){
		
		try {
			logger.debug("start loadDocumentiAnnoIncorsoTest");
			GestioneDocumentiDTO gestioneDocumentiDTO = new GestioneDocumentiDTO();
			gestioneDocumentiDTO.setIstitutoScolastico(new IstitutoScolasticoDTO());
			gestioneDocumentiDTO.getIstitutoScolastico().setCodiceMecIstPrin(COD_SCU_UT);
			assertTrue(tester.loadDocumentiAnnoIncorso(gestioneDocumentiDTO).size() > 0);
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}

	//@Test
	public void loadDocumentiArchivioTest(){
		
		try {
			logger.debug("start loadDocumentiArchivioTest");
			GestioneDocumentiDTO gestioneDocumentiDTO = new GestioneDocumentiDTO();
			gestioneDocumentiDTO.setIstitutoScolastico(new IstitutoScolasticoDTO());
			gestioneDocumentiDTO.getIstitutoScolastico().setCodiceMecIstPrin(COD_SCU_UT);
			assertTrue(tester.loadDocumentiArchivio(gestioneDocumentiDTO).size() > 0);
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	
	 

}
