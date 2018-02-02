package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiPotenziamentoMMPdfImpl   implements ComponentePdf<ComponenteFabbisognoPostiPotenziamentoMMDTO>{

	@Override
	public void load(ComponenteFabbisognoPostiPotenziamentoMMDTO comp, Document document, PdfWriter writer)
			throws Exception {
		
/*		OLD
		int j = 0;
		for (FabbisognoPostiPotenziamentoMMDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("", 1);
			tabella.addHeader("Posti di Potenziamento", 3);
			 
			tabella.addMaster(" Classe Concorso", "" + progetto.getClasseConcorso());
            tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiPotenziamentoPrimoAnno());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiPotenziamentoSecondoAnno());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiPotenziamentoTerzoAnno());
		 	
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}*/

		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(comp.getNome() , table, 4);
			helper.addCellHeader("", table, 1);
			helper.addCellHeader("Posti di Potenziamento", table, 3);


			helper.addCellHeader("Classe Concorso", table, 1);
			helper.addCellHeader(comp.getLabelAnno0(), table, 1);
			helper.addCellHeader(comp.getLabelAnno1(), table, 1);
			helper.addCellHeader(comp.getLabelAnno2(), table, 1);

			int i = 0;
			for (FabbisognoPostiPotenziamentoMMDTO progetto : comp.getItems()){
				i++;
				helper.addCell(progetto.getClasseConcorso() , table, i);
				helper.addCell(progetto.getPostiPotenziamentoPrimoAnno(), table, i);
				helper.addCell(progetto.getPostiPotenziamentoSecondoAnno(), table, i);
				helper.addCell(progetto.getPostiPotenziamentoTerzoAnno(), table, i);
				
				helper.addCell("Motivazione", table, i);
				helper.addCell(progetto.getMotivazione(), table, i, 3);

			}
			document.add(table);
			document.newPage();

		}
		
	}

}