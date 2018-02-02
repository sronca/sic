package it.istruzione.ptof.controller.home;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.istruzione.commons.menu.Menu;
import it.istruzione.commons.menu.MenuBuilder;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.commons.security.SidiProfile;
import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.GUContesti;
import it.istruzione.ptof.beans.GUProfiliDTO;
import it.istruzione.ptof.beans.GlobalFunctionsDTO;
import it.istruzione.ptof.beans.constant.SESSION_CONS;
import it.istruzione.ptof.controller.MainController;
import it.istruzione.ptof.helper.SidiAppCommonsAppProperties;
import it.istruzione.ptof.helper.RequestInvalidException;
import it.istruzione.ptof.service.HomeService;
import it.miur.eds.gestioneutenze.shared.ContestoType;
import it.miur.eds.gestioneutenze.shared.dto.ContestiUtenteDTO;

@RestController
// @PreAuthorize("isAuthorized('#MyFunctionality')")
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private MenuBuilder menuBuilder;
	
	@Autowired
	HomeService homeService ;
	
	@RequestMapping(value = "/home/menu.json", method = RequestMethod.POST)
	public Map<String, Object> menu(@RequestBody HomeBaseForm action, BindingResult bindingResult, HttpSession session)
			throws Exception {
		Map<String, Object> out = new LinkedHashMap<String, Object>();
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		Menu menu =null;
		if ( session.getAttribute(SESSION_CONS.MENU.name())!=null ) {
		  menu = ( Menu )session.getAttribute(SESSION_CONS.MENU.name());  
        } else {
		  menu = menuBuilder.creaMenu(sidiUser);
		  session.setAttribute(SESSION_CONS.MENU.name(),menu);
        }
		
		LinkedHashMap<String, Object> attribForm = new LinkedHashMap<>();
		attribForm.put("menu", menu.getMenu());
		out.put("attribForm", attribForm);

		return out;

	}

	@RequestMapping(value = "/home/initFormProfili.json", method = RequestMethod.POST)
	public Map<String, Object> initFormProfili(@RequestBody HomeBaseForm action, BindingResult bindingResult,
			HttpSession session) {
		Map<String, Object> out = new LinkedHashMap<String, Object>();
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LinkedList<GUProfiliDTO>  listaProfiloUtente = new LinkedList<GUProfiliDTO> ();		
		for (SidiProfile sidiProfile : sidiUser.getSidiProfiles()) {
			GUProfiliDTO profilo = new GUProfiliDTO();
			profilo.setDesc(sidiProfile.getDescrizione());
			profilo.setValue(sidiProfile.getNome());
			profilo.setId(sidiProfile.getNome());// TODO da rivedere 
		 	LinkedList<GUContesti>  contestoUtenteL = new LinkedList<GUContesti>(); 
			for (SidiContesto contesto : sidiProfile.getContesti()){
				GUContesti contestoDTO = new GUContesti();
				contestoDTO.setDesc(contesto.getDescrizione());// cm-desc
				contestoDTO.setValue(contesto.getCodice());
				contestoDTO.setTipo(contesto.getTipo());// numero
				contestoDTO.setId(contesto.getTipo()+"_"+contesto.getCodice());// cm-forte
				contestoUtenteL.add(contestoDTO);
			}
			profilo.setContestoUtenteL(contestoUtenteL);
			listaProfiloUtente.add(profilo);
		}
		LinkedHashMap<String, Object> attribForm = new LinkedHashMap<>();
		attribForm.put("profiloUtenteL", listaProfiloUtente);
		out.put("attribForm", attribForm);
		return out;
	}
	

	@RequestMapping(value = "/home/profilo.json", method = RequestMethod.POST)
	public Map<String, Object> setProfilo(@RequestBody Map<String,String> param , BindingResult bindingResult,
			HttpSession session) {
		Map<String, Object> out = new LinkedHashMap<String, Object>();
		GlobalFunctionsDTO globalFunctionsDTO = new GlobalFunctionsDTO();
		
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (SidiProfile sidiProfile : sidiUser.getSidiProfiles()) {
			if ( param.get("idProfilo").equalsIgnoreCase(sidiProfile.getNome()) ){
				sidiUser.setCurrentProfile(sidiProfile);
			}
			for (SidiContesto contesto : sidiProfile.getContesti()){
				if ( param.get("idContesto").equalsIgnoreCase(contesto.getTipo()+"_"+contesto.getCodice()) ){
					sidiUser.setCurrentContesto( contesto );
				}
			}
		 
		}
	
		if( sidiUser.getCurrentContesto().getTipo()!=null 
				&& sidiUser.getCurrentContesto().getTipo().equalsIgnoreCase(ContestoType.SCUOLE_PRINCIPALI.getCode().intValue()+"") ){
			globalFunctionsDTO.setIstitutoScolasticoDTO(homeService.loadIstitutoScolasticoDTO(sidiUser.getCurrentContesto().getCodice()));
		}
		
		session.setAttribute(SESSION_CONS.GLOBAL_FUNCTIONS.name(), globalFunctionsDTO);
		
		// RESETTO IL MENU
		session.setAttribute(SESSION_CONS.MENU.name(),null); 
		
	 	return out;
	}

	@Autowired
	private SidiAppCommonsAppProperties prop;
    
	@RequestMapping(value = "/home/logout.json", method = RequestMethod.POST)
 	public String logout(@RequestBody String form, HttpServletRequest request  ) throws Exception {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		HttpSession hs = request.getSession();
		Enumeration<String>  e = hs.getAttributeNames();
			while (e.hasMoreElements()) {
			String attr = e.nextElement();
			hs.setAttribute(attr, null);
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setMaxAge(0);
				}
			}
		hs.invalidate();
		return prop.getLinkEsci();
	}
}
