package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.model.VODocumento;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOFiltroRicerca;
import it.istruzione.poninchiaro.model.VOFornitore;
import it.istruzione.poninchiaro.model.VOIstituto;
import it.istruzione.poninchiaro.model.VOProgettoIstituto;
import it.istruzione.poninchiaro.service.DocumentiService;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeneficiariController {
    
	private PoninchiaroService poninchiaroService;
	
	private DocumentiService documentiService;
    
    private static Logger log = Logger.getLogger(BeneficiariController.class);
    
    private static final int PAGE_SIZE = 10;
    
    @Autowired
    public BeneficiariController(PoninchiaroService poninchiaroService, DocumentiService documentiService) {
        this.poninchiaroService = poninchiaroService;
        this.documentiService = documentiService;
    }
    

	@RequestMapping("/beneficiari/ricerca")
	public String beneficiariCercaIstituti(Model model,
			  							   VOFiltroRicerca filtroRicerca
			) throws Exception {
		
		log.debug("in beneficiari-ricerca");
		
		model.addAttribute("anni", poninchiaroService.getAnniBandoList(false));
		model.addAttribute("tipiFondo", poninchiaroService.getTipoFondoList(false));
		model.addAttribute("regioni", poninchiaroService.getRegioniList());
		
		model.addAttribute("filtroRicerca", filtroRicerca);
		
		
		model.addAttribute("title","Pon in Chiaro - Cerca gli istituti beneficiari");
		model.addAttribute("description","Pon in Chiaro &egrave; la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, istituti beneficiari, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Ricerca istituti beneficiari");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/ricerca/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/ricerca/");
		
		return "beneficiari/cercaistituti";
	}
	
	@RequestMapping("/beneficiari/ricerca/istituti")
	public String beneficiariIstituti(Model model,
									  VOFiltroRicerca filtroRicerca,
									  HttpServletRequest request
			) throws Exception {
		
		log.debug("in beneficiari-istituti");
		log.debug(ReflectionToStringBuilder.toString(filtroRicerca,ToStringStyle.MULTI_LINE_STYLE));
		
		//"Fondi FSE 2008 / Regione Puglia / Provincia di Bari";
		String filtri = "Fondi";
		if (filtroRicerca.getTipoFondo() != null && !filtroRicerca.getTipoFondo().isEmpty()){
			filtri = filtri + " " + filtroRicerca.getTipoFondo();
		}
		if (filtroRicerca.getAnno() != null && !filtroRicerca.getAnno().isEmpty()){
			filtri = filtri + " " + filtroRicerca.getAnno();
		}
		if (filtroRicerca.getCodiceRegione() != null && !filtroRicerca.getCodiceRegione().isEmpty()){
			LabelValueBean vo = poninchiaroService.getRegioneByCod(filtroRicerca.getCodiceRegione());
			if (vo != null){
				filtri = filtri + " / Regione " + vo.getLabel();
			}
		}
		if (filtroRicerca.getCodiceProvincia() != null && !filtroRicerca.getCodiceProvincia().isEmpty()){
			LabelValueBean vo = poninchiaroService.getProvinciaByCod(filtroRicerca.getCodiceProvincia());
			if (vo != null){
				filtri = filtri + " / Provincia di " + vo.getLabel();
			}
		}
		if (filtroRicerca.getCodicemeccanografico_autocomplete() != null && !filtroRicerca.getCodicemeccanografico_autocomplete().isEmpty()){
			filtri = filtri + " / Scuola: " + filtroRicerca.getScuola();
		}
		model.addAttribute("filtri", filtri);
		model.addAttribute("tipoFondo", filtroRicerca.getTipoFondo());
		model.addAttribute("anno", filtroRicerca.getAnno());
		
		VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		List<VOIstituto> elencoIstituti = poninchiaroService.getIstitutiBeneficiari(filtroRicerca, evento.getPrgEve());
		model.addAttribute("istituti", elencoIstituti);
		model.addAttribute("numeroscuole", elencoIstituti.size());
		model.addAttribute("dataAggiornamento", evento.getDataAggiornamento());
		
		String queryString = "?";
		if (request.getQueryString()!=null){
			queryString = "?" + request.getQueryString().toString();
		}
		
		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		
		model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
		request.getSession().setAttribute("linkRisultatiRicerca", "/beneficiari/ricerca/istituti"+queryString);
		
		PagedListHolder<VOIstituto> pagedListHolder = new PagedListHolder<VOIstituto>(elencoIstituti);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(PAGE_SIZE);
		pagedListHolder.setMaxLinkedPages(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		
		model.addAttribute("title","Ricerca istituti beneficiari: " + elencoIstituti.size() + " risultati trovati - Pon in Chiaro");
		model.addAttribute("description","La ricerca effettuata ha prodotto " + elencoIstituti.size() + " risultati utili");
		model.addAttribute("keywords","pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei, " + filtri);
		model.addAttribute("socialTitle","Ricerca istituti beneficiari: " + elencoIstituti.size() + " risultati trovati");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/ricerca/istituti"+queryStringWithoutPage);
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/ricerca/istituti"+queryStringWithoutPage);
		
		return "beneficiari/elencoistituti";
	}

	@RequestMapping("/istituti/{codScuUt}/{desNomScuNorm}")
	public String dettaglioScuola(@PathVariable(value = "codScuUt") String codScuUt,
            					  String desNomScuNorm,
            					  Model model,
			  				      HttpServletRequest request
			) throws Exception {
		
		if (codScuUt != null){
			codScuUt = codScuUt.toUpperCase();
		}
		log.debug("in dettaglioScuola : " + codScuUt);
		
		String linkRisultatiRicerca = "/beneficiari/ricerca/";
				
		if (request.getSession().getAttribute("linkRisultatiRicerca") != null){
			linkRisultatiRicerca = request.getSession().getAttribute("linkRisultatiRicerca").toString();
		}
		model.addAttribute("linkRisultatiRicerca", linkRisultatiRicerca);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt);
		model.addAttribute("scuola", scuola);
		String dirigente = "";
		if (scuola != null){

			List<VODocumento> listaDocumenti = documentiService.getDocumentiScuola(
					scuola.getCodiceMeccanografico(),
					scuola.getDatAnnScoRil(), "H");

			if (!listaDocumenti.isEmpty()) {
				model.addAttribute("fotoScuola", listaDocumenti.get(0));
			}

			if (scuola.getSitoweb()!= null &&
					!scuola.getSitoweb().toLowerCase().startsWith("http")) {

				scuola.setSitoweb("http://" + scuola.getSitoweb());
			}

			dirigente = poninchiaroService.getDirigenteScolastico(scuola.getCodiceMeccanografico(),scuola.getDatAnnScoRil());

			if (dirigente != null)
				model.addAttribute("dirigente", dirigente.trim());
			else {
				model.addAttribute("dirigente", "");
			}



			model.addAttribute("title","Informazioni - " + scuola.getDenominazione() + " - Pon in Chiaro");
			model.addAttribute("description","Tutte le informazioni sui progetti infrastruttura, i progetti formazione e i bandi dell'istituto " + scuola.getDenominazione() + ", situato in " + scuola.getIndirizzoCompleto());
			model.addAttribute("keywords",scuola.getDenominazione() + ", Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, progetti formazione, progetti infrastruttura, bandi, " + dirigente + ", " + scuola.getTelefono());
			model.addAttribute("socialTitle","Informazioni - " + scuola.getDenominazione());
			model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/");
			model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/");

		}
		
		return "dettaglio/dettaglioscuola";
	}

	@RequestMapping("/istituti/{codScuUt}/{desNomScuNorm}/progetti/{codTipFon}")
	public String dettaglioProgettiIstituto(@PathVariable(value = "codTipFon") String codTipFon,
								  @PathVariable(value = "codScuUt") String codScuUt,
            					  String desNomScuNorm,
            					  VOFiltroRicerca filtroRicerca,
            					  Model model,
			  				      HttpServletRequest request
			) throws Exception {
		
		if (codScuUt != null){
			codScuUt = codScuUt.toUpperCase();
		}
		log.debug("in dettaglioProgetti : " + codScuUt);
		
		String linkRisultatiRicerca = "/beneficiari/ricerca/";
		
		if (request.getSession().getAttribute("linkRisultatiRicerca") != null){
			linkRisultatiRicerca = request.getSession().getAttribute("linkRisultatiRicerca").toString();
		}
		model.addAttribute("linkRisultatiRicerca", linkRisultatiRicerca);
		
		model.addAttribute("codTipFon", codTipFon);
		if (codTipFon != null){
			codTipFon = codTipFon.toUpperCase();
		}
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt);
		model.addAttribute("scuola", scuola);
		if (scuola != null){

			Map<String, String> anni = poninchiaroService.getAnniBandoList(true);
			model.addAttribute("anni",anni);

			String annoDaVisualizzare;
			if (filtroRicerca != null && filtroRicerca.getAnno() != null && !filtroRicerca.getAnno().isEmpty()){
				annoDaVisualizzare = filtroRicerca.getAnno();
			}else{
				//annoDaVisualizzare = anni.values().iterator().next();
				annoDaVisualizzare = "";
			}
			model.addAttribute("anno",annoDaVisualizzare);

			log.debug("anno da visualizzare : " + annoDaVisualizzare);
			model.addAttribute("filtroRicerca",filtroRicerca);

			VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
			List<VOProgettoIstituto> progetti = poninchiaroService.getProgettiIstituto(codScuUt, annoDaVisualizzare, codTipFon, evento.getPrgEve());
			model.addAttribute("progetti",progetti);
			model.addAttribute("dataAggiornamento", evento.getDataAggiornamento());



			model.addAttribute("title","Progetti " + codTipFon + " - " + scuola.getDenominazione() + " - Pon in Chiaro");
			model.addAttribute("description","La lista completa dei progetti " + codTipFon + " dell'istituto " + scuola.getDenominazione() + ", situato in " + scuola.getIndirizzoCompleto());
			model.addAttribute("keywords",scuola.getDenominazione() + ", Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, progetti formazione, progetti infrastruttura, bandi");
			model.addAttribute("socialTitle","Progetti " + codTipFon + " - " + scuola.getDenominazione());
			model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/progetti/" + codTipFon.toLowerCase() + "/");
			model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/progetti/" + codTipFon.toLowerCase() + "/");

		}
		
		return "dettaglio/progettiscuola";
	}

	@RequestMapping("/beneficiari/fornitori")
	public String beneficiariFornitori(Model model,
									  HttpServletRequest request
			) throws Exception {
		
		log.debug("in beneficiari-fornitori");
		
		
		VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
		List<VOFornitore> elencoFornitori = poninchiaroService.getFornitoriBeneficiari(evento.getPrgEve());
		model.addAttribute("fornitori", elencoFornitori);
		model.addAttribute("numerofornitori", elencoFornitori.size());
		model.addAttribute("dataAggiornamento", evento.getDataAggiornamento());
		
		String queryString = "?";
		if (request.getQueryString()!=null){
			queryString = "?" + request.getQueryString().toString();
		}
		
		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		
		model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
		request.getSession().setAttribute("linkElencoFornitori", "/beneficiari/fornitori"+queryString);
		
		PagedListHolder<VOFornitore> pagedListHolder = new PagedListHolder<VOFornitore>(elencoFornitori);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(PAGE_SIZE);
		pagedListHolder.setMaxLinkedPages(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		
		model.addAttribute("title","Elenco fornitori: " + elencoFornitori.size() + " risultati trovati - Pon in Chiaro");
		model.addAttribute("description","La ricerca effettuata ha prodotto " + elencoFornitori.size() + " risultati utili");
		model.addAttribute("keywords","pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei. Fornitori");
		model.addAttribute("socialTitle","Elenco fornitori: " + elencoFornitori.size() + " risultati trovati");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/fornitori/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/fornitori/");
		
		return "beneficiari/elencofornitori";
	}

	@RequestMapping("/beneficiari/fornitori/{desNomScuNorm}/{codFornitore}/{prgFornitore}/progetti")
	public String dettaglioProgettiFornitore(@PathVariable(value = "codFornitore") String codFornitore,
								  @PathVariable(value = "prgFornitore") String prgFornitore,
            					  String desNomScuNorm,
            					  Model model,
			  				      HttpServletRequest request
			) throws Exception {
		
		log.debug("in dettaglioProgettiFornitore : " + codFornitore);
		
		String linkElencoFornitori = "/beneficiari/fornitori/";
		
		if (request.getSession().getAttribute("linkElencoFornitori") != null){
			linkElencoFornitori = request.getSession().getAttribute("linkElencoFornitori").toString();
		}
		model.addAttribute("linkElencoFornitori", linkElencoFornitori);
		
		VOFornitore fornitore = poninchiaroService.getFornitore(prgFornitore);
		model.addAttribute("fornitore", fornitore);
		if (fornitore != null){

			VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
			List<VOProgettoIstituto> progetti = poninchiaroService.getProgettiFornitore(prgFornitore, evento.getPrgEve());
			model.addAttribute("numeroprogetti",progetti.size());
			model.addAttribute("dataAggiornamento", evento.getDataAggiornamento());

			PagedListHolder<VOProgettoIstituto> pagedListHolder = new PagedListHolder<VOProgettoIstituto>(progetti);
			int page = ServletRequestUtils.getIntParameter(request, "page", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(PAGE_SIZE);
			pagedListHolder.setMaxLinkedPages(5);
			model.addAttribute("pagedListHolder", pagedListHolder);

			model.addAttribute("title","Progetti fornitore - " + fornitore.getDenominazione() + " - Pon in Chiaro");
			model.addAttribute("description","La lista completa dei progetti del fornitore " + fornitore.getDenominazione());
			model.addAttribute("keywords",fornitore.getDenominazione() + ", Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, progetti formazione, progetti infrastruttura, bandi");
			model.addAttribute("socialTitle","Progetti fornitore - " + fornitore.getDenominazione());
			model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/fornitori/" + fornitore.getDesNomScuNorm() + "/" + fornitore.getCodFornitore() + "/" + fornitore.getPrgFornitore() + "/progetti/");
			model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/beneficiari/fornitori/" + fornitore.getDesNomScuNorm() + "/" + fornitore.getCodFornitore() + "/" + fornitore.getPrgFornitore() + "/progetti/");

		}
		
		return "dettaglio/progettifornitore";
	}

	
	
}
