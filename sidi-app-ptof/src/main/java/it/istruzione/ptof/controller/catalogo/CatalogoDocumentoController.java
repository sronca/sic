package it.istruzione.ptof.controller.catalogo;

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
public class CatalogoDocumentoController {
  
	private static Logger logger = Logger.getLogger(CatalogoDocumentoController.class);
	
	@RequestMapping("/fn-gestione-catalogo-documenti.do")
	public String gestioneCatalogoDocumenti(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.GESTIONE_CATALOGO_DOCUMENTI_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.GESTIONE_CATALOGO_DOCUMENTI);
		return "fn-gestione-catalogo-documenti";
	}
	
	@RequestMapping("/fn-gestione-catalogo-documenti-decreti.do")
	public String gestioneCatalogoDocumentiDecreti(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.GESTIONE_CATALOGO_DOCUMENTI_DECRETI_TITTLE);
		return "fn-gestione-catalogo-documenti-decreti";
	}
	

	@RequestMapping("/fn-gestione-catalogo-documenti-decreti-dettaglio.do")
	public String cruscottoConvalidaFabbisogno(@RequestParam Integer progresivoGestioneCatalogoDocumento, Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.GESTIONE_CATALOGO_DOCUMENTI_DECRETI_TITTLE);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-gestione-catalogo-documenti-decreti-dettaglio";
	}
}