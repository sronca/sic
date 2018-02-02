
package it.istruzione.ptof.component.report;

 
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfFileSpecification;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSmartCopy;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.DottedLineSeparator;
import com.lowagie.text.pdf.SimpleBookmark;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDatiFinaliScuolaDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.component.report.comp.PtofComponentePdfHelper;
import it.istruzione.ptof.service.GestionePtofService;

@Component
public class PtofReportImpl implements PtofReport {
   
	private static final int PROFONDITA_CHAP = 0;

	private Logger logger = LoggerFactory.getLogger(PtofReportImpl.class);
	
	private final static int PROFONDITA_CHAPTER = 1;

	@Autowired
	private PtofReportFactory ptofReportFactory;
	
	@Autowired
	private PtofComponentePdfHelper helper;
 
	 
 	 
	@Override
	public void loadDocumentoFormatoPDF(ByteArrayOutputStream out, String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception {
			DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO = new DocumentoAnnoIncorsoDTO();
			documentoAnnoIncorsoDTO.setKey(keyDocumento);
		
		    GestionePtofDTO gestionePtofDTO = new GestionePtofDTO();
			gestionePtofDTO.setIstitutoScolastico(istitutoScolasticoDTO);
			gestionePtofDTO.setDocumentoAnnoIncorsoDTO(documentoAnnoIncorsoDTO);
			
			DocumentoAnnoIncorsoDTO doc = gestionePtofService.loadDocumentoAnnoIncorso(keyDocumento);
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
			String intestazione = "PTOF-"+ doc.getTriennio()+ "-" + istitutoScolasticoDTO.getCodiceMecIstPrin() +"-"+sdf.format(new Date());
			
		    LinkedList<SezioneDTO> loadSezioni = gestionePtofService.loadSezioni(gestionePtofDTO);
		    ComponenteDatiFinaliScuolaDTO datiFinali =null;
		    ComponenteAllegatoDTO allegato =null;
		    for( SezioneDTO sez : loadSezioni ){
		    	if (sez.getTipoSezione()!=null && 0 == sez.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA) ){
		    		SezioneExtDTO sezX = gestionePtofService.loadSezione(sez.getKey(), istitutoScolasticoDTO);
		    		for ( ComponenteDTO com : sezX.getComponenti()){
		    			if( 0== com.getTipoComponente().compareTo(TIPO_COMPONENTE.S_DATI_FINALI_SCUOLA)){
		    				datiFinali = (ComponenteDatiFinaliScuolaDTO) com;
		    			}
		    		}
		    	}
		    	if (sez.getTipoSezione()!=null && 0 == sez.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_31_ALLEGATI) ){
		    		
		    		SezioneExtDTO sezX = gestionePtofService.loadSezione(sez.getKey(), istitutoScolasticoDTO);
		    		for ( ComponenteDTO com : sezX.getComponenti()){
		    			if( 0== com.getTipoComponente().compareTo(TIPO_COMPONENTE.ALLEGATO)){
		    				allegato = (ComponenteAllegatoDTO) com;
		    			}
		    		}
		    		
		    	}	
		    }
		    
		    
		    
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		    LinkedList<PtofIndiceDTO>  indice = addDocumentContent(datiFinali, intestazione, baos, keyDocumento, istitutoScolasticoDTO , loadSezioni  );
			addPagesNumber(baos2, baos);
			buildSTEP3(allegato, out, baos2, indice , istitutoScolasticoDTO , intestazione );
			
			
	}

	
	
	private void buildSTEP3( ComponenteAllegatoDTO allegato  , ByteArrayOutputStream out, ByteArrayOutputStream input, LinkedList<PtofIndiceDTO>  loadSezioni, IstitutoScolasticoDTO ist , String intestazione )
			throws IOException, DocumentException {
		
		
		Document document = new Document(PageSize.A4, 36, 36, 36, 36);
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		PdfImportedPage page;
		PdfReader reader;
  		reader = new PdfReader(input.toByteArray());
		    
			PdfPTable header = new PdfPTable(1);
			{
				 
				header.setHeaderRows(1);
			    header.setLockedWidth(true);
				header.setTotalWidth(523f);
				{
					PdfPTable innerTab = new PdfPTable(2);
					innerTab.setWidthPercentage(100f);
						{
							Paragraph p = new Paragraph();
							//PTOF-“triennio di riferimento”- “Codice Meccanografico dell’Istituzione Scolastica”-Sysdate
							p.add(new Chunk( intestazione  , PtofFonts.BOLDITALIC[2]));
							PdfPCell val = new PdfPCell(p);
							val.setHorizontalAlignment(Element.ALIGN_LEFT);
							val.setBorder(Rectangle.BOTTOM);
							val.setPadding(10f);
							innerTab.addCell(val);
						}
						{
							Paragraph p = new Paragraph();
							p.add(new Chunk("Copertina ed Indici" , PtofFonts.BOLDITALIC[2]));
							PdfPCell val = new PdfPCell(p);
							val.setHorizontalAlignment(Element.ALIGN_RIGHT);
							val.setBorder(Rectangle.BOTTOM);
							val.setPadding(10f);
							innerTab.addCell(val);
						}
						PdfPCell val = new PdfPCell();
						val.setBorder(0);
						val.addElement(innerTab);
						header.addCell(val); 
						header.completeRow(); 
				}	
			  
				 
			}
			
			/********************************/
			
			createTableIst(header, ist);
			
			
			/*******CREA INTESAZIONE ******/
			{
  			Paragraph p = new Paragraph();
  			p.add(new Chunk("Indice" , PtofFonts.BOLDITALIC[0]));
  			 
  			PdfPCell val = new PdfPCell(p);
  			val.setHorizontalAlignment(Element.ALIGN_RIGHT);
  			val.setBorder( Rectangle.BOTTOM );
  			//val.setPaddingTop(document.top()/3);
  			val.setPaddingTop(50f);
  			val.setPaddingBottom(20f);
  			val.setColspan(2);
  			val.setNoWrap(true);
  			header.addCell(val);
			}
			
			
			
		    for ( int i=1 ;i<= reader.getNumberOfPages() ;i++){
		  		 if ( i==2) {
		  			document.newPage();
		  			/********CREA INDICE*************/
		  			PdfPTable table = new PdfPTable(1);
		  			table.setWidthPercentage(100f);
		  			{	for( PtofIndiceDTO sez :  loadSezioni  ){
			  	    	  Chunk leader = new Chunk(new DottedLineSeparator());
			  		      //leader.setAction( PdfAction.gotoLocalPage(sez.getKey() , false ) );
			  		     			  		      
			  		     
						String numeroPagina = ""+sez.getNumeroPagina();
						if (sez.getLivello()==0){
							  Phrase p = new Phrase();
							  p.setFont(PtofFonts.BOLD[1]); 
				  		   
				  		      p.add( sez.getDescrizione() );
				  		      p.add(leader);
				  		      p.add(new Chunk( numeroPagina ));
				  		      PdfPCell cellIndex = new   PdfPCell(p);
				  		      cellIndex.setPaddingTop(20f);
				  		      cellIndex.setPaddingBottom(10f);
				  		      cellIndex.setBorder( Rectangle.BOTTOM );
				  		      table.addCell(cellIndex);
				  		       
			  		      } else {
			  		    	  Phrase p = new Phrase();

				  		      p.setFont(PtofFonts.BOLDITALIC[2]);
				  		  
				  		      p.add( sez.getDescrizione() );
				  		      p.add(leader);
				  		      p.add(new Chunk( numeroPagina ));
				  		      PdfPCell cellIndex =  new   PdfPCell(p);
				  		      cellIndex.setBorder( Rectangle.NO_BORDER );
				  		      cellIndex.setPaddingBottom(10f);
				  		      cellIndex.setPaddingTop(10f);
				  		      cellIndex.setPaddingLeft(10f * sez.getLivello() );
				  		      table.addCell(cellIndex);
			  		          
			  		      }
			  		    }
			  		}
		  			
		  			
		  		    PdfPCell cellNoBord = new PdfPCell();
				    cellNoBord.setBorder(Rectangle.NO_BORDER);
				    cellNoBord.setPaddingBottom(20f);
					cellNoBord.addElement(table); 
		  			header.addCell(cellNoBord);
		  			document.add(header);
		  	  		document.newPage();
		  		 }
			     reader.getPageContent(i);
				 page = writer.getImportedPage(reader, i);
				 document.newPage();
				 cb.addTemplate(page, 0, 0);
				
		}
		  
		writer.setOutlines(  SimpleBookmark.getBookmark(reader) );
		
		
		// ALLEGATI 
		if( allegato !=null ){
		 ZipInputStream zipStream = new ZipInputStream(allegato.getFile().getFile());
		 ZipEntry entry = null;
		 byte[] buffer = new byte[1024];
		 while ((entry = zipStream.getNextEntry()) != null) {
				
			  //  File tempFile = File.createTempFile("tmp_", ".ptof");
				ByteArrayOutputStream fos = new ByteArrayOutputStream();
				//fos.write(entry.getExtra());
				int len;
				while ((len = zipStream.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		        }
				
				PdfFileSpecification fileSpecification = PdfFileSpecification.fileEmbedded(writer, null ,entry.getName(), fos.toByteArray());
				 
			    writer.addFileAttachment(fileSpecification );		
					
		 }
		}
		reader.close();
		input.close();
		document.close();
	}
	
	
	public void createTableIst(PdfPTable  header, IstitutoScolasticoDTO ist) throws DocumentException {
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		helper.addHeaderTab("Dati istituto principale", table, 2);
		int i = 0;
		helper.addRow(table, i++,"Denominazione: " , ist.getDenominazione());
	//	helper.addRow(table, i++,"Tipologie Scuole associate: " , ist.getTipologieScuoleAssociate());
	//	helper.addRow(table, i++,"Dirigente Scolastico: " , ist.getDirigenteScolastico());
		helper.addRow(table, i++,"Indirizzo: " , ist.getIndirizzo());
		helper.addRow(table, i++,"Codice Meccanografico: " , ist.getCodiceMecIstPrin());
		helper.addRow(table, i++,"Telefono : " , ist.getTelefono());
		//helper.addRow(table, i++,"Fax : " , ist.getFax());
		helper.addRow(table, i++,"Email : " , ist.getEmail());
		helper.addRow(table, i++,"PEC : " , ist.getPec());
	//	helper.addRow(table, i++,"Sito WEB : " , ist.getSitoWeb());
	//	helper.addRow(table, i++,"Numero Plessi/scuole : " , ""+ist.getNumeroPlessi());
//		helper.addRow(table, i++,"Di cui: " , ist.getNumeroPlessiPerTipologiaScuola());
		PdfPCell cell1 = new PdfPCell();
		cell1.setPadding(20f);
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(table);
		header.addCell(cell1);

	}
	
	/**
	 * Paginazione 
	 * @param out
	 * @param baos
	 * @throws IOException
	 * @throws DocumentException
	 */
	private void addPagesNumber(ByteArrayOutputStream out, ByteArrayOutputStream baos)
			throws IOException, DocumentException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date currentTime_1 = new Date();
		String dateString = formatter.format(currentTime_1);
		
		PdfImportedPage page; 
		//Document document = new Document(PageSize.A4);	;
		PdfReader reader  = new PdfReader(baos.toByteArray());
		int totalPages = reader.getNumberOfPages();

		PdfStamper stamper = new PdfStamper(reader, out);
		PdfContentByte under = null;
		
		for ( int i=1 ;i<= totalPages ;i++){
			 
		    	under = stamper.getUnderContent(i);
				
			    String pageXofY = String.format("Pagina %d di %d", i-1, totalPages-1);
		      
		        {
		        PdfPTable  header = new PdfPTable(1);
			    header.setLockedWidth(true);
				header.setTotalWidth(523f);
				PdfPCell val = new PdfPCell( new Phrase( i==1?"":pageXofY , PtofFonts.BOLDITALIC[2]));
				val.setHorizontalAlignment(Element.ALIGN_CENTER  );
				val.setBorder( Rectangle.TOP );
				val.setPaddingTop(5f);
			    val.setBorderColorTop(Color.black);
				header.addCell(val); 
				header.completeRow();
				header.writeSelectedRows(0, -1 , 36 , header.getTotalHeight()+36  , under);
		        }
		}
        stamper.setOutlines(  SimpleBookmark.getBookmark(reader));
		reader.close();
		stamper.close();
		//document.close();
		out.close();
	}

	private LinkedList<PtofIndiceDTO> addDocumentContent(ComponenteDatiFinaliScuolaDTO dati, String intestazione, ByteArrayOutputStream baos, String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO, LinkedList<SezioneDTO> loadSezioni   ) throws DocumentException, Exception, IOException {
			
		
			Header event = new Header(intestazione);
			Document document = new Document(PageSize.A4, 36, 36, 20 + event.getTableHeight(), 36);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setPageEvent(event);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			LinkedList<PtofIndiceDTO> out = buildSezioni(dati, intestazione, istitutoScolasticoDTO, document,   writer, cb, loadSezioni, writer.getRootOutline() , 0 );
			document.close();
		    return out;
	}

	
	 
	public PdfPCell getCellIndex(Paragraph p, int verticalAlignment) {
	        PdfPCell cell = new PdfPCell();
	        cell.setVerticalAlignment(verticalAlignment);
	        cell.setUseAscender(true);
	        cell.setUseDescender(true);
	        cell.addElement(p);
	        
	        return cell;
	}

	private LinkedList<PtofIndiceDTO>  buildSezioni(ComponenteDatiFinaliScuolaDTO datiFinali  , String intestazioni ,IstitutoScolasticoDTO istitutoScolasticoDTO, Document document,
			PdfWriter writer, PdfContentByte cb, LinkedList<SezioneDTO> loadSezioni ,  PdfOutline root , int livello ) throws Exception, IOException {
		LinkedList<PtofIndiceDTO> index = new LinkedList<PtofIndiceDTO>();
		for (  SezioneDTO  capitolo :   loadSezioni )
		{   
			
				if (  capitolo.getTipoSezione()!=null && 
						( 0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_31_ALLEGATI) ||
						  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA)) ){
	
					logger.debug("NO LOAD --- LOAD SEZIONE---"+capitolo.getKey() +"--"+ capitolo.getTipoSezione().name());
			
					return index;
				}
			
				if( fabisognoInDocument(capitolo) ){
				  return index;	
				}
								
				
			
				if (( capitolo.getTipoSezione() ==null || capitolo.getStatoSezione().compareTo(TIPO_STATO_SEZIONE.COMPILATA)==0) || (capitolo.getTipoSezione() !=null &&  0!=capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_01_INDICE )) ){
					index.add( new PtofIndiceDTO(capitolo.getKey(),capitolo.getCodice() +" " +capitolo.getNome(), writer.getCurrentPageNumber() , livello ));	
				}
			
				//if( capitolo.getStatoSezione().compareTo(TIPO_STATO_SEZIONE.COMPILATA)==0 )
				{
					
					PdfOutline figlio= buildPdfSezione(datiFinali , intestazioni ,istitutoScolasticoDTO, document,  writer, cb, capitolo , root , livello );
					if ( capitolo.getSottoSezione()!=null && capitolo.getSottoSezione().size()>0 ) {
						index.addAll( buildSezioni(datiFinali , intestazioni, istitutoScolasticoDTO, document,  writer, cb, capitolo.getSottoSezione() ,  figlio , livello+1 ));
					}
			
				}

		}
		return index;
	}



	private boolean fabisognoInDocument(SezioneDTO capitolo) {
		return ( capitolo.getStatoDocumento().compareTo(TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA)==0
				)  
			&&	(  capitolo.getTipoSezione()!=null && 
				 ( 0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE)  ||
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE) || 
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO) ||
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO) ||
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI) ||
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO) ||
				  0==capitolo.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE)
			     ));
	}

	private PdfOutline buildPdfSezione(ComponenteDatiFinaliScuolaDTO datifinali , String intestazione, IstitutoScolasticoDTO istitutoScolasticoDTO, Document document, 
			PdfWriter writer, PdfContentByte cb, SezioneDTO capitolo , PdfOutline root , int livello ) throws Exception, IOException {
		PdfImportedPage page;
		PdfReader reader;
		PdfOutline outline =null;PtofComponentePdfHelper help = new PtofComponentePdfHelper();
		SezioneExtDTO loadSezione = gestionePtofService.loadSezione( capitolo.getKey() , istitutoScolasticoDTO );
		if (  loadSezione.getTipoSezione()!=null && 
				( 0==loadSezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_31_ALLEGATI) ||
				  0==loadSezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA)) ){

			logger.debug("NO LOAD --- LOAD SEZIONE---"+capitolo.getKey() +"--"+ loadSezione.getTipoSezione().name());
	
			return null;
		}
        
		/* DA CAPIRE BENE la sezione fabbisogno non sara' presente nel pdf fintanto che il fabbisogno non e' validato */
		
		if( fabisognoInDocument(capitolo)){
		  return null;	
		}
		
		
		if ( livello ==0 && loadSezione.getTipoSezione()!=null && 0!=loadSezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_01_INDICE)){
		  buildIntestazioneSezione (loadSezione , document ,writer ) ;
		} else if ( loadSezione.getTipoSezione()==null  ){
			
			buildIntestazioneSezione (loadSezione , document ,writer ) ;
			outline = addBookMark(loadSezione,root);

		}  
			
		if ( loadSezione.getTipoSezione()!=null && loadSezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_13_FABBISOGNO)!=0 
				&& loadSezione!=null 
				&& loadSezione.getComponenti()!=null 
				&& loadSezione.getStatoSezione().compareTo(TIPO_STATO_SEZIONE.COMPILATA)==0 ){
	
			logger.debug("--- LOAD SEZIONE---"+capitolo.getKey() +"--"+ loadSezione.getTipoSezione().name());
			
			ByteArrayOutputStream outToAppend= ptofReportFactory.getDocument(datifinali, loadSezione ,livello );
		 	reader = new PdfReader(outToAppend.toByteArray());
		  	for ( int i=1 ;i<= reader.getNumberOfPages() ;i++){
			     reader.getPageContent(i);
			     
			     help.addSpace(document); 
			     
				 page = writer.getImportedPage(reader, i);
				 document.newPage();
				 cb.addTemplate(page, 0, 0);
				
			}
			reader.close();
			outToAppend.close();
			outline = addBookMark(loadSezione,root);
			
		}
		
		return outline;
	}

	private void buildIntestazioneSezione(SezioneExtDTO loadSezione, Document document, PdfWriter writer) throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
		
		PdfPTable header = new PdfPTable(1);
		header.setWidthPercentage(100f);
		
		
		
		{
			Paragraph p = new Paragraph();
			p.add(new Chunk(loadSezione.getCodice() +" " +loadSezione.getNome() , PtofFonts.BOLDITALIC[0]).setLocalDestination(loadSezione.getKey()));
			PdfPCell val = new PdfPCell(p);
			val.setHorizontalAlignment(Element.ALIGN_RIGHT);
			val.setBorder( Rectangle.BOTTOM );
			val.setPaddingTop(document.top()/3);
			val.setPadding(10f);
			 
			val.setNoWrap(true);
			header.addCell(val);
		}
		
		{
			
			String valSez = ( 0==loadSezione.getStatoSezione().compareTo(TIPO_STATO_SEZIONE.BOZZA)?"N/A":" ");
			
			
			if( loadSezione.getStatoDocumento().compareTo(TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA)==0 
				&&	(  loadSezione.getTipoSezione()!=null && 
					  0==loadSezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_13_FABBISOGNO))
				    ){
				valSez="N/A";
			}
				
			
			Paragraph p = new Paragraph();
			p.add(new Chunk(valSez , PtofFonts.BOLD[0]));
			PdfPCell val = new PdfPCell(p);
			val.setHorizontalAlignment(Element.ALIGN_LEFT);
			//val.setPaddingTop(document.top()/3);
			val.setBorder(0);
			val.setPadding(10f);
			header.addCell(val);
		}
		
		
		
		
		{
			Paragraph p = new Paragraph();
			p.add(new Chunk(""+StringUtils.trimToEmpty(loadSezione.getTestoFissoIntestazioneSezione()) , PtofFonts.ITALIC[1]).setLocalDestination(loadSezione.getKey()));
			PdfPCell val = new PdfPCell(p);
			val.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			val.setBorder(0);
			val.setPadding(10f);
			header.addCell(val);
		}
		
		document.newPage();
		createHeader( true ,loadSezione,writer,true);
		document.add(header);
		createHeader( false ,loadSezione,writer,false);
		document.newPage();
		
	}

	private PdfOutline  addBookMark(SezioneExtDTO loadSezione, PdfOutline root ) {
		//return new PdfOutline(root,	PdfAction.gotoLocalPage(loadSezione.getKey(), false), loadSezione.getNome() );
		//return new PdfOutline(root,   new PdfDestination(PdfDestination.FIT) , loadSezione.getNome() , true);
		return null;
	}

	private void createHeader(boolean link ,SezioneExtDTO loadSezione ,PdfWriter writer, boolean hide ) {
		((Header)writer.getPageEvent()).setHeader(!link?null:loadSezione.getKey(),loadSezione.getCodice() + loadSezione.getNome(), hide , loadSezione);
	}
	
	public class Header extends PdfPageEventHelper {

		public Header(String intest) {
			intestazione = intest;
			createTop();
			tableHeight = header.getTotalHeight();
		
		}

		protected PdfPTable header = new PdfPTable(1);
		protected String val = "",key, intestazione;
		protected boolean hide;
		protected float tableHeight;SezioneExtDTO sezione;

		public void setHeader(String key , String val2 , boolean hide, SezioneExtDTO sezione) {
			this.val= val2;	
			this.key = key;
			this.hide =hide;
			this.sezione = sezione;
		}

		private void createTop() {
			header = new PdfPTable(1);
			header.setTotalWidth(650);
			header.setLockedWidth(true);
		 	
			PdfPTable innerTab = new PdfPTable(2);
			innerTab.setTotalWidth(650);
			
			
			{	
				Paragraph p = new Paragraph();
			    p.add(new Chunk(hide?"":intestazione, PtofFonts.BOLDITALIC[2]));
				PdfPCell val = new PdfPCell(p);
				val.setHorizontalAlignment(Element.ALIGN_LEFT);
				val.setBorder(hide?0:Rectangle.BOTTOM);
				val.setPadding(10f);
	  		    innerTab.addCell(val);
							
			}
			
			{
				Paragraph p = new Paragraph();
				
				if (key!=null){
					p.add(new Chunk(hide?"":this.val, PtofFonts.BOLDITALIC[2]).setLocalDestination(key));
				} else {
					p.add(new Chunk(hide?"":this.val, PtofFonts.BOLDITALIC[2]));
				}
				
				PdfPCell val = new PdfPCell(p);
				val.setHorizontalAlignment(Element.ALIGN_RIGHT);
				val.setBorder(hide?0:Rectangle.BOTTOM);
				val.setPadding(10f);
	  		    innerTab.addCell(val);
			}
			
			
			PdfPCell val = new PdfPCell();
			val.setBorder(0);
			val.addElement(innerTab);
			//if ( sezione!=null && sezione.getTipoSezione().compareTo(TIPO_SEZIONE.SEZIONE_01_INDICE)  ){
			   header.addCell(val);
		    //}	
		}

		
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			 createTop();	
			 header.writeSelectedRows(0, -1, -36 ,
					document.top() + ((document.topMargin() + tableHeight) / 2)+11 , writer.getDirectContent());
			 
		}

		public float getTableHeight() {
			return tableHeight;
		}
	}
	 

	 

	@Autowired
	GestionePtofService gestionePtofService;
}
