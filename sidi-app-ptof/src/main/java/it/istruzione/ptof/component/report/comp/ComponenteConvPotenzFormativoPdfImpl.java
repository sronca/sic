package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ConvPotenzFormativoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteConvPotenzFormativoDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteConvPotenzFormativoPdfImpl implements ComponentePdf<ComponenteConvPotenzFormativoDTO> {

	@Override
	public void load(ComponenteConvPotenzFormativoDTO comp, Document document, PdfWriter writer)
			throws Exception {
		 int j=0;
			for ( ConvPotenzFormativoDTO progetto :comp.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(comp.getNome());
			 
				
				tabella.setNome(comp.getNome());
				tabella.addMaster("Convenzioni", progetto.getConvenzioni() );
				tabella.addMaster("Organizzazione", progetto.getOrganizzazione() );
				
				tabella.addDetail("Note", progetto.getNote() );
				tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD() );
			 
				
				tabella.buildRow(j, document);
				  
				
			    
			}
	}

}
