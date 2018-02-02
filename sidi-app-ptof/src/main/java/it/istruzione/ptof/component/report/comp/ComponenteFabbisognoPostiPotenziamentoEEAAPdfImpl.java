package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoEEAADTO;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoEEAADTO;

import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiPotenziamentoEEAAPdfImpl  implements ComponentePdf<ComponenteFabbisognoPostiPotenziamentoEEAADTO>{

	@Override
	public void load(ComponenteFabbisognoPostiPotenziamentoEEAADTO comp, Document document, PdfWriter writer)
			throws Exception {
		int j = 0;
		for (FabbisognoPostiPotenziamentoEEAADTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addHeader("Tipologia posti nel triennio", 6);
			
			tabella.addHeader("Comuni	", 3);
			tabella.addHeader("Sostegno", 3);
		 
			
            tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiComuniPrimoAnno());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiComuniSecondoAnno());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiComuniTerzoAnno());
			
			tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiSostegnoPrimoAnno() );
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiSostegnoSecondoAnno() );
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiSostegnoTerzoAnno() );
		 
			
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}
		
	}

}