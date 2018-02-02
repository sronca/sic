package it.istruzione.ptof.component.report.comp;

import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

 


import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDatiFinaliScuolaDTO;
import it.istruzione.ptof.helper.CommonsUtility;

public class ComponenteDatiFinaliScuolaPdfImpl implements ComponentePdf< ComponenteDatiFinaliScuolaDTO> {

	@Override
	public void load(ComponenteDatiFinaliScuolaDTO ist, Document document, PdfWriter writer) throws Exception {
		
		if(ist==null ){
			ist = new ComponenteDatiFinaliScuolaDTO();
		}
		
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		int i = 0;
		helper.addRow(table, i++,"Data ratifica atto di indirizzo Dirigente Scolastico" ,CommonsUtility.formatIt(ist.getDataRatificaAttoIndirizzoDirigente()) );
		helper.addRow(table, i++,"Data predisposizione PTOF Collegio dei Docenti" , CommonsUtility.formatIt(ist.getDataPredisposizionePTOFCollegioDocenti()));
		helper.addRow(table, i++,"Data approvazione Consiglio d'Istituto" , CommonsUtility.formatIt(ist.getDataApprovazioneConsiglioDocenti()));
		helper.addRow(table, i++,"Data invio USR" , (ist.getStatoDocumento() == TIPO_STATO_DOC.FABBISOGNO_VALIDATO || ist.getStatoDocumento() == TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA)?CommonsUtility.formatIt(ist.getDataInvioUSR()):"" );
		
		String labelDataPublicazione = "Data Anteprima";
		switch (ist.getStatoDocumento()) {
		case FABBISOGNO_VALIDATO:
			labelDataPublicazione = "Data Pubblicazione Definitiva";
			break;
		case DOCUMENTO_IN_ANTEPRIMA:
			labelDataPublicazione = "Data Pubblicazione Parziale";
			break;
		default:
			break;
		}
	 
		helper.addRow(table, i++, labelDataPublicazione , CommonsUtility.formatIt(new Date()));
		
		helper.addRow(table, i++,"Numero Revisione" , ist.getVersione() );
			
		helper.addSpace(document);
		helper.addSpace(document);
		document.add(table);
		helper.addSpace(document);
		helper.addSpace(document);
		
	}

}
