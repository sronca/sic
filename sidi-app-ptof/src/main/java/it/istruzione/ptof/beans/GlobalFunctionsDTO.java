package it.istruzione.ptof.beans;

import javax.servlet.http.HttpSession;

/**
 * @author mcatanzaro
 * Oggetti di sessione cross su tutte le funzioni
 */
public class GlobalFunctionsDTO extends BaseDTO {

	
	/**
	 * Valorizzato quando si selezione il profilo scuola null per altri profili
	 */
	private IstitutoScolasticoDTO istitutoScolasticoDTO ;

	public IstitutoScolasticoDTO getIstitutoScolasticoDTO() {
		return istitutoScolasticoDTO;
	}

	public void setIstitutoScolasticoDTO(IstitutoScolasticoDTO istitutoScolasticoDTO) {
		this.istitutoScolasticoDTO = istitutoScolasticoDTO;
	}

	public static class Builder {
		
		Builder( HttpSession session ){
			
		}
	}


}
