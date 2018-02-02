package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltreIniziativeDidaDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;


public class ComponenteAltreIniziativeDidaPdfImpl implements ComponentePdf<ComponenteAltreIniziativeDidaDTO>{

	@Override
	public void load(ComponenteAltreIniziativeDidaDTO comp, Document document, PdfWriter writer) throws Exception {
		
		
		  
	    int j=0;
		for ( IniziativeDidatticheEducativeDTO progetto :comp.getItems() ){
			j++;
			
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
			tabella.addMaster("Titolo", progetto.getTitoli() );
			tabella.addMaster("Obiettivi", progetto.getObiettivi());
			
			tabella.addDetail("Contenuti", progetto.getContenuti());
			tabella.addDetail("Modalita'", progetto.getModalita());
			tabella.addDetail("Data inizio", progetto.getDataInizio());
			tabella.addDetail("Data fine", progetto.getDataFine() );
			tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD());
			tabella.addDetail("Note", progetto.getNote());

			tabella.buildRow(j, document);
			  
			
		    
		}

		
	}

}
