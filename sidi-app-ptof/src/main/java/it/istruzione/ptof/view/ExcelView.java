package it.istruzione.ptof.view;

import it.istruzione.ptof.beans.BeanCellaExcel;
import it.istruzione.ptof.beans.BeanDocumentoExcel;
import it.istruzione.ptof.constant.AppConstant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;
 
/**
 * 
 * @author Peruvianit
 *
 */
public class ExcelView extends AbstractExcelView  {
	// Styles 

	HSSFFont headerFontTitle = null;
	HSSFFont columnFontTitle = null;
	HSSFFont columnGoldFontTitle = null;
	HSSFCellStyle headerInfoStyle = null;
	
	HSSFFont boldFontWhite = null;
	HSSFCellStyle headerStyle = null;
	HSSFCellStyle columnTitleStyle = null;
	HSSFCellStyle columnTitleGoldStyle = null;
	HSSFCellStyle columnTitleCornFlowerStyle = null;
	HSSFCellStyle cellRowStyle;
	HSSFCellStyle cellStyle;
	HSSFCellStyle cellStyleImporto;
	HSSFCellStyle cellStyleImportoGold;
	HSSFCellStyle cellStyleImportoCornFlower;
	
	HSSFCellStyle cellStyleFooter;
	HSSFCellStyle cellStyleFooterGold;
	HSSFCellStyle cellStyleFooterCornFlower;
			
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  HSSFWorkbook workbook, 
									  HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		
		String filename = (String) model.get("filename");
		
		setContentType("application/vnd.xls");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ filename + ".xls\"");
		
		BeanDocumentoExcel beanDocumentoExcel = (BeanDocumentoExcel)model.get("beanDocumentoExcel");

		List<BeanCellaExcel> listColumnsTittle = beanDocumentoExcel.getListTitoloColonne();
		List<BeanCellaExcel> listColumnsTittleGroup = beanDocumentoExcel.getListTitoloColonneMerge();
		
		List<List<Object>> listRows = beanDocumentoExcel.getListRows();
		List<List<BeanCellaExcel>> listFooters = beanDocumentoExcel.getListFooters();
		
		headerFontTitle = workbook.createFont();
		headerFontTitle.setFontHeightInPoints((short) 16);
		headerFontTitle.setFontName("Arial");
		headerFontTitle.setColor(IndexedColors.WHITE.getIndex());
		headerFontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFontTitle.setItalic(false);
		
		columnFontTitle = workbook.createFont();
		columnFontTitle.setFontHeightInPoints((short) 10);
		columnFontTitle.setFontName("Arial");
		columnFontTitle.setColor(IndexedColors.WHITE.getIndex());
		columnFontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		columnFontTitle.setItalic(false);
		
		columnGoldFontTitle = workbook.createFont();
		columnGoldFontTitle.setFontHeightInPoints((short) 10);
		columnGoldFontTitle.setFontName("Arial");
		columnGoldFontTitle.setColor(IndexedColors.BLACK.getIndex());
		columnGoldFontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		columnGoldFontTitle.setItalic(false);
		
		boldFontWhite = workbook.createFont();
		boldFontWhite.setFontHeightInPoints((short) 10);
		boldFontWhite.setFontName("Arial");
		boldFontWhite.setColor(IndexedColors.WHITE.getIndex());
		boldFontWhite.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		boldFontWhite.setItalic(false);
		
		headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFont(headerFontTitle);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		headerInfoStyle = workbook.createCellStyle();
		headerInfoStyle.setBorderRight((short) 1);
		headerInfoStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		columnTitleStyle = workbook.createCellStyle();
		columnTitleStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		columnTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		columnTitleStyle.setFont(columnFontTitle);
		columnTitleStyle.setBorderBottom((short) 1);
		columnTitleStyle.setBorderTop((short) 1);
		columnTitleStyle.setBorderRight((short) 1);
		columnTitleStyle.setBorderLeft((short) 1);
		columnTitleStyle.setDataFormat((short) 4);
		columnTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnTitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		columnTitleGoldStyle = workbook.createCellStyle();
		columnTitleGoldStyle.setFillForegroundColor(HSSFColor.GOLD.index);
		columnTitleGoldStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		columnTitleGoldStyle.setFont(columnGoldFontTitle);
		columnTitleGoldStyle.setBorderBottom((short) 1);
		columnTitleGoldStyle.setBorderTop((short) 1);
		columnTitleGoldStyle.setBorderRight((short) 1);
		columnTitleGoldStyle.setBorderLeft((short) 1);
		columnTitleGoldStyle.setDataFormat((short) 4);
		columnTitleGoldStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnTitleGoldStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnTitleGoldStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		columnTitleCornFlowerStyle = workbook.createCellStyle();
		columnTitleCornFlowerStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		columnTitleCornFlowerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		columnTitleCornFlowerStyle.setFont(columnGoldFontTitle);
		columnTitleCornFlowerStyle.setBorderBottom((short) 1);
		columnTitleCornFlowerStyle.setBorderTop((short) 1);
		columnTitleCornFlowerStyle.setBorderRight((short) 1);
		columnTitleCornFlowerStyle.setBorderLeft((short) 1);
		columnTitleCornFlowerStyle.setDataFormat((short) 4);
		columnTitleCornFlowerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnTitleCornFlowerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderLeft((short) 1);
		
		cellRowStyle = workbook.createCellStyle();
		cellRowStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		cellRowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellRowStyle.setBorderBottom((short) 1);
		cellRowStyle.setBorderTop((short) 1);
		cellRowStyle.setBorderRight((short) 1);
		cellRowStyle.setBorderLeft((short) 1);
		
		HSSFDataFormat format = workbook.createDataFormat();
		
		cellStyleImporto = workbook.createCellStyle();
		cellStyleImporto.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		cellStyleImporto.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleImporto.setBorderBottom((short) 1);
		cellStyleImporto.setBorderTop((short) 1);
		cellStyleImporto.setBorderRight((short) 1);
		cellStyleImporto.setBorderLeft((short) 1);
		cellStyleImporto.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleImporto.setDataFormat(format.getFormat("##,##0.00"));
		
		cellStyleImportoGold = workbook.createCellStyle();
		cellStyleImportoGold.setFillForegroundColor(HSSFColor.GOLD.index);
		cellStyleImportoGold.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleImportoGold.setBorderBottom((short) 1);
		cellStyleImportoGold.setBorderTop((short) 1);
		cellStyleImportoGold.setBorderRight((short) 1);
		cellStyleImportoGold.setBorderLeft((short) 1);
		cellStyleImportoGold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleImportoGold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleImportoGold.setDataFormat(format.getFormat("##,##0.00"));
		
		cellStyleImportoCornFlower = workbook.createCellStyle();
		cellStyleImportoCornFlower.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		cellStyleImportoCornFlower.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleImportoCornFlower.setBorderBottom((short) 1);
		cellStyleImportoCornFlower.setBorderTop((short) 1);
		cellStyleImportoCornFlower.setBorderRight((short) 1);
		cellStyleImportoCornFlower.setBorderLeft((short) 1);
		cellStyleImportoCornFlower.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleImportoCornFlower.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleImportoCornFlower.setDataFormat(format.getFormat("##,##0.00"));
		
		cellStyleFooter = workbook.createCellStyle();
		cellStyleFooter.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		cellStyleFooter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleFooter.setBorderBottom((short) 1);
		cellStyleFooter.setBorderTop((short) 1);
		cellStyleFooter.setBorderRight((short) 1);
		cellStyleFooter.setBorderLeft((short) 1);
		cellStyleFooter.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		cellStyleFooterGold = workbook.createCellStyle();
		cellStyleFooterGold.setFillForegroundColor(HSSFColor.GOLD.index);
		cellStyleFooterGold.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleFooterGold.setBorderBottom((short) 1);
		cellStyleFooterGold.setBorderTop((short) 1);
		cellStyleFooterGold.setBorderRight((short) 1);
		cellStyleFooterGold.setBorderLeft((short) 1);
		cellStyleFooterGold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleFooterGold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		cellStyleFooterCornFlower = workbook.createCellStyle();
		cellStyleFooterCornFlower.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		cellStyleFooterCornFlower.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyleFooterCornFlower.setBorderBottom((short) 1);
		cellStyleFooterCornFlower.setBorderTop((short) 1);
		cellStyleFooterCornFlower.setBorderRight((short) 1);
		cellStyleFooterCornFlower.setBorderLeft((short) 1);
		cellStyleFooterCornFlower.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleFooterCornFlower.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Report");
		int rowNum = 0;
		
		Integer totalColumnsReport = beanDocumentoExcel.getListTitoloColonne().size();
		
		/*
		 * Impostare largezza colonna
		*/
		
		Map<Integer,Integer> customColunns = beanDocumentoExcel.getCustomColumns();
		for (Map.Entry<Integer, Integer> entry : customColunns.entrySet()) {
			sheet.setColumnWidth(entry.getKey(), entry.getValue());
		}
		
		/*
		 *  Titolo della Pagina Excel
		 * 
		*/
		
		HSSFRow headerTitle = sheet.createRow(rowNum++);
		HSSFCell headerCellTitlePage = headerTitle.createCell(0);
		headerCellTitlePage.setCellStyle(headerStyle);
		headerCellTitlePage.setCellValue(beanDocumentoExcel.getTitlePagina());
		headerTitle.setHeight((short)700);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + (char) (64 + totalColumnsReport ) + "1"));
		
		/*
		 * Header Pagina Excel
		 */
		
		Map<String, String> headers = beanDocumentoExcel.getHeaderPage();
		
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			HSSFRow headersRows = sheet.createRow(rowNum++);
			HSSFCell headerCellHeaderKey = headersRows.createCell(0);
			headerCellHeaderKey.setCellStyle(headerInfoStyle);
			headerCellHeaderKey.setCellValue(entry.getKey());
			
			HSSFCell headerCellHeaderValue = headersRows.createCell(1);
			headerCellHeaderValue.setCellValue(entry.getValue());
		}
		
		rowNum++; // Riga vuota
		
		/*
		 * Merge Titolo Colonne
		*/

		HSSFRow headerTitleMergeTable = sheet.createRow(rowNum++);
		int rownNumGroup = rowNum;
		headerTitleMergeTable.setHeight((short)700);
		
		int colNumber = 0;
		for (BeanCellaExcel col : listColumnsTittleGroup) {
			
			HSSFCell headerCellGroup = headerTitleMergeTable.createCell(col.getStartMergeColumns()-1);
			
			this.setStyleCell(headerCellGroup, col.getStyle());
			for (int i=col.getStartMergeColumns(); i<col.getEndMergeColumns();i++){
				HSSFCell cellMergeStyle = headerTitleMergeTable.createCell(i);	
				this.setStyleCell(cellMergeStyle, col.getStyle());
			}
			
			headerCellGroup.setCellValue(col.getContenuto().toString());
			sheet.addMergedRegion(CellRangeAddress.valueOf((char) (64 + col.getStartMergeColumns() ) + String.valueOf(rownNumGroup) + ":" + (char) (64 + col.getEndMergeColumns() ) + String.valueOf(rownNumGroup)));
		}
		
		/*
		 * Titoli Colonne 
		 */
		HSSFRow headerTitleTable = sheet.createRow(rowNum++);
		headerTitleTable.setHeight((short)700);
		colNumber = 0;
		for (BeanCellaExcel col : listColumnsTittle) {
			HSSFCell headerCell = headerTitleTable.createCell(colNumber++);
			
			if (col.getStyle() != null && col.getStyle().equals(AppConstant.STILO_GOLD)){
				headerCell.setCellStyle(columnTitleGoldStyle);
			}else{
				headerCell.setCellStyle(columnTitleStyle);
			}
			headerCell.setCellValue(col.getContenuto().toString());
		}
		
		/*
		 * Rows 
		 */
		
		for (List<Object> riga : listRows) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			int colNumberRow = 0;
			for (Object val : riga) {
				//row.createCell(colNumberRow++).setCellValue(cell);
				
				HSSFCell cell = row.createCell(colNumberRow++);
				if(val != null){	
					if(val instanceof Double || val instanceof BigDecimal){
						cell.setCellStyle(cellStyleImporto);
						if(val instanceof Double){
							cell.setCellValue((Double)val);
						} else {
							cell.setCellValue(((BigDecimal)val).doubleValue());
						}
					}else if (val instanceof Integer) {
						cell.setCellStyle(cellRowStyle);
						cell.setCellValue((Integer)val);
					}else if (val instanceof Long) {
						cell.setCellStyle(cellRowStyle);
						cell.setCellValue(((Long) val).intValue());
					}else {
						cell.setCellStyle(cellRowStyle);
						cell.setCellValue(String.valueOf(val));
					}
				}else {
//					logger.error("insert --method.getName() "+method.getReturnType().getName()+" NULL");
					/** inserisco cella vuota! */
					cell.setCellStyle(cellStyle);
					cell.setCellValue("");
				}
			}
		}
		
		/*
		 * Footer
		 */
		
		for (List<BeanCellaExcel> riga : listFooters) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.setHeight((short)700);
			
			int rownNumFooter = rowNum;
			int colNumberRow = 0;
			for (BeanCellaExcel beanCellaExcel : riga) {
				Object val = beanCellaExcel.getContenuto();
				
				HSSFCell cell = null;
				
				if (beanCellaExcel.getStartMergeColumns() != null && beanCellaExcel.getEndMergeColumns() != null){
					colNumberRow = beanCellaExcel.getStartMergeColumns()-1;
					sheet.addMergedRegion(CellRangeAddress.valueOf((char) (64 + beanCellaExcel.getStartMergeColumns() ) + String.valueOf(rownNumFooter) + ":" + (char) (64 + beanCellaExcel.getEndMergeColumns() ) + String.valueOf(rownNumFooter)));
					cell = row.createCell(colNumberRow);
					this.setStyleCell(cell, beanCellaExcel.getStyle());
					for (int i=beanCellaExcel.getStartMergeColumns(); i<beanCellaExcel.getEndMergeColumns();i++){
						HSSFCell cellMergeStyle = row.createCell(i);	
						this.setStyleCell(cellMergeStyle, beanCellaExcel.getStyle());
					}
				}else{
					cell = row.createCell(colNumberRow++); 
					this.setStyleCell(cell, beanCellaExcel.getStyle());
				}
				
				if(val != null){					
					//inserisco la cella solo se non è null
					if(val instanceof Double || val instanceof BigDecimal){
						if (beanCellaExcel.getStyle() != null && beanCellaExcel.getStyle().equals(AppConstant.STILO_GOLD)){
							cell.setCellStyle(cellStyleImportoGold);
						}else if (beanCellaExcel.getStyle() != null && beanCellaExcel.getStyle().equals(AppConstant.STILO_CORNFLOWER_BLUE)){
							cell.setCellStyle(cellStyleImportoCornFlower);
						}else{
							cell.setCellStyle(cellStyleImporto);
						}
						
						if(val instanceof Double){
							cell.setCellValue((Double)val);
						}else {
							cell.setCellValue(((BigDecimal)val).doubleValue());
						}
					}else{
						if (beanCellaExcel.getStyle() != null && beanCellaExcel.getStyle().equals(AppConstant.STILO_GOLD)){
							cell.setCellStyle(cellStyleFooterGold);
						}else if (beanCellaExcel.getStyle() != null && beanCellaExcel.getStyle().equals(AppConstant.STILO_CORNFLOWER_BLUE)){
							cell.setCellStyle(cellStyleFooterCornFlower);
						}else{
							cell.setCellStyle(cellStyleFooter);
						}
						
						if (val instanceof Integer) {						
							cell.setCellValue((Integer)val);
						}else if(val instanceof Long){
							cell.setCellValue(((Long)val).intValue());
						}else {
							cell.setCellValue(String.valueOf(val));
						}
					}
				}else {
//					logger.error("insert --method.getName() "+method.getReturnType().getName()+" NULL");
					/** inserisco cella vuota! */
					cell.setCellValue("");
				}
			}
		}
	}
	
	private void setStyleCell(HSSFCell cell, String style){
		if (style != null && style.equals(AppConstant.STILO_GOLD)){
			cell.setCellStyle(columnTitleGoldStyle);
		}else if (style != null && style.equals(AppConstant.STILO_CORNFLOWER_BLUE)){
			cell.setCellStyle(columnTitleCornFlowerStyle);
		}else if (style != null && style.equals(AppConstant.STILO_BLUE_GREY)){
			cell.setCellStyle(columnTitleStyle);
		}else{
			cell.setCellStyle(cellRowStyle);
		}
	}
}