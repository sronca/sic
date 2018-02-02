package it.istruzione.ptof.component.report.comp;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Jpeg;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.component.report.CTabellaPDF;
import it.istruzione.ptof.component.report.PtofFonts;

@Component
public class PtofComponentePdfHelper {
	
	public void createTab (int j , CTabellaPDF tabella, Document document) throws DocumentException{
		createHeadTab(j,tabella, document);
		creaBodyTab(tabella,document);
	}

	
	public void createTab (int j , CTabellaPDF  tabella, PdfPTable tabelCornice) throws DocumentException{
		tabelCornice.addCell(createHeadTabGeneric(j, tabella));
		tabelCornice.addCell(creaBodyTabGeneric( tabella));
	}
    
	public PdfPTable creaCornice(PdfPTable tableInner) {
		PdfPTable cornice = new PdfPTable(1);
		cornice.setWidthPercentage(100f);
		PdfPCell cell =	  new PdfPCell(tableInner);
		cell.setBorder(1);
		cornice.addCell(cell);
		return cornice;
	}


	private void createHeadTab(int j , CTabellaPDF comp, Document document) throws DocumentException {
		
		if (comp.getMaster()==null && comp.getHeader()==null ){
			return;
		}
		
		PdfPTable table = createHeadTabGeneric(j, comp);
		
		document.add(table);
		addSpace(document);
	}


	private PdfPTable createHeadTabGeneric(int j, CTabellaPDF comp) {
		int numeroColSpan =0;
	 	
		if  ( null!= comp.getHeader() ){			
			for (BeanValueDTO header : comp.getHeader() ){
				numeroColSpan = numeroColSpan+ Integer.parseInt(header.getValue());
		    }
		}
		
		int numeroColonne = comp.getMaster()!=null? comp.getMaster().size() : numeroColSpan ;
		
		 
		
		PdfPTable table = new PdfPTable(numeroColonne);
		table.setWidthPercentage(100);
		if (j==1 && comp.getNome()!=null ){
		  addHeaderTab(comp.getNome() , table, numeroColonne);
		}

		 if  ( null!= comp.getHeader() ){
			 for (BeanValueDTO header : comp.getHeader() ){
					addCellHeader(header.getLabel(), table, Integer.parseInt(header.getValue()));
			 }
		 }

		
		if(comp.getMaster()!=null){
			
			for (BeanValueDTO header : comp.getMaster()){
				addCellHeader(header.getLabel(), table, 1);
			}
	
			for (BeanValueDTO header : comp.getMaster()){
				addCell(header.getValue()   , table, 0);
			}
		
		}
		return table;
	}

	private void creaBodyTab( CTabellaPDF tabella, Document document )  throws DocumentException {
			
		PdfPTable table = creaBodyTabGeneric(tabella);
        document.add(table);
		addSpace(document);
	}


	private PdfPTable creaBodyTabGeneric(CTabellaPDF tabella) {
		
		int numeroColonne = tabella.getDetail().size()>0 
				? 3 : 1 , i=0;
		
		PdfPTable table = new PdfPTable(numeroColonne);
		table.setWidthPercentage(100);
		if (tabella.getDetail()!=null) {
			for (BeanValueDTO dettaglio : tabella.getDetail()){
				addRowSpan(table, i++, dettaglio.getLabel() ,dettaglio.getValue());
			}
		} 
		
		if ( null!= tabella.getDetailTab()){
			
			for (PdfPTable dettaglio : tabella.getDetailTab() ){
				PdfPCell cell =	  new PdfPCell(dettaglio);
				cell.setBorder(0);
				table.addCell(cell);
			}
			
		}
		return table;
	}

    public void addHtml(Document document, String html ) throws Exception {
		    	    HTMLWorker htmlWorker = new HTMLWorker(document);
				    htmlWorker.parse(new StringReader(html));
	}

	
	public void addSpace(Document document) throws DocumentException{
		Paragraph space = new Paragraph(' ');
		space.setSpacingAfter(10f);
		document.add(space);
	}  
	
	public void addSpace(LinkedList<Element> document ) throws DocumentException{
		Paragraph space = new Paragraph(' ');
		space.setSpacingAfter(10f);
		document.add(space);
	}  
	
	
	
	/**
	 * su legge il pdf in input e si scrive sul baos
	 * @param baos :
	 * @param input : input
	 * @throws IOException
	 */
	public void mergePDF(ByteArrayOutputStream baos, InputStream input)  {
		Document document = new Document(PageSize.A4);
		PdfReader reader = null; 
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfImportedPage page ; 
			// Load existing PDF
			reader = new PdfReader(input);
			for ( int i=1 ;i<= reader.getNumberOfPages() ;i++){
				 reader.getPageContent(i);
				 page = writer.getImportedPage(reader, i);
				// Copy first page of existing PDF into output PDF
				 document.newPage();
				 cb.addTemplate(page, 0, 0);
			}
			 
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			    document.close();
				reader.close();
		}
	}
	
	public void mergePDF(Document document , PdfWriter writer, InputStream input)  {
		PdfReader reader = null; 
		
		try {
			PdfContentByte cb = writer.getDirectContent();
			PdfImportedPage page ; 
			// Load existing PDF
			reader = new PdfReader(input);
			for ( int i=1 ;i<= reader.getNumberOfPages() ;i++){
				 reader.getPageContent(i);
				 page = writer.getImportedPage(reader, i);
				// Copy first page of existing PDF into output PDF
		 		 cb.addTemplate(page, 0, 0);
		 		 document.newPage();
					
			}
			 
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
	//		    document.close();
				reader.close();
		}
	}
	
	
	public void mergeImg(ByteArrayOutputStream baos, InputStream input)  {
		Document document = new Document(PageSize.A4);
 		try {
			  PdfWriter.getInstance(document,   baos  );
			  document.open();
		      document.newPage();
			  Image img = Image.getInstance( IOUtils.toByteArray(input));
			  int indentation = 0;
		      float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
               - document.rightMargin() - indentation) / img.getWidth()) * 100;
              img.scalePercent(scaler);
			  document.add(img);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			  document.close();
				 
		}
 	}
	
	
	
	public Chapter createChapter(SezioneExtDTO sezioneEstesa ,int profondita ) {
		Chapter chapter = new Chapter(profondita);
		chapter.setTitle(new Paragraph(sezioneEstesa.getNome(), PtofFonts.BOLDITALIC[profondita]));
		chapter.setBookmarkOpen(false);
		chapter.setBookmarkTitle(sezioneEstesa.getNome());
		return chapter;
	}
	
	public Section createParagraph(SezioneExtDTO sezioneEstesa ,Section chapter , int profondita ) {
		Paragraph  paragraph =  new Paragraph(sezioneEstesa.getNome(), profondita > 2 ? PtofFonts.BOLDITALIC[2] : PtofFonts.BOLDITALIC[profondita]);
     	Section section = chapter.addSection(paragraph , profondita);
		section.setBookmarkOpen(false);
		section.setBookmarkTitle(sezioneEstesa.getNome());
	    return section;
	}
	
	public void addCell(String val, PdfPTable table, int i) {
		  Paragraph paragraph2 = new Paragraph(val, PtofFonts.NORMAL[2]);
		  PdfPCell cell =	  new PdfPCell(paragraph2);
		  cell.setBackgroundColor(i%2==0 ? Color.white : Color.getHSBColor(60f, 0.02f, 0.96f )   );
		  cell.setHorizontalAlignment(Element.ALIGN_CENTER );
		  
		  cell.setPadding(10f);
		  table.addCell(cell);
	}

	public void addCell(String val, PdfPTable table, int i, int span) {
		  Paragraph paragraph2 = new Paragraph(val, PtofFonts.NORMAL[2]);
		  PdfPCell cell =	  new PdfPCell(paragraph2);
		  cell.setBackgroundColor(i%2==0 ? Color.white : Color.getHSBColor(60f, 0.02f, 0.96f )   );
		  cell.setHorizontalAlignment(Element.ALIGN_CENTER );
		  cell.setPadding(10f);
		  cell.setColspan(span);
		  table.addCell(cell);
	}
	
	 
	
	public void addHeaderTab(String val, PdfPTable table, int span) {
		  Paragraph paragraph2 = new Paragraph( val ,PtofFonts.BOLD[1]);
		  
		  PdfPCell cell =	  new PdfPCell(paragraph2);
		  cell.setPaddingLeft(0);
		  cell.setPaddingBottom(10f);
		 // cell.setNoWrap(true);
		  cell.setBorderWidth(0); 
		  cell.setBackgroundColor(Color.white);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  cell.setColspan(span);
		  table.addCell(cell);
	}
	public void addCellHeader(String val, PdfPTable table, int span) {
		  Paragraph paragraph2 = new Paragraph( val ,PtofFonts.BOLD[2]);
		  paragraph2.getFont().setColor(Color.white);
		  PdfPCell cell =	  new PdfPCell(paragraph2);
		  cell.setPadding(9f);
		  //cell.setNoWrap(true);
		  if (val==null || val.isEmpty()){  
			  cell.setBackgroundColor(Color.WHITE  );
			  cell.setBorder(0);
		  } else {
			  cell.setBackgroundColor(Color.getHSBColor(180f, 0.21f, 0.45f )  );
		  }
		  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  cell.setColspan(span);
		  table.addCell(cell);
	}

	public void addCellHeader(String val, PdfPTable table, int spancol, int spanrow) {
		  Paragraph paragraph2 = new Paragraph( val ,PtofFonts.BOLD[2]);
		  paragraph2.getFont().setColor(Color.white);
		  PdfPCell cell =	  new PdfPCell(paragraph2);
		  cell.setPadding(9f);
		  //cell.setNoWrap(true);
		  cell.setBackgroundColor(Color.getHSBColor(180f, 0.21f, 0.45f )  );
		  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		  cell.setColspan(spancol);
		  cell.setRowspan(spanrow);
		  
		  table.addCell(cell);
	}

	public void addRow(PdfPTable table, int i, String...cols) {
		int j = 0;
		for ( String col : cols){
			  j++;
			  Paragraph paragraph2 = new Paragraph(col,j==1? PtofFonts.BOLDITALIC[2] : PtofFonts.NORMAL[2]);
			  PdfPCell cell =	  new PdfPCell(paragraph2);
			  cell.setBackgroundColor(i%2==0 ? Color.white : Color.getHSBColor(60f, 0.02f, 0.96f )   );
			  cell.setHorizontalAlignment(Element.ALIGN_LEFT );
			  cell.setPadding(10f);
			  table.addCell(cell);
				
		}
	}

	public void addRowSpan(PdfPTable table, int i, String...cols) {
		// TODO Auto-generated method stub
		int j=0;
		for ( String col : cols){
			  j++;
			  Paragraph paragraph2 = new Paragraph(col,j==1? PtofFonts.BOLDITALIC[2] : PtofFonts.NORMAL[2]);
			  PdfPCell cell =	  new PdfPCell(paragraph2);
			  cell.setColspan(j==1?1:2);
			  cell.setBackgroundColor(i%2==0 ? Color.white : Color.getHSBColor(60f, 0.02f, 0.96f )   );
			  cell.setHorizontalAlignment(Element.ALIGN_LEFT );
			  cell.setPadding(10f);
			  table.addCell(cell);
				
		}
	}


	public void addCell(Integer val, PdfPTable table, int i) {
		 addCell(""+val, table, i);
	}


	public void addCell(BigDecimal bigDecimal, PdfPTable table, int i) {
		
		if( bigDecimal ==null ) {
			addCell("", table, i);
		} else {
			NumberFormat df = NumberFormat.getInstance(Locale.ITALY);
			addCell(df.format(bigDecimal.doubleValue() ), table, i);
		}
	}


	 

	
}
