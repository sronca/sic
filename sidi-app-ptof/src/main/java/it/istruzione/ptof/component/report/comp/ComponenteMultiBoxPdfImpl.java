package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteComboBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteIstitutoPrincipaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMultiBoxDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;

public class ComponenteMultiBoxPdfImpl implements ComponentePdf<ComponenteMultiBoxDTO> {


	public void createTableIst(Document  document, IstitutoScolasticoDTO ist) throws DocumentException {
		

	}


	@Override
	public void load(ComponenteMultiBoxDTO componete, Document document, PdfWriter writer) throws Exception {
		

		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(2);
		if ( componete.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO)==0 )
		{ 
			helper.addHeaderTab("Quadro sintesi contesto", table, 2); 
		}
		int i = 0;
		for (ComponenteComboBoxDTO box : componete.getComboBox() ){
			    helper.addRow(table, i++, box.getNome() ,  box.getSelected().getLabel() );
		}
	    helper.addSpace(document);
		document.add(table);
		helper.addSpace(document);
	}

}
