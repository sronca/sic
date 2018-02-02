package it.istruzione.ptof.component.report.comp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteComboBoxDTO;

public class ComponenteComboBoxPdfImpl implements ComponentePdf<ComponenteComboBoxDTO> {

	 
	public ByteArrayOutputStream loadPage(ComponenteComboBoxDTO comp) {
	    PtofComponentePdfHelper helper = new 	PtofComponentePdfHelper();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  	try {
		 		    Document document = new Document(PageSize.A4);
				    PdfWriter.getInstance(document, baos);
				    document.open();
			 	    Paragraph paragraph = new Paragraph(comp.getSelected().getLabel() +" " + comp.getSelected().getValue() );
				    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
				    document.add(paragraph);
				    document.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	 	 
		return baos;
	}


	@Override
	public void load(ComponenteComboBoxDTO comp, Document document, PdfWriter writer) throws DocumentException {
	    PtofComponentePdfHelper helper = new 	PtofComponentePdfHelper();
	    Paragraph paragraph = new Paragraph(comp.getDescrizione()+" "+comp.getSelected().getLabel());
	    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
	    document.add(paragraph);
		
	}


}