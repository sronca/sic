package it.istruzione.ptof.validator;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.service.impl.ValidatorService;

import org.springframework.beans.factory.annotation.Autowired;

public class DateObbligatorieSezioneDatiFinaliScuolaValidator implements ConvalidaValidator{
	
	@Autowired
	private ValidatorService validatorService;

	@Override
	public ResponseDTO<Boolean> validate(String keyDocumento, IstitutoScolasticoDTO istitutoScolasticoDTO) throws Exception{
		
		ResponseDTO<Boolean> response = new ResponseDTO<>();
		response.setResult(Boolean.TRUE);

		String codScuUte = keyDocumento.substring(0,10);
		Long perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
		Long prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));
		
		Tbs1004Datisezionesottosez sezioneSEZIONE_32_DATI_FINALI_SCUOLA = validatorService.getSezioneScuola(Long.valueOf(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.code()), codScuUte, prgGesCatDoc, perAnnSco);
		if (sezioneSEZIONE_32_DATI_FINALI_SCUOLA == null
				|| sezioneSEZIONE_32_DATI_FINALI_SCUOLA.getDatRatDirSco() == null
				|| sezioneSEZIONE_32_DATI_FINALI_SCUOLA.getDatPrdPto() == null
				|| sezioneSEZIONE_32_DATI_FINALI_SCUOLA.getDatAppCdi() == null){
			
			response.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			response.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("06");
			validationErrorDTO.setMessage("Attenzione, è obbligatorio inserire la 'Data ratifica atto di indirizzo Dirigente Scolastico', la 'Data predisposizione PTOF Collegio dei Docenti' e la 'Data approvazione Consiglio d'Istituto'.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
		
		return response;
	}

}
