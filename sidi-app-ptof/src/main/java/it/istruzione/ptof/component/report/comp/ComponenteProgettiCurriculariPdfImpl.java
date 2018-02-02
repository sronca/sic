package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePlessoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgettiCurriculariDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteProgettiCurriculariPdfImpl  implements ComponentePdf<ComponenteProgettiCurriculariDTO> {
 	
	@Override
	public void load(ComponenteProgettiCurriculariDTO comp, Document document, PdfWriter writer) throws Exception {
		  
		    int j=0;
			for ( AmbitiProgettiDTO progetto :comp.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(comp.getNome());
				tabella.addMaster("Ambito", progetto.getAmbito().getLabel());
				tabella.addMaster("Tipologia Progetto", progetto.getTipologiaProgetto().getLabel());
				tabella.addMaster("Denominazione", progetto.getDenominazione());
				
				tabella.addDetail("Destinatari", progetto.getDestinatari());
				tabella.addDetail("Obiettivi/Altre Priorità", progetto.getObiettiviAltrePriorita());
				tabella.addDetail("Principali Contenuti", progetto.getPrincipaliContenuti());
				tabella.addDetail("Modalità/Approcci formativi utilizzati", progetto.getModalitApprocciFormativiUtilizzati());
				tabella.addDetail("Periodo di svolgimento", progetto.getPeriodoDiSvolgimento());
				tabella.addDetail("Durata dal", progetto.getDurataDal());
				tabella.addDetail("Durata al", progetto.getDurataAl());
				tabella.addDetail("Area tematica PNSD", progetto.getAreaTematicaPNSD());
				tabella.addDetail("Rilevanza per la scuola", progetto.getRilevanzaPerScuola());
				tabella.addDetail("Cooperazione con altre scuole", progetto.getCooperazioneConAltreScuole());
				tabella.addDetail("Risorse Finanziare necessarie", progetto.getRisorseFinanziareNecessarie());
				tabella.addDetail("Risorse umane/area", progetto.getRisorseUmaneArea());
				tabella.addDetail("Altre Risorse necessarie", progetto.getAltreRisorseNecessarie());
				tabella.addDetail("Stato Avanzamento", progetto.getStatoAvanzamento());
				tabella.buildRow(j, document);
				document.newPage();
				
			    
			}
		   

	}

}	

