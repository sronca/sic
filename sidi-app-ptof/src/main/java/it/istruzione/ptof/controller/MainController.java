package it.istruzione.ptof.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
 

@Controller
public class MainController {
  
	private static Logger logger = Logger.getLogger(MainController.class);
  

	@RequestMapping("/home.do")
	public String home(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String go = "home";
		if ( sidiUser.getCurrentProfile()==null){
			if (sidiUser.getSidiProfiles().size()>0 ) {
				go = "redirect:/login/change-profile.do";
			} else {
				sidiUser.setCurrentProfile(sidiUser.getSidiProfiles().iterator().next());
				go = "home";
			}
		}

		return go;
	}


	@RequestMapping("/login/change-profile.do")
	public String changeProfile(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  

		return "login-00-change-profile";
	}

	
	@RequestMapping("/fn-gestione-documenti.do")
	public String gestioneDocumenti(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  

		return "gestione-00-gestione-documenti";
	}

	
	@RequestMapping(value="/fn-gestione-ptof.do",  method = RequestMethod.GET )
	public String gestionePtof(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  

		return "gestione-00-gestione-ptof";
	}

	
	@RequestMapping(value="/fn-pubblicazione-ptof.do",  method = RequestMethod.GET )
	public String pubblicazionePtof(Model model, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return "gestione-00-pubblica-documento";
	}

	
	
	
	@RequestMapping(value="/fn-gestione-sezione-ptof.do",  method = RequestMethod.GET )
	public String gestioneSezionePtof(Model model, HttpServletRequest req, @RequestParam("codsez")  String codsez ) throws Exception {
		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return TIPO_SEZIONE.getInstanceFromCode(codsez).key();
	}
	
}