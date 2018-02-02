package it.istruzione.ptof.helper;

 

 
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import it.istruzione.ptof.beans.ValidationErrorFiedDTO;
import it.istruzione.ptof.beans.constant.CODICE_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.AttribFormDTO;
import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.FileMetaDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResposeIntDTO;
 

 

public class CommonsUtility {
	private static final SimpleDateFormat formatter=new SimpleDateFormat("ddMMyyyy");
	private static final SimpleDateFormat formatterIt=new SimpleDateFormat("dd/MM/yyyy");

	public static Calendar getCalendar(Date date) {
		Calendar result = null;
		if(date !=null) {
			
			result = Calendar.getInstance();
			result.setTime(date);
		}
		return result;
	}

	 
	
	public static String format(Calendar calendar) {
		return format(calendar.getTime());
	}

	public static String format(Date date) {
		return formatter.format(date);
	}

	public static String formatIt(Date date) {
		if (date==null) return "";
		return formatterIt.format(date);
	}

	
	public static String convertToSqlDate(Calendar calendar){
		String result="TO_DATE('"+format(calendar)+"', 'DDMMYYYY')";
		return result;
	}

	public static Date getCalendarToDate(Calendar calendar) {
		// TODO Auto-generated method stub
		 if(calendar !=null) {
			return calendar.getTime();
		}
		return null;
	}

	public static Long getBigDecimalToLong(BigDecimal numGioPag) {
		// TODO Auto-generated method stub
		if( numGioPag!=null) {
			return numGioPag.longValue();
		}
		return null;
	}
	
	
	 
	
 
	public static String getCheckSum ( byte [] xml ){
	 return   org.apache.commons.codec.digest.DigestUtils.md5Hex(xml);
	}
	public static void setResponseFileOut(HttpServletResponse response ,String fname) {
		 
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="+ fname);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",	"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		 
		response.setHeader("Set-Cookie","fileDownload=true; path=/");
	}
	
	public static BigDecimal calcolaPercetuale(BigDecimal value,BigDecimal totale){
		
		 BigDecimal hundred = new BigDecimal(100.00);
		 if( totale.compareTo(BigDecimal.ZERO) ==0 ) return BigDecimal.ZERO;
		 
		return value.multiply(hundred).divide(totale,2, BigDecimal.ROUND_HALF_UP) ;
	}
	
	
	 public static ResponseEntity<LinkedList<it.istruzione.ptof.beans.FileMetaDTO>> setErrorMessageLoadingAllegato(
			LinkedList<FileMetaDTO> files, FileMetaDTO fileMeta, String message ,String keyComponente) {
		files.add(fileMeta);
		HttpHeaders headers = new HttpHeaders();
		List<ValidationErrorFiedDTO> fieldErrorResources = new LinkedList<ValidationErrorFiedDTO>();
		ValidationErrorFiedDTO fieldErrorResource = new ValidationErrorFiedDTO();
		fieldErrorResource.setResource("upload");
		fieldErrorResource.setField("name_allegato_"+keyComponente);
		fieldErrorResource.setCode(CODICE_ERROR.E001);
		fieldErrorResource.setMessage(message);
		fieldErrorResources.add(fieldErrorResource);
		fileMeta.setErrors(fieldErrorResources);
		headers.setContentType(MediaType.TEXT_HTML);
		
		// imposto OK perche baco ie8 ( non gestisce gli stati correttamente )
		return new ResponseEntity<LinkedList<FileMetaDTO>>( files, headers, HttpStatus.OK ); 
	} 
	
	public  static  ResponseEntity  sendMessageToClient( ResposeIntDTO dto, AttribFormDTO outDTO ){
		if ( dto.getValidationError() != null ){
			return new ResponseEntity<ResposeIntDTO>(dto , HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(outDTO,HttpStatus.OK );
	}
	public static XMLGregorianCalendar getXMLGregorianCalendar(Calendar input)   {
		try {
			XMLGregorianCalendar result =null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = "";
			if (input != null) {
				strDate = sdf.format(input.getTime());
				Date dob=sdf.parse(strDate);
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(dob);
				result = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
			} 

			return result;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return anno scolastico di 6 cifre (e.g. 201314)
	 */
	public static int getAnnoScolasticoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);

		if (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER) {
			String tmp = String.valueOf(annoCorrente - 1)
					+ String.valueOf(annoCorrente).substring(2);
			return Integer.parseInt(tmp);
		} else {
			String tmp = String.valueOf(annoCorrente)
					+ String.valueOf(annoCorrente + 1).substring(2);
			return Integer.parseInt(tmp);
		}
	}
	
	public static Date getTodayDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static boolean checkFileZIP(MultipartFile mpf) {
		ZipInputStream zipStream;
		boolean b = true;

		try {
			zipStream = new ZipInputStream(new ByteArrayInputStream(mpf.getBytes()));
			String currentExt = "";
			ZipEntry entry = null;
			while ((entry = zipStream.getNextEntry()) != null) {
				currentExt = FilenameUtils.getExtension(entry.getName());

				//CONTROLLO CHE IL TIPO DI FILE SIA ACCETTATO (TXT,XML,XLS,DOC,PDF)
				if(    !currentExt.equalsIgnoreCase("JPG") &&
						!currentExt.equalsIgnoreCase("JPEG") &&
						!currentExt.equalsIgnoreCase("PDF")){

					b=false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}
	
	public static boolean sezioneIsModificabile(TIPO_SEZIONE tipoSezione, TIPO_STATO_DOC  statoDocumento) {
		boolean out = false;
		if ( tipoSezione==null){
			// caso eccezzionale in cui le sezioni che hanno dei figli non hanno un tipo sezione
			return out;
		}
		switch (tipoSezione) {
		case SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE:
		case SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO:
		case SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO:
		case SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI: {
			// queste sezioni devono essere moficabili fino alla pulicazione completa
			if (statoDocumento.compareTo(TIPO_STATO_DOC.PUBBLICATO_COMPLETO) == 0
					|| statoDocumento.compareTo(TIPO_STATO_DOC.FABBISOGNO_VALIDATO) == 0
					) {
				out = false;
			} else {
				out = true;
			}
		}
			break;
		default:
			// tutte le altre devono essere moficabili solo se il documento e' in copilazione
			if (statoDocumento.compareTo(TIPO_STATO_DOC.IN_COMPILAZIONE) == 0) {
				out = true;
				// viene lasciato al bk la possibilita di cambiare e decidere se modificare l'items
			} else {
				out = false;
			}

			
			break;
		}
		return out;
	}
	
	public static boolean sezioneIsCancellabile(TIPO_SEZIONE tipoSezione, TIPO_STATO_DOC  statoDocumento , TIPO_STATO_SEZIONE statoSezione ) {
		
		return sezioneIsModificabile(tipoSezione, statoDocumento) && statoSezione.compareTo(TIPO_STATO_SEZIONE.COMPILATA) == 0;
	}

	public static boolean isSecondariaDiPrimoGrado(String codScuUte){
    	boolean esito = false;
    	if (codScuUte != null){
    		if (codScuUte.substring(2,4).equals("MM") || codScuUte.substring(2,4).equals("IC")){
    			esito = true;
    		}
    	}
    	return esito;
    }

	public static boolean isSecondariaDiSecondoGrado(String codScuUte){
    	boolean esito = false;
    	if (codScuUte != null){
    		if (codScuUte.substring(2,4).equals("IS") || codScuUte.substring(2,4).compareTo("MM") > 0){
    			esito = true;
    		}
    	}
    	return esito;
    }

	public static boolean isPrimaria(String codScuUte){
    	boolean esito = false;
    	if (codScuUte != null){
    		if (codScuUte.substring(2,4).equals("EE") || codScuUte.substring(2,4).equals("IC")){
    			esito = true;
    		}
    	}
    	return esito;
    }

	public static boolean isInfanzia(String codScuUte){
    	boolean esito = false;
    	if (codScuUte != null){
    		if (codScuUte.substring(2,4).equals("AA")){
    			esito = true;
    		}
    	}
    	return esito;
    }
    

}
