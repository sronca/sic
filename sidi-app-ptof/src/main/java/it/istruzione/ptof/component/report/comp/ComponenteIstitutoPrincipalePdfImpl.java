package it.istruzione.ptof.component.report.comp;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteIstitutoPrincipaleDTO;

public class ComponenteIstitutoPrincipalePdfImpl implements ComponentePdf<ComponenteIstitutoPrincipaleDTO> {
 
	@Override
	public void load(ComponenteIstitutoPrincipaleDTO comp, Document document, PdfWriter writer) throws DocumentException {
			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		    IstitutoScolasticoDTO ist = comp.getIstitutoScolasticoDTO();
			createTableIst(document, ist);
			helper.addSpace(document);
			createPlesso(document, ist);
			
		
	}

	
	public void createPlesso(Document  document, IstitutoScolasticoDTO ist) throws DocumentException {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(4);
		helper.addHeaderTab("Dati Plessi", table, 4);

		helper.addCellHeader("Plesso/Scuola", table, 1);
		helper.addCellHeader("Indirizzo ", table, 1);
		helper.addCellHeader("Telefono ", table, 1);
		helper.addCellHeader("Mail ", table, 1);
		int i = 0;
		for (PlessiDTO plesso : ist.getPlessi()) {
			i++;
			helper.addCell(plesso.getCodiceMecUtente(), table, i);
			helper.addCell(plesso.getIndirizzo(), table, i);
			helper.addCell(plesso.getTelefono(), table, i);
			helper.addCell(plesso.getEmail(), table, i);
		}
		helper.addSpace(document);
		document.add(table);
		helper.addSpace(document);
	}

	public void createTableIst(Document  document, IstitutoScolasticoDTO ist) throws DocumentException {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(2);
		helper.addHeaderTab("Dati istituto principale", table, 2);
		int i = 0;
		helper.addRow(table, i++,"Denominazione: " , ist.getDenominazione());
		helper.addRow(table, i++,"Tipologie Scuole associate: " , ist.getTipologieScuoleAssociate());
		helper.addRow(table, i++,"Dirigente Scolastico: " , ist.getDirigenteScolastico());
		helper.addRow(table, i++,"Indirizzo: " , ist.getIndirizzo());
		helper.addRow(table, i++,"Codice Meccanografico: " , ist.getCodiceMecIstPrin());
		helper.addRow(table, i++,"Telefono : " , ist.getTelefono());
		helper.addRow(table, i++,"Fax : " , ist.getFax());
		helper.addRow(table, i++,"Email : " , ist.getEmail());
		helper.addRow(table, i++,"PEC : " , ist.getPec());
		helper.addRow(table, i++,"Sito WEB : " , ist.getSitoWeb());
		helper.addRow(table, i++,"Numero Plessi/scuole : " , ""+ist.getNumeroPlessi());
		helper.addRow(table, i++,"Di cui: " , ist.getNumeroPlessiPerTipologiaScuola());
		helper.addSpace(document);
		document.add(table);
		helper.addSpace(document);

	}

}
