package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiEEDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteArticolazioneClassiEEPdfImpl implements ComponentePdf<ComponenteArticolazioneClassiEEDTO> {

	@Override
	public void load(ComponenteArticolazioneClassiEEDTO componete, Document document, PdfWriter writer)
			throws Exception {
		    int j=0;
			for ( ArticolazioneClassiEEDTO item :componete.getItems() ){
				j++;
				
				CTabellaPDF tabella = new CTabellaPDF();
				tabella.setNome(componete.getNome());
				tabella.addHeader("Classi Tempo Normale", 6);
				tabella.addMaster("I", ""+item.getClassiAlunniTempoNormaleI());
				tabella.addMaster("II", ""+item.getClassiAlunniTempoNormaleII());
				tabella.addMaster("III", ""+item.getClassiAlunniTempoNormaleIII());
				tabella.addMaster("IV", ""+item.getClassiAlunniTempoNormaleIV());
				tabella.addMaster("V", ""+item.getClassiAlunniTempoNormaleV());
				tabella.addMaster("PLURICL", ""+item.getClassiAlunniTempoNormalePLURICL()	);
			 //	tabella.buildRow(j, document);

			//	CTabellaPDF tabella1 = new CTabellaPDF();
				tabella.addHeader("Classi Tempo Pieno", 6);
				tabella.addMaster("I", ""+item.getClassiAlunniTempoPienoI());
				tabella.addMaster("II", ""+item.getClassiAlunniTempoPienoII());
				tabella.addMaster("III", ""+item.getClassiAlunniTempoPienoIII());
				tabella.addMaster("IV", ""+item.getClassiAlunniTempoPienoIV());
				tabella.addMaster("V", ""+item.getClassiAlunniTempoPienoV());
				tabella.addMaster("PLURICL", ""+item.getClassiAlunniTempoPienoPLURICL()	);
				tabella.buildRow(j, document);
				
			    
			}
	}

 

}
