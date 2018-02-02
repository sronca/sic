package it.istruzione.ptof.component.report;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.DocumentException;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;

public interface PtofReport {

	void loadDocumentoFormatoPDF(ByteArrayOutputStream outputStream, String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) throws   Exception;

}