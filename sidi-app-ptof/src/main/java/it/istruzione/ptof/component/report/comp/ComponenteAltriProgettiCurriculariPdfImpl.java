package it.istruzione.ptof.component.report.comp;

import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltriProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviMiglioramentoDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteAltriProgettiCurriculariPdfImpl implements ComponentePdf<ComponenteAltriProgettiCurriculariDTO>{

	@Override
	public void load(ComponenteAltriProgettiCurriculariDTO componete, Document document, PdfWriter writer)
			throws Exception {
		 
		 int j=0;
			for ( AmbitiProgettiDTO progetto : componete.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(componete.getNome());
				tabella.addMaster("Ambito", progetto.getAmbito().getValue());
				tabella.addMaster("Tipologia Progetto", progetto.getTipologiaProgetto().getValue());
				tabella.addMaster("Denominazione", progetto.getDenominazione());
				
				tabella.addDetail("Destinatari", progetto.getDestinatari());
				tabella.addDetail("Obiettivi/Altre Priorità", progetto.getObiettiviAltrePriorita());
				tabella.addDetail("Principali Contenuti", progetto.getPrincipaliContenuti());
				tabella.addDetail("Modalità/Approcci formativi utilizzati", progetto.getModalitApprocciFormativiUtilizzati());
				tabella.addDetail("Periodo di svolgimento", progetto.getPeriodoDiSvolgimento());
				tabella.addDetail("Durata dal", progetto.getDurataDal());
				tabella.addDetail("Durata al", progetto.getDurataDal());
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
