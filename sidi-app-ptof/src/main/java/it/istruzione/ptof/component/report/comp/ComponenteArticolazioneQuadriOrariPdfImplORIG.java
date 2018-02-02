package it.istruzione.ptof.component.report.comp;

import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariMateriaDTO;
import it.istruzione.ptof.beans.documenti.StrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteArticolazioneQuadriOrariPdfImplORIG implements ComponentePdf< ComponenteArticolazioneQuadriOrariDTO> {

	@Override
	public void load(ComponenteArticolazioneQuadriOrariDTO comp, Document document, PdfWriter writer)
			throws Exception {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		 	
		PdfPTable tableInner = new PdfPTable(1);
		createTab1(tableInner ,comp , helper);
		createTab2(tableInner, comp, helper);
        PdfPTable cornice = helper.creaCornice(tableInner);
		
		document.add(cornice);
		helper.addSpace(document);
	}

	

	public void createTab2(PdfPTable cornice , ComponenteArticolazioneQuadriOrariDTO comp, PtofComponentePdfHelper helper) {
		PdfPTable table = new PdfPTable(1);
		helper.addRow(table, 0, "Note" ,comp.getNota() );
		cornice.addCell(table);
	}
	
	public void createTab1(PdfPTable cornice ,ComponenteArticolazioneQuadriOrariDTO comp, PtofComponentePdfHelper helper) throws DocumentException {
		
		
		int j=0;
		for ( ArticolazioneQuadriOrariDTO progetto :comp.getItems() ){
			j++;
			CTabellaPDF tabella = new CTabellaPDF();
			tabella.setNome(comp.getNome());
		    tabella.addMaster("Anno Scolastico", progetto.getAnnoScolastico()  );
			tabella.addMaster("Indirizzo Studio", progetto.getDescrizioneIndirizzoScolastico());
			tabella.addMaster("Piano Studio", progetto.getDescrizionePianoStudio());
			tabella.addDetail(createDettaglio(progetto.getItems()));
			tabella.buildRow( j , cornice );
		}
		 
	}

	private PdfPTable createDettaglio(LinkedList<ArticolazioneQuadriOrariMateriaDTO> items) {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(7);
	 	helper.addCellHeader("Discipline", table, 1, 2 );
		helper.addCellHeader("Ore settimanali", table, 6 );
		
		helper.addCellHeader("I", table, 1);
		helper.addCellHeader("II", table, 1);
		helper.addCellHeader("III", table, 1);
		helper.addCellHeader("IV", table, 1);
		helper.addCellHeader("V", table, 1);
		helper.addCellHeader("VI", table, 1);
		
		int i = 0;
		for ( ArticolazioneQuadriOrariMateriaDTO item :  items ){
			i++;
			helper.addCell(item.getDescrizioneMateriaScuola() , table, i);
			helper.addCell(item.getNumeroAlunniPrimo() , table, i);
			helper.addCell(item.getNumeroAlunniSecondo() , table, i);
			helper.addCell(item.getNumeroAlunniTerzo() , table, i);
			helper.addCell(item.getNumeroAlunniQuarto() , table, i);
			helper.addCell(item.getNumeroAlunniQuinto() , table, i);
			helper.addCell(item.getNumeroAlunniSesto() , table, i);
		 }
		
		return table;
	}


}
