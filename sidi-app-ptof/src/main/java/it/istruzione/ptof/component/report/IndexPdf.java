package it.istruzione.ptof.component.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class IndexPdf {

	/**
	 * EventListner for Content
	 */
	private static class ContentEvent extends PdfPageEventHelper {
		private int page;
		Map<String, Integer> index = new LinkedHashMap<String, Integer>();
		
		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			page++;
		}
		
		@Override
		public void onChapter(PdfWriter writer, Document document,
				float paragraphPosition, Paragraph title) {
			
			index.put(title.getContent(), page);
		}
		
		@Override
		public void onSection(PdfWriter writer, Document document,
				float paragraphPosition, int depth, Paragraph title) {
			onChapter(writer, document, paragraphPosition, title);
		}
	}
	
	/**
	 * EventListner for Index
	 */
	private static class IndexEvent extends PdfPageEventHelper {
		private int page;
		private boolean body;
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// set page number on content
			if(body) {
				page++;
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, 
						new Phrase(page+""), (document.right() + document.left())/2 , document.bottom() - 18, 0);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		// make content pdf (calculate page number)
		Document content = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter contentWriter = PdfWriter.getInstance(content, new ByteArrayOutputStream());
		ContentEvent event = new ContentEvent();
		contentWriter.setPageEvent(event);
		content.open();
		List<Chapter> chapterList = new ArrayList<Chapter>();
		for (int i = 1; i <= 10; i++) {
			Chunk chapTitle = new Chunk("Chapter" + i);
			Chapter chapter = new Chapter(new Paragraph(chapTitle), i);
			chapTitle.setLocalDestination(chapter.getTitle().getContent());
			
			for (int j = 0; j < i; j++) {
				Chunk secTitle = new Chunk("Section" + (j+1));
				Section section = chapter.addSection(new Paragraph(secTitle));
				secTitle.setLocalDestination(section.getTitle().getContent());
				section.setIndentationLeft(10);
				section.add(new Paragraph("sentense sentense\nsentense sentense"));
			}
			content.add(chapter);
			chapterList.add(chapter);
		}
		content.close();

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// add index page.
		String path = "c://reportOut//IndexPdf.pdf";
		FileOutputStream os = new FileOutputStream(path);
		PdfWriter writer = PdfWriter.getInstance(document, os);
		IndexEvent indexEvent = new IndexEvent();
		writer.setPageEvent(indexEvent);
		
		document.open();
		
		Chapter indexChapter = new Chapter("Index", -1);
		indexChapter.setNumberDepth(-1); // not show number style
		PdfPTable table = new PdfPTable(2);
		for(Map.Entry<String, Integer> index: event.index.entrySet()) {
			PdfPCell left = new PdfPCell(new Phrase(index.getKey()));
			left.setBorder(Rectangle.NO_BORDER);
			
			Chunk pageno = new Chunk(index.getValue()+"");
			pageno.setLocalGoto(index.getKey());
			PdfPCell right = new PdfPCell(new Phrase(pageno));
			right.setHorizontalAlignment(Element.ALIGN_RIGHT);
			right.setBorder(Rectangle.NO_BORDER);
			
			table.addCell(left);
			table.addCell(right);
		}
		indexChapter.add(table);
		document.add(indexChapter);
		// add content chapter
		for(Chapter c : chapterList) {
			document.add(c);
			indexEvent.body = true;
		}
		document.close();
		os.close();
	}

}
