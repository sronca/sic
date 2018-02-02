package it.istruzione.ptof.beans.documenti;
 
import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;

public class GestioneDocumentiDTO extends BaseDTO {
	
	/**
	 * scuola operante ( loggata )
	 * valorizzato con oggetto in sessione se utente scuola
	 */
	private IstitutoScolasticoDTO istitutoScolastico ;

	public IstitutoScolasticoDTO getIstitutoScolastico() {
		return istitutoScolastico;
	}

	public void setIstitutoScolastico(IstitutoScolasticoDTO istitutoScolastico) {
		this.istitutoScolastico = istitutoScolastico;
	}  
	
}
