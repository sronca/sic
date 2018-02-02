package it.istruzione.ptof.controller.consultazionePtof;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.istruzione.commons.security.SidiUser;
import it.istruzione.ptof.constant.AppConstant;
 
     
@Controller
public class GestionePuntualePtofController {
  
	private static Logger logger = Logger.getLogger(GestionePuntualePtofController.class);

	 
	
	@RequestMapping("/fn-consultazione-puntuale.do")
	public String consultazionePuntuale( Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("titoloReport", AppConstant.CONSULTAZIONE_PUNTUALE_TITLE );
		model.addAttribute("tipologiaReport", AppConstant.CONSULTAZIONE_PUNTUALE_PTOF );
		
 
		return "fn-mon-documenti-attivabili";
	}
	
	@RequestMapping("/fn-consultazione-puntuale-dettaglio.do")
	public String consultazionePuntualeDettaglio(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("titoloReport", AppConstant.CONSULTAZIONE_PUNTUALE_TITLE );
		model.addAttribute("tipologiaReport", AppConstant.CONSULTAZIONE_PUNTUALE_PTOF );
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
 
		return "fn-mon-consultazione-ptof";
	}
	
	
	@RequestMapping("/fn-consultazione-puntuale-fabbisogno.do")
	public String reportCompletoDettaglio(Model model, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("titoloReport", AppConstant.CONSULTAZIONE_PUNTUALE_TITLE );
		model.addAttribute("tipologiaReport", AppConstant.CONSULTAZIONE_PUNTUALE_PTOF );
		return "fn-mon-consultazione-dettaglio-ptof";
	}
	
	
}