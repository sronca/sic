package it.istruzione.ptof.validator;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.service.impl.ValidatorService;

import org.springframework.beans.factory.annotation.Autowired;

public class SezioniObbligatorieValidator implements ConvalidaValidator{
	
	@Autowired
	private ValidatorService validatorService;

	@Override
	public ResponseDTO<Boolean> validate(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception{
		
		ResponseDTO<Boolean> response = new ResponseDTO<>();
		response.setResult(Boolean.TRUE);

		String codScuUte = keyDocumento.substring(0,10);
		Long perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
		Long prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));
		
		if (validatorService.findSezioniScuolaObbligatorieNonCompilate(codScuUte, perAnnSco, prgGesCatDoc).size() > 0 ){
			response.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			response.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("04");
			validationErrorDTO.setMessage("Attenzione, è obbligatorio compilare tutti i dati delle sezioni/sottosezioni presenti nella lista delle sezioni contraddistinte come obbligatorie");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
				
		return response;
	}

}
