package it.istruzione.cercalatuascuola.dettaglio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.AlunniDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.AnagraficaScuoleDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.CaratteristicaDiplomatoDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.DocumentiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.ServiziDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOCaratteristica;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumento;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOIndirizzoEsame;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLinkIscrizioniOnline;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOMenu;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VONews;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOOffertaFormativa;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPon;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTempiScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;

@Controller
public class InformazioniController {
	private Logger logger = Logger.getLogger(InformazioniController.class);
	private AnagraficaScuolaService anagraficaScuolaService;
	private AnagraficaScuoleDAO anagraficaScuoleDAO;
	private DocumentiDAO docDao;
	private AlunniDAO alunniDAO;
	private ServiziDAO serviziDAO;
	private GraficiDAO graficiDAO;
	private CaratteristicaDiplomatoDAO caratteristicaDiplomatoDAO;

	private String codiceTipoServizioTempoScuola;
	private String codiceServizioTempoScuola;

	@Autowired
	public InformazioniController(AnagraficaScuolaService anagraficaScuolaService,
			AnagraficaScuoleDAO anagraficaScuoleDAO, DocumentiDAO docDao, AlunniDAO alunniDAO, ServiziDAO serviziDAO,
			GraficiDAO graficiDAO, CaratteristicaDiplomatoDAO capacitaDiplomatoDAO) {

		this.anagraficaScuolaService = anagraficaScuolaService;
		this.anagraficaScuoleDAO = anagraficaScuoleDAO;
		this.docDao = docDao;
		this.alunniDAO = alunniDAO;
		this.serviziDAO = serviziDAO;
		this.graficiDAO = graficiDAO;
		this.caratteristicaDiplomatoDAO = capacitaDiplomatoDAO;
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}")
	public String getInformazioni(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model,
			HttpServletRequest request) {

		try {
			String callBackUrl = (String) request.getSession().getAttribute("callBackUrl");
			logger.debug("callBackUrl: " + callBackUrl);

			String linkRisultatiRicerca = request.getSession().getAttribute("linkRisultatiRicerca") != null
					? request.getSession().getAttribute("linkRisultatiRicerca").toString() : "/";
			model.addAttribute("linkRisultatiRicerca", linkRisultatiRicerca);

			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			logger.info("UrlDocOrientamento: " + scuola.getUrlDocOrientamento());

			List<VODocumento> listaDocumenti = docDao.getDocumentiScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(),
					Constants.DOCUMENTO_HOME);

			if (!listaDocumenti.isEmpty()) {
				model.addAttribute("fotoScuola", listaDocumenti.get(0));
			}

			if (scuola.getDesIndWeb() != null && !scuola.getDesIndWeb().toLowerCase().startsWith("http")) {

				scuola.setDesIndWeb("http://" + scuola.getDesIndWeb());
			}

			VOAnagraficaScuola scuolaPri = null;
			if (!scuola.getCodScuUt().equals(scuola.getCodScuUtPri())) {
				scuolaPri = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUtPri(),
						scuola.getDatAnnScoRil());
				if (scuolaPri != null) {
					scuola.setCodFis(scuolaPri.getCodFis());
					scuola.setDesIndEmaPce(scuolaPri.getDesIndEmaPce());
				}
			}

			List<VOClasse> indirizziStudio = alunniDAO.getDistribuzioneClassiIndirizzi(scuola.getCodForScuApp());
			logger.debug("indirizziStudio=" + indirizziStudio);
			if (indirizziStudio != null && indirizziStudio.size() > 0) {
				logger.debug("indirizziStudio.size()=" + indirizziStudio.size());
				model.addAttribute("indirizziStudio", indirizziStudio);
			}

			List<VOClasse> tempoScuola = anagraficaScuoleDAO.getTempoScuola(scuola.getCodScuUt(),
					scuola.getDatAnnScoRil(), codiceTipoServizioTempoScuola, codiceServizioTempoScuola);

			logger.debug("tempoScuola=" + tempoScuola);
			if (tempoScuola != null && tempoScuola.size() > 0) {
				logger.debug("tempoScuola->size=" + tempoScuola.size());
				model.addAttribute("tempoScuola", tempoScuola);
			}

			List<VOTempiScuola> tempiScuola = anagraficaScuoleDAO.getListaTempiScuola(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()));
			if (tempiScuola != null)
				model.addAttribute("tempiScuola", tempiScuola);

			if (scuola.isSecondariaSecGrado()) {
				List<VOOffertaFormativa> percorsi = anagraficaScuoleDAO.getListaPercorsi(scuola.getCodScuUt(),
						Integer.parseInt(scuola.getDatAnnScoRil()));
				if (percorsi != null)
					model.addAttribute("percorsi", percorsi);
			}

			if (scuola.getCodScuUt().substring(2, 4).equals("MM")) {
				List<VOOffertaFormativa> indirizziMM = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), "MM");
				if (indirizziMM != null)
					model.addAttribute("indirizziMM", indirizziMM);
			}

			int numAlunni = 0;
			int numClassi = 0;
			List<VOAnniCorsoAlunni> voAnniCorsoAlunni = alunniDAO.getAlunniPerAnnoCorso(scuola.getCodForScuApp());
			if (voAnniCorsoAlunni != null) {
				for (int anno = 0; anno < voAnniCorsoAlunni.size(); anno++) {
					VOAnniCorsoAlunni vo = voAnniCorsoAlunni.get(anno);
					numAlunni = numAlunni + Integer.valueOf(vo.getNumAlu()).intValue();
					numClassi = numClassi + Integer.valueOf(vo.getNumCla()).intValue();
				}
			}
			if (numAlunni > 0 && numClassi > 0) {
				model.addAttribute("numeroAlunni", String.valueOf(numAlunni));
				model.addAttribute("numeroClassi", String.valueOf(numClassi));

				float media = numAlunni / numClassi;
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");

				model.addAttribute("mediaAlunniClassi", df.format(media));
			}

			VODocumento ptof = docDao.getPTOFScuola(scuola.getCodScuUtPri(), scuola.getDatAnnScoRil(),
					Constants.DOCUMENTO_DIDATTICA);
			if (ptof != null) {
				model.addAttribute("ptof", ptof);
			}

			model.addAttribute("scuola", scuola);

			if (scuolaPri != null) {
				if (scuolaPri.getDesIndWeb() != null && !scuolaPri.getDesIndWeb().toLowerCase().startsWith("http")) {

					scuolaPri.setDesIndWeb("http://" + scuolaPri.getDesIndWeb());
				}
				model.addAttribute("scuolaPri", scuolaPri);
			} else {
				model.addAttribute("scuolaPri", scuola);
			}

			String dirigente = anagraficaScuoleDAO.getDirigenteScolastico(
					scuolaPri != null ? scuolaPri.getCodScuUt() : scuola.getCodScuUt(),
					scuolaPri != null ? scuolaPri.getDatAnnScoRil() : scuola.getDatAnnScoRil());

			if (dirigente != null)
				model.addAttribute("dirigente", dirigente.trim());
			else {
				model.addAttribute("dirigente", "");
			}
			model.addAttribute("numeroPlessi", "");
			model.addAttribute("numeroInfanzia", "");
			model.addAttribute("numeroPrimaria", "");
			model.addAttribute("numeroIGrado", "");
			model.addAttribute("numeroIIGrado", "");
			model.addAttribute("numeroIC", "");
			model.addAttribute("numeroCT", "");
			model.addAttribute("flgPlesso", "true");

			/*
			 * if ("1".equals(scuola.getFlgSedDir())) { List<VOAnagraficaScuola>
			 * plessi =
			 * anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
			 * scuola.getDatAnnScoRil()); logger.debug("LISTA PLESSI --> " +
			 * plessi.size()); if (plessi.size() > 0) {
			 * model.addAttribute("flgPlessi", "true"); } else {
			 * model.addAttribute("flgPlessi", "false"); } }
			 */

			if (scuola.getFlgIstSta().equals("0")) {
				List<VOAnagraficaScuola> plessi = anagraficaScuoleDAO.getScuoleByIstScol(
						scuolaPri != null ? scuolaPri.getCodScuUt() : scuola.getCodScuUt(),
						scuolaPri != null ? scuolaPri.getDatAnnScoRil() : scuola.getDatAnnScoRil());

				if (plessi != null && plessi.size() > 0) {
					model.addAttribute("flgPlessi", "true");
					model.addAttribute("numeroPlessi", String.valueOf(plessi.size()));
					int numeroInfanzia = 0;
					int numeroPrimaria = 0;
					int numeroIGrado = 0;
					int numeroIIGrado = 0;
					int numeroIC = 0;
					int numeroCT = 0;
					for (VOAnagraficaScuola voAnagraficaScuola : plessi) {
						if (voAnagraficaScuola.getCodScuUt().substring(2, 4).equals("AA"))
							numeroInfanzia++;
						else if (voAnagraficaScuola.getCodScuUt().substring(2, 4).equals("EE"))
							numeroPrimaria++;
						else if (voAnagraficaScuola.getCodScuUt().substring(2, 4).equals("MM"))
							numeroIGrado++;
						else if (voAnagraficaScuola.getCodScuUt().substring(2, 4).equals("CT"))
							numeroCT++;
						else if (voAnagraficaScuola.getCodScuUt().substring(2, 4).equals("IC"))
							numeroIC++;
						else if (voAnagraficaScuola.getCodScuUt().substring(2, 4).compareTo("IS") == 0
								|| voAnagraficaScuola.getCodScuUt().substring(2, 4).compareTo("MM") > 0)
							numeroIIGrado++;
					}
					if (numeroInfanzia > 0) {
						model.addAttribute("numeroInfanzia", String.valueOf(numeroInfanzia));
					}
					if (numeroPrimaria > 0) {
						model.addAttribute("numeroPrimaria", String.valueOf(numeroPrimaria));
					}
					if (numeroIGrado > 0) {
						model.addAttribute("numeroIGrado", String.valueOf(numeroIGrado));
					}
					if (numeroIIGrado > 0) {
						model.addAttribute("numeroIIGrado", String.valueOf(numeroIIGrado));
					}
					if (numeroIC > 0) {
						model.addAttribute("numeroIC", String.valueOf(numeroIC));
					}
					if (numeroCT > 0) {
						model.addAttribute("numeroCT", String.valueOf(numeroCT));
					}
				} else {
					model.addAttribute("flgPlessi", "false");
				}

				if (scuola.getCodScuUt().equalsIgnoreCase(scuola.getCodScuUtPri())) {
					VOAnagraficaScuola voScuola = anagraficaScuoleDAO.getScuolaByPrimaryKey(scuola.getCodScuUt(),
							scuola.getDatAnnScoRil());
					if (voScuola != null) {
						model.addAttribute("flgPlesso", "true");
					} else {
						model.addAttribute("flgPlesso", "false");
					}
				} else {
					model.addAttribute("flgPlesso", "true");
				}

				for (VOAnagraficaScuola plesso : plessi) {
					if (plesso.getCodScuUt().substring(2, 4).compareTo("EE") == 0) {
						plesso.setDesTipSit("Scuola Primaria");
					} else if (plesso.getCodScuUt().substring(2, 4).compareTo("AA") == 0) {
						plesso.setDesTipSit("Scuola dell'Infanzia");
					} else if (plesso.getCodScuUt().substring(2, 3).compareTo("P") == 0
							|| plesso.getCodScuUt().substring(2, 3).compareTo("S") == 0) {
						plesso.setDesTipSit("Liceo");
					} else if (plesso.getCodScuUt().substring(2, 3).compareTo("T") == 0) {
						plesso.setDesTipSit("Istituto Tecnico");
					} else if (plesso.getCodScuUt().substring(2, 3).compareTo("R") == 0) {
						plesso.setDesTipSit("Istituto Professionale");
					}
				}

				model.addAttribute("plessi", plessi);

				if (!scuola.getCodScuUt().equals(scuola.getCodScuUtPri())) {

					List<VOAnagraficaScuola> corsiSerali = anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
							scuola.getDatAnnScoRil());

					if (corsiSerali != null && corsiSerali.size() > 0) {
						model.addAttribute("flagCorsiSerali", "true");
					} else {
						model.addAttribute("flagCorsiSerali", "false");
					}

					for (VOAnagraficaScuola plesso : corsiSerali) {
						if (plesso.getCodScuUt().substring(2, 4).compareTo("EE") == 0) {
							plesso.setDesTipSit("Scuola Primaria");
						} else if (plesso.getCodScuUt().substring(2, 4).compareTo("AA") == 0) {
							plesso.setDesTipSit("Scuola dell'Infanzia");
						} else if (plesso.getCodScuUt().substring(2, 3).compareTo("P") == 0
								|| plesso.getCodScuUt().substring(2, 3).compareTo("S") == 0) {
							plesso.setDesTipSit("Liceo");
						} else if (plesso.getCodScuUt().substring(2, 3).compareTo("T") == 0) {
							plesso.setDesTipSit("Istituto Tecnico");
						} else if (plesso.getCodScuUt().substring(2, 3).compareTo("R") == 0) {
							plesso.setDesTipSit("Istituto Professionale");
						}
					}

					model.addAttribute("corsiSerali", corsiSerali);
				}

			}

			model.addAttribute("listaServiziWeb", serviziDAO.getServiziAttiviPerTipologia(Constants.SERVIZI_WEB,
					scuola.getCodScuUt(), scuola.getDatAnnScoRil()));

			VOLinkIscrizioniOnline linkIscrizioniOnline = anagraficaScuolaService.getUrlIscrizioniOnline(scuola,
					callBackUrl);
			model.addAttribute("urlIscrizioniOnline", linkIscrizioniOnline.getUrlIscrizioniOnline());
			model.addAttribute("provenienzaPortale", linkIscrizioniOnline.isProvenienzaPortale());
			model.addAttribute("visLinkIscr", linkIscrizioniOnline.isVisLinkIscr());

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("title", "Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
			// aggiunta per alternanza scuola lavoro
			VOGrafico infoGrafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(), scuola.getDatAnnScoRil(),
					Constants.SCHEDA_GRA_ALU, Constants.SEZIONE_GRA_ALU_ALT_SCU_LAV, "1");
			if (anagraficaScuolaService.isGraficoVisibile(infoGrafico, scuola)) {
				Integer anno = infoGrafico.getAnnScoRif();
				if (anno == null || anno == 0) {
					anno = Calendar.getInstance().get(Calendar.YEAR);
				}
				int numStrutture = alunniDAO.getNumStruttureAlternanzaScuolaLavoro(scuola.getCodScuUt(), anno);
				logger.info("numero pecorsi alternanza scuola lavoro");
				if (numStrutture > 0) {
					String infoStruttureAltScuolaLavoro = "L'istituto ha stipulato convenzioni con " + numStrutture
							+ " struttur" + (numStrutture == 1 ? "a" : "e");
					model.addAttribute("infoStruttureAltScuolaLavoro", infoStruttureAltScuolaLavoro);
				}
			}
			return "home/home_scheda_scuola";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/corsi")
	public String getCorsiCFP(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model) {
		try {

			VOAnagraficaScuola scuola = null;

			if (codScuUt != null && codScuUt.trim().length() > 0) {
				String datAnnScoRil = Utility.annoAccademico();

				if ("CF".equals(codScuUt.substring(2, 4))) {
					scuola = anagraficaScuoleDAO.getCentroFormazioneProfessionaleByPrimaryKey(codScuUt, datAnnScoRil);
				}
				if (scuola == null) {
					throw new Exception("scuola " + codScuUt + " non trovata");
				}

				if ("CF".equals(codScuUt.substring(2, 4))) {
					scuola.setListaCorsi(anagraficaScuoleDAO.getCorsiCP(codScuUt, datAnnScoRil));
				}

				model.addAttribute("scuola", scuola);

			} else {
				scuola = new VOAnagraficaScuola();
				model.addAttribute("scuola", scuola);
			}
			
			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_CORSI);
			model.addAttribute("title", "Corsi - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/corsi-CFP";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	/*
	 * @RequestMapping(value =
	 * "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/plessi") public String
	 * getPlessi(@PathVariable(value = "codScuUt") String codScuUt, String
	 * desNomScuNorm, Model model) { try { VOAnagraficaScuola scuola =
	 * anagraficaScuolaService.getAnagraficaScuola(codScuUt);
	 * 
	 * if("1".equals(scuola.getFlgSedDir())) { List<VOAnagraficaScuola> plessi =
	 * anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
	 * scuola.getDatAnnScoRil());
	 * 
	 * logger.debug("LISTA PLESSI --> "+plessi.size());
	 * 
	 * for(VOAnagraficaScuola plesso: plessi) {
	 * if(plesso.getCodScuUt().substring(2, 4).compareTo("EE") == 0) {
	 * plesso.setDesTipSit("Scuola Primaria"); } else
	 * if(plesso.getCodScuUt().substring(2, 4).compareTo("AA") == 0) {
	 * plesso.setDesTipSit("Scuola dell'Infanzia"); } else
	 * if(plesso.getCodScuUt().substring(2, 3).compareTo("P") == 0 ||
	 * plesso.getCodScuUt().substring(2, 3).compareTo("S") == 0) {
	 * plesso.setDesTipSit("Liceo"); } else if(plesso.getCodScuUt().substring(2,
	 * 3).compareTo("T") == 0) { plesso.setDesTipSit("Istituto Tecnico"); } else
	 * if(plesso.getCodScuUt().substring(2, 3).compareTo("R") == 0) {
	 * plesso.setDesTipSit("Istituto Professionale"); } }
	 * 
	 * model.addAttribute("plessi", plessi);
	 * 
	 * if(plessi.size()>0){ model.addAttribute("flgPlessi", true); } else {
	 * model.addAttribute("flgPlessi", false); } } model.addAttribute("scuola",
	 * scuola);
	 * 
	 * model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
	 * 
	 * return "home/schede_home_plessi"; } catch (Exception e) {
	 * logger.error(e.getMessage(), e); return "error"; } }
	 */

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/sintesi-progetti-pon")
	public String getProgettiPon(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			List<VOPon> ponList1 = anagraficaScuoleDAO.getListaDatiSintesiPon1(scuola.getCodScuUt());
			List<VOPon> ponList2 = anagraficaScuoleDAO.getListaDatiSintesiPon2(scuola.getCodScuUt());
			List<VOPon> ponList3 = anagraficaScuoleDAO.getListaDatiSintesiPon3(scuola.getCodScuUt());
			List<VOPon> ponList4 = anagraficaScuoleDAO.getListaDatiSintesiPon4(scuola.getCodScuUt());

			List<VOPon> ponListNew = new ArrayList<VOPon>();
			for (int i = 0; i < 9; i++) {
				VOPon voPon1 = ponList1.get(i);
				VOPon voPon2 = ponList2.get(i);
				VOPon voPon3 = ponList3.get(i);
				VOPon voPon4 = ponList4.get(i);

				VOPon voPonNew = new VOPon();
				voPonNew.setAnno(voPon1.getAnno());
				voPonNew.setNumProgettiFsePon(voPon1.getNumProgettiFsePon());
				voPonNew.setImpFinFsePon(voPon1.getImpFinFsePon());

				voPonNew.setAnno(voPon2.getAnno());
				voPonNew.setNumProgettiFsePor(voPon2.getNumProgettiFsePor());
				voPonNew.setImpFinFsePor(voPon2.getImpFinFsePor());

				voPonNew.setAnno(voPon3.getAnno());
				voPonNew.setNumProgettiFesrPon(voPon3.getNumProgettiFesrPon());
				voPonNew.setImpFinFesrPon(voPon3.getImpFinFesrPon());

				voPonNew.setAnno(voPon4.getAnno());
				voPonNew.setNumProgettiFesrPor(voPon4.getNumProgettiFesrPor());
				voPonNew.setImpFinFesrPor(voPon4.getImpFinFesrPor());

				ponListNew.add(voPonNew);
			}

			model.addAttribute("scuola", scuola);
			model.addAttribute("ponList", ponListNew);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_SIN_PRO_PON);
			model.addAttribute("title",
					"Sintesi progetti PON - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/sintesi-progetti-pon";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/progetti-fse")
	public String getProgettiFSE(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
			List<VOPon> ponList = anagraficaScuoleDAO.getListaDatiDettFse(scuola.getCodScuUt());

			VOPon voPon = new VOPon();
			model.addAttribute("scuola", scuola);
			boolean visualizzaDati = false;
			// model.addAttribute("ponList", ponList);
			if (ponList != null && ponList.size() > 0) {
				voPon = ponList.get(0);
				visualizzaDati = true;
			}
			model.addAttribute("voPon", voPon);
			model.addAttribute("visualizzaDati", visualizzaDati);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_PRO_FSE);
			model.addAttribute("title", "Progetti FSE - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/progetti-FSE";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/progetti-fesr")
	public String getProgettiFESR(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm,
			Model model) {
		try {

			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
			List<VOPon> ponList = anagraficaScuoleDAO.getListaDatiDettFesr(scuola.getCodScuUt());

			model.addAttribute("scuola", scuola);
			model.addAttribute("ponList", ponList);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_PRO_FESR);
			model.addAttribute("title", "Progetti FESR - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/progetti-FESR";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/tempi-scuola-AA")
	public String getTempiScuolaAA(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm,
			Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
			
			model.addAttribute("scuola", scuola);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_TEMPI_SCUOLA_AA);
			model.addAttribute("title", "Tempi scuola - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/tempi-scuola-AA";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/tempi-scuola")
	public String getTempiScuola(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			List<VOTempiScuola> listaTmp = anagraficaScuoleDAO.getListaTempiScuola(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()));

			List<VOTempiScuola> listaTmp1 = anagraficaScuoleDAO.getListaTempiScuolaAnnoCorso(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 1, scuola.getCodTipSit());

			List<VOTempiScuola> listaTmp2 = anagraficaScuoleDAO.getListaTempiScuolaAnnoCorso(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 2, scuola.getCodTipSit());

			List<VOTempiScuola> listaTmp3 = anagraficaScuoleDAO.getListaTempiScuolaAnnoCorso(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 3, scuola.getCodTipSit());

			List<VOTempiScuola> listaTmp4 = anagraficaScuoleDAO.getListaTempiScuolaAnnoCorso(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 4, scuola.getCodTipSit());

			List<VOTempiScuola> listaTmp5 = anagraficaScuoleDAO.getListaTempiScuolaAnnoCorso(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 5, scuola.getCodTipSit());

			List<VOOffertaFormativa> lista1 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 1, scuola.getCodTipSit());

			List<VOOffertaFormativa> lista2 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 2, scuola.getCodTipSit());

			List<VOOffertaFormativa> lista3 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 3, scuola.getCodTipSit());

			List<VOOffertaFormativa> lista4 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()), 4, scuola.getCodTipSit());

			if ("1".equals(scuola.getFlgSedDir())) {
				List<VOAnagraficaScuola> plessi = anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
						scuola.getDatAnnScoRil());

				if (plessi.size() > 0) {
					model.addAttribute("flgPlessi", "true");
				} else {
					model.addAttribute("flgPlessi", "false");
				}
			}

			model.addAttribute("listaTmp", listaTmp);

			model.addAttribute("listaTmp1", listaTmp1);
			model.addAttribute("listaTmp2", listaTmp2);
			model.addAttribute("listaTmp3", listaTmp3);
			model.addAttribute("listaTmp4", listaTmp4);
			model.addAttribute("listaTmp5", listaTmp5);

			model.addAttribute("lista1", lista1);
			model.addAttribute("lista2", lista2);
			model.addAttribute("lista3", lista3);
			model.addAttribute("lista4", lista4);

			model.addAttribute("scuola", scuola);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_TEMPI_SCUOLA);
			model.addAttribute("title", "Tempi scuola - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/tempi-scuola";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/offerta-formativa-anno-corrente")
	public String getOffertaFormativaAnnoCorrente(@PathVariable(value = "codScuUt") String codScuUt,
			String desNomScuNorm, Model model) {
		logger.info("getOffertaFormativaAnnoCorrente('" + codScuUt + "','" + desNomScuNorm + "')...");
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
			
			logger.info("scuola serale: " + scuola.isSerale());
			if (scuola.isSerale()) {
				List<VOOffertaFormativa> lista1 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrenteScuolaSerale(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()),
						new ArrayList<Integer>(Arrays.asList(1, 2)), scuola.getCodTipSit());
				List<VOOffertaFormativa> lista2 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrenteScuolaSerale(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()),
						new ArrayList<Integer>(Arrays.asList(3, 4)), scuola.getCodTipSit());
				List<VOOffertaFormativa> lista3 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrenteScuolaSerale(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()),
						new ArrayList<Integer>(Arrays.asList(5)), scuola.getCodTipSit());
				model.addAttribute("lista1", lista1);
				model.addAttribute("lista2", lista2);
				model.addAttribute("lista3", lista3);
			} else {
				List<VOOffertaFormativa> lista1 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 1, scuola.getCodTipSit());
				List<VOOffertaFormativa> lista2 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 2, scuola.getCodTipSit());
				List<VOOffertaFormativa> lista3 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 3, scuola.getCodTipSit());
				List<VOOffertaFormativa> lista4 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 4, scuola.getCodTipSit());
				List<VOOffertaFormativa> lista5 = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrente(
						scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 5, scuola.getCodTipSit());
				model.addAttribute("lista1", lista1);
				model.addAttribute("lista2", lista2);
				model.addAttribute("lista3", lista3);
				model.addAttribute("lista4", lista4);
				model.addAttribute("lista5", lista5);
			}
			if ("1".equals(scuola.getFlgSedDir())) {
				List<VOAnagraficaScuola> plessi = anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
						scuola.getDatAnnScoRil());
				if (plessi.size() > 0) {
					model.addAttribute("flgPlessi", "true");
				} else {
					model.addAttribute("flgPlessi", "false");
				}
			}
			model.addAttribute("scuola", scuola);
			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_OFF_ANN_COR);
			model.addAttribute("title",
					"Indirizzi di studio anno corrente - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");
			if (scuola.isSerale()) {
				return "home/indirizzi-studio-scuole-serali";
			} else {
				return "home/indirizzi-studio";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/offerta-formativa-anno-successivo")
	public String getOffertaFormativaAnnoSuccessivo(@PathVariable(value = "codScuUt") String codScuUt,
			String desNomScuNorm, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			model.addAttribute("flgAsterisco", "false");

			List<VOOffertaFormativa> lista = anagraficaScuoleDAO.getListaIndirizziAnnoSuccessivo(scuola.getCodScuUt(),
					Integer.parseInt(scuola.getDatAnnScoRil()));

			List<VOOffertaFormativa> listaCorrente = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrenteSucc(
					scuola.getCodScuUt(), Integer.parseInt(scuola.getDatAnnScoRil()), 4, scuola.getCodTipSit());

			VOOffertaFormativa vf = lista.get(0);
			if (vf.getDesSer().equalsIgnoreCase("Dati non disponibili per la scuola")) {
				lista = anagraficaScuoleDAO.getListaIndirizziPerAnnoCorrenteSucc(scuola.getCodScuUt(),
						Integer.parseInt(scuola.getDatAnnScoRil()), 4, scuola.getCodTipSit());
			} else {
				for (int i = 0; i < lista.size(); i++) {
					VOOffertaFormativa vfs = lista.get(i);

					StringTokenizer tokens = new StringTokenizer(vfs.getCodSer(), "|");
					String cods = tokens.nextToken() + tokens.nextToken();
					boolean trovato = false;
					for (int j = 0; j < listaCorrente.size(); j++) {
						VOOffertaFormativa vfc = listaCorrente.get(j);

						String codc = "";
						if (vfc.getCodSer() != null && !"".equals(vfc.getCodSer())) {
							StringTokenizer tokenc = new StringTokenizer(vfc.getCodSer(), "|");
							codc = tokenc.nextToken() + tokenc.nextToken();
						}

						if (cods.equalsIgnoreCase(codc)) {
							trovato = true;
						}
					}
					if (!trovato) {
						lista.get(i).setDesSer(vfs.getDesSer() + "*");
						model.addAttribute("flgAsterisco", "true");
					}
				}
			}

			if ("1".equals(scuola.getFlgSedDir())) {
				List<VOAnagraficaScuola> plessi = anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
						scuola.getDatAnnScoRil());

				if (plessi.size() > 0) {
					model.addAttribute("flgPlessi", "true");
				} else {
					model.addAttribute("flgPlessi", "false");
				}
			}

			model.addAttribute("lista", lista);
			model.addAttribute("scuola", scuola);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", Constants.SUB_FUNCTIONALITY_HOME_OFF_ANN_SUC);
			model.addAttribute("title", "Indirizzi di studio anno successivo - Chi siamo - " + scuola.getDesNomScu()
					+ " - Scuola in Chiaro");

			return "home/indirizzi-studio-anno-successivo";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/in-evidenza")
	public String getAvvisi(@PathVariable(value = "codScuUt") String codScuUt, String desNomScuNorm, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);
			List<VONews> listaNews = anagraficaScuoleDAO.getListaNews(codScuUt, scuola.getDatAnnScoRil());

			model.addAttribute("scuola", scuola);
			model.addAttribute("listaNews", listaNews);

			model.addAttribute("title", "Bacheca - Chi siamo - " + scuola.getDesNomScu() + " - Scuola in Chiaro");

			return "home/bacheca";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	/*
	 * @RequestMapping(value =
	 * "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/succursali") public
	 * String getSuccursali(@PathVariable(value = "codScuUt") String codScuUt,
	 * String desNomScuNorm, Model model) { try { VOAnagraficaScuola scuola =
	 * anagraficaScuolaService.getAnagraficaScuola(codScuUt);
	 * 
	 * List<VOSuccursale> succursali =
	 * anagraficaScuoleDAO.getSuccursaliScuola(scuola.getCodScuUt(),scuola.
	 * getDatAnnScoRil());
	 * 
	 * if("1".equals(scuola.getFlgSedDir())) { List<VOAnagraficaScuola> plessi =
	 * anagraficaScuoleDAO.getScuoleByIstScol(scuola.getCodScuUt(),
	 * scuola.getDatAnnScoRil());
	 * 
	 * if(plessi.size()>0) { model.addAttribute("flgPlessi", "true"); } else {
	 * model.addAttribute("flgPlessi", "false"); } }
	 * 
	 * model.addAttribute("scuola", scuola); model.addAttribute("succursali",
	 * succursali);
	 * 
	 * model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
	 * 
	 * return "home/schede_home_succursali"; } catch (Exception e) {
	 * logger.error(e.getMessage(), e); return "error"; } }
	 */

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/{area}/{codiceSottomenu}")
	public String getCompetenzeDiplomato(@PathVariable(value = "codScuUt") String codScuUt,
			@PathVariable(value = "desNomScuNorm") String desNomScuNorm, @PathVariable(value = "area") String area,
			@PathVariable(value = "codiceSottomenu") String codiceSottomenu, Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			model.addAttribute("scuola", scuola);

			Integer annoAccademico = anagraficaScuolaService.getAnnoCaratteristicheDiplomato();

			List<VOTipologia> listaVOTipologia = anagraficaScuolaService.getListaIndirizzoEsame(scuola.getCodForScuApp());

			List<VOIndirizzoEsame> listaVOIndirizzoEsame = new ArrayList<>();

			for (VOTipologia value : listaVOTipologia) {
				VOIndirizzoEsame vo = new VOIndirizzoEsame();

				vo.setTipologia(value);

				List<VOCaratteristica> listaVO = caratteristicaDiplomatoDAO.getListaCompetenza(value.getCodice(), annoAccademico,
						codiceSottomenu);

				if (!CollectionUtils.isEmpty(listaVO)) {
					vo.setListaCaratteristica(listaVO);
				}
				
				listaVOIndirizzoEsame.add(vo);
			}

			model.addAttribute("listaVOIndirizzoEsame", listaVOIndirizzoEsame);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", area);
			model.addAttribute("subSubFunctionalityOn", codiceSottomenu);

			return "home/caratteristicaDiplomato";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}/informazioni/{area}")
	public String getProfiloDiplomato(@PathVariable(value = "codScuUt") String codScuUt,
			@PathVariable(value = "desNomScuNorm") String desNomScuNorm, @PathVariable(value = "area") String area,
			Model model) {
		try {
			VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

			model.addAttribute("scuola", scuola);

			Integer annoAccademico = anagraficaScuolaService.getAnnoCaratteristicheDiplomato();

			List<VOTipologia> listaVOTipologia = anagraficaScuolaService.getListaIndirizzoEsame(scuola.getCodForScuApp());

			List<VOIndirizzoEsame> listaVOIndirizzoEsame = new ArrayList<>();

			for (VOTipologia value : listaVOTipologia) {
				VOIndirizzoEsame vo = new VOIndirizzoEsame();

				vo.setTipologia(value);

				List<VOCaratteristica> listaVO = caratteristicaDiplomatoDAO.getListaProfiloDiplomato(value.getCodice(), annoAccademico);

				if (!CollectionUtils.isEmpty(listaVO)) {
					vo.setListaCaratteristica(listaVO);
				}
				
				listaVOIndirizzoEsame.add(vo);
			}

			model.addAttribute("listaVOIndirizzoEsame", listaVOIndirizzoEsame);

			model.addAttribute("functionalityOn", Constants.FUNCTIONALITY_HOME);
			model.addAttribute("subFunctionalityOn", area);

			return "home/caratteristicaDiplomato";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}

	@Value("${codice.tipo.servizio.tempo.scuola}")
	public void setCodiceTipoServizioTempoScuola(String codiceTipoServizioTempoScuola) {
		this.codiceTipoServizioTempoScuola = codiceTipoServizioTempoScuola;
	}

	@Value("${codice.servizio.tempo.scuola}")
	public void setCodiceServizioTempoScuola(String codiceServizioTempoScuola) {
		this.codiceServizioTempoScuola = codiceServizioTempoScuola;
	}

}
