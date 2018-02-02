package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.ProgrammazioneFormDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAmmDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormDocDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteProgrammazioneFormAmmPdfImpl  implements ComponentePdf<ComponenteProgrammazioneFormAmmDTO> {
 	
	@Override
	public void load(ComponenteProgrammazioneFormAmmDTO comp, Document document, PdfWriter writer) throws Exception {
		  
		    int j=0;
			for ( ProgrammazioneFormDTO progetto :comp.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(comp.getNome());
			 
				
				tabella.setNome(comp.getNome());
				tabella.addMaster("Ambito Formativo", progetto.getAmbitoFormativo().getValue() );
				tabella.addMaster("Denominazione percorso formativo", progetto.getDenominazionePercorsoFormativo());
				
				tabella.addDetail("Obiettivi", progetto.getObiettivi());
				tabella.addDetail("Contenuti", progetto.getContenuti());
				tabella.addDetail("Tempi/ore complessive", ""+progetto.getTempiOreComplessive());
				tabella.addDetail("Data inizio", progetto.getDataInizio() );
				tabella.addDetail("Data fine", progetto.getDataFine() );
				tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD() );
				tabella.addDetail("Percorso formativo organizzato tra piu' scuole", progetto.getPercorsoFormativo().getValue() );
				
				tabella.buildRow(j, document);
				  
				
			    
			}
		   

	}

}
