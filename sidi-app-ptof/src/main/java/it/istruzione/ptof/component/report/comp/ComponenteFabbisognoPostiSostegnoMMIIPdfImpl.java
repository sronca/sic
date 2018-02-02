package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiSostegnoMMIIPdfImpl  implements ComponentePdf<ComponenteFabbisognoPostiSostegnoMMIIDTO>{

	@Override
	public void load(ComponenteFabbisognoPostiSostegnoMMIIDTO comp, Document document, PdfWriter writer)
			throws Exception {
		int j = 0;
		for (FabbisognoPostiSostegnoMMIIDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("Posti Sostegno", 3);
			 
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getNumeroPostiPrimoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getNumeroPostiSecondoAnnoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getNumeroPostiTerzoAnnoTriennio());
		 	
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}
		
	}

}