package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianificazioneInterventiMonitoraggioDTO;

public class ComponenteArticolazioneTempiScuolaPdfImpl implements ComponentePdf<ComponenteArticolazioneTempiScuolaDTO> {

	
	@Override
	public void load(ComponenteArticolazioneTempiScuolaDTO comp, Document document, PdfWriter writer)
			throws Exception {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		
		PdfPTable tableInner = new PdfPTable(1);
		createTab1(tableInner ,comp , helper);
		createTab2(tableInner, comp, helper);
        PdfPTable cornice = helper.creaCornice(tableInner);
        document.add(cornice);
	}

	public void createTab2(PdfPTable cornice , ComponenteArticolazioneTempiScuolaDTO comp, PtofComponentePdfHelper helper) {
		PdfPTable table = new PdfPTable(1);
		helper.addRow(table, 0, "Note" ,comp.getNota() );
		cornice.addCell(table);
	}
	
	public void createTab1(PdfPTable cornice , ComponenteArticolazioneTempiScuolaDTO comp, PtofComponentePdfHelper helper) {
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){
			PdfPTable table = new PdfPTable(1);
			helper.addHeaderTab(comp.getNome() , table, 1);
			helper.addCellHeader("Descrizione Tempo Scuola", table, 1);

			int i = 0;
			for ( ArticolazioneTempiScuolaDTO item :  comp.getItems() ){
				helper.addCell(item.getDescrizioneTempoScuola() , table, i);

			}
			cornice.addCell(table);
		}
	}
}
