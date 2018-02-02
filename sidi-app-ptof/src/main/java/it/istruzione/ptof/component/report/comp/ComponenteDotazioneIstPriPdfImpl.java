package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.DotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDotazioneIstPriDTO;

public class ComponenteDotazioneIstPriPdfImpl  implements ComponentePdf<ComponenteDotazioneIstPriDTO> {

	@Override
	public void load(ComponenteDotazioneIstPriDTO comp, Document document, PdfWriter writer) throws Exception {
		
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
			helper.addHeaderTab(comp.getNome() , table, 3);
			helper.addCellHeader("Dotazioni Multimediali", table, 1);
			helper.addCellHeader("Numero", table, 1);
			helper.addCellHeader("Area tematica PNSD", table, 1);

			int i = 0;
			for ( DotazioneIstPriDTO item : comp.getItems() ){
				helper.addCell(item.getDotazioniMultimediali() , table, i);
				helper.addCell(""+item.getNumero() , table, i);
				helper.addCell(item.getAreaTematicaPNSD() , table, i);
			}
			helper.addSpace(document);
			document.add(table);
			helper.addSpace(document);
		}
		
	}

}
