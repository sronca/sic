package it.istruzione.ptof.component.report.comp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;

@Component
public class ComponenteObbiettiviPrioritariPdfImpl implements ComponentePdf<ComponenteObbiettiviPrioritariDTO> {

	 
	@Override
	public void load(ComponenteObbiettiviPrioritariDTO comp, Document document, PdfWriter writer) throws Exception {
		
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
			LinkedList<ObbiettiviFormativiDTO>  items =  comp.getItems();
			PdfPTable table = new PdfPTable(4);
			helper.addHeaderTab(comp.getNome() , table, 4);

			// Obiettivi	Finalità	Benefici	Priorità
			helper.addCellHeader("Obiettivi", table, 1);
			helper.addCellHeader("Finalita' ", table, 1);
			helper.addCellHeader("Benefici ", table, 1);
			helper.addCellHeader("Priorita' ", table, 1);
			int i = 0;
			for ( ObbiettiviFormativiDTO item : items ){
				helper.addCell(item.getObbiettivo().getLabel() , table, i);
				helper.addCell(item.getFinalita() , table, i);
				helper.addCell(item.getBenefici() , table, i);
				helper.addCell(item.getPriorita().getLabel() , table, i);

			}

			helper.addSpace(document);
			document.add(table);
			helper.addSpace(document);
		}
		
	}

	 

}
