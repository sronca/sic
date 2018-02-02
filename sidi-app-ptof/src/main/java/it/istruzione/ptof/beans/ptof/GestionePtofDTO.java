package it.istruzione.ptof.beans.ptof;
 
import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;

/**
 * @author mcatanzaro
 *
 */
public class GestionePtofDTO extends BaseDTO {
	
	
	/**
	 * scuola operante ( loggata )
	 * valorizzato con oggetto in sessione se utente scuola
	 */
	private IstitutoScolasticoDTO istitutoScolastico ;  
	
	 /**
	 *  sempre valorizzato nelle funzione gestione ptof
	 */
	private DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO ;
	
	public DocumentoAnnoIncorsoDTO getDocumentoAnnoIncorsoDTO() {
		return documentoAnnoIncorsoDTO;
	}

	public void setDocumentoAnnoIncorsoDTO(DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO) {
		this.documentoAnnoIncorsoDTO = documentoAnnoIncorsoDTO;
	}

	public IstitutoScolasticoDTO getIstitutoScolastico() {
		return istitutoScolastico;
	}

	public void setIstitutoScolastico(IstitutoScolasticoDTO istitutoScolastico) {
		this.istitutoScolastico = istitutoScolastico;
	}

}
