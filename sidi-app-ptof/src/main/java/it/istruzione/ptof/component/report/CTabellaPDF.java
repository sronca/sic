package it.istruzione.ptof.component.report;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.component.report.comp.PtofComponentePdfHelper;
import it.istruzione.ptof.helper.CommonsUtility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;

public final class CTabellaPDF {

	private String nome;

	private LinkedList<BeanValueDTO> header; 
	
	private LinkedList<BeanValueDTO> master ; 
	
	private LinkedList<BeanValueDTO> detail ;
	
	private LinkedList<PdfPTable> detailTab ;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<BeanValueDTO> getMaster() {
		return master;
	}

	public void setMaster(LinkedList<BeanValueDTO> master) {
		this.master = master;
	}

	public LinkedList<BeanValueDTO> getDetail() {
		if ( detail ==null){
			detail = new LinkedList<>();
		}
		return detail;
	}

	public void setDetail(LinkedList<BeanValueDTO> detail) {
		this.detail = detail;
	}

	public void addHeader(String label, int span) {
		// TODO Auto-generated method stub
		if ( header ==null){
			header = new LinkedList<>();
		}
		
		header.add( new BeanValueDTO( ""+span, label ) ) ; 
	}

	
	public void addMaster(String label, String value) {
		// TODO Auto-generated method stub
		if ( master ==null){
			master = new LinkedList<>();
		}
		master.add( new BeanValueDTO( value , label ) ) ; 
	}

   public void addDetail(PdfPTable tabella ) {
	    if ( detailTab ==null){
	    	detailTab = new LinkedList<>();
		}
		detailTab.add( tabella ) ; 
	}
   
   public void addDetail(String label, String value) {
	    if ( detail ==null){
			detail = new LinkedList<>();
		}
		detail.add( new BeanValueDTO( value , label  ) ) ; 
	}
   
   public void addDetail(String label, Date value) {
	    if ( detail ==null){
			detail = new LinkedList<>();
		}
		detail.add( new BeanValueDTO(CommonsUtility.formatIt(value) , label  ) ) ; 
	}
	
	public  void buildRow (int j, Document document ) throws DocumentException{
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		helper.createTab(j, this, document);
	}

	public  void buildRow (int j, PdfPTable document ) throws DocumentException{
		PtofComponentePdfHelper helper = new PtofComponentePdfHelper();
		helper.createTab(j, this, document);
	}
	
	public LinkedList<BeanValueDTO> getHeader() {
		return header;
	}

	public void setHeader(LinkedList<BeanValueDTO> header) {
		this.header = header;
	}

	public LinkedList<PdfPTable> getDetailTab() {
		return detailTab;
	}

	public void addDetail(String label, BigDecimal value) {
		if( value !=null ) {
			DecimalFormat df = new DecimalFormat("##.00");
			addDetail(label, df.format(value.doubleValue()) );
		}else {
			addDetail(label, "" );	
		}
		
		
	}

	public void addDetail(String label, Integer value) {
		addDetail( label, value==null?"":""+value );
		
	}

		
	
}
