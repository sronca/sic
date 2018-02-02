package it.istruzione.ptof.validator;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;

public interface ConvalidaValidator {
	
	ResponseDTO<Boolean> validate(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception;

}
