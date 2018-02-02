package it.istruzione.ptof.component.report.comp;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.PrioritaTraguardiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltreIniziativeDidaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteFabbisognoConnessoProgettoPdfImpl implements ComponentePdf<ComponenteFabbisognoConnessoProgettoDTO> {

	@Override
	public void load(ComponenteFabbisognoConnessoProgettoDTO componete, Document document, PdfWriter writer)
			throws Exception {
		    int j=0;
		    PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		    HashMap<String, BigDecimal> importoStimatoTotale = new HashMap<>();
		    HashMap<String, BeanValueDTO> progetti = new HashMap<>();
			
		    
			for ( FabbisognoConnessoProgettoDTO progetto :componete.getItems() ){
				j++;
				String keyProgetto = progetto.getTipoProgetti().getValue();
				if( null == importoStimatoTotale.get(keyProgetto)){
					importoStimatoTotale.put(keyProgetto,  progetto.getImportoStimato() );
					progetti.put(keyProgetto, progetto.getTipoProgetti()  );
				}   else  if ( progetto.getImportoStimato()!=null ) {
					BigDecimal totale  = importoStimatoTotale.get(keyProgetto).add(progetto.getImportoStimato());
					importoStimatoTotale.put(keyProgetto, totale );
				}
			}
		    
			for ( FabbisognoConnessoProgettoDTO progetto :componete.getItems() ){
				j++;
				
				 
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(componete.getNome());
				tabella.addMaster("Tipo Progetto", progetto.getTipoProgetti().getLabel() );
				tabella.addMaster("Denominazione Progetto Scuola", progetto.getDenominazioneProgettiScuola().getLabel() );
				tabella.addMaster("	Codice Meccanografico", progetto.getCodiceMeccanograficoPlesso() );
				
				tabella.addDetail("Nome Bene/Servizio", progetto.getNomeBeneServizio() );
				tabella.addDetail("Descrizione Bene/Servizio", progetto.getDescrizioneBeneServizio() );
				tabella.addDetail("Classificazione", progetto.getClassificazione());
				tabella.addDetail("Numero Docenti ATA Coinvolti", progetto.getNumeroDocentiATACoinvolti()  );
				tabella.addDetail("Importo Stimato",  progetto.getImportoStimato() );
				tabella.addDetail("Note", progetto.getNote());

				tabella.buildRow(j, document);
			    
			}
	
			PdfPTable table = new PdfPTable(2);
			helper.addHeaderTab("Totale importi stimati per tipo progetto" , table, 2);
			helper.addCellHeader("Tipo Progetto", table, 1);
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
