package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
import it.istruzione.ptof.beans.documenti.DotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.PromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponentePromozioneRapportiEntiTerritorioPdfImpl implements ComponentePdf<ComponentePromozioneRapportiEntiTerritorioDTO> {

	@Override
	public void load(ComponentePromozioneRapportiEntiTerritorioDTO comp, Document document, PdfWriter writer)
			throws Exception {

	    int j=0;
		for ( PromozioneRapportiEntiTerritorioDTO item :comp.getItems() ){
			j++;
			
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
		 
			tabella.addMaster("Enti Locali", ""+item.getEntiLocali());
			tabella.addMaster("Tipologia Promozione", ""+item.getTipologiaPromozione());
			 	
			tabella.addDetail("Azioni Intraprese", ""+item.getAzioniIntraprese());
			tabella.addDetail("Area tematica PNSD", ""+item.getAreaTematicaPNSD());
			tabella.buildRow(j, document);
 
		    
		}

		
	}

}
