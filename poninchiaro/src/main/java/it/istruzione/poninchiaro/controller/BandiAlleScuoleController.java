package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.model.VOBandoIstituto;
import it.istruzione.poninchiaro.model.VOFiltroRicerca;
import it.istruzione.poninchiaro.model.VOIstituto;
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
public class BandiAlleScuoleController {
    
	private PoninchiaroService poninchiaroService;
	
    
    private static Logger log = Logger.getLogger(BandiAlleScuoleController.class);
    
    private static final int PAGE_SIZE = 10;
    
    @Autowired
    public BandiAlleScuoleController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    

	@RequestMapping("/bandiscuole/ricerca")
	public String bandiscuoleCercaIstituti(Model model,
			  							   VOFiltroRicerca filtroRicerca
			) throws Exception {
		
		log.debug("in bandiscuole-ricerca");
		
		model.addAttribute("stati", poninchiaroService.getStatiList());
		model.addAttribute("tipiBando", poninchiaroService.getTipoBandoList());
		model.addAttribute("regioni", poninchiaroService.getRegioniList());
		
		model.addAttribute("filtroRicerca", filtroRicerca);
		
		
		model.addAttribute("title","Pon in Chiaro - Cerca i bandi delle scuole");
		model.addAttribute("description","Pon in Chiaro &egrave; la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, bandi scuole, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Pon in chiaro: infomazioni sul Programma Operativo Nazionale e contatti");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/bandiscuole/ricerca/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/bandiscuole/ricerca/");
		
		return "bandiscuole/cercaistitutibandi";
	}
	
	@RequestMapping("/bandiscuole/ricerca/istituti")
	public String bandiscuoleIstituti(Model model,
									  VOFiltroRicerca filtroRicerca,
									  HttpServletRequest request
			) throws Exception {
		
		log.debug("in bandiscuole-istituti");
		log.debug(ReflectionToStringBuilder.toString(filtroRicerca,ToStringStyle.MULTI_LINE_STYLE));
		
		String filtri = "";
		String slash = "";
		if (filtroRicerca.getCodiceRegione() != null && !filtroRicerca.getCodiceRegione().isEmpty()){
			LabelValueBean vo = poninchiaroService.getRegioneByCod(filtroRicerca.getCodiceRegione());
			if (vo != null){
				filtri = filtri + "Regione " + vo.getLabel();
			}
			slash = " / ";
		}
		if (filtroRicerca.getCodiceProvincia() != null && !filtroRicerca.getCodiceProvincia().isEmpty()){
			LabelValueBean vo = poninchiaroService.getProvinciaByCod(filtroRicerca.getCodiceProvincia());
			if (vo != null){
				filtri = filtri + slash + "Provincia di " + vo.getLabel();
			}
			slash = " / ";
		}		
		if (filtroRicerca.getTipoBando() != null && !filtroRicerca.getTipoBando().isEmpty()){
			filtri = filtri + slash + "Tipo bando: " + filtroRicerca.getTipoBando();
			slash = " / ";
		}
		if (filtroRicerca.getStato() != null && !filtroRicerca.getStato().isEmpty() && !poninchiaroService.getDecodificaStato(filtroRicerca.getStato()).equals("")){
			filtri = filtri + slash + "Stato: " + poninchiaroService.getDecodificaStato(filtroRicerca.getStato());
			slash = " / ";
		}
		if (filtroRicerca.getCodicemeccanografico_autocomplete() != null && !filtroRicerca.getCodicemeccanografico_autocomplete().isEmpty()){
			filtri = filtri + slash + "Scuola: " + filtroRicerca.getScuola();
		}
		model.addAttribute("filtri", filtri);
		model.addAttribute("tipoBando", filtroRicerca.getTipoBando());
		
		List<VOIstituto> elencoIstituti = poninchiaroService.getIstitutiBandi(filtroRicerca);
		//model.addAttribute("istituti", elencoIstituti);
		model.addAttribute("numeroscuole", elencoIstituti.size());
		
		String dataDB = poninchiaroService.getDataAggiornamentoIstitutiBandi();
		String dataAggiornamento = "";
		if (!dataDB.isEmpty()){
			dataAggiornamento = dataDB.substring(6, 8) + "/" + dataDB.substring(4, 6) + "/" + dataDB.substring(0, 4) ;
		}
		model.addAttribute("dataAggiornamento", dataAggiornamento);
		
		
		String queryString = "?";
		if (request.getQueryString()!=null){
			queryString = "?" + request.getQueryString().toString();
		}
		
		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		
		model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
		request.getSession().setAttribute("linkRisultatiRicerca", "/bandiscuole/ricerca/istituti"+queryString);
		
		PagedListHolder<VOIstituto> pagedListHolder = new PagedListHolder<VOIstituto>(elencoIstituti);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(PAGE_SIZE);
		pagedListHolder.setMaxLinkedPages(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		
		model.addAttribute("title","Ricerca istituti bandi scuole: " + elencoIstituti.size() + " risultati trovati - Pon in Chiaro");
		model.addAttribute("description","La ricerca effettuata ha prodotto " + elencoIstituti.size() + " risultati utili");
		model.addAttribute("keywords","pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei, " + filtri);
		model.addAttribute("socialTitle","Ricerca istituti bandi scuole: " + elencoIstituti.size() + " risultati trovati");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/bandiscuole/ricerca/istituti"+queryStringWithoutPage);
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/bandiscuole/ricerca/istituti"+queryStringWithoutPage);
		
		return "bandiscuole/elencoistitutibandi";
	}

	@RequestMapping("/istituti/{codScuUt}/{desNomScuNorm}/bandi")
	public String dettaglioBandiIstituto(@PathVariable(value = "codScuUt") String codScuUt,
            					  String desNomScuNorm,
            					  VOFiltroRicerca filtroRicerca,
            					  Model model,
			  				      HttpServletRequest request
			) throws Exception {
		
		if (codScuUt != null){
			codScuUt = codScuUt.toUpperCase();
		}
		log.debug("in dettaglioProgetti : " + codScuUt);
		
		String linkRisultatiRicerca = "/bandiscuole/ricerca/";
		
		if (request.getSession().getAttribute("linkRisultatiRicerca") != null){
			linkRisultatiRicerca = request.getSession().getAttribute("linkRisultatiRicerca").toString();
		}
		model.addAttribute("linkRisultatiRicerca", linkRisultatiRicerca);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt);
		model.addAttribute("scuola", scuola);
		if (scuola != null){

			Map<String, String> tipiBando = poninchiaroService.getTipoBandoList();
			model.addAttribute("tipiBando",tipiBando);

			String tipoBandoDaVisualizzare;
			if (filtroRicerca != null && filtroRicerca.getTipoBando() != null && !filtroRicerca.getTipoBando().isEmpty()){
				tipoBandoDaVisualizzare = filtroRicerca.getTipoBando();
			}else{
				tipoBandoDaVisualizzare = tipiBando.values().iterator().next();
			}
			model.addAttribute("tipoBando",tipoBandoDaVisualizzare);

			log.debug("tipoBando da visualizzare : " + tipoBandoDaVisualizzare);
			model.addAttribute("filtroRicerca",filtroRicerca);

			List<VOBandoIstituto> bandi = poninchiaroService.getBandiIstituto(codScuUt, tipoBandoDaVisualizzare);
			model.addAttribute("bandi",bandi);

			String dataDB = poninchiaroService.getDataAggiornamentoIstitutiBandi();
			String dataAggiornamento = "";
			if (!dataDB.isEmpty()){
				dataAggiornamento = dataDB.substring(6, 8) + "/" + dataDB.substring(4, 6) + "/" + dataDB.substring(0, 4) ;
			}
			model.addAttribute("dataAggiornamento", dataAggiornamento);

			model.addAttribute("title","Bandi scuola - " + scuola.getDenominazione() + " - Pon in Chiaro");
			model.addAttribute("description","La lista completa dei bandi scuola dell'istituto " + scuola.getDenominazione() + ", situato in " + scuola.getIndirizzoCompleto());
			model.addAttribute("keywords",scuola.getDenominazione() + ", Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, progetti formazione, progetti infrastruttura, bandi");
			model.addAttribute("socialTitle","Bandi scuola - " + scuola.getDenominazione());
			model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/bandi/");
			model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/bandi/");

		}
		
		return "dettaglio/bandiscuola";
	}

	@RequestMapping("/istituti/{codScuUt}/{desNomScuNorm}/bandi/{codBando}")
	public String dettaglioBando(@PathVariable(value = "codScuUt") String codScuUt,
            					  String desNomScuNorm,
            					  @PathVariable(value = "codBando") String codBando,
            					  Model model,
			  				      HttpServletRequest request
			) throws Exception {
		
		log.debug("in dettaglioBando : " + codBando);
		if (codScuUt != null){
			codScuUt = codScuUt.toUpperCase();
		}
		
		String linkRisultatiRicerca = "/bandiscuole/ricerca/";
		
		if (request.getSession().getAttribute("linkRisultatiRicerca") != null){
			linkRisultatiRicerca = request.getSession().getAttribute("linkRisultatiRicerca").toString();
		}
		model.addAttribute("linkRisultatiRicerca", linkRisultatiRicerca);
		
		VOIstituto scuola = poninchiaroService.getIstituto(codScuUt);
		model.addAttribute("scuola", scuola);
		VOBandoIstituto bando = poninchiaroService.getBando(codBando);
		model.addAttribute("bando",bando);
		
		if (scuola != null){

			model.addAttribute("title","Bando scuola - " + scuola.getDenominazione() + " - Pon in Chiaro");
			model.addAttribute("description","Bando scuola dell'istituto " + scuola.getDenominazione() + ", situato in " + scuola.getIndirizzoCompleto());
			model.addAttribute("keywords",scuola.getDenominazione() + ", Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, progetti formazione, progetti infrastruttura, bandi");
			model.addAttribute("socialTitle","Bando scuola - " + scuola.getDenominazione());
			model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/bandi/" + codBando + "/");
			model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/istituti/" + scuola.getCodiceMeccanograficoLowerCase() + "/" + scuola.getDesNomScuNorm() + "/bandi/" + codBando + "/");

		}
		
		return "dettaglio/dettagliobando";
	}
	

}
