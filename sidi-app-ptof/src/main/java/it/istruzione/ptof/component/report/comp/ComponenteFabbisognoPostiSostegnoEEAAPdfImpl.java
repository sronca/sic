package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiSostegnoEEAAPdfImpl  implements ComponentePdf<ComponenteFabbisognoPostiSostegnoEEAADTO>{

	@Override
	public void load(ComponenteFabbisognoPostiSostegnoEEAADTO comp, Document document, PdfWriter writer)
			throws Exception {
		int j = 0;
		for (FabbisognoPostiSostegnoEEAADTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("Posti di sostegno nel triennio", 9);
			
			tabella.addHeader("Udito", 3);
			tabella.addHeader("Vista", 3);
			tabella.addHeader("Psicofisico", 3);
			

			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiSostegnoUditoPrimoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiSostegnoUditoSecondoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiSostegnoUditoTerzoTriennio());
			
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiSostegnoVistaPrimoTriennio() );
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiSostegnoVistaSecondoTriennio() );
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiSostegnoVistaTerzoTriennio() );
			
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiSostegnoPsicofisicoPrimoTriennio());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiSostegnoPsicofisicoSecondoTriennio());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiSostegnoPsicofisicoTerzoTriennio());
			
			
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}
		
	}

}
