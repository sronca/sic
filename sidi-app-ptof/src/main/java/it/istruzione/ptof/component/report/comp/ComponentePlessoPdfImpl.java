package it.istruzione.ptof.component.report.comp;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviMiglioramentoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePlessoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;

public class ComponentePlessoPdfImpl implements ComponentePdf<ComponentePlessoDTO> {
 	
	@Override
	public void load(ComponentePlessoDTO comp, Document document, PdfWriter writer) throws Exception {
		    PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		
			int numeroColonne = 5;
			PdfPTable table = new PdfPTable(numeroColonne);
			table.setWidthPercentage(100);
			helper.addHeaderTab("DATI IDENTIFICATIVI DEL PLESSO", table, numeroColonne);
			
			// Denominazione Plesso	Indirizzo	Comune	Codice Meccanografico
			helper.addCellHeader("Denominazione", table, 1);
			helper.addCellHeader("Plesso", table, 1);
			helper.addCellHeader("Indirizzo", table, 1);
			helper.addCellHeader("Comune", table, 1);
			helper.addCellHeader("Codice Meccanografico", table, 1);
			
			helper.addCell(comp.getPlesso().getDenominazione()   , table, 0);
			helper.addCell(comp.getPlesso().getPlessoScuola()   , table, 0);
			helper.addCell(comp.getPlesso().getIndirizzo()   , table, 0);
			helper.addCell(comp.getPlesso().getComune()   , table, 0);
			helper.addCell(comp.getPlesso().getCodiceMecUtente()   , table, 0);
			
			helper.addSpace(document);
			document.add(table);
		    helper.addSpace(document);
		   
	}

	
}

