package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AlternanzaScuolaLavoroDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAlternzaScuolaLavoroDTO;
 

public class ComponenteAlternzaScuolaLavoroPdfImpl implements ComponentePdf<ComponenteAlternzaScuolaLavoroDTO> {

	@Override
	public void load(ComponenteAlternzaScuolaLavoroDTO componete, Document document, PdfWriter writer)
			throws Exception {

		if (componete != null && componete.getItems() != null && !componete.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(componete.getNome() , table, 6);
			helper.addCellHeader("", table, 3);
			helper.addCellHeader("Numero Alumni", table, 3);


			helper.addCellHeader("Percorso", table, 1);
			helper.addCellHeader("Struttura", table, 1);
			helper.addCellHeader("Azienda", table, 1);
			helper.addCellHeader("I Anno", table, 1);
			helper.addCellHeader("II Anno", table, 1);
			helper.addCellHeader("III Anno", table, 1);

			int i = 0;
			for ( AlternanzaScuolaLavoroDTO item :  componete.getItems() ){
				i++;
				helper.addCell(item.getIdentificativoPercorso() , table, i);
				helper.addCell(item.getStruttura() , table, i);
				helper.addCell(item.getAzienda() , table, i);
				helper.addCell(item.getNumeroAlunniPrimoAnnoTriennio() , table, i);
				helper.addCell(item.getNumeroAlunniSecondoAnnoTriennio() , table, i);
				helper.addCell(item.getNumeroAlunniTerzoAnnoTriennio() , table, i);

			}
			document.add(table);
			document.newPage();

		}
		
	}

}
