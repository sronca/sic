package it.istruzione.ptof.service.impl;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaDTO;
import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaFiltroDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoItem;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.SortDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.SortElencoReportCompletoHelper;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.convalida.RicercaConvalidaSingolaDTO;
import it.istruzione.ptof.constant.AppConstant;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.helper.PoliHelper;
import it.istruzione.ptof.model.entity.Mfg1012Regione;
import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;
import it.istruzione.ptof.model.entity.Tbs1006Tipostato;
import it.istruzione.ptof.model.entity.business.CountEntity;
import it.istruzione.ptof.model.repository.Mfg1012RegioneRepository;
import it.istruzione.ptof.model.repository.Tbs1002GestioneptofRepository;
import it.istruzione.ptof.model.repository.business.CountEntityRepository;
import it.istruzione.ptof.service.ConvalidaFabbisognoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ConvalidaFabbisognoServiceImpl extends BaseServiceImpl implements ConvalidaFabbisognoService {
	
	private static Logger logger = Logger.getLogger(ConvalidaFabbisognoServiceImpl.class);
	
	@Autowired
	private Tbs1002GestioneptofRepository tbs1002GestioneptofRepository;
	
	@Autowired
	private Mfg1012RegioneRepository mfg1012RegioneRepository;
	
	@Autowired
	private CountEntityRepository countEntityRepository;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO> getElencoScuolePerConvalidazioneSingola(RicercaConvalidaSingolaDTO pagefilter) {
		
		logger.debug("in getElencoScuolePerConvalidazioneSingola ... ");
		ConvalidaFabbisognoSingolaScuolaFiltroDTO filter = pagefilter.getConvalidaFabbisognoSingolaScuolaFiltro();
		logger.debug(ReflectionToStringBuilder.toString(filter,ToStringStyle.MULTI_LINE_STYLE));

		//***PAGINAZIONE***//
		Integer paginaCorrente = pagefilter.getPaginaCorrente() == null ? 0 : pagefilter.getPaginaCorrente()-1;
		Integer numeroRighePerPagina =  pagefilter.getNumeroRighePerPagina(); 
		
		PageRequest pageRequest  =  new PageRequest(paginaCorrente, numeroRighePerPagina);
		if(pagefilter != null &&  pagefilter.getSort()!=null ){
			List<Order> orders = new ArrayList<Sort.Order>(); 
			for(SortDTO sort : pagefilter.getSort() ){
					orders.add(new Order(sort.getDir(), sort.getCampo().getLabel()));
			}
			pageRequest  = new PageRequest(paginaCorrente, numeroRighePerPagina , new Sort (orders) );
		} else {
			pageRequest  = new PageRequest(paginaCorrente, numeroRighePerPagina , 
					new Sort (Direction.ASC, SortElencoReportCompletoHelper.REGIONE.getLabel()  )
			        .and( new Sort (Direction.ASC, SortElencoReportCompletoHelper.PROVINCIA.getLabel()))
			        .and( new Sort (Direction.ASC, SortElencoReportCompletoHelper.COMUNE.getLabel())));
		}
		
		Page<Tbs1002Gestioneptof> tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findByFilter(filter.getProgresivoDocumento(), 
																									filter.getRegione()!=null?filter.getRegione().trim():null,
																									filter.getProvincia(),
																									filter.getComune(),
																									filter.getCodiceMeccanografico(),
																									TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																									((filter.getDenominazione()!=null) && (!filter.getDenominazione().isEmpty()))?"%" + filter.getDenominazione().toUpperCase() + "%":null,
																									pageRequest);
		
		logger.debug(ReflectionToStringBuilder.toString(tbs1002Gestioneptofs,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("tbs1002Gestioneptofs.getTotalPages() : " + tbs1002Gestioneptofs.getTotalPages());
		logger.debug("tbs1002Gestioneptofs.getTotalElements() : " + tbs1002Gestioneptofs.getTotalElements());
		logger.debug("tbs1002Gestioneptofs.getNumber() : " + tbs1002Gestioneptofs.getNumber());
		
		ResponsePageDTO<ConvalidaFabbisognoSingolaScuolaDTO> out = new ResponsePageDTO<>();
		out.setPagineTotali(tbs1002Gestioneptofs.getTotalPages());
 		out.setRigheTotali( ( new Long ( tbs1002Gestioneptofs.getTotalElements() ) ).intValue() );
		out.setPaginaCorrente(tbs1002Gestioneptofs.getNumber()+1);
		out.setSort( pagefilter.getSort() );
		
		LinkedList<ConvalidaFabbisognoSingolaScuolaDTO> items = new LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>();
		
		for (Tbs1002Gestioneptof tbs1002Gestioneptof : tbs1002Gestioneptofs){
			ConvalidaFabbisognoSingolaScuolaDTO dto = DtoFactory.getConvalidaFabbisognoSingolaScuolaDTO(tbs1002Gestioneptof);
			items.add(dto);
		}
		
		ResponseDTO<LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>> out2= new  ResponseDTO<LinkedList<ConvalidaFabbisognoSingolaScuolaDTO>>();
		out2.setResult(items);
		out.setItems(out2);
		
		return out;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiComuniDTO getFabbisogniPostiComuni(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getFabbisogniPostiComuni ... ");
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug(ReflectionToStringBuilder.toString(sidiContesto,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiComuniDTO out = new CruscottoFabbisogniPostiComuniDTO();

		if (gestioneCatalogoDTO != null && sidiContesto != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiComuniItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
					|| sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){

				List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(sidiContesto.getCodice());
				for (String codiceRegione : regioni){
					mfg1012Regiones.add(mfg1012RegioneRepository.findByCodiceRegione(codiceRegione));
				}

			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				logger.debug("elaborazione codice regione : " + mfg1012Regione.getCodReg().trim());
				CruscottoFabbisogniPostiComuniItem item = new CruscottoFabbisogniPostiComuniItem();
				item.setRegione(mfg1012Regione.getDesReg());
				
				CountEntity fabbisogniPostiComuniInfanzia = countEntityRepository.findFabbisogniPostiComuni(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "AA");
				CountEntity fabbisogniPostiComuniPrimaria = countEntityRepository.findFabbisogniPostiComuni(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "EE");
				CountEntity fabbisogniPostiComuniIGrado = countEntityRepository.findFabbisogniPostiComuni(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "MM");
				CountEntity fabbisogniPostiComuniIIGrado = countEntityRepository.findFabbisogniPostiComuni(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "SS");
				Long fabbisognoInfanzia = null;
				Long fabbisognoPrimaria = null;
				Long fabbisognoIGrado = null;
				Long fabbisognoIIGrado = null;
				
				switch (gestioneCatalogoDTO.getNumeroVersioneDocumento().intValue()){
				case 0:
					fabbisognoInfanzia = fabbisogniPostiComuniInfanzia.getNumPosPri();
					fabbisognoPrimaria = fabbisogniPostiComuniPrimaria.getNumPosPri();
					fabbisognoIGrado = fabbisogniPostiComuniIGrado.getNumPosPri();
					fabbisognoIIGrado = fabbisogniPostiComuniIIGrado.getNumPosPri();
					break;
				case 1:
					fabbisognoInfanzia = fabbisogniPostiComuniInfanzia.getNumPosSec();
					fabbisognoPrimaria = fabbisogniPostiComuniPrimaria.getNumPosSec();
					fabbisognoIGrado = fabbisogniPostiComuniIGrado.getNumPosSec();
					fabbisognoIIGrado = fabbisogniPostiComuniIIGrado.getNumPosSec();
					break;	
				case 2:
					fabbisognoInfanzia = fabbisogniPostiComuniInfanzia.getNumPosTer();
					fabbisognoPrimaria = fabbisogniPostiComuniPrimaria.getNumPosTer();
					fabbisognoIGrado = fabbisogniPostiComuniIGrado.getNumPosTer();
					fabbisognoIIGrado = fabbisogniPostiComuniIIGrado.getNumPosTer();
					break;
				}
				
				item.setFabbisognoInfanzia(fabbisognoInfanzia);
				item.setFabbisognoPrimaria(fabbisognoPrimaria);
				item.setFabbisognoIGrado(fabbisognoIGrado);
				item.setFabbisognoIIGrado(fabbisognoIIGrado);
				
				item.setDecretoInfanzia(0L);
				item.setDecretoPrimaria(0L);
				item.setDecretoIGrado(0L);
				item.setDecretoIIGrado(0L);
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiSostegnoDTO getFabbisogniPostiSostegno(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getFabbisogniPostiSostegno ... ");
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug(ReflectionToStringBuilder.toString(sidiContesto,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiSostegnoDTO out = new CruscottoFabbisogniPostiSostegnoDTO();

		if (gestioneCatalogoDTO != null && sidiContesto != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiSostegnoItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
					|| sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){

				List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(sidiContesto.getCodice());
				for (String codiceRegione : regioni){
					mfg1012Regiones.add(mfg1012RegioneRepository.findByCodiceRegione(codiceRegione));
				}

			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				CruscottoFabbisogniPostiSostegnoItem item = new CruscottoFabbisogniPostiSostegnoItem();
				item.setRegione(mfg1012Regione.getDesReg());
							
				CountEntity fabbisogniPostiSostegno = countEntityRepository.findFabbisogniPostiSostegno(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim());
				CountEntity fabbisogniPostiPotenziamentoAlSostegno = countEntityRepository.findFabbisogniPostiPotenziamentoAlSostegno(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim());
				Long fabbisognoSostegno = null;
				Long fabbisognoPotenziamentoAlSostegno = null;
				
				switch (gestioneCatalogoDTO.getNumeroVersioneDocumento().intValue()){
				case 0:
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosPri();
					fabbisognoPotenziamentoAlSostegno = fabbisogniPostiPotenziamentoAlSostegno.getNumPosPri();
					break;
				case 1:
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosSec();
					fabbisognoPotenziamentoAlSostegno = fabbisogniPostiPotenziamentoAlSostegno.getNumPosSec();
					break;	
				case 2:
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosTer();
					fabbisognoPotenziamentoAlSostegno = fabbisogniPostiPotenziamentoAlSostegno.getNumPosTer();
					break;
				}
				
				item.setFabbisognoSostegno(fabbisognoSostegno);
				item.setFabbisognoPotenziamentoPerSostegno(fabbisognoPotenziamentoAlSostegno);
				
				item.setDecretoOrganicoDiritto(0L);
				item.setDecretoPotenziamentoPerSostegno(0L);
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiDiPotenziamentoDTO getFabbisogniPostiPotenziamento(SidiContesto sidiContesto, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getFabbisogniPostiPotenziamento ... ");
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug(ReflectionToStringBuilder.toString(sidiContesto,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiDiPotenziamentoDTO out = new CruscottoFabbisogniPostiDiPotenziamentoDTO();

		if (gestioneCatalogoDTO != null && sidiContesto != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
					|| sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){

				List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(sidiContesto.getCodice());
				for (String codiceRegione : regioni){
					mfg1012Regiones.add(mfg1012RegioneRepository.findByCodiceRegione(codiceRegione));
				}

			}
			
			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				logger.debug("elaborazione codice regione : " + mfg1012Regione.getCodReg().trim());
				CruscottoFabbisogniPostiDiPotenziamentoItem item = new CruscottoFabbisogniPostiDiPotenziamentoItem();
				item.setRegione(mfg1012Regione.getDesReg());
				
				CountEntity fabbisogniPostiPotenziamentoPrimaria = countEntityRepository.findFabbisogniPostiPotenziamento(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "EE");
				CountEntity fabbisogniPostiPotenziamentoIGrado = countEntityRepository.findFabbisogniPostiPotenziamento(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "MM");
				CountEntity fabbisogniPostiPotenziamentoIIGrado = countEntityRepository.findFabbisogniPostiPotenziamento(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), "SS");
				Long fabbisognoPrimaria = null;
				Long fabbisognoIGrado = null;
				Long fabbisognoIIGrado = null;
				
				switch (gestioneCatalogoDTO.getNumeroVersioneDocumento().intValue()){
				case 0:
					fabbisognoPrimaria = fabbisogniPostiPotenziamentoPrimaria.getNumPosPri();
					fabbisognoIGrado = fabbisogniPostiPotenziamentoIGrado.getNumPosPri();
					fabbisognoIIGrado = fabbisogniPostiPotenziamentoIIGrado.getNumPosPri();
					break;
				case 1:
					fabbisognoPrimaria = fabbisogniPostiPotenziamentoPrimaria.getNumPosSec();
					fabbisognoIGrado = fabbisogniPostiPotenziamentoIGrado.getNumPosSec();
					fabbisognoIIGrado = fabbisogniPostiPotenziamentoIIGrado.getNumPosSec();
					break;	
				case 2:
					fabbisognoPrimaria = fabbisogniPostiPotenziamentoPrimaria.getNumPosTer();
					fabbisognoIGrado = fabbisogniPostiPotenziamentoIGrado.getNumPosTer();
					fabbisognoIIGrado = fabbisogniPostiPotenziamentoIIGrado.getNumPosTer();
					break;
				}
				
				item.setFabbisognoPrimaria(fabbisognoPrimaria);
				item.setFabbisognoIGrado(fabbisognoIGrado);
				item.setFabbisognoIIGrado(fabbisognoIIGrado);
				
				item.setDecretoPrimaria(0L);
				item.setDecretoIGrado(0L);
				item.setDecretoIIGrado(0L);
				
				items.add(item);
			}
			
		}
		
		return out;
	}

	@Override
	public ResponseDTO<Boolean> convalidaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto) {

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try{
			
			if (gestioneCatalogoDTO != null && sidiContesto != null){
				
				List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
				if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
						|| sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)){

					mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();


				}else if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){

					List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(sidiContesto.getCodice());
					for (String codiceRegione : regioni){
						mfg1012Regiones.add(mfg1012RegioneRepository.findByCodiceRegione(codiceRegione));
					}

				}

				for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
					
					logger.debug("elaborazione regione : " + mfg1012Regione.getCodReg());
					
					List<Tbs1002Gestioneptof> tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findByFilter(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), 
																												mfg1012Regione.getCodReg().trim(),
																												null,
																												null,
																												null,
																												TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																												null);

					for (Tbs1002Gestioneptof tbs1002Gestioneptof : tbs1002Gestioneptofs){
						
						Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
						tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.FABBISOGNO_VALIDATO.code());
						tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
						tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
					}

					out.setResult(Boolean.TRUE);
					return out;
				}
			}
			
		}catch(Exception ex){
			logger.error("ERRORE convalidaMassiva : " + ex.getMessage(), ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
		return out;

	}

	@Override
	public ResponseDTO<Boolean> rettificaMassiva(GestioneCatalogoDTO gestioneCatalogoDTO, SidiContesto sidiContesto) {

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try{
			
			if (gestioneCatalogoDTO != null && sidiContesto != null){
				
				List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
				if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
						|| sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)){

					mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();


				}else if (sidiContesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){

					List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(sidiContesto.getCodice());
					for (String codiceRegione : regioni){
						mfg1012Regiones.add(mfg1012RegioneRepository.findByCodiceRegione(codiceRegione));
					}

				}

				for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
					
					logger.debug("elaborazione regione : " + mfg1012Regione.getCodReg());
					
					List<Tbs1002Gestioneptof> tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findByFilter(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), 
																												mfg1012Regione.getCodReg().trim(),
																												null,
																												null,
																												null,
																												TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																												null);

					for (Tbs1002Gestioneptof tbs1002Gestioneptof : tbs1002Gestioneptofs){
						
						Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
						tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.FABBISOGNO_NON_VALIDATO.code());
						tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
						tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
					}

					out.setResult(Boolean.TRUE);
					return out;
				}
			}
			
		}catch(Exception ex){
			logger.error("ERRORE rettificaMassiva : " + ex.getMessage(), ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
		return out;

	}

	@Override
	public ResponseDTO<Boolean> convalidaSingola(String keyDocumentoPtof) {

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try{
			String codScuUte = keyDocumentoPtof.substring(0,10);
			Long perAnnSco = Long.valueOf(keyDocumentoPtof.substring(10,16));
			Long prgGesCatDoc = Long.valueOf(keyDocumentoPtof.substring(16));

			Tbs1002GestioneptofPK tbs1002GestioneptofPK = new Tbs1002GestioneptofPK();
			tbs1002GestioneptofPK.setCodScuUte(codScuUte);
			tbs1002GestioneptofPK.setPerAnnSco(perAnnSco);
			tbs1002GestioneptofPK.setPrgGesCatDoc(prgGesCatDoc);
			Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository.findOne(tbs1002GestioneptofPK);
			Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
			tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.FABBISOGNO_VALIDATO.code());
			tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
			tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
			
			out.setResult(Boolean.TRUE);
			return out;

		}catch(Exception ex){
			logger.error("ERRORE convalidaSingola : " + ex.getMessage(), ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}

		return out;
	}

	@Override
	public ResponseDTO<Boolean> rettificaSingola(String keyDocumentoPtof) {

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try{
			String codScuUte = keyDocumentoPtof.substring(0,10);
			Long perAnnSco = Long.valueOf(keyDocumentoPtof.substring(10,16));
			Long prgGesCatDoc = Long.valueOf(keyDocumentoPtof.substring(16));

			Tbs1002GestioneptofPK tbs1002GestioneptofPK = new Tbs1002GestioneptofPK();
			tbs1002GestioneptofPK.setCodScuUte(codScuUte);
			tbs1002GestioneptofPK.setPerAnnSco(perAnnSco);
			tbs1002GestioneptofPK.setPrgGesCatDoc(prgGesCatDoc);
			Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository.findOne(tbs1002GestioneptofPK);
			Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
			tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.FABBISOGNO_NON_VALIDATO.code());
			tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
			tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
			
			out.setResult(Boolean.TRUE);
			return out;

		}catch(Exception ex){
			logger.error("ERRORE rettificaSingola : " + ex.getMessage(), ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}

		return out;
	}

}
