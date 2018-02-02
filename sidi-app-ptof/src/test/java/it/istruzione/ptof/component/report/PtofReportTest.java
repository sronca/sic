package it.istruzione.ptof.component.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.component.report.comp.PtofComponentePdfHelper;
import net.sf.jasperreports.engine.JasperPrint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class PtofReportTest {

	@Autowired
	PtofReport ptofReport ; 
	
	
	
	@Test
	public void test1() throws Exception {
		
		
		String pathRootReportOUT = "C://reportOUT//";
		String outFileName = pathRootReportOUT + "PTOF-test1.pdf";
		new File(outFileName).delete();
		FileOutputStream fos = new FileOutputStream(outFileName);
		
		String KEY = "RMIC83800A2016172";
	 
		IstitutoScolasticoDTO istitutoScolasticoDTO = new IstitutoScolasticoDTO();
		istitutoScolasticoDTO.setCodiceMecIstPrin("RMIC83800A");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ptofReport.loadDocumentoFormatoPDF(baos ,  KEY , istitutoScolasticoDTO);
		 
	
		fos.write(baos.toByteArray());
		System.out.println("Created file: " + outFileName);
		fos.close();

	}
 

//	@Test
	public void testCreaimg() throws Exception {
		Document document = new Document(PageSize.A4);
		 
		try {
			  PdfWriter writer = PdfWriter.getInstance(document,   new FileOutputStream("C://reportOUT//PTOF-jpeg.pdf") );
			  writer.open();
		      document.open();
		      FileInputStream fileInputStream = new FileInputStream("C:/reportOUT/in/testImg.jpeg");
			  document.add(Image.getInstance( IOUtils.toByteArray(fileInputStream)));
			  document.close();
			  writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			   
				 
		}
	}
}
