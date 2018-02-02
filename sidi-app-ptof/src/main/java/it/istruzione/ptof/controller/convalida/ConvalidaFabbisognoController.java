package it.istruzione.ptof.controller.convalida;

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
public class ConvalidaFabbisognoController {
  
	private static Logger logger = Logger.getLogger(ConvalidaFabbisognoController.class);
	
	@RequestMapping("/fn-convalida-fabbisogno.do")
	public String convalidaFabbisogno(Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_CONVALIDA_FABBISOGNO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.CRUSCOTTO_CONVALIDA_FABBISOGNO);
		return "fn-mon-documenti-attivabili";
	}
	
	@RequestMapping("/fn-cruscotto-convalida-fabbisogno.do")
	public String cruscottoConvalidaFabbisogno(@RequestParam Integer progresivoGestioneCatalogoDocumento, Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_CONVALIDA_FABBISOGNO_TITTLE);
		model.addAttribute("tipologiaReport", AppConstant.CRUSCOTTO_CONVALIDA_FABBISOGNO);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		model.addAttribute("contesto", sidiUser.getCurrentContesto().getDescrizione());
		return "fn-convalida-fabbisogno";
	}
	
	@RequestMapping("/fn-cruscotto-convalida-singole-fabbisogno.do")
	public String reportCompletoDettaglio(@RequestParam Integer progresivoGestioneCatalogoDocumento,Model model, HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession(true);
		SidiUser sidiUser = (SidiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("titoloReport", AppConstant.CRUSCOTTO_FABBISOGNO_CONVALIDA_SINGOLA_TITLE);
		model.addAttribute("tipologiaReport", AppConstant.CRUSCOTTO_FABBISOGNO_CONVALIDA_SINGOLA);
		model.addAttribute("progressivoGestioneCatalogoDocumento", progresivoGestioneCatalogoDocumento);
		return "fn-cruscotto-convalida-singole-fabbisogno";
	}
}