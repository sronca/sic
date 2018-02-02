package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.PianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponentePianificazioneInterventiMonitoraggioPdfImpl  implements ComponentePdf<ComponentePianificazioneInterventiMonitoraggioDTO> {

	@Override
	public void load(ComponentePianificazioneInterventiMonitoraggioDTO comp, Document document, PdfWriter writer)
			throws Exception {
		 
		 int j=0;
			for ( PianificazioneInterventiMonitoraggioDTO progetto :comp.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(comp.getNome());
				tabella.addMaster("Pianificazione Attivita'", progetto.getPianificazioneAttivita() );
				tabella.addMaster("Descrizione", progetto.getDescrizione() );
				tabella.addDetail("Capitolo PTOF", progetto.getCapitoloPTOF());
				
				tabella.addDetail("Data inizio", progetto.getDataInizio());
				tabella.addDetail("Data fine", progetto.getDataFine() );
				
				tabella.buildRow(j, document);
				  
				
			    
			}

	}

}
