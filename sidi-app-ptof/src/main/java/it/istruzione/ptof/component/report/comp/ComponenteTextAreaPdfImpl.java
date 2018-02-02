package it.istruzione.ptof.component.report.comp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.component.report.PtofReportFactory;

public class ComponenteTextAreaPdfImpl implements ComponentePdf<ComponenteTextAreaDTO> {
 	

	private Logger logger = LoggerFactory.getLogger(PtofReportFactory.class);

	public ByteArrayOutputStream loadElement(ComponenteTextAreaDTO comp) {
	    PtofComponentePdfHelper helper = new 	PtofComponentePdfHelper();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 
		try {
			     
			    Document document = new Document(PageSize.A4);
			    helper.addSpace(document);
			    PdfWriter.getInstance(document, baos);
			    document.open();
			    helper.addSpace(document);
			    helper.addSpace(document);
			    HTMLWorker htmlWorker = new HTMLWorker(document);
			    htmlWorker.parse(new StringReader(comp.getValore()));
			    helper.addSpace(document);
			    helper.addSpace(document);
			    document.close();
			    
 			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return baos;
	}

	@Override
	public void load(ComponenteTextAreaDTO comp, Document document, PdfWriter writer) throws Exception {
		        if ( comp.getValore()==null ){
		        	return;
		        }
	 	        HTMLWorker htmlWorker = new HTMLWorker(document);
	 	        
	 	        logger.debug("ComponenteTextAreaDTO --> key "+  comp.getKey() );
	 	        logger.debug("ComponenteTextAreaDTO --> val "+  comp.getValore() );
			    
	 	        htmlWorker.parse(new StringReader(comp.getValore()));
			   
	}

	
}
