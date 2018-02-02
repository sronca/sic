package it.istruzione.ptof.component.report.comp;

import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.PianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.helper.CommonsUtility;

public class ComponentePianoNazionaleScuolaDigitalePdfImpl implements ComponentePdf< ComponentePianoNazionaleScuolaDigitaleDTO > {

	@Override
	public void load(ComponentePianoNazionaleScuolaDigitaleDTO comp, Document document, PdfWriter writer)
			throws Exception {
		
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(comp.getNome(), table, 4);


			helper.addCellHeader("Area Tematica PNSD", table, 1);
			helper.addCellHeader("Numero Sezione ", table, 1);
			helper.addCellHeader("Descrizione Sezione ", table, 1);
			helper.addCellHeader("Contenuto Legato al PNSD ", table, 1);
			int i = 0;
			for ( PianoNazionaleScuolaDigitaleDTO item : comp.getItems() ){
				helper.addCell(item.getAreaTematicaPNSD() , table, i);
				helper.addCell(item.getNumeroSezione() , table, i);
				helper.addCell(item.getDescrizioneSezione() , table, i);
				helper.addCell(item.getContenutoLegatoPNSD() , table, i);

			}
			helper.addSpace(document);
			document.add(table);
			helper.addSpace(document);
		}
		
	}

}
