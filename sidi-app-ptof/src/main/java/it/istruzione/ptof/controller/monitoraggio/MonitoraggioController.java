package it.istruzione.ptof.controller.monitoraggio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.BeanCellaExcel;
import it.istruzione.ptof.beans.BeanDocumentoExcel;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoItem;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaItem;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.MonitoraggioStatisticheDTO;
import it.istruzione.ptof.constant.AppConstant;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.SidiUserHelper;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.MonitoraggioService;
     
@Controller
public class MonitoraggioController {
  
	private static Logger logger = Logger.getLogger(MonitoraggioController.class);

	@Autowired
	private GestioneDocumentiService gestioneDocumentiService;
	
	@Autowired
	private MonitoraggioService monitoraggioService;
	
	@RequestMapping("/fn-mon-report-completo.do")
	public String reportCompleto(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.REPORT_COMPLETO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.REPORT_COMPLETO);
		
		return "fn-mon-documenti-attivabili";
	}

	@RequestMapping("/fn-mon-cruscotto-fabbisogno.do")
	public String cruscottoFabisogno(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABISOGNO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.CRUSCOTTO_FABISOGNO);
		return "fn-mon-documenti-attivabili";
	}
	
	@RequestMapping("/fn-mon-statistiche.do")
	public String statistiche(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("titoloReport", AppConstant.STATISTICHE_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.STATISTICHE);
		return "fn-mon-documenti-attivabili";
	}

	
	@RequestMapping("/fn-mon-det-fab.do")
	public String dettaglioFabisogno(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("titoloReport", AppConstant.DETTAGLIO_FABBISOGNO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.DETTAGLIO_FABBISOGNO);
		return "fn-mon-documenti-attivabili";
	}

	@RequestMapping("/fn-mon-report-completo-dettaglio.do")
	public String reportCompletoDettaglio(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.REPORT_COMPLETO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.REPORT_COMPLETO);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-mon-report-completo"; //fn-mon-report-completo
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-comuni.do")
	public String cruscottoFabisognoPostiComuni(@RequestParam Integer progresivoGestioneCatalogoDocumento, Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABISOGNO_POSTI_COMUNI_TITTLE);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-mon-cruscotto-fabbisogno-posti-comuni";
	}

	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-sostegno.do")
	public String cruscottoFabisognoPostiSostegno(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABISOGNO_POSTI_SOSTEGNO_TITTLE);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-mon-cruscotto-fabbisogno-posti-sostegno";
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-potenziamento.do")
	public String cruscottoFabisognoPostiPotenziamento(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABISOGNO_POSTI_POTENZIAMENTO_TITTLE);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-mon-cruscotto-fabbisogno-posti-potenziamento";
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-totale-organica.do")
	public String cruscottoFabisognoTotaleOrganica(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABISOGNO_POSTI_TOTALE_ORGANICO_TITTLE);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-mon-cruscotto-fabbisogno-totale-organica";
	}
	
	@RequestMapping("/fn-mon-dettaglio-fabbisogno-dettaglio.do")
	public String dettaglioFabbisogno(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.DETTAGLIO_FABBISOGNO_TITTLE);
		return "fn-mon-dettaglio-fabbisogno";
	}
	
	@RequestMapping("/fn-mon-statistiche-dettaglio.do")
	public ModelAndView monStatistiche(@RequestParam Long progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progresivoGestioneCatalogoDocumento);
		
		SidiContesto sidiContesto = sidiUser.getCurrentContesto();
		List<MonitoraggioStatisticheDTO> listMonitoraggioStatisticheDTO = monitoraggioService.getStatistiche(sidiContesto,gestioneCatalogoDTO);
		
		BeanDocumentoExcel beanDocumentoExcel = new BeanDocumentoExcel();
		
		beanDocumentoExcel.setTitlePagina("RIEPILOGHI STATISTICI");
		
		beanDocumentoExcel.getHeaderPage().put("Dati aggiornati al", CommonsUtility.formatIt(new Date()));
		beanDocumentoExcel.getHeaderPage().put("Triennio", gestioneCatalogoDTO.getPeriodoTriennioRiferimentoAsString());
		beanDocumentoExcel.getHeaderPage().put("Versione", gestioneCatalogoDTO.getNumeroVersioneDocumento().toString());
		beanDocumentoExcel.getHeaderPage().put("Nome del documento", gestioneCatalogoDTO.getDescrizioneDocumento());
		
		beanDocumentoExcel.getCustomColumns().put(0, 8000);
		beanDocumentoExcel.getCustomColumns().put(1, 5000);
		beanDocumentoExcel.getCustomColumns().put(2, 5000);
		beanDocumentoExcel.getCustomColumns().put(3, 5000);
		beanDocumentoExcel.getCustomColumns().put(4, 5000);
		beanDocumentoExcel.getCustomColumns().put(5, 7000);	
		beanDocumentoExcel.getCustomColumns().put(6, 7000);	
		beanDocumentoExcel.getCustomColumns().put(7, 7000);	
		beanDocumentoExcel.getCustomColumns().put(8, 7000);	
		beanDocumentoExcel.getCustomColumns().put(9, 5000);	
		
		beanDocumentoExcel.addTitolo(new BeanCellaExcel("Regione",AppConstant.STILO_GOLD),
									 new BeanCellaExcel("Totale istituzioni scolastiche"),
									 new BeanCellaExcel("In compilazione"),
									 new BeanCellaExcel("Convalidati"),
									 new BeanCellaExcel("In anteprima"),
									 new BeanCellaExcel("Fabbisogno validato"),
									 new BeanCellaExcel("Fabbisogno non validato"),
									 new BeanCellaExcel("Con pubblicazione parziale"),
									 new BeanCellaExcel("Con pubblicazione definitiva"),
									 new BeanCellaExcel("Istituti mancanti"));
		
		if (listMonitoraggioStatisticheDTO!= null && listMonitoraggioStatisticheDTO.size()>0){
			for (MonitoraggioStatisticheDTO monitoraggioStatisticheDTO : listMonitoraggioStatisticheDTO) {
				List<Object> columns = new ArrayList<>();
				
				columns.add(monitoraggioStatisticheDTO.getUsr());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiPartecipanti());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiCompilati());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiConvalidati());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiInAnteprima());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiFabbisognoValidato());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiFabbisognoNonValidato());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiPubblicatiParziali());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiPubblicatiDefinitivi());
				columns.add(monitoraggioStatisticheDTO.getNumeroIstitutiNonRispondenti());
				
				beanDocumentoExcel.getListRows().add(columns);
			}
		}
		
		model.addAttribute("filename", AppConstant.NOME_FILE_MON_STATISTICHE);
		model.addAttribute("beanDocumentoExcel", beanDocumentoExcel);
		return new ModelAndView("ExcelView");
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-comuni-dettaglio.do")
	public ModelAndView monFabbisognoPostiComuni(@RequestParam Long progressivoGestioneCatalogoDocumento, @RequestParam String regione,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progressivoGestioneCatalogoDocumento);
		
		SidiContesto sidiContesto = sidiUser.getCurrentContesto();
		CruscottoFabbisogniPostiComuniDTO cruscottoFabbisogniPostiComuniDTO = monitoraggioService.getCruscottoFabbisogniPostiComuni(regione,gestioneCatalogoDTO);
		
		BeanDocumentoExcel beanDocumentoExcel = new BeanDocumentoExcel();
		
		beanDocumentoExcel.setTitlePagina("CRUSCOTTO FABBISOGNO POSTI COMUNI");
		
		beanDocumentoExcel.getHeaderPage().put("Dati aggiornati al", CommonsUtility.formatIt(new Date()));
		beanDocumentoExcel.getHeaderPage().put("Triennio", gestioneCatalogoDTO.getPeriodoTriennioRiferimentoAsString());
		beanDocumentoExcel.getHeaderPage().put("Versione", gestioneCatalogoDTO.getNumeroVersioneDocumento().toString());
		beanDocumentoExcel.getHeaderPage().put("Nome del documento", gestioneCatalogoDTO.getDescrizioneDocumento());
		
		beanDocumentoExcel.getCustomColumns().put(0, 8000);
		beanDocumentoExcel.getCustomColumns().put(1, 8000);
		beanDocumentoExcel.getCustomColumns().put(2, 8000);	
		beanDocumentoExcel.getCustomColumns().put(3, 10000);	
		beanDocumentoExcel.getCustomColumns().put(4, 10000);	
		beanDocumentoExcel.getCustomColumns().put(5, 8000);	
		beanDocumentoExcel.getCustomColumns().put(6, 8000);	
		beanDocumentoExcel.getCustomColumns().put(7, 8000);	
		beanDocumentoExcel.getCustomColumns().put(8, 8000);
		
		beanDocumentoExcel.addTitolo(new BeanCellaExcel("Regione",AppConstant.STILO_GOLD),
									 new BeanCellaExcel("Tab. A – Scuola dell'Infanzia"),
									 new BeanCellaExcel("Tab. B – Scuola Primaria"),
									 new BeanCellaExcel("Tab. C – Scuola Secondaria I Grado"),
									 new BeanCellaExcel("Tab. D – Scuola Secondaria II Grado"),
									 new BeanCellaExcel("Scuola dell'Infanzia"),
									 new BeanCellaExcel("Scuola Primaria"),
									 new BeanCellaExcel("Scuola Secondaria I Grado"),
									 new BeanCellaExcel("Scuola Secondaria II Grado"));
		
		beanDocumentoExcel.addTitoloColonneMerge(new BeanCellaExcel("POSTI COMUNI RELATIVI AL DECRETO",AppConstant.STILO_GOLD,2,5),
				 								 new BeanCellaExcel("POSTI COMUNI RELATIVI AL FABBISOGNO",AppConstant.STILO_GOLD,6,9));
		
		if (cruscottoFabbisogniPostiComuniDTO.getItems() != null && cruscottoFabbisogniPostiComuniDTO.getItems().size()>0){
			for (CruscottoFabbisogniPostiComuniItem cruscottoFabbisogniPostiComuniItem : cruscottoFabbisogniPostiComuniDTO.getItems()) {
				List<Object> columns = new ArrayList<>();
				
				columns.add(cruscottoFabbisogniPostiComuniItem.getRegione());
				columns.add(cruscottoFabbisogniPostiComuniItem.getDecretoInfanzia());
				columns.add(cruscottoFabbisogniPostiComuniItem.getDecretoPrimaria());
				columns.add(cruscottoFabbisogniPostiComuniItem.getDecretoIGrado());
				columns.add(cruscottoFabbisogniPostiComuniItem.getDecretoIIGrado());
				columns.add(cruscottoFabbisogniPostiComuniItem.getFabbisognoInfanzia());
				columns.add(cruscottoFabbisogniPostiComuniItem.getFabbisognoPrimaria());
				columns.add(cruscottoFabbisogniPostiComuniItem.getFabbisognoIGrado());
				columns.add(cruscottoFabbisogniPostiComuniItem.getFabbisognoIIGrado());
				
				beanDocumentoExcel.getListRows().add(columns);
			}
		}
		
		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE PER TIPO SCUOLA",AppConstant.STILO_BLUE_GREY),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleDecretoInfanzia(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleDecretoPrimaria(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleDecretoIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleDecretoIIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleFabbisognoInfanzia(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleFabbisognoPrimaria(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleFabbisognoIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleFabbisognoIIGrado(),AppConstant.STILO_CORNFLOWER_BLUE));

		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE GENERALE",AppConstant.STILO_BLUE_GREY),
									 new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleDecreto(),AppConstant.STILO_GOLD,2,5),
									 new BeanCellaExcel(cruscottoFabbisogniPostiComuniDTO.getTotaleFabbisogno(),AppConstant.STILO_GOLD,6,9));

		
		model.addAttribute("filename", AppConstant.NOME_FILE_MON_CRUSCOTTO_FABBISOGNO_POSTI_COMUNI);
		model.addAttribute("beanDocumentoExcel", beanDocumentoExcel);
		return new ModelAndView("ExcelView");
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-sostegno-dettaglio.do")
	public ModelAndView monFabbisognoPostiSostegno(@RequestParam Long progressivoGestioneCatalogoDocumento, @RequestParam String regione,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progressivoGestioneCatalogoDocumento);
		
		SidiContesto sidiContesto = sidiUser.getCurrentContesto();
		CruscottoFabbisogniPostiSostegnoDTO cruscottoFabbisogniPostiSostegnoDTO = monitoraggioService.getCruscottoFabbisogniPostiSostegno(regione,gestioneCatalogoDTO);
		
		BeanDocumentoExcel beanDocumentoExcel = new BeanDocumentoExcel();
		
		beanDocumentoExcel.setTitlePagina("CRUSCOTTO FABBISOGNO POSTI DI SOSTEGNO");
		
		beanDocumentoExcel.getHeaderPage().put("Dati aggiornati al", CommonsUtility.formatIt(new Date()));
		beanDocumentoExcel.getHeaderPage().put("Triennio", gestioneCatalogoDTO.getPeriodoTriennioRiferimentoAsString());
		beanDocumentoExcel.getHeaderPage().put("Versione", gestioneCatalogoDTO.getNumeroVersioneDocumento().toString());
		beanDocumentoExcel.getHeaderPage().put("Nome del documento", gestioneCatalogoDTO.getDescrizioneDocumento());
		
		beanDocumentoExcel.getCustomColumns().put(0, 8000);
		beanDocumentoExcel.getCustomColumns().put(1, 11000);
		beanDocumentoExcel.getCustomColumns().put(2, 12000);	
		beanDocumentoExcel.getCustomColumns().put(3, 10000);	
		beanDocumentoExcel.getCustomColumns().put(4, 14000);
		
		beanDocumentoExcel.addTitolo(new BeanCellaExcel("Regione",AppConstant.STILO_GOLD),
									 new BeanCellaExcel("Organico di diritto"),
									 new BeanCellaExcel("Organico posti di potenziamento per il sostegno"),
									 new BeanCellaExcel("Fabbisogno  dei posti di sostegno"),
									 new BeanCellaExcel("Fabbisogno dei posti di potenziamento per il sostegno"));
		
		beanDocumentoExcel.addTitoloColonneMerge(new BeanCellaExcel("POSTI DI SOSTEGNO E POSTI DI POTENZIAMENTO PER IL SOSTEGNO  RELATIVI AL DECRETO",AppConstant.STILO_GOLD,2,3),
				 								 new BeanCellaExcel("POSTI DI SOSTEGNO E POSTI DI POTENZIAMENTO PER IL SOSTEGNO RELATIVI AL FABBISOGNO ",AppConstant.STILO_GOLD,4,5));
		
		if (cruscottoFabbisogniPostiSostegnoDTO.getItems() != null && cruscottoFabbisogniPostiSostegnoDTO.getItems().size()>0){
			for (CruscottoFabbisogniPostiSostegnoItem cruscottoFabbisogniPostiSostegnoItem : cruscottoFabbisogniPostiSostegnoDTO.getItems()) {
				List<Object> columns = new ArrayList<>();
				
				columns.add(cruscottoFabbisogniPostiSostegnoItem.getRegione());
				columns.add(cruscottoFabbisogniPostiSostegnoItem.getDecretoOrganicoDiritto());
				columns.add(cruscottoFabbisogniPostiSostegnoItem.getDecretoPotenziamentoPerSostegno());
				columns.add(cruscottoFabbisogniPostiSostegnoItem.getFabbisognoSostegno());
				columns.add(cruscottoFabbisogniPostiSostegnoItem.getFabbisognoPotenziamentoPerSostegno());
				
				beanDocumentoExcel.getListRows().add(columns);
			}
		}
		
		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE",AppConstant.STILO_BLUE_GREY),
								     new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleDecretoOrganicoDiritto(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleDecretoPotenziamentoPerSostegno(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleFabbisognoSostegno(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleFabbisognoPotenziamentoPerSostegno(),AppConstant.STILO_CORNFLOWER_BLUE));

		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE GENERALE",AppConstant.STILO_BLUE_GREY),
									 new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleDecreto(),AppConstant.STILO_GOLD,2,3),
									 new BeanCellaExcel(cruscottoFabbisogniPostiSostegnoDTO.getTotaleFabbisogno(),AppConstant.STILO_GOLD,4,5));

		
		model.addAttribute("filename", AppConstant.NOME_FILE_MON_CRUSCOTTO_FABBISOGNO_POSTI_SOSTEGNO);
		model.addAttribute("beanDocumentoExcel", beanDocumentoExcel);
		return new ModelAndView("ExcelView");
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-posti-potenziamento-dettaglio.do")
	public ModelAndView monFabbisognoPostiPotenziamento(@RequestParam Long progressivoGestioneCatalogoDocumento, @RequestParam String regione,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progressivoGestioneCatalogoDocumento);
		
		SidiContesto sidiContesto = sidiUser.getCurrentContesto();
		CruscottoFabbisogniPostiDiPotenziamentoDTO cruscottoFabbisogniPostiDiPotenziamentoDTO = monitoraggioService.getCruscottoFabbisogniPostiPotenziamento(regione,gestioneCatalogoDTO);
		
		BeanDocumentoExcel beanDocumentoExcel = new BeanDocumentoExcel();
		
		beanDocumentoExcel.setTitlePagina("CRUSCOTTO FABBISOGNO POSTI DI POTENZIAMENTO");
		
		beanDocumentoExcel.getHeaderPage().put("Dati aggiornati al", CommonsUtility.formatIt(new Date()));
		beanDocumentoExcel.getHeaderPage().put("Triennio", gestioneCatalogoDTO.getPeriodoTriennioRiferimentoAsString());
		beanDocumentoExcel.getHeaderPage().put("Versione", gestioneCatalogoDTO.getNumeroVersioneDocumento().toString());
		beanDocumentoExcel.getHeaderPage().put("Nome del documento", gestioneCatalogoDTO.getDescrizioneDocumento());
		
		beanDocumentoExcel.getCustomColumns().put(0, 8000);
		beanDocumentoExcel.getCustomColumns().put(1, 8000);
		beanDocumentoExcel.getCustomColumns().put(2, 10000);	
		beanDocumentoExcel.getCustomColumns().put(3, 10000);	
		beanDocumentoExcel.getCustomColumns().put(4, 8000);	
		beanDocumentoExcel.getCustomColumns().put(5, 10000);
		beanDocumentoExcel.getCustomColumns().put(6, 10000);
			
		
		beanDocumentoExcel.addTitolo(new BeanCellaExcel("Regione",AppConstant.STILO_GOLD),
									 new BeanCellaExcel("Organico Primaria"),
									 new BeanCellaExcel("Organico Secondaria di I Grado"),
									 new BeanCellaExcel("Organico Secondaria di II Grado"),
									 new BeanCellaExcel("Fabbisogno scuola primaria"),
									 new BeanCellaExcel("Fabbisogno scuola secondaria I grado"),
									 new BeanCellaExcel("Fabbisogno scuola secondaria II grado"));
		
		beanDocumentoExcel.addTitoloColonneMerge(new BeanCellaExcel("POSTI DI POTENZIAMENTO RELATIVI AL DECRETO",AppConstant.STILO_GOLD,2,4),
				 								 new BeanCellaExcel("POSTI DI POTENZIAMENTO RELATIVI AL FABBISOGNO",AppConstant.STILO_GOLD,5,7));
		
		if (cruscottoFabbisogniPostiDiPotenziamentoDTO.getItems() != null && cruscottoFabbisogniPostiDiPotenziamentoDTO.getItems().size()>0){
			for (CruscottoFabbisogniPostiDiPotenziamentoItem cruscottoFabbisogniPostiDiPotenziamentoItem : cruscottoFabbisogniPostiDiPotenziamentoDTO.getItems()) {
				List<Object> columns = new ArrayList<>();
				
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getRegione());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getDecretoPrimaria());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getDecretoIGrado());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getDecretoIIGrado());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getFabbisognoPrimaria());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getFabbisognoIGrado());
				columns.add(cruscottoFabbisogniPostiDiPotenziamentoItem.getFabbisognoIIGrado());
				
				beanDocumentoExcel.getListRows().add(columns);
			}
		}
		
		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE PER TIPO SCUOLA",AppConstant.STILO_BLUE_GREY),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleDecretoPrimaria(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleDecretoIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleDecretoIIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleFabbisognoPrimaria(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleFabbisognoIGrado(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleFabbisognoIIGrado(),AppConstant.STILO_CORNFLOWER_BLUE));

		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE GENERALE",AppConstant.STILO_BLUE_GREY),
									 new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleDecreto(),AppConstant.STILO_GOLD,2,4),
									 new BeanCellaExcel(cruscottoFabbisogniPostiDiPotenziamentoDTO.getTotaleFabbisogno(),AppConstant.STILO_GOLD,5,7));

		
		model.addAttribute("filename", AppConstant.NOME_FILE_MON_CRUSCOTTO_FABBISOGNO_POSTI_POTENZIAMENTO);
		model.addAttribute("beanDocumentoExcel", beanDocumentoExcel);
		return new ModelAndView("ExcelView");
	}
	
	@RequestMapping("/fn-mon-cruscotto-fabbisogno-totale-organica-dettaglio.do")
	public ModelAndView monFabbisognoTotaleOrganica(@RequestParam Long progressivoGestioneCatalogoDocumento, @RequestParam String regione,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		GestioneCatalogoDTO gestioneCatalogoDTO = gestioneDocumentiService.getTbs1001GestionecatalogodocById(progressivoGestioneCatalogoDocumento);
		
		SidiContesto sidiContesto = sidiUser.getCurrentContesto();
		CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO = monitoraggioService.getCruscottoFabbisogniTotaleOrganica(regione,gestioneCatalogoDTO);
		
		BeanDocumentoExcel beanDocumentoExcel = new BeanDocumentoExcel();
		
		beanDocumentoExcel.setTitlePagina("CRUSCOTTO RIEPILOGO DELLE DOTAZIONI ORGANICHE DELL'AUTONOMIA");
		
		beanDocumentoExcel.getHeaderPage().put("Dati aggiornati al", CommonsUtility.formatIt(new Date()));
		beanDocumentoExcel.getHeaderPage().put("Triennio", gestioneCatalogoDTO.getPeriodoTriennioRiferimentoAsString());
		beanDocumentoExcel.getHeaderPage().put("Versione", gestioneCatalogoDTO.getNumeroVersioneDocumento().toString());
		beanDocumentoExcel.getHeaderPage().put("Nome del documento", gestioneCatalogoDTO.getDescrizioneDocumento());
		
		beanDocumentoExcel.getCustomColumns().put(0, 8000);
		beanDocumentoExcel.getCustomColumns().put(1, 8000);
		beanDocumentoExcel.getCustomColumns().put(2, 8000);	
		beanDocumentoExcel.getCustomColumns().put(3, 8000);	
		beanDocumentoExcel.getCustomColumns().put(4, 8000);	
		beanDocumentoExcel.getCustomColumns().put(5, 8000);
		beanDocumentoExcel.getCustomColumns().put(6, 8000);
			
		
		beanDocumentoExcel.addTitolo(new BeanCellaExcel("Regione",AppConstant.STILO_GOLD),
									 new BeanCellaExcel("Organico posti comuni"),
									 new BeanCellaExcel("Organico posti di sostegno"),
									 new BeanCellaExcel("Organico posti di potenziamento"),
									 new BeanCellaExcel("Fabbisogno posti comuni"),
									 new BeanCellaExcel("Fabbisogno posti di sostegno"),
									 new BeanCellaExcel("Fabbisogno posti di potenziamento"));
		
		beanDocumentoExcel.addTitoloColonneMerge(new BeanCellaExcel("DATI RELATIVI AL DECRETO",AppConstant.STILO_GOLD,2,4),
				 								 new BeanCellaExcel("DATI RELATIVI AL FABBISOGNO",AppConstant.STILO_GOLD,5,7));
		
		if (cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getItems() != null && cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getItems().size()>0){
			for (CruscottoRiepilogoDotazioniOrganicheAutonomiaItem cruscottoRiepilogoDotazioniOrganicheAutonomiaItem : cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getItems()) {
				List<Object> columns = new ArrayList<>();
				
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getRegione());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getDecretoPostiComuni());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getDecretoPostiSostegno());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getDecretoPostiPotenziamento());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getFabbisognoPostiComuni());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getFabbisognoPostiSostegno());
				columns.add(cruscottoRiepilogoDotazioniOrganicheAutonomiaItem.getFabbisognoPostiPotenziamento());
				
				beanDocumentoExcel.getListRows().add(columns);
			}
		}
		
		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE PER TIPO POSTO",AppConstant.STILO_BLUE_GREY),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleDecretoPostiComuni(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleDecretoPostiSostegno(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleDecretoPostiPotenziamento(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleFabbisognoPostiComuni(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleFabbisognoPostiSostegno(),AppConstant.STILO_CORNFLOWER_BLUE),
								     new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleFabbisognoPostiPotenziamento(),AppConstant.STILO_CORNFLOWER_BLUE));

		beanDocumentoExcel.addFooter(new BeanCellaExcel("TOTALE GENERALE",AppConstant.STILO_BLUE_GREY),
									 new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleDecreto(),AppConstant.STILO_GOLD,2,4),
									 new BeanCellaExcel(cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.getTotaleFabbisogno(),AppConstant.STILO_GOLD,5,7));

		
		model.addAttribute("filename", AppConstant.NOME_FILE_MON_CRUSCOTTO_RIEPILOGO_DOTAZIONI_ORGANICHE);
		model.addAttribute("beanDocumentoExcel", beanDocumentoExcel);
		return new ModelAndView("ExcelView");
	}
}