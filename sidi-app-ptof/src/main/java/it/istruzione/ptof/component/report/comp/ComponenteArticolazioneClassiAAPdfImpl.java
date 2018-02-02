package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteArticolazioneClassiAAPdfImpl implements ComponentePdf<ComponenteArticolazioneClassiAADTO> {

	@Override
	public void load(ComponenteArticolazioneClassiAADTO componete, Document document, PdfWriter writer) throws Exception {

		  
		
 
		
	    int j=0;
		for ( ArticolazioneClassiAADTO progetto :componete.getItems() ){
			j++;
			
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(componete.getNome());
			tabella.addMaster("Sezione Orario Normale", ""+progetto.getSezioneOrarioNormale().intValue() );
			tabella.addMaster("Sezione Orario Ridotto", ""+progetto.getSezioneOrarioRidotto().intValue() );

			tabella.buildRow(j, document);
			  
			
		    
		}
		
	}

}
