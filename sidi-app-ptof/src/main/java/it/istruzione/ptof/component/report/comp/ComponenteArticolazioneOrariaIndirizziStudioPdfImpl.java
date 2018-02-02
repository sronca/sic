package it.istruzione.ptof.component.report.comp;

import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.PianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteArticolazioneOrariaIndirizziStudioPdfImpl implements ComponentePdf< ComponenteArticolazioneOrariaIndirizziStudioDTO> {

	@Override
	public void load(ComponenteArticolazioneOrariaIndirizziStudioDTO comp, Document document, PdfWriter writer)
			throws Exception {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		 	
		PdfPTable tableInner = new PdfPTable(1);
		createTab1(tableInner ,comp , helper);
		createTab2(tableInner, comp, helper);
        PdfPTable cornice = helper.creaCornice(tableInner);
		
		document.add(cornice);
		helper.addSpace(document);
	}

	

	public void createTab2(PdfPTable cornice , ComponenteArticolazioneOrariaIndirizziStudioDTO comp, PtofComponentePdfHelper helper) {
		PdfPTable table = new PdfPTable(1);
		helper.addRow(table, 0, "Note" ,comp.getNota() );
		cornice.addCell(table);
	}
		
	public void createTab1(PdfPTable cornice ,ComponenteArticolazioneOrariaIndirizziStudioDTO comp, PtofComponentePdfHelper helper) {
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){
			PdfPTable table = new PdfPTable(2);
			helper.addHeaderTab(comp.getNome() , table, 2);
			helper.addCellHeader("Indirizzo Studio", table, 1);
			helper.addCellHeader("Descrizione", table, 1);

			int i = 0;
			for ( ArticolazioneOrariaIndirizziStudioDTO item :  comp.getItems() ){
				helper.addCell(item.getIndirizzoStudio() , table, i);
				helper.addCell(item.getDescrizione() , table, i);

			}
			cornice.addCell(table);
		}
	}
 

}
