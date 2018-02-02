package it.istruzione.sidi.ptof;

import static org.junit.Assert.assertTrue;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.PageDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.ReportCompletoFiltroDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.constant.AppConstant;
import it.istruzione.ptof.service.MonitoraggioService;
import it.istruzione.ptof.service.TipologicheService;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class MonitoraggioServiceImplTest {
	
	private static Logger logger = Logger.getLogger(MonitoraggioServiceImplTest.class);
	
	@Autowired
	private MonitoraggioService tester;
	
	@Autowired
	private TipologicheService tipologicheService;
	
	//@Test
	public void getReportCompletoTest(){
		
		try {
			logger.debug("start getReportCompletoTest");
			
			/*ReportCompletoFiltroDTO reportCompletoFiltroDTO = new ReportCompletoFiltroDTO(1L, "LA", "RM", null, "6", "");
			PageDTO page = new PageDTO();
			ResponsePageDTO<ReportCompletoDTO> out = tester.getReportCompleto(reportCompletoFiltroDTO, page);
			logger.debug("OUT : " + ReflectionToStringBuilder.toString(out,ToStringStyle.MULTI_LINE_STYLE));
			//assertTrue(out.getRigheTotali() == 2);
			LinkedList<ReportCompletoDTO> out = tester.getReportCompleto(reportCompletoFiltroDTO);
			logger.debug("end getReportCompletoTest : " + out.size());
			assertTrue(out.size() == 2);*/
			
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	
	@Test
	public void getStatisticheTest(){
		
		try {
			logger.debug("start getStatisticheTest");
			BigDecimal versione = new BigDecimal(0);
			GestioneCatalogoDTO gestioneCatalogoDTO = new GestioneCatalogoDTO(1L,"Catalogo Sample", new BigDecimal(201619), versione);
			SidiContesto sidiContesto = new SidiContesto("141", AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE, "ufficio ministero");
			//tester.getStatistiche(sidiContesto, gestioneCatalogoDTO);
			
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}
	
	//@Test
	public void tipologicheTest(){
		
		try {
			logger.debug("start tipologicheTest");
			SidiContesto contestoUfficio = new SidiContesto("xxx", AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE, "yyy");
			LinkedList<BeanValueDTO> regioniContestoUfficio = tipologicheService.getRegioni(contestoUfficio);
			logger.debug("regioniContestoUfficio : " + regioniContestoUfficio.size());
			SidiContesto contestoUSR = new SidiContesto("YR19", AppConstant.TIPO_CONTESTO_REGIONALE, "yyy");
			LinkedList<BeanValueDTO> regioniContestoUSR = tipologicheService.getRegioni(contestoUSR);
			logger.debug("regioniContestoUSR : " + regioniContestoUSR.size());
			LinkedList<BeanValueDTO> province = tipologicheService.getProvince("LA");
			logger.debug("province : " + province.size());
			LinkedList<BeanValueDTO> comuni = tipologicheService.getComuni("RM");
			logger.debug("comuni : " + comuni.size());
			assertTrue(comuni.size() > 0);
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
		}
		
	}

}
