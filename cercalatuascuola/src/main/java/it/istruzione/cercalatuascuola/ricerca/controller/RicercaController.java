package it.istruzione.cercalatuascuola.ricerca.controller;

import it.istruzione.cercalatuascuola.common.util.StaticSelect;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;
import it.istruzione.cercalatuascuola.ricerca.dao.CercaLaTuaScuolaDAO;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VORicerca;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOScuola;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOServizi;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VoCookieScuoleSelezionate;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VoCriteriPrecedenza;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VoMarker;
import it.istruzione.cercalatuascuola.ricerca.service.RicercaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

@Controller
public class RicercaController {
    private CercaLaTuaScuolaDAO cercaLaTuaScuolaDAO;
    private RicercaService ricercaService;
    private AnagraficaScuolaService anagraficaScuolaService;
    
    private static Logger log = Logger.getLogger(RicercaController.class);
    private static final String TIPO_RICERCA_RAPIDA = "RAPIDA";
    private static final String TIPO_RICERCA_VICINO_A_TE = "VICINO_A_TE";
    private static final String TIPO_RICERCA_AVANZATA = "AVANZATA";
    private static final int PAGE_SIZE = 10;
    
    @Value("${anno.scolastico.indirizzi.studio}")
    private String ANNO_SCOLASTICO_INDIRIZZI_STUDIO;
    
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RicercaController(CercaLaTuaScuolaDAO cercaLaTuaScuolaDAO, RicercaService ricercaService, AnagraficaScuolaService anagraficaScuolaService) {
        this.cercaLaTuaScuolaDAO = cercaLaTuaScuolaDAO;
        this.ricercaService = ricercaService;
        this.anagraficaScuolaService = anagraficaScuolaService;
    }
    
	@RequestMapping({ "/", "/index.html" })
	public String index(Model model,
						VORicerca voRicerca,
						HttpServletRequest request,
						String tip) throws Exception {
		log.debug("in index");
		log.debug("ANNO_SCOLASTICO_INDIRIZZI_STUDIO : " +ANNO_SCOLASTICO_INDIRIZZI_STUDIO);
		log.debug("tip : " + tip);
		model.addAttribute("voRicerca", voRicerca);
		if (tip != null && 
				(tip.equals("1") || tip.equals("2") || tip.equals("3"))){
			model.addAttribute("tip", tip);
		}else{
			model.addAttribute("tip", "1");
		}
		
		
		String callBackUrl = request.getParameter("callBackUrl");
		log.debug("callBackUrl : " +callBackUrl);
		if (callBackUrl != null && !callBackUrl.trim().equals("") && !callBackUrl.trim().equals("null")){
			request.getSession().setAttribute("callBackUrl", callBackUrl);
			model.addAttribute("callBackUrl", callBackUrl);
		}
		
		Map<String, String> listaRegioni = ricercaService.getRegioniList();
		Map<String, String> listaOrdini = StaticSelect.caricaOrdini();
		Map<String, String> listaRaggi = StaticSelect.caricaRaggi();
		
		model.addAttribute("listaRegioni", listaRegioni);
		model.addAttribute("listaOrdini", listaOrdini);
		model.addAttribute("listaRaggi", listaRaggi);
		String msgANNO_SCOLASTICO_INDIRIZZI_STUDIO = "";
		if (ANNO_SCOLASTICO_INDIRIZZI_STUDIO != null && !ANNO_SCOLASTICO_INDIRIZZI_STUDIO.equals("")){
			msgANNO_SCOLASTICO_INDIRIZZI_STUDIO = " (indirizzi validi per l'anno " + ANNO_SCOLASTICO_INDIRIZZI_STUDIO.substring(0, 4) + "/" + ANNO_SCOLASTICO_INDIRIZZI_STUDIO.substring(4, 6) + ")";
		}
		model.addAttribute("msgANNO_SCOLASTICO_INDIRIZZI_STUDIO",msgANNO_SCOLASTICO_INDIRIZZI_STUDIO);
		
		model.addAttribute("title","Scuola in Chiaro - Cerca le scuole, esamina e confronta le loro caratteristiche");
		model.addAttribute("description","Scuola in Chiaro 2.0 � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
		model.addAttribute("keywords","scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
		model.addAttribute("socialTitle","Scuola in Chiaro - Cerca le scuole, esamina e confronta le loro caratteristiche");
		model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/");
		model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/");
		
		return "ricerca/home";
	}
	
	 @RequestMapping(value = "/ricerca/nuova", method = RequestMethod.GET)
	public String nuova_ricerca(Model model,
						VORicerca voRicerca,
						HttpServletRequest request) throws Exception {
		log.debug("in nuova_ricerca");
		log.debug("ANNO_SCOLASTICO_INDIRIZZI_STUDIO : " +ANNO_SCOLASTICO_INDIRIZZI_STUDIO);
		model.addAttribute("voRicerca", voRicerca);
		
		
		String callBackUrl = request.getParameter("callBackUrl");
		log.debug("callBackUrl : " +callBackUrl);
		if (callBackUrl != null && !callBackUrl.trim().equals("") && !callBackUrl.trim().equals("null")){
			request.getSession().setAttribute("callBackUrl", callBackUrl);
			model.addAttribute("callBackUrl", callBackUrl);
		}
		
		Map<String, String> listaRegioni = ricercaService.getRegioniList();
		Map<String, String> listaOrdini = StaticSelect.caricaOrdini();
		Map<String, String> listaRaggi = StaticSelect.caricaRaggi();
		
		model.addAttribute("listaRegioni", listaRegioni);
		model.addAttribute("listaOrdini", listaOrdini);
		model.addAttribute("listaRaggi", listaRaggi);
		String msgANNO_SCOLASTICO_INDIRIZZI_STUDIO = "";
		if (ANNO_SCOLASTICO_INDIRIZZI_STUDIO != null && !ANNO_SCOLASTICO_INDIRIZZI_STUDIO.equals("")){
			msgANNO_SCOLASTICO_INDIRIZZI_STUDIO = " (indirizzi validi per l'anno " + ANNO_SCOLASTICO_INDIRIZZI_STUDIO.substring(0, 4) + "/" + ANNO_SCOLASTICO_INDIRIZZI_STUDIO.substring(4, 6) + ")";
		}
		model.addAttribute("msgANNO_SCOLASTICO_INDIRIZZI_STUDIO",msgANNO_SCOLASTICO_INDIRIZZI_STUDIO);
		
		model.addAttribute("title","Scuola in Chiaro - Cerca le scuole, esamina e confronta le loro caratteristiche");
		model.addAttribute("description","Scuola in Chiaro 2.0 � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
		model.addAttribute("keywords","scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
		model.addAttribute("socialTitle","Scuola in Chiaro - Cerca le scuole, esamina e confronta le loro caratteristiche");
		model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/");
		model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/");
		
		
		return "ricerca/nuova_ricerca";
	}
	
    @RequestMapping(value = "/ricerca/risultati", method = RequestMethod.GET)
    public String ricerca_risultati(Model model, 
    								VORicerca voRicerca,
    								HttpServletRequest request) {
    	log.debug("start method ricerca_risultati");
    	log.debug(ReflectionToStringBuilder.toString(voRicerca));
    	
    	try {
    		List<VOScuola> elencoScuole = new ArrayList<VOScuola>();
    		model.addAttribute("keywordsricercarapida","");
    		if (voRicerca != null){
    			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_RAPIDA)){
    				
    				elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicercaRapida(voRicerca.getRapida());
    				model.addAttribute("keywordsricercarapida",voRicerca.getRapida());
    				
    			}else if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)
    						|| voRicerca.getTipoRicerca().equals(TIPO_RICERCA_AVANZATA)){
    				
    				if (voRicerca.getCodiceOrdine() == null || voRicerca.getCodiceOrdine().equals("")){
    					
    					elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicerca(voRicerca);
    					elencoScuole.addAll(cercaLaTuaScuolaDAO.getListaCentriFormazioneProfessionale(voRicerca));
    					
    				}else if (voRicerca.getCodiceOrdine().equals("5")){
    					
    					elencoScuole = cercaLaTuaScuolaDAO.getListaCentriFormazioneProfessionale(voRicerca);
    					
    				}else{

    					elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicerca(voRicerca);
    					
    				}
    				
    			}
    			
    		}
    		
    		
    		String queryString = "?" + request.getQueryString().toString();
    		
			String idform = "";
			int generaIdForm = ServletRequestUtils.getIntParameter(request, "gidf", 0);
			if (generaIdForm == 1){
				idform = "result-" + Utility.numberRnd();
				queryString = queryString + "&idform=" + idform;
				model.addAttribute("idform", idform);
			}else
				model.addAttribute("idform", ServletRequestUtils.getStringParameter(request, "idform","result-" + Utility.numberRnd()));
			   		
    		String queryStringWithoutPage = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
    		queryStringWithoutPage = queryStringWithoutPage.replaceAll("(\\?gidf=)\\d+", "?").replaceAll("(\\&gidf=)\\d+", "").replaceAll("\\?&", "?");
    		
    		
			model.addAttribute("queryStringWithoutPage", queryStringWithoutPage);
			request.getSession().setAttribute("linkRisultatiRicerca", "/ricerca/risultati"+queryString);
			
			// initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
			PagedListHolder<VOScuola> pagedListHolder = new PagedListHolder<VOScuola>(elencoScuole);
			int page = ServletRequestUtils.getIntParameter(request, "page", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(PAGE_SIZE);
			pagedListHolder.setMaxLinkedPages(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("numeroScuoleTrovate", elencoScuole.size());
			
			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_RAPIDA)){
				model.addAttribute("title","Ricerca: " + elencoScuole.size() + " risultati per il termine " + voRicerca.getRapida() + " - Scuola in Chiaro");
				model.addAttribute("description","La ricerca effettuata per il termine " + voRicerca.getRapida() + " ha prodotto " + elencoScuole.size() + " risultati utili");
				model.addAttribute("keywords","scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online, " + voRicerca.getRapida());
				model.addAttribute("socialTitle","Ricerca: " + elencoScuole.size() + " risultati per il termine " + voRicerca.getRapida() + "");
				model.addAttribute("dimensionValue","search_simple");
			}else if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				model.addAttribute("title","Ricerca per posizione: " + elencoScuole.size() + " risultati trovati per " + voRicerca.getIndirizzoRiferimento() + " - Scuola in Chiaro");
				model.addAttribute("description","La ricerca effettuata per l'indirizzo " + voRicerca.getIndirizzoRiferimento() + " ha prodotto " + elencoScuole.size() + " risultati utili");
				model.addAttribute("keywords","scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online, " + voRicerca.getIndirizzoRiferimento());
				model.addAttribute("socialTitle","Ricerca per posizione: " + elencoScuole.size() + " risultati trovati per " + voRicerca.getIndirizzoRiferimento() + "");
				model.addAttribute("dimensionValue","search_position");
			}else if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_AVANZATA)){
				model.addAttribute("title","Ricerca avanzata: " + elencoScuole.size() + " risultati trovati - Scuola in Chiaro");
				model.addAttribute("description","La ricerca effettuata ha prodotto " + elencoScuole.size() + " risultati utili");
				model.addAttribute("keywords","scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online, " + voRicerca.getDenominazione() + " " + voRicerca.getCodMecc());
				model.addAttribute("socialTitle","Ricerca avanzata: " + elencoScuole.size() + " risultati trovati");
				model.addAttribute("dimensionValue","search_advanced");
			}
			
			
			model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/ricerca/risultati"+queryStringWithoutPage);
			model.addAttribute("canonical","");

			
			
		} catch (Exception e) {
			log.error("ERRORE : ",e);
		}
        return "ricerca/risultati";
    }
    
    @RequestMapping(value = "/mappa/risultati", method = RequestMethod.GET)
    public String mappa_risultati(Model model, 
    								VORicerca voRicerca,
    								HttpServletRequest request) {
    	log.debug("start method mappa_risultati");
    	log.debug(ReflectionToStringBuilder.toString(voRicerca));
    	
    	try {
    		List<VOScuola> elencoScuole = new ArrayList<VOScuola>();
    		if (voRicerca != null){
    			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_RAPIDA)){
    				
    				elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicercaRapida(voRicerca.getRapida());
    				model.addAttribute("keywordsricercarapida",voRicerca.getRapida());
    				
    			}else if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)
    						|| voRicerca.getTipoRicerca().equals(TIPO_RICERCA_AVANZATA)){
    				
    				if (voRicerca.getCodiceOrdine().equals("5"))
    					elencoScuole = cercaLaTuaScuolaDAO.getListaCentriFormazioneProfessionale(voRicerca);
    				else
    					elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicerca(voRicerca);
    				
    			}
    			
    		}
    		
			model.addAttribute("scuoleDaConfrontare", elencoScuole);
			
			
		} catch (Exception e) {
			log.error("ERRORE : ",e);
		}
        return "ricerca/mappa_istituti";
    }
    
	@RequestMapping("/ricerca/getRisultati.json")
	public @ResponseBody List<VoMarker> getRisultati(VORicerca voRicerca) {
		
    	log.debug("start method getRisultati");
    	log.debug(ReflectionToStringBuilder.toString(voRicerca));

    	List<VoMarker> markers = new ArrayList<VoMarker>();
		try{
			List<VOScuola> elencoScuole = new ArrayList<VOScuola>();
			if (voRicerca.getCodiceOrdine().equals("5"))
				elencoScuole = cercaLaTuaScuolaDAO.getListaCentriFormazioneProfessionale(voRicerca);
			else
				elencoScuole = cercaLaTuaScuolaDAO.getListaScuoleRicerca(voRicerca);
			
			log.debug("elencoScuole.size() : " + elencoScuole.size());
			
			String iconPrefix = "/cercalatuascuola/resources/css/images/pin/";
			for(VOScuola voScuola : elencoScuole){
				if (!voScuola.getLatitudine().isEmpty() && !voScuola.getLongitudine().isEmpty()){
					VoMarker marker = new VoMarker();
					String data = "<strong>"+voScuola.getDescrizione()+"</strong>"
							   + "||"+voScuola.getOrdine()+" "+voScuola.getTipologia()
							   + "||"+voScuola.getIndirizzoCompleto()
							   + "||Tel. "+voScuola.getTelefono()
							   + "||<strong><a href='/cercalatuascuola/istituti/"+voScuola.getCodMecc()+"/"+voScuola.getNormalizedName()+"/'>Vai alla scheda</a></strong>";
					marker.setData(data);
					marker.setLat(voScuola.getLatitudine());
					marker.setLng(voScuola.getLongitudine());
					marker.setAddress(voScuola.getIndirizzoCompleto());
					
					if (voScuola.isCfp()){
						marker.setIcon(iconPrefix+"pin-small-3.png");
					}else if (voScuola.getCheckStatale().equals("S")){
						marker.setIcon(iconPrefix+"pin-small-1.png");
					}else if (voScuola.getCheckNonStatale().equals("S")){
						marker.setIcon(iconPrefix+"pin-small-2.png");
					}
					
					markers.add(marker);
				}
				
			}
		}catch(Exception ex){
			log.error(ex.getMessage(),ex);
		}
		return markers;		
	}

    @RequestMapping(value = "/confronta/risultati", method = RequestMethod.GET)
    public String confronta_risultati(Model model,
    								  String idform,
    								  HttpServletRequest request) {
    	log.debug("start method confronta_risultati : " + idform);
    	
    	String strCookie = "";
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals("cookie_" + idform)) {
    				log.debug("cookie.getValue() : " + cookie.getValue());
    				strCookie = cookie.getValue();
    			}
    		}
    	}
    	
    	try {
    		String decodedCookie = URLDecoder.decode(strCookie,Charset.defaultCharset().name());
    		log.debug("decodedCookie : " + decodedCookie);
    		VoCookieScuoleSelezionate voCookieScuoleSelezionate = objectMapper.readValue(decodedCookie, VoCookieScuoleSelezionate.class);
    		List<String> listaCodMeccScuoleDaConfrontare = new ArrayList<String>();
    		if (voCookieScuoleSelezionate != null && voCookieScuoleSelezionate.getItems() != null){
    			log.debug("scuoleDaConfrontare.getItems().size() : " + voCookieScuoleSelezionate.getItems().size());
    			Iterator<HashMap<String, String>> it = voCookieScuoleSelezionate.getItems().iterator();
    			while (it.hasNext()){
    				HashMap<String, String> vo = it.next();
    				listaCodMeccScuoleDaConfrontare.add(vo.values().iterator().next());
    			}
    		}
    		for (String codiceMeccanografico : listaCodMeccScuoleDaConfrontare){
    			log.debug(codiceMeccanografico);
    		}

    		List<VOServizi> servizi = cercaLaTuaScuolaDAO.getServiziRicercaAvanzataEPopupConfronta();
    		List<VOScuola> scuoleDaConfrontare = new ArrayList<VOScuola>();
    		
    		for (int i=0; i<listaCodMeccScuoleDaConfrontare.size(); i++)
    		{
    			VOScuola voScuola = cercaLaTuaScuolaDAO.getScuola(listaCodMeccScuoleDaConfrontare.get(i));
    			//voScuola = cercaLaTuaScuolaDAO.getServiziScuola(voScuola, voRicerca.getAnnoAccademico(),request.getParameter("serviziAggiuntivi"));
    			voScuola = cercaLaTuaScuolaDAO.getServiziScuola(voScuola);

    			int numAlunni = 0;
    			int numClassi = 0;
    			List<VOAnniCorsoAlunni> voAnniCorsoAlunni = cercaLaTuaScuolaDAO.getAlunniPerAnnoCorso(voScuola.getCodForte());
    			if (voAnniCorsoAlunni != null){
    				for (int anno=0; anno<voAnniCorsoAlunni.size();anno++){
    					VOAnniCorsoAlunni vo = voAnniCorsoAlunni.get(anno);
    					numAlunni = numAlunni + Integer.valueOf(vo.getNumAlu()).intValue();
    					numClassi = numClassi + Integer.valueOf(vo.getNumCla()).intValue();
    				}
    			}
    			if (numAlunni > 0 && numClassi > 0){
    				voScuola.setNumeroAlunni(String.valueOf(numAlunni));
    				voScuola.setNumeroClassi(String.valueOf(numClassi));
    				float media = numAlunni / numClassi;
    				java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
    				voScuola.setMediaAlunniClassi(df.format(media));
    			}


    			for (int e = 0; e < voScuola.getServizi().size(); e++)
    			{
    				VOServizi voServizioScuola = (VOServizi)voScuola.getServizi().get(e);

    				for (int a = 0; a < servizi.size(); a++)
    				{
    					VOServizi voServizioDB = (VOServizi)servizi.get(a);

    					if(voServizioScuola.getCodiceServizio1lv().equals(voServizioDB.getCodiceServizio1lv()) &&
    					   voServizioScuola.getCodiceServizio2lv().equals(voServizioDB.getCodiceServizio2lv()) &&
    					   voServizioScuola.getCodiceServizio3lv().equals(voServizioDB.getCodiceServizio3lv()))
    					{
    						switch (i)
    						{
    							case 0:
    								voServizioDB.setScuola1("S");
    								break;
    							case 1:
    								voServizioDB.setScuola2("S");
    								break;
    							case 2:
    								voServizioDB.setScuola3("S");
    								break;
    							case 3:
    								voServizioDB.setScuola4("S");
    								break;
    							case 4:
    								voServizioDB.setScuola5("S");
    							break;							
    							case 5:
    								voServizioDB.setScuola6("S");
    							break;							

    							default:
    								break;
    						}

    						break;
    					}
    				}
    			}
    			
    			scuoleDaConfrontare.add(voScuola);
    		}
    		
    		model.addAttribute("linkRisultatiRicerca",request.getSession().getAttribute("linkRisultatiRicerca"));
    		model.addAttribute("scuoleDaConfrontare",scuoleDaConfrontare);
    		model.addAttribute("scuoleDaConfrontareSize",scuoleDaConfrontare.size());
    		model.addAttribute("servizi",servizi);
    		model.addAttribute("idform", idform);
    		model.addAttribute("title","Confronta gli istituti selezionati - Scuola in Chiaro");
    		model.addAttribute("description","Scheda di confronto tra i " + scuoleDaConfrontare.size() + " istituti selezionati");
    		model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    		model.addAttribute("socialTitle","Confronta gli istituti selezionati");
    		model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/confronta/risultati/");
    		model.addAttribute("canonical","");


    	} catch (Exception e) {
			log.error("ERRORE : ",e);
		}
        return "ricerca/confronta";
    }
    
    @RequestMapping(value = "/info1", method = RequestMethod.GET)
    public String info1(Model model) {
    	log.debug("start method info");
    	
        return "ricerca/info1";
    }

    @RequestMapping(value = "/info2", method = RequestMethod.GET)
    public String info2(Model model) {
    	log.debug("start method info");
    	
        return "ricerca/info2";
    }
    
    @RequestMapping(value = "/approfondisci", method = RequestMethod.GET)
    public String approfondisci(Model model) {
    	log.debug("start method approfondisci");
    	
    	model.addAttribute("title","Il progetto - Scuola in Chiaro");
    	model.addAttribute("description","La pagina il progetto di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
    	model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    	model.addAttribute("socialTitle","Il progetto");
    	model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/approfondisci/");
    	model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/approfondisci/");
        return "ricerca/approfondisci";
    }
    
    @RequestMapping(value = "/guida", method = RequestMethod.GET)
    public String guida(Model model) {
    	log.debug("start method guida");
    	
    	model.addAttribute("title","Guida alla navigazione - Scuola in Chiaro");
    	model.addAttribute("description","La pagina di guida alla navigazione di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
    	model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    	model.addAttribute("socialTitle","Guida alla navigazione");
    	model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/guida/");
    	model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/guida/");
        return "ricerca/guida";
    }

/*    @RequestMapping(value = "/opendata", method = RequestMethod.GET)
    public String opendata(Model model) {
    	log.debug("start method opendata");
    	
    	model.addAttribute("title","Download dati - Scuola in Chiaro");
    	model.addAttribute("description","La pagina download dati di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
    	model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    	model.addAttribute("socialTitle","Download dati");
    	model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/opendata/");
    	model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/opendata/");
    	return "ricerca/opendata";
    }*/

    @RequestMapping(value = "/opendata", method = RequestMethod.GET)
    public ModelAndView opendata(Model model) {
    	log.debug("start method opendata");
    	String redirectUrl = "http://dati.istruzione.it/opendata/";
    	RedirectView redirectView = new RedirectView(redirectUrl, true);
    	redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    	return new ModelAndView(redirectView);
    }

    
    @RequestMapping(value = "/mappa_sito", method = RequestMethod.GET)
    public String mappa_sito(Model model) {
    	log.debug("start method mappa_sito");
    	
    	model.addAttribute("title","Mappa del sito - Scuola in Chiaro");
    	model.addAttribute("description","La pagina mappa del sito di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
    	model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    	model.addAttribute("socialTitle","Mappa del sito");
    	model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/mappa_sito/");
    	model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/mappa_sito/");
    	return "ricerca/mappa_sito";
    }
    
    @RequestMapping(value = "/informativa_cookie", method = RequestMethod.GET)
    public String informativa_cookie(Model model) {
    	log.debug("start method informativa_cookie");
    	
    	model.addAttribute("title","Cookie policy - Scuola in Chiaro");
    	model.addAttribute("description","La pagina di cookie policy di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
    	model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
    	model.addAttribute("socialTitle","Cookie policy");
    	model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/informativa_cookie/");
    	model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/informativa_cookie/");
        return "ricerca/informativa_cookie";
    }    
    
    @RequestMapping(value = "/preferiti", method = RequestMethod.GET)
    public String preferiti(Model model) {
    	log.debug("start method preferiti");
    	
    	
		String idform = "preferiti-" + Utility.numberRnd();
		model.addAttribute("idform", idform);
		
		model.addAttribute("title","Preferiti - Scuola in Chiaro");
		model.addAttribute("description","La pagina dei preferiti di Scuola in Chiaro, che � l'applicazione che permette di cercare una scuola o un centro di formazione professionale regionale sul territorio nazionale, conoscere tutte le informazioni disponibili sugli istituti scolastici di ogni ordine e grado, mettere a confronto la loro offerta formativa e accedere direttamente ad alcuni servizi legati alla ricerca di scuole come, per esempio, le Iscrizione on-line.");
		model.addAttribute("keywords", "scuola in chiaro, la buona scuola, schede scuole, informazioni istituti, schede dati scuole, iscrizioni online");
		model.addAttribute("socialTitle","Preferiti");
		model.addAttribute("urlPage","http://cercalatuascuola.istruzione.it/cercalatuascuola/preferiti/");
		model.addAttribute("canonical","http://cercalatuascuola.istruzione.it/cercalatuascuola/preferiti/");
		
        return "ricerca/preferiti";
    }    

    @RequestMapping(value = "/mappa_istituto/{codScuUt}/{desNomScuNorm}", method = RequestMethod.GET)
    public String mappa_istituto(@PathVariable(value = "codScuUt") String codScuUt,
    							 @PathVariable(value = "desNomScuNorm") String desNomScuNorm,
								 Model model) {
    	log.debug("start method mappa_istituto");
    	log.debug("codScuUt : " + codScuUt);
    	log.debug("desNomScuNorm : " + desNomScuNorm);
    	
    	try {
			VOScuola voScuola = cercaLaTuaScuolaDAO.getScuola(codScuUt);
			model.addAttribute("voScuola", voScuola);
		} catch (Exception e) {
			log.error("ERRORE : ",e);
		}
    	
        return "ricerca/mappa_istituto";
    }

    @RequestMapping(value = "/mappa_istituti_confronta", method = RequestMethod.GET)
    public String mappa_istituti_confronta(Model model,
    							 String idform,
    							 HttpServletRequest request) {
    	log.debug("start method start method confronta_risultati : : " + idform);
    	
    	String strCookie = "";
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals("cookie_" + idform)) {
    				log.debug("cookie.getValue() : " + cookie.getValue());
    				strCookie = cookie.getValue();
    			}
    		}
    	}
    	
    	try {
    		String decodedCookie = URLDecoder.decode(strCookie,Charset.defaultCharset().name());
    		log.debug("decodedCookie : " + decodedCookie);
    		VoCookieScuoleSelezionate voCookieScuoleSelezionate = objectMapper.readValue(decodedCookie, VoCookieScuoleSelezionate.class);
    		List<String> listaCodMeccScuoleDaConfrontare = new ArrayList<String>();
    		if (voCookieScuoleSelezionate != null && voCookieScuoleSelezionate.getItems() != null){
    			log.debug("scuoleDaConfrontare.getItems().size() : " + voCookieScuoleSelezionate.getItems().size());
    			Iterator<HashMap<String, String>> it = voCookieScuoleSelezionate.getItems().iterator();
    			while (it.hasNext()){
    				HashMap<String, String> vo = it.next();
    				listaCodMeccScuoleDaConfrontare.add(vo.values().iterator().next());
    			}
    		}
    		for (String codiceMeccanografico : listaCodMeccScuoleDaConfrontare){
    			log.debug(codiceMeccanografico);
    		}

    		List<VOScuola> scuoleDaConfrontare = new ArrayList<VOScuola>();
    		
    		for (int i=0; i<listaCodMeccScuoleDaConfrontare.size(); i++)
    		{
    			VOScuola voScuola = cercaLaTuaScuolaDAO.getScuola(listaCodMeccScuoleDaConfrontare.get(i)); 			
    			scuoleDaConfrontare.add(voScuola);
    		}
    		
    		model.addAttribute("scuoleDaConfrontare",scuoleDaConfrontare);
    		model.addAttribute("idform", idform);



    	} catch (Exception e) {
			log.error("ERRORE : ",e);
		}

        return "ricerca/mappa_istituti";
    }
    
    @RequestMapping(value = "/criteriprecedenza/{codScuUt}", method = RequestMethod.GET)
    public String criteriprecedenza(@PathVariable(value = "codScuUt") String codScuUt,
    								Model model) {
    	log.debug("start method criteriprecedenza : " + codScuUt);
    	String testo = "Le informazioni non sono al momento disponibili.";
    	String annoIOL = Utility.annoScolasticoSuccessivo();
    	
    	try {
			annoIOL = anagraficaScuolaService.getAnnoIscrizioniOnline();
		} catch (Exception e1) {
    		log.error(e1.getMessage(),e1);
    	}
    	
    	try{
    		String str_url = anagraficaScuolaService.getUrlHomePageIol("ATT_CRI_PRE");
    		log.debug("URL : " + str_url + codScuUt);
    		if (str_url != null && !str_url.isEmpty()){
    			URL url = new URL(str_url + codScuUt);
    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    			conn.setRequestMethod("GET");
    			conn.setRequestProperty("Accept", "application/json");

    			if (conn.getResponseCode() != 200) {
    				log.error("Failed to get criteriprecedenza : HTTP error code : " + conn.getResponseCode());
    				model.addAttribute("testo",testo);
    				model.addAttribute("annoIOL",annoIOL);
    				return "ricerca/criteriprecedenza";
    			}

    			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    			StringBuffer output = new StringBuffer();
    			String line;

    			while ((line = br.readLine()) != null) {
    				output.append(line);
    			}

    			conn.disconnect();

    			log.debug(output);
    			
    			if (!output.toString().isEmpty()){
    				VoCriteriPrecedenza voCriteriPrecedenza = objectMapper.readValue(output.toString(), VoCriteriPrecedenza.class);
    				log.debug(voCriteriPrecedenza.getEsito());
    				log.debug(voCriteriPrecedenza.getDescrizioneEsito());
    				log.debug(voCriteriPrecedenza.getCodiceScuola());
    				log.debug(voCriteriPrecedenza.getRegoleIscrizione());
    				testo = voCriteriPrecedenza.getRegoleIscrizione();
    			}
    			
    			
    		}

    	  } catch (MalformedURLException e) {

    		log.error(e.getMessage(),e);

    	  } catch (Exception e) {

    		  log.error(e.getMessage(),e);

    	  }

    	model.addAttribute("testo",testo);
    	model.addAttribute("annoIOL", annoIOL);
        return "ricerca/criteriprecedenza";
    }
    
    
}
