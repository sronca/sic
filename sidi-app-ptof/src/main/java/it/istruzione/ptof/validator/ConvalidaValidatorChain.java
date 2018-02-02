package it.istruzione.ptof.validator;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;

import java.util.Iterator;
import java.util.List;

public class ConvalidaValidatorChain implements ConvalidaValidator{
	
    private List<ConvalidaValidator> validators;

    @Override
    public ResponseDTO<Boolean> validate(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception{

    	ResponseDTO<Boolean> response = new ResponseDTO<>();
    	response.setResult(Boolean.TRUE);

        for (Iterator<ConvalidaValidator> iter = validators.iterator(); iter.hasNext()
                && response.getResult().booleanValue();) {

        	ConvalidaValidator validator = iter.next();
        	response = validator.validate(keyDocumento, istitutoScolasticoDTO);
        }
        return response;
    }

    public void setValidators(List<ConvalidaValidator> validators) {
        this.validators = validators;
    }

}
