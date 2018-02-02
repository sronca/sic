package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltriProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAttivitaSostegnoDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteAttivitaSostegnoPdfImpl implements ComponentePdf< ComponenteAttivitaSostegnoDTO> {

	@Override
	public void load(ComponenteAttivitaSostegnoDTO comp, Document document, PdfWriter writer) throws Exception {
		  int j=0;
			for ( AttivitaSostegnoDTO progetto :comp.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(comp.getNome());
				tabella.addMaster("Contenuti dell'attivita' di sostegno", progetto.getContenutiAttivitaSostegno());
				tabella.addDetail("Metodologie utilizzate", progetto.getMetodologieUtilizzate());
				
				tabella.addDetail("Cooperazione con servizi socio-sanitari e associazioni di settore", progetto.getCoopServiziSSAssocSettore());
				tabella.addDetail("Aspetti di supporto logistico", progetto.getAspettiSupportoLogistico());
				tabella.addDetail("Note", progetto.getNote());
				
				tabella.buildRow(j, document);
				document.newPage();
				
			    
			}
		   
		
	}

}
