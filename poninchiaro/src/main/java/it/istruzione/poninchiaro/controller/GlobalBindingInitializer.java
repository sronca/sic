package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.HtmlEscapeStringEditor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalBindingInitializer {


	@InitBinder
	public void init(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, new HtmlEscapeStringEditor());

	}

}
