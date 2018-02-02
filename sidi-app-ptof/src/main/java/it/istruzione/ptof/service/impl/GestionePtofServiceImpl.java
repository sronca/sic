package it.istruzione.ptof.service.impl;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.ConvPotenzFormativoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoSostegnoMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.OrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.PianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.ProgrammazioneFormDTO;
import it.istruzione.ptof.beans.documenti.PromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.SezioneBaseDTO;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.StrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltreIniziativeDidaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltriProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteComboBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteConvPotenzFormativoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDatiFinaliScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMonitoraggioPianificazioneDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMultiBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariAltriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaAltriRuoliDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAmmDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAusDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormDocDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormTecDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteStrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.model.entity.Tbs1005Allegato;
import it.istruzione.ptof.model.entity.Tbs1006Tipostato;
import it.istruzione.ptof.model.entity.Tbs1007Testo;
import it.istruzione.ptof.model.entity.Tbs1008Tipoobiettivo;
import it.istruzione.ptof.model.entity.Tbs1009Obiettivo;
import it.istruzione.ptof.model.entity.Tbs1012Tipopriorita;
import it.istruzione.ptof.model.entity.Tbs1013Altrobiettivo;
import it.istruzione.ptof.model.entity.Tbs1014Componentesezione;
import it.istruzione.ptof.model.entity.Tbs1019Tipoambito;
import it.istruzione.ptof.model.entity.Tbs1020Classifprogetambito;
import it.istruzione.ptof.model.entity.Tbs1020ClassifprogetambitoPK;
import it.istruzione.ptof.model.entity.Tbs1021Ambitoprogettoptof;
import it.istruzione.ptof.model.entity.Tbs1022Ambitoprogettoaltro;
import it.istruzione.ptof.model.entity.Tbs1023Altreinizididattiche;
import it.istruzione.ptof.model.entity.Tbs1024Attivitasostegnoptof;
import it.istruzione.ptof.model.entity.Tbs1025Tiporuolo;
import it.istruzione.ptof.model.entity.Tbs1026Organrisorseptof;
import it.istruzione.ptof.model.entity.Tbs1027Performazpersonale;
import it.istruzione.ptof.model.entity.Tbs1028Attrezzature;
import it.istruzione.ptof.model.entity.Tbs1029Dotazionemultimediale;
import it.istruzione.ptof.model.entity.Tbs1030Rapportoente;
import it.istruzione.ptof.model.entity.Tbs1031Convenzioneforma;
import it.istruzione.ptof.model.entity.Tbs1032Pianificattivita;
import it.istruzione.ptof.model.entity.Tbs1034Articoclasseinfanzia;
import it.istruzione.ptof.model.entity.Tbs1035Articoclasseprimaria;
import it.istruzione.ptof.model.entity.Tbs1036Articolaclasseigrado;
import it.istruzione.ptof.model.entity.Tbs1037Fabpostocomune;
import it.istruzione.ptof.model.entity.Tbs1038Articolaclasseiigrado;
import it.istruzione.ptof.model.entity.Tbs1039Fabpostosostegno;
import it.istruzione.ptof.model.entity.Tbs1040Fabpostopotenziamento;
import it.istruzione.ptof.model.entity.Tbs1041Fabfiguraprofessionale;
import it.istruzione.ptof.model.entity.Tbs1042Fabattrezmateriale;
import it.istruzione.ptof.model.entity.business.Documento;
import it.istruzione.ptof.model.entity.business.Sezione;
import it.istruzione.ptof.model.repository.Tbs1002GestioneptofRepository;
import it.istruzione.ptof.model.repository.Tbs1004DatisezionesottosezRepository;
import it.istruzione.ptof.model.repository.Tbs1005AllegatoRepository;
import it.istruzione.ptof.model.repository.Tbs1007TestoRepository;
import it.istruzione.ptof.model.repository.Tbs1009ObiettivoRepository;
import it.istruzione.ptof.model.repository.Tbs1010TipoaggregacontestoRepository;
import it.istruzione.ptof.model.repository.Tbs1013AltrobiettivoRepository;
import it.istruzione.ptof.model.repository.Tbs1014ComponentesezioneRepository;
import it.istruzione.ptof.model.repository.Tbs1015TipomulticulturalitaRepository;
import it.istruzione.ptof.model.repository.Tbs1017RavobiettivoRepository;
import it.istruzione.ptof.model.repository.Tbs1018RavprioritatraguardiRepository;
import it.istruzione.ptof.model.repository.Tbs1021AmbitoprogettoptofRepository;
import it.istruzione.ptof.model.repository.Tbs1022AmbitoprogettoaltroRepository;
import it.istruzione.ptof.model.repository.Tbs1023AltreinizididatticheRepository;
import it.istruzione.ptof.model.repository.Tbs1024AttivitasostegnoptofRepository;
import it.istruzione.ptof.model.repository.Tbs1026OrganrisorseptofRepository;
import it.istruzione.ptof.model.repository.Tbs1027PerformazpersonaleRepository;
import it.istruzione.ptof.model.repository.Tbs1028AttrezzatureRepository;
import it.istruzione.ptof.model.repository.Tbs1029DotazionemultimedialeRepository;
import it.istruzione.ptof.model.repository.Tbs1030RapportoenteRepository;
import it.istruzione.ptof.model.repository.Tbs1031ConvenzioneformaRepository;
import it.istruzione.ptof.model.repository.Tbs1032PianificattivitaRepository;
import it.istruzione.ptof.model.repository.Tbs1034ArticoclasseinfanziaRepository;
import it.istruzione.ptof.model.repository.Tbs1035ArticoclasseprimariaRepository;
import it.istruzione.ptof.model.repository.Tbs1036ArticolaclasseigradoRepository;
import it.istruzione.ptof.model.repository.Tbs1037FabpostocomuneRepository;
import it.istruzione.ptof.model.repository.Tbs1038ArticolaclasseiigradoRepository;
import it.istruzione.ptof.model.repository.Tbs1039FabpostosostegnoRepository;
import it.istruzione.ptof.model.repository.Tbs1040FabpostopotenziamentoRepository;
import it.istruzione.ptof.model.repository.Tbs1041FabfiguraprofessionaleRepository;
import it.istruzione.ptof.model.repository.Tbs1042FabattrezmaterialeRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiAARepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiEERepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIIRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIRepository;
import it.istruzione.ptof.model.repository.business.DocumentoRepository;
import it.istruzione.ptof.model.repository.business.DotazioniMultimedialiRepository;
import it.istruzione.ptof.model.repository.business.SezioneRepository;
import it.istruzione.ptof.model.repository.business.TipologiaScuolaRepository;
import it.istruzione.ptof.service.GestionePtofService;
import it.istruzione.ptof.service.HomeService;
import it.istruzione.ptof.validator.ConvalidaValidator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sronca
 * servizi per RF003– Gestione PTOF
 */

@Service
public class GestionePtofServiceImpl extends BaseServiceImpl implements GestionePtofService{
	
	@Autowired
	private UtilPtofServiceImpl utilPtofServiceImpl;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private SezioneRepository sezioneRepository;
	
	@Autowired
	private Tbs1002GestioneptofRepository tbs1002GestioneptofRepository;
	
	@Autowired
	private Tbs1004DatisezionesottosezRepository tbs1004DatisezionesottosezRepository;
	
	@Autowired
	private Tbs1007TestoRepository tbs1007TestoRepository;

	@Autowired
	private Tbs1005AllegatoRepository tbs1005AllegatoRepository;
	
	@Autowired
	private Tbs1014ComponentesezioneRepository tbs1014ComponentesezioneRepository;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private Tbs1010TipoaggregacontestoRepository tbs1010TipoaggregacontestoRepository;
	
	@Autowired
	private Tbs1015TipomulticulturalitaRepository tbs1015TipomulticulturalitaRepository;
	
	@Autowired
	private Tbs1009ObiettivoRepository tbs1009ObiettivoRepository;
	
	@Autowired
	private Tbs1013AltrobiettivoRepository tbs1013AltrobiettivoRepository;
	
	@Autowired
	private Tbs1021AmbitoprogettoptofRepository tbs1021AmbitoprogettoptofRepository;

	@Autowired
	private Tbs1022AmbitoprogettoaltroRepository tbs1022AmbitoprogettoaltroRepository;
	
	@Autowired
	private Tbs1023AltreinizididatticheRepository tbs1023AltreinizididatticheRepository;
	
	@Autowired
	private Tbs1024AttivitasostegnoptofRepository tbs1024AttivitasostegnoptofRepository;
	
	@Autowired
	private Tbs1017RavobiettivoRepository tbs1017RavobiettivoRepository;
	
	@Autowired
	private Tbs1018RavprioritatraguardiRepository tbs1018RavprioritatraguardiRepository;
	
	@Autowired
	private Tbs1026OrganrisorseptofRepository tbs1026OrganrisorseptofRepository;
	
	@Autowired
	private Tbs1027PerformazpersonaleRepository tbs1027PerformazpersonaleRepository;
	
	@Autowired
	private Tbs1028AttrezzatureRepository tbs1028AttrezzatureRepository;
	
	@Autowired
	private Tbs1029DotazionemultimedialeRepository tbs1029DotazionemultimedialeRepository;
	
	@Autowired
	private Tbs1030RapportoenteRepository tbs1030RapportoenteRepository;
	
	@Autowired
	private Tbs1031ConvenzioneformaRepository tbs1031ConvenzioneformaRepository;
	
	@Autowired
	private Tbs1032PianificattivitaRepository tbs1032PianificattivitaRepository;

	@Autowired
	private DotazioniMultimedialiRepository dotazioniMultimedialiRepository;
	
	@Autowired
	private Tbs1034ArticoclasseinfanziaRepository tbs1034ArticoclasseinfanziaRepository;
	
	@Autowired
	private Tbs1035ArticoclasseprimariaRepository tbs1035ArticoclasseprimariaRepository;
	
	@Autowired
	private Tbs1036ArticolaclasseigradoRepository tbs1036ArticolaclasseigradoRepository;
	
	@Autowired
	private Tbs1038ArticolaclasseiigradoRepository tbs1038ArticolaclasseiigradoRepository;
	
	@Autowired
	private ArticolazioneClassiAARepository articolazioneClassiAARepository;
	
	@Autowired
	private ArticolazioneClassiEERepository articolazioneClassiEERepository;

	@Autowired
	private ArticolazioneClassiMMIRepository articolazioneClassiMMIRepository;

	@Autowired
	private ArticolazioneClassiMMIIRepository articolazioneClassiMMIIRepository;
	
	@Autowired
	private TipologiaScuolaRepository tipologiaScuolaRepository;
	
	@Autowired
	private Tbs1037FabpostocomuneRepository tbs1037FabpostocomuneRepository;
	
	@Autowired
	private Tbs1039FabpostosostegnoRepository tbs1039FabpostosostegnoRepository;
	
	@Autowired
	private Tbs1040FabpostopotenziamentoRepository tbs1040FabpostopotenziamentoRepository;
	
	@Autowired
	private Tbs1041FabfiguraprofessionaleRepository tbs1041FabfiguraprofessionaleRepository;
	
	@Autowired
	private Tbs1042FabattrezmaterialeRepository tbs1042FabattrezmaterialeRepository;
	
	@Autowired
	private ConvalidaValidator convalidaValidatorChain;
	
	private static Logger logger = Logger.getLogger(GestionePtofServiceImpl.class);
	
	private final static String TIPO_CMP_TIPOLOGIA_DI_AGGREGAZIONE_CONTESTO = "CB00";
	private final static String TIPO_CMP_LIVELLO_DI_MULTICULTURALITA = "CB01";

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
    public DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento){
		
    	logger.debug("in loadDocumentoAnnoIncorso ... " + keyDocumento);
		String codScuUte = null;
		Long perAnnSco = null;
		Long prgGesCatDoc = null;
		
		if (keyDocumento != null && ! keyDocumento.isEmpty()){
			codScuUte = keyDocumento.substring(0,10);
			perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
			prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));
		}
		Documento documento = documentoRepository.getDocumentoByKey(codScuUte,perAnnSco,prgGesCatDoc);
		logger.debug(ReflectionToStringBuilder.toString(documento,ToStringStyle.MULTI_LINE_STYLE));
		
		return DtoFactory.getDocumentoAnnoIncorsoDTO(documento);
    }
    
    /**
     * usato per elevare il livello di sicurezza 
     * verifica che il documento appartenga alla scuola passata in input e  
     * in caso positivo ritorna il doc altrimenti null 
     * @param keyDocumento
     * @param istitutoScolasticoDTO
     * @return
     */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
    public DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento,IstitutoScolasticoDTO istitutoScolasticoDTO){
    	
		if (keyDocumento.substring(0,10).equals(istitutoScolasticoDTO.getCodiceMecIstPrin())){
			return this.loadDocumentoAnnoIncorso(keyDocumento);
		}else{
			return null;
		}
    }
    
    /**
     * Il metodo restituisce l'elenco delle sezioni e sottosezioni con lo stato di compilazione da parte della scuola
     * la chiave di ogni record è l'attributo PRG_DAT_PTF della tabella TBS1004_DATISEZIONESOTTOSEZ
     * l'accesso alla sotto sezione Alternanza Scuola Lavoro è consentito solo per le scuole secondarie di II grado
     * l'accesso alla sezione Piano Nazionale della Scuola Digitale è consentito solo se lo stato del documento è CONVALIDATO
     * @param GestionePtofDTO
     * @return LinkedList<SezioneDTO>
     */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
    public LinkedList<SezioneDTO> loadSezioni(GestionePtofDTO gestionePtofDTO){
		
		LinkedList<SezioneDTO> out = new LinkedList<>();
		String chiaveDocumento = gestionePtofDTO.getDocumentoAnnoIncorsoDTO().getKey();
		String codScuUte = null;
		Long perAnnSco = null;
		Long prgGesCatDoc = null;
		
		if (chiaveDocumento != null && ! chiaveDocumento.isEmpty()){
			codScuUte = chiaveDocumento.substring(0,10);
			perAnnSco = Long.valueOf(chiaveDocumento.substring(10,16));
			prgGesCatDoc = Long.valueOf(chiaveDocumento.substring(16));
		}
		
		Documento documento = documentoRepository.getDocumentoByKey(codScuUte,perAnnSco,prgGesCatDoc);
		TIPO_STATO_DOC statoDocumento = TIPO_STATO_DOC.getInstanceFromCode(documento.getCodSta());
		
		if (codScuUte.equals(gestionePtofDTO.getIstitutoScolastico().getCodiceMecIstPrin())){ //controllo di sicurezza
			
			LinkedList<Sezione> sezioni = sezioneRepository.findSezioniScuola(codScuUte, perAnnSco, prgGesCatDoc);
			for (Sezione sezione : sezioni){
				SezioneDTO dto = new SezioneDTO();
				dto.setKey(sezione.getChiave());
				dto.setCodice(sezione.getCodSezSotSez());
				dto.setNome(sezione.getDesSez());
				dto.setStatoSezione(TIPO_STATO_SEZIONE.getInstanceFromCode(sezione.getCodSta()));
				dto.setTipoSezione(TIPO_SEZIONE.getInstanceFromCode(sezione.getPrgSezSotSez().toString()));
				dto.setStatoDocumento(statoDocumento);
				if (dto.getTipoSezione() != null
						&& dto.getTipoSezione().equals(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE)
						&& statoDocumento.equals(TIPO_STATO_DOC.IN_COMPILAZIONE)){
					dto.setDisabled(Boolean.TRUE);
					dto.setDisabledMessage("La sezione PIANO NAZIONALE SCUOLA DIGITALE sarà accessibile solo dopo aver effettuato la convalida del PTOF.");
				}
				LinkedList<Sezione> sottoSezioni = sezioneRepository.findSottoSezioniScuola(codScuUte, perAnnSco, prgGesCatDoc, sezione.getPrgSezSotSez());
				if (!sottoSezioni.isEmpty()){
					LinkedList<SezioneDTO> sottoSezioniDto = new LinkedList<>();
					for (Sezione sottoSezione : sottoSezioni){
						SezioneDTO sdto = new SezioneDTO();
						sdto.setKey(sottoSezione.getChiave());
						sdto.setCodice(sottoSezione.getCodSezSotSez());
						sdto.setNome(sottoSezione.getDesSez());
						sdto.setStatoSezione(TIPO_STATO_SEZIONE.getInstanceFromCode(sottoSezione.getCodSta()));
						sdto.setTipoSezione(TIPO_SEZIONE.getInstanceFromCode(sottoSezione.getPrgSezSotSez().toString()));
						sdto.setStatoDocumento(statoDocumento);
						/************** GESTIONE PLESSI *******************/
						if (sottoSezione.getPrgSezSotSezPle() != null && sottoSezione.getPrgSezSotSezPle().equals(1L)){
							LinkedList<Sezione> sottoSezioniPlessi = sezioneRepository.findSottoSezioniPlessiScuola(codScuUte, perAnnSco, prgGesCatDoc, sottoSezione.getPrgSezSotSez());
							if (!sottoSezioniPlessi.isEmpty()){
								LinkedList<SezioneDTO> sezionePlessi = new LinkedList<>();
								for (Sezione sottoSezionePlesso : sottoSezioniPlessi){
									SezioneDTO sezionePlesso = new SezioneDTO();
									sezionePlesso.setKey(sottoSezionePlesso.getChiave());
									sezionePlesso.setCodice(sottoSezionePlesso.getCodSezSotSez());
									sezionePlesso.setNome(sottoSezionePlesso.getDesSez());
									sezionePlesso.setStatoSezione(TIPO_STATO_SEZIONE.getInstanceFromCode(sottoSezionePlesso.getCodSta()));
									sezionePlesso.setTipoSezione(TIPO_SEZIONE.getInstanceFromCode(sottoSezionePlesso.getPrgSezSotSez().toString()));
									sezionePlesso.setStatoDocumento(statoDocumento);
									if (	sezionePlesso.getTipoSezione() != null
											&& sezionePlesso.getTipoSezione().equals(TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO)
											&& !CommonsUtility.isSecondariaDiSecondoGrado(sottoSezionePlesso.getCodScuUtePle())){
										sezionePlesso.setDisabled(Boolean.TRUE);
										sezionePlesso.setDisabledMessage("La sezione ALTERNANZA SCUOLA LAVORO è accessibile solo per le scuole secondarie di II grado.");
									}
									sezionePlessi.add(sezionePlesso);
								}
								sdto.setSottoSezione(sezionePlessi);
							}
						}
						/************** GESTIONE PLESSI *******************/
						sottoSezioniDto.add(sdto);
					}
					dto.setSottoSezione(sottoSezioniDto);
				}
				
				out.add(dto);
			}
			
		}
    	return out;
    }
    
    @Override
    public SezioneExtDTO loadSezione(String keySezione , IstitutoScolasticoDTO istitutoScolasticoDTO ){
    	
    	logger.debug("loadSezione : " + keySezione);
    	
    	SezioneExtDTO sezioneExtDTO = null;
    	Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(Long.valueOf(keySezione), istitutoScolasticoDTO.getCodiceMecIstPrin());
    	if (tbs1004Datisezionesottosez != null){
    		
    		//logger.debug(ReflectionToStringBuilder.toString(tbs1004Datisezionesottosez,ToStringStyle.MULTI_LINE_STYLE));
    		//logger.debug(ReflectionToStringBuilder.toString(tbs1004Datisezionesottosez.getTbs1003Catalogosezione(),ToStringStyle.MULTI_LINE_STYLE));
    		
    		sezioneExtDTO = new SezioneExtDTO();
    		sezioneExtDTO.setCodice(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getCodSezSotSez());
    		sezioneExtDTO.setKey(tbs1004Datisezionesottosez.getPrgDatPtf().toString());
    		sezioneExtDTO.setNome(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getDesSez());
    		sezioneExtDTO.setObbInsAlmenoUnCom(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getFlgVal().equals("1"));
    		sezioneExtDTO.setStatoSezione(TIPO_STATO_SEZIONE.getInstanceFromCode(tbs1004Datisezionesottosez.getCodSta()));
    		sezioneExtDTO.setTipoSezione(TIPO_SEZIONE.getInstanceFromCode(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().toString()));
    		sezioneExtDTO.setTestoFissoIntestazioneSezione(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getDesMsgInt());
    		
    		Documento documento = documentoRepository.getDocumentoByKey(tbs1004Datisezionesottosez.getCodScuUte(),tbs1004Datisezionesottosez.getPerAnnSco(),tbs1004Datisezionesottosez.getPrgGesCatDoc());
    		TIPO_STATO_DOC statoDocumento = TIPO_STATO_DOC.getInstanceFromCode(documento.getCodSta());
    		sezioneExtDTO.setStatoDocumento(statoDocumento);
    		
    		
    		LinkedList<ComponenteDTO> componenti = new LinkedList<>();
    		LinkedList<Tbs1014Componentesezione> tbs1014Componenteseziones = tbs1014ComponentesezioneRepository.findByPrgSezSotSezAndPrgCmpSezPdrIsNullOrderByPrgOrdAsc(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez());
    		
    		if (tbs1014Componenteseziones != null && !tbs1014Componenteseziones.isEmpty()){
    			logger.debug("numero componenti : " + tbs1014Componenteseziones.size());
    			for (Tbs1014Componentesezione tbs1014Componentesezione : tbs1014Componenteseziones){

    				logger.debug("tbs1014Componentesezione.getTbs1016Tipocomponente().getCodTipCmp() : " + tbs1014Componentesezione.getTbs1016Tipocomponente().getCodTipCmp());
    				TIPO_COMPONENTE tipoComponente = TIPO_COMPONENTE.getInstanceFromCode(tbs1014Componentesezione.getTbs1016Tipocomponente().getCodCatCmp());
    				logger.debug("tipoComponente : " + tipoComponente.name());

    				switch (tipoComponente) {
    				case TEXTEDITOR:
    					utilPtofServiceImpl.gestisciLoadComponenteTEXTEDITOR(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				case ALLEGATO:
    					utilPtofServiceImpl.gestisciLoadComponenteALLEGATO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				case DATI_ISTITUTO_PRINCIPALE:
    					utilPtofServiceImpl.gestisciLoadComponenteDATI_ISTITUTO_PRINCIPALE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				case MULTI_BOX:
    					utilPtofServiceImpl.gestisciLoadComponenteMULTI_BOX(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    					
    				case S_OBBIETTIVI_FORMATIVI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_OBBIETTIVI_FORMATIVI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				case S_OBBIETTIVI_FORMATIVI_ALTRI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_OBBIETTIVI_FORMATIVI_ALTRI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_OBBIETTIVI_MIGLIORAMENTO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_OBBIETTIVI_MIGLIORAMENTO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    					
    				case DATI_PLESSO:
    					utilPtofServiceImpl.gestisciLoadComponenteDATI_PLESSO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_PROGETTI_CURRICULARI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_PROGETTI_CURRICULARI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ALTRI_PROGETTI_CURRICULARI_EXSTRA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ALTRI_PROGETTI_CURRICULARI_EXSTRA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    					
    				case S_ALTRE_INIZIATIVE_DIDATTICO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ALTRE_INIZIATIVE_DIDATTICO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ATTIVITA_SOSTEGNO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ATTIVITA_SOSTEGNO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_RISORSE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    					
    				case S_PROGRAMMAZIONE_FORMAZIONE_DOC:
    				case S_PROGRAMMAZIONE_FORMAZIONE_AMM:
    				case S_PROGRAMMAZIONE_FORMAZIONE_TEC:
    				case S_PROGRAMMAZIONE_FORMAZIONE_AUS:
    					utilPtofServiceImpl.gestisciLoadComponenteS_PROGRAMMAZIONE_FORMAZIONE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, tipoComponente, statoDocumento);
    					break;
    					
    				case S_STRUMENTI_ATTREZZATURE_TECNOLOGIA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_STRUMENTI_ATTREZZATURE_TECNOLOGIA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE:
    					//checkDataS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(tbs1004Datisezionesottosez);
    					utilPtofServiceImpl.gestisciLoadComponenteS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_PIANIFICAZIONE_ATTIVITA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_PIANIFICAZIONE_ATTIVITA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_MONITORAGGIO_PIANIFICAZIONE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_MONITORAGGIO_PIANIFICAZIONE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_CLASSI_AA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_AA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_CLASSI_EE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_EE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_CLASSI_MMI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ORGANIZZAZIONE_CLASSI_MMII:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMII(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				case S_DATI_FINALI_SCUOLA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_DATI_FINALI_SCUOLA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento, documento.getNumVerDoc().toString());
    					break;
    				
    				case S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_CATTEDRE_AA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_AA_EE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "AA", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_CATTEDRE_EE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_AA_EE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "EE", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_CATTEDRE_IG:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_MM_SS(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "MM", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_CATTEDRE_IIG:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_MM_SS(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "SS", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_CONNESSO_PROGETTO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_CONNESSO_PROGETTO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_CONNESSO_FORMAZIONE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_CONNESSO_FORMAZIONE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_ATTR:
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_INFR:
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_ATTR:
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_INFR:
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_ATTR:
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_INFR:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, tipoComponente, statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_SOSTEGNO_AA:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_AA__EE__MM(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "AA", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_SOSTEGNO_EE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_AA__EE__MM(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "EE", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_SOSTEGNO_MM:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_AA__EE__MM(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "MM", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_SOSTEGNO_MMII:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_SS(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "SS", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "EE", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_POTENZIAMENTO_MMI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "MM", statoDocumento);
    					break;
    				
       				case S_FABBISOGNO_POSTI_POTENZIAMENTO_MMI_SOSTEGNO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS_SOSTEGNO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "MM", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_POTENZIAMENTO_SS:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "SS", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_POTENZIAMENTO_SS_SOSTEGNO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS_SOSTEGNO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, "SS", statoDocumento);
    					break;
    				
    				case S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI:
    					utilPtofServiceImpl.gestisciLoadComponenteS_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    					
    				case S_PIANO_NAZIONALE_SCUOLA_DIGITALE:
    					utilPtofServiceImpl.gestisciLoadComponenteS_PIANO_NAZIONALE_SCUOLA_DIGITALE(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;
    				
    				case S_ALTERNANZA_SCUOLA_LAVORO:
    					utilPtofServiceImpl.gestisciLoadComponenteS_ALTERNANZA_SCUOLA_LAVORO(tbs1004Datisezionesottosez, tbs1014Componentesezione, componenti, statoDocumento);
    					break;

    				

    				default:
    					throw new RuntimeException("Tipo componente non gestito");
    				}



    			}
    		}
    		sezioneExtDTO.setComponenti(componenti);
    	}
    	return sezioneExtDTO;
    }
    
	/**
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 *  salva  i componenti della sezione e non cambia lo stato della sezione
	 */
	@Override
	public void saveSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
		logger.debug("in saveSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004Datisezionesottosez == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}
		
		for (ComponenteDTO componenteDTO : sezione.getComponenti()){
			
			TIPO_COMPONENTE tipoComponente = componenteDTO.getTipoComponente();
			logger.debug("tipoComponente : " + tipoComponente.name());

			switch (tipoComponente) {
			case TEXTEDITOR:
				ComponenteTextAreaDTO componenteTextAreaDTO = (ComponenteTextAreaDTO)componenteDTO;
				logger.debug(ReflectionToStringBuilder.toString(componenteTextAreaDTO.getValore(),ToStringStyle.MULTI_LINE_STYLE));
				Tbs1007Testo tbs1007Testo = tbs1007TestoRepository.findOne(Long.valueOf(componenteTextAreaDTO.getKey()));
				tbs1007Testo.setOggTes(componenteTextAreaDTO.getValore().getBytes());
				tbs1007TestoRepository.save(tbs1007Testo);
				break;
				
			case MULTI_BOX:
				ComponenteMultiBoxDTO componenteMultiBoxDTO = (ComponenteMultiBoxDTO)componenteDTO;
				for (ComponenteComboBoxDTO componenteComboBoxDTO : componenteMultiBoxDTO.getComboBox()){
					 String codTipCmp = componenteComboBoxDTO.getKey();
			    	if (codTipCmp.equals(TIPO_CMP_TIPOLOGIA_DI_AGGREGAZIONE_CONTESTO)){
			    		if (componenteComboBoxDTO.getSelected() != null){
			    			tbs1004Datisezionesottosez.setCodTipCts(componenteComboBoxDTO.getSelected().getValue());
			    		}else{
			    			tbs1004Datisezionesottosez.setCodTipCts(null);
			    		}
			    	}else if (codTipCmp.equals(TIPO_CMP_LIVELLO_DI_MULTICULTURALITA)){
			    		if (componenteComboBoxDTO.getSelected() != null){
			    			tbs1004Datisezionesottosez.setCodTipMlt(componenteComboBoxDTO.getSelected().getValue());
			    		}else{
			    			tbs1004Datisezionesottosez.setCodTipMlt(null);
			    		}
			    	}
				}
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_MONITORAGGIO_PIANIFICAZIONE:
				ComponenteMonitoraggioPianificazioneDTO componenteMonitoraggioPianificazioneDTO = (ComponenteMonitoraggioPianificazioneDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDesModVer(componenteMonitoraggioPianificazioneDTO.getModalitaVerifica());
				tbs1004Datisezionesottosez.setDesRapPia(componenteMonitoraggioPianificazioneDTO.getSoluzioniAdottateRispettoPianificazione());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_DATI_FINALI_SCUOLA:
				ComponenteDatiFinaliScuolaDTO componenteDatiFinaliScuolaDTO = (ComponenteDatiFinaliScuolaDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDatRatDirSco(componenteDatiFinaliScuolaDTO.getDataRatificaAttoIndirizzoDirigente());
				tbs1004Datisezionesottosez.setDatPrdPto(componenteDatiFinaliScuolaDTO.getDataPredisposizionePTOFCollegioDocenti());
				tbs1004Datisezionesottosez.setDatAppCdi(componenteDatiFinaliScuolaDTO.getDataApprovazioneConsiglioDocenti());
				tbs1004Datisezionesottosez.setDatInvUsr(componenteDatiFinaliScuolaDTO.getDataInvioUSR());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO:
				ComponenteArticolazioneOrariaIndirizziStudioDTO componenteArticolazioneOrariaIndirizziStudioDTO = (ComponenteArticolazioneOrariaIndirizziStudioDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDesNotIndStu(componenteArticolazioneOrariaIndirizziStudioDTO.getNota());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO:
				ComponenteArticolazioneQuadriOrariDTO componenteArticolazioneQuadriOrariDTO = (ComponenteArticolazioneQuadriOrariDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDesNotQuaOra(componenteArticolazioneQuadriOrariDTO.getNota());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA:
				ComponenteArticolazioneTempiScuolaDTO componenteArticolazioneTempiScuolaDTO = (ComponenteArticolazioneTempiScuolaDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDesNotTmpScu(componenteArticolazioneTempiScuolaDTO.getNota());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
			
			case S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO:
				ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO componenteFabbisognoAttrezzatureInfraAltreInfoDTO = (ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO)componenteDTO;
				tbs1004Datisezionesottosez.setDesBeaFabAtm(componenteFabbisognoAttrezzatureInfraAltreInfoDTO.getBeneficiAttesi());
				tbs1004Datisezionesottosez.setDesFinFabAtm(componenteFabbisognoAttrezzatureInfraAltreInfoDTO.getFinalita());
				tbs1004Datisezionesottosez.setDesMotFabAtm(componenteFabbisognoAttrezzatureInfraAltreInfoDTO.getMotivazione());
				tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
					
				break;
				
			default:
				//throw new RuntimeException("Tipo componente non gestito");
			}
			
		}
		
		
	}

	/**
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 * cancella tutti i componenti delle sezione a imposta lo stato a BOZZA
	 */
	@Override
	public void deleteSezione(SezioneExtDTO sezione,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {

		logger.debug("in deleteSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004DB = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004DB == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}
		
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = new Tbs1004Datisezionesottosez();
		tbs1004Datisezionesottosez.setCodScuUte(tbs1004DB.getCodScuUte());
		tbs1004Datisezionesottosez.setPerAnnSco(tbs1004DB.getPerAnnSco());
		tbs1004Datisezionesottosez.setPrgGesCatDoc(tbs1004DB.getPrgGesCatDoc());
		tbs1004Datisezionesottosez.setTbs1003Catalogosezione(tbs1004DB.getTbs1003Catalogosezione());
		tbs1004Datisezionesottosez.setCodSta(TIPO_STATO_SEZIONE.BOZZA.code());
		tbs1004Datisezionesottosez.setCodScuUtePle(tbs1004DB.getCodScuUtePle());
		
		logger.debug("elimino la sezione con tutti i record figli");
		tbs1004DatisezionesottosezRepository.delete(tbs1004DB);
		logger.debug("reinserisco la sezione in bozza");
		tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
		utilPtofServiceImpl.gestisciStatoSezionePadre(tbs1004Datisezionesottosez.getPrgDatPtf());
		
		/** Se la sezione è la 9. Strumenti e attrezzature e tecnologia, PRG_SEZ_SOT_SEZ = 32, ripristino sulla tabella dati PTOF
		 * i record delle dotazioni multimediali dalla TBS1033_DOTAZIONMUSICSCUOLA
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA.code()))){
			utilPtofServiceImpl.copyDataS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(tbs1004Datisezionesottosez);
		}
		
		/** Se la sezione è la SEZIONE_17_ORGANIZZAZIONE_CLASSI, inizializzo sulle tabelle dati PTOF
		 * i record relativi all'articolazione delle classi
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI.code()))){
			utilPtofServiceImpl.copyDataSEZIONE_17_ORGANIZZAZIONE_CLASSI(tbs1004Datisezionesottosez);
		}
		
		/** Se la sezione è la SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE, inizializzo sulle tabelle dati PTOF
		 * i record relativi ai fabbisogni posti e cattedre
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE.code()))){
			utilPtofServiceImpl.copyDataSEZIONE_35_FABBISOGNO_POSTI_CATTEDRE(tbs1004Datisezionesottosez);
		}
		
		/** Se la sezione è la SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO, inizializzo sulle tabelle dati PTOF
		 * i record relativi ai fabbisogni posti di sostegno
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO.code()))){
			utilPtofServiceImpl.copyDataSEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO(tbs1004Datisezionesottosez);
		}
		
		/** Se la sezione è la SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO, inizializzo sulle tabelle dati PTOF
		 * i record relativi ai fabbisogni posti di potenziamento
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO.code()))){
			utilPtofServiceImpl.copyDataSEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO(tbs1004Datisezionesottosez);
		}
		
		/** Se la sezione è la SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI, inizializzo sulle tabelle dati PTOF
		 * i record relativi ai fabbisogni posti ATA
		 **/
		if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().equals(Long.valueOf(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI.code()))){
			utilPtofServiceImpl.copyDataSEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI(tbs1004Datisezionesottosez);
		}

	}

	/**
	 * @deprecated
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 * cancella i componenti delle sezione e mette lo stato della sezione in bozza
	 */
	@Override
	public void deleteComponentiInSezione(SezioneBaseDTO sezione,
			LinkedList<ComponenteDTO> componeti,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
	}

	/**
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 * @param statoSezione
	 * salva  i componenti della sezione e cambia lo stato in statoSezione (input)
	 */
	@Override
	public void saveSezione(SezioneExtDTO sezione,
			IstitutoScolasticoDTO istitutoScolasticoDTO,
			TIPO_STATO_SEZIONE statoSezione) {

		this.saveSezione(sezione, istitutoScolasticoDTO);
		utilPtofServiceImpl.updateStatoSezione(Long.valueOf(sezione.getKey()), istitutoScolasticoDTO, statoSezione);
		utilPtofServiceImpl.gestisciStatoSezionePadre(Long.valueOf(sezione.getKey()));
	}
	
	/**
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 * salva il singolo componente delle sezione e cambia lo stato della sezione
	 * es: usato con allegato
	 */
	@Override
	public ComponenteDTO saveComponenteInSezione(SezioneBaseDTO sezione, 
												 ComponenteDTO componente , 
												 IstitutoScolasticoDTO istitutoScolasticoDTO , 
												 TIPO_STATO_SEZIONE statoSezione){
		
		logger.debug("in saveComponenteInSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004DB = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004DB == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}
		
		TIPO_COMPONENTE tipoComponente = componente.getTipoComponente();
		logger.debug("tipoComponente : " + tipoComponente.name());

		switch (tipoComponente) {
		case ALLEGATO:
			ComponenteAllegatoDTO componenteAllegatoDTO = (ComponenteAllegatoDTO)componente;
			logger.debug("aggiorno tbs1005Allegato");

			Tbs1005Allegato tbs1005Allegato = null;
			tbs1005Allegato = tbs1005AllegatoRepository.findOne(Long.valueOf(componenteAllegatoDTO.getKey()));
			if (tbs1005Allegato == null){
				tbs1005Allegato = new Tbs1005Allegato();
				Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = new Tbs1004Datisezionesottosez();
				tbs1004Datisezionesottosez.setPrgDatPtf(prgDatPtf);
				tbs1005Allegato.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
			}
			tbs1005Allegato.setDesNomAll(componenteAllegatoDTO.getFile().getFileName());
			try {
				tbs1005Allegato.setOggAll(org.apache.commons.io.IOUtils.toByteArray( componenteAllegatoDTO.getFile().getFile() ));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			tbs1005Allegato = tbs1005AllegatoRepository.save(tbs1005Allegato);

			componente.setKey(tbs1005Allegato.getPrgAll().toString());
			break;

		default:
			throw new RuntimeException("Tipo componente non gestito");
		}
		
		utilPtofServiceImpl.updateStatoSezione(prgDatPtf, istitutoScolasticoDTO, statoSezione);
		utilPtofServiceImpl.gestisciStatoSezionePadre(prgDatPtf);

		
		return componente;
	}

	
	/**
	 * @param sezione
	 * @param istitutoScolasticoDTO
	 * cancella solo i componenti delle sezione e cambia lo stato della sezione
	 * es: usato per cancella allegato
	 */
	@Override
	public void deleteComponentiInSezione(SezioneBaseDTO sezione,
										  LinkedList<ComponenteDTO> componeti,
										  IstitutoScolasticoDTO istitutoScolasticoDTO,
										  TIPO_STATO_SEZIONE statoSezione) {
		
		logger.debug("in deleteComponentiInSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004DB = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004DB == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}
		
		for (ComponenteDTO componente : componeti){
			TIPO_COMPONENTE tipoComponente = componente.getTipoComponente();
			logger.debug("tipoComponente : " + tipoComponente.name());

			switch (tipoComponente) {
			case ALLEGATO:
				ComponenteAllegatoDTO componenteAllegatoDTO = (ComponenteAllegatoDTO)componente;
				logger.debug("eliminazione dell'allegato in tbs1005Allegato : " + componenteAllegatoDTO.getKey());
				Tbs1005Allegato tbs1005Allegato = tbs1005AllegatoRepository.findOne(Long.valueOf(componenteAllegatoDTO.getKey()));
				if (tbs1005Allegato != null){
					//tbs1005AllegatoRepository.delete(Long.valueOf(componenteAllegatoDTO.getKey()));
					tbs1005Allegato.setDesNomAll("");
					tbs1005Allegato.setOggAll(null);
					tbs1005AllegatoRepository.save(tbs1005Allegato);
				}else{
					throw new RuntimeException("Allegato non esistente in base dati");
				}
				break;

			default:
				throw new RuntimeException("Tipo componente non gestito");
			}

		}
		utilPtofServiceImpl.updateStatoSezione(prgDatPtf, istitutoScolasticoDTO, statoSezione);
		utilPtofServiceImpl.gestisciStatoSezionePadre(prgDatPtf);

		
	}

	/**
	 * @param sezione
	 * @param componenti
	 * @param istitutoScolasticoDTO
	 * cancella gli items del componente ( passati in input ) delle sezione e imposta lo stato della sezione in bozza
	 * use 
	 *  caso 1 : nel componponte obbiettivi verra la passa la lista dei componeti da cancellare 
	 */
	@Override
	public void deleteItemsInComponenteInSezione(SezioneBaseDTO sezione,
			ComponenteDTO componete, IstitutoScolasticoDTO istitutoScolasticoDTO) {

		logger.debug("in deleteItemsInComponenteInSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004DB = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004DB == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}

		TIPO_COMPONENTE tipoComponente = componete.getTipoComponente();
		logger.debug("tipoComponente : " + tipoComponente.name());

		switch (tipoComponente) {
		case  S_OBBIETTIVI_FORMATIVI:
			ComponenteObbiettiviPrioritariDTO componenteObbiettiviPrioritariDTO = (ComponenteObbiettiviPrioritariDTO)componete;
			for (ObbiettiviFormativiDTO obbiettiviFormativiDTO : componenteObbiettiviPrioritariDTO.getItems()){
				logger.debug("eliminazione obiettivo formativo su Tbs1009Obiettivo : " + obbiettiviFormativiDTO.getKey());
				tbs1009ObiettivoRepository.delete(Long.valueOf(obbiettiviFormativiDTO.getKey()));
			}
			break;
			
		case  S_OBBIETTIVI_FORMATIVI_ALTRI:
			ComponenteObbiettiviPrioritariAltriDTO componenteObbiettiviPrioritariAltriDTO = (ComponenteObbiettiviPrioritariAltriDTO)componete;
			for (ObbiettiviFormativiDTO obbiettiviFormativiDTO : componenteObbiettiviPrioritariAltriDTO.getItems()){
				logger.debug("eliminazione altro obiettivo formativo su tbs1013Altrobiettivo : " + obbiettiviFormativiDTO.getKey());
				tbs1013AltrobiettivoRepository.delete(Long.valueOf(obbiettiviFormativiDTO.getKey()));
			}
			break;
		
		case  S_PROGETTI_CURRICULARI:
			ComponenteProgettiCurriculariDTO componenteProgettiCurriculariDTO = (ComponenteProgettiCurriculariDTO)componete;
			for (AmbitiProgettiDTO ambitiProgettiDTO : componenteProgettiCurriculariDTO.getItems()){
				logger.debug("eliminazione progetto curriculare su tbs1021Ambitoprogettoptof : " + ambitiProgettiDTO.getKey());
				tbs1021AmbitoprogettoptofRepository.delete(Long.valueOf(ambitiProgettiDTO.getKey()));
			}
			break;
		
		case  S_ALTRI_PROGETTI_CURRICULARI_EXSTRA:
			ComponenteAltriProgettiCurriculariDTO componenteAltriProgettiCurriculariDTO = (ComponenteAltriProgettiCurriculariDTO)componete;
			for (AmbitiProgettiDTO ambitiProgettiDTO : componenteAltriProgettiCurriculariDTO.getItems()){
				logger.debug("eliminazione altro progetto curriculare su tbs1022Ambitoprogettoaltro : " + ambitiProgettiDTO.getKey());
				tbs1022AmbitoprogettoaltroRepository.delete(Long.valueOf(ambitiProgettiDTO.getKey()));
			}
			break;
		
		case  S_ALTRE_INIZIATIVE_DIDATTICO:
			ComponenteAltreIniziativeDidaDTO componenteAltreIniziativeDidaDTO = (ComponenteAltreIniziativeDidaDTO)componete;
			for (IniziativeDidatticheEducativeDTO iniziativeDidatticheEducativeDTO : componenteAltreIniziativeDidaDTO.getItems()){
				logger.debug("eliminazione iniziative didattiche educative su tbs1023Altreinizididattiche : " + iniziativeDidatticheEducativeDTO.getKey());
				tbs1023AltreinizididatticheRepository.delete(Long.valueOf(iniziativeDidatticheEducativeDTO.getKey()));
			}
			break;
		
		case  S_ATTIVITA_SOSTEGNO:
			ComponenteAttivitaSostegnoDTO componenteAttivitaSostegnoDTO = (ComponenteAttivitaSostegnoDTO)componete;
			for (AttivitaSostegnoDTO attivitaSostegnoDTO : componenteAttivitaSostegnoDTO.getItems()){
				logger.debug("eliminazione attivita sostegno su tbs1024Attivitasostegnoptof : " + attivitaSostegnoDTO.getKey());
				tbs1024AttivitasostegnoptofRepository.delete(Long.valueOf(attivitaSostegnoDTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI:
			ComponenteOrganigrammaAltriRuoliDTO componenteOrganigrammaAltriRuoliDTO = (ComponenteOrganigrammaAltriRuoliDTO)componete;
			for (OrganigrammaDTO organigrammaDTO : componenteOrganigrammaAltriRuoliDTO.getItems()){
				logger.debug("eliminazione organigramma su tbs1026Organrisorseptof : " + organigrammaDTO.getKey());
				tbs1026OrganrisorseptofRepository.delete(Long.valueOf(organigrammaDTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_RISORSE:
			ComponenteOrganigrammaDTO componenteOrganigrammaDTO = (ComponenteOrganigrammaDTO)componete;
			for (OrganigrammaDTO organigrammaDTO : componenteOrganigrammaDTO.getItems()){
				logger.debug("eliminazione organigramma su tbs1026Organrisorseptof : " + organigrammaDTO.getKey());
				tbs1026OrganrisorseptofRepository.delete(Long.valueOf(organigrammaDTO.getKey()));
			}
			break;
		
		case  S_PROGRAMMAZIONE_FORMAZIONE_DOC:
			ComponenteProgrammazioneFormDocDTO componenteProgrammazioneFormDocDTO = (ComponenteProgrammazioneFormDocDTO)componete;
			for (ProgrammazioneFormDTO programmazioneFormDTO : componenteProgrammazioneFormDocDTO.getItems()){
				logger.debug("eliminazione programmazione form su tbs1027Performazpersonale : " + programmazioneFormDTO.getKey());
				tbs1027PerformazpersonaleRepository.delete(Long.valueOf(programmazioneFormDTO.getKey()));
			}
			break;
		
		case  S_PROGRAMMAZIONE_FORMAZIONE_AMM:
			ComponenteProgrammazioneFormAmmDTO componenteProgrammazioneFormAmmDTO = (ComponenteProgrammazioneFormAmmDTO)componete;
			for (ProgrammazioneFormDTO programmazioneFormDTO : componenteProgrammazioneFormAmmDTO.getItems()){
				logger.debug("eliminazione programmazione form su tbs1027Performazpersonale : " + programmazioneFormDTO.getKey());
				tbs1027PerformazpersonaleRepository.delete(Long.valueOf(programmazioneFormDTO.getKey()));
			}
			break;
		
		case  S_PROGRAMMAZIONE_FORMAZIONE_TEC:
			ComponenteProgrammazioneFormTecDTO componenteProgrammazioneFormTecDTO = (ComponenteProgrammazioneFormTecDTO)componete;
			for (ProgrammazioneFormDTO programmazioneFormDTO : componenteProgrammazioneFormTecDTO.getItems()){
				logger.debug("eliminazione programmazione form su tbs1027Performazpersonale : " + programmazioneFormDTO.getKey());
				tbs1027PerformazpersonaleRepository.delete(Long.valueOf(programmazioneFormDTO.getKey()));
			}
			break;
		
		case  S_PROGRAMMAZIONE_FORMAZIONE_AUS:
			ComponenteProgrammazioneFormAusDTO componenteProgrammazioneFormAusDTO = (ComponenteProgrammazioneFormAusDTO)componete;
			for (ProgrammazioneFormDTO programmazioneFormDTO : componenteProgrammazioneFormAusDTO.getItems()){
				logger.debug("eliminazione programmazione form su tbs1027Performazpersonale : " + programmazioneFormDTO.getKey());
				tbs1027PerformazpersonaleRepository.delete(Long.valueOf(programmazioneFormDTO.getKey()));
			}
			break;
		
		case  S_STRUMENTI_ATTREZZATURE_TECNOLOGIA:
			ComponenteStrumentiAttrezzatureTecnologicheDTO componenteStrumentiAttrezzatureTecnologicheDTO = (ComponenteStrumentiAttrezzatureTecnologicheDTO)componete;
			for (StrumentiAttrezzatureTecnologicheDTO strumentiAttrezzatureTecnologicheDTO : componenteStrumentiAttrezzatureTecnologicheDTO.getItems()){
				logger.debug("eliminazione strumenti attrezzature tecnologiche su tbs1028Attrezzature : " + strumentiAttrezzatureTecnologicheDTO.getKey());
				tbs1028AttrezzatureRepository.delete(Long.valueOf(strumentiAttrezzatureTecnologicheDTO.getKey()));
			}
			break;
		
		case  S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE:
			ComponenteDotazioneIstPriDTO componenteDotazioneIstPriDTO = (ComponenteDotazioneIstPriDTO)componete;
			for (DotazioneIstPriDTO dotazioneIstPriDTO : componenteDotazioneIstPriDTO.getItems()){
				logger.debug("eliminazione dotazione Ist Pri su tbs1029Dotazionemultimediale : " + dotazioneIstPriDTO.getKey());
				tbs1029DotazionemultimedialeRepository.delete(Long.valueOf(dotazioneIstPriDTO.getKey()));
			}
			break;
		
		case  S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO:
			ComponentePromozioneRapportiEntiTerritorioDTO componentePromozioneRapportiEntiTerritorioDTO = (ComponentePromozioneRapportiEntiTerritorioDTO)componete;
			for (PromozioneRapportiEntiTerritorioDTO promozioneRapportiEntiTerritorioDTO : componentePromozioneRapportiEntiTerritorioDTO.getItems()){
				logger.debug("eliminazione promozione rapporti enti territorio su tbs1030Rapportoente : " + promozioneRapportiEntiTerritorioDTO.getKey());
				tbs1030RapportoenteRepository.delete(Long.valueOf(promozioneRapportiEntiTerritorioDTO.getKey()));
			}
			break;
		
		case  S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO:
			ComponenteConvPotenzFormativoDTO componenteConvPotenzFormativoDTO = (ComponenteConvPotenzFormativoDTO)componete;
			for (ConvPotenzFormativoDTO convPotenzFormativoDTO : componenteConvPotenzFormativoDTO.getItems()){
				logger.debug("eliminazione conv potenz formativo su tbs1031Convenzioneforma : " + convPotenzFormativoDTO.getKey());
				tbs1031ConvenzioneformaRepository.delete(Long.valueOf(convPotenzFormativoDTO.getKey()));
			}
			break;
		
		case  S_PIANIFICAZIONE_ATTIVITA:
			ComponentePianificazioneInterventiMonitoraggioDTO componentePianificazioneInterventiMonitoraggioDTO = (ComponentePianificazioneInterventiMonitoraggioDTO)componete;
			for (PianificazioneInterventiMonitoraggioDTO pianificazioneInterventiMonitoraggioDTO : componentePianificazioneInterventiMonitoraggioDTO.getItems()){
				logger.debug("eliminazione pianificazione interventi monitoraggio su tbs1032Pianificattivita : " + pianificazioneInterventiMonitoraggioDTO.getKey());
				tbs1032PianificattivitaRepository.delete(Long.valueOf(pianificazioneInterventiMonitoraggioDTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_AA:
			ComponenteArticolazioneClassiAADTO componenteArticolazioneClassiAADTO = (ComponenteArticolazioneClassiAADTO)componete;
			for (ArticolazioneClassiAADTO articolazioneClassiAADTO : componenteArticolazioneClassiAADTO.getItems()){
				tbs1034ArticoclasseinfanziaRepository.delete(Long.valueOf(articolazioneClassiAADTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_EE:
			ComponenteArticolazioneClassiEEDTO componenteArticolazioneClassiEEDTO = (ComponenteArticolazioneClassiEEDTO)componete;
			for (ArticolazioneClassiEEDTO articolazioneClassiEEDTO : componenteArticolazioneClassiEEDTO.getItems()){
				tbs1035ArticoclasseprimariaRepository.delete(Long.valueOf(articolazioneClassiEEDTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_MMI:
			ComponenteArticolazioneClassiMMIDTO componenteArticolazioneClassiMMIDTO = (ComponenteArticolazioneClassiMMIDTO)componete;
			for (ArticolazioneClassiIMMIDTO articolazioneClassiIMMIDTO : componenteArticolazioneClassiMMIDTO.getItems()){
				tbs1036ArticolaclasseigradoRepository.delete(Long.valueOf(articolazioneClassiIMMIDTO.getKey()));
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_MMII:
			ComponenteArticolazioneClassiMMIIDTO componenteArticolazioneClassiMMIIDTO = (ComponenteArticolazioneClassiMMIIDTO)componete;
			for (ArticolazioneClassiMMIIDTO articolazioneClassiMMIIDTO : componenteArticolazioneClassiMMIIDTO.getItems()){
				tbs1038ArticolaclasseiigradoRepository.delete(Long.valueOf(articolazioneClassiMMIIDTO.getKey()));
			}
			break;
		
		case  S_FABBISOGNO_CONNESSO_PROGETTO:
			ComponenteFabbisognoConnessoProgettoDTO componenteFabbisognoConnessoProgettoDTO = (ComponenteFabbisognoConnessoProgettoDTO)componete;
			for (FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO : componenteFabbisognoConnessoProgettoDTO.getItems()){
				String tipoProgetto = fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(0, 1);
				if (tipoProgetto.equals("P")){
					Long prgAmbPgtPto = Long.valueOf(fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(1));
					Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof = tbs1021AmbitoprogettoptofRepository.findOne(prgAmbPgtPto);
					if (tbs1021Ambitoprogettoptof != null){
						tbs1021Ambitoprogettoptof.setDesNomBenSer(null);
						tbs1021Ambitoprogettoptof.setDesBenSer(null);
						tbs1021Ambitoprogettoptof.setDesCls(null);
						tbs1021Ambitoprogettoptof.setNumDocAta(null);
						tbs1021Ambitoprogettoptof.setImpSti(null);
						tbs1021Ambitoprogettoptof.setDesNot(null);
						tbs1021AmbitoprogettoptofRepository.save(tbs1021Ambitoprogettoptof);
					}
					
				}else if (tipoProgetto.equals("A")){
					Long prgAmbPgtAlt = Long.valueOf(fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(1));
					Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro = tbs1022AmbitoprogettoaltroRepository.findOne(prgAmbPgtAlt);
					if (tbs1022Ambitoprogettoaltro != null){
						tbs1022Ambitoprogettoaltro.setDesNomBenSer(null);
						tbs1022Ambitoprogettoaltro.setDesBenSer(null);
						tbs1022Ambitoprogettoaltro.setDesCls(null);
						tbs1022Ambitoprogettoaltro.setNumDocAta(null);
						tbs1022Ambitoprogettoaltro.setImpSti(null);
						tbs1022Ambitoprogettoaltro.setDesNot(null);
						tbs1022AmbitoprogettoaltroRepository.save(tbs1022Ambitoprogettoaltro);
					}
				}
			}
			break;

		case  S_FABBISOGNO_CONNESSO_FORMAZIONE:
			ComponenteFabbisognoConnessoFormazioneDTO componenteFabbisognoConnessoFormazioneDTO = (ComponenteFabbisognoConnessoFormazioneDTO)componete;
			for (FabbisognoConnessoFormazioneDTO fabbisognoConnessoFormazioneDTO : componenteFabbisognoConnessoFormazioneDTO.getItems()){
				Tbs1027Performazpersonale tbs1027Performazpersonale = tbs1027PerformazpersonaleRepository.findOne(Long.valueOf(fabbisognoConnessoFormazioneDTO.getDenominazionePercorsoFormativo().getValue()));
				if (tbs1027Performazpersonale != null){
					tbs1027Performazpersonale.setDesNomBenSer(null);
					tbs1027Performazpersonale.setDesBenSer(null);
					tbs1027Performazpersonale.setDesCls(null);
					tbs1027Performazpersonale.setNumDocAta(null);
					tbs1027Performazpersonale.setImpSti(null);
					tbs1027Performazpersonale.setDesNot(null);
					tbs1027PerformazpersonaleRepository.save(tbs1027Performazpersonale);
				}


			}
			break;
		
		case  S_FABBISOGNO_ATTREZZATURE_INFRA:
			ComponenteFabbisognoAttrezzatureInfraDTO componenteFabbisognoAttrezzatureInfraDTO = (ComponenteFabbisognoAttrezzatureInfraDTO)componete;
			for (FabbisognoAttrezzatureInfraDTO fabbisognoAttrezzatureInfraDTO : componenteFabbisognoAttrezzatureInfraDTO.getItems()){
				tbs1042FabattrezmaterialeRepository.delete(Long.valueOf(fabbisognoAttrezzatureInfraDTO.getKey()));
			}
			break;

			
		default:
			throw new RuntimeException("Tipo componente non gestito");
		}

		utilPtofServiceImpl.updateStatoSezione(prgDatPtf, istitutoScolasticoDTO, TIPO_STATO_SEZIONE.BOZZA);
		utilPtofServiceImpl.gestisciStatoSezionePadre(prgDatPtf);

	}

	/**
	 * @param sezione
	 * @param componenti
	 * @param istitutoScolasticoDTO
	 * vengono salvati/modificati gli items del componente ( passati in input ) e ritornati in out ( con la chiave se new ) 
	 * lo stato della sezione viene impostato a bozza
	 * 
	 * ATTENZIONE : gli items con la key sono da modificare altrimenti sono nuovi da inserire
	 * use 
	 *  caso 1 : nel componponte obbiettivi verra la passa la lista dei componeti da SALVARE 
	 * @return 
	 */
	@Override
	public ComponenteDTO saveItemsInComponenteInSezione(SezioneBaseDTO sezione,
			ComponenteDTO componete, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
		logger.debug("in saveItemsInComponenteInSezione");
		Long prgDatPtf = Long.valueOf(sezione.getKey());
		Tbs1004Datisezionesottosez tbs1004DB = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004DB == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}

		TIPO_COMPONENTE tipoComponente = componete.getTipoComponente();
		logger.debug("tipoComponente : " + tipoComponente.name());

		switch (tipoComponente) {
		case  S_OBBIETTIVI_FORMATIVI:
			ComponenteObbiettiviPrioritariDTO componenteObbiettiviPrioritariDTO = (ComponenteObbiettiviPrioritariDTO)componete;
			for (ObbiettiviFormativiDTO obbiettiviFormativiDTO : componenteObbiettiviPrioritariDTO.getItems()){
				logger.debug("save obiettivo formativo su Tbs1009Obiettivo key : " + obbiettiviFormativiDTO.getKey());
				Tbs1009Obiettivo tbs1009Obiettivo = new Tbs1009Obiettivo();
				tbs1009Obiettivo.setDesBenAtt(obbiettiviFormativiDTO.getBenefici());
				tbs1009Obiettivo.setDesFin(obbiettiviFormativiDTO.getFinalita());
				if (obbiettiviFormativiDTO.getKey() != null && !obbiettiviFormativiDTO.getKey().isEmpty()){
					tbs1009Obiettivo.setPrgObi(Long.valueOf(obbiettiviFormativiDTO.getKey()));
				}
				tbs1009Obiettivo.setTbs1004Datisezionesottosez(tbs1004DB);
				Tbs1008Tipoobiettivo tbs1008Tipoobiettivo = new Tbs1008Tipoobiettivo();
				tbs1008Tipoobiettivo.setPrgTipObi(Long.valueOf(obbiettiviFormativiDTO.getObbiettivo().getValue()));
				tbs1009Obiettivo.setTbs1008Tipoobiettivo(tbs1008Tipoobiettivo);
				Tbs1012Tipopriorita tbs1012Tipopriorita = new Tbs1012Tipopriorita();
				tbs1012Tipopriorita.setCodTipPri(obbiettiviFormativiDTO.getPriorita().getValue());
				tbs1009Obiettivo.setTbs1012Tipopriorita(tbs1012Tipopriorita);
				
				tbs1009Obiettivo = tbs1009ObiettivoRepository.save(tbs1009Obiettivo);
				obbiettiviFormativiDTO.setKey(tbs1009Obiettivo.getPrgObi().toString());
			}
			break;
			
		case  S_OBBIETTIVI_FORMATIVI_ALTRI:
			ComponenteObbiettiviPrioritariAltriDTO componenteObbiettiviPrioritariAltriDTO = (ComponenteObbiettiviPrioritariAltriDTO)componete;
			for (ObbiettiviFormativiDTO obbiettiviFormativiDTO : componenteObbiettiviPrioritariAltriDTO.getItems()){
				logger.debug("save altro obiettivo formativo su tbs1013Altrobiettivo key : " + obbiettiviFormativiDTO.getKey());
				Tbs1013Altrobiettivo tbs1013Altrobiettivo = new Tbs1013Altrobiettivo();
				tbs1013Altrobiettivo.setDesBenAtt(obbiettiviFormativiDTO.getBenefici());
				tbs1013Altrobiettivo.setDesFin(obbiettiviFormativiDTO.getFinalita());
				if (obbiettiviFormativiDTO.getKey() != null && !obbiettiviFormativiDTO.getKey().isEmpty()){
					tbs1013Altrobiettivo.setPrgAltObi(Long.valueOf(obbiettiviFormativiDTO.getKey()));
				}
				tbs1013Altrobiettivo.setTbs1004Datisezionesottosez(tbs1004DB);
				tbs1013Altrobiettivo.setDesAltObi(obbiettiviFormativiDTO.getObbiettivo().getLabel());
				Tbs1012Tipopriorita tbs1012Tipopriorita = new Tbs1012Tipopriorita();
				tbs1012Tipopriorita.setCodTipPri(obbiettiviFormativiDTO.getPriorita().getValue());
				tbs1013Altrobiettivo.setTbs1012Tipopriorita(tbs1012Tipopriorita);
				
				tbs1013Altrobiettivo = tbs1013AltrobiettivoRepository.save(tbs1013Altrobiettivo);
				obbiettiviFormativiDTO.setKey(tbs1013Altrobiettivo.getPrgAltObi().toString());
			}
			break;
		
		case  S_PROGETTI_CURRICULARI:
			ComponenteProgettiCurriculariDTO componenteProgettiCurriculariDTO = (ComponenteProgettiCurriculariDTO)componete;
			for (AmbitiProgettiDTO ambitiProgettiDTO : componenteProgettiCurriculariDTO.getItems()){
				logger.debug("save progetti su Tbs1021Ambitoprogettoptof key : " + ambitiProgettiDTO.getKey());
				Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof = new Tbs1021Ambitoprogettoptof();
				
				if (ambitiProgettiDTO.getKey() != null && !ambitiProgettiDTO.getKey().isEmpty()){
					tbs1021Ambitoprogettoptof.setPrgAmbPgtPto(Long.valueOf(ambitiProgettiDTO.getKey()));
				}
				
				tbs1021Ambitoprogettoptof.setCodAreTem(ambitiProgettiDTO.getAreaTematicaPNSD());
				tbs1021Ambitoprogettoptof.setDatFinVal(ambitiProgettiDTO.getDurataAl());
				tbs1021Ambitoprogettoptof.setDatIniVal(ambitiProgettiDTO.getDurataDal());
				tbs1021Ambitoprogettoptof.setDesAltRis(ambitiProgettiDTO.getAltreRisorseNecessarie());
				tbs1021Ambitoprogettoptof.setDesCooScu(ambitiProgettiDTO.getCooperazioneConAltreScuole());
				tbs1021Ambitoprogettoptof.setDesDenPgtCur(ambitiProgettiDTO.getDenominazione());
				tbs1021Ambitoprogettoptof.setDesDstPgt(ambitiProgettiDTO.getDestinatari());
				tbs1021Ambitoprogettoptof.setDesModForUti(ambitiProgettiDTO.getModalitApprocciFormativiUtilizzati());
				tbs1021Ambitoprogettoptof.setDesObiPri(ambitiProgettiDTO.getObiettiviAltrePriorita());
				tbs1021Ambitoprogettoptof.setDesPerSvo(ambitiProgettiDTO.getPeriodoDiSvolgimento());
				tbs1021Ambitoprogettoptof.setDesPrpCnt(ambitiProgettiDTO.getPrincipaliContenuti());
				tbs1021Ambitoprogettoptof.setDesRilScu(ambitiProgettiDTO.getRilevanzaPerScuola());
				tbs1021Ambitoprogettoptof.setDesRisUma(ambitiProgettiDTO.getRisorseUmaneArea());
				tbs1021Ambitoprogettoptof.setDesStaAva(ambitiProgettiDTO.getStatoAvanzamento());
				tbs1021Ambitoprogettoptof.setImpRisFin(ambitiProgettiDTO.getRisorseFinanziareNecessarie());
				tbs1021Ambitoprogettoptof.setTbs1004Datisezionesottosez(tbs1004DB);
				Tbs1020Classifprogetambito tbs1020Classifprogetambito = new Tbs1020Classifprogetambito();
				Tbs1020ClassifprogetambitoPK pk = new Tbs1020ClassifprogetambitoPK();
				pk.setCodTipAmb(ambitiProgettiDTO.getAmbito().getValue());
				pk.setPrgPgtAmb(Long.valueOf(ambitiProgettiDTO.getTipologiaProgetto().getValue()));
				tbs1020Classifprogetambito.setId(pk);
				tbs1021Ambitoprogettoptof.setTbs1020Classifprogetambito(tbs1020Classifprogetambito);
				
				tbs1021Ambitoprogettoptof = tbs1021AmbitoprogettoptofRepository.save(tbs1021Ambitoprogettoptof);
				ambitiProgettiDTO.setKey(tbs1021Ambitoprogettoptof.getPrgAmbPgtPto().toString());
			}
			break;

		case  S_ALTRI_PROGETTI_CURRICULARI_EXSTRA:
			ComponenteAltriProgettiCurriculariDTO componenteAltriProgettiCurriculariDTO = (ComponenteAltriProgettiCurriculariDTO)componete;
			for (AmbitiProgettiDTO ambitiProgettiDTO : componenteAltriProgettiCurriculariDTO.getItems()){
				logger.debug("save progetti su Tbs1022Ambitoprogettoaltro key : " + ambitiProgettiDTO.getKey());
				Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro = new Tbs1022Ambitoprogettoaltro();
				
				if (ambitiProgettiDTO.getKey() != null && !ambitiProgettiDTO.getKey().isEmpty()){
					tbs1022Ambitoprogettoaltro.setPrgAmbPgtAlt(Long.valueOf(ambitiProgettiDTO.getKey()));
				}
				
				tbs1022Ambitoprogettoaltro.setCodAreTem(ambitiProgettiDTO.getAreaTematicaPNSD());
				tbs1022Ambitoprogettoaltro.setDatFinVal(ambitiProgettiDTO.getDurataAl());
				tbs1022Ambitoprogettoaltro.setDatIniVal(ambitiProgettiDTO.getDurataDal());
				tbs1022Ambitoprogettoaltro.setDesAltRis(ambitiProgettiDTO.getAltreRisorseNecessarie());
				tbs1022Ambitoprogettoaltro.setDesCooScu(ambitiProgettiDTO.getCooperazioneConAltreScuole());
				tbs1022Ambitoprogettoaltro.setDesDenPgtAlt(ambitiProgettiDTO.getTipologiaProgetto().getValue());
				tbs1022Ambitoprogettoaltro.setDesDenPgtCur(ambitiProgettiDTO.getDenominazione());
				tbs1022Ambitoprogettoaltro.setDesDstPgt(ambitiProgettiDTO.getDestinatari());
				tbs1022Ambitoprogettoaltro.setDesModForUti(ambitiProgettiDTO.getModalitApprocciFormativiUtilizzati());
				tbs1022Ambitoprogettoaltro.setDesObiPri(ambitiProgettiDTO.getObiettiviAltrePriorita());
				tbs1022Ambitoprogettoaltro.setDesPerSvo(ambitiProgettiDTO.getPeriodoDiSvolgimento());
				tbs1022Ambitoprogettoaltro.setDesPrpCnt(ambitiProgettiDTO.getPrincipaliContenuti());
				tbs1022Ambitoprogettoaltro.setDesRilScu(ambitiProgettiDTO.getRilevanzaPerScuola());
				tbs1022Ambitoprogettoaltro.setDesRisUma(ambitiProgettiDTO.getRisorseUmaneArea());
				tbs1022Ambitoprogettoaltro.setDesStaAva(ambitiProgettiDTO.getStatoAvanzamento());
				tbs1022Ambitoprogettoaltro.setImpRisFin(ambitiProgettiDTO.getRisorseFinanziareNecessarie());
				tbs1022Ambitoprogettoaltro.setTbs1004Datisezionesottosez(tbs1004DB);
				Tbs1019Tipoambito tbs1019Tipoambito = new Tbs1019Tipoambito();
				tbs1019Tipoambito.setCodTipAmb(ambitiProgettiDTO.getAmbito().getValue());
				tbs1022Ambitoprogettoaltro.setTbs1019Tipoambito(tbs1019Tipoambito);
				
				tbs1022Ambitoprogettoaltro = tbs1022AmbitoprogettoaltroRepository.save(tbs1022Ambitoprogettoaltro);
				ambitiProgettiDTO.setKey(tbs1022Ambitoprogettoaltro.getPrgAmbPgtAlt().toString());
			}
			break;
		
		case  S_ALTRE_INIZIATIVE_DIDATTICO:
			ComponenteAltreIniziativeDidaDTO componenteAltreIniziativeDidaDTO = (ComponenteAltreIniziativeDidaDTO)componete;
			for (IniziativeDidatticheEducativeDTO iniziativeDidatticheEducativeDTO : componenteAltreIniziativeDidaDTO.getItems()){
				logger.debug("save altre inizi didattiche su tbs1023Altreinizididattiche key : " + iniziativeDidatticheEducativeDTO.getKey());
				Tbs1023Altreinizididattiche tbs1023Altreinizididattiche = new Tbs1023Altreinizididattiche();
				
				if (iniziativeDidatticheEducativeDTO.getKey() != null && !iniziativeDidatticheEducativeDTO.getKey().isEmpty()){
					tbs1023Altreinizididattiche.setPrgAltIniDid(Long.valueOf(iniziativeDidatticheEducativeDTO.getKey()));
				}
				
				tbs1023Altreinizididattiche.setDatFinVal(iniziativeDidatticheEducativeDTO.getDataFine());
				tbs1023Altreinizididattiche.setDatIniVal(iniziativeDidatticheEducativeDTO.getDataInizio());
				tbs1023Altreinizididattiche.setDesAreTemPns(iniziativeDidatticheEducativeDTO.getAreaTematicaPNSD());
				tbs1023Altreinizididattiche.setDesCnu(iniziativeDidatticheEducativeDTO.getContenuti());
				tbs1023Altreinizididattiche.setDesMod(iniziativeDidatticheEducativeDTO.getModalita());
				tbs1023Altreinizididattiche.setDesNot(iniziativeDidatticheEducativeDTO.getNote()); 
				tbs1023Altreinizididattiche.setDesObi(iniziativeDidatticheEducativeDTO.getObiettivi());
				tbs1023Altreinizididattiche.setDesTit(iniziativeDidatticheEducativeDTO.getTitoli());
				tbs1023Altreinizididattiche.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1023Altreinizididattiche = tbs1023AltreinizididatticheRepository.save(tbs1023Altreinizididattiche);
				iniziativeDidatticheEducativeDTO.setKey(tbs1023Altreinizididattiche.getPrgAltIniDid().toString());
			}
			break;
		
		case  S_ATTIVITA_SOSTEGNO:
			ComponenteAttivitaSostegnoDTO componenteAttivitaSostegnoDTO = (ComponenteAttivitaSostegnoDTO)componete;
			for (AttivitaSostegnoDTO attivitaSostegnoDTO : componenteAttivitaSostegnoDTO.getItems()){
				logger.debug("save attivita sostegno su tbs1024Attivitasostegnoptof key : " + attivitaSostegnoDTO.getKey());
				Tbs1024Attivitasostegnoptof tbs1024Attivitasostegnoptof = new Tbs1024Attivitasostegnoptof();
				
				if (attivitaSostegnoDTO.getKey() != null && !attivitaSostegnoDTO.getKey().isEmpty()){
					tbs1024Attivitasostegnoptof.setPrgAttSos(Long.valueOf(attivitaSostegnoDTO.getKey()));
				}
				
				tbs1024Attivitasostegnoptof.setDesAspSupLog(attivitaSostegnoDTO.getAspettiSupportoLogistico());
				tbs1024Attivitasostegnoptof.setDesCntAttSos(attivitaSostegnoDTO.getContenutiAttivitaSostegno());
				tbs1024Attivitasostegnoptof.setDesCooSssAss(attivitaSostegnoDTO.getCoopServiziSSAssocSettore());
				tbs1024Attivitasostegnoptof.setDesMetUti(attivitaSostegnoDTO.getMetodologieUtilizzate());
				tbs1024Attivitasostegnoptof.setDesNot(attivitaSostegnoDTO.getNote());
				tbs1024Attivitasostegnoptof.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1024Attivitasostegnoptof = tbs1024AttivitasostegnoptofRepository.save(tbs1024Attivitasostegnoptof);
				attivitaSostegnoDTO.setKey(tbs1024Attivitasostegnoptof.getPrgAttSos().toString());
			}
			break;
		
		case  S_ORGANIZZAZIONE_RISORSE:
			ComponenteOrganigrammaDTO componenteOrganigrammaDTO = (ComponenteOrganigrammaDTO)componete;
			for (OrganigrammaDTO organigrammaDTO : componenteOrganigrammaDTO.getItems()){
				logger.debug("save organigramma su Tbs1026Organrisorseptof key : " + organigrammaDTO.getKey());
				Tbs1026Organrisorseptof tbs1026Organrisorseptof = new Tbs1026Organrisorseptof();
				
				if (organigrammaDTO.getKey() != null && !organigrammaDTO.getKey().isEmpty()){
					tbs1026Organrisorseptof.setPrgOrgRis(Long.valueOf(organigrammaDTO.getKey()));
				}
				
				tbs1026Organrisorseptof.setDesNotRsp(organigrammaDTO.getNoteResponsabilita());
				tbs1026Organrisorseptof.setDesResRuo(organigrammaDTO.getNome());
				tbs1026Organrisorseptof.setDesRsp(organigrammaDTO.getResponsabilita());
				tbs1026Organrisorseptof.setDesRuoAlt(null);
				tbs1026Organrisorseptof.setTbs1004Datisezionesottosez(tbs1004DB);
				Tbs1025Tiporuolo tbs1025Tiporuolo = new Tbs1025Tiporuolo();
				tbs1025Tiporuolo.setCodTipRuo(organigrammaDTO.getRuolo().getValue());
				tbs1026Organrisorseptof.setTbs1025Tiporuolo(tbs1025Tiporuolo);
				
				tbs1026Organrisorseptof = tbs1026OrganrisorseptofRepository.save(tbs1026Organrisorseptof);
				organigrammaDTO.setKey(tbs1026Organrisorseptof.getPrgOrgRis().toString());
			}
			break;
		
		case  S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI:
			ComponenteOrganigrammaAltriRuoliDTO componenteOrganigrammaAltriRuoliDTO = (ComponenteOrganigrammaAltriRuoliDTO)componete;
			for (OrganigrammaDTO organigrammaDTO : componenteOrganigrammaAltriRuoliDTO.getItems()){
				logger.debug("save organigramma su Tbs1026Organrisorseptof key : " + organigrammaDTO.getKey());
				Tbs1026Organrisorseptof tbs1026Organrisorseptof = new Tbs1026Organrisorseptof();
				
				if (organigrammaDTO.getKey() != null && !organigrammaDTO.getKey().isEmpty()){
					tbs1026Organrisorseptof.setPrgOrgRis(Long.valueOf(organigrammaDTO.getKey()));
				}
				
				tbs1026Organrisorseptof.setDesNotRsp(organigrammaDTO.getNoteResponsabilita());
				tbs1026Organrisorseptof.setDesResRuo(organigrammaDTO.getNome());
				tbs1026Organrisorseptof.setDesRsp(organigrammaDTO.getResponsabilita());
				tbs1026Organrisorseptof.setDesRuoAlt(organigrammaDTO.getRuolo().getValue());
				tbs1026Organrisorseptof.setTbs1004Datisezionesottosez(tbs1004DB);
				tbs1026Organrisorseptof.setTbs1025Tiporuolo(null);
				
				tbs1026Organrisorseptof = tbs1026OrganrisorseptofRepository.save(tbs1026Organrisorseptof);
				organigrammaDTO.setKey(tbs1026Organrisorseptof.getPrgOrgRis().toString());
			}
			break;
		
		case S_PROGRAMMAZIONE_FORMAZIONE_DOC:
		case S_PROGRAMMAZIONE_FORMAZIONE_AMM:
		case S_PROGRAMMAZIONE_FORMAZIONE_TEC:
		case S_PROGRAMMAZIONE_FORMAZIONE_AUS:
			
			ComponenteDTO componenteProgrammazioneDTO = null;
			LinkedList<ProgrammazioneFormDTO> items = null;
	    	switch (tipoComponente) {
			case S_PROGRAMMAZIONE_FORMAZIONE_DOC:
				componenteProgrammazioneDTO = (ComponenteProgrammazioneFormDocDTO)componete;
				items = ((ComponenteProgrammazioneFormDocDTO)componenteProgrammazioneDTO).getItems();
				break;
			case S_PROGRAMMAZIONE_FORMAZIONE_AMM:
				componenteProgrammazioneDTO = (ComponenteProgrammazioneFormAmmDTO)componete;
				items = ((ComponenteProgrammazioneFormAmmDTO)componenteProgrammazioneDTO).getItems();
				break;
			case S_PROGRAMMAZIONE_FORMAZIONE_TEC:
				componenteProgrammazioneDTO = (ComponenteProgrammazioneFormTecDTO)componete;
				items = ((ComponenteProgrammazioneFormTecDTO)componenteProgrammazioneDTO).getItems();
				break;
			case S_PROGRAMMAZIONE_FORMAZIONE_AUS:
				componenteProgrammazioneDTO = (ComponenteProgrammazioneFormAusDTO)componete;
				items = ((ComponenteProgrammazioneFormAusDTO)componenteProgrammazioneDTO).getItems();
				break;

			default:
				throw new RuntimeException("Tipo componente non gestito");
			}
	    	
			
			for (ProgrammazioneFormDTO programmazioneFormDTO : items){
				logger.debug("save programmazione form su Tbs1027Performazpersonale key : " + programmazioneFormDTO.getKey());
				Tbs1027Performazpersonale tbs1027Performazpersonale = new Tbs1027Performazpersonale();
				
				if (programmazioneFormDTO.getKey() != null && !programmazioneFormDTO.getKey().isEmpty()){
					tbs1027Performazpersonale.setPrgForPer(Long.valueOf(programmazioneFormDTO.getKey()));
				}
				
				tbs1027Performazpersonale.setDatFinVal(programmazioneFormDTO.getDataFine());
				tbs1027Performazpersonale.setDatIniVal(programmazioneFormDTO.getDataInizio());
				tbs1027Performazpersonale.setDesAreTemPsd(programmazioneFormDTO.getAreaTematicaPNSD());
				tbs1027Performazpersonale.setDesCnt(programmazioneFormDTO.getContenuti());
				tbs1027Performazpersonale.setDesObi(programmazioneFormDTO.getObiettivi());
				if (programmazioneFormDTO.getTempiOreComplessive() != null){
					tbs1027Performazpersonale.setDesOreCmp(programmazioneFormDTO.getTempiOreComplessive().toString());
				}
				tbs1027Performazpersonale.setDesPerFor(programmazioneFormDTO.getDenominazionePercorsoFormativo());
				if (programmazioneFormDTO.getCondiviso() != null){
					tbs1027Performazpersonale.setFlgCnd(programmazioneFormDTO.getCondiviso().getValue());
				}
				if (programmazioneFormDTO.getCollaborazioneReteScolastic() != null){
					tbs1027Performazpersonale.setFlgColRes(programmazioneFormDTO.getCollaborazioneReteScolastic().getValue());
				}
				if (programmazioneFormDTO.getPercorsoFormativo() != null){
					tbs1027Performazpersonale.setFlgForOts(programmazioneFormDTO.getPercorsoFormativo().getValue());
				}
				Tbs1019Tipoambito tbs1019Tipoambito = new Tbs1019Tipoambito();
				tbs1019Tipoambito.setCodTipAmb(programmazioneFormDTO.getAmbitoFormativo().getValue());
				tbs1027Performazpersonale.setTbs1019Tipoambito(tbs1019Tipoambito);
				tbs1027Performazpersonale.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1027Performazpersonale = tbs1027PerformazpersonaleRepository.save(tbs1027Performazpersonale);
				programmazioneFormDTO.setKey(tbs1027Performazpersonale.getPrgForPer().toString());
			}
			break;
		
		case  S_STRUMENTI_ATTREZZATURE_TECNOLOGIA:
			ComponenteStrumentiAttrezzatureTecnologicheDTO componenteStrumentiAttrezzatureTecnologicheDTO = (ComponenteStrumentiAttrezzatureTecnologicheDTO)componete;
			for (StrumentiAttrezzatureTecnologicheDTO strumentiAttrezzatureTecnologicheDTO : componenteStrumentiAttrezzatureTecnologicheDTO.getItems()){
				logger.debug("save strumenti attrezzature tecnologiche su Tbs1028Attrezzature key : " + strumentiAttrezzatureTecnologicheDTO.getKey());
				Tbs1028Attrezzature tbs1028Attrezzature = new Tbs1028Attrezzature();
				
				if (strumentiAttrezzatureTecnologicheDTO.getKey() != null && !strumentiAttrezzatureTecnologicheDTO.getKey().isEmpty()){
					tbs1028Attrezzature.setPrgAtt(Long.valueOf(strumentiAttrezzatureTecnologicheDTO.getKey()));
				}
				
				tbs1028Attrezzature.setDesAreTemPsd(strumentiAttrezzatureTecnologicheDTO.getAreaTematicaPNSD());
				tbs1028Attrezzature.setDesObbIst(strumentiAttrezzatureTecnologicheDTO.getDescrizione());
				tbs1028Attrezzature.setDesStrAtt(strumentiAttrezzatureTecnologicheDTO.getStrumentiAttrezzature());
				tbs1028Attrezzature.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1028Attrezzature = tbs1028AttrezzatureRepository.save(tbs1028Attrezzature);
				strumentiAttrezzatureTecnologicheDTO.setKey(tbs1028Attrezzature.getPrgAtt().toString());
			}
			break;
		
		case  S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE:
			ComponenteDotazioneIstPriDTO componenteDotazioneIstPriDTO = (ComponenteDotazioneIstPriDTO)componete;
			for (DotazioneIstPriDTO dotazioneIstPriDTO : componenteDotazioneIstPriDTO.getItems()){
				logger.debug("save dotazione Ist Pri su Tbs1029Dotazionemultimediale key : " + dotazioneIstPriDTO.getKey());
				Tbs1029Dotazionemultimediale tbs1029Dotazionemultimediale = new Tbs1029Dotazionemultimediale();
				
				if (dotazioneIstPriDTO.getKey() != null && !dotazioneIstPriDTO.getKey().isEmpty()){
					tbs1029Dotazionemultimediale.setPrgDotMus(Long.valueOf(dotazioneIstPriDTO.getKey()));
				}
				
				tbs1029Dotazionemultimediale.setDesAreTemPsd(dotazioneIstPriDTO.getAreaTematicaPNSD());
				tbs1029Dotazionemultimediale.setDesDotMus(dotazioneIstPriDTO.getDotazioniMultimediali());
				if (dotazioneIstPriDTO.getNumero() != null){
					tbs1029Dotazionemultimediale.setNumDotMld(dotazioneIstPriDTO.getNumero().longValue());
				}
				tbs1029Dotazionemultimediale.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1029Dotazionemultimediale = tbs1029DotazionemultimedialeRepository.save(tbs1029Dotazionemultimediale);
				dotazioneIstPriDTO.setKey(tbs1029Dotazionemultimediale.getPrgDotMus().toString());
			}
			break;

		case  S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO:
			ComponentePromozioneRapportiEntiTerritorioDTO componentePromozioneRapportiEntiTerritorioDTO = (ComponentePromozioneRapportiEntiTerritorioDTO)componete;
			for (PromozioneRapportiEntiTerritorioDTO promozioneRapportiEntiTerritorioDTO : componentePromozioneRapportiEntiTerritorioDTO.getItems()){
				logger.debug("save promozione rapporti enti territorio su Tbs1030Rapportoente key : " + promozioneRapportiEntiTerritorioDTO.getKey());
				Tbs1030Rapportoente tbs1030Rapportoente = new Tbs1030Rapportoente();
				
				if (promozioneRapportiEntiTerritorioDTO.getKey() != null && !promozioneRapportiEntiTerritorioDTO.getKey().isEmpty()){
					tbs1030Rapportoente.setPrgRapEnt(Long.valueOf(promozioneRapportiEntiTerritorioDTO.getKey()));
				}
				
				tbs1030Rapportoente.setDesAreTemPsd(promozioneRapportiEntiTerritorioDTO.getAreaTematicaPNSD());
				tbs1030Rapportoente.setDesAziInt(promozioneRapportiEntiTerritorioDTO.getAzioniIntraprese());
				tbs1030Rapportoente.setDesEntLoc(promozioneRapportiEntiTerritorioDTO.getEntiLocali());
				tbs1030Rapportoente.setDesPro(promozioneRapportiEntiTerritorioDTO.getTipologiaPromozione());
				tbs1030Rapportoente.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1030Rapportoente = tbs1030RapportoenteRepository.save(tbs1030Rapportoente);
				promozioneRapportiEntiTerritorioDTO.setKey(tbs1030Rapportoente.getPrgRapEnt().toString());
			}
			break;
		
		case  S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO:
			ComponenteConvPotenzFormativoDTO componenteConvPotenzFormativoDTO = (ComponenteConvPotenzFormativoDTO)componete;
			for (ConvPotenzFormativoDTO convPotenzFormativoDTO : componenteConvPotenzFormativoDTO.getItems()){
				logger.debug("save conv potenz formativo su Tbs1031Convenzioneforma key : " + convPotenzFormativoDTO.getKey());
				Tbs1031Convenzioneforma tbs1031Convenzioneforma = new Tbs1031Convenzioneforma();
				
				if (convPotenzFormativoDTO.getKey() != null && !convPotenzFormativoDTO.getKey().isEmpty()){
					tbs1031Convenzioneforma.setPrgCnvFor(Long.valueOf(convPotenzFormativoDTO.getKey()));
				}
				
				tbs1031Convenzioneforma.setDesAreTemPsd(convPotenzFormativoDTO.getAreaTematicaPNSD());
				tbs1031Convenzioneforma.setDesCnv(convPotenzFormativoDTO.getConvenzioni());
				tbs1031Convenzioneforma.setDesNot(convPotenzFormativoDTO.getNote());
				tbs1031Convenzioneforma.setDesOrg(convPotenzFormativoDTO.getOrganizzazione());
				tbs1031Convenzioneforma.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1031Convenzioneforma = tbs1031ConvenzioneformaRepository.save(tbs1031Convenzioneforma);
				convPotenzFormativoDTO.setKey(tbs1031Convenzioneforma.getPrgCnvFor().toString());
			}
			break;
		
		case  S_PIANIFICAZIONE_ATTIVITA:
			ComponentePianificazioneInterventiMonitoraggioDTO componentePianificazioneInterventiMonitoraggioDTO = (ComponentePianificazioneInterventiMonitoraggioDTO)componete;
			for (PianificazioneInterventiMonitoraggioDTO pianificazioneInterventiMonitoraggioDTO : componentePianificazioneInterventiMonitoraggioDTO.getItems()){
				logger.debug("save pianificazione interventi monitoraggio su Tbs1032Pianificattivita key : " + pianificazioneInterventiMonitoraggioDTO.getKey());
				Tbs1032Pianificattivita tbs1032Pianificattivita = new Tbs1032Pianificattivita();
				
				if (pianificazioneInterventiMonitoraggioDTO.getKey() != null && !pianificazioneInterventiMonitoraggioDTO.getKey().isEmpty()){
					tbs1032Pianificattivita.setPrgPiaAtt(Long.valueOf(pianificazioneInterventiMonitoraggioDTO.getKey()));
				}
				
				tbs1032Pianificattivita.setDatFinVal(pianificazioneInterventiMonitoraggioDTO.getDataFine());
				tbs1032Pianificattivita.setDatIniVal(pianificazioneInterventiMonitoraggioDTO.getDataInizio());
				tbs1032Pianificattivita.setDesCapPto(pianificazioneInterventiMonitoraggioDTO.getCapitoloPTOF());
				tbs1032Pianificattivita.setDesIntMon(pianificazioneInterventiMonitoraggioDTO.getDescrizione());
				tbs1032Pianificattivita.setDesPiaAtt(pianificazioneInterventiMonitoraggioDTO.getPianificazioneAttivita());
				tbs1032Pianificattivita.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1032Pianificattivita = tbs1032PianificattivitaRepository.save(tbs1032Pianificattivita);
				pianificazioneInterventiMonitoraggioDTO.setKey(tbs1032Pianificattivita.getPrgPiaAtt().toString());
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_AA:
			ComponenteArticolazioneClassiAADTO componenteArticolazioneClassiAADTO = (ComponenteArticolazioneClassiAADTO)componete;
			for (ArticolazioneClassiAADTO articolazioneClassiAADTO : componenteArticolazioneClassiAADTO.getItems()){
				Tbs1034Articoclasseinfanzia tbs1034Articoclasseinfanzia = new Tbs1034Articoclasseinfanzia();
				
				if (articolazioneClassiAADTO.getKey() != null && !articolazioneClassiAADTO.getKey().isEmpty()){
					tbs1034Articoclasseinfanzia.setPrgArcInf(Long.valueOf(articolazioneClassiAADTO.getKey()));
				}
				
				tbs1034Articoclasseinfanzia.setCodTipPos(articolazioneClassiAADTO.getTipologiaPosti());
				tbs1034Articoclasseinfanzia.setDesTipPos(articolazioneClassiAADTO.getDescrizione());
				tbs1034Articoclasseinfanzia.setNumSezNor(articolazioneClassiAADTO.getSezioneOrarioNormale());
				tbs1034Articoclasseinfanzia.setNumSezRid(articolazioneClassiAADTO.getSezioneOrarioRidotto());
				tbs1034Articoclasseinfanzia.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1034Articoclasseinfanzia = tbs1034ArticoclasseinfanziaRepository.save(tbs1034Articoclasseinfanzia);
				articolazioneClassiAADTO.setKey(tbs1034Articoclasseinfanzia.getPrgArcInf().toString());
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_EE:
			ComponenteArticolazioneClassiEEDTO componenteArticolazioneClassiEEDTO = (ComponenteArticolazioneClassiEEDTO)componete;
			for (ArticolazioneClassiEEDTO articolazioneClassiEEDTO : componenteArticolazioneClassiEEDTO.getItems()){
				Tbs1035Articoclasseprimaria tbs1035Articoclasseprimaria = new Tbs1035Articoclasseprimaria();
				
				if (articolazioneClassiEEDTO.getKey() != null && !articolazioneClassiEEDTO.getKey().isEmpty()){
					tbs1035Articoclasseprimaria.setPrgArcPri(Long.valueOf(articolazioneClassiEEDTO.getKey()));
				}
				
				tbs1035Articoclasseprimaria.setCodTipPos(articolazioneClassiEEDTO.getTipologiaPosti());
				tbs1035Articoclasseprimaria.setDesTipPos(articolazioneClassiEEDTO.getDescrizione());
				tbs1035Articoclasseprimaria.setNumClaTmpN01(articolazioneClassiEEDTO.getClassiAlunniTempoNormaleI());
				tbs1035Articoclasseprimaria.setNumClaTmpN02(articolazioneClassiEEDTO.getClassiAlunniTempoNormaleII());
				tbs1035Articoclasseprimaria.setNumClaTmpN03(articolazioneClassiEEDTO.getClassiAlunniTempoNormaleIII());
				tbs1035Articoclasseprimaria.setNumClaTmpN04(articolazioneClassiEEDTO.getClassiAlunniTempoNormaleIV());
				tbs1035Articoclasseprimaria.setNumClaTmpN05(articolazioneClassiEEDTO.getClassiAlunniTempoNormaleV());
				tbs1035Articoclasseprimaria.setNumClaTmpNpl(articolazioneClassiEEDTO.getClassiAlunniTempoNormalePLURICL());
				tbs1035Articoclasseprimaria.setNumClaTmpP01(articolazioneClassiEEDTO.getClassiAlunniTempoPienoI());
				tbs1035Articoclasseprimaria.setNumClaTmpP02(articolazioneClassiEEDTO.getClassiAlunniTempoPienoII());
				tbs1035Articoclasseprimaria.setNumClaTmpP03(articolazioneClassiEEDTO.getClassiAlunniTempoPienoIII());
				tbs1035Articoclasseprimaria.setNumClaTmpP04(articolazioneClassiEEDTO.getClassiAlunniTempoPienoIV());
				tbs1035Articoclasseprimaria.setNumClaTmpP05(articolazioneClassiEEDTO.getClassiAlunniTempoPienoV());
				tbs1035Articoclasseprimaria.setNumClaTmpPpl(articolazioneClassiEEDTO.getClassiAlunniTempoPienoPLURICL());
				tbs1035Articoclasseprimaria.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1035Articoclasseprimaria = tbs1035ArticoclasseprimariaRepository.save(tbs1035Articoclasseprimaria);
				articolazioneClassiEEDTO.setKey(tbs1035Articoclasseprimaria.getPrgArcPri().toString());
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_MMI:
			ComponenteArticolazioneClassiMMIDTO componenteArticolazioneClassiMMIDTO = (ComponenteArticolazioneClassiMMIDTO)componete;
			for (ArticolazioneClassiIMMIDTO articolazioneClassiIMMIDTO : componenteArticolazioneClassiMMIDTO.getItems()){
				Tbs1036Articolaclasseigrado tbs1036Articolaclasseigrado = new Tbs1036Articolaclasseigrado();
				
				if (articolazioneClassiIMMIDTO.getKey() != null && !articolazioneClassiIMMIDTO.getKey().isEmpty()){
					tbs1036Articolaclasseigrado.setPrgArcPgr(Long.valueOf(articolazioneClassiIMMIDTO.getKey()));
				}
				
				tbs1036Articolaclasseigrado.setNumClaTmpN01(articolazioneClassiIMMIDTO.getTempoNormaleTotaliClassiI());
				tbs1036Articolaclasseigrado.setNumClaTmpN02(articolazioneClassiIMMIDTO.getTempoNormaleTotaliClassiII());
				tbs1036Articolaclasseigrado.setNumClaTmpN03(articolazioneClassiIMMIDTO.getTempoNormaleTotaliClassiIII());
				tbs1036Articolaclasseigrado.setNumClaTmpP01(articolazioneClassiIMMIDTO.getTempoProlungatoTotaliClassiI());
				tbs1036Articolaclasseigrado.setNumClaTmpP02(articolazioneClassiIMMIDTO.getTempoProlungatoTotaliClassiII());
				tbs1036Articolaclasseigrado.setNumClaTmpP03(articolazioneClassiIMMIDTO.getTempoProlungatoTotaliClassiIII());
				
				tbs1036Articolaclasseigrado.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1036Articolaclasseigrado = tbs1036ArticolaclasseigradoRepository.save(tbs1036Articolaclasseigrado);
				articolazioneClassiIMMIDTO.setKey(tbs1036Articolaclasseigrado.getPrgArcPgr().toString());
				
			}
			break;
		
		case  S_ORGANIZZAZIONE_CLASSI_MMII:
			ComponenteArticolazioneClassiMMIIDTO componenteArticolazioneClassiMMIIDTO = (ComponenteArticolazioneClassiMMIIDTO)componete;
			for (ArticolazioneClassiMMIIDTO articolazioneClassiMMIIDTO : componenteArticolazioneClassiMMIIDTO.getItems()){
				Tbs1038Articolaclasseiigrado tbs1038Articolaclasseiigrado = new Tbs1038Articolaclasseiigrado();
				
				if (articolazioneClassiMMIIDTO.getKey() != null && !articolazioneClassiMMIIDTO.getKey().isEmpty()){
					tbs1038Articolaclasseiigrado.setPrgArcSgr(Long.valueOf(articolazioneClassiMMIIDTO.getKey()));
				}
				
				tbs1038Articolaclasseiigrado.setDesInd(articolazioneClassiMMIIDTO.getIndirizzo());
				tbs1038Articolaclasseiigrado.setNumCla001(articolazioneClassiMMIIDTO.getClassiI());
				tbs1038Articolaclasseiigrado.setNumCla002(articolazioneClassiMMIIDTO.getClassiII());
				tbs1038Articolaclasseiigrado.setNumCla003(articolazioneClassiMMIIDTO.getClassiIII());
				tbs1038Articolaclasseiigrado.setNumCla004(articolazioneClassiMMIIDTO.getClassiIV());
				tbs1038Articolaclasseiigrado.setNumCla005(articolazioneClassiMMIIDTO.getClassiV());
				tbs1038Articolaclasseiigrado.setNumCla006(articolazioneClassiMMIIDTO.getClassiVI());
				
				tbs1038Articolaclasseiigrado.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1038Articolaclasseiigrado = tbs1038ArticolaclasseiigradoRepository.save(tbs1038Articolaclasseiigrado);
				articolazioneClassiMMIIDTO.setKey(tbs1038Articolaclasseiigrado.getPrgArcSgr().toString());
				
			}
			break;
		
		case  S_FABBISOGNO_POSTI_CATTEDRE_AA_EE: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiCattedreEEAADTO componenteFabbisognoPostiCattedreEEAADTO = (ComponenteFabbisognoPostiCattedreEEAADTO)componete;
			for (FabbisognoPostiCattedreDTO fabbisognoPostiCattedreDTO : componenteFabbisognoPostiCattedreEEAADTO.getItems()){
				Tbs1037Fabpostocomune tbs1037Fabpostocomune = tbs1037FabpostocomuneRepository.findOne(Long.valueOf(fabbisognoPostiCattedreDTO.getKey()));
				if (tbs1037Fabpostocomune != null){
					tbs1037Fabpostocomune.setDesMot(fabbisognoPostiCattedreDTO.getMotivazione());
					tbs1037Fabpostocomune.setNumPosPri(toBigDecimal(fabbisognoPostiCattedreDTO.getPostiComuniPrimoAnnoTriennio()));
					tbs1037Fabpostocomune.setNumPosSec(toBigDecimal(fabbisognoPostiCattedreDTO.getPostiComuniSecondoAnnoTriennio()));
					tbs1037Fabpostocomune.setNumPosTer(toBigDecimal(fabbisognoPostiCattedreDTO.getPostiComuniTerzoAnnoTriennio()));
					tbs1037FabpostocomuneRepository.save(tbs1037Fabpostocomune);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_CATTEDRE_MM: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiCattedreMMDTO componenteFabbisognoPostiCattedreMMDTO = (ComponenteFabbisognoPostiCattedreMMDTO)componete;
			for (FabbisognoPostiCattedreMMDTO fabbisognoPostiCattedreMMDTO : componenteFabbisognoPostiCattedreMMDTO.getItems()){
				Tbs1037Fabpostocomune tbs1037Fabpostocomune = tbs1037FabpostocomuneRepository.findOne(Long.valueOf(fabbisognoPostiCattedreMMDTO.getKey()));
				if (tbs1037Fabpostocomune != null){
					tbs1037Fabpostocomune.setDesMot(fabbisognoPostiCattedreMMDTO.getMotivazione());
					tbs1037Fabpostocomune.setNumPosPri(toBigDecimal(fabbisognoPostiCattedreMMDTO.getCattedrePrimoAnnoTriennio()));
					tbs1037Fabpostocomune.setNumPosSec(toBigDecimal(fabbisognoPostiCattedreMMDTO.getCattedreSecondoAnnoTriennio()));
					tbs1037Fabpostocomune.setNumPosTer(toBigDecimal(fabbisognoPostiCattedreMMDTO.getCattedreTerzoAnnoTriennio()));
					tbs1037FabpostocomuneRepository.save(tbs1037Fabpostocomune);
				}
			}
			break;
		
		case  S_FABBISOGNO_CONNESSO_PROGETTO:
			ComponenteFabbisognoConnessoProgettoDTO componenteFabbisognoConnessoProgettoDTO = (ComponenteFabbisognoConnessoProgettoDTO)componete;
			for (FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO : componenteFabbisognoConnessoProgettoDTO.getItems()){
				String tipoProgetto = fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(0, 1);
				if (tipoProgetto.equals("P")){
					Long prgAmbPgtPto = Long.valueOf(fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(1));
					Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof = tbs1021AmbitoprogettoptofRepository.findOne(prgAmbPgtPto);
					if (tbs1021Ambitoprogettoptof != null){
						tbs1021Ambitoprogettoptof.setDesNomBenSer(fabbisognoConnessoProgettoDTO.getNomeBeneServizio());
						tbs1021Ambitoprogettoptof.setDesBenSer(fabbisognoConnessoProgettoDTO.getDescrizioneBeneServizio());
						tbs1021Ambitoprogettoptof.setDesCls(fabbisognoConnessoProgettoDTO.getClassificazione());
						tbs1021Ambitoprogettoptof.setNumDocAta(fabbisognoConnessoProgettoDTO.getNumeroDocentiATACoinvolti());
						tbs1021Ambitoprogettoptof.setImpSti(fabbisognoConnessoProgettoDTO.getImportoStimato());
						tbs1021Ambitoprogettoptof.setDesNot(fabbisognoConnessoProgettoDTO.getNote());
						tbs1021AmbitoprogettoptofRepository.save(tbs1021Ambitoprogettoptof);
					}
					
				}else if (tipoProgetto.equals("A")){
					Long prgAmbPgtAlt = Long.valueOf(fabbisognoConnessoProgettoDTO.getDenominazioneProgettiScuola().getValue().substring(1));
					Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro = tbs1022AmbitoprogettoaltroRepository.findOne(prgAmbPgtAlt);
					if (tbs1022Ambitoprogettoaltro != null){
						tbs1022Ambitoprogettoaltro.setDesNomBenSer(fabbisognoConnessoProgettoDTO.getNomeBeneServizio());
						tbs1022Ambitoprogettoaltro.setDesBenSer(fabbisognoConnessoProgettoDTO.getDescrizioneBeneServizio());
						tbs1022Ambitoprogettoaltro.setDesCls(fabbisognoConnessoProgettoDTO.getClassificazione());
						tbs1022Ambitoprogettoaltro.setNumDocAta(fabbisognoConnessoProgettoDTO.getNumeroDocentiATACoinvolti());
						tbs1022Ambitoprogettoaltro.setImpSti(fabbisognoConnessoProgettoDTO.getImportoStimato());
						tbs1022Ambitoprogettoaltro.setDesNot(fabbisognoConnessoProgettoDTO.getNote());
						tbs1022AmbitoprogettoaltroRepository.save(tbs1022Ambitoprogettoaltro);
					}
				}
			}
			break;
		
		case  S_FABBISOGNO_CONNESSO_FORMAZIONE:
			ComponenteFabbisognoConnessoFormazioneDTO componenteFabbisognoConnessoFormazioneDTO = (ComponenteFabbisognoConnessoFormazioneDTO)componete;
			for (FabbisognoConnessoFormazioneDTO fabbisognoConnessoFormazioneDTO : componenteFabbisognoConnessoFormazioneDTO.getItems()){
				Tbs1027Performazpersonale tbs1027Performazpersonale = tbs1027PerformazpersonaleRepository.findOne(Long.valueOf(fabbisognoConnessoFormazioneDTO.getDenominazionePercorsoFormativo().getValue()));
				if (tbs1027Performazpersonale != null){
					tbs1027Performazpersonale.setDesNomBenSer(fabbisognoConnessoFormazioneDTO.getNomeBeneServizio());
					tbs1027Performazpersonale.setDesBenSer(fabbisognoConnessoFormazioneDTO.getDescrizioneBeneServizio());
					tbs1027Performazpersonale.setDesCls(fabbisognoConnessoFormazioneDTO.getClassificazione());
					tbs1027Performazpersonale.setNumDocAta(fabbisognoConnessoFormazioneDTO.getNumeroDocentiATACoinvolti());
					tbs1027Performazpersonale.setImpSti(fabbisognoConnessoFormazioneDTO.getImportoStimato());
					tbs1027Performazpersonale.setDesNot(fabbisognoConnessoFormazioneDTO.getNote());
					tbs1027PerformazpersonaleRepository.save(tbs1027Performazpersonale);
				}


			}
			break;
		
		case  S_FABBISOGNO_ATTREZZATURE_INFRA:
			ComponenteFabbisognoAttrezzatureInfraDTO componenteFabbisognoAttrezzatureInfraDTO = (ComponenteFabbisognoAttrezzatureInfraDTO)componete;
			for (FabbisognoAttrezzatureInfraDTO fabbisognoAttrezzatureInfraDTO : componenteFabbisognoAttrezzatureInfraDTO.getItems()){
				Tbs1042Fabattrezmateriale tbs1042Fabattrezmateriale = new Tbs1042Fabattrezmateriale();
				
				if (fabbisognoAttrezzatureInfraDTO.getKey() != null && !fabbisognoAttrezzatureInfraDTO.getKey().isEmpty()){
					tbs1042Fabattrezmateriale.setPrgFabAtm(Long.valueOf(fabbisognoAttrezzatureInfraDTO.getKey()));
				}
				
				tbs1042Fabattrezmateriale.setCodOrdScu(componenteFabbisognoAttrezzatureInfraDTO.getCodOrdScu());
				tbs1042Fabattrezmateriale.setCodTipFab(componenteFabbisognoAttrezzatureInfraDTO.getCodTipFab());
				tbs1042Fabattrezmateriale.setDatAnnScoRif(tbs1004DB.getPerAnnSco());//remove: not used
				tbs1042Fabattrezmateriale.setDesAreTemPsd(fabbisognoAttrezzatureInfraDTO.getAreaTematicaPNSD());
				tbs1042Fabattrezmateriale.setDesAtm(fabbisognoAttrezzatureInfraDTO.getDescrizione());
				tbs1042Fabattrezmateriale.setDesFonFin(fabbisognoAttrezzatureInfraDTO.getFonteFinanziamento());
				if (fabbisognoAttrezzatureInfraDTO.getStimaCosti() != null){
					tbs1042Fabattrezmateriale.setDesStiCos(fabbisognoAttrezzatureInfraDTO.getStimaCosti().toString());
				}
				tbs1042Fabattrezmateriale.setDesTipAtm(fabbisognoAttrezzatureInfraDTO.getTipologia());
				tbs1042Fabattrezmateriale.setNumAtm(fabbisognoAttrezzatureInfraDTO.getNumeroPezzi());

				tbs1042Fabattrezmateriale.setTbs1004Datisezionesottosez(tbs1004DB);
				
				tbs1042Fabattrezmateriale = tbs1042FabattrezmaterialeRepository.save(tbs1042Fabattrezmateriale);
				fabbisognoAttrezzatureInfraDTO.setKey(tbs1042Fabattrezmateriale.getPrgFabAtm().toString());
			}
			break;
		
		case  S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiSostegnoEEAADTO componenteFabbisognoPostiSostegnoEEAADTO = (ComponenteFabbisognoPostiSostegnoEEAADTO)componete;
			for (FabbisognoPostiSostegnoEEAADTO fabbisognoPostiSostegnoEEAADTO : componenteFabbisognoPostiSostegnoEEAADTO.getItems()){
				Tbs1039Fabpostosostegno tbs1039Fabpostosostegno = tbs1039FabpostosostegnoRepository.findOne(Long.valueOf(fabbisognoPostiSostegnoEEAADTO.getKey()));
				if (tbs1039Fabpostosostegno != null){
					tbs1039Fabpostosostegno.setDesMot(fabbisognoPostiSostegnoEEAADTO.getMotivazione());
					
					tbs1039Fabpostosostegno.setNumPosUdiPri(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoUditoPrimoTriennio()));
					tbs1039Fabpostosostegno.setNumPosUdiSec(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoUditoSecondoTriennio()));
					tbs1039Fabpostosostegno.setNumPosUdiTer(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoUditoTerzoTriennio()));

					tbs1039Fabpostosostegno.setNumPosVisPri(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoVistaPrimoTriennio()));
					tbs1039Fabpostosostegno.setNumPosVisSec(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoVistaSecondoTriennio()));
					tbs1039Fabpostosostegno.setNumPosVisTer(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoVistaTerzoTriennio()));
					
					tbs1039Fabpostosostegno.setNumPosPsiPri(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoPsicofisicoPrimoTriennio()));
					tbs1039Fabpostosostegno.setNumPosPsiSec(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoPsicofisicoSecondoTriennio()));
					tbs1039Fabpostosostegno.setNumPosPsiTer(toBigDecimal(fabbisognoPostiSostegnoEEAADTO.getPostiSostegnoPsicofisicoTerzoTriennio()));
					
					tbs1039FabpostosostegnoRepository.save(tbs1039Fabpostosostegno);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_SOSTEGNO_MMII: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiSostegnoMMIIDTO componenteFabbisognoPostiSostegnoMMIIDTO = (ComponenteFabbisognoPostiSostegnoMMIIDTO)componete;
			for (FabbisognoPostiSostegnoMMIIDTO fabbisognoPostiSostegnoMMIIDTO : componenteFabbisognoPostiSostegnoMMIIDTO.getItems()){
				Tbs1039Fabpostosostegno tbs1039Fabpostosostegno = tbs1039FabpostosostegnoRepository.findOne(Long.valueOf(fabbisognoPostiSostegnoMMIIDTO.getKey()));
				if (tbs1039Fabpostosostegno != null){
					tbs1039Fabpostosostegno.setDesMot(fabbisognoPostiSostegnoMMIIDTO.getMotivazione());
					
					tbs1039Fabpostosostegno.setNumPosSsgPri(toBigDecimal(fabbisognoPostiSostegnoMMIIDTO.getNumeroPostiPrimoAnnoTriennio()));
					tbs1039Fabpostosostegno.setNumPosSsgSec(toBigDecimal(fabbisognoPostiSostegnoMMIIDTO.getNumeroPostiSecondoAnnoTriennio()));
					tbs1039Fabpostosostegno.setNumPosSsgTer(toBigDecimal(fabbisognoPostiSostegnoMMIIDTO.getNumeroPostiTerzoAnnoTriennio()));

					tbs1039FabpostosostegnoRepository.save(tbs1039Fabpostosostegno);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiPotenziamentoEEAADTO componenteFabbisognoPostiPotenziamentoEEAADTO = (ComponenteFabbisognoPostiPotenziamentoEEAADTO)componete;
			for (FabbisognoPostiPotenziamentoEEAADTO fabbisognoPostiPotenziamentoEEAADTO : componenteFabbisognoPostiPotenziamentoEEAADTO.getItems()){
				Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento = tbs1040FabpostopotenziamentoRepository.findOne(Long.valueOf(fabbisognoPostiPotenziamentoEEAADTO.getKey()));
				if (tbs1040Fabpostopotenziamento != null){
					tbs1040Fabpostopotenziamento.setDesMot(fabbisognoPostiPotenziamentoEEAADTO.getMotivazione());
					
					tbs1040Fabpostopotenziamento.setNumPosComPri(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiComuniPrimoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosComSec(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiComuniSecondoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosComTer(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiComuniTerzoAnno()));
					
					tbs1040Fabpostopotenziamento.setNumPosSosPri(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiSostegnoPrimoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosSosSec(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiSostegnoSecondoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosSosTer(toBigDecimal(fabbisognoPostiPotenziamentoEEAADTO.getPostiSostegnoTerzoAnno()));

					tbs1040FabpostopotenziamentoRepository.save(tbs1040Fabpostopotenziamento);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_POTENZIAMENTO_MM: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiPotenziamentoMMDTO componenteFabbisognoPostiPotenziamentoMMDTO = (ComponenteFabbisognoPostiPotenziamentoMMDTO)componete;
			for (FabbisognoPostiPotenziamentoMMDTO fabbisognoPostiPotenziamentoMMDTO: componenteFabbisognoPostiPotenziamentoMMDTO.getItems()){
				Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento = tbs1040FabpostopotenziamentoRepository.findOne(Long.valueOf(fabbisognoPostiPotenziamentoMMDTO.getKey()));
				if (tbs1040Fabpostopotenziamento != null){
					tbs1040Fabpostopotenziamento.setDesMot(fabbisognoPostiPotenziamentoMMDTO.getMotivazione());
					
					tbs1040Fabpostopotenziamento.setNumPosComPri(toBigDecimal(fabbisognoPostiPotenziamentoMMDTO.getPostiPotenziamentoPrimoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosComSec(toBigDecimal(fabbisognoPostiPotenziamentoMMDTO.getPostiPotenziamentoSecondoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosComTer(toBigDecimal(fabbisognoPostiPotenziamentoMMDTO.getPostiPotenziamentoTerzoAnno()));

					tbs1040FabpostopotenziamentoRepository.save(tbs1040Fabpostopotenziamento);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SOSTEGNO: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO componenteFabbisognoPostiPotenziamentoSostegnoMMDTO = (ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO)componete;
			for (FabbisognoPostiPotenziamentoSostegnoMMDTO fabbisognoPostiPotenziamentoSostegnoMMDTO: componenteFabbisognoPostiPotenziamentoSostegnoMMDTO.getItems()){
				Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento = tbs1040FabpostopotenziamentoRepository.findOne(Long.valueOf(fabbisognoPostiPotenziamentoSostegnoMMDTO.getKey()));
				if (tbs1040Fabpostopotenziamento != null){
					tbs1040Fabpostopotenziamento.setDesMot(fabbisognoPostiPotenziamentoSostegnoMMDTO.getMotivazione());
					
					tbs1040Fabpostopotenziamento.setNumPosSosPri(toBigDecimal(fabbisognoPostiPotenziamentoSostegnoMMDTO.getPostiSostegnoPrimoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosSosSec(toBigDecimal(fabbisognoPostiPotenziamentoSostegnoMMDTO.getPostiSostegnoSecondoAnno()));
					tbs1040Fabpostopotenziamento.setNumPosSosTer(toBigDecimal(fabbisognoPostiPotenziamentoSostegnoMMDTO.getPostiSostegnoTerzoAnno()));

					tbs1040FabpostopotenziamentoRepository.save(tbs1040Fabpostopotenziamento);
				}
			}
			break;
		
		case  S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI: /** solo update per questo tipo di componente **/
			ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO componenteFabbisognoPostiPersonaleAmmTecAusiDTO = (ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO)componete;
			for (FabbisognoPostiPersonaleAmmTecAusiDTO fabbisognoPostiPersonaleAmmTecAusiDTO: componenteFabbisognoPostiPersonaleAmmTecAusiDTO.getItems()){
				Tbs1041Fabfiguraprofessionale tbs1041Fabfiguraprofessionale = tbs1041FabfiguraprofessionaleRepository.findOne(Long.valueOf(fabbisognoPostiPersonaleAmmTecAusiDTO.getKey()));
				if (tbs1041Fabfiguraprofessionale != null){
					tbs1041Fabfiguraprofessionale.setDesMot(fabbisognoPostiPersonaleAmmTecAusiDTO.getMotivazione());
					
					tbs1041Fabfiguraprofessionale.setNumPosPri(toBigDecimal(fabbisognoPostiPersonaleAmmTecAusiDTO.getNumeroPostiPrimoAnnoTriennio()));
					tbs1041Fabfiguraprofessionale.setNumPosSec(toBigDecimal(fabbisognoPostiPersonaleAmmTecAusiDTO.getNumeroPostiSecondoAnnoTriennio()));
					tbs1041Fabfiguraprofessionale.setNumPosTer(toBigDecimal(fabbisognoPostiPersonaleAmmTecAusiDTO.getNumeroPostiTerzoAnnoTriennio()));

					tbs1041FabfiguraprofessionaleRepository.save(tbs1041Fabfiguraprofessionale);
				}
			}
			break;
		
		default:
			throw new RuntimeException("Tipo componente non gestito");
		}

		utilPtofServiceImpl.updateStatoSezione(prgDatPtf, istitutoScolasticoDTO, TIPO_STATO_SEZIONE.BOZZA);
		utilPtofServiceImpl.gestisciStatoSezionePadre(prgDatPtf);
		
		
		return componete;
	}

	/**
	 * @param keyDocumento
	 * @param istitutoScolasticoDTO
	 * Controlla 
	 *	1.	Che  tutte le sezioni/sottosezioni obbligatorie siano state compilate.
	 *	2.	Che le date: “Data ratifica atto di indirizzo Dirigente Scolastico”, “Data predisposizione PTOF Collegio dei Docenti”, “Data approvazione Consiglio d’Istituto” siano state inserite“ (Vedi RF051– Dati Finali Scuola)
	 *  
	 *  nel caso in cui i controlli non siano superati:
	 *   - ritorna un oggetto ResponseDTO con validationErrorDTO !=null con il result == false
	 *    
	 *  nel caso in cui i controlli siano superati:
	 *   - ritorna un oggetto ResponseDTO con validationErrorDTO == null e il result == true
	 *   - cambia lo stato del ptof in CONVALIDATO
	 *   - imposta la data di invio a USR nella sezione SEZIONE_32_DATI_FINALI_SCUOLA
	 *   - imposta lo stato della sezione 14 PIANO NAZIONALE SCUOLA DIGITALE a SEZIONE COMPILATA
	 *   - imposta lo stato della sezione a compilato se essa contiene almeno una sottosezione compilata
	 */
	@Override
	public ResponseDTO<Boolean> validaECambiaStatoPtof(String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try{
			out = convalidaValidatorChain.validate(keyDocumento,istitutoScolasticoDTO);
			logger.debug("esito validazione : " + out.getResult().booleanValue());

			if (out.getResult().booleanValue()){
				
				logger.debug("procedo con la convalida");

				String codScuUte = keyDocumento.substring(0,10);
				Long perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
				Long prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));

				//valorizzazione dello stato a CONVALIDATO
				Tbs1002GestioneptofPK tbs1002GestioneptofPK = new Tbs1002GestioneptofPK();
				tbs1002GestioneptofPK.setCodScuUte(codScuUte);
				tbs1002GestioneptofPK.setPerAnnSco(perAnnSco);
				tbs1002GestioneptofPK.setPrgGesCatDoc(prgGesCatDoc);
				Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository.findOne(tbs1002GestioneptofPK);
				Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
				tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.CONVALIDATO.code());
				tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
				tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
				logger.debug("save su tbs1002Gestioneptof");
				
				//valorizzazione della data invio USR
				Tbs1004Datisezionesottosez sezioneSEZIONE_32_DATI_FINALI_SCUOLA = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.code()), codScuUte, prgGesCatDoc, perAnnSco);
				sezioneSEZIONE_32_DATI_FINALI_SCUOLA.setDatInvUsr(CommonsUtility.getTodayDate());
				tbs1004DatisezionesottosezRepository.save(sezioneSEZIONE_32_DATI_FINALI_SCUOLA);
				logger.debug("save su sezioneSEZIONE_32_DATI_FINALI_SCUOLA");
				
				//valorizzazione dello stato della sezione 14 PIANO NAZIONALE SCUOLA DIGITALE a SEZIONE COMPILATA
				Tbs1004Datisezionesottosez sezione33_PIANO_NAZIONALE_SCUOLA_DIGITALE = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE.code()), codScuUte, prgGesCatDoc, perAnnSco);
				sezione33_PIANO_NAZIONALE_SCUOLA_DIGITALE.setCodSta(TIPO_STATO_SEZIONE.COMPILATA.code());
				tbs1004DatisezionesottosezRepository.save(sezione33_PIANO_NAZIONALE_SCUOLA_DIGITALE);
				logger.debug("save su sezione33_PIANO_NAZIONALE_SCUOLA_DIGITALE");
				
				//valorizzazione dello stato della sezione a compilato se essa contiene almeno una sottosezione compilata
				LinkedList<Sezione> sezioni = sezioneRepository.findSezioniScuolaInStatoBozzaAventiSottosezioniCompilate(codScuUte, perAnnSco, prgGesCatDoc);
				if (sezioni != null && !sezioni.isEmpty()){
					for (Sezione sezione : sezioni){
						Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findOne(Long.valueOf(sezione.getChiave()));
						if (tbs1004Datisezionesottosez != null){
							tbs1004Datisezionesottosez.setCodSta(TIPO_STATO_SEZIONE.COMPILATA.code());
							tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
							logger.debug("save su sezione : " + sezione.getCodSezSotSez() + " - " + sezione.getDesSez());
						}
					}
				}
				
				
			}
		}catch(Exception ex){
			logger.error("ERRORE validaECambiaStatoPtof : " + ex.getMessage(), ex);
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
	public ResponseDTO<Boolean> setAnnullaConvalidaPtof(String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
		logger.debug("in setAnnullaConvalidaPtof ... " + keyDocumento);
		logger.debug(ReflectionToStringBuilder.toString(istitutoScolasticoDTO,ToStringStyle.MULTI_LINE_STYLE));

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		
		String codScuUte = null;
		Long perAnnSco = null;
		Long prgGesCatDoc = null;
		
		if (keyDocumento != null && ! keyDocumento.isEmpty()){
			codScuUte = keyDocumento.substring(0,10);
			perAnnSco = Long.valueOf(keyDocumento.substring(10,16));
			prgGesCatDoc = Long.valueOf(keyDocumento.substring(16));
		}else{
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("01");
			validationErrorDTO.setMessage("Chiave documento non valida");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
			return out;
		}
		
		if (codScuUte.equals(istitutoScolasticoDTO.getCodiceMecIstPrin())){ //controllo di sicurezza

			Tbs1002GestioneptofPK tbs1002GestioneptofPK = new Tbs1002GestioneptofPK();
			tbs1002GestioneptofPK.setCodScuUte(codScuUte);
			tbs1002GestioneptofPK.setPerAnnSco(perAnnSco);
			tbs1002GestioneptofPK.setPrgGesCatDoc(prgGesCatDoc);
			Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository.findOne(tbs1002GestioneptofPK);

			logger.debug(TIPO_STATO_DOC.getInstanceFromCode(tbs1002Gestioneptof.getTbs1006Tipostato().getCodSta()));
			if (TIPO_STATO_DOC.getInstanceFromCode(tbs1002Gestioneptof.getTbs1006Tipostato().getCodSta()) == TIPO_STATO_DOC.FABBISOGNO_NON_VALIDATO ){
				logger.debug("ritorno allo stato PUBBLICATO PARZIALE");
				Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
				tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code());
				tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
				tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);
				
				Tbs1004Datisezionesottosez sezioneSEZIONE_32_DATI_FINALI_SCUOLA = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.code()), codScuUte, prgGesCatDoc, perAnnSco);
				//valorizzazione della data invio USR
				sezioneSEZIONE_32_DATI_FINALI_SCUOLA.setDatInvUsr(CommonsUtility.getTodayDate());
				tbs1004DatisezionesottosezRepository.save(sezioneSEZIONE_32_DATI_FINALI_SCUOLA);

			}else{
				logger.debug("ritorno allo stato IN COMPILAZIONE");
				//valorizzazione dello stato a IN COMPILAZIONE			
				Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
				tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.IN_COMPILAZIONE.code());
				tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
				tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);

				Tbs1004Datisezionesottosez sezioneSEZIONE_32_DATI_FINALI_SCUOLA = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.code()), codScuUte, prgGesCatDoc, perAnnSco);
				//valorizzazione della data invio USR = null
				sezioneSEZIONE_32_DATI_FINALI_SCUOLA.setDatInvUsr(null);
				tbs1004DatisezionesottosezRepository.save(sezioneSEZIONE_32_DATI_FINALI_SCUOLA);
			}

			out.setResult(Boolean.TRUE);
			return out;

		}else{
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("01");
			validationErrorDTO.setMessage("Codice meccanografico non valido");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
			return out;
		}
		
	}
	
	private BigDecimal toBigDecimal(Integer in){
		if (in != null){
			return new BigDecimal(in.intValue());
		}else{
			return BigDecimal.valueOf(0l);
		}
	}
	
}
