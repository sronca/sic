package it.istruzione.test;

import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;

public class TestPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println( TIPO_FILE_ACCETTATO.PDF.isValidFileName("pdffff"));
		System.out.println( TIPO_FILE_ACCETTATO.PDF.isValidFileName("pdf"));
		
		System.out.println( TIPO_FILE_ACCETTATO.JPEG.isValidFileName("pdf"));
		System.out.println( "true="+TIPO_FILE_ACCETTATO.JPEG.isValidFileName("jpg"));
		System.out.println( "true="+TIPO_FILE_ACCETTATO.JPEG.isValidFileName("jpeg"));
		System.out.println( "false="+TIPO_FILE_ACCETTATO.JPEG.isValidFileName("jjpg"));
		System.out.println( "false="+TIPO_FILE_ACCETTATO.JPEG.isValidFileName(" jpg "));
				
	}

}
