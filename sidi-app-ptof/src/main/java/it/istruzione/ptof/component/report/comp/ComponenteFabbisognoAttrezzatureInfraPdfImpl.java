package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

 
import it.istruzione.ptof.beans.documenti.FabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoAttrezzatureInfraPdfImpl  implements ComponentePdf<ComponenteFabbisognoAttrezzatureInfraDTO>{

	@Override
	public void load(ComponenteFabbisognoAttrezzatureInfraDTO componete, Document document, PdfWriter writer)
			throws Exception {
		
		int j=0;
			for ( FabbisognoAttrezzatureInfraDTO progetto : componete.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(componete.getNome());
				tabella.addMaster("Tipologia", progetto.getTipologia());
				tabella.addMaster("Descrizione", progetto.getDescrizione());
				
				tabella.addDetail("Numeri pezzi", progetto.getNumeroPezzi());
				tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD());
				tabella.addDetail("Stima costi attesi", progetto.getStimaCosti());
				tabella.addDetail("Fonte finanziamento", progetto.getFonteFinanziamento() );
			 
				tabella.buildRow(j, document);
				document.newPage();
				
			    
			}
		
	}

}
