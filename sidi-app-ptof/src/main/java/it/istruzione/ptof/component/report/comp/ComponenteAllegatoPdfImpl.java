package it.istruzione.ptof.component.report.comp;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;

import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.component.report.PtofFonts;

@Component
public class ComponenteAllegatoPdfImpl implements ComponentePdf<ComponenteAllegatoDTO> {

	
	private void buildIntestazioneSezione( Document document, PdfWriter writer) throws DocumentException {
		PdfPTable header = new PdfPTable(1);
		header.setWidthPercentage(100f);
		 
		Paragraph p = new Paragraph();
		p.add(new Chunk("PIANO TRIENNALE OFFERTA FORMATIVA" , PtofFonts.BOLDITALIC[0]));
		 
		PdfPCell val = new PdfPCell(p);
		val.setHorizontalAlignment(Element.ALIGN_RIGHT);
		val.setBorder( Rectangle.BOTTOM );
		val.setPaddingTop(document.top()/3);
		val.setPaddingBottom(10f);
		val.setBorder(0); 
	 
		val.setNoWrap(true);
		header.addCell(val);
		
		document.add(header);
		document.newPage();
		
	}
	 
	@Override
	public void load(ComponenteAllegatoDTO comp, Document document, PdfWriter writer) {
	  
		
		PtofComponentePdfHelper helper = new 	PtofComponentePdfHelper();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 
		try {
			
				if ( comp.getFile()==null) {
					if (  0== comp.getTipoFileAccettato().compareTo(TIPO_FILE_ACCETTATO.JPEG) ) {
						buildIntestazioneSezione(document, writer);	
					}
					return ;
				}

			InputStream file = comp.getFile().getFile();
			switch (comp.getTipoFileAccettato()) {
			case JPEG: {
				helper.mergeImg(baos, file);
				break;
			}
			case PDF: {
				helper.mergePDF(baos, file);
				break;
			}
			default:
				break;
			}
			
			document.newPage();
			ByteArrayInputStream input = new ByteArrayInputStream( ((ByteArrayOutputStream)baos).toByteArray());
			helper.mergePDF(document , writer  , input );
			try {input.close();} catch (IOException e) {
				new RuntimeException(e);
			}
			document.newPage();
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	 

}
