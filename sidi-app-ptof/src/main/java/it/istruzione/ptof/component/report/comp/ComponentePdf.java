package it.istruzione.ptof.component.report.comp;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

public interface ComponentePdf<C extends ComponenteDTO> {
 
	public void load(C componete, Document document, PdfWriter writer) throws Exception;
	  
}
