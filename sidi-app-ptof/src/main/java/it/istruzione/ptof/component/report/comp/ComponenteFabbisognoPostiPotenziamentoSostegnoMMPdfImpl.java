package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoSostegnoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoPostiPotenziamentoSostegnoMMPdfImpl   implements ComponentePdf<ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO>{

	@Override
	public void load(ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO comp, Document document, PdfWriter writer)
			throws Exception {
		int j = 0;
		for (FabbisognoPostiPotenziamentoSostegnoMMDTO progetto : comp.getItems()) {
			j++;

			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			//tabella.addHeader("", 1);
			tabella.addHeader("Posti di Potenziamento", 3);
			//tabella.addHeader("Classe Concorso", 1);
			 
            tabella.addMaster(comp.getLabelAnno0(), "" + progetto.getPostiSostegnoPrimoAnno());
			tabella.addMaster(comp.getLabelAnno1(), "" + progetto.getPostiSostegnoSecondoAnno());
			tabella.addMaster(comp.getLabelAnno2(), "" + progetto.getPostiSostegnoTerzoAnno());
		 	
			tabella.addDetail("Motivazione", progetto.getMotivazione());

			tabella.buildRow(j, document);

		}
		
	}

}