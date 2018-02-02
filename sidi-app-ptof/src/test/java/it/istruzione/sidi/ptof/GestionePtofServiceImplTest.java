package it.istruzione.sidi.ptof;

import static org.junit.Assert.*;

import java.util.LinkedList;

import javax.validation.constraints.AssertTrue;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.service.GestionePtofService;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class GestionePtofServiceImplTest {
	
	private static Logger logger = Logger.getLogger(GestionePtofServiceImplTest.class);
	
	@Autowired
	private GestionePtofService tester;
	
	//@Test
	public void loadDocumentoAnnoIncorsoTest(){
		
		try {
			String KEY = "RMIC81600D2016172";
			String KEY_KO = "RMEE00100T2016172";
			IstitutoScolasticoDTO istitutoScolasticoDTO = new IstitutoScolasticoDTO();
			istitutoScolasticoDTO.setCodiceMecIstPrin("RMIC81600D");
			
			logger.debug("start loadDocumentoAnnoIncorsoTest");
			assertNotNull("Test loadDocumentoAnnoIncorso(key) con chiave RMIC81600D2016172 : expected not null",tester.loadDocumentoAnnoIncorso(KEY));
			assertNull("Test loadDocumentoAnnoIncorso(key) con chiave RMEE00100T2016172 inesistente : expected null",tester.loadDocumentoAnnoIncorso(KEY_KO));
			assertNull("Test loadDocumentoAnnoIncorso(key,istituto) con istituto diverso : expected null",tester.loadDocumentoAnnoIncorso(KEY_KO,istitutoScolasticoDTO));
			assertNotNull("Test loadDocumentoAnnoIncorso(key,istituto) con istituto uguale : expected not null",tester.loadDocumentoAnnoIncorso(KEY,istitutoScolasticoDTO));
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	
	//@Test
	public void loadSezioniTest(){
		
		try {
			String KEY = "RMIC81600D2016172";
			
			IstitutoScolasticoDTO istitutoScolasticoDTO = new IstitutoScolasticoDTO();
			istitutoScolasticoDTO.setCodiceMecIstPrin("RMIC81600D");
			
			DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO = new DocumentoAnnoIncorsoDTO();
			documentoAnnoIncorsoDTO.setKey(KEY);
			
			GestionePtofDTO gestionePtofDTO = new GestionePtofDTO();
			gestionePtofDTO.setIstitutoScolastico(istitutoScolasticoDTO);
			gestionePtofDTO.setDocumentoAnnoIncorsoDTO(documentoAnnoIncorsoDTO);
			
			logger.debug("start loadSezioniTest");
			
			LinkedList<SezioneDTO> sezioni = tester.loadSezioni(gestionePtofDTO);
			assertTrue(sezioni.size() == 16);
			
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}

	//@Test
	public void loadSezioneTest(){
		
		try {
			IstitutoScolasticoDTO istitutoScolasticoDTO = new IstitutoScolasticoDTO();
			istitutoScolasticoDTO.setCodiceMecIstPrin("RMIC81600D");
			
			logger.debug("start loadSezioneTest");
			
			SezioneExtDTO sezioneExtDTO = tester.loadSezione("496", istitutoScolasticoDTO);
			assertNotNull(sezioneExtDTO);
			
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	

}
