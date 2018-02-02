package it.istruzione.ptof.service.impl;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.ptof.TipologicaDTO;
import it.istruzione.ptof.constant.AppConstant;
import it.istruzione.ptof.helper.PoliHelper;
import it.istruzione.ptof.model.entity.Mfg1012Regione;
import it.istruzione.ptof.model.entity.Mfg1014Comune;
import it.istruzione.ptof.model.entity.Mfg1029Provnuoist;
import it.istruzione.ptof.model.entity.Tbs1008Tipoobiettivo;
import it.istruzione.ptof.model.entity.Tbs1012Tipopriorita;
import it.istruzione.ptof.model.entity.Tbs1019Tipoambito;
import it.istruzione.ptof.model.entity.Tbs1020Classifprogetambito;
import it.istruzione.ptof.model.entity.Tbs1025Tiporuolo;
import it.istruzione.ptof.model.entity.business.FabbisogniScuola;
import it.istruzione.ptof.model.repository.Mfg1012RegioneRepository;
import it.istruzione.ptof.model.repository.Mfg1029ProvnuoistRepository;
import it.istruzione.ptof.model.repository.Tbs1008TipoobiettivoRepository;
import it.istruzione.ptof.model.repository.Tbs1012TipoprioritaRepository;
import it.istruzione.ptof.model.repository.Tbs1019TipoambitoRepository;
import it.istruzione.ptof.model.repository.Tbs1025TiporuoloRepository;
import it.istruzione.ptof.model.repository.business.FabbisogniScuolaRepository;
import it.istruzione.ptof.service.TipologicheService;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipologicheServiceImpl extends BaseServiceImpl implements TipologicheService{
	
	private static Logger logger = Logger.getLogger(TipologicheServiceImpl.class);
	
	@Autowired
	private Tbs1012TipoprioritaRepository tbs1012TipoprioritaRepository;
	
	@Autowired
	private Tbs1008TipoobiettivoRepository tbs1008TipoobiettivoRepository;
	
	@Autowired
	private Tbs1019TipoambitoRepository tbs1019TipoambitoRepository;
	
	@Autowired
	private Tbs1025TiporuoloRepository tbs1025TiporuoloRepository;
	
	@Autowired
	private FabbisogniScuolaRepository fabbisogniScuolaRepository;
	
	@Autowired
	private Mfg1012RegioneRepository mfg1012RegioneRepository;
	
	@Autowired
	private Mfg1029ProvnuoistRepository mfg1029ProvnuoistRepository;


	@Override
	//@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<BeanValueDTO> getTipologica(TipologicaDTO tipologica, IstitutoScolasticoDTO istitutoScolastico) {
		
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		
		switch (tipologica.getTipo()) {
		case PRIORITA:
			LinkedList<Tbs1012Tipopriorita> tbs1012Tipoprioritas = tbs1012TipoprioritaRepository.findByOrderByCodTipPriAsc();
			for (Tbs1012Tipopriorita elem : tbs1012Tipoprioritas){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesTipPri());
				dto.setValue(elem.getCodTipPri());
				out.add(dto);
			}
			break;

		case OBBIETTIVI:
			LinkedList<Tbs1008Tipoobiettivo> tbs1008Tipoobiettivos = tbs1008TipoobiettivoRepository.findByOrderByPrgTipObiAsc();
			for (Tbs1008Tipoobiettivo elem : tbs1008Tipoobiettivos){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesTipObi());
				dto.setValue(elem.getPrgTipObi().toString());
				out.add(dto);
			}
			break;
		
		case AMBITI:
			LinkedList<Tbs1019Tipoambito> tbs1019Tipoambitos = tbs1019TipoambitoRepository.findByFlgTipAmbOrderByCodTipAmbAsc("P");
			for (Tbs1019Tipoambito elem : tbs1019Tipoambitos){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesTipAmb());
				dto.setValue(elem.getCodTipAmb());
				out.add(dto);
			}
			break;
		
		case TIPOPROGETTI:
			
			Tbs1019Tipoambito tbs1019Tipoambito = tbs1019TipoambitoRepository.findOne(tipologica.getItemToFilter().getValue());
			for (Tbs1020Classifprogetambito elem : tbs1019Tipoambito.getTbs1020Classifprogetambitos()){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesDenPgt());
				dto.setValue(elem.getId().getPrgPgtAmb().toString());
				out.add(dto);
			}
			break;
		
		case RUOLI:
			LinkedList<Tbs1025Tiporuolo> tbs1025Tiporuolos = tbs1025TiporuoloRepository.findByOrderByCodTipRuoAsc();
			for (Tbs1025Tiporuolo elem : tbs1025Tiporuolos){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesTipRuo());
				dto.setValue(elem.getCodTipRuo());
				out.add(dto);
			}
			break;
		
		case AMBITI_FORMATIVI:
			LinkedList<Tbs1019Tipoambito> tbs1019TipoambitoFs = tbs1019TipoambitoRepository.findByFlgTipAmbOrderByCodTipAmbAsc("F");
			for (Tbs1019Tipoambito elem : tbs1019TipoambitoFs){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesTipAmb());
				dto.setValue(elem.getCodTipAmb());
				out.add(dto);
			}
			break;
		
		case TIPOPROGETTI_SCUOLA:
			
			LinkedList<FabbisogniScuola> tipoProgettiScuolas = fabbisogniScuolaRepository.findTipoProgettiScuola(istitutoScolastico.getCodiceMecIstPrin());
			for (FabbisogniScuola elem : tipoProgettiScuolas){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDes());
				dto.setValue(elem.getKey());
				out.add(dto);
			}
			break;
		
		case DENOMINAZIONE_PROGETTI_SCUOLA:
			
			String tipoProgettoScuola = tipologica.getItemToFilter().getValue().substring(0,1);
			String codTipoProgettoScuola = tipologica.getItemToFilter().getValue().substring(1);
			logger.debug(tipoProgettoScuola);
			logger.debug(codTipoProgettoScuola);
			if (tipoProgettoScuola.equals("P")){
				LinkedList<FabbisogniScuola> progettiScuolas = fabbisogniScuolaRepository.findProgettiScuola(istitutoScolastico.getCodiceMecIstPrin(),Long.valueOf(codTipoProgettoScuola));
				for (FabbisogniScuola elem : progettiScuolas){
					BeanValueDTO dto = new BeanValueDTO();
					dto.setLabel(elem.getDes());
					dto.setValue(elem.getKey());
					out.add(dto);
				}
			}else if (tipoProgettoScuola.equals("A")){
				LinkedList<FabbisogniScuola> progettiScuolas = fabbisogniScuolaRepository.findAltriProgettiScuola(istitutoScolastico.getCodiceMecIstPrin(),codTipoProgettoScuola);
				for (FabbisogniScuola elem : progettiScuolas){
					BeanValueDTO dto = new BeanValueDTO();
					dto.setLabel(elem.getDes());
					dto.setValue(elem.getKey());
					out.add(dto);
				}
			}
			break;
		
		case AMBITI_FORMATIVI_SCUOLA:
			
			LinkedList<FabbisogniScuola> ambitiFormativiScuola = fabbisogniScuolaRepository.findAmbitiFormativiScuola(istitutoScolastico.getCodiceMecIstPrin());
			for (FabbisogniScuola elem : ambitiFormativiScuola){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDes());
				dto.setValue(elem.getKey());
				out.add(dto);
			}
			break;
		
		case PERCORSI_FORMATIVI:
			
			LinkedList<FabbisogniScuola> percorsiFormativiScuola = fabbisogniScuolaRepository.findPercorsiFormativiScuola(istitutoScolastico.getCodiceMecIstPrin(),tipologica.getItemToFilter().getValue());
			for (FabbisogniScuola elem : percorsiFormativiScuola){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDes());
				dto.setValue(elem.getKey());
				out.add(dto);
			}
			break;

		default:
			throw new RuntimeException("Tipo componente non gestito");
		}

		
		
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getRegioni(SidiContesto contesto) {
		
		logger.debug("in getRegioni");
		logger.debug(ReflectionToStringBuilder.toString(contesto,ToStringStyle.MULTI_LINE_STYLE));
		
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		
		if (contesto.getTipo().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE)
				|| contesto.getTipo().equals(AppConstant.TIPO_CONTESTO_NAZIONE)
				){
			BeanValueDTO empty = new BeanValueDTO();
			empty.setLabel("Tutte le regioni");
			empty.setValue("");
			out.add(empty);
			List<Mfg1012Regione> mfg1012Regiones = mfg1012RegioneRepository.findAllRegioniItaliane();
			for (Mfg1012Regione elem : mfg1012Regiones){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesReg());
				dto.setValue(elem.getCodReg());
				out.add(dto);
			}
		}else if (contesto.getTipo().equals(AppConstant.TIPO_CONTESTO_REGIONALE)){
			List<String> regioni = PoliHelper.poloRegionaleToCodiceRegioneList(contesto.getCodice());
			for (String codiceRegione : regioni){
				Mfg1012Regione mfg1012Regione = mfg1012RegioneRepository.findByCodiceRegione(codiceRegione);
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(mfg1012Regione.getDesReg());
				dto.setValue(mfg1012Regione.getCodReg());
				out.add(dto);
			}
		}
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getProvince(String codiceRegione) {
		
		logger.debug("in getProvince");
		logger.debug(codiceRegione);

		LinkedList<BeanValueDTO> out = new LinkedList<>();
		LinkedList<Mfg1029Provnuoist> province = mfg1029ProvnuoistRepository.findByCodiceRegione(codiceRegione.trim());
		if (province != null){
			for (Mfg1029Provnuoist elem : province){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesPrv());
				dto.setValue(elem.getCodPrv());
				out.add(dto);
			}
		}
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getComuni(String codiceProvincia) {
		
		logger.debug("in getComuni");
		logger.debug(codiceProvincia);
		
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		Mfg1029Provnuoist mfg1029Provnuoist = mfg1029ProvnuoistRepository.findOne(codiceProvincia);
		if (mfg1029Provnuoist != null){
			for (Mfg1014Comune elem : mfg1029Provnuoist.getMfg1014Comunes()){
				BeanValueDTO dto = new BeanValueDTO();
				dto.setLabel(elem.getDesCom());
				dto.setValue(elem.getCodCom());
				out.add(dto);
			}
		}
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getStatoPtof() {
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		BeanValueDTO statoPubblicatoParzialmente = new BeanValueDTO();
		statoPubblicatoParzialmente.setLabel(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.getBean().getLabel());
		statoPubblicatoParzialmente.setValue(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.getBean().getValue());
		out.add(statoPubblicatoParzialmente);
		BeanValueDTO statoPubblicatoTotalmente = new BeanValueDTO();
		statoPubblicatoTotalmente.setLabel(TIPO_STATO_DOC.PUBBLICATO_COMPLETO.getBean().getLabel());
		statoPubblicatoTotalmente.setValue(TIPO_STATO_DOC.PUBBLICATO_COMPLETO.getBean().getValue());
		out.add(statoPubblicatoTotalmente);
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getTipologiaScuolaPtof() {
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		BeanValueDTO infanzia = new BeanValueDTO();
		infanzia.setLabel("Infanzia");
		infanzia.setValue("AA");
		out.add(infanzia);
		BeanValueDTO primaria = new BeanValueDTO();
		primaria.setLabel("Primaria");
		primaria.setValue("EE");
		out.add(primaria);
		BeanValueDTO primogrado = new BeanValueDTO();
		primogrado.setLabel("Secondaria di I grado");
		primogrado.setValue("MM");
		out.add(primogrado);
		BeanValueDTO secondogrado = new BeanValueDTO();
		secondogrado.setLabel("Secondaria di II grado");
		secondogrado.setValue("SS");
		out.add(secondogrado);
		return out;
	}

}
