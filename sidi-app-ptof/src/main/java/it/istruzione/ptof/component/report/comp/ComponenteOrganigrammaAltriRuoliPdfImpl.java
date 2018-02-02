package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.OrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaAltriRuoliDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteOrganigrammaAltriRuoliPdfImpl  implements ComponentePdf<ComponenteOrganigrammaAltriRuoliDTO>{

	@Override
	public void load(ComponenteOrganigrammaAltriRuoliDTO componete, Document document, PdfWriter writer) throws Exception {
		 int j=0;
			for ( OrganigrammaDTO progetto :componete.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(componete.getNome());
				tabella.addMaster("Ruolo", progetto.getRuolo().getValue() );
				tabella.addMaster("Nome", progetto.getNome() );
				tabella.addMaster("Responsabilita'", progetto.getResponsabilita());
				tabella.addDetail("Note", progetto.getNoteResponsabilita());

				tabella.buildRow(j, document);
				 
				
			    
			}
		
	}

}
