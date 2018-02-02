package it.istruzione.ptof.validator;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;

public class ChiaveDocumentoValidator implements ConvalidaValidator{

	@Override
	public ResponseDTO<Boolean> validate(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception{

		ResponseDTO<Boolean> response = new ResponseDTO<>();
		response.setResult(Boolean.TRUE);

		String codScuUte = null;
		Long perAnnSco = null;
		Long prgGesCatDoc = null;

		try{
			if (keyDocumento != null && ! keyDocumento.isEmpty()){
				codScuUte = keyDocumento.substring(0,10);
				perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
				prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));
			}else{
				response.setResult(Boolean.FALSE);
				ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
				response.setValidationError(validationErrorDTO);
				validationErrorDTO.setKey("01");
				validationErrorDTO.setMessage("Chiave documento non valida");
				validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
				return response;
			}
		}catch(Exception ex){
			response.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			response.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("02");
			validationErrorDTO.setMessage("Chiave documento non valida");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
			return response;
		}
		
		if (!codScuUte.equals(istitutoScolasticoDTO.getCodiceMecIstPrin())){
			response.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			response.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("03");
			validationErrorDTO.setMessage("Codice meccanografico non valido");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}


		return response;
	}

}
