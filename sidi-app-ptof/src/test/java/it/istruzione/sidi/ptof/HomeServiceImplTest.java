package it.istruzione.sidi.ptof;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import javax.validation.constraints.AssertTrue;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_TIPOLOGICHE_SEZIONE;
import it.istruzione.ptof.beans.ptof.TipologicaDTO;
import it.istruzione.ptof.service.HomeService;
import it.istruzione.ptof.service.TipologicheService;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class HomeServiceImplTest {
	
	private final static String COD_FOR_SCU_APP_OK = "FIIS00800G";
	private final static String COD_FOR_SCU_APP_KO = "XXXXXXXXXX";
	private static Logger logger = Logger.getLogger(HomeServiceImplTest.class);
	
	@Autowired
	private HomeService tester;
	
	@Autowired
	private TipologicheService tipologicheService;
	
	//@Test
	public void loadIstitutoScolasticoDTOTest(){
		
		try {
			logger.debug("start loadIstitutoScolasticoDTOTest");
			assertNotNull("Test per codice forte scuola esistente. Il metodo deve restituire not null",tester.loadIstitutoScolasticoDTO(COD_FOR_SCU_APP_OK));
			assertTrue("Test per codice forte scuola esistente con 3 plessi",tester.loadIstitutoScolasticoDTO(COD_FOR_SCU_APP_OK).getPlessi().size()==3);
			assertNull("Test per codice forte scuola non esistente. Il metodo deve restituire null",tester.loadIstitutoScolasticoDTO(COD_FOR_SCU_APP_KO));
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	
	//@Test
	public void tipologicheTest(){
		
		try {
			logger.debug("start tipologicheTest");
			TipologicaDTO tipologica =  new TipologicaDTO();
			tipologica.setTipo(TIPO_TIPOLOGICHE_SEZIONE.PRIORITA);
			LinkedList<BeanValueDTO> items = tipologicheService.getTipologica(tipologica, null);
			for (BeanValueDTO dto : items){
				logger.debug(dto.toString());
			}
			assertTrue(items.size() > 0);
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}

}
