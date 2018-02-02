package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AlternanzaScuolaLavoroDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiCattedreMMPdfImpl  
implements ComponentePdf<ComponenteFabbisognoPostiCattedreMMDTO>{

	@Override
	public void load(ComponenteFabbisognoPostiCattedreMMDTO comp, Document document, PdfWriter writer)
			throws Exception {
		
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(comp.getNome() , table, 4);
			helper.addCellHeader("", table, 1);
			helper.addCellHeader("Cattedre", table, 3);


			helper.addCellHeader("Classe Concorso", table, 1);
			helper.addCellHeader(comp.getLabelAnno0(), table, 1);
			helper.addCellHeader(comp.getLabelAnno1(), table, 1);
			helper.addCellHeader(comp.getLabelAnno2(), table, 1);

			int i = 0;
			for (FabbisognoPostiCattedreMMDTO progetto : comp.getItems()){
				i++;
				helper.addCell(progetto.getClasseConcorso() , table, i);
				helper.addCell(progetto.getCattedrePrimoAnnoTriennio(), table, i);
				helper.addCell(progetto.getCattedreSecondoAnnoTriennio(), table, i);
				helper.addCell(progetto.getCattedreTerzoAnnoTriennio(), table, i);
				
				helper.addCell("Motivazione", table, i);
				helper.addCell(progetto.getMotivazione(), table, i, 3);

			}
			document.add(table);
			document.newPage();

		}
		
		
/*		OLD
		int j = 0;
		for (FabbisognoPostiCattedreMMDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("", 1);
			tabella.addHeader("Cattedre", 3);
			
			tabella.addMaster(" Classe Concorso", "" + progetto.getClasseConcorso());
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getCattedrePrimoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getCattedreSecondoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getCattedreTerzoAnnoTriennio());
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}*/
		
		
		
	}

}
