package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.componenti.ComponenteMonitoraggioPianificazioneDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;
 

public class ComponenteMonitoraggioPianificazionePdfImpl implements ComponentePdf<ComponenteMonitoraggioPianificazioneDTO> {

	@Override
	public void load(ComponenteMonitoraggioPianificazioneDTO comp, Document document, PdfWriter writer)
			throws Exception {
		 
		CTabellaPDF tabella = new CTabellaPDF();
		//tabella.setNome(comp.getNome());
		tabella.addDetail("Modalità di verifica", comp.getModalitaVerifica() );
		tabella.addDetail("Descrizione", comp.getSoluzioniAdottateRispettoPianificazione() );
		  
		tabella.buildRow(1, document);
	}

}
