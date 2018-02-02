package it.istruzione.ptof.service.impl;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.AlternanzaScuolaLavoroDTO;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariMateriaDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.ConvPotenzFormativoDTO;
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
import it.istruzione.ptof.beans.documenti.PianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.beans.documenti.PrioritaTraguardiDTO;
import it.istruzione.ptof.beans.documenti.ProgrammazioneFormDTO;
import it.istruzione.ptof.beans.documenti.PromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.SintesiProcessoDTO;
import it.istruzione.ptof.beans.documenti.StrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAlternzaScuolaLavoroDTO;
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
import it.istruzione.ptof.beans.documenti.componenti.ComponenteIstitutoPrincipaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMonitoraggioPianificazioneDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMultiBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviMiglioramentoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariAltriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaAltriRuoliDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePlessoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAmmDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAusDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormDocDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormTecDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteStrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.model.entity.Tbs1001Gestionecatalogodoc;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.model.entity.Tbs1005Allegato;
import it.istruzione.ptof.model.entity.Tbs1007Testo;
import it.istruzione.ptof.model.entity.Tbs1009Obiettivo;
import it.istruzione.ptof.model.entity.Tbs1010Tipoaggregacontesto;
import it.istruzione.ptof.model.entity.Tbs1013Altrobiettivo;
import it.istruzione.ptof.model.entity.Tbs1014Componentesezione;
import it.istruzione.ptof.model.entity.Tbs1015Tipomulticulturalita;
import it.istruzione.ptof.model.entity.Tbs1017Ravobiettivo;
import it.istruzione.ptof.model.entity.Tbs1018Ravprioritatraguardi;
import it.istruzione.ptof.model.entity.Tbs1021Ambitoprogettoptof;
import it.istruzione.ptof.model.entity.Tbs1022Ambitoprogettoaltro;
import it.istruzione.ptof.model.entity.Tbs1023Altreinizididattiche;
import it.istruzione.ptof.model.entity.Tbs1024Attivitasostegnoptof;
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
import it.istruzione.ptof.model.entity.business.AlternanzaScuolaLavoro;
import it.istruzione.ptof.model.entity.business.ArticolazioneClassiAA;
import it.istruzione.ptof.model.entity.business.ArticolazioneClassiEE;
import it.istruzione.ptof.model.entity.business.ArticolazioneClassiMMI;
import it.istruzione.ptof.model.entity.business.ArticolazioneClassiMMII;
import it.istruzione.ptof.model.entity.business.DotazioniMultimediali;
import it.istruzione.ptof.model.entity.business.FabbisognoPostiAta;
import it.istruzione.ptof.model.entity.business.FabbisognoPostiCattedre;
import it.istruzione.ptof.model.entity.business.FabbisognoPostiPotenziamento;
import it.istruzione.ptof.model.entity.business.FabbisognoPostiSostegno;
import it.istruzione.ptof.model.entity.business.IndirizzoStudio;
import it.istruzione.ptof.model.entity.business.Pnsd;
import it.istruzione.ptof.model.entity.business.QuadroOrario;
import it.istruzione.ptof.model.entity.business.QuadroOrarioDettaglio;
import it.istruzione.ptof.model.entity.business.TempoScuola;
import it.istruzione.ptof.model.entity.business.TipologiaScuola;
import it.istruzione.ptof.model.repository.Tbs1001GestionecatalogodocRepository;
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
import it.istruzione.ptof.model.repository.business.AlternanzaScuolaLavoroRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiAARepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiEERepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIIRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIRepository;
import it.istruzione.ptof.model.repository.business.DocumentoRepository;
import it.istruzione.ptof.model.repository.business.DotazioniMultimedialiRepository;
import it.istruzione.ptof.model.repository.business.FabbisognoPostiAtaRepository;
import it.istruzione.ptof.model.repository.business.FabbisognoPostiCattedreRepository;
import it.istruzione.ptof.model.repository.business.FabbisognoPostiPotenziamentoRepository;
import it.istruzione.ptof.model.repository.business.FabbisognoPostiSostegnoRepository;
import it.istruzione.ptof.model.repository.business.ImportoTotaleRepository;
import it.istruzione.ptof.model.repository.business.IndirizzoStudioRepository;
import it.istruzione.ptof.model.repository.business.PnsdRepository;
import it.istruzione.ptof.model.repository.business.QuadroOrarioDettaglioRepository;
import it.istruzione.ptof.model.repository.business.QuadroOrarioRepository;
import it.istruzione.ptof.model.repository.business.SezioneRepository;
import it.istruzione.ptof.model.repository.business.TempoScuolaRepository;
import it.istruzione.ptof.model.repository.business.TipologiaScuolaRepository;
import it.istruzione.ptof.service.HomeService;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 * @author sronca
 * servizi per Gestione PTOF
 */

@Service
public class UtilPtofServiceImpl extends BaseServiceImpl{
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private SezioneRepository sezioneRepository;
	
	@Autowired
	private Tbs1004DatisezionesottosezRepository tbs1004DatisezionesottosezRepository;
	
	@Autowired
	private Tbs1001GestionecatalogodocRepository tbs1001GestionecatalogodocRepository;
	
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
	private Tbs1037FabpostocomuneRepository tbs1037FabpostocomuneRepository;
	
	@Autowired
	private Tbs1038ArticolaclasseiigradoRepository tbs1038ArticolaclasseiigradoRepository;
	
	@Autowired
	private Tbs1039FabpostosostegnoRepository tbs1039FabpostosostegnoRepository;
	
	@Autowired
	private Tbs1040FabpostopotenziamentoRepository tbs1040FabpostopotenziamentoRepository;
	
	@Autowired
	private Tbs1041FabfiguraprofessionaleRepository tbs1041FabfiguraprofessionaleRepository;
	
	@Autowired
	private Tbs1042FabattrezmaterialeRepository tbs1042FabattrezmaterialeRepository;
	
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
	private IndirizzoStudioRepository indirizzoStudioRepository;
	
	@Autowired
	private QuadroOrarioRepository quadroOrarioRepository;
	
	@Autowired
	private QuadroOrarioDettaglioRepository quadroOrarioDettaglioRepository;
	
	@Autowired
	private TempoScuolaRepository tempoScuolaRepository;
	
	@Autowired
	private FabbisognoPostiCattedreRepository fabbisognoPostiCattedreRepository;
	
	@Autowired
	private FabbisognoPostiSostegnoRepository fabbisognoPostiSostegnoRepository;
	
	@Autowired
	private FabbisognoPostiPotenziamentoRepository fabbisognoPostiPotenziamentoRepository;
	
	@Autowired
	private FabbisognoPostiAtaRepository fabbisognoPostiAtaRepository;
	
	@Autowired
	private ImportoTotaleRepository importoTotaleRepository;
	
	@Autowired
	private PnsdRepository pnsdRepository;
	
	@Autowired
	private AlternanzaScuolaLavoroRepository alternanzaScuolaLavoroRepository;
	
	private static Logger logger = Logger.getLogger(UtilPtofServiceImpl.class);
	
	private final static String TIPO_CMP_TIPOLOGIA_DI_AGGREGAZIONE_CONTESTO = "CB00";
	private final static String TIPO_CMP_LIVELLO_DI_MULTICULTURALITA = "CB01";
   
    public void setCommonFieldToComponenteDTO(ComponenteDTO componenteDTO, Tbs1014Componentesezione tbs1014Componentesezione, Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, TIPO_STATO_DOC statoDocumento){
		componenteDTO.setNome(tbs1014Componentesezione.getTbs1016Tipocomponente().getDesTipCmp());
		componenteDTO.setObbligatorio(tbs1014Componentesezione.getFlgObl().equals("Y"));
		//NOT IMPLEMENTED componenteDTO.setDescrizione(descrizione);
		componenteDTO.setStatoSezione(TIPO_STATO_SEZIONE.getInstanceFromCode(tbs1004Datisezionesottosez.getCodSta()));
		componenteDTO.setTipoSezione(TIPO_SEZIONE.getInstanceFromCode(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez().toString()));
		componenteDTO.setStatoDocumento(statoDocumento);
    }
    
    public void gestisciLoadComponenteTEXTEDITOR(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** possono essere presenti n o 0 componenti di questo tipo, nel caso non sia presente inserisco il record senza blob **/
    	ComponenteDTO componenteDTO = null;
    	List<Tbs1007Testo> tbs1007Testos = tbs1004Datisezionesottosez.getTbs1007Testos();
    	if (tbs1007Testos != null && !tbs1007Testos.isEmpty()){
    		logger.debug("tbs1007Testos presente");
    		for (Tbs1007Testo elem : tbs1007Testos){
    			componenteDTO = new ComponenteTextAreaDTO();
    			componenteDTO.setKey(elem.getPrgTes().toString());
    			if (elem.getOggTes() != null){
    				((ComponenteTextAreaDTO)componenteDTO).setValore(new String(elem.getOggTes()));
    			}
	        	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
	        	componenti.add(componenteDTO);
    		}
    	}else{
    		for (int i=0; i<tbs1014Componentesezione.getQtyMca(); i++){
    			logger.debug("inserisco tbs1007Testo");
    			Tbs1007Testo tbs1007Testo = new Tbs1007Testo();
    			tbs1007Testo.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1007Testo = tbs1007TestoRepository.save(tbs1007Testo);

    			componenteDTO = new ComponenteTextAreaDTO();
    			componenteDTO.setKey(tbs1007Testo.getPrgTes().toString());
    			setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    			componenti.add(componenteDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteALLEGATO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** possono essere presenti n o 0 componenti di questo tipo, nel caso non sia presente inserisco il record senza blob **/
    	ComponenteDTO componenteDTO = null;
    	List<Tbs1005Allegato> tbs1005Allegatos = tbs1004Datisezionesottosez.getTbs1005Allegatos();
    	if (tbs1005Allegatos != null && !tbs1005Allegatos.isEmpty()){
    		logger.debug("tbs1005Allegatos presente");
    		for (Tbs1005Allegato elem : tbs1005Allegatos){
    			componenteDTO = new ComponenteAllegatoDTO();
    			componenteDTO.setKey(elem.getPrgAll().toString());
    			if (elem.getOggAll() != null){
    				FileDTO file = new FileDTO();
    				file.setFile(new ByteArrayInputStream(elem.getOggAll()));
    				file.setFileName(elem.getDesNomAll());
    				((ComponenteAllegatoDTO)componenteDTO).setFile(file);
    			}
    			TIPO_FILE_ACCETTATO tipoFileAccettato = TIPO_FILE_ACCETTATO.PDF;
    			if (tbs1014Componentesezione.getTbs1016Tipocomponente().getCodTipCmp().equals("UP00")){
    				tipoFileAccettato = TIPO_FILE_ACCETTATO.JPEG;
    			}else if (tbs1014Componentesezione.getTbs1016Tipocomponente().getCodTipCmp().equals("UP02")){
    				tipoFileAccettato = TIPO_FILE_ACCETTATO.ZIP;
    			}
    			((ComponenteAllegatoDTO)componenteDTO).setTipoFileAccettato(tipoFileAccettato);
	        	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
	        	componenti.add(componenteDTO);
    		}
    	}else{
    		for (int i=0; i<tbs1014Componentesezione.getQtyMca(); i++){
    			logger.debug("inserisco tbs1005Allegato");
    			Tbs1005Allegato tbs1005Allegato = new Tbs1005Allegato();
    			tbs1005Allegato.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1005Allegato = tbs1005AllegatoRepository.save(tbs1005Allegato);

    			componenteDTO = new ComponenteAllegatoDTO();
    			componenteDTO.setKey(tbs1005Allegato.getPrgAll().toString());
    			TIPO_FILE_ACCETTATO tipoFileAccettato = TIPO_FILE_ACCETTATO.PDF;
    			if (tbs1014Componentesezione.getTbs1016Tipocomponente().getCodTipCmp().equals("UP00")){
    				tipoFileAccettato = TIPO_FILE_ACCETTATO.JPEG;
    			}
    			((ComponenteAllegatoDTO)componenteDTO).setTipoFileAccettato(tipoFileAccettato);
    			setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    			componenti.add(componenteDTO);
    		}
    	}
    }
    
    public void gestisciLoadComponenteDATI_ISTITUTO_PRINCIPALE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** E' un componente in sola visualizzazione **/
    	ComponenteDTO componenteDTO = null;
    	IstitutoScolasticoDTO istitutoScolasticoDTO = homeService.loadIstitutoScolasticoDTO(tbs1004Datisezionesottosez.getCodScuUte());
    	
    	if (istitutoScolasticoDTO != null){
    		logger.debug("istitutoScolasticoDTO presente");
    		componenteDTO = new ComponenteIstitutoPrincipaleDTO();
    		componenteDTO.setKey(istitutoScolasticoDTO.getKey());
    		((ComponenteIstitutoPrincipaleDTO)componenteDTO).setIstitutoScolasticoDTO(istitutoScolasticoDTO);
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    	}
    }
    
    public void gestisciLoadComponenteDATI_PLESSO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** E' un componente in sola visualizzazione **/
    	ComponenteDTO componenteDTO = null;
    	PlessiDTO plessiDTO = homeService.findPlessoByCodiceMeccanografico(tbs1004Datisezionesottosez.getCodScuUtePle());
    	
    	if (plessiDTO != null){
    		logger.debug("plessiDTO presente");
    		componenteDTO = new ComponentePlessoDTO();
    		componenteDTO.setKey(plessiDTO.getKey());
    		((ComponentePlessoDTO)componenteDTO).setPlesso(plessiDTO);
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    	}
    }

    public void gestisciLoadComponenteS_OBBIETTIVI_MIGLIORAMENTO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** E' un componente in sola visualizzazione **/
    	ComponenteObbiettiviMiglioramentoDTO componenteDTO = new ComponenteObbiettiviMiglioramentoDTO();
    	LinkedList<PrioritaTraguardiDTO> prioritaTraguardi = new LinkedList<>();
    	LinkedList<SintesiProcessoDTO> sintesiProcesso = new LinkedList<>();
    	componenteDTO.setPrioritaTraguardi(prioritaTraguardi);
    	componenteDTO.setSintesiProcesso(sintesiProcesso);
    	LinkedList<Tbs1018Ravprioritatraguardi> tbs1018Ravprioritatraguardis = tbs1018RavprioritatraguardiRepository.findByCodScuUte(tbs1004Datisezionesottosez.getCodScuUte());
    	LinkedList<Tbs1017Ravobiettivo> tbs1017Ravobiettivos = tbs1017RavobiettivoRepository.findByCodScuUte(tbs1004Datisezionesottosez.getCodScuUte());
    	
    	if (tbs1018Ravprioritatraguardis != null && !tbs1018Ravprioritatraguardis.isEmpty()){
    		logger.debug("tbs1018Ravprioritatraguardis presente");
    		componenteDTO.setMotivazioneSceltaPriorita(tbs1018Ravprioritatraguardis.getFirst().getDesMotScePri());
    		for (Tbs1018Ravprioritatraguardi tbs1018Ravprioritatraguardi : tbs1018Ravprioritatraguardis){
    			PrioritaTraguardiDTO prioritaTraguardiDTO = new PrioritaTraguardiDTO();
    			prioritaTraguardiDTO.setArea(tbs1018Ravprioritatraguardi.getDesEsiStu());
    			prioritaTraguardiDTO.setDescPriorita(tbs1018Ravprioritatraguardi.getDesPri());
    			prioritaTraguardiDTO.setDescTraguardo(tbs1018Ravprioritatraguardi.getDesTra());
    			prioritaTraguardi.add(prioritaTraguardiDTO);
    		}
    	}
    	
    	if (tbs1017Ravobiettivos != null && !tbs1017Ravobiettivos.isEmpty()){
    		logger.debug("tbs1017Ravobiettivos presente");
    		for (Tbs1017Ravobiettivo tbs1017Ravobiettivo : tbs1017Ravobiettivos){
    			SintesiProcessoDTO sintesiProcessoDTO = new SintesiProcessoDTO();
    			sintesiProcessoDTO.setAreaProcesso(tbs1017Ravobiettivo.getDesArePrc());
    			sintesiProcessoDTO.setDescObiettivoProcesso(tbs1017Ravobiettivo.getDesObiPrc());
    			sintesiProcesso.add(sintesiProcessoDTO);
    		}
    	}
    	
		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
		componenti.add(componenteDTO);
		
    }

    public void gestisciLoadComponenteMULTI_BOX(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/** Il componente MULTI_BOX è una lista di componenti COMBO_BOX a selezione singola.
    	 *  Il dato selezionato, per ciascuna COMBO_BOX, viene inserito nella tbs1004Datisezionesottosez con la transazione SALVA SEZIONE.
    	 *  L'elenco degli item per ogni COMBO_BOX viene individuato mediante l'attributo codTipCmp del tipo componente
    	 **/
    	ComponenteMultiBoxDTO componenteDTO = null;
    	logger.debug("in gestisciComponenteMULTI_BOX");
    	componenteDTO = new ComponenteMultiBoxDTO();
    	LinkedList<ComponenteComboBoxDTO> comboBox = new LinkedList<>();
    	componenteDTO.setComboBox(comboBox);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1014Componentesezione tbs1014ComponentesezioneFiglio : tbs1014Componentesezione.getTbs1014ComponentesezioneFigli()){
    		ComponenteComboBoxDTO componenteComboBoxDTO = new ComponenteComboBoxDTO();
    		componenteComboBoxDTO.setKey(tbs1014ComponentesezioneFiglio.getCodTipCmp());
    		LinkedList<BeanValueDTO> itemSelezionabili = getItems(tbs1014ComponentesezioneFiglio.getCodTipCmp());
    		BeanValueDTO selected = getSelectedItem(tbs1004Datisezionesottosez, tbs1014ComponentesezioneFiglio.getCodTipCmp(), itemSelezionabili);
    		componenteComboBoxDTO.setItemSelezionabili(itemSelezionabili);
    		componenteComboBoxDTO.setSelected(selected);
    		setCommonFieldToComponenteDTO(componenteComboBoxDTO, tbs1014ComponentesezioneFiglio, tbs1004Datisezionesottosez, statoDocumento);
    		comboBox.add(componenteComboBoxDTO);
    	}
    }
    
    public LinkedList<BeanValueDTO> getItems(String codTipCmp){
    	
    	LinkedList<BeanValueDTO> items = new LinkedList<>();
    	if (codTipCmp.equals(TIPO_CMP_TIPOLOGIA_DI_AGGREGAZIONE_CONTESTO)){
    		logger.debug("find items per CB00 - Tipologia di aggregazione Contesto");
    		List<Tbs1010Tipoaggregacontesto> tbs1010Tipoaggregacontestos = tbs1010TipoaggregacontestoRepository.findAll(new Sort(new Order(Sort.Direction.ASC, "codTipCts")));
    		for (Tbs1010Tipoaggregacontesto elem: tbs1010Tipoaggregacontestos){
    			BeanValueDTO dto = new BeanValueDTO(elem.getCodTipCts(), elem.getDesTipCts());
    			items.add(dto);
    		}
    	}else if (codTipCmp.equals(TIPO_CMP_LIVELLO_DI_MULTICULTURALITA)){
    		logger.debug("find items per CB01 - Livello di Multiculturalita");
    		List<Tbs1015Tipomulticulturalita> tbs1015Tipomulticulturalitas = tbs1015TipomulticulturalitaRepository.findAll(new Sort(new Order(Sort.Direction.ASC, "codTipMlt")));
    		for (Tbs1015Tipomulticulturalita elem: tbs1015Tipomulticulturalitas){
    			BeanValueDTO dto = new BeanValueDTO(elem.getCodTipMlt(), elem.getDesTipMlt());
    			items.add(dto);
    		}
    	}
    	
    	return items;
    }
    
    public BeanValueDTO getSelectedItem(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, String codTipCmp, LinkedList<BeanValueDTO> items){
    	
    	BeanValueDTO selected = new BeanValueDTO();
    	String dbValue = null;
    	if (codTipCmp.equals(TIPO_CMP_TIPOLOGIA_DI_AGGREGAZIONE_CONTESTO)){
    		dbValue = tbs1004Datisezionesottosez.getCodTipCts();
    	}else if (codTipCmp.equals(TIPO_CMP_LIVELLO_DI_MULTICULTURALITA)){
    		dbValue = tbs1004Datisezionesottosez.getCodTipMlt();
    	}
    	
    	if (dbValue!= null && !dbValue.isEmpty()){
    		for (BeanValueDTO dto : items){
    			if (dto.getValue().equals(dbValue)){
    				selected.setLabel(dto.getLabel());
    				selected.setValue(dto.getValue());
    			}
    		}
    	}
    	
    	return selected;
    	
    }

    public void gestisciLoadComponenteS_OBBIETTIVI_FORMATIVI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteObbiettiviPrioritariDTO componenteDTO = new ComponenteObbiettiviPrioritariDTO();
    	logger.debug("in gestisciComponenteS_OBBIETTIVI_FORMATIVI");
    	LinkedList<ObbiettiviFormativiDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1009Obiettivo tbs1009Obiettivo : tbs1004Datisezionesottosez.getTbs1009Obiettivos()){
    		
    		items.add(DtoFactory.getObbiettiviFormativiDTO(tbs1009Obiettivo));
    	}
    }

    public void gestisciLoadComponenteS_OBBIETTIVI_FORMATIVI_ALTRI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteObbiettiviPrioritariAltriDTO componenteDTO = new ComponenteObbiettiviPrioritariAltriDTO();
    	logger.debug("in gestisciComponenteS_OBBIETTIVI_FORMATIVI_ALTRI");
    	LinkedList<ObbiettiviFormativiDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1013Altrobiettivo tbs1013Altrobiettivo : tbs1004Datisezionesottosez.getTbs1013Altrobiettivos()){
    		
    		items.add(DtoFactory.getObbiettiviFormativiDTO(tbs1013Altrobiettivo));
    	}
    }

    public void gestisciLoadComponenteS_PROGETTI_CURRICULARI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteProgettiCurriculariDTO componenteDTO = new ComponenteProgettiCurriculariDTO();
    	logger.debug("in gestisciLoadComponenteS_PROGETTI_CURRICULARI");
    	LinkedList<AmbitiProgettiDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof : tbs1004Datisezionesottosez.getTbs1021Ambitoprogettoptofs()){

    		items.add(DtoFactory.getAmbitiProgettiDTO(tbs1021Ambitoprogettoptof));
    	}
    }

    public void gestisciLoadComponenteS_ALTRI_PROGETTI_CURRICULARI_EXSTRA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteAltriProgettiCurriculariDTO componenteDTO = new ComponenteAltriProgettiCurriculariDTO();
    	logger.debug("in gestisciLoadComponenteS_ALTRI_PROGETTI_CURRICULARI_EXSTRA");
    	LinkedList<AmbitiProgettiDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro : tbs1004Datisezionesottosez.getTbs1022Ambitoprogettoaltros()){

    		items.add(DtoFactory.getAmbitiProgettiDTO(tbs1022Ambitoprogettoaltro));
    	}
    }

    public void gestisciLoadComponenteS_ALTRE_INIZIATIVE_DIDATTICO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteAltreIniziativeDidaDTO componenteDTO = new ComponenteAltreIniziativeDidaDTO();
    	logger.debug("in gestisciLoadComponenteS_ALTRE_INIZIATIVE_DIDATTICO");
    	LinkedList<IniziativeDidatticheEducativeDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1023Altreinizididattiche tbs1023Altreinizididattiche : tbs1004Datisezionesottosez.getTbs1023Altreinizididattiches()){

    		items.add(DtoFactory.getIniziativeDidatticheEducativeDTO(tbs1023Altreinizididattiche));
    	}
    }

    public void gestisciLoadComponenteS_ATTIVITA_SOSTEGNO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteAttivitaSostegnoDTO componenteDTO = new ComponenteAttivitaSostegnoDTO();
    	logger.debug("in gestisciLoadComponenteS_ATTIVITA_SOSTEGNO");
    	LinkedList<AttivitaSostegnoDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1024Attivitasostegnoptof tbs1024Attivitasostegnoptof : tbs1004Datisezionesottosez.getTbs1024Attivitasostegnoptofs()){

    		items.add(DtoFactory.getAttivitaSostegnoDTO(tbs1024Attivitasostegnoptof));
    	}
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteOrganigrammaDTO componenteDTO = new ComponenteOrganigrammaDTO();
    	logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE");
    	LinkedList<OrganigrammaDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1026Organrisorseptof tbs1026Organrisorseptof : tbs1004Datisezionesottosez.getTbs1026Organrisorseptofs()){

    		if (tbs1026Organrisorseptof.getTbs1025Tiporuolo() != null){
    			OrganigrammaDTO organigrammaDTO = new OrganigrammaDTO();
    			organigrammaDTO.setKey(tbs1026Organrisorseptof.getPrgOrgRis().toString());
    			organigrammaDTO.setNome(tbs1026Organrisorseptof.getDesResRuo());
    			organigrammaDTO.setNoteResponsabilita(tbs1026Organrisorseptof.getDesNotRsp());
    			organigrammaDTO.setResponsabilita(tbs1026Organrisorseptof.getDesRsp());
    			BeanValueDTO ruolo = new BeanValueDTO();
    			ruolo.setLabel(tbs1026Organrisorseptof.getTbs1025Tiporuolo().getDesTipRuo());
    			ruolo.setValue(tbs1026Organrisorseptof.getTbs1025Tiporuolo().getCodTipRuo());
    			organigrammaDTO.setRuolo(ruolo);
    			items.add(organigrammaDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteOrganigrammaAltriRuoliDTO componenteDTO = new ComponenteOrganigrammaAltriRuoliDTO();
    	logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI");
    	LinkedList<OrganigrammaDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1026Organrisorseptof tbs1026Organrisorseptof : tbs1004Datisezionesottosez.getTbs1026Organrisorseptofs()){

    		if (tbs1026Organrisorseptof.getTbs1025Tiporuolo() == null){
    			OrganigrammaDTO organigrammaDTO = new OrganigrammaDTO();
    			organigrammaDTO.setKey(tbs1026Organrisorseptof.getPrgOrgRis().toString());
    			organigrammaDTO.setNome(tbs1026Organrisorseptof.getDesResRuo());
    			organigrammaDTO.setNoteResponsabilita(tbs1026Organrisorseptof.getDesNotRsp());
    			organigrammaDTO.setResponsabilita(tbs1026Organrisorseptof.getDesRsp());
    			BeanValueDTO ruolo = new BeanValueDTO();
    			ruolo.setLabel(tbs1026Organrisorseptof.getDesRuoAlt());
    			ruolo.setValue(tbs1026Organrisorseptof.getDesRuoAlt());
    			organigrammaDTO.setRuolo(ruolo);
    			items.add(organigrammaDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_PROGRAMMAZIONE_FORMAZIONE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_COMPONENTE tipo_componente, TIPO_STATO_DOC statoDocumento){

    	logger.debug("in gestisciLoadComponenteS_PROGRAMMAZIONE_FORMAZIONE_DOC");
    	LinkedList<ProgrammazioneFormDTO> items = new LinkedList<>();
    	ComponenteDTO componenteDTO = null;
    	switch (tipo_componente) {
		case S_PROGRAMMAZIONE_FORMAZIONE_DOC:
			componenteDTO = new ComponenteProgrammazioneFormDocDTO();
			((ComponenteProgrammazioneFormDocDTO)componenteDTO).setItems(items);
			break;
		case S_PROGRAMMAZIONE_FORMAZIONE_AMM:
			componenteDTO = new ComponenteProgrammazioneFormAmmDTO();
			((ComponenteProgrammazioneFormAmmDTO)componenteDTO).setItems(items);
			break;
		case S_PROGRAMMAZIONE_FORMAZIONE_TEC:
			componenteDTO = new ComponenteProgrammazioneFormTecDTO();
			((ComponenteProgrammazioneFormTecDTO)componenteDTO).setItems(items);
			break;
		case S_PROGRAMMAZIONE_FORMAZIONE_AUS:
			componenteDTO = new ComponenteProgrammazioneFormAusDTO();
			((ComponenteProgrammazioneFormAusDTO)componenteDTO).setItems(items);
			break;

		default:
			throw new RuntimeException("Tipo componente non gestito");
		}
    	
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1027Performazpersonale tbs1027Performazpersonale : tbs1004Datisezionesottosez.getTbs1027Performazpersonales()){

    		ProgrammazioneFormDTO programmazioneFormDTO = new ProgrammazioneFormDTO();
    		BeanValueDTO ambitoFormativo = new BeanValueDTO();
    		ambitoFormativo.setLabel(tbs1027Performazpersonale.getTbs1019Tipoambito().getDesTipAmb());
    		ambitoFormativo.setValue(tbs1027Performazpersonale.getTbs1019Tipoambito().getCodTipAmb());
    		programmazioneFormDTO.setAmbitoFormativo(ambitoFormativo);
    		programmazioneFormDTO.setAreaTematicaPNSD(tbs1027Performazpersonale.getDesAreTemPsd());
    		BeanValueDTO collaborazioneReteScolastic = new BeanValueDTO();
    		collaborazioneReteScolastic.setLabel(tbs1027Performazpersonale.getFlgColRes());
    		collaborazioneReteScolastic.setValue(tbs1027Performazpersonale.getFlgColRes());
    		programmazioneFormDTO.setCollaborazioneReteScolastic(collaborazioneReteScolastic);
    		BeanValueDTO condiviso = new BeanValueDTO();
    		collaborazioneReteScolastic.setLabel(tbs1027Performazpersonale.getFlgCnd());
    		collaborazioneReteScolastic.setValue(tbs1027Performazpersonale.getFlgCnd());
    		programmazioneFormDTO.setCondiviso(condiviso);
    		programmazioneFormDTO.setContenuti(tbs1027Performazpersonale.getDesCnt());
    		programmazioneFormDTO.setDataFine(tbs1027Performazpersonale.getDatFinVal());
    		programmazioneFormDTO.setDataInizio(tbs1027Performazpersonale.getDatIniVal());
    		programmazioneFormDTO.setDenominazionePercorsoFormativo(tbs1027Performazpersonale.getDesPerFor());
    		programmazioneFormDTO.setKey(tbs1027Performazpersonale.getPrgForPer().toString());
    		programmazioneFormDTO.setObiettivi(tbs1027Performazpersonale.getDesObi());
    		BeanValueDTO percorsoFormativo = new BeanValueDTO();
    		collaborazioneReteScolastic.setLabel(tbs1027Performazpersonale.getFlgForOts());
    		collaborazioneReteScolastic.setValue(tbs1027Performazpersonale.getFlgForOts());
    		programmazioneFormDTO.setPercorsoFormativo(percorsoFormativo);
    		programmazioneFormDTO.setTempiOreComplessive(new BigDecimal(tbs1027Performazpersonale.getDesOreCmp()));
    		
    		programmazioneFormDTO.setCancellabile(tbs1027Performazpersonale.getDesNomBenSer() == null || tbs1027Performazpersonale.getDesNomBenSer().isEmpty());
    		programmazioneFormDTO.setModificabile(tbs1027Performazpersonale.getDesNomBenSer() == null || tbs1027Performazpersonale.getDesNomBenSer().isEmpty());
    		programmazioneFormDTO.setVisualizzabile(true);
    		
    		items.add(programmazioneFormDTO);
    	}
    }

    public void gestisciLoadComponenteS_STRUMENTI_ATTREZZATURE_TECNOLOGIA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteStrumentiAttrezzatureTecnologicheDTO componenteDTO = new ComponenteStrumentiAttrezzatureTecnologicheDTO();
    	logger.debug("in gestisciLoadComponenteS_STRUMENTI_ATTREZZATURE_TECNOLOGIA");
    	LinkedList<StrumentiAttrezzatureTecnologicheDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1028Attrezzature tbs1028Attrezzature : tbs1004Datisezionesottosez.getTbs1028Attrezzatures()){

    		StrumentiAttrezzatureTecnologicheDTO strumentiAttrezzatureTecnologicheDTO = new StrumentiAttrezzatureTecnologicheDTO();
    		strumentiAttrezzatureTecnologicheDTO.setKey(tbs1028Attrezzature.getPrgAtt().toString());
    		strumentiAttrezzatureTecnologicheDTO.setAreaTematicaPNSD(tbs1028Attrezzature.getDesAreTemPsd());
    		strumentiAttrezzatureTecnologicheDTO.setDescrizione(tbs1028Attrezzature.getDesObbIst());
    		strumentiAttrezzatureTecnologicheDTO.setStrumentiAttrezzature(tbs1028Attrezzature.getDesStrAtt());
    		items.add(strumentiAttrezzatureTecnologicheDTO);
    	}
    }

    public void gestisciLoadComponenteS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteDotazioneIstPriDTO componenteDTO = new ComponenteDotazioneIstPriDTO();
    	logger.debug("in gestisciLoadComponentegestisciLoadComponenteS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE");
    	LinkedList<DotazioneIstPriDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1029Dotazionemultimediale tbs1029Dotazionemultimediale : tbs1004Datisezionesottosez.getTbs1029Dotazionemultimediales()){

    		DotazioneIstPriDTO dotazioneIstPriDTO = new DotazioneIstPriDTO();
    		dotazioneIstPriDTO.setAreaTematicaPNSD(tbs1029Dotazionemultimediale.getDesAreTemPsd());
    		dotazioneIstPriDTO.setDotazioniMultimediali(tbs1029Dotazionemultimediale.getDesDotMus());
    		dotazioneIstPriDTO.setKey(tbs1029Dotazionemultimediale.getPrgDotMus().toString());
    		dotazioneIstPriDTO.setNumero(new BigDecimal(tbs1029Dotazionemultimediale.getNumDotMld()));
    		items.add(dotazioneIstPriDTO);
    	}
    }

    public void gestisciLoadComponenteS_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponentePromozioneRapportiEntiTerritorioDTO componenteDTO = new ComponentePromozioneRapportiEntiTerritorioDTO();
    	logger.debug("in gestisciLoadComponenteS_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO");
    	LinkedList<PromozioneRapportiEntiTerritorioDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1030Rapportoente tbs1030Rapportoente : tbs1004Datisezionesottosez.getTbs1030Rapportoentes()){

    		PromozioneRapportiEntiTerritorioDTO promozioneRapportiEntiTerritorioDTO = new PromozioneRapportiEntiTerritorioDTO();
    		promozioneRapportiEntiTerritorioDTO.setAreaTematicaPNSD(tbs1030Rapportoente.getDesAreTemPsd());
    		promozioneRapportiEntiTerritorioDTO.setAzioniIntraprese(tbs1030Rapportoente.getDesAziInt());
    		promozioneRapportiEntiTerritorioDTO.setEntiLocali(tbs1030Rapportoente.getDesEntLoc());
    		promozioneRapportiEntiTerritorioDTO.setKey(tbs1030Rapportoente.getPrgRapEnt().toString());
    		promozioneRapportiEntiTerritorioDTO.setTipologiaPromozione(tbs1030Rapportoente.getDesPro());
    		items.add(promozioneRapportiEntiTerritorioDTO);
    	}
    }

    public void gestisciLoadComponenteS_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteConvPotenzFormativoDTO componenteDTO = new ComponenteConvPotenzFormativoDTO();
    	logger.debug("in gestisciLoadComponenteS_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO");
    	LinkedList<ConvPotenzFormativoDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1031Convenzioneforma tbs1031Convenzioneforma : tbs1004Datisezionesottosez.getTbs1031Convenzioneformas()){

    		ConvPotenzFormativoDTO convPotenzFormativoDTO = new ConvPotenzFormativoDTO();
    		convPotenzFormativoDTO.setAreaTematicaPNSD(tbs1031Convenzioneforma.getDesAreTemPsd());
    		convPotenzFormativoDTO.setConvenzioni(tbs1031Convenzioneforma.getDesCnv());
    		convPotenzFormativoDTO.setKey(tbs1031Convenzioneforma.getPrgCnvFor().toString());
    		convPotenzFormativoDTO.setNote(tbs1031Convenzioneforma.getDesNot());
    		convPotenzFormativoDTO.setOrganizzazione(tbs1031Convenzioneforma.getDesOrg());
    		items.add(convPotenzFormativoDTO);
    	}
    }

    public void gestisciLoadComponenteS_PIANIFICAZIONE_ATTIVITA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponentePianificazioneInterventiMonitoraggioDTO componenteDTO = new ComponentePianificazioneInterventiMonitoraggioDTO();
    	logger.debug("in gestisciLoadComponenteS_PIANIFICAZIONE_ATTIVITA");
    	LinkedList<PianificazioneInterventiMonitoraggioDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	for (Tbs1032Pianificattivita tbs1032Pianificattivita: tbs1004Datisezionesottosez.getTbs1032Pianificattivitas()){

    		PianificazioneInterventiMonitoraggioDTO pianificazioneInterventiMonitoraggioDTO = new PianificazioneInterventiMonitoraggioDTO();
    		pianificazioneInterventiMonitoraggioDTO.setCapitoloPTOF(tbs1032Pianificattivita.getDesCapPto());
    		pianificazioneInterventiMonitoraggioDTO.setDataFine(tbs1032Pianificattivita.getDatFinVal());
    		pianificazioneInterventiMonitoraggioDTO.setDataInizio(tbs1032Pianificattivita.getDatIniVal());
    		pianificazioneInterventiMonitoraggioDTO.setDescrizione(tbs1032Pianificattivita.getDesIntMon());
    		pianificazioneInterventiMonitoraggioDTO.setKey(tbs1032Pianificattivita.getPrgPiaAtt().toString());
    		pianificazioneInterventiMonitoraggioDTO.setPianificazioneAttivita(tbs1032Pianificattivita.getDesPiaAtt());
    		items.add(pianificazioneInterventiMonitoraggioDTO);
    	}
    }

    public void gestisciLoadComponenteS_MONITORAGGIO_PIANIFICAZIONE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/**
    	 *  Il dato inserito nei due attributi viene inserito nella tbs1004Datisezionesottosez con la transazione SALVA SEZIONE.
    	 **/
    	logger.debug("in gestisciLoadComponenteS_MONITORAGGIO_PIANIFICAZIONE");
    	ComponenteMonitoraggioPianificazioneDTO componenteDTO = new ComponenteMonitoraggioPianificazioneDTO();
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	componenteDTO.setModalitaVerifica(tbs1004Datisezionesottosez.getDesModVer());
    	componenteDTO.setSoluzioniAdottateRispettoPianificazione(tbs1004Datisezionesottosez.getDesRapPia());
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_AA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	TipologiaScuola tipologiaScuola = tipologiaScuolaRepository.findTipologiaScuola(tbs1004Datisezionesottosez.getCodScuUte(), "AA");
    	if (tipologiaScuola != null){
    		ComponenteArticolazioneClassiAADTO componenteDTO = new ComponenteArticolazioneClassiAADTO();
    		logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_AA");
    		LinkedList<ArticolazioneClassiAADTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);

    		for (Tbs1034Articoclasseinfanzia tbs1034Articoclasseinfanzia: tbs1004Datisezionesottosez.getTbs1034Articoclasseinfanzias()){

    			ArticolazioneClassiAADTO articolazioneClassiAADTO = new ArticolazioneClassiAADTO();
    			articolazioneClassiAADTO.setDescrizione(tbs1034Articoclasseinfanzia.getDesTipPos());
    			articolazioneClassiAADTO.setKey(tbs1034Articoclasseinfanzia.getPrgArcInf().toString());
    			articolazioneClassiAADTO.setSezioneOrarioNormale(tbs1034Articoclasseinfanzia.getNumSezNor());
    			articolazioneClassiAADTO.setSezioneOrarioRidotto(tbs1034Articoclasseinfanzia.getNumSezRid());
    			articolazioneClassiAADTO.setTipologiaPosti(tbs1034Articoclasseinfanzia.getCodTipPos());

    			items.add(articolazioneClassiAADTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_EE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	TipologiaScuola tipologiaScuola = tipologiaScuolaRepository.findTipologiaScuola(tbs1004Datisezionesottosez.getCodScuUte(), "EE");
    	if (tipologiaScuola != null){
    		ComponenteArticolazioneClassiEEDTO componenteDTO = new ComponenteArticolazioneClassiEEDTO();
    		logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_EE");
    		LinkedList<ArticolazioneClassiEEDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);

    		for (Tbs1035Articoclasseprimaria tbs1035Articoclasseprimaria: tbs1004Datisezionesottosez.getTbs1035Articoclasseprimarias()){

    			ArticolazioneClassiEEDTO articolazioneClassiEEDTO = new ArticolazioneClassiEEDTO();
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormaleI(tbs1035Articoclasseprimaria.getNumClaTmpN01());
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormaleII(tbs1035Articoclasseprimaria.getNumClaTmpN02());
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormaleIII(tbs1035Articoclasseprimaria.getNumClaTmpN03());
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormaleIV(tbs1035Articoclasseprimaria.getNumClaTmpN04());
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormaleV(tbs1035Articoclasseprimaria.getNumClaTmpN05());
    			articolazioneClassiEEDTO.setClassiAlunniTempoNormalePLURICL(tbs1035Articoclasseprimaria.getNumClaTmpNpl());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoI(tbs1035Articoclasseprimaria.getNumClaTmpP01());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoII(tbs1035Articoclasseprimaria.getNumClaTmpP02());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoIII(tbs1035Articoclasseprimaria.getNumClaTmpP03());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoIV(tbs1035Articoclasseprimaria.getNumClaTmpP04());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoV(tbs1035Articoclasseprimaria.getNumClaTmpP05());
    			articolazioneClassiEEDTO.setClassiAlunniTempoPienoPLURICL(tbs1035Articoclasseprimaria.getNumClaTmpPpl());
    			articolazioneClassiEEDTO.setDescrizione(tbs1035Articoclasseprimaria.getDesTipPos());
    			articolazioneClassiEEDTO.setTipologiaPosti(tbs1035Articoclasseprimaria.getCodTipPos());
    			articolazioneClassiEEDTO.setKey(tbs1035Articoclasseprimaria.getPrgArcPri().toString());

    			items.add(articolazioneClassiEEDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	TipologiaScuola tipologiaScuola = tipologiaScuolaRepository.findTipologiaScuola(tbs1004Datisezionesottosez.getCodScuUte(), "MM");
    	if (tipologiaScuola != null){
    		ComponenteArticolazioneClassiMMIDTO componenteDTO = new ComponenteArticolazioneClassiMMIDTO();
    		logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMI");
    		LinkedList<ArticolazioneClassiIMMIDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);

    		for (Tbs1036Articolaclasseigrado tbs1036Articolaclasseigrado: tbs1004Datisezionesottosez.getTbs1036Articolaclasseigrados()){

    			ArticolazioneClassiIMMIDTO articolazioneClassiIMMIDTO = new ArticolazioneClassiIMMIDTO();
    			articolazioneClassiIMMIDTO.setKey(tbs1036Articolaclasseigrado.getPrgArcPgr().toString());
    			articolazioneClassiIMMIDTO.setTempoNormaleTotaliClassiI(tbs1036Articolaclasseigrado.getNumClaTmpN01());
    			articolazioneClassiIMMIDTO.setTempoNormaleTotaliClassiII(tbs1036Articolaclasseigrado.getNumClaTmpN02());
    			articolazioneClassiIMMIDTO.setTempoNormaleTotaliClassiIII(tbs1036Articolaclasseigrado.getNumClaTmpN03());
    			articolazioneClassiIMMIDTO.setTempoProlungatoTotaliClassiI(tbs1036Articolaclasseigrado.getNumClaTmpP01());
    			articolazioneClassiIMMIDTO.setTempoProlungatoTotaliClassiII(tbs1036Articolaclasseigrado.getNumClaTmpP02());
    			articolazioneClassiIMMIDTO.setTempoProlungatoTotaliClassiIII(tbs1036Articolaclasseigrado.getNumClaTmpP03());
    			
    			items.add(articolazioneClassiIMMIDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMII(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	TipologiaScuola tipologiaScuola = tipologiaScuolaRepository.findTipologiaScuola(tbs1004Datisezionesottosez.getCodScuUte(), "SS");
    	if (tipologiaScuola != null){
    		ComponenteArticolazioneClassiMMIIDTO componenteDTO = new ComponenteArticolazioneClassiMMIIDTO();
    		logger.debug("in gestisciLoadComponenteS_ORGANIZZAZIONE_CLASSI_MMII");
    		LinkedList<ArticolazioneClassiMMIIDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);

    		for (Tbs1038Articolaclasseiigrado tbs1038Articolaclasseiigrado: tbs1004Datisezionesottosez.getTbs1038Articolaclasseiigrados()){

    			ArticolazioneClassiMMIIDTO articolazioneClassiMMIIDTO = new ArticolazioneClassiMMIIDTO();
    			articolazioneClassiMMIIDTO.setClassiI(tbs1038Articolaclasseiigrado.getNumCla001());
    			articolazioneClassiMMIIDTO.setClassiII(tbs1038Articolaclasseiigrado.getNumCla002());
    			articolazioneClassiMMIIDTO.setClassiIII(tbs1038Articolaclasseiigrado.getNumCla003());
    			articolazioneClassiMMIIDTO.setClassiIV(tbs1038Articolaclasseiigrado.getNumCla004());
    			articolazioneClassiMMIIDTO.setClassiV(tbs1038Articolaclasseiigrado.getNumCla005());
    			articolazioneClassiMMIIDTO.setClassiVI(tbs1038Articolaclasseiigrado.getNumCla006());
    			articolazioneClassiMMIIDTO.setIndirizzo(tbs1038Articolaclasseiigrado.getDesInd());
    			articolazioneClassiMMIIDTO.setKey(tbs1038Articolaclasseiigrado.getPrgArcSgr().toString());

    			items.add(articolazioneClassiMMIIDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_AA_EE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
  	
    	LinkedList<Tbs1037Fabpostocomune> fabbisogni = tbs1037FabpostocomuneRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesClcAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd);
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiCattedreEEAADTO componenteDTO = new ComponenteFabbisognoPostiCattedreEEAADTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE " + scuOrd);
    		LinkedList<FabbisognoPostiCattedreDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1037Fabpostocomune tbs1037Fabpostocomune: fabbisogni){

    			FabbisognoPostiCattedreDTO fabbisognoPostiCattedreDTO = new FabbisognoPostiCattedreDTO();
    			fabbisognoPostiCattedreDTO.setKey(tbs1037Fabpostocomune.getPrgFabPosCom().toString());
    			fabbisognoPostiCattedreDTO.setMotivazione(tbs1037Fabpostocomune.getDesMot());
    			fabbisognoPostiCattedreDTO.setPostiComuniPrimoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosPri()));
    			fabbisognoPostiCattedreDTO.setPostiComuniSecondoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosSec()));
    			fabbisognoPostiCattedreDTO.setPostiComuniTerzoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosTer()));
    			
    			fabbisognoPostiCattedreDTO.setCancellabile(false);
    			fabbisognoPostiCattedreDTO.setModificabile(true);
    			fabbisognoPostiCattedreDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiCattedreDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE_MM_SS(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1037Fabpostocomune> fabbisogni = tbs1037FabpostocomuneRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesClcAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd);
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiCattedreMMDTO componenteDTO = new ComponenteFabbisognoPostiCattedreMMDTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_CATTEDRE " + scuOrd);
    		LinkedList<FabbisognoPostiCattedreMMDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);

    		for (Tbs1037Fabpostocomune tbs1037Fabpostocomune: fabbisogni){

    			FabbisognoPostiCattedreMMDTO fabbisognoPostiCattedreDTO = new FabbisognoPostiCattedreMMDTO();
    			fabbisognoPostiCattedreDTO.setKey(tbs1037Fabpostocomune.getPrgFabPosCom().toString());
    			fabbisognoPostiCattedreDTO.setMotivazione(tbs1037Fabpostocomune.getDesMot());
    			fabbisognoPostiCattedreDTO.setCattedrePrimoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosPri()));
    			fabbisognoPostiCattedreDTO.setCattedreSecondoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosSec()));
    			fabbisognoPostiCattedreDTO.setCattedreTerzoAnnoTriennio(toInteger(tbs1037Fabpostocomune.getNumPosTer()));
    			fabbisognoPostiCattedreDTO.setClasseConcorso(tbs1037Fabpostocomune.getDesClc());
    			
    			fabbisognoPostiCattedreDTO.setCancellabile(false);
    			fabbisognoPostiCattedreDTO.setModificabile(true);
    			fabbisognoPostiCattedreDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiCattedreDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_CONNESSO_PROGETTO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteFabbisognoConnessoProgettoDTO componenteDTO = new ComponenteFabbisognoConnessoProgettoDTO();
    	logger.debug("in gestisciLoadComponenteS_FABBISOGNO_CONNESSO_PROGETTO");
    	LinkedList<FabbisognoConnessoProgettoDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenteDTO.setItemsAggiungibili(false);
    	componenti.add(componenteDTO);
    	
    	List<Tbs1021Ambitoprogettoptof> tbs1021Ambitoprogettoptofs = tbs1021AmbitoprogettoptofRepository.findByTbs1004DatisezionesottosezCodScuUte(tbs1004Datisezionesottosez.getCodScuUte());
    	for (Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof: tbs1021Ambitoprogettoptofs){

    		FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO = new FabbisognoConnessoProgettoDTO();
    		fabbisognoConnessoProgettoDTO.setClassificazione(tbs1021Ambitoprogettoptof.getDesCls());
    		BeanValueDTO denominazioneProgettiScuola = new BeanValueDTO();
    		denominazioneProgettiScuola.setLabel(tbs1021Ambitoprogettoptof.getDesDenPgtCur());
    		denominazioneProgettiScuola.setValue("P"+tbs1021Ambitoprogettoptof.getPrgAmbPgtPto().toString());
    		fabbisognoConnessoProgettoDTO.setDenominazioneProgettiScuola(denominazioneProgettiScuola);
    		fabbisognoConnessoProgettoDTO.setDescrizioneBeneServizio(tbs1021Ambitoprogettoptof.getDesBenSer());
    		fabbisognoConnessoProgettoDTO.setImportoStimato(tbs1021Ambitoprogettoptof.getImpSti());
    		fabbisognoConnessoProgettoDTO.setKey("P"+tbs1021Ambitoprogettoptof.getPrgAmbPgtPto().toString());
    		fabbisognoConnessoProgettoDTO.setNomeBeneServizio(tbs1021Ambitoprogettoptof.getDesNomBenSer());
    		fabbisognoConnessoProgettoDTO.setNote(tbs1021Ambitoprogettoptof.getDesNot());
    		fabbisognoConnessoProgettoDTO.setNumeroDocentiATACoinvolti(tbs1021Ambitoprogettoptof.getNumDocAta());
    		BeanValueDTO tipoProgetti = new BeanValueDTO();
    		tipoProgetti.setLabel(tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getDesDenPgt());
    		tipoProgetti.setValue("P"+tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getId().getPrgPgtAmb().toString());
    		fabbisognoConnessoProgettoDTO.setTipoProgetti(tipoProgetti);
    		fabbisognoConnessoProgettoDTO.setTotaleImportoStimatoProgetto(
    				importoTotaleRepository.findImportoTotalePerTipoProgetto(
    						tbs1004Datisezionesottosez.getCodScuUte(),
    						tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getId().getPrgPgtAmb()
    						).getImp());
    		fabbisognoConnessoProgettoDTO.setCodiceMeccanograficoPlesso(tbs1021Ambitoprogettoptof.getTbs1004Datisezionesottosez().getCodScuUtePle());

    		items.add(fabbisognoConnessoProgettoDTO);
    	}
    	
    	List<Tbs1022Ambitoprogettoaltro> tbs1022Ambitoprogettoaltros = tbs1022AmbitoprogettoaltroRepository.findByTbs1004DatisezionesottosezCodScuUte(tbs1004Datisezionesottosez.getCodScuUte());
    	for (Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro: tbs1022Ambitoprogettoaltros){

    		FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO = new FabbisognoConnessoProgettoDTO();
    		fabbisognoConnessoProgettoDTO.setClassificazione(tbs1022Ambitoprogettoaltro.getDesCls());
    		BeanValueDTO denominazioneProgettiScuola = new BeanValueDTO();
    		denominazioneProgettiScuola.setLabel(tbs1022Ambitoprogettoaltro.getDesDenPgtCur());
    		denominazioneProgettiScuola.setValue("A"+tbs1022Ambitoprogettoaltro.getPrgAmbPgtAlt().toString());
    		fabbisognoConnessoProgettoDTO.setDenominazioneProgettiScuola(denominazioneProgettiScuola);
    		fabbisognoConnessoProgettoDTO.setDescrizioneBeneServizio(tbs1022Ambitoprogettoaltro.getDesBenSer());
    		fabbisognoConnessoProgettoDTO.setImportoStimato(tbs1022Ambitoprogettoaltro.getImpSti());
    		fabbisognoConnessoProgettoDTO.setKey("A"+tbs1022Ambitoprogettoaltro.getPrgAmbPgtAlt().toString());
    		fabbisognoConnessoProgettoDTO.setNomeBeneServizio(tbs1022Ambitoprogettoaltro.getDesNomBenSer());
    		fabbisognoConnessoProgettoDTO.setNote(tbs1022Ambitoprogettoaltro.getDesNot());
    		fabbisognoConnessoProgettoDTO.setNumeroDocentiATACoinvolti(tbs1022Ambitoprogettoaltro.getNumDocAta());
    		BeanValueDTO tipoProgetti = new BeanValueDTO();
    		tipoProgetti.setLabel(tbs1022Ambitoprogettoaltro.getDesDenPgtAlt());
    		tipoProgetti.setValue("A"+tbs1022Ambitoprogettoaltro.getDesDenPgtAlt());
    		fabbisognoConnessoProgettoDTO.setTipoProgetti(tipoProgetti);
    		fabbisognoConnessoProgettoDTO.setTotaleImportoStimatoProgetto(
    				importoTotaleRepository.findImportoTotalePerTipoAltroProgetto(
    						tbs1004Datisezionesottosez.getCodScuUte(),
    						tbs1022Ambitoprogettoaltro.getDesDenPgtAlt()
    						).getImp());
    		fabbisognoConnessoProgettoDTO.setCodiceMeccanograficoPlesso(tbs1022Ambitoprogettoaltro.getTbs1004Datisezionesottosez().getCodScuUtePle());
    		
    		items.add(fabbisognoConnessoProgettoDTO);
    	}
    	
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_CONNESSO_FORMAZIONE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	ComponenteFabbisognoConnessoFormazioneDTO componenteDTO = new ComponenteFabbisognoConnessoFormazioneDTO();
    	logger.debug("in gestisciLoadComponenteS_FABBISOGNO_CONNESSO_FORMAZIONE");
    	LinkedList<FabbisognoConnessoFormazioneDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenteDTO.setItemsAggiungibili(false);
    	componenti.add(componenteDTO);
    	
    	List<Tbs1027Performazpersonale> tbs1027Performazpersonales = tbs1027PerformazpersonaleRepository.findByTbs1004DatisezionesottosezCodScuUte(tbs1004Datisezionesottosez.getCodScuUte());
    	for (Tbs1027Performazpersonale tbs1027Performazpersonale: tbs1027Performazpersonales){

    		FabbisognoConnessoFormazioneDTO fabbisognoConnessoFormazioneDTO = new FabbisognoConnessoFormazioneDTO();
    		fabbisognoConnessoFormazioneDTO.setClassificazione(tbs1027Performazpersonale.getDesCls());
    		BeanValueDTO denominazionePercorsoFormativo = new BeanValueDTO();
    		denominazionePercorsoFormativo.setLabel(tbs1027Performazpersonale.getDesPerFor());
    		denominazionePercorsoFormativo.setValue(tbs1027Performazpersonale.getPrgForPer().toString());
    		fabbisognoConnessoFormazioneDTO.setDenominazionePercorsoFormativo(denominazionePercorsoFormativo);
    		fabbisognoConnessoFormazioneDTO.setDescrizioneBeneServizio(tbs1027Performazpersonale.getDesBenSer());
    		fabbisognoConnessoFormazioneDTO.setImportoStimato(tbs1027Performazpersonale.getImpSti());
    		fabbisognoConnessoFormazioneDTO.setKey(tbs1027Performazpersonale.getPrgForPer().toString());
    		fabbisognoConnessoFormazioneDTO.setNomeBeneServizio(tbs1027Performazpersonale.getDesNomBenSer());
    		fabbisognoConnessoFormazioneDTO.setNote(tbs1027Performazpersonale.getDesNot());
    		fabbisognoConnessoFormazioneDTO.setNumeroDocentiATACoinvolti(tbs1027Performazpersonale.getNumDocAta());
    		BeanValueDTO ambitoFormativo = new BeanValueDTO();
    		ambitoFormativo.setLabel(tbs1027Performazpersonale.getTbs1019Tipoambito().getDesTipAmb());
    		ambitoFormativo.setValue(tbs1027Performazpersonale.getTbs1019Tipoambito().getCodTipAmb());
    		fabbisognoConnessoFormazioneDTO.setAmbitoFormativo(ambitoFormativo);
    		
    		fabbisognoConnessoFormazioneDTO.setTotaleImportoStimatoAmbitoFormativo(
    				importoTotaleRepository.findImportoTotalePerAmbitoFormativo(
    						tbs1004Datisezionesottosez.getCodScuUte(),
    						tbs1027Performazpersonale.getTbs1019Tipoambito().getCodTipAmb()
    						).getImp());
    		
    		fabbisognoConnessoFormazioneDTO.setCodiceMeccanograficoPlesso(tbs1027Performazpersonale.getTbs1004Datisezionesottosez().getCodScuUtePle());

    		items.add(fabbisognoConnessoFormazioneDTO);
    	}
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_AA__EE__MM(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1039Fabpostosostegno> fabbisogni = tbs1039FabpostosostegnoRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesAreDisAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd);
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiSostegnoEEAADTO componenteDTO = new ComponenteFabbisognoPostiSostegnoEEAADTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_AA__EE__MM " + scuOrd);
    		LinkedList<FabbisognoPostiSostegnoEEAADTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1039Fabpostosostegno tbs1039Fabpostosostegno: fabbisogni){

    			FabbisognoPostiSostegnoEEAADTO fabbisognoPostiSostegnoEEAADTO = new FabbisognoPostiSostegnoEEAADTO();
    			fabbisognoPostiSostegnoEEAADTO.setKey(tbs1039Fabpostosostegno.getPrgFabPosSos().toString());
    			fabbisognoPostiSostegnoEEAADTO.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
    			
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoUditoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiPri()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoUditoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiSec()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoUditoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosUdiTer()));

    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoVistaPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisPri()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoVistaSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisSec()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoVistaTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosVisTer()));

    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoPsicofisicoPrimoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiPri()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoPsicofisicoSecondoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiSec()));
    			fabbisognoPostiSostegnoEEAADTO.setPostiSostegnoPsicofisicoTerzoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosPsiTer()));

    			fabbisognoPostiSostegnoEEAADTO.setCancellabile(false);
    			fabbisognoPostiSostegnoEEAADTO.setModificabile(true);
    			fabbisognoPostiSostegnoEEAADTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiSostegnoEEAADTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_SS(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1039Fabpostosostegno> fabbisogni = tbs1039FabpostosostegnoRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesAreDisAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd);
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiSostegnoMMIIDTO componenteDTO = new ComponenteFabbisognoPostiSostegnoMMIIDTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_SOSTEGNO_SS " + scuOrd);
    		LinkedList<FabbisognoPostiSostegnoMMIIDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1039Fabpostosostegno tbs1039Fabpostosostegno: fabbisogni){

    			FabbisognoPostiSostegnoMMIIDTO fabbisognoPostiSostegnoMMIIDTO = new FabbisognoPostiSostegnoMMIIDTO();
    			fabbisognoPostiSostegnoMMIIDTO.setKey(tbs1039Fabpostosostegno.getPrgFabPosSos().toString());
    			fabbisognoPostiSostegnoMMIIDTO.setMotivazione(tbs1039Fabpostosostegno.getDesMot());
    			
    			fabbisognoPostiSostegnoMMIIDTO.setNumeroPostiPrimoAnnoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgPri()));
    			fabbisognoPostiSostegnoMMIIDTO.setNumeroPostiSecondoAnnoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgSec()));
    			fabbisognoPostiSostegnoMMIIDTO.setNumeroPostiTerzoAnnoTriennio(toInteger(tbs1039Fabpostosostegno.getNumPosSsgTer()));

    			fabbisognoPostiSostegnoMMIIDTO.setCancellabile(false);
    			fabbisognoPostiSostegnoMMIIDTO.setModificabile(true);
    			fabbisognoPostiSostegnoMMIIDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiSostegnoMMIIDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1040Fabpostopotenziamento> fabbisogni = tbs1040FabpostopotenziamentoRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuOrderByDesClcAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd);
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiPotenziamentoEEAADTO componenteDTO = new ComponenteFabbisognoPostiPotenziamentoEEAADTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE " + scuOrd);
    		LinkedList<FabbisognoPostiPotenziamentoEEAADTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento: fabbisogni){

    			FabbisognoPostiPotenziamentoEEAADTO fabbisognoPostiPotenziamentoEEAADTO = new FabbisognoPostiPotenziamentoEEAADTO();
    			fabbisognoPostiPotenziamentoEEAADTO.setKey(tbs1040Fabpostopotenziamento.getPrgFabPosPtz().toString());
    			fabbisognoPostiPotenziamentoEEAADTO.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
    			
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiComuniPrimoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComPri()));
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiComuniSecondoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComSec()));
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiComuniTerzoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComTer()));
    			
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiSostegnoPrimoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosPri()));
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiSostegnoSecondoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosSec()));
    			fabbisognoPostiPotenziamentoEEAADTO.setPostiSostegnoTerzoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosTer()));

    			fabbisognoPostiPotenziamentoEEAADTO.setCancellabile(false);
    			fabbisognoPostiPotenziamentoEEAADTO.setModificabile(true);
    			fabbisognoPostiPotenziamentoEEAADTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiPotenziamentoEEAADTO);
    		}
    	}
    }


    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1040Fabpostopotenziamento> fabbisogni = tbs1040FabpostopotenziamentoRepository.
				findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuAndDesClcNotOrderByDesClcAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd, "Sostegno");
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiPotenziamentoMMDTO componenteDTO = new ComponenteFabbisognoPostiPotenziamentoMMDTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS " + scuOrd);
    		LinkedList<FabbisognoPostiPotenziamentoMMDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento: fabbisogni){

    			FabbisognoPostiPotenziamentoMMDTO fabbisognoPostiPotenziamentoMMDTO = new FabbisognoPostiPotenziamentoMMDTO();
    			fabbisognoPostiPotenziamentoMMDTO.setKey(tbs1040Fabpostopotenziamento.getPrgFabPosPtz().toString());
    			fabbisognoPostiPotenziamentoMMDTO.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
    			fabbisognoPostiPotenziamentoMMDTO.setClasseConcorso(tbs1040Fabpostopotenziamento.getDesClc());
    			
    			fabbisognoPostiPotenziamentoMMDTO.setPostiPotenziamentoPrimoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComPri()));
    			fabbisognoPostiPotenziamentoMMDTO.setPostiPotenziamentoSecondoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComSec()));
    			fabbisognoPostiPotenziamentoMMDTO.setPostiPotenziamentoTerzoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosComTer()));

      			fabbisognoPostiPotenziamentoMMDTO.setCancellabile(false);
    			fabbisognoPostiPotenziamentoMMDTO.setModificabile(true);
    			fabbisognoPostiPotenziamentoMMDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiPotenziamentoMMDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS_SOSTEGNO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, String scuOrd, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1040Fabpostopotenziamento> fabbisogni = tbs1040FabpostopotenziamentoRepository.
				findByTbs1004DatisezionesottosezPrgDatPtfAndCodOrdScuAndDesClcOrderByDesClcAsc(tbs1004Datisezionesottosez.getPrgDatPtf(), scuOrd, "Sostegno");
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO componenteDTO = new ComponenteFabbisognoPostiPotenziamentoSostegnoMMDTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SS_SOSTEGNO " + scuOrd);
    		LinkedList<FabbisognoPostiPotenziamentoSostegnoMMDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento: fabbisogni){

    			FabbisognoPostiPotenziamentoSostegnoMMDTO fabbisognoPostiPotenziamentoSostegnoMMDTO = new FabbisognoPostiPotenziamentoSostegnoMMDTO();
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setKey(tbs1040Fabpostopotenziamento.getPrgFabPosPtz().toString());
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setMotivazione(tbs1040Fabpostopotenziamento.getDesMot());
    			
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setPostiSostegnoPrimoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosPri()));
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setPostiSostegnoSecondoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosSec()));
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setPostiSostegnoTerzoAnno(toInteger(tbs1040Fabpostopotenziamento.getNumPosSosTer()));

    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setCancellabile(false);
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setModificabile(true);
    			fabbisognoPostiPotenziamentoSostegnoMMDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiPotenziamentoSostegnoMMDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
      	
    	LinkedList<Tbs1041Fabfiguraprofessionale> fabbisogni = tbs1041FabfiguraprofessionaleRepository.
    										findByTbs1004DatisezionesottosezPrgDatPtfOrderByDesFigPrfAsc(tbs1004Datisezionesottosez.getPrgDatPtf());
    	
    	if (fabbisogni != null && !fabbisogni.isEmpty()){
    		ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO componenteDTO = new ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO();
    		logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI ");
    		LinkedList<FabbisognoPostiPersonaleAmmTecAusiDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int annoInizioTriennio = Integer.valueOf(tbs1001Gestionecatalogodoc.getPerTriRif().toString().substring(0,4)).intValue();
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			String labelAnno0 = String.valueOf(annoInizioTriennio) + "/" + String.valueOf(annoInizioTriennio + 1).substring(2,4);
			String labelAnno1 = String.valueOf(annoInizioTriennio + 1) + "/" + String.valueOf(annoInizioTriennio + 2).substring(2,4);
			String labelAnno2 = String.valueOf(annoInizioTriennio +2) + "/" + String.valueOf(annoInizioTriennio + 3).substring(2,4);
			
    		componenteDTO.setVersione(numVer);
    		componenteDTO.setLabelAnno0(labelAnno0);
    		componenteDTO.setLabelAnno1(labelAnno1);
    		componenteDTO.setLabelAnno2(labelAnno2);
    		

    		for (Tbs1041Fabfiguraprofessionale tbs1041Fabfiguraprofessionale: fabbisogni){

    			FabbisognoPostiPersonaleAmmTecAusiDTO fabbisognoPostiPersonaleAmmTecAusiDTO = new FabbisognoPostiPersonaleAmmTecAusiDTO();
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setKey(tbs1041Fabfiguraprofessionale.getPrgFabPosFpf().toString());
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setMotivazione(tbs1041Fabfiguraprofessionale.getDesMot());
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setFiguraProfessionale(tbs1041Fabfiguraprofessionale.getDesFigPrf());
    			
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setNumeroPostiPrimoAnnoTriennio(toInteger(tbs1041Fabfiguraprofessionale.getNumPosPri()));
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setNumeroPostiSecondoAnnoTriennio(toInteger(tbs1041Fabfiguraprofessionale.getNumPosSec()));
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setNumeroPostiTerzoAnnoTriennio(toInteger(tbs1041Fabfiguraprofessionale.getNumPosTer()));
    			
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setCancellabile(false);
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setModificabile(true);
    			fabbisognoPostiPersonaleAmmTecAusiDTO.setVisualizzabile(true);

    			items.add(fabbisognoPostiPersonaleAmmTecAusiDTO);
    		}
    	}
    }

    public void gestisciLoadComponenteS_PIANO_NAZIONALE_SCUOLA_DIGITALE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){


    	ComponentePianoNazionaleScuolaDigitaleDTO componenteDTO = new ComponentePianoNazionaleScuolaDigitaleDTO();
    	logger.debug("in gestisciLoadComponenteS_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI ");
    	LinkedList<PianoNazionaleScuolaDigitaleDTO> items = new LinkedList<>();
    	componenteDTO.setItems(items);
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);

    	LinkedList<Pnsd> pnsds = pnsdRepository.find(tbs1004Datisezionesottosez.getCodScuUte());
    	for (Pnsd pnsd : pnsds){
    		PianoNazionaleScuolaDigitaleDTO pianoNazionaleScuolaDigitaleDTO = new PianoNazionaleScuolaDigitaleDTO();
    		pianoNazionaleScuolaDigitaleDTO.setKey(pnsd.getId());
    		pianoNazionaleScuolaDigitaleDTO.setAreaTematicaPNSD(pnsd.getArea());
    		pianoNazionaleScuolaDigitaleDTO.setNumeroSezione(pnsd.getCodSez());
    		pianoNazionaleScuolaDigitaleDTO.setDescrizioneSezione(pnsd.getDesSez());
    		pianoNazionaleScuolaDigitaleDTO.setContenutoLegatoPNSD(pnsd.getContenuto());
    		pianoNazionaleScuolaDigitaleDTO.setCancellabile(false);
    		pianoNazionaleScuolaDigitaleDTO.setModificabile(false);
    		pianoNazionaleScuolaDigitaleDTO.setVisualizzabile(true);
    		items.add(pianoNazionaleScuolaDigitaleDTO);
    	}
    }

    public void gestisciLoadComponenteS_ALTERNANZA_SCUOLA_LAVORO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){


    	if (CommonsUtility.isSecondariaDiSecondoGrado(tbs1004Datisezionesottosez.getCodScuUtePle())){
    		ComponenteAlternzaScuolaLavoroDTO componenteDTO = new ComponenteAlternzaScuolaLavoroDTO();
    		logger.debug("in gestisciLoadComponenteS_ALTERNANZA_SCUOLA_LAVORO ");
    		LinkedList<AlternanzaScuolaLavoroDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);

    		LinkedList<AlternanzaScuolaLavoro> alternanzaScuolaLavoros = alternanzaScuolaLavoroRepository.find(tbs1004Datisezionesottosez.getCodScuUtePle());
    		for (AlternanzaScuolaLavoro alternanzaScuolaLavoro : alternanzaScuolaLavoros){
    			AlternanzaScuolaLavoroDTO alternanzaScuolaLavoroDTO = new AlternanzaScuolaLavoroDTO();
    			alternanzaScuolaLavoroDTO.setIdentificativoPercorso(alternanzaScuolaLavoro.getPercorso());
    			alternanzaScuolaLavoroDTO.setStruttura(alternanzaScuolaLavoro.getStruttura());
    			alternanzaScuolaLavoroDTO.setAzienda(alternanzaScuolaLavoro.getAzienda());
    			alternanzaScuolaLavoroDTO.setNumeroAlunniPrimoAnnoTriennio(alternanzaScuolaLavoro.getAlunniIAnno());
    			alternanzaScuolaLavoroDTO.setNumeroAlunniSecondoAnnoTriennio(alternanzaScuolaLavoro.getAlunniIIAnno());
    			alternanzaScuolaLavoroDTO.setNumeroAlunniTerzoAnnoTriennio(alternanzaScuolaLavoro.getAlunniIIIAnno());
    			alternanzaScuolaLavoroDTO.setCancellabile(false);
    			alternanzaScuolaLavoroDTO.setModificabile(false);
    			alternanzaScuolaLavoroDTO.setVisualizzabile(false);
    			items.add(alternanzaScuolaLavoroDTO);
    		}
    	}
    }
    
    public void updateStatoSezione(Long prgDatPtf, IstitutoScolasticoDTO istitutoScolasticoDTO, TIPO_STATO_SEZIONE statoSezione) {
		
		logger.debug("update Stato Sezione");
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findByPrgDatPtfAndCodScuUte(prgDatPtf, istitutoScolasticoDTO.getCodiceMecIstPrin());
		if (tbs1004Datisezionesottosez == null){
			throw new RuntimeException("Attenzione: chiave sezione e codice meccanografico istituto non corrispondenti");
		}
		
		tbs1004Datisezionesottosez.setCodSta(statoSezione.code());
		tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
	}

    public void updateStatoSezione(Long prgDatPtf, TIPO_STATO_SEZIONE statoSezione) {
		
		logger.debug("update Stato Sezione");
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findOne(prgDatPtf);
		if (tbs1004Datisezionesottosez == null){
			throw new RuntimeException("Attenzione: chiave dati sezione non esistente");
		}
		
		tbs1004Datisezionesottosez.setCodSta(statoSezione.code());
		tbs1004DatisezionesottosezRepository.save(tbs1004Datisezionesottosez);
	}
	
    public void gestisciStatoSezionePadre(Long prgDatPtf) {
		
		this.gestisciStatoSezioneRaggruppantePlessi(prgDatPtf);
		logger.debug("in gestisciStatoSezionePadre : " + prgDatPtf);
		
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findOne(prgDatPtf);
		if (tbs1004Datisezionesottosez == null){
			throw new RuntimeException("Attenzione: chiave dati sezione non esistente");
		}else{
			if (tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSezPad() != null){
				Long prgSezSotSezPad = tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSezPad();
				logger.debug("è una sottosezione della sezione " + prgSezSotSezPad);
				List<Tbs1004Datisezionesottosez> sottosezioni = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezPadAndCodScuUteAndPrgGesCatDocAndPerAnnSco(prgSezSotSezPad, tbs1004Datisezionesottosez.getCodScuUte(), tbs1004Datisezionesottosez.getPrgGesCatDoc(), tbs1004Datisezionesottosez.getPerAnnSco());
				boolean trovataSottosezioneInBozza = false;
				for (Tbs1004Datisezionesottosez sottosezione : sottosezioni){
					logger.debug("chiave sottosezione : " + sottosezione.getPrgDatPtf());
					if (sottosezione.getCodSta().equals(TIPO_STATO_SEZIONE.BOZZA.code())){
						trovataSottosezioneInBozza = true;
					}
				}
				
				Tbs1004Datisezionesottosez sezionePadre = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(prgSezSotSezPad, tbs1004Datisezionesottosez.getCodScuUte(), tbs1004Datisezionesottosez.getPrgGesCatDoc(), tbs1004Datisezionesottosez.getPerAnnSco());
				TIPO_STATO_SEZIONE stato_to_update = (trovataSottosezioneInBozza)?TIPO_STATO_SEZIONE.BOZZA:TIPO_STATO_SEZIONE.COMPILATA;
				logger.debug("chiave sezione padre : " + sezionePadre.getPrgDatPtf());
				this.updateStatoSezione(sezionePadre.getPrgDatPtf(), stato_to_update);
				
			}
		}
		
	}

    public void gestisciStatoSezioneRaggruppantePlessi(Long prgDatPtf) {
		
		logger.debug("in gestisciStatoSezioneRaggruppantePlessi : " + prgDatPtf);
		Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = tbs1004DatisezionesottosezRepository.findOne(prgDatPtf);
		if (tbs1004Datisezionesottosez == null){
			throw new RuntimeException("Attenzione: chiave dati sezione non esistente");
		}else{
			if (tbs1004Datisezionesottosez.getCodScuUtePle() != null && !tbs1004Datisezionesottosez.getCodScuUtePle().isEmpty()){
				logger.debug("è una sottosezione di tipo PLESSO");
				/** cerco i record su Tbs1004Datisezionesottosez relativi alo stesso PRG_SEZ_SOT_SEZ e con codScuUtePle diverso da null **/
				List<Tbs1004Datisezionesottosez> sottosezioni = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndCodScuUtePleIsNotNullAndPrgGesCatDocAndPerAnnSco(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez(), tbs1004Datisezionesottosez.getCodScuUte(), tbs1004Datisezionesottosez.getPrgGesCatDoc(), tbs1004Datisezionesottosez.getPerAnnSco());
				boolean trovataSottosezioneInBozza = false;
				for (Tbs1004Datisezionesottosez sottosezione : sottosezioni){
					logger.debug("chiave sottosezione : " + sottosezione.getPrgDatPtf());
					if (sottosezione.getCodSta().equals(TIPO_STATO_SEZIONE.BOZZA.code())){
						trovataSottosezioneInBozza = true;
					}
				}
				
				Tbs1004Datisezionesottosez sezionePadre = tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndCodScuUtePleIsNullAndPrgGesCatDocAndPerAnnSco(tbs1004Datisezionesottosez.getTbs1003Catalogosezione().getPrgSezSotSez(), tbs1004Datisezionesottosez.getCodScuUte(), tbs1004Datisezionesottosez.getPrgGesCatDoc(), tbs1004Datisezionesottosez.getPerAnnSco());
				TIPO_STATO_SEZIONE stato_to_update = (trovataSottosezioneInBozza)?TIPO_STATO_SEZIONE.BOZZA:TIPO_STATO_SEZIONE.COMPILATA;
				logger.debug("chiave sezione padre : " + sezionePadre.getPrgDatPtf());
				this.updateStatoSezione(sezionePadre.getPrgDatPtf(), stato_to_update);
				
			}
		}
		
	}

    public void gestisciLoadComponenteS_DATI_FINALI_SCUOLA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento, String versione){
    	/**
    	 *  Il dato inserito nei 4 attributi viene inserito nella tbs1004Datisezionesottosez con la transazione SALVA SEZIONE.
    	 **/
    	logger.debug("in gestisciLoadComponenteS_DATI_FINALI_SCUOLA");
    	ComponenteDatiFinaliScuolaDTO componenteDTO = new ComponenteDatiFinaliScuolaDTO();
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	componenteDTO.setDataRatificaAttoIndirizzoDirigente(tbs1004Datisezionesottosez.getDatRatDirSco());
    	componenteDTO.setDataPredisposizionePTOFCollegioDocenti(tbs1004Datisezionesottosez.getDatPrdPto());
    	componenteDTO.setDataApprovazioneConsiglioDocenti(tbs1004Datisezionesottosez.getDatAppCdi());
    	componenteDTO.setDataInvioUSR(tbs1004Datisezionesottosez.getDatInvUsr());
    	componenteDTO.setVersione(versione);
    }
    
    public void gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	if (CommonsUtility.isSecondariaDiPrimoGrado(tbs1004Datisezionesottosez.getCodScuUtePle()) || CommonsUtility.isSecondariaDiSecondoGrado(tbs1004Datisezionesottosez.getCodScuUtePle())){
    		ComponenteArticolazioneOrariaIndirizziStudioDTO componenteDTO = new ComponenteArticolazioneOrariaIndirizziStudioDTO();
    		logger.debug("in gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO");
    		LinkedList<ArticolazioneOrariaIndirizziStudioDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setNota(tbs1004Datisezionesottosez.getDesNotIndStu());
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		LinkedList<IndirizzoStudio> indirizzoStudios = indirizzoStudioRepository.find(tbs1004Datisezionesottosez.getCodScuUtePle());

    		for (IndirizzoStudio indirizzoStudio: indirizzoStudios){

    			ArticolazioneOrariaIndirizziStudioDTO articolazioneOrariaIndirizziStudioDTO = new ArticolazioneOrariaIndirizziStudioDTO();
    			//articolazioneOrariaIndirizziStudioDTO.setAnnoScolasticoFineErogazioneIndirizzo(indirizzoStudio.getAnnoFine());
    			//articolazioneOrariaIndirizziStudioDTO.setAnnoScolasticoInizioErogazioneIndirizzo(indirizzoStudio.getAnnoInizio());
    			articolazioneOrariaIndirizziStudioDTO.setDescrizione(indirizzoStudio.getDescrizione());
    			articolazioneOrariaIndirizziStudioDTO.setIndirizzoStudio(indirizzoStudio.getIndirizzo());
    			articolazioneOrariaIndirizziStudioDTO.setKey(indirizzoStudio.getId());

    			items.add(articolazioneOrariaIndirizziStudioDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){

    	if (CommonsUtility.isSecondariaDiSecondoGrado(tbs1004Datisezionesottosez.getCodScuUtePle())){
    		ComponenteArticolazioneQuadriOrariDTO componenteDTO = new ComponenteArticolazioneQuadriOrariDTO();
    		logger.debug("in gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO");
    		LinkedList<ArticolazioneQuadriOrariDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setNota(tbs1004Datisezionesottosez.getDesNotQuaOra());
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		LinkedList<QuadroOrario> quadroOrarios = quadroOrarioRepository.find(tbs1004Datisezionesottosez.getCodScuUtePle());

    		for (QuadroOrario quadroOrario: quadroOrarios){

    			ArticolazioneQuadriOrariDTO articolazioneQuadriOrariDTO = new ArticolazioneQuadriOrariDTO();
    			articolazioneQuadriOrariDTO.setAnnoScolastico(quadroOrario.getAnno());
    			articolazioneQuadriOrariDTO.setDescrizioneIndirizzoScolastico(quadroOrario.getIndirizzo());
    			articolazioneQuadriOrariDTO.setDescrizionePianoStudio(quadroOrario.getPianoStudio());
    			articolazioneQuadriOrariDTO.setKey(quadroOrario.getPrgPiaStu().toString());
    			
    			LinkedList<ArticolazioneQuadriOrariMateriaDTO> dettagliQuadroOrario = new LinkedList<>();
    			LinkedList<QuadroOrarioDettaglio> quadroOrarioDettaglios = quadroOrarioDettaglioRepository.find(tbs1004Datisezionesottosez.getCodScuUtePle(), quadroOrario.getPrgPiaStu());
    			for (QuadroOrarioDettaglio quadroOrarioDettaglio : quadroOrarioDettaglios){
    				
    				ArticolazioneQuadriOrariMateriaDTO articolazioneQuadriOrariMateriaDTO = new ArticolazioneQuadriOrariMateriaDTO();
    				articolazioneQuadriOrariMateriaDTO.setDescrizioneMateriaScuola(quadroOrarioDettaglio.getMateria());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniPrimo(quadroOrarioDettaglio.getNumPrima());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniSecondo(quadroOrarioDettaglio.getNumSeconda());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniTerzo(quadroOrarioDettaglio.getNumTerza());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniQuarto(quadroOrarioDettaglio.getNumQuarta());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniQuinto(quadroOrarioDettaglio.getNumQuinta());
    				articolazioneQuadriOrariMateriaDTO.setNumeroAlunniSesto(quadroOrarioDettaglio.getNumSesta());
    				
    				dettagliQuadroOrario.add(articolazioneQuadriOrariMateriaDTO);
    				
    			}
    			
    			
    			articolazioneQuadriOrariDTO.setItems(dettagliQuadroOrario);

    			items.add(articolazioneQuadriOrariDTO);
    		}
    	}
    	
    }
    

    public void gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){


    	
    	if (CommonsUtility.isPrimaria(tbs1004Datisezionesottosez.getCodScuUtePle()) || CommonsUtility.isSecondariaDiPrimoGrado(tbs1004Datisezionesottosez.getCodScuUtePle())){
    		ComponenteArticolazioneTempiScuolaDTO componenteDTO = new ComponenteArticolazioneTempiScuolaDTO();
    		logger.debug("in gestisciLoadComponenteS_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA");
    		LinkedList<ArticolazioneTempiScuolaDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setNota(tbs1004Datisezionesottosez.getDesNotTmpScu());
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		LinkedList<TempoScuola> tempoScuolas = tempoScuolaRepository.find(tbs1004Datisezionesottosez.getCodScuUtePle());

    		for (TempoScuola tempoScuola: tempoScuolas){

        		ArticolazioneTempiScuolaDTO articolazioneTempiScuolaDTO = new ArticolazioneTempiScuolaDTO();
        		articolazioneTempiScuolaDTO.setKey(tempoScuola.getId());
        		articolazioneTempiScuolaDTO.setDescrizioneTempoScuola(tempoScuola.getTempoScuola());
        		
        		articolazioneTempiScuolaDTO.setCancellabile(false);
        		articolazioneTempiScuolaDTO.setVisualizzabile(true);
        		articolazioneTempiScuolaDTO.setModificabile(false);
        		
        		items.add(articolazioneTempiScuolaDTO);
    		}
    	}
    	
    }

    public void gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_COMPONENTE tipo_componente, TIPO_STATO_DOC statoDocumento){

    	logger.debug("in gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA");
    	boolean aggiungiIlComponente = false;
    	switch (tipo_componente) {
		case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_ATTR:
		case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_INFR:
			aggiungiIlComponente = (CommonsUtility.isPrimaria(tbs1004Datisezionesottosez.getCodScuUte()) || CommonsUtility.isInfanzia(tbs1004Datisezionesottosez.getCodScuUte()));
			break;
		case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_ATTR:
		case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_INFR:
			aggiungiIlComponente = (CommonsUtility.isSecondariaDiPrimoGrado(tbs1004Datisezionesottosez.getCodScuUte()));
			break;
		case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_ATTR:
		case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_INFR:
			aggiungiIlComponente = (CommonsUtility.isSecondariaDiSecondoGrado(tbs1004Datisezionesottosez.getCodScuUte()));
			break;

		default:
			throw new RuntimeException("Tipo componente non gestito");
		}
    	
    	
    	if (aggiungiIlComponente){
    		ComponenteFabbisognoAttrezzatureInfraDTO componenteDTO = new ComponenteFabbisognoAttrezzatureInfraDTO();
    		LinkedList<FabbisognoAttrezzatureInfraDTO> items = new LinkedList<>();
    		componenteDTO.setItems(items);
    		componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    		setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    		componenti.add(componenteDTO);
    		
    		String codOrdScu = null;
    		String codTipFab = null;
    		switch (tipo_componente) {
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_ATTR:
    			codOrdScu = "AE";
    			codTipFab = "AT";
    			break;
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_INFR:
    			codOrdScu = "AE";
    			codTipFab = "IN";
    			break;
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_ATTR:
    			codOrdScu = "MM";
    			codTipFab = "AT";
    			break;
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_MM_INFR:
    			codOrdScu = "MM";
    			codTipFab = "IN";
    			break;
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_ATTR:
    			codOrdScu = "SS";
    			codTipFab = "AT";
    			break;
    		case S_FABBISOGNO_ATTREZZATURE_INFRA_SS_INFR:
    			codOrdScu = "SS";
    			codTipFab = "IN";
    			break;

    		default:
    			throw new RuntimeException("Tipo componente non gestito");
    		}
    		
    		componenteDTO.setCodOrdScu(codOrdScu);
    		componenteDTO.setCodTipFab(codTipFab);
    		
    		for (Tbs1042Fabattrezmateriale tbs1042Fabattrezmateriale: tbs1004Datisezionesottosez.getTbs1042Fabattrezmateriales()){
    			
    			if (tbs1042Fabattrezmateriale.getCodOrdScu().equals(codOrdScu) && tbs1042Fabattrezmateriale.getCodTipFab().equals(codTipFab)){
    				
    				FabbisognoAttrezzatureInfraDTO fabbisognoAttrezzatureInfraDTO = new FabbisognoAttrezzatureInfraDTO();
    				fabbisognoAttrezzatureInfraDTO.setAreaTematicaPNSD(tbs1042Fabattrezmateriale.getDesAreTemPsd());
    				fabbisognoAttrezzatureInfraDTO.setDescrizione(tbs1042Fabattrezmateriale.getDesAtm());
    				fabbisognoAttrezzatureInfraDTO.setFonteFinanziamento(tbs1042Fabattrezmateriale.getDesFonFin());
    				fabbisognoAttrezzatureInfraDTO.setKey(tbs1042Fabattrezmateriale.getPrgFabAtm().toString());
    				fabbisognoAttrezzatureInfraDTO.setNumeroPezzi(tbs1042Fabattrezmateriale.getNumAtm());
    				fabbisognoAttrezzatureInfraDTO.setStimaCosti((tbs1042Fabattrezmateriale.getDesStiCos()!=null && !tbs1042Fabattrezmateriale.getDesStiCos().isEmpty())?new BigDecimal(tbs1042Fabattrezmateriale.getDesStiCos()):new BigDecimal("0"));
    				fabbisognoAttrezzatureInfraDTO.setTipologia(tbs1042Fabattrezmateriale.getDesTipAtm());
    				items.add(fabbisognoAttrezzatureInfraDTO);
    				
    			}
    		}
    		
    		
    	}
    }
    
    public void gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez, Tbs1014Componentesezione tbs1014Componentesezione, LinkedList<ComponenteDTO> componenti, TIPO_STATO_DOC statoDocumento){
    	/**
    	 *  Il dato inserito nei due attributi viene inserito nella tbs1004Datisezionesottosez con la transazione SALVA SEZIONE.
    	 **/
    	logger.debug("in gestisciLoadComponenteS_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO");
    	ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO componenteDTO = new ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO();
    	componenteDTO.setKey(tbs1014Componentesezione.getCodTipCmp());
    	setCommonFieldToComponenteDTO(componenteDTO, tbs1014Componentesezione, tbs1004Datisezionesottosez, statoDocumento);
    	componenti.add(componenteDTO);
    	
    	componenteDTO.setBeneficiAttesi(tbs1004Datisezionesottosez.getDesBeaFabAtm());
    	componenteDTO.setFinalita(tbs1004Datisezionesottosez.getDesFinFabAtm());
    	componenteDTO.setMotivazione(tbs1004Datisezionesottosez.getDesMotFabAtm());
    }
    

    public void copyDataS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla tabella dello scarico della rilevazione dotazione multimediale TBS1033_DOTAZIONMUSICSCUOLA
    	 * Se li trovo li carico sulla corrispondente tabella PTOF TBS1029_DOTAZIONEMULTIMEDIALE
    	 **/
    	
    	logger.debug("findDotazioniMultimedialiByCodiceScuolaAndAnnoScolastico start");
    	DotazioniMultimediali dotazioniMultimediali = dotazioniMultimedialiRepository
    										.findDotazioniMultimedialiByCodiceScuolaAndAnnoScolastico(tbs1004Datisezionesottosez.getCodScuUte(), tbs1004Datisezionesottosez.getPerAnnSco());
    	if (dotazioniMultimediali != null){
    		logger.debug(ReflectionToStringBuilder.toString(dotazioniMultimediali,ToStringStyle.MULTI_LINE_STYLE));
    		
    		Tbs1029Dotazionemultimediale computer = new Tbs1029Dotazionemultimediale();
    		computer.setDesDotMus("Computer");
    		computer.setNumDotMld(dotazioniMultimediali.getComputer());
    		computer.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    		
    		Tbs1029Dotazionemultimediale dispositiviMobili = new Tbs1029Dotazionemultimediale();
    		dispositiviMobili.setDesDotMus("Dispositivi Mobili");
    		dispositiviMobili.setNumDotMld(dotazioniMultimediali.getDispositiviMobili());
    		dispositiviMobili.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    		
    		Tbs1029Dotazionemultimediale lim = new Tbs1029Dotazionemultimediale();
    		lim.setDesDotMus("Lim");
    		lim.setNumDotMld(dotazioniMultimediali.getLim());
    		lim.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    		
    		Tbs1029Dotazionemultimediale proiettoriInterattivi = new Tbs1029Dotazionemultimediale();
    		proiettoriInterattivi.setDesDotMus("Proiettori Interattivi");
    		proiettoriInterattivi.setNumDotMld(dotazioniMultimediali.getProiettoriInterattivi());
    		proiettoriInterattivi.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    		
    		tbs1029DotazionemultimedialeRepository.save(computer);
    		tbs1029DotazionemultimedialeRepository.save(dispositiviMobili);
    		tbs1029DotazionemultimedialeRepository.save(lim);
    		tbs1029DotazionemultimedialeRepository.save(proiettoriInterattivi);
    		
/*    		List<Tbs1033Dotazionmusicscuola> tbs1033Dotazionmusicscuolas = tbs1033DotazionmusicscuolaRepository.findByCodScuUtePri(dotazioniMultimediali.getId().getCodScuUtePri());
    		for (Tbs1033Dotazionmusicscuola tbs1033Dotazionmusicscuola : tbs1033Dotazionmusicscuolas){
    			tbs1033Dotazionmusicscuola.setCodPgmUltMov("ELABORATO");
    			tbs1033DotazionmusicscuolaRepository.save(tbs1033Dotazionmusicscuola);
    		}*/
    		
    	}
    	logger.debug("findDotazioniMultimedialiByCodiceScuolaAndAnnoScolastico end");
    }
    
    public void copyDataSEZIONE_17_ORGANIZZAZIONE_CLASSI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla vista VBS1149_PTFODACLA relativi all'articolazione delle classi
    	 * Se li trovo li carico sulle corrispondenti tabelle PTOF
    	 * TBS1034_ARTICOCLASSEINFANZIA
		 * TBS1035_ARTICOCLASSEPRIMARIA
		 * TBS1036_ARTICOLACLASSEIGRADO
		 * TBS1038_ARTICOLACLASSEIIGRADO
    	 **/
    	
    	logger.debug("copyDataSEZIONE_17_ORGANIZZAZIONE_CLASSI start");
    	
    	LinkedList<ArticolazioneClassiAA> articolazioneClassiAAs = articolazioneClassiAARepository.findArticolazioneClassi(tbs1004Datisezionesottosez.getCodScuUte());
    	if (articolazioneClassiAAs != null && !articolazioneClassiAAs.isEmpty()){
    		logger.debug("trovate articolazioneClassiAA : " + articolazioneClassiAAs.size());
    		for (ArticolazioneClassiAA articolazioneClassiAA: articolazioneClassiAAs){
    			logger.debug(ReflectionToStringBuilder.toString(articolazioneClassiAA,ToStringStyle.MULTI_LINE_STYLE));
    			Tbs1034Articoclasseinfanzia tbs1034Articoclasseinfanzia = new Tbs1034Articoclasseinfanzia();
    			tbs1034Articoclasseinfanzia.setCodTipPos("XX");
    			tbs1034Articoclasseinfanzia.setDesTipPos("XX");
    			tbs1034Articoclasseinfanzia.setNumSezNor(articolazioneClassiAA.getSezioniN());
    			tbs1034Articoclasseinfanzia.setNumSezRid(articolazioneClassiAA.getSezioniR());
    			tbs1034Articoclasseinfanzia.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1034ArticoclasseinfanziaRepository.save(tbs1034Articoclasseinfanzia);
    		}
    	}
    	
    	LinkedList<ArticolazioneClassiEE> articolazioneClassiEEs = articolazioneClassiEERepository.findArticolazioneClassi(tbs1004Datisezionesottosez.getCodScuUte());
    	if (articolazioneClassiEEs != null && !articolazioneClassiEEs.isEmpty()){
    		logger.debug("trovate articolazioneClassiEE : " + articolazioneClassiEEs.size());
    		for (ArticolazioneClassiEE articolazioneClassiEE: articolazioneClassiEEs){
    			logger.debug(ReflectionToStringBuilder.toString(articolazioneClassiEE,ToStringStyle.MULTI_LINE_STYLE));
    			Tbs1035Articoclasseprimaria tbs1035Articoclasseprimaria = new Tbs1035Articoclasseprimaria();
    			tbs1035Articoclasseprimaria.setCodTipPos("XX");
    			tbs1035Articoclasseprimaria.setDesTipPos("XX");
    			tbs1035Articoclasseprimaria.setNumClaTmpN01(articolazioneClassiEE.getCl1N());
    			tbs1035Articoclasseprimaria.setNumClaTmpN02(articolazioneClassiEE.getCl2N());
    			tbs1035Articoclasseprimaria.setNumClaTmpN03(articolazioneClassiEE.getCl3N());
    			tbs1035Articoclasseprimaria.setNumClaTmpN04(articolazioneClassiEE.getCl4N());
    			tbs1035Articoclasseprimaria.setNumClaTmpN05(articolazioneClassiEE.getCl5N());
    			tbs1035Articoclasseprimaria.setNumClaTmpNpl(articolazioneClassiEE.getPluN());
    			tbs1035Articoclasseprimaria.setNumClaTmpP01(articolazioneClassiEE.getCl1P());
    			tbs1035Articoclasseprimaria.setNumClaTmpP02(articolazioneClassiEE.getCl2P());
    			tbs1035Articoclasseprimaria.setNumClaTmpP03(articolazioneClassiEE.getCl3P());
    			tbs1035Articoclasseprimaria.setNumClaTmpP04(articolazioneClassiEE.getCl4P());
    			tbs1035Articoclasseprimaria.setNumClaTmpP05(articolazioneClassiEE.getCl5P());
    			tbs1035Articoclasseprimaria.setNumClaTmpPpl(articolazioneClassiEE.getPluP());
    			tbs1035Articoclasseprimaria.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1035ArticoclasseprimariaRepository.save(tbs1035Articoclasseprimaria);
    		}
    	}

    	LinkedList<ArticolazioneClassiMMI> articolazioneClassiMMIs = articolazioneClassiMMIRepository.findArticolazioneClassi(tbs1004Datisezionesottosez.getCodScuUte());
    	if (articolazioneClassiMMIs != null && !articolazioneClassiMMIs.isEmpty()){
    		logger.debug("trovate articolazioneClassiMMI : " + articolazioneClassiMMIs.size());
    		for (ArticolazioneClassiMMI articolazioneClassiMMI: articolazioneClassiMMIs){
    			logger.debug(ReflectionToStringBuilder.toString(articolazioneClassiMMI,ToStringStyle.MULTI_LINE_STYLE));
    			Tbs1036Articolaclasseigrado tbs1036Articolaclasseigrado = new Tbs1036Articolaclasseigrado();
    			tbs1036Articolaclasseigrado.setNumClaTmpN01(articolazioneClassiMMI.getCl1N());
    			tbs1036Articolaclasseigrado.setNumClaTmpN02(articolazioneClassiMMI.getCl2N());
    			tbs1036Articolaclasseigrado.setNumClaTmpN03(articolazioneClassiMMI.getCl3N());
    			tbs1036Articolaclasseigrado.setNumClaTmpP01(articolazioneClassiMMI.getCl1P());
    			tbs1036Articolaclasseigrado.setNumClaTmpP02(articolazioneClassiMMI.getCl2P());
    			tbs1036Articolaclasseigrado.setNumClaTmpP03(articolazioneClassiMMI.getCl3P());
    			tbs1036Articolaclasseigrado.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1036ArticolaclasseigradoRepository.save(tbs1036Articolaclasseigrado);
    		}
    	}

    	LinkedList<ArticolazioneClassiMMII> articolazioneClassiMMIIs = articolazioneClassiMMIIRepository.findArticolazioneClassi(tbs1004Datisezionesottosez.getCodScuUte());
    	if (articolazioneClassiMMIIs != null && !articolazioneClassiMMIIs.isEmpty()){
    		logger.debug("trovate articolazioneClassiMMII : " + articolazioneClassiMMIIs.size());
    		for (ArticolazioneClassiMMII articolazioneClassiMMII: articolazioneClassiMMIIs){
    			logger.debug(ReflectionToStringBuilder.toString(articolazioneClassiMMII,ToStringStyle.MULTI_LINE_STYLE));
    			Tbs1038Articolaclasseiigrado tbs1038Articolaclasseiigrado = new Tbs1038Articolaclasseiigrado();
    			tbs1038Articolaclasseiigrado.setDesInd(articolazioneClassiMMII.getDesSetEco());
    			tbs1038Articolaclasseiigrado.setNumCla001(articolazioneClassiMMII.getCl1());
    			tbs1038Articolaclasseiigrado.setNumCla002(articolazioneClassiMMII.getCl2());
    			tbs1038Articolaclasseiigrado.setNumCla003(articolazioneClassiMMII.getCl3());
    			tbs1038Articolaclasseiigrado.setNumCla004(articolazioneClassiMMII.getCl4());
    			tbs1038Articolaclasseiigrado.setNumCla005(articolazioneClassiMMII.getCl5());
    			tbs1038Articolaclasseiigrado.setNumCla006(articolazioneClassiMMII.getCl6());
    			tbs1038Articolaclasseiigrado.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1038ArticolaclasseiigradoRepository.save(tbs1038Articolaclasseiigrado);
    		}
    	}
    	
    	logger.debug("copyDataSEZIONE_17_ORGANIZZAZIONE_CLASSI end");
    }

    public void copyDataSEZIONE_35_FABBISOGNO_POSTI_CATTEDRE(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla vista VBS1150_PTFODPOSDOC
    	 * Se li trovo li carico sulla corrispondente tabella PTOF
    	 * TBS1037_FABPOSTOCOMUNE
    	 **/
    	
    	logger.debug("copyDataSEZIONE_35_FABBISOGNO_POSTI_CATTEDRE start");
    	
    	LinkedList<FabbisognoPostiCattedre> fabbisognoPostiCattedres = fabbisognoPostiCattedreRepository.find(tbs1004Datisezionesottosez.getCodScuUte());
    	if (fabbisognoPostiCattedres != null && !fabbisognoPostiCattedres.isEmpty()){
    		logger.debug("trovate FabbisognoPostiCattedre : " + fabbisognoPostiCattedres.size());
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			
    		for (FabbisognoPostiCattedre fabbisognoPostiCattedre: fabbisognoPostiCattedres){
    			Tbs1037Fabpostocomune tbs1037Fabpostocomune = new Tbs1037Fabpostocomune();
    			tbs1037Fabpostocomune.setCodOrdScu(fabbisognoPostiCattedre.getCodScuOrd());
    			
    			if (numVer == 0){
    				tbs1037Fabpostocomune.setNumPosPri(fabbisognoPostiCattedre.getNumPosti());
    				tbs1037Fabpostocomune.setNumPosSec(fabbisognoPostiCattedre.getNumPosti());
    				tbs1037Fabpostocomune.setNumPosTer(fabbisognoPostiCattedre.getNumPosti());
    			}else if (numVer == 1){
    				tbs1037Fabpostocomune.setNumPosPri(new BigDecimal(0));
    				tbs1037Fabpostocomune.setNumPosSec(fabbisognoPostiCattedre.getNumPosti());
    				tbs1037Fabpostocomune.setNumPosTer(fabbisognoPostiCattedre.getNumPosti());
    			}else if (numVer == 2){
    				tbs1037Fabpostocomune.setNumPosPri(new BigDecimal(0));
    				tbs1037Fabpostocomune.setNumPosSec(new BigDecimal(0));
    				tbs1037Fabpostocomune.setNumPosTer(fabbisognoPostiCattedre.getNumPosti());
    			}
    			
    			tbs1037Fabpostocomune.setDesMot(null);
    			if (!fabbisognoPostiCattedre.getCodScuOrd().equals("AA") && !fabbisognoPostiCattedre.getCodScuOrd().equals("EE")){
    				tbs1037Fabpostocomune.setCodClc(fabbisognoPostiCattedre.getCodClc());
    				tbs1037Fabpostocomune.setDesClc(fabbisognoPostiCattedre.getDesClc());
    			}
    			tbs1037Fabpostocomune.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1037FabpostocomuneRepository.save(tbs1037Fabpostocomune);
    		}
    	}    	
    }

    public void copyDataSEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla vista VBS1151_PTFODPOSDOCSOS
    	 * Se li trovo li carico sulla corrispondente tabella PTOF
    	 * TBS1039_FABPOSTOSOSTEGNO
    	 **/
    	
    	logger.debug("copyDataSEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO start");
    	
    	LinkedList<FabbisognoPostiSostegno> fabbisognoPostiSostegnos = fabbisognoPostiSostegnoRepository.find(tbs1004Datisezionesottosez.getCodScuUte());
    	if (fabbisognoPostiSostegnos != null && !fabbisognoPostiSostegnos.isEmpty()){
    		logger.debug("trovati FabbisognoPostiSostegno : " + fabbisognoPostiSostegnos.size());
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			
    		for (FabbisognoPostiSostegno fabbisognoPostiSostegno: fabbisognoPostiSostegnos){
    			Tbs1039Fabpostosostegno tbs1039Fabpostosostegno = new Tbs1039Fabpostosostegno();
    			tbs1039Fabpostosostegno.setCodOrdScu(fabbisognoPostiSostegno.getCodScuOrd());
    			
    			if (fabbisognoPostiSostegno.getCodScuOrd().equals("AA")
    					|| fabbisognoPostiSostegno.getCodScuOrd().equals("EE")
    					|| fabbisognoPostiSostegno.getCodScuOrd().equals("MM")){
    				if (numVer == 0){
    					tbs1039Fabpostosostegno.setNumPosUdiPri(fabbisognoPostiSostegno.getNumPostiSosUD());
    					tbs1039Fabpostosostegno.setNumPosUdiSec(fabbisognoPostiSostegno.getNumPostiSosUD());
    					tbs1039Fabpostosostegno.setNumPosUdiTer(fabbisognoPostiSostegno.getNumPostiSosUD());

    					tbs1039Fabpostosostegno.setNumPosVisPri(fabbisognoPostiSostegno.getNumPostiSosVI());
    					tbs1039Fabpostosostegno.setNumPosVisSec(fabbisognoPostiSostegno.getNumPostiSosVI());
    					tbs1039Fabpostosostegno.setNumPosVisTer(fabbisognoPostiSostegno.getNumPostiSosVI());

    					tbs1039Fabpostosostegno.setNumPosPsiPri(fabbisognoPostiSostegno.getNumPostiSosPF());
    					tbs1039Fabpostosostegno.setNumPosPsiSec(fabbisognoPostiSostegno.getNumPostiSosPF());
    					tbs1039Fabpostosostegno.setNumPosPsiTer(fabbisognoPostiSostegno.getNumPostiSosPF());
    				}else if (numVer == 1){
    					tbs1039Fabpostosostegno.setNumPosUdiPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosUdiSec(fabbisognoPostiSostegno.getNumPostiSosUD());
    					tbs1039Fabpostosostegno.setNumPosUdiTer(fabbisognoPostiSostegno.getNumPostiSosUD());

    					tbs1039Fabpostosostegno.setNumPosVisPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosVisSec(fabbisognoPostiSostegno.getNumPostiSosVI());
    					tbs1039Fabpostosostegno.setNumPosVisTer(fabbisognoPostiSostegno.getNumPostiSosVI());

    					tbs1039Fabpostosostegno.setNumPosPsiPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosPsiSec(fabbisognoPostiSostegno.getNumPostiSosPF());
    					tbs1039Fabpostosostegno.setNumPosPsiTer(fabbisognoPostiSostegno.getNumPostiSosPF());
    				}else if (numVer == 2){
    					tbs1039Fabpostosostegno.setNumPosUdiPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosUdiSec(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosUdiTer(fabbisognoPostiSostegno.getNumPostiSosUD());

    					tbs1039Fabpostosostegno.setNumPosVisPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosVisSec(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosVisTer(fabbisognoPostiSostegno.getNumPostiSosVI());

    					tbs1039Fabpostosostegno.setNumPosPsiPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosPsiSec(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosPsiTer(fabbisognoPostiSostegno.getNumPostiSosPF());
    				}
    			}else {
    				/** SS **/
    				if (numVer == 0){
    					tbs1039Fabpostosostegno.setNumPosSsgPri(fabbisognoPostiSostegno.getNumPostiSosTOT());
    					tbs1039Fabpostosostegno.setNumPosSsgSec(fabbisognoPostiSostegno.getNumPostiSosTOT());
    					tbs1039Fabpostosostegno.setNumPosSsgTer(fabbisognoPostiSostegno.getNumPostiSosTOT());
    				}else if (numVer == 1){
    					tbs1039Fabpostosostegno.setNumPosSsgPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosSsgSec(fabbisognoPostiSostegno.getNumPostiSosTOT());
    					tbs1039Fabpostosostegno.setNumPosSsgTer(fabbisognoPostiSostegno.getNumPostiSosTOT());
    				}else if (numVer == 2){
    					tbs1039Fabpostosostegno.setNumPosSsgPri(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosSsgSec(new BigDecimal(0));
    					tbs1039Fabpostosostegno.setNumPosSsgTer(fabbisognoPostiSostegno.getNumPostiSosTOT());
    				}
    			}
    			tbs1039Fabpostosostegno.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1039Fabpostosostegno.setDesMot(null);
    			tbs1039FabpostosostegnoRepository.save(tbs1039Fabpostosostegno);
    		}
    	}    	
    }

    public void copyDataSEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla vista VBS1153_PTFODPOSPOT
    	 * Se li trovo li carico sulla corrispondente tabella PTOF
    	 * TBS1040_FABPOSTOPOTENZIAMENTO
    	 **/
    	
    	logger.debug("copyDataSEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO start");
   		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
		int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
    	
    	LinkedList<FabbisognoPostiPotenziamento> fabbisognoPostiPotenziamentoEE = fabbisognoPostiPotenziamentoRepository.findEE(tbs1004Datisezionesottosez.getCodScuUte());
    	if (fabbisognoPostiPotenziamentoEE != null && !fabbisognoPostiPotenziamentoEE.isEmpty()){
    		logger.debug("trovate FabbisognoPostiPotenziamento per EE : " + fabbisognoPostiPotenziamentoEE.size());
    		for (FabbisognoPostiPotenziamento fabbisognoPostiPotenziamento: fabbisognoPostiPotenziamentoEE){
    			Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento = new Tbs1040Fabpostopotenziamento();
    			tbs1040Fabpostopotenziamento.setCodOrdScu(fabbisognoPostiPotenziamento.getCodScuOrd());

    			if (numVer == 0){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComSec(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				
    			}else if (numVer == 1){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComSec(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				
    			}else if (numVer == 2){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComSec(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos()); 
    				
    			}

    			tbs1040Fabpostopotenziamento.setDesMot(null);
    			tbs1040Fabpostopotenziamento.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1040FabpostopotenziamentoRepository.save(tbs1040Fabpostopotenziamento);
    			
    		}
    	}

    	LinkedList<FabbisognoPostiPotenziamento> fabbisognoPostiPotenziamentoMMSS = fabbisognoPostiPotenziamentoRepository.findMMSS(tbs1004Datisezionesottosez.getCodScuUte());
    	if (fabbisognoPostiPotenziamentoMMSS != null && !fabbisognoPostiPotenziamentoMMSS.isEmpty()){
    		logger.debug("trovate FabbisognoPostiPotenziamento per MM e SS : " + fabbisognoPostiPotenziamentoMMSS.size());
    		for (FabbisognoPostiPotenziamento fabbisognoPostiPotenziamento: fabbisognoPostiPotenziamentoMMSS){
    			Tbs1040Fabpostopotenziamento tbs1040Fabpostopotenziamento = new Tbs1040Fabpostopotenziamento();
    			tbs1040Fabpostopotenziamento.setCodOrdScu(fabbisognoPostiPotenziamento.getCodScuOrd());
    			
    			if (numVer == 0){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComSec(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    			}else if (numVer == 1){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComSec(fabbisognoPostiPotenziamento.getNumPostiPot());
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(fabbisognoPostiPotenziamento.getNumPostiPotSos());
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos());

    			}else if (numVer == 2){
    				tbs1040Fabpostopotenziamento.setNumPosComPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComSec(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosComTer(fabbisognoPostiPotenziamento.getNumPostiPot());
    				
    				tbs1040Fabpostopotenziamento.setNumPosSosPri(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosSec(new BigDecimal(0));
    				tbs1040Fabpostopotenziamento.setNumPosSosTer(fabbisognoPostiPotenziamento.getNumPostiPotSos());    			}
    			
    			tbs1040Fabpostopotenziamento.setDesMot(null);
    			tbs1040Fabpostopotenziamento.setCodClc(fabbisognoPostiPotenziamento.getCodClc());
    			tbs1040Fabpostopotenziamento.setDesClc(fabbisognoPostiPotenziamento.getDesClc());
    			tbs1040Fabpostopotenziamento.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1040FabpostopotenziamentoRepository.save(tbs1040Fabpostopotenziamento);
    		}
    	}
    	
    }

    public void copyDataSEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI(Tbs1004Datisezionesottosez tbs1004Datisezionesottosez){

    	/**
    	 * Verifico se ci sono records sulla vista VBS1152_PTFODPOSATA
    	 * Se li trovo li carico sulla corrispondente tabella PTOF
    	 * TBS1041_FABFIGURAPROFESSIONALE
    	 **/
    	
    	logger.debug("copyDataSEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI start");
    	
    	LinkedList<FabbisognoPostiAta> fabbisognoPostiAtas = fabbisognoPostiAtaRepository.find(tbs1004Datisezionesottosez.getCodScuUte());
    	if (fabbisognoPostiAtas != null && !fabbisognoPostiAtas.isEmpty()){
    		logger.debug("trovati FabbisognoPostiAta : " + fabbisognoPostiAtas.size());
    		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository.findOne(tbs1004Datisezionesottosez.getPrgGesCatDoc());
			int numVer = tbs1001Gestionecatalogodoc.getNumVerDoc().intValue();
			
    		for (FabbisognoPostiAta fabbisognoPostiAta: fabbisognoPostiAtas){
    			Tbs1041Fabfiguraprofessionale tbs1041Fabfiguraprofessionale = new Tbs1041Fabfiguraprofessionale();
    			
    			if (numVer == 0){
    				tbs1041Fabfiguraprofessionale.setNumPosPri(fabbisognoPostiAta.getNumPosti());
    				tbs1041Fabfiguraprofessionale.setNumPosSec(fabbisognoPostiAta.getNumPosti());
    				tbs1041Fabfiguraprofessionale.setNumPosTer(fabbisognoPostiAta.getNumPosti());
    			}else if (numVer == 1){
    				tbs1041Fabfiguraprofessionale.setNumPosPri(new BigDecimal(0));
    				tbs1041Fabfiguraprofessionale.setNumPosSec(fabbisognoPostiAta.getNumPosti());
    				tbs1041Fabfiguraprofessionale.setNumPosTer(fabbisognoPostiAta.getNumPosti());
    			}else if (numVer == 2){
    				tbs1041Fabfiguraprofessionale.setNumPosPri(new BigDecimal(0));
    				tbs1041Fabfiguraprofessionale.setNumPosSec(new BigDecimal(0));
    				tbs1041Fabfiguraprofessionale.setNumPosTer(fabbisognoPostiAta.getNumPosti());
    			}
    			
    			tbs1041Fabfiguraprofessionale.setDesMot(null);
    			tbs1041Fabfiguraprofessionale.setDesFigPrf(fabbisognoPostiAta.getDesProAta());
    			tbs1041Fabfiguraprofessionale.setTbs1004Datisezionesottosez(tbs1004Datisezionesottosez);
    			tbs1041FabfiguraprofessionaleRepository.save(tbs1041Fabfiguraprofessionale);
    		}
    	}    	
    }


	private Integer toInteger(BigDecimal in){
		if (in != null){
			return Integer.valueOf(in.intValue());
		}else{
			return Integer.valueOf(0);
		}
	}
	
	public static Tbs1002GestioneptofPK getTbs1002GestioneptofPKFromKeyDocumento(String key){
		Tbs1002GestioneptofPK out = null;
		try{
			out = new Tbs1002GestioneptofPK();
			out.setCodScuUte(key.substring(0,10));
			out.setPerAnnSco(Long.valueOf(key.substring(10,16)));
			out.setPrgGesCatDoc(Long.valueOf(key.substring(16)));
		}catch (Exception ex){
			logger.error("ERRORE in getTbs1002GestioneptofPKFromKeyDocumento ", ex);
			return null;
		}
		return out;
	}

	public static String getKeyDocumentoFromTbs1002GestioneptofPK(Tbs1002GestioneptofPK pk){
		String out = "";
		try{
			out = out + pk.getCodScuUte() + pk.getPerAnnSco().toString() + pk.getPrgGesCatDoc().toString();
		}catch (Exception ex){
			logger.error("ERRORE in getKeyDocumentoFromTbs1002GestioneptofPK ", ex);
			return null;
		}
		return out;
	}


}
