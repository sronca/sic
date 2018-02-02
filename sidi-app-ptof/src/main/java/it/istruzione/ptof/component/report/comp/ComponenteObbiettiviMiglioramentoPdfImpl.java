package it.istruzione.ptof.component.report.comp;

import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.PrioritaTraguardiDTO;
import it.istruzione.ptof.beans.documenti.SintesiProcessoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviMiglioramentoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariAltriDTO;

public class ComponenteObbiettiviMiglioramentoPdfImpl  implements ComponentePdf<ComponenteObbiettiviMiglioramentoDTO> {

	 
	@Override
	public void load(ComponenteObbiettiviMiglioramentoDTO comp, Document document, PdfWriter writer) throws Exception {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		createPriorita(comp, document, helper);
		createModalita(comp, document, helper);
		createSintesi(comp, document, helper);
	    
	}

	private void createModalita(ComponenteObbiettiviMiglioramentoDTO comp, Document document,
			PtofComponentePdfHelper helper)  throws DocumentException { 
		
		PdfPTable table = new PdfPTable(1);
		helper.addCellHeader("Motivazione scelta priorita'", table, 1);
		helper.addCell(comp.getMotivazioneSceltaPriorita() , table, 0);
		helper.addSpace(document);
		document.add(table);
	    helper.addSpace(document);
	}

	private void createPriorita(ComponenteObbiettiviMiglioramentoDTO comp, Document document, PtofComponentePdfHelper helper) throws DocumentException {
		LinkedList<PrioritaTraguardiDTO>  items =  comp.getPrioritaTraguardi();
		PdfPTable table = new PdfPTable(3);
		helper.addHeaderTab(comp.getNome() , table, 3);
		// Area	Descrizione delle priorità	Descrizione del traguardo
		helper.addCellHeader("Area", table, 1);
		helper.addCellHeader("Descrizione delle priorita'", table, 1);
		helper.addCellHeader("Descrizione del traguardo ", table, 1);
		
		int i = 0;
		for ( PrioritaTraguardiDTO item : items ){
			helper.addCell(item.getArea() , table, i);
			helper.addCell(item.getDescPriorita() , table, i);
			helper.addCell(item.getDescTraguardo() , table, i);
		}
		helper.addSpace(document);
		document.add(table);
	    helper.addSpace(document);
	}

	private void createSintesi(ComponenteObbiettiviMiglioramentoDTO comp, Document document, PtofComponentePdfHelper helper) throws DocumentException {
		LinkedList<SintesiProcessoDTO>  items =  comp.getSintesiProcesso();
		PdfPTable table = new PdfPTable(2);
		helper.addHeaderTab(comp.getNome() , table, 2);
		// Area di processo	Descrizione dell'obiettivo di processo
		helper.addCellHeader("Area di processo", table, 1);
		helper.addCellHeader("Descrizione dell'obiettivo di processo", table, 1);
		
		int i = 0;
		for ( SintesiProcessoDTO item : items ){
			helper.addCell(item.getAreaProcesso() , table, i);
			helper.addCell(item.getDescObiettivoProcesso() , table, i);
		}
		helper.addSpace(document);
		document.add(table);
	    helper.addSpace(document);
	}

	 

}
