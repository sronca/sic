package it.istruzione.ptof.service.impl;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.ConsultazioneFabbisognoFiltroDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoItem;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaItem;
import it.istruzione.ptof.beans.FabbisognoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDiSostegnoItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoPrimariaItem;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoIIGradoItem;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoItem;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.MonitoraggioStatisticheDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.ReportCompletoFiltroDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.SortDTO;
import it.istruzione.ptof.beans.constant.SortElencoReportCompletoHelper;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.constant.AppConstant;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.helper.PoliHelper;
import it.istruzione.ptof.model.entity.Mfg1012Regione;
import it.istruzione.ptof.model.entity.Tbs1001Gestionecatalogodoc;
import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.model.entity.Tbs1037Fabpostocomune;
import it.istruzione.ptof.model.entity.Tbs1039Fabpostosostegno;
import it.istruzione.ptof.model.entity.Tbs1040Fabpostopotenziamento;
import it.istruzione.ptof.model.entity.Tbs1043Fabdecretointerminper;
import it.istruzione.ptof.model.entity.Tbs1043FabdecretointerminperPK;
import it.istruzione.ptof.model.entity.business.CountEntity;
import it.istruzione.ptof.model.entity.business.Scuola;
import it.istruzione.ptof.model.repository.Mfg1012RegioneRepository;
import it.istruzione.ptof.model.repository.Tbs1001GestionecatalogodocRepository;
import it.istruzione.ptof.model.repository.Tbs1002GestioneptofRepository;
import it.istruzione.ptof.model.repository.Tbs1004DatisezionesottosezRepository;
import it.istruzione.ptof.model.repository.Tbs1043FabdecretointerminperRepository;
import it.istruzione.ptof.model.repository.business.CountEntityRepository;
import it.istruzione.ptof.model.repository.business.ScuolaRepository;
import it.istruzione.ptof.monitoraggio.RicercaConsultazionePtofDTO;
import it.istruzione.ptof.monitoraggio.RicercaReportDTO;
import it.istruzione.ptof.service.HomeService;
import it.istruzione.ptof.service.MonitoraggioService;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
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
public class MonitoraggioServiceImpl extends BaseServiceImpl implements MonitoraggioService {

	private static Logger logger = Logger.getLogger(MonitoraggioServiceImpl.class);
	
	@Autowired
	private Tbs1001GestionecatalogodocRepository tbs1001GestionecatalogodocRepository;

	@Autowired
	private Tbs1002GestioneptofRepository tbs1002GestioneptofRepository;
	
	@Autowired
	private Mfg1012RegioneRepository mfg1012RegioneRepository;
	
	@Autowired
	private CountEntityRepository countEntityRepository;
	
	@Autowired
	private Tbs1043FabdecretointerminperRepository tbs1043FabdecretointerminperRepository;
	
	@Autowired
	private Tbs1004DatisezionesottosezRepository tbs1004DatisezionesottosezRepository;
	
	@Autowired
	private ScuolaRepository scuolaRepository;
	
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<MonitoraggioStatisticheDTO> getStatistiche(SidiContesto sidiContesto,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getStatistiche ... ");
		LinkedList<MonitoraggioStatisticheDTO> out = new LinkedList<>();
		
		logger.debug(ReflectionToStringBuilder.toString(sidiContesto,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		
		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()));
		if (tbs1001Gestionecatalogodoc != null){
			int annoScolasticoInizio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue() + tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			int annoScolasticoFine = annoScolasticoInizio + 1;
			String annoScolastico = String.valueOf(annoScolasticoInizio) + String.valueOf(annoScolasticoFine).substring(2);
			logger.debug(" annoScolastico : " + annoScolastico);

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

				MonitoraggioStatisticheDTO monitoraggioStatisticheDTO = new MonitoraggioStatisticheDTO();
				monitoraggioStatisticheDTO.setUsr(mfg1012Regione.getDesReg());
				monitoraggioStatisticheDTO.setNumeroIstitutiCompilati(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.IN_COMPILAZIONE.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiConvalidati(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.CONVALIDATO.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiInAnteprima(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiFabbisognoValidato(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.FABBISOGNO_VALIDATO.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiFabbisognoNonValidato(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.FABBISOGNO_NON_VALIDATO.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiPubblicatiDefinitivi(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.PUBBLICATO_COMPLETO.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiPubblicatiParziali(countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code()));
				monitoraggioStatisticheDTO.setNumeroIstitutiPartecipanti(countEntityRepository.findCountMfg1002AnagistscolByFilter(Long.valueOf(annoScolastico), mfg1012Regione.getCodReg().trim(), null, null));

				int numeroIstitutiRispondenti = countEntityRepository.findCountTbs1002GestioneptofByFilter(Long.valueOf(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento().intValue()), mfg1012Regione.getCodReg().trim(), null, null, null, null);
				int numeroIstitutiNonRispondenti = monitoraggioStatisticheDTO.getNumeroIstitutiPartecipanti().intValue() - numeroIstitutiRispondenti;
				monitoraggioStatisticheDTO.setNumeroIstitutiNonRispondenti(numeroIstitutiNonRispondenti);

				out.add(monitoraggioStatisticheDTO);
				logger.debug(ReflectionToStringBuilder.toString(monitoraggioStatisticheDTO,ToStringStyle.MULTI_LINE_STYLE));

			}

		}
		
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ResponsePageDTO<ReportCompletoDTO> getReportCompleto(RicercaReportDTO form) {
		
		logger.debug("in getReportCompleto ... ");
		ReportCompletoFiltroDTO filter = form.getReportCompleto();
		logger.debug(ReflectionToStringBuilder.toString(filter,ToStringStyle.MULTI_LINE_STYLE));

		//***PAGINAZIONE***//
		Integer paginaCorrente = form.getPaginaCorrente() == null ? 0 : form.getPaginaCorrente()-1;
		Integer numeroRighePerPagina =  form.getNumeroRighePerPagina(); 
		
		PageRequest pageRequest  =  new PageRequest(paginaCorrente, numeroRighePerPagina);
		if(form != null &&  form.getSort()!=null ){
			List<Order> orders = new ArrayList<Sort.Order>(); 
			for(SortDTO sort : form.getSort() ){
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
																										   filter.getStatoPTOF(),
																										   null,
																										   pageRequest);
		
		logger.debug(ReflectionToStringBuilder.toString(tbs1002Gestioneptofs,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("tbs1002Gestioneptofs.getTotalPages() : " + tbs1002Gestioneptofs.getTotalPages());
		logger.debug("tbs1002Gestioneptofs.getTotalElements() : " + tbs1002Gestioneptofs.getTotalElements());
		logger.debug("tbs1002Gestioneptofs.getNumber() : " + tbs1002Gestioneptofs.getNumber());
		
		ResponsePageDTO<ReportCompletoDTO> out = new ResponsePageDTO<>();
		out.setPagineTotali(tbs1002Gestioneptofs.getTotalPages());
 		out.setRigheTotali( ( new Long ( tbs1002Gestioneptofs.getTotalElements() ) ).intValue() );
		out.setPaginaCorrente(tbs1002Gestioneptofs.getNumber()+1);
		out.setSort( form.getSort() );
		
		LinkedList<ReportCompletoDTO> items = new LinkedList<ReportCompletoDTO>();
		
		for (Tbs1002Gestioneptof tbs1002Gestioneptof : tbs1002Gestioneptofs){
			ReportCompletoDTO dto = DtoFactory.getDocumentoReportCompletoDTO(tbs1002Gestioneptof);
			items.add(dto);
		}
		
		ResponseDTO<LinkedList<ReportCompletoDTO>> out2= new  ResponseDTO<LinkedList<ReportCompletoDTO>>();
		out2.setResult(items);
		out.setItems(out2);
		
		return out;
	}
	
	
	/**
	 * @param keyPtof : chiave del documento
	 * @param tipoFileDaScaricare : VISUALIZZAZIONE_DOCUMENTO_CONVALIDATO, PUBBLICAZIONE_PARZIALE, PUBBLICAZIONE_COMPLETA

	 * @return FileDTO
	 * use: scaricare il documento ptof relativo al tipoFileDaScaricare richiesto in input
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public FileDTO loadPtofFile(String keyPtof, TIPO_FILE_PDF tipoFileDaScaricare){

		FileDTO dto = null;

		Tbs1002GestioneptofPK pk =  UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(keyPtof);
		if (pk == null){
			return null;
		}

		Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository.findOne(pk);
		if (tbs1002Gestioneptof != null){
			dto = new FileDTO();
			switch (tipoFileDaScaricare) {
			case VISUALIZZAZIONE_DOCUMENTO_CONVALIDATO :
				if (tbs1002Gestioneptof.getOggPdfVisCnv() != null){
					dto.setFile( new ByteArrayInputStream( tbs1002Gestioneptof.getOggPdfVisCnv()));
				}else{
					throw new RuntimeException("Tipo file non presente");
				}
				break;
			case PUBBLICAZIONE_PARZIALE :
				if (tbs1002Gestioneptof.getOggPdfPtoPar() != null){
					dto.setFile( new ByteArrayInputStream( tbs1002Gestioneptof.getOggPdfPtoPar()));
				}else{
					throw new RuntimeException("Tipo file non presente");
				}
				break;
			case PUBBLICAZIONE_COMPLETA :
				if (tbs1002Gestioneptof.getOggPdfPtoCom() != null){
					dto.setFile( new ByteArrayInputStream( tbs1002Gestioneptof.getOggPdfPtoCom()));
				}else{
					throw new RuntimeException("Tipo file non presente");
				}
				break;
			default:
				throw new RuntimeException("Tipo file non gestito");
			}
			dto.setFileName("PTOF_" + tbs1002Gestioneptof.getId().getCodScuUte() + "_" + tipoFileDaScaricare.name() + ".PDF");
			return dto;
		}


		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiComuniDTO getCruscottoFabbisogniPostiComuni(
			String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getCruscottoFabbisogniPostiComuni ... ");
		logger.debug("codiceRegione : " + codiceRegione);
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiComuniDTO out = new CruscottoFabbisogniPostiComuniDTO();

		if (gestioneCatalogoDTO != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiComuniItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (codiceRegione == null || codiceRegione.isEmpty()){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else {
				Mfg1012Regione mfg1012Regione = mfg1012RegioneRepository.findByCodiceRegione(codiceRegione);
				if (mfg1012Regione != null && mfg1012Regione.getDesReg() != null && !mfg1012Regione.getDesReg().isEmpty()){
					mfg1012Regiones.add(mfg1012Regione);
				}
			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				logger.debug("elaborazione codice regione : " + mfg1012Regione.getCodReg().trim());
				CruscottoFabbisogniPostiComuniItem item = new CruscottoFabbisogniPostiComuniItem();
				item.setRegione(mfg1012Regione.getDesReg());
				
				Tbs1043FabdecretointerminperPK pkTabA = new Tbs1043FabdecretointerminperPK();
				pkTabA.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabA.setCodRifDec("A");
				pkTabA.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043A = tbs1043FabdecretointerminperRepository.findOne(pkTabA);
				
				Tbs1043FabdecretointerminperPK pkTabB = new Tbs1043FabdecretointerminperPK();
				pkTabB.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabB.setCodRifDec("B");
				pkTabB.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043B = tbs1043FabdecretointerminperRepository.findOne(pkTabB);
				
				Tbs1043FabdecretointerminperPK pkTabC = new Tbs1043FabdecretointerminperPK();
				pkTabC.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabC.setCodRifDec("C");
				pkTabC.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043C = tbs1043FabdecretointerminperRepository.findOne(pkTabC);
				
				Tbs1043FabdecretointerminperPK pkTabD = new Tbs1043FabdecretointerminperPK();
				pkTabD.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabD.setCodRifDec("D");
				pkTabD.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043D = tbs1043FabdecretointerminperRepository.findOne(pkTabD);
				
				item.setDecretoInfanzia(tbs1043A != null ? tbs1043A.getNumPosCom() : 0L);
				item.setDecretoPrimaria(tbs1043B != null ? tbs1043B.getNumPosCom() : 0L);
				item.setDecretoIGrado(tbs1043C != null ? tbs1043C.getNumPosCom() : 0L);
				item.setDecretoIIGrado(tbs1043D != null ? tbs1043D.getNumPosCom() : 0L);
				
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
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiSostegnoDTO getCruscottoFabbisogniPostiSostegno(
			String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getCruscottoFabbisogniPostiSostegno ... ");
		logger.debug("codiceRegione : " + codiceRegione);
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiSostegnoDTO out = new CruscottoFabbisogniPostiSostegnoDTO();

		if (gestioneCatalogoDTO != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiSostegnoItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (codiceRegione == null || codiceRegione.isEmpty()){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else {
				Mfg1012Regione mfg1012Regione = mfg1012RegioneRepository.findByCodiceRegione(codiceRegione);
				if (mfg1012Regione != null && mfg1012Regione.getDesReg() != null && !mfg1012Regione.getDesReg().isEmpty()){
					mfg1012Regiones.add(mfg1012Regione);
				}
			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				CruscottoFabbisogniPostiSostegnoItem item = new CruscottoFabbisogniPostiSostegnoItem();
				item.setRegione(mfg1012Regione.getDesReg());
				
				Tbs1043FabdecretointerminperPK pkTabE = new Tbs1043FabdecretointerminperPK();
				pkTabE.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabE.setCodRifDec("E");
				pkTabE.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				
				Tbs1043Fabdecretointerminper tbs1043Fabdecretointerminper = tbs1043FabdecretointerminperRepository.findOne(pkTabE);
				item.setDecretoOrganicoDiritto(tbs1043Fabdecretointerminper != null ? tbs1043Fabdecretointerminper.getNumPosSos() : 0L);
				item.setDecretoPotenziamentoPerSostegno(tbs1043Fabdecretointerminper != null ? tbs1043Fabdecretointerminper.getNumPosPtzSos() : 0L);
				
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
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoFabbisogniPostiDiPotenziamentoDTO getCruscottoFabbisogniPostiPotenziamento(
								String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getCruscottoFabbisogniPostiPotenziamento ... ");
		logger.debug("codiceRegione : " + codiceRegione);
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoFabbisogniPostiDiPotenziamentoDTO out = new CruscottoFabbisogniPostiDiPotenziamentoDTO();

		if (gestioneCatalogoDTO != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (codiceRegione == null || codiceRegione.isEmpty()){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else {
				Mfg1012Regione mfg1012Regione = mfg1012RegioneRepository.findByCodiceRegione(codiceRegione);
				if (mfg1012Regione != null && mfg1012Regione.getDesReg() != null && !mfg1012Regione.getDesReg().isEmpty()){
					mfg1012Regiones.add(mfg1012Regione);
				}
			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				logger.debug("elaborazione codice regione : " + mfg1012Regione.getCodReg().trim());
				CruscottoFabbisogniPostiDiPotenziamentoItem item = new CruscottoFabbisogniPostiDiPotenziamentoItem();
				item.setRegione(mfg1012Regione.getDesReg());
						
				Tbs1043FabdecretointerminperPK pkTabF = new Tbs1043FabdecretointerminperPK();
				pkTabF.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabF.setCodRifDec("F");
				pkTabF.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043F = tbs1043FabdecretointerminperRepository.findOne(pkTabF);
				
				item.setDecretoPrimaria(tbs1043F != null ? tbs1043F.getNumPosPtzPri() : 0L);
				item.setDecretoIGrado(tbs1043F != null ? tbs1043F.getNumPosPtzIgr() : 0L);
				item.setDecretoIIGrado(tbs1043F != null ? tbs1043F.getNumPosPtzSgr() : 0L);
				
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
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO getCruscottoFabbisogniTotaleOrganica(
								String codiceRegione, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in getCruscottoFabbisogniTotaleOrganica ... ");
		logger.debug("codiceRegione : " + codiceRegione);
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,ToStringStyle.MULTI_LINE_STYLE));
		
		CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO out = new CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO();

		if (gestioneCatalogoDTO != null){
			
			out.setData(CommonsUtility.formatIt(new Date()));
			out.setGestioneCatalogoDTO(gestioneCatalogoDTO);
			
			LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem> items = new LinkedList<>();
			out.setItems(items);
			
			List<Mfg1012Regione> mfg1012Regiones = new ArrayList<>();
			if (codiceRegione == null || codiceRegione.isEmpty()){

				mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
				

			}else {
				Mfg1012Regione mfg1012Regione = mfg1012RegioneRepository.findByCodiceRegione(codiceRegione);
				if (mfg1012Regione != null && mfg1012Regione.getDesReg() != null && !mfg1012Regione.getDesReg().isEmpty()){
					mfg1012Regiones.add(mfg1012Regione);
				}
			}

			for (Mfg1012Regione mfg1012Regione : mfg1012Regiones){
				
				logger.debug("elaborazione codice regione : " + mfg1012Regione.getCodReg().trim());
				CruscottoRiepilogoDotazioniOrganicheAutonomiaItem item = new CruscottoRiepilogoDotazioniOrganicheAutonomiaItem();
				item.setRegione(mfg1012Regione.getDesReg());
						
				Tbs1043FabdecretointerminperPK pkTabG = new Tbs1043FabdecretointerminperPK();
				pkTabG.setCodReg(mfg1012Regione.getCodReg().trim());
				pkTabG.setCodRifDec("G");
				pkTabG.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
				Tbs1043Fabdecretointerminper tbs1043G = tbs1043FabdecretointerminperRepository.findOne(pkTabG);
				
				item.setDecretoPostiComuni(tbs1043G != null ? tbs1043G.getNumTotPosCom() : 0L);
				item.setDecretoPostiSostegno(tbs1043G != null ? tbs1043G.getNumTotPosSos() : 0L);
				item.setDecretoPostiPotenziamento(tbs1043G != null ? tbs1043G.getNumTotPosPtz() : 0L);
				
				CountEntity fabbisogniPostiComuni = countEntityRepository.findFabbisogniPostiComuni(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), null);
				CountEntity fabbisogniPostiSostegno = countEntityRepository.findFabbisogniPostiSostegno(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim());
				CountEntity fabbisogniPostiPotenziamentoAlSostegno = countEntityRepository.findFabbisogniPostiPotenziamentoAlSostegno(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim());
				CountEntity fabbisogniPostiPotenziamento = countEntityRepository.findFabbisogniPostiPotenziamento(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento(), mfg1012Regione.getCodReg().trim(), null);
				Long fabbisognoComuni = null;
				Long fabbisognoSostegno = null;
				Long fabbisognoPotenziamento = null;
				
				switch (gestioneCatalogoDTO.getNumeroVersioneDocumento().intValue()){
				case 0:
					fabbisognoComuni = fabbisogniPostiComuni.getNumPosPri();
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosPri() + fabbisogniPostiPotenziamentoAlSostegno.getNumPosPri();
					fabbisognoPotenziamento = fabbisogniPostiPotenziamento.getNumPosPri();
					break;
				case 1:
					fabbisognoComuni = fabbisogniPostiComuni.getNumPosSec();
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosSec() + fabbisogniPostiPotenziamentoAlSostegno.getNumPosSec();
					fabbisognoPotenziamento = fabbisogniPostiPotenziamento.getNumPosSec();
					break;	
				case 2:
					fabbisognoComuni = fabbisogniPostiComuni.getNumPosTer();
					fabbisognoSostegno = fabbisogniPostiSostegno.getNumPosTer() + fabbisogniPostiPotenziamentoAlSostegno.getNumPosTer();
					fabbisognoPotenziamento = fabbisogniPostiPotenziamento.getNumPosTer();
					break;
				}
				
				item.setFabbisognoPostiComuni(fabbisognoComuni);
				item.setFabbisognoPostiSostegno(fabbisognoSostegno);
				item.setFabbisognoPostiPotenziamento(fabbisognoPotenziamento);
				
				items.add(item);
			}
			
		}
		
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ResponsePageDTO<FabbisognoDTO> getConsultazionFabbisogno(RicercaConsultazionePtofDTO pagefilter) {

		logger.debug("in getConsultazionFabbisogno ... ");
		ConsultazioneFabbisognoFiltroDTO filter = pagefilter.getConsultazioneFabbisogno();
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

		Page<Tbs1002Gestioneptof> tbs1002Gestioneptofs = null;
		
/*		if (filter.getTipologiaScuola() == null || filter.getTipologiaScuola().isEmpty()){
			
			tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findByFilter(filter.getProgresivoDocumento(), 
																			  filter.getRegione()!=null?filter.getRegione().trim():null,
																			  filter.getProvincia(),
																			  filter.getComune(),
																			  filter.getCodiceMeccanografico(),
																			  TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																			  null,
																			  pageRequest);
		}else if (filter.getTipologiaScuola().equals("AA")){

			tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findInfanziaByFilter(filter.getProgresivoDocumento(), 
																					filter.getRegione()!=null?filter.getRegione().trim():null,
																					filter.getProvincia(),
																					filter.getComune(),
																					filter.getCodiceMeccanografico(),
																					TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																					null,
																					pageRequest);			
		}else if (filter.getTipologiaScuola().equals("EE")){

			tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findPrimariaByFilter(filter.getProgresivoDocumento(), 
																					filter.getRegione()!=null?filter.getRegione().trim():null,
																					filter.getProvincia(),
																					filter.getComune(),
																					filter.getCodiceMeccanografico(),
																					TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																					null,
																					pageRequest);			
		}else if (filter.getTipologiaScuola().equals("MM")){

			tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findIGradoByFilter(filter.getProgresivoDocumento(), 
																					filter.getRegione()!=null?filter.getRegione().trim():null,
																					filter.getProvincia(),
																					filter.getComune(),
																					filter.getCodiceMeccanografico(),
																					TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																					null,
																					pageRequest);			
		}else if (filter.getTipologiaScuola().equals("SS")){

			tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findIIGradoByFilter(filter.getProgresivoDocumento(), 
																					filter.getRegione()!=null?filter.getRegione().trim():null,
																					filter.getProvincia(),
																					filter.getComune(),
																					filter.getCodiceMeccanografico(),
																					TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																					null,
																					pageRequest);			
		}*/
		
		tbs1002Gestioneptofs = tbs1002GestioneptofRepository.findByFilter(filter.getProgresivoDocumento(), 
																		  filter.getRegione()!=null?filter.getRegione().trim():null,
																		  filter.getProvincia(),
																		  filter.getComune(),
																		  filter.getCodiceMeccanografico(),
																		  TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code(),
																		  null,
																		  pageRequest);

		logger.debug(ReflectionToStringBuilder.toString(tbs1002Gestioneptofs,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("tbs1002Gestioneptofs.getTotalPages() : " + tbs1002Gestioneptofs.getTotalPages());
		logger.debug("tbs1002Gestioneptofs.getTotalElements() : " + tbs1002Gestioneptofs.getTotalElements());
		logger.debug("tbs1002Gestioneptofs.getNumber() : " + tbs1002Gestioneptofs.getNumber());

		ResponsePageDTO<FabbisognoDTO> out = new ResponsePageDTO<>();
		out.setPagineTotali(tbs1002Gestioneptofs.getTotalPages());
		out.setRigheTotali( ( new Long ( tbs1002Gestioneptofs.getTotalElements() ) ).intValue() );
		out.setPaginaCorrente(tbs1002Gestioneptofs.getNumber()+1);
		out.setSort( pagefilter.getSort() );

		LinkedList<FabbisognoDTO> items = new LinkedList<FabbisognoDTO>();

		for (Tbs1002Gestioneptof tbs1002Gestioneptof : tbs1002Gestioneptofs){
			FabbisognoDTO dto = DtoFactory.getFabbisognoDTO(tbs1002Gestioneptof);
			items.add(dto);
		}

		ResponseDTO<LinkedList<FabbisognoDTO>> out2= new  ResponseDTO<LinkedList<FabbisognoDTO>>();
		out2.setResult(items);
		out.setItems(out2);

		return out;		

	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public FabbisognoPostiComuniDTO  getFabbisognoPostiComuni(String key) {

		logger.debug("in getFabbisognoPostiComuni ... " + key);
		
		FabbisognoPostiComuniDTO out = new FabbisognoPostiComuniDTO();
		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(key);
		
		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(pk.getPrgGesCatDoc());
		int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
		String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
		String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
		String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
		
		out.setLabelAnno0(labelAnno0);
		out.setLabelAnno1(labelAnno1);
		out.setLabelAnno2(labelAnno2);
		LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIGrado = new LinkedList<>();
		LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIIGrado = new LinkedList<>();
		out.setFabbisognoPostiComuniIGrado(fabbisognoPostiComuniIGrado);
		out.setFabbisognoPostiComuniIIGrado(fabbisognoPostiComuniIIGrado);
		
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.
				findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE.code()), pk.getCodScuUte(), pk.getPrgGesCatDoc(), pk.getPerAnnSco());
		
		if (tbs1004Datisezionesottosez != null){
			List<Tbs1037Fabpostocomune> tbs1037Fabpostocomunes = tbs1004Datisezionesottosez.getTbs1037Fabpostocomunes();
			if (tbs1037Fabpostocomunes != null){
				logger.debug("size fabbisogni : " + tbs1037Fabpostocomunes.size());
				for (Tbs1037Fabpostocomune tbs1037Fabpostocomune : tbs1037Fabpostocomunes){
					FabbisognoPostiComuniItem item = new FabbisognoPostiComuniItem();
					item.setMotivazione(tbs1037Fabpostocomune.getDesMot());
					item.setPostiAnnoZero(toLong(tbs1037Fabpostocomune.getNumPosPri()));
					item.setPostiAnnoUno(toLong(tbs1037Fabpostocomune.getNumPosSec()));
					item.setPostiAnnoDue(toLong(tbs1037Fabpostocomune.getNumPosTer()));
					
					if (tbs1037Fabpostocomune.getCodOrdScu().equals("AA")){
						out.setFabbisognoPostiComuniInfanzia(item);
					}else if (tbs1037Fabpostocomune.getCodOrdScu().equals("EE")){
						out.setFabbisognoPostiComuniPrimaria(item);
					}else if (tbs1037Fabpostocomune.getCodOrdScu().equals("MM")){
						fabbisognoPostiComuniIGrado.add(item);
						item.setClasseDiConcorso(tbs1037Fabpostocomune.getDesClc());
					}else if (tbs1037Fabpostocomune.getCodOrdScu().equals("SS")){
						fabbisognoPostiComuniIIGrado.add(item);
						item.setClasseDiConcorso(tbs1037Fabpostocomune.getDesClc());
					}
				}

			}
		}
		return out;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public FabbisognoPostiSostegnoDTO getPostiSostegno(String key) {

		logger.debug("in getPostiSostegno ... " + key);
		
		FabbisognoPostiSostegnoDTO out = new FabbisognoPostiSostegnoDTO();
		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(key);
		
		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(pk.getPrgGesCatDoc());
		int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
		String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
		String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
		String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
		
		out.setLabelAnno0(labelAnno0);
		out.setLabelAnno1(labelAnno1);
		out.setLabelAnno2(labelAnno2);
		
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.
				findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO.code()), pk.getCodScuUte(), pk.getPrgGesCatDoc(), pk.getPerAnnSco());
		
		if (tbs1004Datisezionesottosez != null){
			List<Tbs1039Fabpostosostegno> tbs1039Fabpostosostegnos = tbs1004Datisezionesottosez.getTbs1039Fabpostosostegnos();
			if (tbs1039Fabpostosostegnos != null){
				logger.debug("size fabbisogni : " + tbs1039Fabpostosostegnos.size());
				for (Tbs1039Fabpostosostegno tbs1039Fabpostosostegno : tbs1039Fabpostosostegnos){

					if (tbs1039Fabpostosostegno.getCodOrdScu().equals("AA")){
						
						FabbisognoPostiSostegnoItem item = new FabbisognoPostiSostegnoItem();
						item.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
						item.setPostiSostegnoUditoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiPri()));
						item.setPostiSostegnoUditoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiSec()));
						item.setPostiSostegnoUditoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiTer()));
						item.setPostiSostegnoVistaPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisPri()));
						item.setPostiSostegnoVistaSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisSec()));
						item.setPostiSostegnoVistaTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisTer()));
						item.setPostiSostegnoPsicofisicoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiPri()));
						item.setPostiSostegnoPsicofisicoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiSec()));
						item.setPostiSostegnoPsicofisicoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiTer()));
						out.setFabbisognoPostiSostegnoInfanzia(item);
						
					}else if (tbs1039Fabpostosostegno.getCodOrdScu().equals("EE")){
						
						FabbisognoPostiSostegnoItem item = new FabbisognoPostiSostegnoItem();
						item.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
						item.setPostiSostegnoUditoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiPri()));
						item.setPostiSostegnoUditoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiSec()));
						item.setPostiSostegnoUditoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiTer()));
						item.setPostiSostegnoVistaPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisPri()));
						item.setPostiSostegnoVistaSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisSec()));
						item.setPostiSostegnoVistaTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisTer()));
						item.setPostiSostegnoPsicofisicoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiPri()));
						item.setPostiSostegnoPsicofisicoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiSec()));
						item.setPostiSostegnoPsicofisicoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiTer()));
						out.setFabbisognoPostiSostegnoPrimaria(item);

					}else if (tbs1039Fabpostosostegno.getCodOrdScu().equals("MM")){
						
						FabbisognoPostiSostegnoItem item = new FabbisognoPostiSostegnoItem();
						item.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
						item.setPostiSostegnoUditoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiPri()));
						item.setPostiSostegnoUditoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiSec()));
						item.setPostiSostegnoUditoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiTer()));
						item.setPostiSostegnoVistaPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisPri()));
						item.setPostiSostegnoVistaSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisSec()));
						item.setPostiSostegnoVistaTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisTer()));
						item.setPostiSostegnoPsicofisicoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiPri()));
						item.setPostiSostegnoPsicofisicoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiSec()));
						item.setPostiSostegnoPsicofisicoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiTer()));
						out.setFabbisognoPostiSostegnoIGrado(item);
						
					}else if (tbs1039Fabpostosostegno.getCodOrdScu().equals("SS")){
						
						FabbisognoPostiSostegnoIIGradoItem item = new FabbisognoPostiSostegnoIIGradoItem();
						item.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
						item.setPostiSostegnoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgPri()));
						item.setPostiSostegnoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgSec()));
						item.setPostiSostegnoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgTer()));
						out.setFabbisognoPostiSostegnoIIGrado(item);
						
					}

				}

			}
		}
		
		return out;
		
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public FabbisognoPostiPotenziamentoDTO getPostiPotenziamento(String key) {

		logger.debug("in getPostiPotenziamento ... " + key);
		
		FabbisognoPostiPotenziamentoDTO out = new FabbisognoPostiPotenziamentoDTO();
		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(key);
		
		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(pk.getPrgGesCatDoc());
		int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
		String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
		String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
		String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
		
		out.setLabelAnno0(labelAnno0);
		out.setLabelAnno1(labelAnno1);
		out.setLabelAnno2(labelAnno2);
		LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIGrado = new LinkedList<>();
		LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIIGrado = new LinkedList<>();
		out.setFabbisognoPostiPotenziamentoIGrado(fabbisognoPostiPotenziamentoIGrado);
		out.setFabbisognoPostiPotenziamentoIIGrado(fabbisognoPostiPotenziamentoIIGrado);
		
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.
				findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO.code()), pk.getCodScuUte(), pk.getPrgGesCatDoc(), pk.getPerAnnSco());
		
		if (tbs1004Datisezionesottosez != null){
			List<Tbs1040Fabpostopotenziamento> tbs1040Fabpostopotenziamentos = tbs1004Datisezionesottosez.getTbs1040Fabpostopotenziamentos();
			if (tbs1040Fabpostopotenziamentos != null){
				logger.debug("size fabbisogni : " + tbs1040Fabpostopotenziamentos.size());
				for (Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento : tbs1040Fabpostopotenziamentos){
					
					if (tbs1040Fabpostopotenziamento.getCodOrdScu().equals("EE")){
						
						FabbisognoPostiPotenziamentoPrimariaItem item = new FabbisognoPostiPotenziamentoPrimariaItem();
						item.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
						item.setPostiPotenziamentoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComPri()));
						item.setPostiPotenziamentoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComSec()));
						item.setPostiPotenziamentoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComTer()));
						item.setPostiPotenziamentoDiSostegnoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosPri()));
						item.setPostiPotenziamentoDiSostegnoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosSec()));
						item.setPostiPotenziamentoDiSostegnoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosTer()));
						out.setFabbisognoPostiPotenziamentoPrimaria(item);
						
					}else if (tbs1040Fabpostopotenziamento.getCodOrdScu().equals("MM")){
						
						if (tbs1040Fabpostopotenziamento.getDesClc().equals("Sostegno")){
							
							FabbisognoPostiPotenziamentoDiSostegnoItem item = new FabbisognoPostiPotenziamentoDiSostegnoItem();
							item.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
							item.setPostiPotenziamentoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosPri()));
							item.setPostiPotenziamentoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosSec()));
							item.setPostiPotenziamentoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosTer()));
							out.setFabbisognoPostiPotenziamentoDiSostegnoIGrado(item);
							
						}else{
							
							FabbisognoPostiPotenziamentoItem item = new FabbisognoPostiPotenziamentoItem();
							item.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
							item.setPostiPotenziamentoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComPri()));
							item.setPostiPotenziamentoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComSec()));
							item.setPostiPotenziamentoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComTer()));
							item.setClasseDiConcorso(tbs1040Fabpostopotenziamento.getDesClc());
							fabbisognoPostiPotenziamentoIGrado.add(item);
						}
						
					}else if (tbs1040Fabpostopotenziamento.getCodOrdScu().equals("SS")){

						if (tbs1040Fabpostopotenziamento.getDesClc().equals("Sostegno")){

							FabbisognoPostiPotenziamentoDiSostegnoItem item = new FabbisognoPostiPotenziamentoDiSostegnoItem();
							item.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
							item.setPostiPotenziamentoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosPri()));
							item.setPostiPotenziamentoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosSec()));
							item.setPostiPotenziamentoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosTer()));
							out.setFabbisognoPostiPotenziamentoDiSostegnoIIGrado(item);

						}else{

							FabbisognoPostiPotenziamentoItem item = new FabbisognoPostiPotenziamentoItem();
							item.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
							item.setPostiPotenziamentoPrimoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComPri()));
							item.setPostiPotenziamentoSecondoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComSec()));
							item.setPostiPotenziamentoTerzoTriennio(toInteger(tbs1040Fabpostopotenziamento.getNumPosComTer()));
							item.setClasseDiConcorso(tbs1040Fabpostopotenziamento.getDesClc());
							fabbisognoPostiPotenziamentoIIGrado.add(item);
						}

					}
				}

			}
		}
		return out;
	}


	private Integer toInteger(BigDecimal in){
		if (in != null){
			return Integer.valueOf(in.intValue());
		}else{
			return Integer.valueOf(0);
		}
	}

	private Long toLong(BigDecimal in){
		if (in != null){
			return Long.valueOf(in.intValue());
		}else{
			return Long.valueOf(0);
		}
	}


	@Override
	public IstitutoScolasticoDTO getScuolaByKeyDocumento(String key) {
		
		logger.debug("in getScuolaByKeyDocumento ... " + key);
		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(key);
		Scuola scuola = scuolaRepository.findPlessoByCodiceMeccanograficoAndAnnoScolastico(pk.getCodScuUte(), pk.getPerAnnSco().intValue());
		return DtoFactory.getIstitutoScolasticoDTO(scuola);
		
	}

}
