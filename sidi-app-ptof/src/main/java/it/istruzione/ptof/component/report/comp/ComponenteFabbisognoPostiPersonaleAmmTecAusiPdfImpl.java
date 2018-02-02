package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPersonaleAmmTecAusiDTO;
 
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO;
 
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiPersonaleAmmTecAusiPdfImpl
		implements ComponentePdf<ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO> {
	 

	@Override
	public void load(ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO comp, Document document, PdfWriter writer)
			throws Exception {

/*		OLD
		int j = 0;
		for (FabbisognoPostiPersonaleAmmTecAusiDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("", 1);
			tabella.addHeader("Posti",3);
			 
			tabella.addMaster("Profilo Professionale", progetto.getFiguraProfessionale());
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getNumeroPostiPrimoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getNumeroPostiSecondoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getNumeroPostiTerzoAnnoTriennio());
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}*/
		
		if (comp != null && comp.getItems() != null && !comp.getItems().isEmpty()){

			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100f);
			helper.addHeaderTab(comp.getNome() , table, 4);
			helper.addCellHeader("", table, 1);
			helper.addCellHeader("Posti", table, 3);


			helper.addCellHeader("Profilo Professionale", table, 1);
			helper.addCellHeader(comp.getLabelAnno0(), table, 1);
			helper.addCellHeader(comp.getLabelAnno1(), table, 1);
			helper.addCellHeader(comp.getLabelAnno2(), table, 1);

			int i = 0;
			for (FabbisognoPostiPersonaleAmmTecAusiDTO progetto : comp.getItems()){
				i++;
				helper.addCell(progetto.getFiguraProfessionale() , table, i);
				helper.addCell(progetto.getNumeroPostiPrimoAnnoTriennio(), table, i);
				helper.addCell(progetto.getNumeroPostiSecondoAnnoTriennio(), table, i);
				helper.addCell(progetto.getNumeroPostiTerzoAnnoTriennio(), table, i);
				
				helper.addCell("Motivazione", table, i);
				helper.addCell(progetto.getMotivazione(), table, i, 3);

			}
			document.add(table);
			document.newPage();

		}		

	}
}
