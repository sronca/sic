package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;
import it.istruzione.ptof.model.entity.business.ArticolazioneClassiMMII;

public class ComponenteArticolazioneClassiMMIIPdfImpl implements ComponentePdf<ComponenteArticolazioneClassiMMIIDTO> {

	@Override
	public void load(ComponenteArticolazioneClassiMMIIDTO componete, Document document, PdfWriter writer)
			throws Exception {
		  
		if (componete != null && componete.getItems() != null && !componete.getItems().isEmpty()){
			int j=0;
			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();


			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(componete.getNome() , table, 7);

			// Obiettivi	Finalità	Benefici	Priorità
			helper.addCellHeader("", table, 1);
			helper.addCellHeader("Classi ", table, 6);

			helper.addCellHeader("Indirizzo", table, 1);
			helper.addCellHeader("I",   table, 1);
			helper.addCellHeader("II",  table, 1);
			helper.addCellHeader("III", table, 1);
			helper.addCellHeader("IV",  table, 1);
			helper.addCellHeader("V",   table, 1);
			helper.addCellHeader("VI",  table, 1);

			for ( ArticolazioneClassiMMIIDTO progetto :componete.getItems() ){
				j++;
				helper.addCell(progetto.getIndirizzo() , table, j);
				helper.addCell(progetto.getClassiI(), table, j);
				helper.addCell(progetto.getClassiII() , table, j);
				helper.addCell(progetto.getClassiIII() , table, j);
				helper.addCell(progetto.getClassiIV() , table, j);
				helper.addCell(progetto.getClassiV() , table, j);
				helper.addCell(progetto.getClassiVI() , table, j);
			}



			helper.addSpace(document);
			document.add(table);
			helper.addSpace(document);

		}
		 
	}

 

}
