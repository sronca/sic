package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.model.VOEvento;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LescuoledelponController {
    
	private PoninchiaroService poninchiaroService;
	
    private static Logger log = Logger.getLogger(LescuoledelponController.class);
    
    private static final int PAGE_SIZE = 10;
    
    @Autowired
    public LescuoledelponController(PoninchiaroService poninchiaroService) {
        this.poninchiaroService = poninchiaroService;
    }
    

	@RequestMapping("/scuoledelpon/ricerca")
	public String scuoledelponCercaIstituti(Model model,
			  							   VOFiltroRicerca filtroRicerca
			) throws Exception {
		
		log.debug("in scuoledelpon-ricerca");
		
		Map<String, String> tipiFondo = poninchiaroService.getTipoFondoList(true);
		tipiFondo.put("NESSUNO", "NESSUNO");
		model.addAttribute("regioni", poninchiaroService.getRegioniList());
		model.addAttribute("tipiFondo", tipiFondo);
		model.addAttribute("tipiIstruzione", poninchiaroService.getTipoIstruzioneList());
		
		model.addAttribute("filtroRicerca", filtroRicerca);
		
		
		model.addAttribute("title","Pon in Chiaro - Le scuole del pon");
		model.addAttribute("description","Pon in Chiaro &egrave; la rubrica contenente le principali informazioni sui Fondi Strutturali Europei");
		model.addAttribute("keywords","pon in chiaro, Le scuole del pon, istituti beneficiari, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei");
		model.addAttribute("socialTitle","Le scuole del pon");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/scuoledelpon/ricerca/");
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/scuoledelpon/ricerca/");
		
		return "lescuoledelpon/cercaistituti";
	}
	
	@RequestMapping("/scuoledelpon/ricerca/istituti")
	public String scuoledelponIstituti(Model model,
									  VOFiltroRicerca filtroRicerca,
									  HttpServletRequest request
			) throws Exception {
		
		log.debug("in scuoledelpon-istituti");
		log.debug(ReflectionToStringBuilder.toString(filtroRicerca,ToStringStyle.MULTI_LINE_STYLE));
		
		String filtri = "";
		String slash = "";
		if (filtroRicerca.getCodiceRegione() != null && !filtroRicerca.getCodiceRegione().isEmpty()){
			LabelValueBean vo = poninchiaroService.getRegioneByCod(filtroRicerca.getCodiceRegione());
			if (vo != null){
				filtri = filtri + "Regione " + vo.getLabel();
				slash = " / ";
			}
		}
		if (filtroRicerca.getCodiceProvincia() != null && !filtroRicerca.getCodiceProvincia().isEmpty()){
			LabelValueBean vo = poninchiaroService.getProvinciaByCod(filtroRicerca.getCodiceProvincia());
			if (vo != null){
				filtri = filtri + slash + "Provincia di " + vo.getLabel();
				slash = " / ";
			}
		}
		if (filtroRicerca.getCodiceComune() != null && !filtroRicerca.getCodiceComune().isEmpty()){
			LabelValueBean vo = poninchiaroService.getComuneByCod(filtroRicerca.getCodiceComune());
			if (vo != null){
				filtri = filtri + slash + "Comune di " + vo.getLabel();
				slash = " / ";
			}
		}		
		if (filtroRicerca.getCodicemeccanografico_autocomplete() != null && !filtroRicerca.getCodicemeccanografico_autocomplete().isEmpty()){
			filtri = filtri + slash + "Scuola: " + filtroRicerca.getScuola();
			slash = " / ";
		}
		if (filtroRicerca.getCodiceTipoIstruzione() != null && !filtroRicerca.getCodiceTipoIstruzione().isEmpty()){
				filtri = filtri + slash + "Tipo istruzione " + poninchiaroService.getDecodificaTipoIstruzione(filtroRicerca.getCodiceTipoIstruzione());
				slash = " / ";
		}
		if (filtroRicerca.getTipoFondo() != null && !filtroRicerca.getTipoFondo().isEmpty()){
			filtri = filtri + slash + "Categoria di intervento " + filtroRicerca.getTipoFondo();
			slash = " / ";
		}
		if (filtroRicerca.getCodiceSottoCategoria() != null && !filtroRicerca.getCodiceSottoCategoria().isEmpty()){
			LabelValueBean vo = poninchiaroService.getSottoCategoriaByCod(filtroRicerca.getCodiceSottoCategoria());
			if (vo != null){
				filtri = filtri + slash + "Sotto categoria" + vo.getLabel();
				slash = " / ";
			}
		}
		model.addAttribute("filtri", filtri);
		//model.addAttribute("tipoFondo", filtroRicerca.getTipoFondo());
		
		VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
		List<VOIstituto> elencoIstituti = poninchiaroService.getIstitutiScuoledelpon(filtroRicerca, evento.getPrgEve());
		model.addAttribute("istituti", elencoIstituti);
		model.addAttribute("numeroscuole", elencoIstituti.size());
		model.addAttribute("dataAggiornamento", evento.getDataAggiornamento());
		
		String queryString = "?";
		if (request.getQueryString()!=null){
			queryString = "?" + request.getQueryString().toString();
		}
		
		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		
		model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
		request.getSession().setAttribute("linkRisultatiRicerca", "/scuoledelpon/ricerca/istituti"+queryString);
		
		PagedListHolder<VOIstituto> pagedListHolder = new PagedListHolder<VOIstituto>(elencoIstituti);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(PAGE_SIZE);
		pagedListHolder.setMaxLinkedPages(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		
		model.addAttribute("title","Le scuole del PON - ricerca istituti: " + elencoIstituti.size() + " risultati trovati - Pon in Chiaro");
		model.addAttribute("description","La ricerca effettuata ha prodotto " + elencoIstituti.size() + " risultati utili");
		model.addAttribute("keywords","Le scuole del PON, pon in chiaro, Fondi Strutturali Europei, Fondo Sociale Europeo, Fondo Europeo Sviluppo Regionale, Programma Operativo Nazionale, PON. schede scuole fondi europei, " + filtri);
		model.addAttribute("socialTitle","Le scuole del PON - ricerca istituti: " + elencoIstituti.size() + " risultati trovati");
		model.addAttribute("urlPage","http://poninchiaro.istruzione.it/poninchiaro/scuoledelpon/ricerca/istituti"+queryStringWithoutPage);
		model.addAttribute("canonical","http://poninchiaro.istruzione.it/poninchiaro/scuoledelpon/ricerca/istituti"+queryStringWithoutPage);
		
		return "lescuoledelpon/elencoistituti";
	}
	
}
