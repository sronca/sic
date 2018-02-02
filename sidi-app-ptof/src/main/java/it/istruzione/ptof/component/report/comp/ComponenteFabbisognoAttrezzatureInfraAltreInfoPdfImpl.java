package it.istruzione.ptof.component.report.comp;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;
 

public class ComponenteFabbisognoAttrezzatureInfraAltreInfoPdfImpl  implements ComponentePdf<ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO> {

	@Override
	public void load(ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO componente, Document document, PdfWriter writer)	throws Exception {
		 
		 
			PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			int i = 0;
			helper.addRowSpan(table, i++,"Motivazione" , componente.getMotivazione());
			helper.addRowSpan(table, i++,"Finalità" , componente.getFinalita());
		 	helper.addRowSpan(table, i++,"Benefici Attesi: " , componente.getBeneficiAttesi());
	
		 	
		 	
		 	
			document.add(table);
 
	}

}
