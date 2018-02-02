package it.istruzione.ptof.component.report;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDatiFinaliScuolaDTO;
import it.istruzione.ptof.component.report.comp.ComponenteDatiFinaliScuolaPdfImpl;
import it.istruzione.ptof.component.report.comp.ComponentePdf;
import it.istruzione.ptof.component.report.comp.PtofComponentePdfHelper;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.service.GestionePtofService;

@Component
public class PtofReportFactory {

	@Autowired
	GestionePtofService gestionePtofService;
	
	@Autowired
	PtofComponentePdfHelper helper;

	private Logger logger = LoggerFactory.getLogger(PtofReportFactory.class);

	public ByteArrayOutputStream getDocument(ComponenteDatiFinaliScuolaDTO componenteDatiFinaliScuolaDTO , SezioneExtDTO sezioneEstesa , int livello ) throws Exception {
		logger.debug("--->> START BUILD SEZIONE " + sezioneEstesa.getTipoSezione().name() + "---------");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		document.open();
		if (livello>0 ){
		 buildIntestazioneSezione(sezioneEstesa, document, writer); 
		}
		for (ComponenteDTO comp : sezioneEstesa.getComponenti()) {
			logger.debug("---------START BUILD COMPONENTE---------" + comp.getTipoComponente().name() + "---------");

			ComponentePdf comDF;
			String simpleName = comp.getClass().getSimpleName();
			comDF = (ComponentePdf) Class.forName(ComponentePdf.class.getPackage().getName() + "."
					+ simpleName.substring(0, simpleName.length() - 3) + "PdfImpl").newInstance();
			comDF.load(comp, document, writer);
			logger.debug("----------END BUILD COMPONENTE---------");
			
			if(0== sezioneEstesa.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_02_FINITER)){
				
				ComponenteDatiFinaliScuolaPdfImpl compFinalita = new ComponenteDatiFinaliScuolaPdfImpl();
				compFinalita.load(componenteDatiFinaliScuolaDTO, document, writer);
			}
			
			
		} // for

		 
		
		document.close();
		logger.debug("----END BUILD SEZIONE---------");
		return baos;
	}

	
	private void buildIntestazioneSezione(SezioneExtDTO sezioneEstesa, Document document, PdfWriter writer) throws DocumentException {
		PtofComponentePdfHelper help = new PtofComponentePdfHelper();
		PdfPTable header = new PdfPTable(1);
		header.setWidthPercentage(100f);
		 
		Paragraph p = new Paragraph();
		Chunk setLocalDestination = new Chunk(sezioneEstesa.getCodice() +" "+sezioneEstesa.getNome() , PtofFonts.BOLDITALIC[1]).setLocalDestination(sezioneEstesa.getKey());
		 
		p.add(setLocalDestination);
		 
 		PdfPCell val = new PdfPCell(p);
		val.setHorizontalAlignment(Element.ALIGN_LEFT);
		val.setBorder(2);
		val.setPaddingTop(30f);
		val.setPaddingBottom(10f);
		val.setBorderColorTop(Color.white);
		val.setBorderColorRight(Color.white);
		val.setBorderColorLeft(Color.white);
		val.setBorderColorBottom(Color.white);
		  	
		val.setNoWrap(true);
		header.addCell(val);
		help.addSpace(document);
		help.addSpace(document);
		document.add(header);
		help.addSpace(document);
		help.addSpace(document);
		 
		
	}
}
