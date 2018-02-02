package it.istruzione.ptof.catalogo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jboss.logging.Logger;

import it.istruzione.ptof.beans.DatiDecretoDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.ValidationErrorFiedDTO;
import it.istruzione.ptof.beans.constant.CODICE_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;

public class DecretiHelper {
	private static Logger logger = Logger.getLogger(DecretiHelper.class);
	
	private Map<Integer, ValidationErrorFiedDTO> mapValidationErrorFiedDTO = new HashMap<>();
	private int currentRow = 1;
	
	public SummaryImportDecreti importDecretiExcel(InputStream inputStream) throws Exception{
		SummaryImportDecreti summaryImportDecreti = new SummaryImportDecreti();
		
		try {
			List<DatiDecretoDTO> listDatiDecreti = new ArrayList<>();
			
			CSVParser parser = CSVFormat.newFormat(';').withHeader().parse(new InputStreamReader(inputStream));
			
			for(CSVRecord record : parser){
				if (record.size()!=13){
					throw new Exception("File non validato, l'applicazione gestice 13 colonne");
				}
				
				currentRow++;
				DatiDecretoDTO datiDecretoDTO = new DatiDecretoDTO();
				try{
					/*
					 * COLONNA 	
					 *    1 	progressivoDecreto;
						  2 	codiceTabella;
						  3		codiceRegione;
						  4 	descrizioneRegione;
						  5 	postiComuni;
						  6		postiSostegno;
						  7 	postiPotenziamentoSostegno;
						  8 	postiPotenziamentoPrima; // CHIEDERE IL NOME
						  9 	postiPotenziamentoIGrado;
						 10 	postiPotenziamentoIIGrado;
						 11 	totalePostiComuni;
						 12 	totalePostiSostegno;
						 13 	totalePostiPotenziamento;
					 * 
					 */
					
					datiDecretoDTO.setProgressivoDecreto(getContentCell(Long.class, record.get(0), true));
					datiDecretoDTO.setCodiceTabella(getContentCell(String.class, record.get(1), true));
					datiDecretoDTO.setCodiceRegione(getContentCell(String.class, record.get(2), true));
					datiDecretoDTO.setDescrizioneRegione(getContentCell(String.class, record.get(3), true));
					
					datiDecretoDTO.setPostiComuni(getContentCell(Long.class, record.get(4), false));
					datiDecretoDTO.setPostiSostegno(getContentCell(Long.class, record.get(5), false));
					datiDecretoDTO.setPostiPotenziamentoSostegno(getContentCell(Long.class, record.get(6), false));
					datiDecretoDTO.setPostiPotenziamentoPrimaria(getContentCell(Long.class, record.get(7), false));
					datiDecretoDTO.setPostiPotenziamentoIGrado(getContentCell(Long.class, record.get(8), false));
					datiDecretoDTO.setPostiPotenziamentoIIGrado(getContentCell(Long.class, record.get(9), false));
					datiDecretoDTO.setTotalePostiComuni(getContentCell(Long.class, record.get(10), false));
					datiDecretoDTO.setTotalePostiSostegno(getContentCell(Long.class, record.get(11), false));
					datiDecretoDTO.setTotalePostiPotenziamento(getContentCell(Long.class, record.get(12), false));
					
					listDatiDecreti.add(datiDecretoDTO);
					
				}catch (Exception ex) {
					ValidationErrorFiedDTO validationErrorFiedDTO = new ValidationErrorFiedDTO();
					validationErrorFiedDTO.setCode(CODICE_ERROR.E002);
					validationErrorFiedDTO.setMessage(String.valueOf(currentRow));
					mapValidationErrorFiedDTO.put(currentRow, validationErrorFiedDTO);
					logger.error(ex.getMessage());
				}
			}
			
			summaryImportDecreti.setListDatiDecretoDTO(listDatiDecreti);
		}catch (FileNotFoundException fEx){
			fEx.printStackTrace();
			logger.error(fEx.getMessage());
			throw fEx;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			throw e;
		}
		
		if(mapValidationErrorFiedDTO.size()>0){
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			
			validationErrorDTO.setMessage("Problemi con l'import Dati decreti");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
			
			LinkedList<ValidationErrorFiedDTO> listError = new LinkedList<>();
			
			for (Map.Entry<Integer,ValidationErrorFiedDTO> entry : mapValidationErrorFiedDTO.entrySet()) {
				listError.add(entry.getValue());
			}

			validationErrorDTO.setFieldErrors(listError);
			summaryImportDecreti.setValidationErrorDTO(validationErrorDTO);
		}
		
		summaryImportDecreti.setRowWorking(currentRow);
		return summaryImportDecreti;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getContentCell(Class<T> clazz, String value, boolean required) throws Exception{
		T o = null;
		
		try{
			if(value == null && required){
				throw new Exception("Required column");
			}
			
			if (value!=null && !value.equals("")){
				if (clazz.getName().equals("java.lang.Integer")){
					o = (T) new Integer(value);
				}else if (clazz.getName().equals("java.lang.String")){
					o = (T) new String(value);
				}else if (clazz.getName().equals("java.lang.Long")){
					o = (T) new Long(value);
				}
			}
			
		}catch (Exception e) {
			logger.error(clazz.getName() + "=[" + value + "]" );
			if (!mapValidationErrorFiedDTO.containsKey(currentRow)){
				ValidationErrorFiedDTO validationErrorFiedDTO = new ValidationErrorFiedDTO();
				validationErrorFiedDTO.setCode(CODICE_ERROR.E002);
				validationErrorFiedDTO.setMessage("Riga [" + currentRow + "] - controllare che dati siano conformi");
				
				mapValidationErrorFiedDTO.put(currentRow, validationErrorFiedDTO);
			}
		}
		return o;		
	}

	/**
	 * Per fare Testing
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		DecretiHelper decretiHelper = new DecretiHelper();
		
		File file = new File("C:\\temp\\StrutturaDatiDecreto.csv");
		
		SummaryImportDecreti summaryImportDecreti = decretiHelper.importDecretiExcel(new FileInputStream(file));
		
		if (summaryImportDecreti.getValidationErrorDTO() != null){
			List<ValidationErrorFiedDTO> list  = summaryImportDecreti.getValidationErrorDTO().getFieldErrors();
			
			for (ValidationErrorFiedDTO validationErrorFiedDTO : list) {
				System.out.println(validationErrorFiedDTO.getMessage());
			}
			
			System.out.println("Righe errori " + summaryImportDecreti.getValidationErrorDTO().getFieldErrors().size());
		}
		System.out.println("Righe lavorati " + summaryImportDecreti.getRowWorking());
    }
}
