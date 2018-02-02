package it.istruzione.ptof.service.mock.impl;



import java.util.LinkedList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoItem;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.convalida.RicercaConvalidaSingolaDTO;
import it.istruzione.ptof.service.ConvalidaFabbisognoService;


/**
 * @author peruvianit RF071– Gestione Catalogo Reportistica
 */
@Service
@Primary
public class ConvalidaFabbisognoMockService implements ConvalidaFabbisognoService {
	@Override
	public ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO> getElencoScuolePerConvalidazioneSingola(
			RicercaConvalidaSingolaDTO form) {
			ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO> out = new ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO>();
		
			Populator p = new PopulatorBuilder().build();
		
			LinkedList<ConvalidaFabbisognoSingolaScuolaDTO> items = new LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>();
	
			for (int i = 0; i < 2; i++) {
				items.add(p.populateBean(ConvalidaFabbisognoSingolaScuolaDTO.class));
			}
			
			out.setPagineTotali(9);
	  		out.setRigheTotali(2);
			out.setPaginaCorrente(3);
			out.setSort( form.getSort() );
			ResponseDTO<LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>> out2= new  ResponseDTO<LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>>();
			out2.setResult(items);
			out.setItems(out2);
	 
			return out;
	}

	@Override
	public ResponseDTO<Boolean> convalidaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto) {
		ResponseDTO<Boolean> res = new ResponseDTO<Boolean>();
		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
		validationErrorDTO.setKey("sergio");
		validationErrorDTO.setMessage("Errore generato in modalita Mockup!");
		validationErrorDTO.setTipoErrore(TIPO_ERROR.NONBLOCCANTE);
		
		res.setResult(false);
		res.setValidationError(validationErrorDTO);
		return res;
	}


	@Override
	public ResponseDTO<Boolean> rettificaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto) {
		ResponseDTO<Boolean> res = new ResponseDTO<Boolean>();
		res.setResult(true);
		return res;
	}

	@Override
	public CruscottoFabbisogniPostiComuniDTO getFabbisogniPostiComuni(SidiContesto sidiContesto,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoFabbisogniPostiComuniDTO cruscottoFabbisogniPostiComuniDTO = new CruscottoFabbisogniPostiComuniDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiComuniItem> list = new LinkedList<CruscottoFabbisogniPostiComuniItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiComuniItem.class));
		}

		cruscottoFabbisogniPostiComuniDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiComuniDTO.setItems(list);
		return cruscottoFabbisogniPostiComuniDTO;
	}

	@Override
	public CruscottoFabbisogniPostiSostegnoDTO getFabbisogniPostiSostegno(SidiContesto sidiContesto,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoFabbisogniPostiSostegnoDTO cruscottoFabbisogniPostiSostegnoDTO = new CruscottoFabbisogniPostiSostegnoDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiSostegnoItem> list = new LinkedList<CruscottoFabbisogniPostiSostegnoItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiSostegnoItem.class));
		}

		cruscottoFabbisogniPostiSostegnoDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiSostegnoDTO.setItems(list);
		return cruscottoFabbisogniPostiSostegnoDTO;
	}

	@Override
	public CruscottoFabbisogniPostiDiPotenziamentoDTO getFabbisogniPostiPotenziamento(SidiContesto sidiContesto,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoFabbisogniPostiDiPotenziamentoDTO cruscottoFabbisogniPostiDiPotenziamentoDTO = new CruscottoFabbisogniPostiDiPotenziamentoDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> list = new LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiDiPotenziamentoItem.class));
		}

		cruscottoFabbisogniPostiDiPotenziamentoDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiDiPotenziamentoDTO.setItems(list);
		return cruscottoFabbisogniPostiDiPotenziamentoDTO;
	}

	@Override
	public ResponseDTO<Boolean> convalidaSingola(String keyDocumentoPtof) {
		ResponseDTO<Boolean> res = new ResponseDTO<Boolean>();
		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
		validationErrorDTO.setKey("sergio");
		validationErrorDTO.setMessage("Errore generato in modalita Mockup - Convalida Singola!");
		validationErrorDTO.setTipoErrore(TIPO_ERROR.NONBLOCCANTE);
		
		res.setResult(false);
		res.setValidationError(validationErrorDTO);
		return res;
	}

	@Override
	public ResponseDTO<Boolean> rettificaSingola(String keyDocumentoPtof) {
		ResponseDTO<Boolean> res = new ResponseDTO<Boolean>();
		res.setResult(true);
		return res;
	}
}
