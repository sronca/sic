package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiCattedreEEAAPdfImpl 
implements ComponentePdf<ComponenteFabbisognoPostiCattedreEEAADTO>{

	@Override
	public void load(ComponenteFabbisognoPostiCattedreEEAADTO comp, Document document, PdfWriter writer)
			throws Exception {
		int j = 0;
		for (FabbisognoPostiCattedreDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("Cattedre", 3);
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiComuniPrimoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiComuniSecondoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiComuniTerzoAnnoTriennio());
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}
	}

}
