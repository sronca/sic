package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.util.List;

public class VOAVCPFinanza {

	private VODocumentoAVCP documentoSel;
	
	private VODocumentoAVCPMetadata documentoMetadataSel;
	
	private List<VOLottoAVCP> lotti;

	public VODocumentoAVCP getDocumentoSel() {
		return documentoSel;
	}

	public void setDocumentoSel(VODocumentoAVCP documentoSel) {
		this.documentoSel = documentoSel;
	}

	public VODocumentoAVCPMetadata getDocumentoMetadataSel() {
		return documentoMetadataSel;
	}

	public void setDocumentoMetadataSel(VODocumentoAVCPMetadata documentoMetadataSel) {
		this.documentoMetadataSel = documentoMetadataSel;
	}

	public List<VOLottoAVCP> getLotti() {
		return lotti;
	}

	public void setLotti(List<VOLottoAVCP> lotti) {
		this.lotti = lotti;
	}

	
}
