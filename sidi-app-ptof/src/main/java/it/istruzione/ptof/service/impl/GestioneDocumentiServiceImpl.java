package it.istruzione.ptof.service.impl;

import it.istruzione.ptof.beans.DatiDecretoDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_RICHIESTA_CREAZIONE_PDF;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAttivabileDTO;
import it.istruzione.ptof.beans.documenti.DocumentoPubblicazioneDTO;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.component.report.PtofReport;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.model.entity.Tbs1001Gestionecatalogodoc;
import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1002GestioneptofPK;
import it.istruzione.ptof.model.entity.Tbs1003Catalogosezione;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.model.entity.Tbs1006Tipostato;
import it.istruzione.ptof.model.entity.Tbs1043Fabdecretointerminper;
import it.istruzione.ptof.model.entity.Tbs1043FabdecretointerminperPK;
import it.istruzione.ptof.model.entity.Tbs1044Richiestapdf;
import it.istruzione.ptof.model.entity.business.Documento;
import it.istruzione.ptof.model.repository.Tbs1001GestionecatalogodocRepository;
import it.istruzione.ptof.model.repository.Tbs1002GestioneptofRepository;
import it.istruzione.ptof.model.repository.Tbs1003CatalogosezioneRepository;
import it.istruzione.ptof.model.repository.Tbs1004DatisezionesottosezRepository;
import it.istruzione.ptof.model.repository.Tbs1029DotazionemultimedialeRepository;
import it.istruzione.ptof.model.repository.Tbs1034ArticoclasseinfanziaRepository;
import it.istruzione.ptof.model.repository.Tbs1035ArticoclasseprimariaRepository;
import it.istruzione.ptof.model.repository.Tbs1036ArticolaclasseigradoRepository;
import it.istruzione.ptof.model.repository.Tbs1038ArticolaclasseiigradoRepository;
import it.istruzione.ptof.model.repository.Tbs1043FabdecretointerminperRepository;
import it.istruzione.ptof.model.repository.Tbs1044RichiestapdfRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiAARepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiEERepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIIRepository;
import it.istruzione.ptof.model.repository.business.ArticolazioneClassiMMIRepository;
import it.istruzione.ptof.model.repository.business.DocumentoRepository;
import it.istruzione.ptof.model.repository.business.DotazioniMultimedialiRepository;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.HomeService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sronca RF002– Gestione Documenti
 */

@Service
public class GestioneDocumentiServiceImpl extends BaseServiceImpl implements
		GestioneDocumentiService {

	private static Logger logger = Logger
			.getLogger(GestioneDocumentiServiceImpl.class);

	@Autowired
	private UtilPtofServiceImpl utilPtofServiceImpl;

	@Autowired
	private Tbs1001GestionecatalogodocRepository tbs1001GestionecatalogodocRepository;

	@Autowired
	private Tbs1002GestioneptofRepository tbs1002GestioneptofRepository;

	@Autowired
	private Tbs1003CatalogosezioneRepository tbs1003CatalogosezioneRepository;

	@Autowired
	private Tbs1004DatisezionesottosezRepository tbs1004DatisezionesottosezRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private Tbs1029DotazionemultimedialeRepository tbs1029DotazionemultimedialeRepository;

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
	private Tbs1044RichiestapdfRepository tbs1044RichiestapdfRepository;

	@Autowired
	private HomeService homeService;

	@Autowired
	private PtofReport ptofReport;
	
	@Autowired
	private Tbs1043FabdecretointerminperRepository tbs1043FabdecretointerminperRepository;
	
	
	/**
	 * Il metodo estrae la lista dei documenti aperti per la compilazione, nella
	 * normalità è sempre un solo documento Se la scuola non ha iniziato la
	 * compilazione, vengono inseriti i records sul DB di inizializzazione
	 * PTOF/SCUOLA sulle tabelle TBS1002_GESTIONEPTOF -
	 * TBS1004_DATISEZIONESOTTOSEZ in stato bozza
	 */
	public LinkedList<DocumentoAnnoIncorsoDTO> loadDocumentiAnnoIncorso(
			GestioneDocumentiDTO gestioneDocumentiServiceDTO) {

		logger.debug("in loadDocumentiAnnoIncorso ... ");
		logger.debug(ReflectionToStringBuilder.toString(
				gestioneDocumentiServiceDTO.getIstitutoScolastico(),
				ToStringStyle.MULTI_LINE_STYLE));

		Date today = CommonsUtility.getTodayDate();
		logger.debug("today is " + today.toString());

		LinkedList<Tbs1001Gestionecatalogodoc> tbs1001Gestionecatalogodocs = tbs1001GestionecatalogodocRepository
				.findByDatIniValBeforeAndDatFinValAfter(today, today);
		if (!tbs1001Gestionecatalogodocs.isEmpty()) {
			Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001Gestionecatalogodocs
					.getFirst();
			logger.debug("trovato elemento catalogo aperto ... ");
			logger.debug(ReflectionToStringBuilder.toString(
					tbs1001Gestionecatalogodoc, ToStringStyle.MULTI_LINE_STYLE));
			logger.debug("verifico se per la scuola è presente il record sulla Tbs1002Gestionepto per questo elemento catalogo aperto ... ");
			Tbs1002GestioneptofPK pk = new Tbs1002GestioneptofPK();
			pk.setCodScuUte(gestioneDocumentiServiceDTO.getIstitutoScolastico()
					.getCodiceMecIstPrin());
			pk.setPerAnnSco(Long.valueOf(CommonsUtility
					.getAnnoScolasticoCorrente()));
			pk.setPrgGesCatDoc(tbs1001Gestionecatalogodoc.getPrgGesCatDoc());
			if (tbs1002GestioneptofRepository.findOne(pk) == null) {
				logger.debug("record non presente, lo inserisco ... ");
				Tbs1002Gestioneptof tbs1002Gestioneptof = new Tbs1002Gestioneptof();
				tbs1002Gestioneptof.setId(pk);
				Tbs1006Tipostato tbs1006Tipostato = new Tbs1006Tipostato();
				tbs1006Tipostato.setCodSta(TIPO_STATO_DOC.IN_COMPILAZIONE
						.code());
				tbs1002Gestioneptof.setTbs1006Tipostato(tbs1006Tipostato);
				tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);

				logger.debug("verifico se, per ogni sezione, per la scuola è presente il record sulla TBS1004_DATISEZIONESOTTOSEZ per questo elemento catalogo aperto ... ");
				List<Tbs1003Catalogosezione> tbs1003Catalogoseziones = tbs1003CatalogosezioneRepository
						.findAll();

				for (Tbs1003Catalogosezione tbs1003Catalogosezione : tbs1003Catalogoseziones) {

					logger.debug("inserisco sezione IN BOZZA : "
							+ tbs1003Catalogosezione.getCodSezSotSez());
					Tbs1004Datisezionesottosez tbs1004Datisezionesottosez = new Tbs1004Datisezionesottosez();
					tbs1004Datisezionesottosez
							.setCodScuUte(gestioneDocumentiServiceDTO
									.getIstitutoScolastico()
									.getCodiceMecIstPrin());
					tbs1004Datisezionesottosez
							.setPerAnnSco(Long.valueOf(CommonsUtility
									.getAnnoScolasticoCorrente()));
					tbs1004Datisezionesottosez
							.setPrgGesCatDoc(tbs1001Gestionecatalogodoc
									.getPrgGesCatDoc());
					tbs1004Datisezionesottosez
							.setTbs1003Catalogosezione(tbs1003Catalogosezione);
					tbs1004Datisezionesottosez
							.setCodSta(TIPO_STATO_SEZIONE.BOZZA.code());
					tbs1004DatisezionesottosezRepository
							.save(tbs1004Datisezionesottosez);

					if (tbs1003Catalogosezione.getPrgSezSotSezPle() != null
							&& tbs1003Catalogosezione.getPrgSezSotSezPle()
									.equals(1L)) {
						logger.debug("inserisco sezione IN BOZZA per ogni plesso : "
								+ tbs1003Catalogosezione.getCodSezSotSez());
						List<PlessiDTO> plessi = gestioneDocumentiServiceDTO
								.getIstitutoScolastico().getPlessi();
						logger.debug("Numero plessi : " + plessi.size());
						if (!plessi.isEmpty()) {
							for (PlessiDTO plessiDTO : plessi) {
								logger.debug("inserisco sezione IN BOZZA per plesso : "
										+ plessiDTO.getCodiceMecUtente());
								tbs1004Datisezionesottosez = new Tbs1004Datisezionesottosez();
								tbs1004Datisezionesottosez
										.setCodScuUte(gestioneDocumentiServiceDTO
												.getIstitutoScolastico()
												.getCodiceMecIstPrin());
								tbs1004Datisezionesottosez.setPerAnnSco(Long
										.valueOf(CommonsUtility
												.getAnnoScolasticoCorrente()));
								tbs1004Datisezionesottosez
										.setPrgGesCatDoc(tbs1001Gestionecatalogodoc
												.getPrgGesCatDoc());
								tbs1004Datisezionesottosez
										.setTbs1003Catalogosezione(tbs1003Catalogosezione);
								tbs1004Datisezionesottosez
										.setCodSta(TIPO_STATO_SEZIONE.BOZZA
												.code());
								tbs1004Datisezionesottosez
										.setCodScuUtePle(plessiDTO
												.getCodiceMecUtente());
								tbs1004DatisezionesottosezRepository
										.save(tbs1004Datisezionesottosez);
							}
						}

					}

					/**
					 * Se la sezione è la 9. Strumenti e attrezzature e
					 * tecnologia, PRG_SEZ_SOT_SEZ = 32, inizializzo sulla
					 * tabella dati PTOF i record delle dotazioni multimediali
					 * dalla TBS1033_DOTAZIONMUSICSCUOLA
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA
											.code()))) {
						utilPtofServiceImpl
								.copyDataS_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE(tbs1004Datisezionesottosez);
					}

					/**
					 * Se la sezione è la SEZIONE_17_ORGANIZZAZIONE_CLASSI,
					 * inizializzo sulle tabelle dati PTOF i record relativi
					 * all'articolazione delle classi
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI
											.code()))) {
						utilPtofServiceImpl
								.copyDataSEZIONE_17_ORGANIZZAZIONE_CLASSI(tbs1004Datisezionesottosez);
					}

					/**
					 * Se la sezione è la SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE,
					 * inizializzo sulle tabelle dati PTOF i record relativi ai
					 * fabbisogni posti e cattedre
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE
											.code()))) {
						utilPtofServiceImpl
								.copyDataSEZIONE_35_FABBISOGNO_POSTI_CATTEDRE(tbs1004Datisezionesottosez);
					}

					/**
					 * Se la sezione è la SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO,
					 * inizializzo sulle tabelle dati PTOF i record relativi ai
					 * fabbisogni posti di sostegno
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO
											.code()))) {
						utilPtofServiceImpl
								.copyDataSEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO(tbs1004Datisezionesottosez);
					}

					/**
					 * Se la sezione è la
					 * SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO, inizializzo
					 * sulle tabelle dati PTOF i record relativi ai fabbisogni
					 * posti di potenziamento
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO
											.code()))) {
						utilPtofServiceImpl
								.copyDataSEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO(tbs1004Datisezionesottosez);
					}

					/**
					 * Se la sezione è la
					 * SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI,
					 * inizializzo sulle tabelle dati PTOF i record relativi ai
					 * fabbisogni posti ATA
					 **/
					if (tbs1004Datisezionesottosez
							.getTbs1003Catalogosezione()
							.getPrgSezSotSez()
							.equals(Long
									.valueOf(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI
											.code()))) {
						utilPtofServiceImpl
								.copyDataSEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI(tbs1004Datisezionesottosez);
					}

				}

			} else {
				logger.debug("record presente ...");
			}

		}

		logger.debug("eseguo la query per presentare le informazioni ... ");
		LinkedList<Documento> documenti = documentoRepository
				.findDocumentiIncorso(gestioneDocumentiServiceDTO
						.getIstitutoScolastico().getCodiceMecIstPrin(),
						CommonsUtility.getAnnoScolasticoCorrente());

		LinkedList<DocumentoAnnoIncorsoDTO> out = new LinkedList<>();
		for (Documento documento : documenti) {
			DocumentoAnnoIncorsoDTO dto = DtoFactory
					.getDocumentoAnnoIncorsoDTO(documento);
			out.add(dto);
		}

		return out;
	}

	/**
	 * Il metodo estrae la lista dei documenti chiusi per la compilazione
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<DocumentoArchivioDTO> loadDocumentiArchivio(
			GestioneDocumentiDTO gestioneDocumentiServiceDTO) {
		logger.debug("in loadDocumentiArchivio ... ");
		logger.debug(ReflectionToStringBuilder.toString(
				gestioneDocumentiServiceDTO.getIstitutoScolastico(),
				ToStringStyle.MULTI_LINE_STYLE));

		LinkedList<Documento> documenti = documentoRepository
				.findDocumentiArchivio(gestioneDocumentiServiceDTO
						.getIstitutoScolastico().getCodiceMecIstPrin(),
						CommonsUtility.getAnnoScolasticoCorrente());

		LinkedList<DocumentoArchivioDTO> out = new LinkedList<>();
		for (Documento documento : documenti) {
			DocumentoArchivioDTO dto = DtoFactory
					.getDocumentoArchivioDTO(documento);
			out.add(dto);
		}

		return out;
	}

	/**
	 * @param keyPtof
	 *            : chiave del documento selezionato tra i doc trovati con il
	 *            metodo loadDocumentiArchivio
	 * @param gestioneDocumentiServiceDTO
	 *            usato per elevare il livello di sicurezza verifica che il
	 *            documento appartenga alla scuola passata in input e in caso
	 *            positivo ritorna il doc altrimenti null
	 * @return use: scaricare il ptof nell'archivio RF002 – Gestisci Documento
	 * 
	 */
	// @Transactional(propagation = Propagation.SUPPORTS)
	// public FileDTO loadPtofFile(String keyPtof, GestioneDocumentiDTO
	// gestioneDocumentiServiceDTO){
	//
	// FileDTO dto = null;
	//
	// Tbs1002GestioneptofPK pk =
	// UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(keyPtof);
	// if (pk == null){
	// return null;
	// }
	//
	// if
	// (pk.getCodScuUte().equals(gestioneDocumentiServiceDTO.getIstitutoScolastico().getCodiceMecIstPrin())){
	// Tbs1002Gestioneptof tbs1002Gestioneptof =
	// tbs1002GestioneptofRepository.findOne(pk);
	// if (tbs1002Gestioneptof != null){
	// dto = new FileDTO();
	// dto.setFile( new ByteArrayInputStream(
	// tbs1002Gestioneptof.getOggPdfPtoPar() ));
	// dto.setFileName(tbs1002Gestioneptof.getTbs1001Gestionecatalogodoc().getDesDoc());
	// return dto;
	// }
	//
	// }
	//
	// return dto;
	// }

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabili() {
		logger.debug("in loadDocumentiAttivabili ... ");

		List<Tbs1001Gestionecatalogodoc> documenti = tbs1001GestionecatalogodocRepository
				.findAll(new Sort(new Order(Sort.Direction.DESC, "perTriRif"),
						new Order(Sort.Direction.DESC, "numVerDoc")));

		LinkedList<DocumentoAttivabileDTO> out = new LinkedList<>();
		for (Tbs1001Gestionecatalogodoc documento : documenti) {
			DocumentoAttivabileDTO dto = DtoFactory
					.getDocumentoAttivabiliDTO(documento);
			out.add(dto);
		}

		return out;

	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabiliInCorso() {
		logger.debug("in loadDocumentiAttivabiliInCorso ... ");

		Date today = CommonsUtility.getTodayDate();

		LinkedList<Tbs1001Gestionecatalogodoc> tbs1001Gestionecatalogodocs = tbs1001GestionecatalogodocRepository.findByDatIniValBeforeAndDatFinValAfter(today, today);

		LinkedList<DocumentoAttivabileDTO> out = new LinkedList<>();
		for (Tbs1001Gestionecatalogodoc documento : tbs1001Gestionecatalogodocs) {
			DocumentoAttivabileDTO dto = DtoFactory
					.getDocumentoAttivabiliDTO(documento);
			out.add(dto);
		}

		return out;

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public GestioneCatalogoDTO getTbs1001GestionecatalogodocById(
			Long prgGesCatDoc) {
		GestioneCatalogoDTO gestioneCatalogoDTO = null;
		Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository
				.findOne(prgGesCatDoc);

		if (tbs1001Gestionecatalogodoc != null) {
			gestioneCatalogoDTO = new GestioneCatalogoDTO();
			gestioneCatalogoDTO
					.setProgresivoGestioneCatalogoDocumento(tbs1001Gestionecatalogodoc
							.getPrgGesCatDoc());
			gestioneCatalogoDTO
					.setDescrizioneDocumento(tbs1001Gestionecatalogodoc
							.getDesDoc());
			gestioneCatalogoDTO
					.setPeriodoTriennioRiferimento(tbs1001Gestionecatalogodoc
							.getPerTriRif());
			gestioneCatalogoDTO
					.setNumeroVersioneDocumento(tbs1001Gestionecatalogodoc
							.getNumVerDoc());
			gestioneCatalogoDTO.setDataInzioValidita(tbs1001Gestionecatalogodoc
					.getDatIniVal());
			gestioneCatalogoDTO.setDataFineValidita(tbs1001Gestionecatalogodoc
					.getDatFinVal());
		}

		return gestioneCatalogoDTO;
	}

	/**
	 * Il metodo estrae la lista dei documenti per i quali è stata inserita una
	 * richiesta di creazione PDF. Legge dalla tabella delle richieste con stato
	 * RICHIESTA_DA_EVADERE
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<GestionePtofDTO> getListaPtofGeneraPDF() {

		logger.debug("in getListaPtofGeneraPDF ...");

		LinkedList<GestionePtofDTO> out = new LinkedList<>();
		LinkedList<Tbs1044Richiestapdf> tbs1044Richiestapdfs = tbs1044RichiestapdfRepository
				.findByFlgStoPdfOrderByDatInsRicPdfAsc(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE
						.code());

		if (tbs1044Richiestapdfs != null && !tbs1044Richiestapdfs.isEmpty()) {
			for (Tbs1044Richiestapdf tbs1044Richiestapdf : tbs1044Richiestapdfs) {
				logger.debug(ReflectionToStringBuilder.toString(
						tbs1044Richiestapdf, ToStringStyle.MULTI_LINE_STYLE));
				GestionePtofDTO gestionePtofDTO = new GestionePtofDTO();
				DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO = new DocumentoAnnoIncorsoDTO();
				String key = tbs1044Richiestapdf.getCodScuUte()
						+ tbs1044Richiestapdf.getPerAnnSco().toString()
						+ tbs1044Richiestapdf.getPrgGesCatDoc().toString();
				documentoAnnoIncorsoDTO.setKey(key);
				gestionePtofDTO
						.setDocumentoAnnoIncorsoDTO(documentoAnnoIncorsoDTO);
				IstitutoScolasticoDTO istitutoScolastico = homeService
						.loadIstitutoScolasticoDTO(tbs1044Richiestapdf
								.getCodScuUte());
				gestionePtofDTO.setIstitutoScolastico(istitutoScolastico);
				out.add(gestionePtofDTO);
			}
		}
		return out;
	}

	/**
	 * Il metodo salva il documento PDF in base dati nel campo corrispondente
	 * allo stato per il quale è stato creato. Oltre al documento vengono
	 * impostati : user dell'utente che ha effettuato la richiesta, data di
	 * pubblicazione documento, stato documento L'esito della storicizzazione
	 * viene memorizzato nella tabella delle rischieste
	 */
	@Override
	public void setPtofFile(GestionePtofDTO gestionePtofDTO,
			ByteArrayOutputStream ptof) {

		logger.debug("in setPtofFile ...");
		logger.debug(ReflectionToStringBuilder.toString(gestionePtofDTO.getDocumentoAnnoIncorsoDTO(),ToStringStyle.MULTI_LINE_STYLE));
		logger.debug(ReflectionToStringBuilder.toString(gestionePtofDTO.getIstitutoScolastico(),ToStringStyle.MULTI_LINE_STYLE));

		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl
				.getTbs1002GestioneptofPKFromKeyDocumento(gestionePtofDTO
						.getDocumentoAnnoIncorsoDTO().getKey());

		if (pk.getCodScuUte().equals(
				gestionePtofDTO.getIstitutoScolastico().getCodiceMecIstPrin())) {
			/** controllo di congruenza dei parametri passati in input **/

			Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository
					.findOne(pk);
			if (tbs1002Gestioneptof != null) {

				TIPO_STATO_DOC statoDocumentoAttuale = TIPO_STATO_DOC
						.getInstanceFromCode(tbs1002Gestioneptof
								.getTbs1006Tipostato().getCodSta());
				TIPO_STATO_DOC statoDocumentoDaImpostare = null;
				switch (statoDocumentoAttuale) {
				case CONVALIDATO:
					statoDocumentoDaImpostare = TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA;
					break;
				case DOCUMENTO_IN_ANTEPRIMA:
					statoDocumentoDaImpostare = TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE;
					break;
				case FABBISOGNO_VALIDATO:
					statoDocumentoDaImpostare = TIPO_STATO_DOC.PUBBLICATO_COMPLETO;
					break;
				default:
					throw new RuntimeException(
							"Stato documento non compatibile per la storicizzazione del PDF.");
				}

				logger.debug("statoDocumentoDaImpostare : "
						+ statoDocumentoDaImpostare);

				/**
				 * verifico se è presente una richiesta di creazione file da
				 * gestire per la key documento di input, se la trovo leggo
				 * l'utente che l'ha inserita e procedo con l'inserimento in
				 * caso contrario non eseguo azioni
				 **/

				Tbs1044Richiestapdf tbs1044Richiestapdf = tbs1044RichiestapdfRepository
						.findByDocAndStato(
								pk.getPrgGesCatDoc(),
								pk.getPerAnnSco(),
								pk.getCodScuUte(),
								TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE
										.code());

				if (tbs1044Richiestapdf != null) {
					
					logger.debug("trovata richiesta : " + tbs1044Richiestapdf.getPrgRicPdf());

					switch (statoDocumentoDaImpostare) {
					case DOCUMENTO_IN_ANTEPRIMA:
						tbs1002Gestioneptof.setOggPdfVisCnv(ptof.toByteArray());
						tbs1002Gestioneptof
								.setCodUtePdfVisCnv(tbs1044Richiestapdf
										.getCodUteUltMov());
						tbs1002Gestioneptof.setDatPdfVisCnv(new Date());
						Tbs1006Tipostato statoAnteprima = new Tbs1006Tipostato();
						statoAnteprima
								.setCodSta(TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA
										.code());
						tbs1002Gestioneptof.setTbs1006Tipostato(statoAnteprima);
						break;
					case PUBBLICATO_PARZIALMENTE:
						tbs1002Gestioneptof.setOggPdfPtoPar(ptof.toByteArray());
						tbs1002Gestioneptof.setCodUtePubPdfPtoPar(tbs1044Richiestapdf.getCodUteUltMov());
						tbs1002Gestioneptof.setDatPubPdfPtoPar(new Date());
						Tbs1006Tipostato statoPubblicatoParziale = new Tbs1006Tipostato();
						statoPubblicatoParziale.setCodSta(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.code());
						tbs1002Gestioneptof.setTbs1006Tipostato(statoPubblicatoParziale);
						break;
					case PUBBLICATO_COMPLETO:
						tbs1002Gestioneptof.setOggPdfPtoCom(ptof.toByteArray());
						tbs1002Gestioneptof.setCodUtePubPdfPtoCom(tbs1044Richiestapdf.getCodUteUltMov());
						tbs1002Gestioneptof.setDatPubPdfPtoCom(new Date());
						Tbs1006Tipostato statoPubblicatoCompleto = new Tbs1006Tipostato();
						statoPubblicatoCompleto.setCodSta(TIPO_STATO_DOC.PUBBLICATO_COMPLETO.code());
						tbs1002Gestioneptof.setTbs1006Tipostato(statoPubblicatoCompleto);
						break;
					default:
						break;
					}

					tbs1002GestioneptofRepository.save(tbs1002Gestioneptof);

					tbs1044Richiestapdf.setDatEvaRicPdf(new Date());
					tbs1044Richiestapdf.setFlgStoPdf(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_EVASA_CON_SUCCESSO.code());
					tbs1044RichiestapdfRepository.save(tbs1044Richiestapdf);

				}

			}

		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<DocumentoPubblicazioneDTO> loadDocumentiPubblicazione(
			IstitutoScolasticoDTO istitutoScolasticoDTO) {

		logger.debug("in loadDocumentiPubblicazione ... ");
		logger.debug(ReflectionToStringBuilder.toString(istitutoScolasticoDTO,
				ToStringStyle.MULTI_LINE_STYLE));

		LinkedList<Documento> documenti = documentoRepository
				.findDocumentiPubblicazione(
						istitutoScolasticoDTO.getCodiceMecIstPrin(),
						CommonsUtility.getAnnoScolasticoCorrente());

		LinkedList<DocumentoPubblicazioneDTO> out = new LinkedList<>();
		for (Documento documento : documenti) {
			DocumentoPubblicazioneDTO dto = DtoFactory
					.getDocumentoPubblicazioneDTO(documento);
			Tbs1044Richiestapdf tbs1044Richiestapdf = tbs1044RichiestapdfRepository
					.findByDocAndStato(
							documento.getPrgGesCatDoc(),
							documento.getPerAnnSco(),
							documento.getCodScuUte(),
							TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE
									.code());
			if (tbs1044Richiestapdf != null) {
				dto.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE);
			} else {
				dto.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.NESSUNA_RICHIESTA);
			}

			out.add(dto);
		}

		return out;

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public FileDTO loadFilePtofPubblicato(String keyPtof,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
		logger.debug("in loadFilePtofPubblicato ... " + keyPtof);
		logger.debug(ReflectionToStringBuilder.toString(istitutoScolasticoDTO, ToStringStyle.MULTI_LINE_STYLE));

		FileDTO dto = null;

		Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(keyPtof);
		if (pk == null) {
			return null;
		}

		if (pk.getCodScuUte().equals(
				istitutoScolasticoDTO.getCodiceMecIstPrin())) {
			/** controllo di congruenza dei parametri passati in input **/
			Tbs1002Gestioneptof tbs1002Gestioneptof = tbs1002GestioneptofRepository
					.findOne(pk);
			if (tbs1002Gestioneptof != null) {
				TIPO_STATO_DOC statoDocumento = TIPO_STATO_DOC.getInstanceFromCode(tbs1002Gestioneptof.getTbs1006Tipostato().getCodSta());
				logger.debug("statoDocumento : " + statoDocumento);
				dto = new FileDTO();
				switch (statoDocumento) {
				case DOCUMENTO_IN_ANTEPRIMA:
					if (tbs1002Gestioneptof.getOggPdfVisCnv() != null) {
						dto.setFile(new ByteArrayInputStream(
								tbs1002Gestioneptof.getOggPdfVisCnv()));
					} else {
						throw new RuntimeException("Tipo file non presente");
					}
					break;
				case PUBBLICATO_PARZIALMENTE:
				case FABBISOGNO_VALIDATO:
					if (tbs1002Gestioneptof.getOggPdfPtoPar() != null) {
						dto.setFile(new ByteArrayInputStream(
								tbs1002Gestioneptof.getOggPdfPtoPar()));
					} else {
						throw new RuntimeException("Tipo file non presente");
					}
					break;
				case PUBBLICATO_COMPLETO:
					if (tbs1002Gestioneptof.getOggPdfPtoCom() != null) {
						dto.setFile(new ByteArrayInputStream(
								tbs1002Gestioneptof.getOggPdfPtoCom()));
					} else {
						throw new RuntimeException("Tipo file non presente");
					}
					break;
				default:
					throw new RuntimeException("Tipo file non gestito");
				}
				dto.setFileName("PTOF_"
						+ tbs1002Gestioneptof.getId().getCodScuUte()
						+ "_" + statoDocumento.name() + ".PDF");
				return dto;
			}
		}

		return dto;
	}

	@Override
	public ResponseDTO<Boolean> richiestaPubblicazionePtof(String keyPtof, IstitutoScolasticoDTO istitutoScolasticoDTO) {

		/** STEP 1 : inserimento richiesta **/
		/** STEP 2 : generazione del PDF **/
		/** STEP 3 : setPtofFile **/

		logger.debug("in richiestaPubblicazionePtof ... " + keyPtof);
		logger.debug(ReflectionToStringBuilder.toString(istitutoScolasticoDTO, ToStringStyle.MULTI_LINE_STYLE));

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try {
			
			Tbs1002GestioneptofPK pk = UtilPtofServiceImpl.getTbs1002GestioneptofPKFromKeyDocumento(keyPtof);

			if (pk.getCodScuUte().equals(istitutoScolasticoDTO.getCodiceMecIstPrin())) {/** controllo di congruenza dei parametri passati in input **/


				if (tbs1044RichiestapdfRepository.findByDocAndStato(pk.getPrgGesCatDoc(), 
																	pk.getPerAnnSco(), 
																	pk.getCodScuUte(), 
																	TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE.code()
						) == null){ /** controllo che non esista una richiesta da evadere **/
					
					logger.debug("STEP 1 : inserimento richiesta");
					Tbs1044Richiestapdf tbs1044Richiestapdf = new Tbs1044Richiestapdf();
					tbs1044Richiestapdf.setCodScuUte(pk.getCodScuUte());
					tbs1044Richiestapdf.setDatInsRicPdf(new Date());
					tbs1044Richiestapdf.setFlgStoPdf(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE.code());
					tbs1044Richiestapdf.setPerAnnSco(pk.getPerAnnSco());
					tbs1044Richiestapdf.setPrgGesCatDoc(pk.getPrgGesCatDoc());
					tbs1044Richiestapdf = tbs1044RichiestapdfRepository.saveAndFlush(tbs1044Richiestapdf);
					logger.debug("richiesta inserita : " + tbs1044Richiestapdf.getPrgRicPdf());
					
					logger.debug("STEP 2 : generazione del PDF");
					 ByteArrayOutputStream baos = new ByteArrayOutputStream();
					 ptofReport.loadDocumentoFormatoPDF(baos, keyPtof, istitutoScolasticoDTO);
					 logger.debug("PDF generato");

					 logger.debug("STEP 3 : setPtofFile");
					 GestionePtofDTO gestionePtofDTO = new GestionePtofDTO();
					 DocumentoAnnoIncorsoDTO documentoAnnoIncorsoDTO = new DocumentoAnnoIncorsoDTO();
					 documentoAnnoIncorsoDTO.setKey(keyPtof);
					 gestionePtofDTO.setDocumentoAnnoIncorsoDTO(documentoAnnoIncorsoDTO);
					 gestionePtofDTO.setIstitutoScolastico(istitutoScolasticoDTO);
					 setPtofFile(gestionePtofDTO, baos);
					 logger.debug("PDF salvato");
					
					out.setResult(Boolean.TRUE);
				}else{
					logger.debug("Esiste già una richiesta da evadere per il documento selezionato");
					out.setResult(Boolean.FALSE);
					ValidationErrorDTO validationError = new ValidationErrorDTO();
					validationError.setKey("02");
					validationError.setMessage("Esiste già una richiesta da evadere per il documento selezionato");
					validationError.setTipoErrore(TIPO_ERROR.BLOCCANTE);
					out.setValidationError(validationError);
				}
			}else{
				logger.debug("La chiave del documento non corrisponde all'istituto del contesto selezionato");
				out.setResult(Boolean.FALSE);
				ValidationErrorDTO validationError = new ValidationErrorDTO();
				validationError.setKey("01");
				validationError.setMessage("La chiave del documento non corrisponde all'istituto del contesto selezionato");
				validationError.setTipoErrore(TIPO_ERROR.BLOCCANTE);
				out.setValidationError(validationError);
			}

		} catch (Exception ex) {
			logger.error("ERRORE richiestaPubblicazionePtof : " + ex.getMessage(),
					ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO
					.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
		return out;
	}

	@Override
	public ResponseDTO<Boolean> salvaCatalogoDocumento(
			GestioneCatalogoDTO gestioneCatalogoDTO) {

		logger.debug("in salvaCatalogoDocumento ... ");
		logger.debug(ReflectionToStringBuilder.toString(gestioneCatalogoDTO,
				ToStringStyle.MULTI_LINE_STYLE));

		ResponseDTO<Boolean> out = new ResponseDTO<>();
		try {
			Tbs1001Gestionecatalogodoc tbs1001Gestionecatalogodoc = null;
			if (gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento() != null) {
				tbs1001Gestionecatalogodoc = tbs1001GestionecatalogodocRepository
						.findOne(gestioneCatalogoDTO
								.getProgresivoGestioneCatalogoDocumento());
			} else {
				tbs1001Gestionecatalogodoc = new Tbs1001Gestionecatalogodoc();
			}
			tbs1001Gestionecatalogodoc.setDatIniVal(gestioneCatalogoDTO
					.getDataInzioValidita());
			tbs1001Gestionecatalogodoc.setDatFinVal(gestioneCatalogoDTO
					.getDataFineValidita());
			tbs1001Gestionecatalogodoc.setDesDoc(gestioneCatalogoDTO
					.getDescrizioneDocumento());
			tbs1001Gestionecatalogodoc.setNumVerDoc(gestioneCatalogoDTO
					.getNumeroVersioneDocumento());
			tbs1001Gestionecatalogodoc.setPerTriRif(gestioneCatalogoDTO
					.getPeriodoTriennioRiferimento());
			tbs1001GestionecatalogodocRepository
					.save(tbs1001Gestionecatalogodoc);

			out.setResult(Boolean.TRUE);

		} catch (Exception ex) {
			logger.error("ERRORE salvaCatalogoDocumento : " + ex.getMessage(),
					ex);
			out.setResult(Boolean.FALSE);
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			out.setValidationError(validationErrorDTO);
			validationErrorDTO.setKey("07");
			validationErrorDTO
					.setMessage("Attenzione, si è verificato un errore.");
			validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		}
		return out;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public GestioneCatalogoDTO generaNuovoCatalogo() {

		logger.debug("in generaNuovoCatalogo ... ");

		GestioneCatalogoDTO out = new GestioneCatalogoDTO();
		out.setPeriodoTriennioRiferimento(new BigDecimal(201518));
		out.setNumeroVersioneDocumento(new BigDecimal(1));

		List<Tbs1001Gestionecatalogodoc> documenti = tbs1001GestionecatalogodocRepository
				.findAll(new Sort(new Order(Sort.Direction.DESC, "perTriRif"),
						new Order(Sort.Direction.DESC, "numVerDoc")));
		if (documenti != null && !documenti.isEmpty()) {
			int lastTriennio = documenti.get(0).getPerTriRif().intValue();
			int lastVersione = documenti.get(0).getNumVerDoc().intValue();
			logger.debug("lastTriennio " + lastTriennio);
			logger.debug("lastVersione " + lastVersione);

			if (lastVersione < 2) {
				out.setPeriodoTriennioRiferimento(new BigDecimal(lastTriennio));
				out.setNumeroVersioneDocumento(new BigDecimal(lastVersione + 1));
			} else {
				int annoInizioLastTriennio = Integer.valueOf(
						String.valueOf(lastTriennio).substring(0, 4))
						.intValue();
				int annoInizioNuovoTriennio = annoInizioLastTriennio + 3;
				int annoFineNuovoTriennio = annoInizioNuovoTriennio + 3;
				String nuovoTriennio = String.valueOf(annoInizioNuovoTriennio)
						+ String.valueOf(annoFineNuovoTriennio).substring(2, 4);
				out.setPeriodoTriennioRiferimento(new BigDecimal(nuovoTriennio));
				out.setNumeroVersioneDocumento(new BigDecimal(0));
			}
		}

		logger.debug("new triennio "
				+ out.getPeriodoTriennioRiferimento().toString());
		logger.debug("new versione "
				+ out.getNumeroVersioneDocumento().toString());

		return out;

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<DatiDecretoDTO> getDatiDecreto(GestioneCatalogoDTO gestioneCatalogoDTO) {

		LinkedList<DatiDecretoDTO> out = new LinkedList<>();
		LinkedList<Tbs1043Fabdecretointerminper> tbs1043Fabdecretointerminpers = tbs1043FabdecretointerminperRepository.findByIdPrgGesCatDocOrderByIdCodRegAscIdCodRifDecAsc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
		for (Tbs1043Fabdecretointerminper tbs1043Fabdecretointerminper : tbs1043Fabdecretointerminpers){
			DatiDecretoDTO dto = new DatiDecretoDTO();
			dto.setProgressivoDecreto(tbs1043Fabdecretointerminper.getPrgDec());
			dto.setCodiceTabella(tbs1043Fabdecretointerminper.getId().getCodRifDec());
			dto.setCodiceRegione(tbs1043Fabdecretointerminper.getId().getCodReg());
			dto.setDescrizioneRegione(tbs1043Fabdecretointerminper.getDesReg());
			dto.setPostiComuni(tbs1043Fabdecretointerminper.getNumPosCom());
			dto.setPostiSostegno(tbs1043Fabdecretointerminper.getNumPosSos());
			dto.setPostiPotenziamentoSostegno(tbs1043Fabdecretointerminper.getNumPosPtzSos());
			dto.setPostiPotenziamentoPrimaria(tbs1043Fabdecretointerminper.getNumPosPtzPri());
			dto.setPostiPotenziamentoIGrado(tbs1043Fabdecretointerminper.getNumPosPtzIgr());
			dto.setPostiPotenziamentoIIGrado(tbs1043Fabdecretointerminper.getNumPosPtzSgr());
			dto.setTotalePostiComuni(tbs1043Fabdecretointerminper.getNumTotPosCom());
			dto.setTotalePostiPotenziamento(tbs1043Fabdecretointerminper.getNumTotPosPtz());
			dto.setTotalePostiSostegno(tbs1043Fabdecretointerminper.getNumTotPosSos());
			out.add(dto);
		}
		return out;
	}

	@Override
	public void importDatiDecreto(List<DatiDecretoDTO> items, GestioneCatalogoDTO gestioneCatalogoDTO) {
		
		logger.debug("in importDatiDecreto ... " + gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
		logger.debug("items.size() : " + items.size());
		
		for (DatiDecretoDTO dto : items){
			
			logger.debug(ReflectionToStringBuilder.toString(dto, ToStringStyle.MULTI_LINE_STYLE));
			
			Tbs1043Fabdecretointerminper tbs1043Fabdecretointerminper = new Tbs1043Fabdecretointerminper();
			Tbs1043FabdecretointerminperPK pk = new Tbs1043FabdecretointerminperPK();
			pk.setPrgGesCatDoc(gestioneCatalogoDTO.getProgresivoGestioneCatalogoDocumento());
			pk.setCodReg(dto.getCodiceRegione());
			pk.setCodRifDec(dto.getCodiceTabella());
			tbs1043Fabdecretointerminper.setId(pk);
			tbs1043Fabdecretointerminper.setDesReg(dto.getDescrizioneRegione());
			tbs1043Fabdecretointerminper.setPrgDec(dto.getProgressivoDecreto());
			tbs1043Fabdecretointerminper.setNumPosCom(dto.getPostiComuni());
			tbs1043Fabdecretointerminper.setNumPosSos(dto.getPostiSostegno());
			tbs1043Fabdecretointerminper.setNumPosPtzSos(dto.getPostiPotenziamentoSostegno());
			tbs1043Fabdecretointerminper.setNumPosPtzPri(dto.getPostiPotenziamentoPrimaria());
			tbs1043Fabdecretointerminper.setNumPosPtzIgr(dto.getPostiPotenziamentoIGrado());
			tbs1043Fabdecretointerminper.setNumPosPtzSgr(dto.getPostiPotenziamentoIIGrado());
			tbs1043Fabdecretointerminper.setNumTotPosCom(dto.getTotalePostiComuni());
			tbs1043Fabdecretointerminper.setNumTotPosSos(dto.getTotalePostiSostegno());
			tbs1043Fabdecretointerminper.setNumTotPosPtz(dto.getTotalePostiPotenziamento());
			tbs1043FabdecretointerminperRepository.save(tbs1043Fabdecretointerminper);
		}
	}
}
