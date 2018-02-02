package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ProgrammazioneFormDTO;
import it.istruzione.ptof.beans.documenti.StrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormDocDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteStrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteStrumentiAttrezzatureTecnologichePdfImpl  implements ComponentePdf< ComponenteStrumentiAttrezzatureTecnologicheDTO> {

	@Override
	public void load(ComponenteStrumentiAttrezzatureTecnologicheDTO comp, Document document, PdfWriter writer)
			throws Exception {
		   int j=0;
					for ( StrumentiAttrezzatureTecnologicheDTO progetto :comp.getItems() ){
						j++;
						
						CTabellaPDF tabella = new CTabellaPDF();
						tabella.setNome(comp.getNome());
					 
						
						tabella.setNome(comp.getNome());
						tabella.addMaster("Strumenti e attrezzature", progetto.getStrumentiAttrezzature()  );
						 
						tabella.addDetail("Descrizione", progetto.getDescrizione());
						tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD());
						 
						
						tabella.buildRow(j, document);
						  
						
					    
					}
		
	}

}
