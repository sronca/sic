package it.istruzione.ptof.component.report.comp;

import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

public class ComponenteArticolazioneClassiMMIPdfImpl implements ComponentePdf<ComponenteArticolazioneClassiMMIDTO> {

	@Override
	public void load(ComponenteArticolazioneClassiMMIDTO componete, Document document, PdfWriter writer)
			throws Exception {
	

	    int j=0;
		for ( ArticolazioneClassiIMMIDTO item :componete.getItems() ){
			j++;
			
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(componete.getNome());
			tabella.addHeader("Totali classi tempo normale", 3);
			tabella.addHeader("Totali classi tempo prolungato", 3);
			
			tabella.addMaster("I", ""+item.getTempoNormaleTotaliClassiI());
			tabella.addMaster("II", ""+item.getTempoNormaleTotaliClassiII());
			tabella.addMaster("III", ""+item.getTempoNormaleTotaliClassiIII());
			tabella.addMaster("I", ""+item.getTempoProlungatoTotaliClassiI());
			tabella.addMaster("II", ""+item.getTempoProlungatoTotaliClassiII());
			tabella.addMaster("III", ""+item.getTempoProlungatoTotaliClassiIII());
			tabella.buildRow(j, document);
			
		    
		}

	
	}

 

}
