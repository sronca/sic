package it.istruzione.ptof.component.report.comp;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoConnessoFormazionePdfImpl implements ComponentePdf<ComponenteFabbisognoConnessoFormazioneDTO> {

	@Override
	public void load(ComponenteFabbisognoConnessoFormazioneDTO componete, Document document, PdfWriter writer)
			throws Exception {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		 HashMap<String, BigDecimal> importoStimatoTotale = new HashMap<>();
		    HashMap<String, BeanValueDTO> progetti = new HashMap<>();
			

		for ( FabbisognoConnessoFormazioneDTO progetto :componete.getItems() ){
			String keyProgetto = progetto.getAmbitoFormativo().getValue();
			if( null == importoStimatoTotale.get(keyProgetto)){
				importoStimatoTotale.put(keyProgetto,progetto.getImportoStimato());	
				progetti.put(keyProgetto, progetto.getAmbitoFormativo()  );
				
			} else if ( progetto.getImportoStimato()!=null ) {
				BigDecimal totale  = importoStimatoTotale.get(keyProgetto).add(progetto.getImportoStimato());
				importoStimatoTotale.put(keyProgetto, totale );
			}
		}
	    
		
	    int j=0;
		for ( FabbisognoConnessoFormazioneDTO progetto :componete.getItems() ){
			j++;
			
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(componete.getNome());
			tabella.addMaster("Ambito Formativo", progetto.getAmbitoFormativo().getLabel() );
			tabella.addMaster("Denominazione Percorso Formativo", progetto.getDenominazionePercorsoFormativo().getLabel());
			
			tabella.addDetail("Nome Bene/Servizio", progetto.getNomeBeneServizio() );
			tabella.addDetail("Descrizione Bene/Servizio", progetto.getDescrizioneBeneServizio() );
			tabella.addDetail("Classificazione", progetto.getClassificazione() );
			tabella.addDetail("Numero Docenti ATA Coinvolti", progetto.getNumeroDocentiATACoinvolti() );
			tabella.addDetail("Importo Stimato", progetto.getImportoStimato()  );
			tabella.addDetail("Note", progetto.getNote());

			tabella.buildRow(j, document);
			  
			
		    
		}
		

		PdfPTable table = new PdfPTable(2);
		helper.addHeaderTab("Totale importi stimati per ambito formativo" , table, 2);
		helper.addCellHeader("Tipo Ambito Formativo", table, 1);
		helper.addCellHeader("Importo Stimato", table, 1);
		int i = 0;
		for ( String progetto :importoStimatoTotale.keySet() ){
				helper.addCell(progetti.get(progetto).getLabel() , table, i);
				helper.addCell( importoStimatoTotale.get(progetto) , table, i);
		}
		helper.addSpace(document);
		document.add(table);
	    helper.addSpace(document);
		
	}

}
